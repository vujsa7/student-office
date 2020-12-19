package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class MyToolBar extends JToolBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7607547575772558529L;

	public MyToolBar() {
		
		super(SwingConstants.HORIZONTAL);
		setFloatable(false);
		setBackground(new Color(90, 90, 90));
		setBorderPainted(false);
		BoxLayout box = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(box);
		
		HoverButton newBtn = new HoverButton("assets/icons/new_white.png", "New");
		KeyStroke keyNew = KeyStroke.getKeyStroke(KeyEvent.VK_1, Event.CTRL_MASK); 
		Action performNew = new AbstractAction("New") {  
		    public void actionPerformed(ActionEvent e) {     
		         new Thread(
							new Runnable() {
								public void run() {
										try {
											newBtn.setAlpha(1f);
											Thread.sleep(1000);
											newBtn.setAlpha(0.7f);
										} catch (Exception e) {
											
										}
									
								}
							}).start();
		         
		    }
		}; 
		newBtn.getActionMap().put("performNew", performNew);
		newBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyNew, "performNew"); 
		add(newBtn);
		
		HoverButton editBtn = new HoverButton("assets/icons/edit_white.png", "Edit");
		KeyStroke keyEdit = KeyStroke.getKeyStroke(KeyEvent.VK_2, Event.CTRL_MASK); 
		Action performEdit = new AbstractAction("Edit") {  
		    public void actionPerformed(ActionEvent e) {     
		         new Thread(
							new Runnable() {
								public void run() {
										try {
											editBtn.setAlpha(1f);
											Thread.sleep(1000);
											editBtn.setAlpha(0.7f);
										} catch (Exception e) {
											
										}
									
								}
							}).start();
		         
		    }
		}; 
		editBtn.getActionMap().put("performEdit", performEdit);
		editBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyEdit, "performEdit"); 
		add(editBtn);
		
		HoverButton deleteBtn = new HoverButton("assets/icons/delete_white1.png", "Delete");
		KeyStroke keyDelete = KeyStroke.getKeyStroke(KeyEvent.VK_3, Event.CTRL_MASK); 
		Action performDelete = new AbstractAction("Delete") {  
		    public void actionPerformed(ActionEvent e) {     
		         new Thread(
							new Runnable() {
								public void run() {
										try {
											deleteBtn.setAlpha(1f);
											Thread.sleep(1000);
											deleteBtn.setAlpha(0.7f);
										} catch (Exception e) {
											
										}
									
								}
							}).start();
		         
		    }
		}; 
		deleteBtn.getActionMap().put("performDelete", performDelete);
		deleteBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyDelete, "performDelete"); 
		add(deleteBtn);
		
		
		add(Box.createGlue());
		
		SearchIconPanel searchIconPanel = new SearchIconPanel();
		add(searchIconPanel);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setMaximumSize(new Dimension(214,25));
		searchPanel.setLayout(new GridLayout(0,1));
		searchPanel.add(new SearchBar());
		add(searchPanel);
		
		add(Box.createHorizontalStrut(12));
		
		SearchButton searchButton = new SearchButton();
		add(searchButton);
		
		add(Box.createHorizontalStrut(12));
	}
	
}
