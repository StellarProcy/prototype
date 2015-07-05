import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;


public class ComponentManager extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected WalletImpl wallet;
	
	protected IconButton button = new IconButton();
	protected IconButton close  = new IconButton();
	protected IconButton edit = new IconButton();
	
	protected JTextField field = new JTextField();
	
	ComponentManager (WalletImpl wallet){
		this.wallet = wallet.clone();
	}
	
	public WalletImpl getWallet(){
		return wallet;
	}
	
	public void setWallet(WalletImpl wallet){
		this.wallet = wallet.clone();
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
		field.setText(wallet.getAmount().toString());
		button.setMargin(new Insets(0, 0, 0, 0));
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
	

	
	
}
