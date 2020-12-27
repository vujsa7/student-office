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
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.MutableComboBoxModel;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;


public class DateComboBox extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2036463339188625847L;
	private Integer selectedItem;
	private static Collection<DateComboBox> dateComboBoxes = new ArrayList<DateComboBox>();
	private String comboType;
	private static boolean leap = false;
	private JComboBox<Integer> combo;
	private Integer comboSize;
	private MutableComboBoxModel<Integer> model;
	static String dateString;
	
	public DateComboBox(ArrayList<Integer> list, Dimension size, String type) {
		
		setLayout(new GridLayout(0,1));
		setMinimumSize(size);
		setMaximumSize(size);
		dateComboBoxes.add(this);
		combo = new JComboBox<Integer>();
		model = (MutableComboBoxModel<Integer>) combo.getModel();	
		for(int i = 0; i < list.size(); i++) {
			combo.insertItemAt(list.get(i), i);;
		}
		comboSize = list.size();
		dateString = "1950-01-01";
		combo.setSelectedItem(list.get(0));
		
		combo.setPreferredSize(size);
		combo.setUI(DateComboBoxUI.createUI(combo));
		combo.setBorder(new SimpleRoundBorder());
		comboType = type;
		combo.addActionListener (new MyDateComboBoxListener());
		
		JPanel comboBoxPanel = new JPanel();
		FlowLayout fl = (FlowLayout) comboBoxPanel.getLayout();
		fl.setVgap(0);
		comboBoxPanel.add(combo);
		
		add(comboBoxPanel);
	}
	
	public JComboBox<Integer> getComboBox() {
		return combo;
	}
	
	private void updateDays(ArrayList<Integer> days) {
		for(int i = 0; i < comboSize; i++){
			model.removeElementAt(0);
			
	    }
		for(int i = 0; i < days.size(); i++) {
			model.insertElementAt(days.get(i), i);
		}
		comboSize = days.size();
		combo.setSelectedItem(model.getElementAt(0));
	}
	
	
	public String getComboType() {
		return comboType;
	}
	
	public DateComboBox getDateComboBox() {
		return this;
	}
	
	public static String getDateString() {
		return dateString;
	}

	public static class DateComboBoxUI extends BasicComboBoxUI {

	    public static ComboBoxUI createUI(JComponent c) {
	        return new DateComboBoxUI();
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
	
	public class MyDateComboBoxListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			selectedItem = (Integer) combo.getSelectedItem();
			updateDate();
	    	if(comboType.equals("years")) {
	    		// Provera da li je godina prestupna
	    		if (selectedItem % 4 == 0) {
	    		      if (selectedItem % 100 == 0) {
	    		        if (selectedItem % 400 == 0) {
	    		        	leap = true;
	    		        }
	    		        else {
	    		        	leap = false;
	    		        }
	    		      } else {
	    		    	  leap = true;
	    		      }
	    		    } else {
	    		    	 leap = false;
	    		    }
	    		for(DateComboBox dateComboBox : dateComboBoxes) {
	    			if(dateComboBox.getComboType().equals("months")) {
	    				selectedItem = (Integer) dateComboBox.getComboBox().getSelectedItem();
	    				if(selectedItem == 2) {
	    					for(DateComboBox dateComboBox1 : dateComboBoxes) {
	    						if(dateComboBox1.getComboType().equals("days")) {
			    					if(leap) {
			    						@SuppressWarnings("serial")
			    						ArrayList<Integer> days = new ArrayList<Integer>() {{
											for(int i = 1; i <= 29; i++)
												add(i);
										}};
			    						dateComboBox1.getDateComboBox().updateDays(days);
			    					} else {
			    						@SuppressWarnings("serial")
			    						ArrayList<Integer> days = new ArrayList<Integer>() {{
											for(int i = 1; i <= 28; i++)
												add(i);
										}};
			    						dateComboBox1.getDateComboBox().updateDays(days);
			    					}
			    					
			    				}
	    					}
	    					
	    				}
	    			}
	    		}
	    	} else if (comboType.equals("months")) {
	    		// Postavljanje dana u mesecu
	    		if (selectedItem == 2) {
	    			for(DateComboBox dateComboBox : dateComboBoxes) {
	    				if(dateComboBox.getComboType().equals("days")) {
	    					if(leap) {
	    						@SuppressWarnings("serial")
	    						ArrayList<Integer> days = new ArrayList<Integer>() {{
									for(int i = 1; i <= 29; i++)
										add(i);
								}};
	    						dateComboBox.getDateComboBox().updateDays(days);
	    					} else {
	    						@SuppressWarnings("serial")
	    						ArrayList<Integer> days = new ArrayList<Integer>() {{
									for(int i = 1; i <= 28; i++)
										add(i);
								}};
	    						dateComboBox.getDateComboBox().updateDays(days);
	    					}
	    					
	    				}
	    			}
	    		} else if(selectedItem == 4 || selectedItem == 6 || selectedItem == 9 || selectedItem == 11) {
	    			for(DateComboBox dateComboBox : dateComboBoxes) {
	    				if(dateComboBox.getComboType().equals("days")) {
	    					@SuppressWarnings("serial")
	    					ArrayList<Integer> days = new ArrayList<Integer>() {{
								for(int i = 1; i <= 30; i++)
									add(i);
							}};
							dateComboBox.getDateComboBox().updateDays(days);
	    				}
	    			}
	    		} else {
	    			for(DateComboBox dateComboBox : dateComboBoxes) {
	    				if(dateComboBox.getComboType().equals("days")) {
	    					@SuppressWarnings("serial")
							ArrayList<Integer> days = new ArrayList<Integer>() {{
								for(int i = 1; i <= 31; i++)
									add(i);
							}};
							dateComboBox.getDateComboBox().updateDays(days);
	    				}
	    			}
	    		}
	    	}
			
		}

		private void updateDate() {
			String date = "";
			for(DateComboBox dateComboBox : dateComboBoxes) {
				date = date.concat(String.valueOf(dateComboBox.getComboBox().getSelectedItem()));
				date = date.concat("-");
			}
			date = date.substring(0, date.length() - 1);
			dateString = date.toString();
		}
	}

	public static void emptyComboBox() {
		if(!dateComboBoxes.isEmpty()) {
			dateComboBoxes.clear();
		}
		
	}

}