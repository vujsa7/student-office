package view;

import java.awt.GridLayout;

import javax.swing.JRootPane;

public class RightRootPane extends JRootPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6105433962681526953L;

	public RightRootPane() {
		RightPanel rightPanel = new RightPanel();
		MenuBar menuBar = new MenuBar();
		GridLayout layout = new GridLayout(0,1);
		this.getContentPane().setLayout(layout);
		this.setJMenuBar(menuBar);
		this.getContentPane().add(rightPanel);
	}

}
