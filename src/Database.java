import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		// CreateDB();
		// WriteDB();
		// DelIncome(8);
		// Integer in = AddIncome("������");
		// System.out.println(in + " �����");
		// AddExpenseStory(4,1,70);
		// AddIncomeStory(1,1,30);
		// ReadDB();
		// GetListWallet();
		GetListExpenseHistory(3);
	}

	// ���������� ��������
	public static Integer AddWallet(String name, Integer amount) {
		Integer idWallet = -1;
		try {
			Conn();
			resSet = statmt.executeQuery("SELECT id FROM wallet WHERE name='"
					+ name + "'");
			if (!resSet.next()) {
				statmt.execute("INSERT INTO 'wallet' ('name', 'amount') VALUES ('"
						+ name + "', " + amount + "); ");
				resSet = statmt
						.executeQuery("SELECT id FROM wallet WHERE name='"
								+ name + "'");
				resSet.next();
				idWallet = resSet.getInt("id");
				resSet.close();
				CloseDB();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idWallet;

	}

	// �������� ��������
	public static void DelWallet(Integer idWallet) {
		try {
			Conn();
			statmt.execute("DELETE FROM wallet WHERE id=" + idWallet);
			CloseDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ���������� ��������� �������
	public static Integer AddIncome(String name, Integer amount) {
		Integer idIncome = -1;
		try {
			Conn();
			resSet = statmt.executeQuery("SELECT id FROM income WHERE name='"
					+ name + "'");
			if (!resSet.next()) {
				statmt.execute("INSERT INTO 'income' ('name') VALUES ('" + name
						+ "', " + amount + "); ");
				resSet = statmt
						.executeQuery("SELECT id FROM income WHERE name='"
								+ name + "'");
				resSet.next();
				idIncome = resSet.getInt("id");
				resSet.close();
				CloseDB();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idIncome;
	}

	// �������� ��������� �������
	public static void DelIncome(Integer idIncome) {
		try {
			Conn();
			statmt.execute("DELETE FROM income WHERE id=" + idIncome);
			CloseDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ���������� ��������� ��������
	public static Integer AddExpense(String name, Integer amount) {
		Integer idExpense = -1;
		try {
			Conn();
			resSet = statmt.executeQuery("SELECT id FROM expense WHERE name='"
					+ name + "'");
			if (!resSet.next()) {
				statmt.execute("INSERT INTO 'expense' ('name') VALUES ('"
						+ name + "', " + amount + "); ");
				resSet = statmt
						.executeQuery("SELECT id FROM expense WHERE name='"
								+ name + "'");
				resSet.next();
				idExpense = resSet.getInt("id");
				resSet.close();
				CloseDB();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idExpense;
	}

	// �������� ��������� ��������
	public static void DelExpense(Integer idExpense) {
		try {
			Conn();
			statmt.execute("DELETE FROM expense WHERE id=" + idExpense);
			CloseDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ���������� ���������� ������
	public static void AddIncomeStory(Integer idIncome, Integer idWallet,
			Integer many) {
		try {
			Conn();
			statmt.execute("INSERT INTO 'incomeStory' ('id_income', 'id_wallet', 'dateIncome', 'many') VALUES ("
					+ idIncome
					+ ", "
					+ idWallet
					+ ", date('now'),"
					+ many
					+ "); ");

			statmt.execute("UPDATE income SET amount = amount+'" + many
					+ "' WHERE id=" + idIncome + "; ");
			statmt.execute("UPDATE wallet SET amount = amount+'" + many
					+ "' WHERE id=" + idWallet + "; ");
			CloseDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ���������� ���������� �������
	public static void AddExpenseStory(Integer idExpense, Integer idWallet,
			Integer many) {
		try {
			Conn();
			statmt.execute("INSERT INTO 'expenseStory' ('id_expense', 'id_wallet', 'dateExpense', 'many') VALUES ("
					+ idExpense
					+ ", "
					+ idWallet
					+ ", date('now'),"
					+ many
					+ "); ");

			statmt.execute("UPDATE expense SET amount = amount+'" + many
					+ "' WHERE id=" + idExpense + "; ");
			statmt.execute("UPDATE wallet SET amount = amount-'" + many
					+ "' WHERE id=" + idWallet + "; ");
			CloseDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ����� ������ ���������
	public static List<WalletImpl> GetListWallet() {
		List<WalletImpl> list = new ArrayList<WalletImpl>();
		try {
			Conn();
			resSet = statmt.executeQuery("SELECT * FROM wallet");
			while (resSet.next()) {
				int id = resSet.getInt("id");
				String name = resSet.getString("name");
				int amount = resSet.getInt("amount");
				Monetary ruble = new RubleUnit(amount);
				WalletImpl wallet = new WalletImpl(id, name, ruble);
				list.add(wallet);
			}
			CloseDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// ����� ������ �������
	public static List<WalletImpl> GetListIncome() {
		List<WalletImpl> list = new ArrayList<WalletImpl>();
		try {
			Conn();
			resSet = statmt.executeQuery("SELECT * FROM income");
			while (resSet.next()) {
				int id = resSet.getInt("id");
				String name = resSet.getString("name");
				int amount = resSet.getInt("amount");
				Monetary ruble = new RubleUnit(amount);
				WalletImpl wallet = new WalletImpl(id, name, ruble);
				list.add(wallet);
			}
			CloseDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// ����� ������ ��������
	public static List<WalletImpl> GetListExpense() {
		List<WalletImpl> list = new ArrayList<WalletImpl>();
		try {
			Conn();
			resSet = statmt.executeQuery("SELECT * FROM expense");
			while (resSet.next()) {
				int id = resSet.getInt("id");
				String name = resSet.getString("name");
				int amount = resSet.getInt("amount");
				Monetary ruble = new RubleUnit(amount);
				WalletImpl wallet = new WalletImpl(id, name, ruble);
				list.add(wallet);
			}
			CloseDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * ������ �������� �� ������ 0-�� ���� ������;
	 * 1 - �� ������� �����;
	 * 2 - �� ������� �������;
	 * 3 - �� ������� ���;
	 * 4 - �� ��������� 2 ����;
	 */
	public static List<WalletImpl> GetListExpenseHistory(int p) {
		String query = "";
		switch (p) {
		case 0:
			query = "SELECT expenseStory.id as id, name, many FROM expenseStory inner join expense on expenseStory.id_expense=expense.id";
			break;
		case 1:
			query = "SELECT expenseStory.id as id, name, many FROM expenseStory inner join expense on expenseStory.id_expense=expense.id WHERE strftime('%m', dateExpense) = strftime('%m', date('now')) and strftime('%y', dateExpense) = strftime('%y', date('now'));";
			break;
		case 2:
			query = "SELECT expenseStory.id as id, name, many FROM expenseStory inner join expense on expenseStory.id_expense=expense.id WHERE strftime('%m', dateExpense) = strftime('%m', date('now')) and strftime('%y', dateExpense) = strftime('%y', date('now'));";
			break;
		case 3:
			query = "SELECT expenseStory.id as id, name, many FROM expenseStory inner join expense on expenseStory.id_expense=expense.id WHERE strftime('%y', dateExpense) < strftime('%y', date('now'));";
			break;
		case 4:
			query = "SELECT expenseStory.id as id, name, many FROM expenseStory inner join expense on expenseStory.id_expense=expense.id WHERE strftime('%y', dateExpense) = strftime('%y', date('now')) OR strftime('%y', dateExpense) = strftime('%y', date('now'), '-1 month');";
			break;

		}
		List<WalletImpl> list = new ArrayList<WalletImpl>();
		try {
			Conn();
			resSet = statmt.executeQuery(query);
			while (resSet.next()) {
				int id = resSet.getInt("id");
				String name = resSet.getString("name");
				int many = resSet.getInt("many");
				Monetary ruble = new RubleUnit(many);
				WalletImpl wallet = new WalletImpl(id, name, ruble);
				list.add(wallet);
				System.out.println("id= "+id);
			}
			CloseDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// -------- ����� ������--------
	public static void ReadDB() throws ClassNotFoundException, SQLException {
		Conn();
		resSet = statmt.executeQuery("SELECT * FROM incomeStory");
		while (resSet.next()) {
			int id = resSet.getInt("id");
			int idIncome = resSet.getInt("id_income");
			int idWallet = resSet.getInt("id_wallet");
			String date = resSet.getString("dateIncome");
			int many = resSet.getInt("many");
			System.out.println("ID = " + id);
			System.out.println("idIncome = " + idIncome);
			System.out.println("idWallet = " + idWallet);
			System.out.println("many = " + date);
			System.out.println("many = " + many);
			System.out.println();
		}

		resSet = statmt.executeQuery("SELECT * FROM expenseStory");

		while (resSet.next()) {
			int id = resSet.getInt("id");
			int idExpense = resSet.getInt("id_expense");
			int idWallet = resSet.getInt("id_wallet");
			String date = resSet.getString("dateExpense");
			int many = resSet.getInt("many");
			System.out.println("ID = " + id);
			System.out.println("idExpense = " + idExpense);
			System.out.println("idWallet = " + idWallet);
			System.out.println("many = " + date);
			System.out.println("many = " + many);
			System.out.println();
		}

		resSet = statmt.executeQuery("SELECT * FROM expense");
		while (resSet.next()) {
			int id = resSet.getInt("id");
			String name = resSet.getString("name");
			System.out.println("ID = " + id);
			System.out.println("name = " + name);
			System.out.println();
		}

		resSet = statmt.executeQuery("SELECT * FROM income");
		while (resSet.next()) {
			int id = resSet.getInt("id");
			String name = resSet.getString("name");
			System.out.println("ID = " + id);
			System.out.println("name = " + name);
			System.out.println();
		}

		resSet = statmt.executeQuery("SELECT * FROM wallet");
		while (resSet.next()) {
			int id = resSet.getInt("id");
			String name = resSet.getString("name");
			System.out.println("ID = " + id);
			System.out.println("name = " + name);
			System.out.println();
		}
		resSet.close();
		CloseDB();
	}

	// --------����������� � ���� ������--------
	public static void Conn() throws ClassNotFoundException, SQLException {

		conn = null;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:sqlite.db3");
		statmt = conn.createStatement();
	}

	// --------��������--------
	public static void CloseDB() throws ClassNotFoundException, SQLException {
		statmt.close();
		conn.close();
	}

	// --------�������� ������--------
	public static void CreateDB() throws ClassNotFoundException, SQLException {
		Conn();

		statmt.execute("CREATE TABLE if not exists 'income' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'amount' INTEGER);");
		statmt.execute("CREATE TABLE if not exists 'expense' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'amount' INTEGER);");
		statmt.execute("CREATE TABLE if not exists 'wallet' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'amount' INTEGER);");
		statmt.execute("CREATE TABLE if not exists 'incomeStory' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'id_income' INTEGER, 'id_wallet' INTEGER, 'dateIncome' date, 'many' INTEGER);");
		statmt.execute("CREATE TABLE if not exists 'expenseStory' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'id_expense' INTEGER, 'id_wallet' INTEGER, 'dateExpense' date, 'many' INTEGER);");
		CloseDB();
	}

	// --------���������� ������--------
	public static void WriteDB() throws ClassNotFoundException, SQLException {
		Conn();
		statmt.execute("INSERT INTO 'income' ('name', 'amount') VALUES ('��������', 0); ");
		statmt.execute("INSERT INTO 'income' ('name', 'amount') VALUES ('������', 0); ");
		statmt.execute("INSERT INTO 'income' ('name', 'amount') VALUES ('���������', 0); ");
		statmt.execute("INSERT INTO 'income' ('name', 'amount') VALUES ('�������', 0); ");
		statmt.execute("INSERT INTO 'expense' ('name', 'amount') VALUES ('�����������', 0); ");
		statmt.execute("INSERT INTO 'expense' ('name', 'amount') VALUES ('�������', 0); ");
		statmt.execute("INSERT INTO 'expense' ('name', 'amount') VALUES ('��������', 0); ");
		statmt.execute("INSERT INTO 'expense' ('name', 'amount') VALUES ('���������', 0); ");
		statmt.execute("INSERT INTO 'wallet' ('name', 'amount') VALUES ('��������', 1100); ");
		statmt.execute("INSERT INTO 'wallet' ('name', 'amount') VALUES ('����� VISA', 100); ");
		statmt.execute("INSERT INTO 'wallet' ('name', 'amount') VALUES ('������ ������', 0); ");
		statmt.execute("INSERT INTO 'wallet' ('name', 'amount') VALUES ('PayPal', 30); ");
		CloseDB();
	}

}
