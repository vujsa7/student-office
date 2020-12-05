package main;


import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.MenuBar;
import view.StatusBar;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		
			    
			    
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Studentska služba");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		setSize(toolkit.getScreenSize().width*3/4, toolkit.getScreenSize().height*3/4);
		setLocationRelativeTo(null);
		Image titleBarLogo = toolkit.getImage("assets/icons/titleBarLogo.png");
		setIconImage(titleBarLogo);
		JPanel mainPanel = new JPanel();
		add(mainPanel);
		BoxLayout box = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
		mainPanel.setLayout(box);
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.black);
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.orange);
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		MenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6370020036393900504L;

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}

}