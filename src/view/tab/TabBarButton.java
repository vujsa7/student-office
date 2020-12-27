package view.tab;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TabBarButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 966208169263131010L;
	private float alphaButton = 1f;
	private ImageIcon hoveredIcon;
	private ImageIcon normalIcon;
	private ImageIcon clickedIcon;
	private static Collection<TabBarButton> buttons = new ArrayList<TabBarButton>();
	private static String activeButton = "Studenti";
	private String buttonId;

	public TabBarButton(String text) {
		buttonId = text;
		if(text.equals("Studenti")){
			hoveredIcon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"student_button_hover.jpg"));
			normalIcon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"student_button.jpg"));
			clickedIcon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"student_button_clicked.jpg"));
		} else if(text.equals("Profesori")){
			hoveredIcon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"professor_button_hover.jpg"));
			normalIcon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"professor_button.jpg"));
			clickedIcon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"professor_button_clicked.jpg"));
		} else {
			hoveredIcon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"subject_button_hover.jpg"));
			normalIcon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"subject_button.jpg"));
			clickedIcon = getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"subject_button_clicked.jpg"));
		}
		if(activeButton == text) {
			setIcon(clickedIcon);
		} else {
			setIcon(normalIcon);
		}
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new MyMouseListener());
		add(this);
	}
	
	public static void add(TabBarButton obj){
        buttons.add(obj);
    }
	
	public static void updateAll(){
        for(TabBarButton obj : buttons){
            obj.updateView();
        }
    }
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(418, 70,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resizedImage);
		return icon;
	}
	
	public float getAlpha() {
		return alphaButton;
	}

	public void setAlpha(float alpha) {
		this.alphaButton = alpha;
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaButton));
		super.paintComponent(g2);
	}
	
	public class MyMouseListener extends MouseAdapter{
		
		public void mouseEntered(MouseEvent mouseEvent) {
			JButton thisButton = (JButton) mouseEvent.getComponent();
			if(!activeButton.equals(buttonId)) {
				thisButton.setIcon(hoveredIcon);
				new Thread(
						new Runnable() {
							public void run() {
								for (float i = 0.3f; i <= 1f; i += 0.07f){
									setAlpha(i);
									try {
										Thread.sleep(10);
									} catch (Exception e) {
										
									}
								}
							}
						}).start();
			}
			
		}
		
		public void mouseExited(MouseEvent mouseEvent) {
			JButton thisButton = (JButton) mouseEvent.getComponent();
			if(!activeButton.equals(buttonId)) {
				thisButton.setIcon(normalIcon);
				setAlpha(1f);
			}
		}
		
		public void mousePressed(MouseEvent mouseEvent) {
			activeButton = buttonId;
			updateAll();
		}
	}
	
	
	
	public void updateView() {
		if(activeButton == buttonId) {
			setIcon(clickedIcon);
		} else {
			setIcon(normalIcon);
			setAlpha(1f);
		}
	}
	
	public static String getActiveButton() {
		return activeButton;
	}

}
