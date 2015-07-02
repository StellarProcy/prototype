import java.awt.Component;
import java.awt.Container;
import java.awt.MenuContainer;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;


public class WalletView extends Component 
<<<<<<< HEAD
						implements Wallet {
=======
			implements ActionListener, Wallet{
>>>>>>> origin/master

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected RubleUnit balance;
	
	protected IconButton wallet = new IconButton();
	protected IconButton close  = new IconButton();
	protected IconButton edit = new IconButton();
	
	protected JTextField field = new JTextField();
	
	private Long ID;
	private String name;
	
	WalletView (){
		this(0, 0, 0, 0);
	}
	WalletView (int x, int y, int width, int height){
		this(new Rectangle(x, y, width, height));
	}
	WalletView (Rectangle rect){
		this.setBounds(rect);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height){
		setBoundsOnComponents(x, y, width, height);
		super.setBounds(x, y, width, height);

	}
	@Override
	public void setBounds(Rectangle rect){
		setBoundsOnComponents(rect);
		super.setBounds(rect);
	}
	
	public void setIcon(String filename){
		wallet.drawIcon(filename);
	}
	
	
	
	private void initialize (){
		field.setEditable(false);
		field.setText("0");
		//close.setIcon(new ImageIcon(Window.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
		close.drawIcon("close.gif");
		edit.drawIcon("karandash.gif");
	}
	
	
	public void appendTo(Container content){
		initialize();
		content.add(wallet);
		content.add(close);
		content.add(edit);
		content.add(field);
	}
	
	
	protected final int FIELD_RELATION_HEIGHT = 3;
	protected final int SQUARE_RELATION_HEIGHT = 3;
	
	private void setBoundsOnComponents (Rectangle rect){
		this.setBounds(rect.x, rect.y, rect.width, rect.height);
	}
	
	private void setBoundsOnComponents (int x, int y, int width, int height){
		int divided = height / FIELD_RELATION_HEIGHT;
		height -= divided;
		field.setBounds(x, y + height, width, divided);
		
		divided = height / SQUARE_RELATION_HEIGHT;
		width -= divided;
		close.setBounds(x + width, y,		    divided, divided);
		edit.setBounds (x + width, y + divided, divided, divided);
		
		wallet.setBounds(x, y, width, height);
	}
	
	
	
	@Override
	public Long getID() {
		return ID;
	}
	@Override
	public void setID(Long ID) {
		this.ID = ID;
	}
	@Override
	public double getAmount() {
		return balance.get();
	}
	@Override
	public void setAmount(double amount) {
		balance.set(amount);
	}
	@Override
	public void setName(String name){
		this.name = name;
	}
	@Override
	public String getName(){
		return name;
	}

	
	
}