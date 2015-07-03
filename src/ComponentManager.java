import java.awt.Component;
import java.awt.Container;
import java.awt.MenuContainer;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTextField;


<<<<<<< HEAD:src/ComponentManager.java
public class ComponentManager extends Component 
							  implements Wallet {
=======
public class WalletView extends Component implements Wallet {
>>>>>>> origin/master:src/WalletView.java

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected RubleUnit balance;
	
	protected IconButton button = new IconButton();
	protected IconButton close  = new IconButton();
	protected IconButton edit = new IconButton();
	
	protected JTextField field = new JTextField();
	
	private Long ID;
	private String name;
	
	ComponentManager (){
		this(0, 0, 0, 0);
	}
	ComponentManager (int x, int y, int width, int height){
		this(new Rectangle(x, y, width, height));
	}
	ComponentManager (Rectangle rect){
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
		button.drawIcon(filename);
	}
	
	
	
	private void initialize (){
		field.setEditable(false);
		field.setText("0");
		close.drawIcon("close.gif");
		edit.drawIcon("karandash.gif");
	}
	
	
	public void appendTo(Container content){
		initialize();
		content.add(button);
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
		
		button.setBounds(x, y, width, height);
	}
	
	
	public List<Component> getComponents(){
		List<Component> list = new ArrayList<Component>();
		list.add(close);
		list.add(edit);
		list.add(field);
		list.add(button);
		return list;
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
