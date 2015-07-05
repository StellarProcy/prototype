import java.awt.Component;
import java.awt.Rectangle;
import java.util.List;


public class ViewUtil {
	
	protected static final int LOCATE_RIGHT = 2;
	protected final static int LOCATE_UP = 4;
	protected final static int LOCATE_LEFT = 8;
	protected final static int LOCATE_DOWN = 16;
	
	public static Rectangle consecutiveLocation (List<? extends Component> list, Rectangle rect, int range, int location){
		Rectangle currentRect = null;
		
		for (Component comp : list){
			currentRect = next (currentRect, rect, range, location);
			comp.setBounds(currentRect);
		}
		return next (currentRect, rect, range, location);
	}

	public static Rectangle next(Rectangle rect, Rectangle mark, int range, int location){
		if (rect == null){
			rect = (Rectangle) mark.clone();
		}
		else  {
			switch(location){
			case LOCATE_RIGHT:
				rect.x += mark.width + range; break;
			case LOCATE_DOWN:
				rect.y += mark.height + range; break;
			case LOCATE_LEFT:
				rect.x -= mark.width + range; break;
			case LOCATE_UP:
				rect.y -= mark.height + range; break;
			}
		}
		return rect;
	}
	
}
