package view;

import java.awt.Frame;

import javax.swing.JDialog;

public class SimpleDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleDialog(Frame parent, String title) {
		super(parent, title);
		
		setSize(750, 700);
		setLocationRelativeTo(parent);
		setResizable(false);
	}
}
