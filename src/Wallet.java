import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;


public class Wallet extends Component implements ActionListener{

	protected RubleUnit balance;
	
	protected IconButton wallet = new IconButton();
	protected IconButton close  = new IconButton();
	protected IconButton edit = new IconButton();
	
	protected JTextField field = new JTextField();
	
	Wallet (){
		this(0, 0, 0, 0);
	}
	Wallet (int x, int y, int width, int height){
		this(new Rectangle(x, y, width, height));
	}
	Wallet (Rectangle rect){
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
		close.setIcon(new ImageIcon(Window.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
		edit.drawIcon("karandash.gif");
		close.addActionListener(this);
		edit.addActionListener(this);
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
	
	//TODO: GOTTA FINISH THIS
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Object unknown = arg0.getSource();
		
		if (unknown.equals(close)){
			//DO SMTG
		} else if (unknown.equals(edit)){
			//DO SMTG
		}
	}


	
	
}
