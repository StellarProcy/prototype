
public interface Wallet {
	
	public Long getID();
	public void setID(Long ID);
	
	public String getName();
	public void setName(String name);
	
	public Monetary getAmount();
	public void setAmount(Monetary amount);
}
