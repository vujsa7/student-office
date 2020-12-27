package view.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.MainFrame;
import view.dialogs.ProfessorDialog;
import view.dialogs.StudentDialog;
import view.tab.TabBarButton;

public class ToolBar extends JToolBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7607547575772558529L;

	public ToolBar() {
		super(SwingConstants.HORIZONTAL);
		setFloatable(false);
		setBackground(new Color(90, 90, 90));
		setBorderPainted(false);
		BoxLayout box = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(box);
		HoverButton newBtn = new HoverButton("assets"+ File.separator +"icons"+ File.separator +"new_white.png", "New");
		KeyStroke keyNew = KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK); 
		Action performNew = new AbstractAction("New") {  
		    /**
			 * 
			 */
			private static final long serialVersionUID = -1236190707680017271L;

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
		         if(TabBarButton.getActiveButton() == "Studenti") {
		        	 try {
						StudentDialog studentDialog = new StudentDialog(MainFrame.getInstance());
						studentDialog.setVisible(true);
					} catch (FontFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	 
		         } else if(TabBarButton.getActiveButton() == "Profesori") {
		        	 try {
						ProfessorDialog professorDialog = new ProfessorDialog(MainFrame.getInstance());
						professorDialog.setVisible(true);
					} catch (FontFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		         } else {
		        	 
		         }
		    }
		}; 
		newBtn.getActionMap().put("performNew", performNew);
		newBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyNew, "performNew"); 
		add(newBtn);
		
		HoverButton editBtn = new HoverButton("assets"+ File.separator +"icons"+ File.separator +"edit_white.png", "Edit");
		KeyStroke keyEdit = KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK); 
		Action performEdit = new AbstractAction("Edit") {  
		    /**
			 * 
			 */
			private static final long serialVersionUID = -4039649347818021451L;

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
		
		HoverButton deleteBtn = new HoverButton("assets"+ File.separator +"icons"+ File.separator +"delete_white1.png", "Delete");
		KeyStroke keyDelete = KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK); 
		Action performDelete = new AbstractAction("Delete") {  
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1384798606031478310L;

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
		
		SearchBarTextField searchBarTextField = new SearchBarTextField();
		
		JTextField searchTextField = new JTextField();
		searchTextField.setBorder(new EmptyBorder(0,0,0,10));
		searchTextField.setMinimumSize(new Dimension(170,35));
		searchTextField.setOpaque(false);
		SearchButton searchButton = new SearchButton();
		searchTextField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    changed();
			  }

			  public void changed() {
			     if (searchTextField.getText().equals("")){
			       searchButton.setEnabled(false);
			       searchButton.setForeground(Color.black);
			       searchButton.resetIcon();
			     }
			     else {
			       searchButton.setEnabled(true);
			    }

			  }
			});
		
		searchBarTextField.add(searchTextField);
		add(searchBarTextField);
		
		add(Box.createHorizontalStrut(12));
		add(searchButton);
		
		add(Box.createHorizontalStrut(12));
	}
	
}
