package view.dialogs.components;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ButtonHolderPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2191212716310408075L;


	public ButtonHolderPanel(DialogConfirmButton dialogConfirmButton) {
		setPreferredSize(new Dimension(507, 49));
		setOpaque(false);
		BoxLayout buttonHolder = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(buttonHolder);
		DialogDeclineButton dialogDeclineButton = new DialogDeclineButton();
		//DialogConfirmButton dialogConfirmButton = new DialogConfirmButton();
		add(Box.createHorizontalStrut(239));
		add(dialogDeclineButton);
		add(Box.createHorizontalStrut(30));
		add(dialogConfirmButton);
		add(Box.createHorizontalStrut(54));
		
		
		
	}
	
	
}