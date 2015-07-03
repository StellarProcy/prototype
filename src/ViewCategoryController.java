import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;


public class ViewCategoryController implements Renderable<Rectangle>, ActionListener{

	private JFrame frame;
	private JButton addExpense;
	private JButton addIncome;
	
	private List<ComponentManager> income;
	private List<ComponentManager> expense;
	private String buttonName = "Добавить";

	private final int LEFT_BORDER = 10;
	private final int TOP_BORDER = 100;
	private final int FINAL_WIDTH = 140;
	private final int FINAL_HEIGHT = 50;
	private final int FINAL_RANGE = 5;
	
	
	ViewCategoryController(JFrame frame, List<String> income, List<String> expense){
		initialize(frame, income, expense);
		render();
	}
	
	private void create(ComponentManager cm, Rectangle rect, String name, List<ComponentManager> list, Color color){
		render(cm, rect);
		cm.setName(name);
		cm.button.setText(name);
		cm.button.setBackground(color);
		cm.close.addActionListener(this);
		cm.edit.addActionListener(this);
		cm.appendTo(frame.getContentPane());
		list.add(cm);
	}
	private Rectangle moveToRight(Rectangle rect){
		rect.x = rect.x + FINAL_RANGE + FINAL_WIDTH;
		return rect;
	}
	private Rectangle prepare(Rectangle rect){
		rect = next(null);
		rect.y = rect.y - FINAL_RANGE - FINAL_HEIGHT;
		return rect;
	}
	private void initialize(JFrame frame, List<String> incomeNames, List<String> expenseNames){
		this.frame = frame;
		income = new ArrayList<ComponentManager>();
		
		expense = new ArrayList<ComponentManager>();
		addExpense = new JButton(buttonName);
		addIncome = new JButton(buttonName);
		
		Rectangle rect = null;
		rect = prepare(rect);
		for (String name: incomeNames){
			create(new ComponentManager(), rect, name, income, Color.GREEN);
		}
		rect = next(rect);
		addIncome.setBounds(rect);
		rect = prepare(rect);
		rect = moveToRight(rect);
		Color color = new Color(255, 102, 51);
		
		for (String name: expenseNames){
			create(new ComponentManager(), rect, name, expense, color);
		}
		
		rect = next(rect);
		addExpense.setBounds(rect);
		
		frame.getContentPane().add(addIncome);
		frame.getContentPane().add(addExpense);
		frame.repaint();
		
	}

	private void observe(List<ComponentManager> list, JButton button, Rectangle rect){
		for (ComponentManager current : list){
			current.setBounds(rect);
			rect.y = rect.y + FINAL_HEIGHT + FINAL_RANGE;
		}
		button.setBounds(rect);
	}
	
	private Rectangle newDefaultInstance(){
		return new Rectangle(LEFT_BORDER, TOP_BORDER, FINAL_WIDTH, FINAL_HEIGHT);
	}
	@Override
	public void render() {
		Rectangle rect = newDefaultInstance();
		observe(income, addIncome, rect);
		rect = newDefaultInstance();
		rect = moveToRight(rect);
		observe(expense, addExpense, rect);
		frame.repaint();
	}


	@Override
	public Rectangle render(Object obj, Rectangle rect) {
		if (obj instanceof ComponentManager){
			rect = next(rect);
			((ComponentManager) obj).setBounds(rect);
		}
		return rect;
	}	

	private Rectangle next(Rectangle rect){
		if (rect == null){
			rect = new Rectangle (LEFT_BORDER, TOP_BORDER, FINAL_WIDTH, FINAL_HEIGHT);
		}
		else rect.y = rect.y + FINAL_HEIGHT + FINAL_RANGE;
		return rect;
	}

	
	private void check(Object unknown, List<ComponentManager> list){
		for (ComponentManager cm: list){
			if (unknown.equals(cm.close)){
				
				delete (frame.getContentPane(),
						cm.getComponents());
				
				list.remove(cm);
				break;
			} else if (unknown.equals(cm.edit)){
				// do smthg
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object unknown = arg0.getSource();
		check(unknown, income);
		check(unknown, expense);
		render();	
	}
	
	
	private void delete(Container content, List<Component> components){
		for (Component component: components){
			content.remove(component);
		}
	}
}
