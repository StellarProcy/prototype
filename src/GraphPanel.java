import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class GraphPanel extends JPanel{
	
	GraphPanel (){
		super();
	}
	
	@Override
	public void paint (Graphics g){
		super.paint(g);
		
		g.setColor(Color.green);
		g.fillRect(50, 50, 50, 200);
		
		g.setColor(Color.blue);
		g.fillRect(100, 100, 60, 150);

		g.setColor(Color.CYAN);
		g.fillRect(160, 120, 60, 150);

		g.setColor(Color.ORANGE);
		g.fillRect(220, 160, 40, 150);


	}

}
