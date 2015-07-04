
public class WalletImpl implements Wallet {

	private Long ID;
	private String name;
	protected RubleUnit balance = new RubleUnit();
	
	@Override
	public Long getID() {
		return ID;
	}
	@Override
	public void setID(Long ID) {
		this.ID = ID;
	}
	
	@Override
	public void setName(String name){
		this.name = name;
	}
	@Override
	public String getName(){
		return name;
	}
	@Override
	public Monetary getAmount() {
		return balance;
	}
	@Override
	public void setAmount(Monetary amount) {
		this.balance = (RubleUnit) amount;
	}
	
}
