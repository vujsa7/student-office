package view;


import javax.swing.JFrame;
import javax.swing.JRootPane;

public class RightRootPane extends JRootPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6105433962681526953L;

	public RightRootPane(JFrame parent, TablePanel tablePanel) {
		RightPanel rightPanel = new RightPanel(tablePanel);
		MenuBar menuBar = new MenuBar(parent);
		setJMenuBar(menuBar);
		getContentPane().add(rightPanel);
	}

}
