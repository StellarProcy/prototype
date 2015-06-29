import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Button;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.awt.Canvas;
import java.awt.Panel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;


public class Window {

	private JFrame frame;
	private JMenuItem menuItemSalary;
	private JMenuItem menuItemPurchases;
	private JMenuItem menuItemPayments;
	private JMenuItem menuItemPrize;
	private IconButton buttonVisa;
	private IconButton buttonMasterCard;
	private IconButton buttonYandexMoney;
	private IconButton buttonPayPal;
	private JPanel panel;
	private JMenuItem menuItemAdd;
	private JButton buttonAdd;

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
	
	
	
	@SuppressWarnings("unused")
	private void addComponent(Component component, Container container, 
						      Rectangle rect, Color bgColor, String src){

		if (component instanceof JMenuItem == true){
			component = new JMenuItem(src);
		} else if (component instanceof IconButton){
			component = new IconButton(src);
		}
		
		component.setBounds(rect);
		component.setBackground(bgColor);
		container.add(component);
	}
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 899, 505);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		menuItemSalary = new JMenuItem("\u0417\u0430\u0440\u043F\u043B\u0430\u0442\u0430");
		menuItemSalary.setBackground(Color.LIGHT_GRAY);
		menuItemSalary.setForeground(Color.BLACK);
		menuItemSalary.setBounds(10, 120, 129, 22);
		frame.getContentPane().add(menuItemSalary);
		
		menuItemPurchases = new JMenuItem("\u041F\u043E\u043A\u0443\u043F\u043A\u0438");
		menuItemPurchases.setForeground(Color.BLACK);
		menuItemPurchases.setBackground(Color.LIGHT_GRAY);
		menuItemPurchases.setBounds(10, 153, 129, 22);
		frame.getContentPane().add(menuItemPurchases);
		
		menuItemPrize = new JMenuItem("\u041F\u0440\u0435\u043C\u0438\u044F");
		menuItemPrize.setForeground(Color.BLACK);
		menuItemPrize.setBackground(Color.LIGHT_GRAY);
		menuItemPrize.setBounds(10, 186, 129, 22);
		frame.getContentPane().add(menuItemPrize);
		
		menuItemPayments = new JMenuItem("\u041E\u043F\u043B\u0430\u0442\u0430 \u0441\u0447\u0435\u0442\u043E\u0432");
		menuItemPayments.setForeground(Color.BLACK);
		menuItemPayments.setBackground(Color.LIGHT_GRAY);
		menuItemPayments.setBounds(10, 219, 129, 22);
		frame.getContentPane().add(menuItemPayments);
		
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
		panel.setBounds(242, 104, 328, 206);
		frame.getContentPane().add(panel);
		
		menuItemAdd = new JMenuItem("+");
		menuItemAdd.setForeground(Color.BLUE);
		menuItemAdd.setBackground(Color.LIGHT_GRAY);
		menuItemAdd.setBounds(10, 252, 129, 22);
		frame.getContentPane().add(menuItemAdd);
		
		buttonAdd = new JButton("new wallet");
		buttonAdd.setToolTipText("");
		buttonAdd.setBounds(782, 321, 70, 52);
		frame.getContentPane().add(buttonAdd);

	}
}
