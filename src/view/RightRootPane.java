package view;

import javax.swing.JFrame;
import javax.swing.JRootPane;

public class RightRootPane extends JRootPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6105433962681526953L;

	public RightRootPane(JFrame main) {
		RightPanel rightPanel = new RightPanel();
		MenuBar menuBar = new MenuBar(main);
		setJMenuBar(menuBar);
		getContentPane().add(rightPanel);
	}

}
