package view.dialogs.components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import view.listeners.ProfessorListener;
import view.listeners.StudentListener;

public class CustomTextField extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6309053179026110859L;
	
	
	public CustomTextField(DialogConfirmButton dialogConfirmButton, JTextField textField, String regex, String textFieldName, String dialogType){
		setLayout(new GridLayout(0,1));
		ImageIcon icon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"dialog_text_field.png"));
		setIcon(icon);
		setMaximumSize(new Dimension(214, 36));
		textField.setName(textFieldName);
		textField.setOpaque(false);
		textField.setBorder(new EmptyBorder(0,10,0,10));
		textField.setMaximumSize(new Dimension(198,36));
		textField.setMinimumSize(new Dimension(198,36));
		if(dialogType == "student") {
			textField.addFocusListener(new StudentListener(regex));
		} else {
			textField.addFocusListener(new ProfessorListener(regex));
		}
		add(textField);
		
	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(214, 36,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resizedImage);
		return icon;
	}
	
}