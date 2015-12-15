//==================================================
// Author:	Kevin Cabrera
// Filename:	SelectPanel.java
//==================================================
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class SelectPanel extends JPanel {
	private Vector<String> bankList;
	private JLabel availLabel = new JLabel("Available bank(s)");
	private JLabel selectLabel = new JLabel("Selected bank(s)"); 
	private JLabel numOfBanks = new JLabel("The number of selected banks: " + 0);
	private JButton add = new JButton("Add");
	private JButton remove = new JButton("Remove");
	private DefaultListModel availModel = new DefaultListModel();
	private DefaultListModel selectModel = new DefaultListModel();
	private JList availList = new JList(availModel);
	private JList selectList = new JList(selectModel);
	
	public SelectPanel(Vector bankList) {
		this.bankList = bankList;
		
		availList.setBorder(BorderFactory.createEtchedBorder(1));
		selectList.setBorder(BorderFactory.createEtchedBorder(1));
		
		//create button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 1));
		buttonPanel.add(add);
		buttonPanel.add(remove);
		
		ActionListener b1 = new ButtonListener();
		add.addActionListener(b1);
		remove.addActionListener(b1);
		
		//create list panels
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(availLabel, BorderLayout.NORTH);
		leftPanel.add(availList, BorderLayout.CENTER);
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(selectLabel, BorderLayout.NORTH);
		rightPanel.add(selectList, BorderLayout.CENTER);
		
		//create main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 3));
		mainPanel.add(leftPanel);
		mainPanel.add(buttonPanel);
		mainPanel.add(rightPanel);
		
		
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
		add(numOfBanks, BorderLayout.SOUTH);
	}
	
	public void updateBankList() {
		availModel.clear();
		for (int i = 0; i < bankList.size(); i++)
		{
			availModel.addElement(bankList.get(i));
		}
	}
	
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == add)
			{
				int selectedIndex = availList.getSelectedIndex();
				if (selectModel.size() == 0)
					selectModel.addElement(bankList.get(selectedIndex));
				else
				{
					for (int i = 0; i < selectModel.size(); i++)
					{
						if (selectModel.get(i).equals(bankList.get(selectedIndex)))
							break;
						else if (i == selectModel.size() - 1)
						{
							selectModel.addElement(bankList.get(selectedIndex));
						}
					}
				}			
				numOfBanks.setText("The number of selected banks: " + selectModel.size());
			}
			else
			{
				selectModel.removeElementAt(selectList.getSelectedIndex());
				numOfBanks.setText("The number of selected banks: " + selectModel.size());
			}
		}
	}
}
