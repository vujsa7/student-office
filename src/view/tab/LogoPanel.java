package view.tab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogoPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2808708266696395403L;

	LogoPanel(){
		setLayout(new GridLayout(0,1));
		setPreferredSize(new Dimension(418, 251));
		setMaximumSize(new Dimension(418, 251)); 
		setBackground(new Color(58,39,93)); 
		
		JPanel basePanel = new JPanel();
		BoxLayout box = new BoxLayout(basePanel, BoxLayout.Y_AXIS);
		basePanel.setLayout(box);
		basePanel.setOpaque(false);
		
		JPanel topInnerPanel = new JPanel();
		topInnerPanel.setBackground(new Color(58,39,93)); 
		basePanel.add(topInnerPanel);
		
		JPanel bottomInnerPanel = new JPanel();
		bottomInnerPanel.setOpaque(false);
		bottomInnerPanel.setLayout(new GridLayout(0,1));
		bottomInnerPanel.setPreferredSize(new Dimension(418, 160));
		bottomInnerPanel.setMaximumSize(new Dimension(418, 160));
		
		JPanel bottomInnerBasePanel = new JPanel();
		bottomInnerBasePanel.setOpaque(false);
		BoxLayout box2 = new BoxLayout(bottomInnerBasePanel, BoxLayout.Y_AXIS);
		bottomInnerBasePanel.setLayout(box2);
		
		
		JPanel topLogoPanel = new JPanel();
		topLogoPanel.setOpaque(false);
		topLogoPanel.setLayout(new GridLayout(0,1));
		topLogoPanel.setPreferredSize(new Dimension(418, 100));
		topLogoPanel.setMaximumSize(new Dimension(418, 100));
		
		JPanel bottomLogoPanel = new JPanel();
		bottomLogoPanel.setBackground(new Color(58,39,93));
		
		
		JPanel topLogoInnerPanel = new JPanel();
		BoxLayout box3 = new BoxLayout(topLogoInnerPanel, BoxLayout.X_AXIS);
		topLogoInnerPanel.setLayout(box3);
		topLogoInnerPanel.setOpaque(false);
		
		ImagePanel imgPanel = new ImagePanel();
		topLogoInnerPanel.add(Box.createHorizontalStrut(47));
		topLogoInnerPanel.add(imgPanel);
		topLogoInnerPanel.add(Box.createHorizontalStrut(14));
		TextLabel textLabel = new TextLabel();
		topLogoInnerPanel.add(textLabel);
		topLogoInnerPanel.add(Box.createGlue());
		topLogoPanel.add(topLogoInnerPanel);
		
		bottomInnerBasePanel.add(topLogoPanel);
		bottomInnerBasePanel.add(bottomLogoPanel);
		
		bottomInnerPanel.add(bottomInnerBasePanel);
		basePanel.add(bottomInnerPanel);
		
		add(basePanel);
	}
	
	public class ImagePanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5198136480838864634L;
		private Image image;
		
		public ImagePanel() {
			ImageIcon icon = getResizedIcon(new ImageIcon("assets/icons/logo1.png"));
			image = icon.getImage();
			FlowLayout layout = (FlowLayout)this.getLayout();
			setPreferredSize(new Dimension(100,100));
			layout.setVgap(0);
			setOpaque(false);
		}
		
		protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this);        
	    }
		
		
		public ImageIcon getResizedIcon(ImageIcon icon) {
			Image image = icon.getImage();
			Image resizedImage = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(resizedImage);
			return icon;
		}
		
	}
	
	public class TextLabel extends JLabel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1982853620888094915L;
		
		public TextLabel() {
			setText("<html>Studentska<br/>Služba</html>");
			setPreferredSize(new Dimension(207, 106));
			setForeground(Color.white);
			File font_file = new File("assets/fonts/PlayfairDisplay-Black.ttf");
			Font font;
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, font_file);
				Font sizedFont = font.deriveFont(40f);
				setFont(sizedFont);
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	
		
	}
	
	
	
	
}
