package view.dialogs.components.addingsubject;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import controller.ProfessorController;
import controller.StudentController;
import controller.SubjectController;
import model.Profesor;
import view.dialogs.PredmetDialog;
import view.dialogs.PredmetEditDialog;
import view.table.TablePanel;

public class MinusButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3033747832062430631L;
	public float alpha = 0.7f;
	public static MinusButton instance = null;
	
	public static MinusButton getInstance() {
		if(instance == null) {
			instance = new MinusButton();
			return instance;
		}
		return instance;
	}
	

	private MinusButton() {
		setPreferredSize(new Dimension(36,36));
		setMaximumSize(new Dimension(36,36));
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setIcon(getResizedIcon(new ImageIcon("assets"+ File.separator +"icons"+ File.separator +"minus.png")));
		setEnabled(false);
		addMouseListener(new MyMouseListener());
	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(36, 36,  java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(resizedImage);
		return icon;
	}
	
	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
		this.repaint();
	}

	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		super.paintComponent(g2);
	}
	
	public class MyMouseListener extends MouseAdapter{
		public void mouseEntered(MouseEvent mouseEvent) {
			JButton button = (JButton) mouseEvent.getComponent();
			if(button.isEnabled()) {
				new Thread(
					new Runnable() {
						public void run() {
							for (float i = 0.7f; i <= 1f; i += 0.03f){
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
			JButton button = (JButton) mouseEvent.getComponent();
			if(button.isEnabled()) {
				new Thread(
						new Runnable() {
							public void run() {
								for (float i = 1f; i >= 0.7f; i -= 0.03f) {
									setAlpha(i);
									try{
										Thread.sleep(10);
									} catch (Exception e) {
										
									}
								}
							}
						}).start();
			}
		}
		public void mousePressed(MouseEvent mouseEvent) {
			JButton button = (JButton) mouseEvent.getComponent();
			if(button.isEnabled()) {
				new Thread(
						new Runnable() {
							public void run() {
								for (float i = 1f; i >= 0.7f; i -= 0.1f) {
									setAlpha(i);
									try {
										Thread.sleep(1);
									} catch (Exception e) {
										
									}
								}
							}
						}).start();
				String[] options = new String[2];
				options[0] = new String("Obriši");
				options[1] = new String("Poništi");
				int reply = JOptionPane.showOptionDialog(null, "Da li ste sigurni da želite da obrišete profesora sa ovog predmeta?", "Brisanje profesora", 0,
						JOptionPane.INFORMATION_MESSAGE, null, options, null);
				if(reply == 0) {
					ProfessorController.getInstance().obrisiPredmetSaProfesoraIzPredmetEditDialoga();
					SubjectController.getInstance().obrisiProfesoraSaPredmeta(PredmetEditDialog.staraSifra);
					
					PredmetEditDialog.getTextFieldForProfesorNameAndSurname().setText("");
					setEnabled(false);
					PredmetEditDialog.getInstance().canPlusBeVisible();
				}
				
			}
		}
	}

	

}
