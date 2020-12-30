package view.dialogs.components.professoredit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.dialogs.ProfessorEditDialog;

public class DialogProfessorEditTabButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8086043011618051472L;

	public DialogProfessorEditTabButtonPanel() {
		setLayout(new GridLayout(0,1));
		setMaximumSize(new Dimension(507, 50));
		setBackground(new Color(123,32,188));
		
		JLabel label = new JLabel();
		BoxLayout box = new BoxLayout(label, BoxLayout.X_AXIS);
		label.setLayout(box);
		label.setMaximumSize(new Dimension(507, 50));;
		label.setIcon(new ImageIcon("assets" + File.separator + "icons" + File.separator + "dialog_tab_button_panel.jpg"));
		
		DialogProfessorEditTabButton button1 = new DialogProfessorEditTabButton(ProfessorEditDialog.INFO_PANEL);
		DialogProfessorEditTabButton button2 = new DialogProfessorEditTabButton(ProfessorEditDialog.SUBJECT_PANEL);
		label.add(button1);
		label.add(button2);
		add(label);
	}
	
	

}
