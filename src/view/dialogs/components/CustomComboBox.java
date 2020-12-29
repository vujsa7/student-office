package view.dialogs.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class CustomComboBox extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2036463339188625847L;
	private String field;
	private JComboBox<String> combo;
	
	public CustomComboBox(ArrayList<String> arrayList) {
		
		setLayout(new GridLayout(0,1));
		setMinimumSize(new Dimension(214,36));
		setMaximumSize(new Dimension(214,36));
		String[] array = arrayList.toArray(new String[arrayList.size()]);
		combo = new JComboBox<String>(array);	
		combo.setPreferredSize(new Dimension(214,36));
		combo.setUI(CustomComboBoxUI.createUI(combo));
		combo.setBorder(new SimpleRoundBorder());

		field = (String) combo.getSelectedItem();
		
		JPanel comboBoxPanel = new JPanel();
		FlowLayout fl = (FlowLayout) comboBoxPanel.getLayout();
		fl.setVgap(0);
		comboBoxPanel.add(combo);
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				field = (String) combo.getSelectedItem();
			}
			
		});
		add(comboBoxPanel);
	}
	
	public String getField() {
		return field;
	}

	public void setDefaultValue() {
		combo.setSelectedIndex(0);	
	}
	
	public static class CustomComboBoxUI extends BasicComboBoxUI {

	    public static ComboBoxUI createUI(JComponent c) {
	        return new CustomComboBoxUI();
	    }

	    @Override protected JButton createArrowButton() {
	    	JButton comboBoxButton = new JButton();
	    	ImageIcon imageIcon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"arrow.png"));
	    	comboBoxButton.setOpaque(false);
	    	comboBoxButton.setFocusPainted(false);
	    	comboBoxButton.setBorderPainted(false);
	    	comboBoxButton.setContentAreaFilled(false);
	    	comboBoxButton.setIcon(imageIcon);
	        return comboBoxButton;
	       
	    }
	    
	    private ImageIcon getResizedIcon(ImageIcon icon) {
			Image image = icon.getImage();
			Image resizedImage = image.getScaledInstance(15, 7,  java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(resizedImage);
			return icon;
		}
	    
	}
	
	public class SimpleRoundBorder extends AbstractBorder {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -3659927490296375725L;
		private Color bgColor = new Color(112, 112, 112);
		
	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        ((Graphics2D)g).setColor(bgColor);
	        ((Graphics2D)g).drawRoundRect(x, y, width-1, height-1, 12, 12); 
	    }
	 
	    public Insets getBorderInsets(Component c) {
	        return new Insets(3, 6, 3, 3);
	    }
	 
	    public Insets getBorderInsets(Component c, Insets insets){
	        insets.top = insets.left = insets.bottom = insets.right = 3;
	        return insets;
	    }
	}

}