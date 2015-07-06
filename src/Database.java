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
		// Integer in = AddIncome("Бензин");
		// System.out.println(in + " ответ");
		// AddExpenseStory(2,1,50);
		// AddIncomeStory(1,1,30);
		// ReadDB();
		// GetListWallet();
		// GetListExpenseHistory(4);
		//WalletImpl impl = new WalletImpl(4,"Транспорт", new RubleUnit(100));
		//EditExpense(impl);
	}

	public static void rewriteDB(List<ComponentManager> wallets,
			List<ComponentManager> income, List<ComponentManager> expense) {
		try {
			Conn();
			List<WalletImpl> in = GetListIncome();
			List<WalletImpl> ex = GetListExpense();
			List<WalletImpl> wa = GetListWallet();

			if (in != null)
				for (WalletImpl wi : in)
					statmt.execute("DELETE FROM income WHERE id=" + wi.getID());
			if (ex != null)
				for (WalletImpl wi : ex)
					statmt.execute("DELETE FROM expense WHERE id=" + wi.getID());
			if (wa != null)
				for (WalletImpl wi : in)
					statmt.execute("DELETE FROM wallet WHERE id=" + wi.getID());
			CloseDB();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// добавление кошелька
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

	// удаление кошелька
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

	// редактирование кошелька
	public static int EditWallet(WalletImpl impl) {
		Integer id = impl.getID();
		String name = impl.getName();
		Integer many = impl.getAmount().get();
		try {
			Conn();
			resSet = statmt.executeQuery("SELECT id FROM wallet WHERE name='"
					+ name + "'");
			if (!resSet.next()) {
				statmt.execute("UPDATE wallet SET amount = '" + many
						+ "', name = '" + name + "'WHERE id=" + id + "; ");
				resSet.close();
				CloseDB();
			} else {
				id = -1;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	// добавление категории доходов
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

	// удаление категории доходов
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

	// редактирование категории доходов
		public static int EditIncome(WalletImpl impl) {
			Integer id = impl.getID();
			String name = impl.getName();
			Integer many = impl.getAmount().get();
			try {
				Conn();
				resSet = statmt.executeQuery("SELECT id FROM income WHERE name='"
						+ name + "'");
				if (!resSet.next()) {
					statmt.execute("UPDATE income SET amount = '" + many
							+ "', name = '" + name + "'WHERE id=" + id + "; ");
					resSet.close();
					CloseDB();
				} else {
					id = -1;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return id;
		}
	
	// добавление категории расходов
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

	// удаление категории расходов
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
	
	// редактирование категории расходов
			public static int EditExpense(WalletImpl impl) {
				Integer id = impl.getID();
				String name = impl.getName();
				Integer many = impl.getAmount().get();
				try {
					Conn();
					resSet = statmt.executeQuery("SELECT id FROM expense WHERE name='"
							+ name + "'");
					if (!resSet.next()) {
						statmt.execute("UPDATE expense SET amount = '" + many
								+ "', name = '" + name + "'WHERE id=" + id + "; ");
						resSet.close();
						CloseDB();
					} else {
						id = -1;
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return id;
			}

	// добавление транзакции дохода
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

	// добавление транзакции расхода
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

	// вывод списка кошельков
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

	// вывод списка доходов
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

	// вывод списка расходов
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
	 * список расходов за период 0-за весь период; 1 - за текущий месяц; 2 - за
	 * текущий квартал; 3 - за текущий год; 4 - за последние 2 года;
	 */
	public static List<WalletImpl> GetListExpenseHistory(int p) {
		String query = "";
		switch (p) {
		case 0:
			query = "SELECT expense.id as id, name, SUM (many) as many FROM expenseStory inner join expense on expenseStory.id_expense=expense.id GROUP BY expense.id";
			break;
		case 1:
			query = "SELECT expense.id as id, name, SUM (many) as many FROM expenseStory inner join expense on expenseStory.id_expense=expense.id WHERE strftime('%m', dateExpense) = strftime('%m', date('now')) AND strftime('%Y', dateExpense) = strftime('%Y', date('now')) GROUP BY expense.id";
			break;
		case 2:
			query = "SELECT expense.id as id, name, SUM (many) as many FROM expenseStory inner join expense on expenseStory.id_expense=expense.id WHERE strftime('%m', dateExpense) = strftime('%m', date('now')) AND strftime('%Y', dateExpense) = strftime('%Y', date('now')) GROUP BY expense.id";
			break;
		case 3:
			query = "SELECT expense.id as id, name, many as many FROM expenseStory inner join expense on expenseStory.id_expense=expense.id WHERE strftime('%Y', dateExpense) = strftime('%Y', date('now')) GROUP BY expense.id";
			break;
		case 4:
			query = "SELECT expense.id as id, name, many as many FROM expenseStory inner join expense on expenseStory.id_expense=expense.id WHERE strftime('%Y', dateExpense) = strftime('%Y', date('now'), '-1 year') OR strftime('%Y', dateExpense) = strftime('%Y', date('now')) GROUP BY expense.id";
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
			}
			CloseDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
