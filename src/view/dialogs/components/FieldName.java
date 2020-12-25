package view.dialogs.components;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FieldName extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8704200338633838431L;

	public FieldName(String fieldText) {
		
		FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
		layout.setHgap(0);
		setLayout(layout);
		setPreferredSize(new Dimension(200, 36));
		setMinimumSize(new Dimension(200, 36));
		setOpaque(false);
		JPanel fieldNamePanel = new JPanel();
		
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER);
		layout1.setHgap(0);
		fieldNamePanel.setLayout(layout1);
		fieldNamePanel.setOpaque(false);
		JLabel labelFieldName = new JLabel(fieldText , SwingConstants.RIGHT);
		labelFieldName.setVerticalAlignment(JLabel.CENTER);
		labelFieldName.setBorder(new EmptyBorder(3,0,0,0));
		fieldNamePanel.add(labelFieldName);
		add(fieldNamePanel);
	}
}
