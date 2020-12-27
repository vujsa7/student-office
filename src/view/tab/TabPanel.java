package view.tab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import view.table.TablePanel;

public class TabPanel  extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8121057789462742949L;

	private TablePanel tablePanel;
	
	public TabPanel(){
		setBackground(new Color(58,39,93));
		BoxLayout box=new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(box);
		setPreferredSize(new Dimension(418, 780));
		
		LogoPanel logoPanel = new LogoPanel();
		TabBarButtonPanel tabBarButtonPanel = new TabBarButtonPanel();
		for (final String keyText : TablePanel.KEY_TEXTS) {
	         TabBarButton btn = new TabBarButton(keyText);
	         btn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               if (tablePanel != null) {
	                  tablePanel.swapView(keyText);
	               }
	            }
	         });
	         tabBarButtonPanel.add(btn);
	    }
		JPanel freeSpacePanel = new JPanel();
		freeSpacePanel.setLayout(new GridLayout(0,1));
		freeSpacePanel.setBackground(new Color(58,39,93));
		add(logoPanel);
		add(tabBarButtonPanel);
		add(freeSpacePanel);
		
	}

	public void setTablePanel(TablePanel tablePanel) {
		this.tablePanel = tablePanel;
	}
	
}
