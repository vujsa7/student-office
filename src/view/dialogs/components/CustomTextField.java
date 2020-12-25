package view.dialogs.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CustomTextField extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6309053179026110859L;
	
	
	public CustomTextField(DialogConfirmButton dialogConfirmButton, JTextField textField){
		setLayout(new GridLayout(0,1));
		ImageIcon icon = getResizedIcon(new ImageIcon("assets/icons/dialog_text_field.png"));
		setIcon(icon);
		setMaximumSize(new Dimension(214, 36));
		
		textField.setOpaque(false);
		textField.setBorder(new EmptyBorder(0,10,0,10));
		textField.setMinimumSize(new Dimension(198,36));
		add(textField);
		
	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(214, 36,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resizedImage);
		return icon;
	}
	
}
