package view;

import java.awt.Dimension;

import javax.swing.JTextField;

public class SearchBar extends JTextField{
	/**
	 * 
	 */
	private static final long serialVersionUID = -704582934280562569L;

	SearchBar(){
		setBorder(javax.swing.BorderFactory.createEmptyBorder());
		setPreferredSize(new Dimension(214,25));
	}
}
