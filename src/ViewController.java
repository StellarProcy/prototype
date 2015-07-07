import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;


public class ViewController implements Renderable, ActionListener{
	
	ViewController (JFrame frame){
		this.frame = frame;
		initialize();
	}
	
	private JFrame frame;
	private ComponentManager buffer;
	private ComponentManager delBuffer;
	private DialogFrame dialog;
	private ConfirmRemovingDialog confirmDialog;
	
	private int statement = 16;
	private final int ST_NEW_IN = 2;
	private final int ST_NEW_EX = 4;
	private final int ST_NEW_WA = 8;
	private final int ST_EDIT = 16;
	private final int ST_DEL = 32;
	
	private final String NO_NAME = "Безымянный";
	
	private final ComponentOrientation categoryOrientation = ComponentOrientation.RIGHT_TO_LEFT;
	private final ComponentOrientation walletOrientation = ComponentOrientation.LEFT_TO_RIGHT;
	
	public void addWallets(Collection<WalletImpl> wallets){
		createList(wallets, this.wallets, waColor, getWaRect(), walletOrientation);
		
	}
	public void addIncome(Collection <WalletImpl> income){
		createList(income, this.income, inColor, getInRect(), categoryOrientation);
	}
	public void addExpense(Collection<WalletImpl> expense){
		createList(expense, this.expense, exColor, getExRect(), categoryOrientation);
	}
	public List<ComponentManager> getWallets() {
		return wallets;
	}
	
	public List<ComponentManager> getIncome() {
		return income;
	}
	
	public List<ComponentManager> getExpense() {
		return expense;
	}

	

	private void initialize(){
		List<JButton> list = Arrays.asList(addIncome, addExpense, addWallet);
		for (JButton button: list){
			frame.getContentPane().add(button);
			button.setMargin(new Insets(0, 0, 0, 0));
			button.addActionListener(this);
		}
	}
	@Override
	public void render() {
		addIncome.setBounds(
				ViewUtil.consecutiveLocation(
						income, getInRect(), CATEGORY_RANGE, ViewUtil.LOCATE_DOWN));

		addExpense.setBounds(
				ViewUtil.consecutiveLocation(
						expense, getExRect(), CATEGORY_RANGE, ViewUtil.LOCATE_DOWN));
		
		addWallet.setBounds(
				ViewUtil.consecutiveLocation(
						wallets, getWaRect(), WALLET_RANGE, ViewUtil.LOCATE_RIGHT));
				
		
		frame.repaint();
	}

	
	private List<ComponentManager> income = new ArrayList<ComponentManager>();
	private List<ComponentManager> expense = new ArrayList<ComponentManager>();
	
	
	private final String addIncomeName = "Добавить доход";
	private final String addExpenseName = "Добавить расход";
	private final String addWalletName = "Добавить кошелек";
	
	private List<ComponentManager> wallets = new ArrayList<ComponentManager>();
	
	private JButton addIncome = new JButton(addIncomeName);
	private JButton addExpense = new JButton(addExpenseName);
	private JButton addWallet = new JButton(addWalletName);
	
	private final Color inColor = Color.GREEN;
	private final Color exColor = new Color(255, 102, 51);
	private final Color waColor = null;
	
	private final Rectangle categoryRect = 
			new Rectangle(10, 100, 140, 55);
	private final int CATEGORY_RANGE = 5;
	
	private final Rectangle walletRect = 
			new Rectangle(10, 30, 130, 60);
	private final int WALLET_RANGE = 20;
	
	private Rectangle getInRect(){
		return (Rectangle) categoryRect.clone();
	}
	private Rectangle getExRect(){
		 Rectangle result = (Rectangle) categoryRect.clone();
		 result.x += result.width + CATEGORY_RANGE;
		 return result;
	}
	private Rectangle getWaRect(){
		return (Rectangle) walletRect.clone();
	}
	
	
	
	private void createList (Collection <WalletImpl> src, List<ComponentManager> dest, Color color, Rectangle rect, ComponentOrientation orientation){
		for (WalletImpl wi : src){
			dest.add(
					create (wi, color, rect, orientation)
					);
		}
		render();
	}
	
	public ComponentManager create(WalletImpl wi, Color color, Rectangle rect, ComponentOrientation orientation){
		ComponentManager cm = new ComponentManager(wi);
		if (color != null)
			cm.button.setBackground(color);
		cm.field.setComponentOrientation(orientation);
		cm.setName(wi.getName());
		cm.button.setText(wi.getName());
		cm.close.addActionListener(this);
		cm.edit.addActionListener(this);
		cm.setBounds(rect);
		cm.appendTo(frame.getContentPane());
		return cm;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object unknown = arg0.getSource();
		check(unknown, income);
		check(unknown, expense);
		check(unknown, wallets);
		check(unknown, dialog);
		check(unknown);
		check(unknown, confirmDialog);
		render();	
	}
	
	
	private void openDialog(ComponentManager cm){
		buffer = cm;
		String name;
		if (statement != ST_EDIT)
			name = "Создание";
		else name = "Редактирование";
		dialog = new DialogFrame(frame, true, name + " " + cm.getWallet().getName(), 
				cm.getWallet().getAmount(), hasComment(cm));
		dialog.cancelButton.addActionListener(this);
		dialog.okButton.addActionListener(this);
		dialog.setVisible(true);	
	}
	
	private void clearBuffer(){
		buffer.setVisible(false);
		frame.remove(buffer);
		expense.remove(buffer);
		income.remove(buffer);
		wallets.remove(buffer);
		buffer = null;
	}
	private void addComponent(ComponentManager cm, List<ComponentManager> list, ComponentOrientation orientation){
		ComponentManager clone = create(cm.getWallet().clone(), cm.button.getBackground(), cm.getBounds(), orientation);
		list.add(clone);
		clearBuffer();
		render();
	}
	
	
	private void fill(ComponentManager unknown, DialogFrame frame){
		if (statement == ST_NEW_EX){
			addComponent(unknown, expense, categoryOrientation);
		} else if (statement == ST_NEW_IN){
			addComponent(unknown, income, categoryOrientation);
		} else if (statement == ST_NEW_WA){
			addComponent(unknown, wallets, walletOrientation);
		}
		int amount = Integer.parseInt(frame.textMonetary.getText());
		Monetary monetary = unknown.getWallet().getAmount();
		String text = frame.textName.getText();
		unknown.setName(text);
		unknown.button.setText(text);
		monetary.set(amount);
		unknown.field.setText(monetary.toString());
	}
	
	private boolean hasComment(ComponentManager cm){
		if (income.contains(cm) || expense.contains(cm))
			return true;
		return false;
	}

	
	private void check(Object unknown, List<ComponentManager> list){
		for (ComponentManager cm: list){
			if (unknown.equals(cm.close)){
				statement = ST_DEL;
				delBuffer = cm;
				confirmDialog = new ConfirmRemovingDialog(frame, true, cm.getName());
				confirmDialog.cancelButton.addActionListener(this);
				confirmDialog.okButton.addActionListener(this);
				confirmDialog.setVisible(true);
				break;
			} else if (unknown.equals(cm.edit)){
				statement = ST_EDIT;
				openDialog(cm);
				break;
			}
		}
	}

	private void check (Object unknown, ConfirmRemovingDialog obj){
		if (obj == null || statement != ST_DEL)
			return;
		if (unknown.equals(obj.cancelButton)){
			obj.dispose();
		} else if (unknown.equals(obj.okButton)){
			delete (frame.getContentPane(),
				delBuffer.getComponents());
			delBuffer.setVisible(false);
			wallets.remove(delBuffer);
			income.remove(delBuffer);
			expense.remove(delBuffer);
			frame.repaint();
			obj.dispose();
		}
	}
	private void check(Object unknown, DialogFrame obj){
		if (obj == null)
			return;
		if (unknown.equals(obj.cancelButton)){
			obj.dispose();
		} else if (unknown.equals(obj.okButton)){
			try {
				obj.validateFields();
				fill(buffer, obj);
				obj.dispose();
				frame.repaint();
			} catch (IllegalArgumentException ex){
				obj.labelException.setText(ex.getMessage());
				obj.labelException.setVisible(true);
			}
		}
	}
	
	private void check(Object unknown){
		if (unknown.equals(addExpense)){
			newComponentDialog(ST_NEW_EX, exColor);
		} else if (unknown.equals(addIncome)){
			newComponentDialog(ST_NEW_IN, inColor);
		} else if (unknown.equals(addWallet)){
			newComponentDialog(ST_NEW_WA, waColor);
		}
	}
	
	private void newComponentDialog(int statement, Color color){
		this.statement = statement;
		buffer = create(new WalletImpl(-1, NO_NAME , new RubleUnit()), 
				color, categoryRect, categoryOrientation);
		openDialog(buffer);
	}
	

	private void delete(Container content, List<Component> components){
		for (Component component: components){
			content.remove(component);
		}
	}
}
