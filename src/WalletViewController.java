import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;


public class WalletViewController implements Renderable, ActionListener{
	
	private JFrame frame;
	private JButton addWallet; 
	private List<WalletView> wallets;
	
	private final String defaultName = "��� ��������"; 
	private final String buttonName = "��������";
	
	private final int LEFT_BORDER = 10;
	private final int TOP_BORDER = 30;
	private final int FINAL_WIDTH = 80;
	private final int FINAL_HEIGHT = 60;
	private final int FINAL_RANGE = 20;
	
	WalletViewController (JFrame frame, List<String> paths,  List<String> names){
		initialize(paths, names, frame);
		render();
	}
	
	@Override
	public void render(){
		int x = LEFT_BORDER;

		for (WalletView current : wallets){
			current.setBounds(x, TOP_BORDER, FINAL_WIDTH, FINAL_HEIGHT);
			x = x + FINAL_WIDTH + FINAL_RANGE;
			
		}
		addWallet.setBounds(x, TOP_BORDER, FINAL_WIDTH, FINAL_HEIGHT);
		frame.repaint();
	}
	
	@Override
	public Rectangle render(Object obj, Rectangle rect) {
		if (obj instanceof WalletView){
			rect = next(rect);
			((WalletView) obj).setBounds(rect);
		}
		return rect;
	}
	
	private void initialize(List<String> paths, List<String> names, JFrame frame){
		this.frame = frame;
		wallets = new ArrayList<WalletView>();
		addWallet = new JButton(buttonName);
		
		Iterator<String> nameIter = names.iterator();
		Rectangle rect = null;
		for (String path: paths){
			WalletView current = new WalletView();
			
			render(current, rect);
			current.setIcon(path);
		
			if (nameIter.hasNext()){
				current.setName(nameIter.next());
			} else current.setName(defaultName );
			
			current.close.addActionListener(this);
			current.edit.addActionListener(this);
			current.appendTo(frame.getContentPane());
			wallets.add(current);
		}
		
		frame.getContentPane().add(addWallet);
		frame.repaint();
	}
	

	private Rectangle next(Rectangle rect){
		if (rect == null){
			rect = new Rectangle (LEFT_BORDER, TOP_BORDER, FINAL_WIDTH, FINAL_HEIGHT);
		}
		else rect.x = rect.x + FINAL_WIDTH + FINAL_RANGE;
		return rect;
	}

	//TODO: COMPLETE EDIT ACTION
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object unknown = arg0.getSource();
		
		for (int i = 0; i < wallets.size(); i++){
			if (unknown.equals(wallets.get(i).close)){
				frame.getContentPane().remove(wallets.get(i));
				wallets.remove(i);
			} else if (unknown.equals(wallets.get(i).edit)){
				System.out.println("edit!");
			}
		}
		
		render();
		
	}




	
	
}
