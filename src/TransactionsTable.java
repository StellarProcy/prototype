import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TransactionsTable extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static int i = 0;

	ViewController vc;

	public TransactionsTable(ViewController vc) {
		super("История транзакций");
		this.vc = vc;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//JOptionPane.showMessageDialog(null, "Selected Item: " + arg0.getActionCommand());

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		List<Transaction> transactions = vc.getTransactions();

		ArrayList<MyBean> beans = new ArrayList<MyBean>();
		
		beans.add(new MyBean("Кошелёк", "Категория", "Дата", "Сумма"));
		for (Transaction tr : transactions) {
			String wallet = String.valueOf(tr.getWalletID());            // TODO добавить получение имени кошелька
			String category = String.valueOf(tr.getCategoryID());;       // TODO добавить получение имени категории
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
			String date = format.format(tr.getDate());
			String amount = tr.getAmount().toString();
			beans.add(new MyBean(wallet, category, date, amount));
		}

		TableModel model = new MyTableModel(beans);
		JTable table = new JTable(model);

		getContentPane().add(new JScrollPane(table));

		setPreferredSize(new Dimension(360, 220));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				new TransactionsTable(new ViewController(new JFrame()));
			}
		});
	}

	public class MyTableModel implements TableModel {

		private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

		private List<MyBean> beans;

		public MyTableModel(List<MyBean> beans) {
			this.beans = beans;
		}

		public void addTableModelListener(TableModelListener listener) {
			listeners.add(listener);
		}

		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}

		public int getColumnCount() {
			return 4;
		}

		public String getColumnid_transaction(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return "Кошелёк";
			case 1:
				return "Категория";
			case 2:
				return "Дата";
			case 3:
				return "Сумма";
			}
			return "";
		}

		public int getRowCount() {
			return beans.size();
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			MyBean bean = beans.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return bean.getWallet();
			case 1:
				return bean.getCategory();
			case 2:
				return bean.getDate();
			case 3:
				return bean.getAmount();
			}
			return "";
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public void removeTableModelListener(TableModelListener listener) {
			listeners.remove(listener);
		}

		public void setValueAt(Object value, int rowIndex, int columnIndex) {

		}

		@Override
		public String getColumnName(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class MyBean {
		
		private String wallet;
		private String category;
		private String date;
		private String amount;

		public MyBean(String wallet, String category, String date, String amount) {
			this.setWallet(wallet);
			this.setCategory(category);
			this.setDate(date);
			this.setAmount(amount);
		}

		public String getWallet() {
			return wallet;
		}

		public void setWallet(String wallet) {
			this.wallet = wallet;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}
	}
}
