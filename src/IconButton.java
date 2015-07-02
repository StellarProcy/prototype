import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class IconButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String filename;
	
	private Image getScaledInstance (BufferedImage bImg){
		return bImg.getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH);
	}

	IconButton(){
		super();
	}
	IconButton(String filename) {
		super();
		this.filename = filename;
	}

	
	public void drawIcon(){
		drawIcon(this.filename);
	}
	
	public void drawIcon(String filename){
		if (filename != null) {
			Image img = this.getImage(filename);
			setIcon(new ImageIcon(img));
		}
	}

	
	
	private Image getImage(String filename){
		BufferedImage bi = null;
		
		try {
			bi = ImageIO.read(new File (filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this.getScaledInstance(bi);
	}
	
	private Image getImage(int width, int height, Color color) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setColor(color);
		g.dispose();
		return bi; 
	}
	
	public void rolloverPaint(){
		Image rollover = getImage(64, 64, Color.ORANGE).getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH);
	    setRolloverIcon(new ImageIcon(rollover));
	}
}
