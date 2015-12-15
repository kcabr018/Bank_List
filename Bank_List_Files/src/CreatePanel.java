
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class CreatePanel extends JPanel {
	private Vector<String> bankList;
	private SelectPanel selectPanel;
	private JLabel instruction = new JLabel("Enter a Bank's name");
	private JLabel error = new JLabel();
	private JTextField field = new JTextField();
	private JButton addButton = new JButton("Add a Bank");
	private JTextArea bankAreaList = new JTextArea("No Bank");
	private JScrollPane scroll = new JScrollPane(bankAreaList);
	
	public CreatePanel(Vector<String> bankList, SelectPanel panel) {
		this.bankList = bankList;
		selectPanel = panel;
		
		//set up text area
		bankAreaList.setBorder(BorderFactory.createEtchedBorder(1));
		bankAreaList.setLineWrap(true);
		bankAreaList.setWrapStyleWord(true);
		
		ActionListener b1 = new ButtonListener();
		addButton.addActionListener(b1);
		
		//create and adjust a left panel
		JPanel leftPanel = new JPanel();
		leftPanel.add(instruction);
		leftPanel.add(addButton);
		leftPanel.add(field);
		
		leftPanel.setLayout(null);
		instruction.setBounds(0, 70, 125, 20);
		addButton.setBounds(0, 112, 246, 60);
		field.setBounds(125, 53, 122, 60);
		
		setLayout(new GridLayout(1,2));
		add(leftPanel);
		add(scroll);
	}
	
	public void setTextArea() {
		bankAreaList.removeAll();
		String list = "";
		
		for (int i = 0; i < bankList.size(); i++)
		{
			list += bankList.get(i) + "\n";
		}
		bankAreaList.setText(list);
	}
	
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String input = field.getText();
			
			if (bankList.size() == 0)
			{
				if (input.equals(""));
				else
				{
					bankList.add(input);
					setTextArea();
					selectPanel.updateBankList();	
				}
			}
			else 
			{
				if (input.equals(""));
				else
				{
					for (int i = 0; i < bankList.size(); i++)
					{
						if (input.equals(bankList.get(i)))
						{
							error.setText("Bank not added");
							break;
						}
						else if (i == bankList.size() - 1)
						{
							bankList.add(input);
							setTextArea();
							selectPanel.updateBankList();
						}
					}	
				}
			}
			
		}
	}
}
