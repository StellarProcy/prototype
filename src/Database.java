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
		 //CreateDB();
		 //WriteDB();
		// DelIncome(8);
		// Integer in = AddIncome("Бензин");
		// System.out.println(in + " ответ");
		//AddExpenseStory(1,1,20);
		//AddIncomeStory(1,1,30);
		//ReadDB();
		// GetListWallet();
	}


	// добавление кошелька
	public static Integer AddWallet(String name, Integer amount)
			throws ClassNotFoundException, SQLException {
		Conn();
		resSet = statmt.executeQuery("SELECT id FROM wallet WHERE name='"
				+ name + "'");
		if (!resSet.next()) {
			statmt.execute("INSERT INTO 'wallet' ('name', 'amount') VALUES ('"
					+ name + "', " + amount + "); ");
			resSet = statmt.executeQuery("SELECT id FROM wallet WHERE name='"
					+ name + "'");
			resSet.next();
			Integer idWallet = resSet.getInt("id");
			resSet.close();
			CloseDB();
			return idWallet;
		} else {
			return -1;
		}
	}

	// удаление кошелька
	public static void DelWallet(Integer idWallet)
			throws ClassNotFoundException, SQLException {
		Conn();
		statmt.execute("DELETE FROM wallet WHERE id=" + idWallet);
		CloseDB();
	}

	// добавление категории доходов
	public static Integer AddIncome(String name, Integer amount)
			throws ClassNotFoundException, SQLException {
		Conn();
		resSet = statmt.executeQuery("SELECT id FROM income WHERE name='"
				+ name + "'");
		if (!resSet.next()) {
			statmt.execute("INSERT INTO 'income' ('name') VALUES ('" + name
					+ "', " + amount + "); ");
			resSet = statmt.executeQuery("SELECT id FROM income WHERE name='"
					+ name + "'");
			resSet.next();
			Integer idIncome = resSet.getInt("id");
			resSet.close();
			CloseDB();
			return idIncome;
		} else {
			return -1;
		}
	}

	// удаление категории доходов
	public static void DelIncome(Integer idIncome)
			throws ClassNotFoundException, SQLException {
		Conn();
		statmt.execute("DELETE FROM income WHERE id=" + idIncome);
		CloseDB();
	}

	// добавление категории расходов
	public static Integer AddExpense(String name, Integer amount)
			throws ClassNotFoundException, SQLException {
		Conn();
		resSet = statmt.executeQuery("SELECT id FROM expense WHERE name='"
				+ name + "'");
		if (!resSet.next()) {
			statmt.execute("INSERT INTO 'expense' ('name') VALUES ('" + name
					+ "', " + amount + "); ");
			resSet = statmt.executeQuery("SELECT id FROM expense WHERE name='"
					+ name + "'");
			resSet.next();
			Integer idExpense = resSet.getInt("id");
			resSet.close();
			CloseDB();
			return idExpense;
		} else {
			return -1;
		}
	}

	// удаление категории расходов
	public static void DelExpense(Integer idExpense)
			throws ClassNotFoundException, SQLException {
		Conn();
		statmt.execute("DELETE FROM expense WHERE id=" + idExpense);
		CloseDB();
	}

	// добавление транзакции дохода
	public static void AddIncomeStory(Integer idIncome, Integer idWallet,
			Integer many) throws ClassNotFoundException, SQLException {
		Conn();
		statmt.execute("INSERT INTO 'incomeStory' ('id_income', 'id_wallet', 'dateIncome', 'many') VALUES ("
				+ idIncome + ", " + idWallet + ", date('now')," + many + "); ");
		
		statmt.execute("UPDATE income SET amount = amount+'"+many+"' WHERE id="+idIncome+"; ");
		statmt.execute("UPDATE wallet SET amount = amount+'"+many+"' WHERE id="+idWallet+"; ");
		
		CloseDB();
	}

	// добавление транзакции расхода
	public static void AddExpenseStory(Integer idExpense, Integer idWallet,
			Integer many) throws ClassNotFoundException, SQLException {
		Conn();
		statmt.execute("INSERT INTO 'expenseStory' ('id_expense', 'id_wallet', 'dateExpense', 'many') VALUES ("
				+ idExpense + ", " + idWallet + ", date('now')," + many + "); ");
		
		statmt.execute("UPDATE expense SET amount = amount+'"+many+"' WHERE id="+idExpense+"; ");
		statmt.execute("UPDATE wallet SET amount = amount-'"+many+"' WHERE id="+idWallet+"; ");
		
		CloseDB();
	}

	// вывод списка кошельков
	public static List<WalletImpl> GetListWallet()
			throws ClassNotFoundException, SQLException {
		List<WalletImpl> list = new ArrayList<WalletImpl>();

		Conn();
		resSet = statmt.executeQuery("SELECT * FROM wallet");
		while (resSet.next()) {
			int id = resSet.getInt("id");
			String name = resSet.getString("name");
			int amount = resSet.getInt("amount");
			WalletImpl wallet = new WalletImpl();
			Monetary ruble = new RubleUnit(amount);
			wallet.setID((long) id);
			wallet.setName(name);
			wallet.setAmount(ruble);
			list.add(wallet);
		}
		return list;
	}

	// вывод списка доходов
	public static List<WalletImpl> GetListIncome()
			throws ClassNotFoundException, SQLException {
		List<WalletImpl> list = new ArrayList<WalletImpl>();

		Conn();
		resSet = statmt.executeQuery("SELECT * FROM income");
		while (resSet.next()) {
			int id = resSet.getInt("id");
			String name = resSet.getString("name");
			int amount = resSet.getInt("amount");
			WalletImpl wallet = new WalletImpl();
			Monetary ruble = new RubleUnit(amount);
			wallet.setID((long) id);
			wallet.setName(name);
			wallet.setAmount(ruble);
			list.add(wallet);
		}
		return list;
	}

	// вывод списка расходов
	public static List<WalletImpl> GetListExpense()
			throws ClassNotFoundException, SQLException {
		List<WalletImpl> list = new ArrayList<WalletImpl>();

		Conn();
		resSet = statmt.executeQuery("SELECT * FROM expense");
		while (resSet.next()) {
			int id = resSet.getInt("id");
			String name = resSet.getString("name");
			int amount = resSet.getInt("amount");
			WalletImpl wallet = new WalletImpl();
			Monetary ruble = new RubleUnit(amount);
			wallet.setID((long) id);
			wallet.setName(name);
			wallet.setAmount(ruble);
			list.add(wallet);
		}
		return list;
	}

	// -------- Вывод таблиц--------
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

	// --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
	public static void Conn() throws ClassNotFoundException, SQLException {

		conn = null;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:sqlite.db3");
		statmt = conn.createStatement();
	}

	// --------Закрытие--------
	public static void CloseDB() throws ClassNotFoundException, SQLException {
		statmt.close();
		conn.close();
	}

	// --------Создание таблиц--------
	public static void CreateDB() throws ClassNotFoundException, SQLException {
		Conn();

		statmt.execute("CREATE TABLE if not exists 'income' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'amount' INTEGER);");
		statmt.execute("CREATE TABLE if not exists 'expense' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'amount' INTEGER);");
		statmt.execute("CREATE TABLE if not exists 'wallet' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'amount' INTEGER);");
		statmt.execute("CREATE TABLE if not exists 'incomeStory' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'id_income' INTEGER, 'id_wallet' INTEGER, 'dateIncome' date, 'many' INTEGER);");
		statmt.execute("CREATE TABLE if not exists 'expenseStory' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'id_expense' INTEGER, 'id_wallet' INTEGER, 'dateExpense' date, 'many' INTEGER);");
		CloseDB();
	}

	// --------Заполнение таблиц--------
	public static void WriteDB() throws ClassNotFoundException, SQLException {
		Conn();
		statmt.execute("INSERT INTO 'income' ('name', 'amount') VALUES ('Зарплата', 0); ");
		statmt.execute("INSERT INTO 'income' ('name', 'amount') VALUES ('Активы', 0); ");
		statmt.execute("INSERT INTO 'income' ('name', 'amount') VALUES ('Дивиденты', 0); ");
		statmt.execute("INSERT INTO 'income' ('name', 'amount') VALUES ('Фриланс', 0); ");
		statmt.execute("INSERT INTO 'expense' ('name', 'amount') VALUES ('Развлечения', 0); ");
		statmt.execute("INSERT INTO 'expense' ('name', 'amount') VALUES ('Питание', 0); ");
		statmt.execute("INSERT INTO 'expense' ('name', 'amount') VALUES ('Квартира', 0); ");
		statmt.execute("INSERT INTO 'expense' ('name', 'amount') VALUES ('Транспорт', 0); ");
		statmt.execute("INSERT INTO 'wallet' ('name', 'amount') VALUES ('Бумажник', 1100); ");
		statmt.execute("INSERT INTO 'wallet' ('name', 'amount') VALUES ('Карта VISA', 100); ");
		statmt.execute("INSERT INTO 'wallet' ('name', 'amount') VALUES ('Яндекс деньги', 0); ");
		statmt.execute("INSERT INTO 'wallet' ('name', 'amount') VALUES ('PayPal', 30); ");
		CloseDB();
	}

}
