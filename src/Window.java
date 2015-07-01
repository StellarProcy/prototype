import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import java.awt.Color;
import java.lang.reflect.Field;
import javax.swing.border.BevelBorder;
import javax.swing.JToggleButton;


public class Window implements ActionListener{

	private JFrame frame;
	private GraphPanel panel;
	
	private IconButton buttonVisa;
	private IconButton buttonMasterCard;
	private IconButton buttonYandexMoney;
	private IconButton buttonPayPal;
	private JButton buttonAdd;
	
	private JToggleButton buttonYear;
	private JToggleButton buttonMonth;
	private JToggleButton buttonWeek;
	
	private JMenuItem menuItemSalary;
	private JMenuItem menuItemPrize;
	private JMenuItem menuItemReDept;
	private JMenuItem menuItemInheritance;
	private JMenuItem menuItemAddGain;

	private JMenuItem menuItemFlat;
	private JMenuItem menuItemProducts;
	private JMenuItem menuItemTransport;
	private JMenuItem menuItemEntertainment;
	private JMenuItem menuItemClub;
	private JMenuItem menuItemPurchases;
	private JMenuItem menuItemTravel;
	private JMenuItem menuItemAddLoss;
	
	private JMenuBar menuBar;
	private JMenu menuControl;
	private JMenu menuHelp;
	private JMenuItem menuItemTransactionHistory;
	private JMenuItem menuItemHelpContent;
	private JMenuItem menuItemAbout;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Window() {
		initialize();
	}
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 899, 505);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		menuItemSalary = new JMenuItem("\u0417\u0430\u0440\u043F\u043B\u0430\u0442\u0430");
		menuItemSalary.setBackground(new Color(50, 205, 50));
		menuItemSalary.setForeground(Color.BLACK);
		menuItemSalary.setBounds(10, 84, 129, 22);
		frame.getContentPane().add(menuItemSalary);
		
		menuItemPrize = new JMenuItem("\u041F\u0440\u0435\u043C\u0438\u044F");
		menuItemPrize.setForeground(Color.BLACK);
		menuItemPrize.setBackground(new Color(50, 205, 50));
		menuItemPrize.setBounds(10, 117, 129, 22);
		frame.getContentPane().add(menuItemPrize);
		
		menuItemInheritance = new JMenuItem("\u041D\u0430\u0441\u043B\u0435\u0434\u0441\u0442\u0432\u043E");
		menuItemInheritance.setForeground(Color.BLACK);
		menuItemInheritance.setBackground(new Color(50, 205, 50));
		menuItemInheritance.setBounds(10, 150, 129, 22);
		frame.getContentPane().add(menuItemInheritance);
		
		menuItemReDept = new JMenuItem("\u0412\u043E\u0437\u0432\u0440\u0430\u0442 \u0434\u043E\u043B\u0433\u0430");
		menuItemReDept.setForeground(Color.BLACK);
		menuItemReDept.setBackground(new Color(50, 205, 50));
		menuItemReDept.setBounds(10, 183, 129, 22);
		frame.getContentPane().add(menuItemReDept);
		
		buttonVisa = new IconButton("visa.bmp");
		buttonVisa.setBounds(782, 84, 70, 52);
		buttonVisa.paint();
		frame.getContentPane().add(buttonVisa);
		
		buttonMasterCard = new IconButton("mastercard.bmp");
		buttonMasterCard.setBounds(782, 142, 70, 52);
		buttonMasterCard.paint();
		frame.getContentPane().add(buttonMasterCard);
		
		buttonYandexMoney = new IconButton("yandexmoney.bmp");
		buttonYandexMoney.setBounds(782, 200, 70, 52);
		buttonYandexMoney.paint();
		frame.getContentPane().add(buttonYandexMoney);
		
		buttonPayPal = new IconButton("paypal.bmp");
		buttonPayPal.setBounds(782, 258, 70, 52);
		buttonPayPal.paint();
		frame.getContentPane().add(buttonPayPal);
		
		panel = new GraphPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(444, 84, 328, 206);
		frame.getContentPane().add(panel);
		
		menuItemAddGain = new JMenuItem("+");
		menuItemAddGain.setForeground(new Color(255, 255, 255));
		menuItemAddGain.setBackground(new Color(50, 205, 50));
		menuItemAddGain.setBounds(10, 216, 129, 22);
		frame.getContentPane().add(menuItemAddGain);
		
		buttonAdd = new JButton("new wallet");
		buttonAdd.setToolTipText("");
		buttonAdd.setBounds(782, 321, 70, 52);
		frame.getContentPane().add(buttonAdd);
		
		buttonYear = new JToggleButton("\u0413\u043E\u0434");
		buttonYear.setBounds(444, 301, 100, 23);
		buttonYear.addActionListener(this);
		frame.getContentPane().add(buttonYear);
		
		buttonMonth = new JToggleButton("\u041C\u0435\u0441\u044F\u0446");
		buttonMonth.setBounds(554, 301, 108, 23);
		buttonMonth.addActionListener(this);
		buttonMonth.setSelected(true);
		frame.getContentPane().add(buttonMonth);
		
		buttonWeek = new JToggleButton("\u041D\u0435\u0434\u0435\u043B\u044F");
		buttonWeek.setBounds(672, 301, 100, 23);
		buttonWeek.addActionListener(this);
		frame.getContentPane().add(buttonWeek);
		
		menuItemFlat = new JMenuItem("\u041A\u0432\u0430\u0440\u0442\u0438\u0440\u0430");
		menuItemFlat.setBackground(new Color(255, 0, 0));
		menuItemFlat.setBounds(149, 84, 129, 22);
		frame.getContentPane().add(menuItemFlat);
		
		menuItemProducts = new JMenuItem("\u041F\u0440\u043E\u0434\u0443\u043A\u0442\u044B");
		menuItemProducts.setBackground(new Color(255, 0, 0));
		menuItemProducts.setBounds(149, 117, 129, 22);
		frame.getContentPane().add(menuItemProducts);
		
		menuItemTransport = new JMenuItem("\u0422\u0440\u0430\u043D\u0441\u043F\u043E\u0440\u0442");
		menuItemTransport.setBackground(new Color(255, 0, 0));
		menuItemTransport.setBounds(149, 150, 129, 22);
		frame.getContentPane().add(menuItemTransport);
		
		menuItemEntertainment = new JMenuItem("\u0420\u0430\u0437\u0432\u043B\u0435\u0447\u0435\u043D\u0438\u044F");
		menuItemEntertainment.setBackground(new Color(255, 0, 0));
		menuItemEntertainment.setBounds(149, 183, 129, 22);
		frame.getContentPane().add(menuItemEntertainment);
		
		menuItemClub = new JMenuItem("\u0417\u0430\u0432\u0435\u0434\u0435\u043D\u0438\u044F");
		menuItemClub.setBackground(new Color(255, 0, 0));
		menuItemClub.setBounds(149, 216, 129, 22);
		frame.getContentPane().add(menuItemClub);
		
		menuItemPurchases = new JMenuItem("\u041F\u043E\u043A\u0443\u043F\u043A\u0438 \u0438 \u0443\u0441\u043B\u0443\u0433\u0438");
		menuItemPurchases.setBackground(new Color(255, 0, 0));
		menuItemPurchases.setBounds(149, 252, 129, 22);
		frame.getContentPane().add(menuItemPurchases);
		
		menuItemTravel = new JMenuItem("\u041F\u0443\u0442\u0435\u0448\u0435\u0441\u0442\u0432\u0438\u0435");
		menuItemTravel.setBackground(new Color(255, 0, 0));
		menuItemTravel.setBounds(149, 285, 129, 22);
		frame.getContentPane().add(menuItemTravel);
		
		menuItemAddLoss = new JMenuItem("+");
		menuItemAddLoss.setForeground(new Color(255, 255, 255));
		menuItemAddLoss.setBackground(new Color(255, 0, 0));
		menuItemAddLoss.setBounds(149, 321, 129, 22);
		frame.getContentPane().add(menuItemAddLoss);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 883, 21);
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
		panel.render(e);
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
