package view.dialogs.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ErrorPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3021059409241442393L;
	private JLabel label;

	public ErrorPanel(String errorText) {
		File font_file = new File("assets"+ File.separator +"fonts"+ File.separator +"Montserrat-Regular.ttf");
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, font_file);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Font sizedFont = font.deriveFont(10f);
		JLabel errorLabel = new JLabel(errorText);
		errorLabel.setFont(sizedFont);
		errorLabel.setForeground(new Color(199,0,0));
		errorLabel.setBorder(new EmptyBorder(0,0,0,0));
		label = errorLabel;
		FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
		layout.setVgap(0);
		setLayout(layout);
		
		setMaximumSize(new Dimension(214, 13));
		setPreferredSize(new Dimension(214, 13));
		setOpaque(false);
		setVisible(false);
		add(errorLabel);
	}

	public void setLabelText(String errorMessage) {
		label.setText(errorMessage);
	}

}
