
public class WalletImpl implements Wallet, Cloneable{

	private int ID;
	private String name;
	private Monetary amount;
	
	WalletImpl (int ID, String name, Monetary amount){
		this.ID = ID;
		this.name = name;
		this.amount = amount;
	}
	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void setID(int ID) {
		this.ID = ID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Monetary getAmount() {
		return amount;
	}

	@Override
	public void setAmount(Monetary amount) {
		this.amount = amount;
	}
	
	public WalletImpl clone (){
		return new WalletImpl(ID, name, amount);
	}

}
