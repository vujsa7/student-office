package view.listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import controller.StudentController;
import view.dialogs.StudentEditDialog;

public class StudentEditListener implements FocusListener{
	
	private String regex;
	public StudentEditListener(String regex) {
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
			StudentEditDialog.showErrorPanel(Integer.parseInt(textFieldName));
			StudentEditDialog.checkIfCanBeValidated();
		} else {
			String textFieldName = textField.getName();
			if(textField.getName() == "5") {
				if(StudentController.getInstance().postojiLiIndeks(textField.getText())) {
					StudentEditDialog.showIndexErrorPanel();
					StudentEditDialog.checkIfCanBeValidated();
				} else {
					StudentEditDialog.hideIndexErrorPanel();
					StudentEditDialog.checkIfCanBeValidated();
				}
			} else {
				StudentEditDialog.hideErrorPanel(Integer.parseInt(textFieldName));
				StudentEditDialog.checkIfCanBeValidated();
			}
		}
	}

}
