package view.toolbar;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class ToolBarPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 884792021463366840L;

	public ToolBarPanel() {
		setLayout(new GridLayout(0,1));
		setPreferredSize(new Dimension(1920, 50));
		setMaximumSize(new Dimension(1920, 50)); 
		ToolBar myToolBar = new ToolBar();
		add(myToolBar);
		
	}

}
