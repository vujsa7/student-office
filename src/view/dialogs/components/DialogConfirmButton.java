package view.dialogs.components;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import controller.ProfessorController;
import view.dialogs.ProfessorDialog;
import view.table.TablePanel;

public class DialogConfirmButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1985136620379147442L;
	private float alphaButton = 1f;
	private ImageIcon hoveredConfirmIcon;
	private ImageIcon confirmIcon;
	private ProfessorDialog professorDialog;
	
	public DialogConfirmButton(ProfessorDialog dialog) {
		setEnabled(false);
		professorDialog = dialog;
		confirmIcon = getResizedIcon(new ImageIcon("assets/icons/potvrdi.png"));
		hoveredConfirmIcon = getResizedIcon(new ImageIcon("assets/icons/potvrdi_hovered.png"));
		setIcon(confirmIcon);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new MyMouseListener());

	}
	
	public ImageIcon getResizedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(92, 36,  java.awt.Image.SCALE_SMOOTH);
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
			if(thisButton.isEnabled()) {
				thisButton.setIcon(hoveredConfirmIcon);
				new Thread(
						new Runnable() {
							public void run() {
								for (float i = 0.75f; i <= 1f; i += 0.02f){
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
			if(thisButton.isEnabled()) {
				
				
				new Thread(
						new Runnable() {
							public void run() {
								for (float i = 1f; i >= 0.8f; i -= 0.07f){
									setAlpha(i);
									try {
										Thread.sleep(10);
									} catch (Exception e) {
										
									}
								}
								thisButton.setIcon(confirmIcon);
								setAlpha(1f);
							}
						}).start();
				
				
			}

		}
		
		public void mousePressed(MouseEvent mouseEvent) {
			if(isEnabled()) {
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
				ArrayList<JTextField> textFieldList = ProfessorDialog.list;
				Collection<CustomComboBox> comboBoxes = CustomComboBox.customComboBoxes;
				ArrayList<String> comboAnswers = new ArrayList<String>();
				for(CustomComboBox customComboBox : comboBoxes) {
					comboAnswers.add(customComboBox.getCustomComboBox().getField());
				}
				System.out.println(textFieldList.get(0).getText()+ textFieldList.get(1).getText()+
						DateComboBox.dateString+ textFieldList.get(2).getText()+ textFieldList.get(3).getText()+ textFieldList.get(4).getText()+
						textFieldList.get(5).getText()+ textFieldList.get(6).getText()+ comboAnswers.get(0)+ comboAnswers.get(1));
				
				ProfessorController.getInstance().dodajProfesora(textFieldList.get(0).getText(), textFieldList.get(1).getText(),
						"1", textFieldList.get(2).getText(), textFieldList.get(3).getText(), textFieldList.get(4).getText(),
						textFieldList.get(5).getText(), textFieldList.get(6).getText(),comboAnswers.get(0), comboAnswers.get(1));
					professorDialog.dispose();
				
				
			}
		}
	}


	public void resetIcon() {
		setIcon(confirmIcon);
	}
	
}