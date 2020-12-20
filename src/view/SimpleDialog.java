package view;

import java.awt.Frame;

import javax.swing.JDialog;

public class SimpleDialog extends JDialog{
	
	public SimpleDialog(Frame parent, String title) {
		super(parent, title);
		
		setSize(250, 250);
		setLocationRelativeTo(parent);
	}
}
