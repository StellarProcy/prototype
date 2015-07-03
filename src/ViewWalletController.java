import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;


public class ViewWalletController implements Renderable<Rectangle>, ActionListener{
	
	private JFrame frame;
	private JButton addWallet; 
	private List<ComponentManager> wallets;
	
	private final String defaultName = "��� ��������"; 
	private final String buttonName = "��������";
	
	private final int LEFT_BORDER = 10;
	private final int TOP_BORDER = 30;
	private final int FINAL_WIDTH = 80;
	private final int FINAL_HEIGHT = 60;
	private final int FINAL_RANGE = 20;
	
	private DialogFrame dialog;
	private ComponentManager buffer;
	
	ViewWalletController (JFrame frame, List<String> paths,  List<String> names){
		initialize(paths, names, frame);
		render();
	}
	
	@Override
	
	public void render(){
		int x = LEFT_BORDER;

		for (ComponentManager current : wallets){
			current.setBounds(x, TOP_BORDER, FINAL_WIDTH, FINAL_HEIGHT);
			x = x + FINAL_WIDTH + FINAL_RANGE;
			
		}
		addWallet.setBounds(x, TOP_BORDER, FINAL_WIDTH, FINAL_HEIGHT);
		frame.repaint();
	}
	
	@Override
	public Rectangle render(Object obj, Rectangle rect) {
		if (obj instanceof ComponentManager){
			rect = next(rect);
			((ComponentManager) obj).setBounds(rect);
		}
		return rect;
	}
	
	private void initialize(List<String> paths, List<String> names, JFrame frame){
		this.frame = frame;
		wallets = new ArrayList<ComponentManager>();
		addWallet = new JButton(buttonName);
		
		Iterator<String> nameIter = names.iterator();
		Rectangle rect = null;
		for (String path: paths){
			ComponentManager current = new ComponentManager();
			
			render(current, rect);
			current.setIcon(path);
		
			if (nameIter.hasNext()){
				current.setName(nameIter.next());
			} else current.setName(defaultName );
			
			current.close.addActionListener(this);
			current.edit.addActionListener(this);
			current.appendTo(frame.getContentPane());
			wallets.add(current);
		}
		
		frame.getContentPane().add(addWallet);
		frame.repaint();
	}
	

	private Rectangle next(Rectangle rect){
		if (rect == null){
			rect = new Rectangle (LEFT_BORDER, TOP_BORDER, FINAL_WIDTH, FINAL_HEIGHT);
		}
		else rect.x = rect.x + FINAL_WIDTH + FINAL_RANGE;
		return rect;
	}

	private void fill(ComponentManager unknown, DialogFrame frame){
		unknown.setName(frame.textName.getText());
		unknown.balance.set(Integer.parseInt(frame.textMonetary.getText()));
		unknown.field.setText(unknown.balance.toString());
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
			} catch (IllegalArgumentException ex){
				obj.labelException.setText(ex.getMessage());
				obj.labelException.setVisible(true);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object unknown = arg0.getSource();
		
		for (ComponentManager wallet: wallets){
			if (unknown.equals(wallet.close)){
				
				delete (frame.getContentPane(),
						wallet.getComponents());
				
				wallets.remove(wallet);
				break;
			} else if (unknown.equals(wallet.edit)){
				buffer = (ComponentManager) wallet;
				dialog = new DialogFrame(frame, true, wallet.getName(), wallet.getAmount(), false);
				dialog.cancelButton.addActionListener(this);
				dialog.okButton.addActionListener(this);
				dialog.setVisible(true);
				
			}
		}
		check(unknown, dialog);
		render();	
	}
	
	
	private void delete(Container content, List<Component> components){
		for (Component component: components){
			content.remove(component);
		}
	}




	
	
}
