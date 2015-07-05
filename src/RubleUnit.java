
public class RubleUnit implements Monetary, Cloneable{
	
	RubleUnit(){
		this(0);
	}
	
	RubleUnit(int value){
		this.value = value;
	}

	
	private String token = "р";
	private String name = "рубль";
	
	private int value = 0;
	
	public void set(int value){
		this.value = value;
	}
	
	public String getMonetaryToken() {
		return token;
	}

	public String getCanonicalName() {
		return name;
	}

	public int get() {
		return value;
	}
	
	@Override
	public String toString(){
		return value + token;
	}

	public RubleUnit clone(){
		return new RubleUnit(this.value);
	}
}
