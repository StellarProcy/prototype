import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NewTransactionFrame extends JFrame {
	private JButton addTransaction;
	private JButton removeTransaction;

	public static void main(String[] args) {
		NewTransactionFrame app = new NewTransactionFrame();
		app.setVisible(true);
		app.pack(); // Оптимальный размер в зависимости от содержимого окна
	}
}
