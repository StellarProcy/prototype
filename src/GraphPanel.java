import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;


public class GraphPanel extends JPanel implements Changeable<ActionEvent>{
	
	private double p = 1;
	
	GraphPanel (){
		super();
	}
	
	@Override
	public void paint (Graphics g){
		super.paint(g);
		
		g.setColor(Color.green);
		g.fillRect(50, mul(50, p), 50, 500 );
		
		g.setColor(Color.blue);
		g.fillRect(100, mul(100, p), 60, 550);

		g.setColor(Color.CYAN);
		g.fillRect(160, mul(120, p), 60, 550);

		g.setColor(Color.ORANGE);
		g.fillRect(220, mul(160, p), 40, 550);


	}

	private int mul (int arg0, double arg1){
		double result = arg1 * (double) arg0;
		return (int) result;
	}
	

	@Override
	public void change(ActionEvent e) {
		if (e.getActionCommand().equals("Год")){
			p = 1.5;
		} else if (e.getActionCommand().equals("Месяц")){
			p = 1.0;
		} else if (e.getActionCommand().equals("Неделя")){
			p = 0.3;
		}
		
		this.repaint();
	}

}
