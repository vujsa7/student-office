package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class TabBarButtonPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7455221618933483832L;
	


	public TabBarButtonPanel() {
		
		setMaximumSize(new Dimension(418,210));
        setMinimumSize(new Dimension(418,210));
        setLayout(new GridLayout(0,1));
		setBackground(new Color(58,39,93));
		
	}
	

}
