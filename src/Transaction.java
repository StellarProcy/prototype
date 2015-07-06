public class Transactions{

	private static int ID = (Integer) null;
	private int id_wallet;
    private int id_category;
    private int date;
    private Monetary amount;
	private Object name;
	
	Transactions (int id_wallet, int id_category, Monetary amount){ //не забыть про дату
		this.ID = ID;
		this.name = name;
		this.amount = amount;
	}
	

	public int getid_wallet() {
		return ID;
	}

	
	public void setid_wallet(int ID) {
		this.ID = ID;
	}

	public int getid_category1() {
		return ID;
	}

	
	public void setid_category(int ID) {
		this.ID = ID;
	}


	public int getdate() {
		return date;
	}

	
	public void setdate() {
		//return date;
		
	}

	
	public Monetary getAmount() {
		return amount;
	}

	
	public void setAmount(Monetary amount) {
		this.amount = amount;
	}
	


}
