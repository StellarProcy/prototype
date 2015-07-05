import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
 
public class DiagramPanel extends JPanel implements Changeable<ActionEvent>{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer[] values ;
     private Insets insets;
     Color colors[] = { 
         Color.RED,
         Color.ORANGE,
         Color.YELLOW,
         Color.GREEN,
         Color.BLUE,
         Color.MAGENTA,
         Color.PINK
     };

   /** Creates new form TestDraw */
   public DiagramPanel() {
       initComponents();
   }

   

   @Override
   public void change(ActionEvent e) {
	   if (e.getActionCommand().equals("���")){
		   values[5] = new Integer(300);
	   } else if (e.getActionCommand().equals("�����")){
		   values[5] = new Integer(100);
	   } else if (e.getActionCommand().equals("������")){
		   values[5] = new Integer(5);
	   }

	   this.repaint();
   }


   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   
   // <editor-fold defaultstate="collapsed" desc="Generated Code">
   private void initComponents() {

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
       this.setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGap(0, 400, Short.MAX_VALUE)
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGap(0, 300, Short.MAX_VALUE)
       );
   }// </editor-fold>

   // Variables declaration - do not modify
   // End of variables declaration
      @Override
 public void paint(Graphics g) {
   Graphics2D gd = (Graphics2D) g;
   gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

   super.paint(g);
   if (insets == null) {
     insets = getInsets();
   }
   int x = 10;//insets.left;
   int y = 10;//insets.top;
   int width  = 145;//getWidth()  - insets.left - insets.right;
   int height = 145;//getHeight() - insets.top  - insets.bottom;
   int start  = 0;

   int steps    = values.length;
   int stepSize = 0;
   int total    = values[0];
   for (int i = 1; i < steps; i++) {
     stepSize = getStepSize(values[i],total);
     g.setColor(colors[i-1]);
     g.fillArc(x, y, width, height, start, stepSize);
     start += stepSize;
   }
 }
 
 
 private int getStepSize(Integer val,int total){
     return (int)(360*val)/total;
  }

 public void setValues(Integer[] values){
     this.values = values;
 }

}
