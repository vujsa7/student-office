package view.listeners;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class MyFocusListener implements FocusListener{
	
	@Override
	public void focusGained(FocusEvent e) {
		
		JTextField txtField = (JTextField) e.getComponent();
		txtField.setBackground(Color.WHITE);
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField txt = (JTextField) e.getComponent();
		txt.setBackground(Color.GRAY);
		
		
		
	}
	
}
