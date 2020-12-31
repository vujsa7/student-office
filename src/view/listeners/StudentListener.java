package view.listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import controller.StudentController;
import view.dialogs.StudentDialog;

public class StudentListener implements FocusListener{
	
	private String regex;
	public StudentListener(String regex) {
		this.regex = regex;
		
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField textField = (JTextField) e.getComponent();
		if(!Pattern.matches(regex, textField.getText())){
			String textFieldName = textField.getName();
			StudentDialog.showErrorPanel(Integer.parseInt(textFieldName));
			StudentDialog.checkIfCanBeValidated();
		} else {
			String textFieldName = textField.getName();
			if(textField.getName().equals("5")) {
				if(StudentController.getInstance().proveriPostojanjeIndeksa(textField.getText())) {
					StudentDialog.showIndexErrorPanel();
					StudentDialog.checkIfCanBeValidated();
				} else {
					StudentDialog.hideIndexErrorPanel();
					StudentDialog.checkIfCanBeValidated();
				}
			} else {
				StudentDialog.hideErrorPanel(Integer.parseInt(textFieldName));
				StudentDialog.checkIfCanBeValidated();
			}
		}
		
	}
	
}