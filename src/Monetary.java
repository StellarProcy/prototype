public interface Monetary {

	public String getMonetaryToken();

	public String getCanonicalName();

	public int get();

	public void set(int value);

	public Monetary clone();
}
