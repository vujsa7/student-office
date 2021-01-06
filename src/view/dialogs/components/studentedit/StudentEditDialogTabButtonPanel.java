package view.dialogs.components.studentedit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.dialogs.StudentEditDialog;

public class StudentEditDialogTabButtonPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5619359292501667282L;
	
	public StudentEditDialogTabButtonPanel() {
		setLayout(new GridLayout(0,1));
		setBackground(Color.white);
		
		JLabel label = new JLabel();
		BoxLayout box = new BoxLayout(label, BoxLayout.X_AXIS);
		label.setLayout(box);
		label.setMaximumSize(new Dimension(507, 50));
		label.setIcon(getResizedIcon(new ImageIcon("assets" + File.separator + "icons" + File.separator + "dialog_tab_button_panel.jpg")));
		
		StudentEditDialogTabButton button1 = new StudentEditDialogTabButton(StudentEditDialog.INFO_PANEL);
		StudentEditDialogTabButton button2 = new StudentEditDialogTabButton(StudentEditDialog.POLOZENI_PANEL);
		StudentEditDialogTabButton button3 = new StudentEditDialogTabButton(StudentEditDialog.NEPOLOZENI_PANEL);
		label.add(button1);
		label.add(button2);
		label.add(button3);
		add(label);
	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(1000, 50,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resizedImage);
		return icon;
	}
}
