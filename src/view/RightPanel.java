package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class RightPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5726614048951072812L;

	public RightPanel() {
		this.setBackground(Color.white);
		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(box);
		JPanel toolBarPanel = new JPanel();
		toolBarPanel.setLayout(new GridLayout(0,1));
		toolBarPanel.setMaximumSize(new Dimension(1920, 50));
		toolBarPanel.setMinimumSize(new Dimension(0, 50));
		MyToolBar myToolBar = new MyToolBar();
		toolBarPanel.add(myToolBar);
		add(toolBarPanel);
		JPanel tablePanel = new JPanel();
		add(tablePanel);
		StatusBarPanel statusBarPanel = new StatusBarPanel();
		add(statusBarPanel);
	}
}
