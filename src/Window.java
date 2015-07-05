import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import javax.swing.border.BevelBorder;
import javax.swing.JToggleButton;


public class Window implements ActionListener{

	private JFrame frame;
	private GraphPanel gistPanel;
	private JToggleButton buttonYear;
	private JToggleButton buttonMonth;
	private JToggleButton buttonWeek;
	
	private JMenuBar menuBar;
	private JMenu menuControl;
	private JMenu menuHelp;
	private JMenuItem menuItemTransactionHistory;
	private JMenuItem menuItemExcelExport;
	private JMenuItem menuItemHelpContent;
	private JMenuItem menuItemAbout;
	private DiagramPanel diagPanel;
	
	ViewController vc;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
					
					window.frame.setIconImage(new ImageIcon("rub.jpg").getImage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initializeViewController(){
		List<WalletImpl> income = Arrays.
				asList(
						new WalletImpl(0, "Çàðïëàòà", new RubleUnit()),
						new WalletImpl(0, "Àêòèâû", new RubleUnit()),
						new WalletImpl(0, "Äèâèäåíäû", new RubleUnit()),
						new WalletImpl(0, "Ôðèëàíñ", new RubleUnit()));
						new WalletImpl(0, "Ôðèëàíñ", new RubleUnit()),
						new WalletImpl(0, "Äèâèäåíäû", new RubleUnit()));

		List<WalletImpl> expense = Arrays.
				asList(
						new WalletImpl(0, "ÆÊÕ", new RubleUnit()),
						new WalletImpl(0, "Ïðîäóêòû", new RubleUnit()),
						new WalletImpl(0, "Òðàíñïîðò", new RubleUnit()),
						new WalletImpl(0, "Îáðàçîâàíèå", new RubleUnit()),
						new WalletImpl(0, "Ðàçâëå÷åíèÿ", new RubleUnit()));
		
		List<WalletImpl> wallets = Arrays.
				asList(
						new WalletImpl(0, "Íàëè÷íûå", new RubleUnit()),
						new WalletImpl(0, "Visa", new RubleUnit()),
						new WalletImpl(0, "PayPal", new RubleUnit()),
						new WalletImpl(0, "ßíäåêñ.Äåíüãè", new RubleUnit()));
		
		vc = new ViewController(frame);
		vc.addExpense(expense);
		vc.addIncome(income);
		vc.addWallets(wallets);
		
		menuItemExcelExport = new JMenuItem("Ýêñïîðò â Excel");
		ExcelExporter al = new ExcelExporter(vc);
		menuItemExcelExport.addActionListener(al);
		menuControl.add(menuItemExcelExport);
	}
	
	public Window() {
		initialize();
		initializeViewController();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 899, 505);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		gistPanel = new GraphPanel();
		gistPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		gistPanel.setBounds(545, 128, 328, 163);
		frame.getContentPane().add(gistPanel);
		
		diagPanel = new DiagramPanel();
		diagPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		diagPanel.setBounds(369, 128, 166, 166);
		diagPanel.setValues(new Integer[]{750,175,150,50,125,100,100,50});
		frame.getContentPane().add(diagPanel);
		
		
		buttonYear = new JToggleButton("\u0413\u043E\u0434");
		buttonYear.setBounds(545, 302, 100, 23);
		buttonYear.addActionListener(this);
		frame.getContentPane().add(buttonYear);
		
		buttonMonth = new JToggleButton("\u041C\u0435\u0441\u044F\u0446");
		buttonMonth.setBounds(655, 302, 108, 23);
		buttonMonth.addActionListener(this);
		buttonMonth.setSelected(true);
		frame.getContentPane().add(buttonMonth);
		
		buttonWeek = new JToggleButton("\u041D\u0435\u0434\u0435\u043B\u044F");
		buttonWeek.setBounds(773, 302, 100, 23);
		buttonWeek.addActionListener(this);
		frame.getContentPane().add(buttonWeek);
		
		
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 920, 21);
		frame.getContentPane().add(menuBar);
		
		menuControl = new JMenu("\u0423\u043F\u0440\u0430\u0432\u043B\u0435\u043D\u0438\u0435");
		menuBar.add(menuControl);
		
		menuItemTransactionHistory = new JMenuItem("\u0418\u0441\u0442\u043E\u0440\u0438\u044F \u0442\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u0439");
		menuControl.add(menuItemTransactionHistory);

		menuHelp = new JMenu("\u041F\u043E\u043C\u043E\u0449\u044C");
		menuBar.add(menuHelp);
		
		menuItemHelpContent = new JMenuItem("\u0420\u0443\u043A\u043E\u0432\u043E\u0434\u0441\u0442\u0432\u043E");
		menuHelp.add(menuItemHelpContent);
		
		menuItemAbout = new JMenuItem("\u041E \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u0435");
		menuHelp.add(menuItemAbout);
		

	}

	private <T, V extends ActionEvent> void offOtherJToggleButtons(T object, V besides){
		
		if (object instanceof JToggleButton){
			AbstractButton button = (AbstractButton) object;
			String buttonName = button.getText();
			String instanceName = besides.getActionCommand();
			
			button.setSelected(buttonName.equals(instanceName));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gistPanel.change(e);
		diagPanel.change(e);
		
		Field[] fields = this.getClass()
								.getDeclaredFields();
		
		for (Field field : fields){
			try {
				Object obj = field.get(this);
				this.offOtherJToggleButtons(obj, e);
			} catch (IllegalArgumentException | IllegalAccessException ex) {
				ex.printStackTrace();
			}
		}
	}
}
