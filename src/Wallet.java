
public interface Wallet {
	
	public int getID();
	public void setID(int ID);
	
	public String getName();
	public void setName(String name);
	
	public Monetary getAmount();
	public void setAmount(Monetary amount);
}
