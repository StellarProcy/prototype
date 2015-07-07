import java.util.Date;

public class Transaction {

	private int ID;
	private int walletID;
	private int categoryID;
	private Date date;
	private Monetary amount;

	Transaction(int ID, int walletID, int categoryID, Date date, Monetary amount) {
		this.ID = ID;
		this.walletID = walletID;
		this.categoryID = categoryID;
		this.date = date;
		this.amount = amount;
	}

	public int getID() {
		return ID;
	}

	public int getWalletID() {
		return walletID;
	}

	public void setWalletID(int ID) {
		this.walletID = ID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int ID) {
		this.categoryID = ID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Monetary getAmount() {
		return amount;
	}

	public void setAmount(Monetary amount) {
		this.amount = amount;
	}
}
