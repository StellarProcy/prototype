
public class RubleUnit implements Monetary{
	
	RubleUnit(){
		this(0.0);
	}
	RubleUnit(int value){
		this((double) value);
	}
	RubleUnit(double value){
		this.value = value;
	}

	
	private String token = "₽";
	private String name = "рубль";
	
	private double value;
	
	public void set(double value){
		this.value = value;
	}
	
	public String getMonetaryToken() {
		return token;
	}

	public String getCanonicalName() {
		return name;
	}

	public double get() {
		return value;
	}

}
