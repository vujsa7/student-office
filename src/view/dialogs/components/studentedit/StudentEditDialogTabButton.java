package view.dialogs.components.studentedit;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.dialogs.StudentDialog;
import view.dialogs.StudentEditDialog;

public class StudentEditDialogTabButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3622500675617951570L;
	public ImageIcon activeIcon;
	public ImageIcon inactiveIcon;
	public static String activeButton = "Informacije";
	private String buttonId;
	
	private static Collection<StudentEditDialogTabButton> buttons = new ArrayList<StudentEditDialogTabButton>();
	
	public StudentEditDialogTabButton(String buttonText) {
		buttonId = buttonText;
		setMaximumSize(new Dimension(StudentDialog.getInstance().getWidth()/4,50));
		activeIcon = getResizedIcon(new ImageIcon("assets" + File.separator + "icons" + File.separator + "dialog_active_button.jpg"));
		inactiveIcon = getResizedIcon(new ImageIcon("assets" + File.separator + "icons" + File.separator + "dialog_inactive_button.jpg"));
		if(buttons.size() == 0) {
			setIcon(activeIcon);
		} else {
			setIcon(inactiveIcon);
		}
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		setText(buttonText);
		if(buttons.size() == 0) {
			setForeground(new Color(122, 72, 221));
		} else {
			setForeground(new Color(90, 90, 90));
		}
		buttons.add(this);
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new MyMouseListener());
	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(StudentDialog.getInstance().getWidth()/4, 50,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resizedImage);
		return icon;
	}
	
	public class MyMouseListener extends MouseAdapter{
		
		public void mouseEntered(MouseEvent mouseEvent) {
			JButton thisButton = (JButton) mouseEvent.getComponent();
			thisButton.setForeground(new Color(122, 72, 221));
		}
		
		public void mouseExited(MouseEvent mouseEvent) {
			JButton thisButton = (JButton) mouseEvent.getComponent();
			thisButton.setForeground(new Color(90, 90, 90));
		}
		
		public void mousePressed(MouseEvent mouseEvent) {
			activeButton = buttonId;
			updateAll();
		}		
	}
	
	public static void updateAll() {
		for(StudentEditDialogTabButton button : buttons) {
			button.updateView();
		}
		if(activeButton == StudentEditDialog.INFO_PANEL) {
			StudentEditDialog.getInstance().setPreferredSize(new Dimension(507, 804));
			StudentEditDialog.getInstance().pack();
		} else {
			StudentEditDialog.getInstance().setPreferredSize(new Dimension(1000, 804));
			StudentEditDialog.getInstance().pack();
		}
	}
	
	public void updateView() {
		if(activeButton == buttonId) {
			setIcon(activeIcon);
		} else {
			setIcon(inactiveIcon);
		}
		StudentEditDialog.getInstance().swapView(activeButton);
	}
}
