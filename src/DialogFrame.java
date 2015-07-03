import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Color;


public class DialogFrame extends JDialog{

	private final JPanel contentPanel = new JPanel();
	
	protected JTextField textName;
	protected JTextField textMonetary;
	protected JTextField textComment;
	protected JLabel labelName;
	protected JLabel labelMonetary;
	protected JLabel labelComment;
	protected JLabel labelException;
	protected boolean hasComment;
	
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel label;
	
	protected JButton okButton;
	protected JButton cancelButton;

	
	public DialogFrame(JFrame frame, boolean focus, String name, Monetary current, boolean hasComment){
		super(frame, "Настройка " + name, focus);
		init(name, current);
		showComment(hasComment);
	}

	private void showComment(boolean hasComment){
		this.hasComment = hasComment;
		if (hasComment != true){
			labelComment.setVisible(false);
			textComment.setVisible(false);
		}
	}
	
	protected void validateFields() throws IllegalArgumentException {
		if (textName.getText().equals("")){
			throw new IllegalArgumentException("Название не должно быть пустым.");
		} else if (textMonetary.equals("")){
			textMonetary.setText("0");
		} 
		try {
			int buf = Integer.parseInt(textMonetary.getText());
			
			if (buf < 0)
				throw new IllegalArgumentException("Сумма не должна быть отрицательна");
		} catch(NumberFormatException e){
			throw new IllegalArgumentException("Сумма должна быть корректна.");
		}
	}
	private void init(String name, Monetary current) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		labelName = new JLabel("\u0418\u043C\u044F");
		labelName.setBounds(23, 27, 104, 14);
		contentPanel.add(labelName);
		
		textName = new JTextField();
		textName.setBounds(158, 24, 188, 20);
		textName.setText(name);
		contentPanel.add(textName);
		textName.setColumns(10);
		
		textMonetary = new JTextField();
		textMonetary.setBounds(158, 55, 188, 20);
		textMonetary.setText(current.get() + "");
		contentPanel.add(textMonetary);
		textMonetary.setColumns(10);

		textComment = new JTextField();
		textComment.setBounds(158, 87, 188, 20);
		contentPanel.add(textComment);
		textComment.setColumns(10);
		
		labelMonetary = new JLabel("\u0421\u0443\u043C\u043C\u0430");
		labelMonetary.setBounds(22, 58, 104, 14);
		contentPanel.add(labelMonetary);
		
		labelComment = new JLabel("\u041A\u043E\u043C\u043C\u0435\u043D\u0442\u0430\u0440\u0438\u0439");
		labelComment.setBounds(23, 90, 104, 14);
		contentPanel.add(labelComment);
		
		labelException = new JLabel("");
		labelException.setBounds(113, 155, 253, 14);
		labelException.setVisible(false);
		contentPanel.add(labelException);
		
		lblNewLabel = new JLabel("*");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(102, 27, 46, 14);
		contentPanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(102, 58, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		label = new JLabel("* \u043E\u0431\u044F\u0437\u0430\u0442\u0435\u043B\u044C\u043D\u044B \u043A \u0437\u0430\u043F\u043E\u043B\u043D\u0435\u043D\u0438\u044E");
		label.setBounds(25, 126, 183, 14);
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


	/*
	private void fill(){
	//	buffer.setName(labelName.getText());
		//buffer.balance.set(Double.parseDouble(labelMonetary.getText()));
		buffer.setName("hello");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object unknown = e.getSource();
		if (unknown.equals(cancelButton)){
			dispose();
		} else if(unknown.equals(okButton)){
			try {
				validateFields();
				fill();
				dispose();
			} catch (Exception ex){
				labelException.setText(ex.getMessage());
				labelException.setVisible(true);
			}
		}
	}*/
}
