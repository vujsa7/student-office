package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;


public class SearchButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1985136620379147442L;
	public static Color backgroundColor;
	
	public SearchButton() {
		backgroundColor = new Color(255,255,255);
		setText("Search");
		setPreferredSize(new Dimension(104, 25));
		setMaximumSize(new Dimension(104, 25));
		setBorderPainted(false);
		setFocusPainted(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new MyMouseListener());
		setBackground(backgroundColor);
		

	}
	

	
	public class MyMouseListener extends MouseAdapter{
		
		public void mouseEntered(MouseEvent mouseEvent) {
			SearchButton s = (SearchButton)mouseEvent.getComponent();
			s.setBackground(new Color(141,102,217));
			s.setForeground(Color.white);
		}
		
		public void mouseExited(MouseEvent mouseEvent) {
			SearchButton s = (SearchButton)mouseEvent.getComponent();
			s.setBackground(Color.white);
			s.setForeground(Color.black);
		}
		
	}

}
