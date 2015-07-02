import java.awt.Rectangle;


public interface Renderable {

	public void render();
	public Rectangle render(Object obj, Rectangle rect);
}
