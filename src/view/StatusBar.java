package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;




public class StatusBar extends JPanel {
	
	public StatusBar(int panelWidth) {
		
		this.setBackground(new Color(179,179,179));
		BoxLayout boxStatus = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(boxStatus);
		Toolkit kit = Toolkit.getDefaultToolkit();
		this.setPreferredSize(new Dimension(panelWidth, 35));
		this.setMaximumSize(new Dimension(kit.getScreenSize().width, 35));
		
		JLabel ime = new JLabel("Studentska služba");
		
		SimpleDateFormat formatter= new SimpleDateFormat("HH:mm    dd.MM.yyyy.");
		
		Date date = new Date(System.currentTimeMillis());
		
		JLabel vreme = new JLabel(formatter.format(date));
		
		ime.setBackground(new Color(179,179,179));
		vreme.setBackground(new Color(179,179,179));
		
		this.add(ime);
		this.add(Box.createGlue());
		this.add(vreme);
		
		updateTime(vreme);
	}
	
	public void updateTime(JLabel vreme) {
		
		Thread t = new Thread() {
			
			public void run() {
				
				try {
					while(true) {
						SimpleDateFormat formatter= new SimpleDateFormat("HH:mm    dd.MM.yyyy.");
						Date date = new Date(System.currentTimeMillis());
						vreme.setText(formatter.format(date));
						
						Thread.sleep(1000);
					}	
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		};
		t.start();
		
	}
}

