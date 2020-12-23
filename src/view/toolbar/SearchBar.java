package view.toolbar;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class SearchBar extends JTextField{
	/**
	 * 
	 */
	private static final long serialVersionUID = -704582934280562569L;

	SearchBar(){
		setBorder(new EmptyBorder(0,0,0,10));
		setPreferredSize(new Dimension(170,35));
	}
	
}
