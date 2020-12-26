
/*package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import view.dialogs.components.CustomComboBox;
import view.dialogs.components.DateComboBox;

public class ComboBoxListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {

		boolean leap = false;
		Integer selectedItem = ((DateComboBox) e.getSource()).getDateComboBox().getSelectedItem();
		//selectedItem = (Integer) e.getSoucombo.getSelectedItem();
    	if(((DateComboBox) e.getSource()).getComboType().equals("years")) {
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
    		for(DateComboBox dateComboBox : DateComboBox.dateComboBoxes) {
    			if(dateComboBox.getComboType().equals("months")) {
    				selectedItem = (Integer) dateComboBox.getComboBox().getSelectedItem();
    				System.out.println(selectedItem);
    				if(selectedItem == 2) {
    					for(DateComboBox dateComboBox1 : DateComboBox.dateComboBoxes) {
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
    	} else if (((DateComboBox) e.getSource()).getComboType().equals("months")) {
    		// Postavljanje dana u mesecu
    		if (selectedItem == 2) {
    			for(DateComboBox dateComboBox : DateComboBox.dateComboBoxes) {
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
    			for(DateComboBox dateComboBox : DateComboBox.dateComboBoxes) {
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
    			for(DateComboBox dateComboBox : DateComboBox.dateComboBoxes) {
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
}
*/