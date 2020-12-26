package view.dialogs.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class ButtonHolderPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2191212716310408075L;


	public ButtonHolderPanel(DialogConfirmButton dialogConfirmButton, JDialog dialog) {
		setPreferredSize(new Dimension(507, 49));
		setOpaque(false);
		BoxLayout buttonHolder = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(buttonHolder);
		DialogDeclineButton dialogDeclineButton = new DialogDeclineButton();
		dialogDeclineButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		add(Box.createHorizontalStrut(239));
		add(dialogDeclineButton);
		add(Box.createHorizontalStrut(30));
		add(dialogConfirmButton);
		add(Box.createHorizontalStrut(54));
		
		
		
	}
	
	
}