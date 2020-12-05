package main;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import view.RightRootPane;
import view.TabPanel;

public class MainFrame extends JFrame {

	public MainFrame() {
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Studentska služba");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		setSize(toolkit.getScreenSize().width*3/4, toolkit.getScreenSize().height*3/4);
		setLocationRelativeTo(null);
		Image titleBarLogo = toolkit.getImage("assets/icons/titleBarLogo.png");
		setIconImage(titleBarLogo);
		
		TabPanel tabPanel = new TabPanel();
		RightRootPane rightRootPane = new RightRootPane();
	
		this.add(rightRootPane, BorderLayout.CENTER);
		this.add(tabPanel, BorderLayout.WEST);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6370020036393900504L;

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}

}