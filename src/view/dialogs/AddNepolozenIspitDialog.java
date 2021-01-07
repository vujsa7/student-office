package view.dialogs;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.AddNepolozenIspitController;
import controller.StudentController;
import controller.SubjectController;
import model.AbstractAddNepolozeniIspitiTable;
import model.Predmet;
import view.dialogs.components.DialogConfirmButton;
import view.dialogs.components.DialogDeclineButton;
import view.dialogs.tables.AddNepolozenIspitTable;

public class AddNepolozenIspitDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -360701514509775453L;
	
	private static AddNepolozenIspitDialog instance = null;
	
	public static AddNepolozenIspitDialog getInstance() {
			instance = new AddNepolozenIspitDialog(StudentEditDialog.getInstance());
		
		return instance;
	}
	
	private AddNepolozenIspitTable nepolozeniIspiti;
	private DialogConfirmButton dialogConfirmButton;
	private int selectedPredmet = -1;
	
	private AddNepolozenIspitDialog(JDialog parent) {
		super(parent, "Dodavanje predmeta", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(580, 420));
		setResizable(false);
		pack();
		setLocationRelativeTo(parent);
		
		JPanel basePanel = new JPanel();
		BoxLayout box = new BoxLayout(basePanel, BoxLayout.Y_AXIS);
		basePanel.setLayout(box);
		
		basePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		nepolozeniIspiti = new AddNepolozenIspitTable();
		nepolozeniIspiti.addMouseListener(new TableMouseListener());
		basePanel.add(Box.createVerticalStrut(10));
		JScrollPane scrollPane = new JScrollPane(nepolozeniIspiti);
		basePanel.add(scrollPane);
		basePanel.add(Box.createVerticalStrut(10));
		
		JPanel buttonHolderPanel = new JPanel();
		buttonHolderPanel.setPreferredSize(new Dimension(580, 36));
		buttonHolderPanel.setOpaque(false);
		BoxLayout buttonHolder = new BoxLayout(buttonHolderPanel, BoxLayout.X_AXIS);
		buttonHolderPanel.setLayout(buttonHolder);
		
		dialogConfirmButton = new DialogConfirmButton();
		dialogConfirmButton.addMouseListener(new MyMouseListener());
		
		DialogDeclineButton dialogDeclineButton = new DialogDeclineButton();
		dialogDeclineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		buttonHolderPanel.add(Box.createHorizontalStrut(306));
		buttonHolderPanel.add(dialogDeclineButton);
		buttonHolderPanel.add(Box.createHorizontalStrut(30));
		buttonHolderPanel.add(dialogConfirmButton);
		
		basePanel.add(buttonHolderPanel);
		basePanel.add(Box.createVerticalStrut(10));
		add(basePanel);
	}
	
	public int getSelectedIspit() {
		return selectedPredmet;
	}


	public void setSelectedIspit(int selectedPredmet) {
		this.selectedPredmet = selectedPredmet;
	}
	

	public void refreshView() {
		AbstractAddNepolozeniIspitiTable model = (AbstractAddNepolozeniIspitiTable) nepolozeniIspiti.getModel();
		model.fireTableDataChanged();
		validate();
	}
	
	private class MyMouseListener extends MouseAdapter{
		public void mouseEntered(MouseEvent mouseEvent) {
			JButton thisButton = (JButton) mouseEvent.getComponent();
			if(thisButton.isEnabled()) {
				thisButton.setIcon(dialogConfirmButton.hoveredConfirmIcon);
				new Thread(
						new Runnable() {
							public void run() {
								for (float i = 0.75f; i <= 1f; i += 0.02f){
									dialogConfirmButton.setAlpha(i);
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
									dialogConfirmButton.setAlpha(i);
									try {
										Thread.sleep(10);
									} catch (Exception e) {
										
									}
								}
								thisButton.setIcon(dialogConfirmButton.confirmIcon);
								dialogConfirmButton.setAlpha(1f);
							}
						}).start();
			}
		}
		public void mousePressed(MouseEvent mouseEvent) {
			JButton thisButton = (JButton) mouseEvent.getComponent();
			if(thisButton.isEnabled()) {
				String sifraPredmeta = AddNepolozenIspitController.getInstance().getSifraPredmeta(selectedPredmet);
				Predmet predmet = SubjectController.getInstance().nabaviPredmetSaSifrom(sifraPredmeta);
				StudentController.getInstance().dodajPredmetUNepolozene(predmet);
				dispose();
				
			}		
		}
	}
	
	class TableMouseListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent mouseEvent) {
			if(!nepolozeniIspiti.getSelectionModel().isSelectionEmpty()) {
				selectedPredmet = nepolozeniIspiti.convertRowIndexToModel(nepolozeniIspiti.getSelectedRow());
				dialogConfirmButton.setEnabled(true);
			} else {
				selectedPredmet = -1;
				dialogConfirmButton.setEnabled(false);
			}
		}
	}
}
