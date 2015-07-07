import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;


public class GraphPanel extends JPanel implements Changeable<ActionEvent>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] expense = {10,160,50,80,30,70,60};
	private int[] income = {40,30,50,10,60,40,50};
	
	GraphPanel (){
		super();
	}
	
	@Override
	public void paint (Graphics g){
		super.paint(g);
		int xSize=150;
		int ySize=300;
		int y=30;
		int max=expense[0];
		int min=expense[0];
		for (int i=1; i<expense.length; i++){
			if (expense[i]>max){
				max = expense[i];
			}else{
				if (expense[i]<min){
					min = expense[i];
				}
			}
		}
		for (int i=1; i<income.length; i++){
			if (income[i]>max){
				max = income[i];
			}else{
				if (income[i]<min){
					min = income[i];
				}
			}
		}
		int yPoint = y;
		int steps=ySize/expense.length;
		g.setColor(Color.green);
		for (int i=1; i<expense.length; i++){
			int yA = mul(expense[i-1], (double)xSize/(max-min));//expense[i-1]*(xSize%(max-min));
			int yB = mul(expense[i], (double)xSize/(max-min));//expense[i-1]*(xSize%(max-min));
			g.drawLine(yPoint,yA,yPoint+steps,yB);
			yPoint+=steps;
		}
		yPoint = y;
		g.setColor(Color.red);
		for (int i=1; i<income.length; i++){
			int yA = mul(income[i-1], (double)xSize/(max-min));//expense[i-1]*(xSize%(max-min));
			int yB = mul(income[i], (double)xSize/(max-min));//expense[i-1]*(xSize%(max-min));
			g.drawLine(yPoint,yA,yPoint+steps,yB);
			yPoint+=steps;
		}
	}

	private int mul (int arg0, double arg1){
		double result = arg1 * (double) arg0;
		return (int) result;
	}
	

	@Override
	public void change(ActionEvent e) {
		if (e.getActionCommand().equals("Месяц")){
			expense = new int[] {30,100,50,80,70,30,20};
			income = new int[] {20,30,80,30,60,90,10};
		} else if (e.getActionCommand().equals("Квартал")){
			expense = new int[] {30,20,30,80,20,30,20};
			income = new int[] {20,30,80,40,60,60,20};
		} else if (e.getActionCommand().equals("Год")){
			expense = new int[] {50,20,50,80,70,30,90};
			income = new int[] {70,40,80,30,60,90,10};
		} else if (e.getActionCommand().equals("Весь период")){
			expense = new int[] {30,50,80,10,90,40,30};
			income = new int[] {90,60,90,50,60,20,30};
		}
		
		this.repaint();
	}

}
