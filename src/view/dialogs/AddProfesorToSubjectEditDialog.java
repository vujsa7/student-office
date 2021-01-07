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

import controller.AddProfesorToSubjectController;

import controller.ProfessorController;

import controller.SubjectController;
import model.AbstractAddProfesorToSubjectTable;
import view.dialogs.components.DialogConfirmButton;
import view.dialogs.components.DialogDeclineButton;
import view.dialogs.tables.AddProfesorToSubjectTable;


public class AddProfesorToSubjectEditDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1497365723049935614L;
	private static AddProfesorToSubjectEditDialog instance = null;
	
	public static AddProfesorToSubjectEditDialog getInstance() {
		if(instance == null)
			instance = new AddProfesorToSubjectEditDialog(PredmetEditDialog.getInstance());
		
		return instance;
	}
	
	private AddProfesorToSubjectTable profesoriUSistemu;
	private DialogConfirmButton dialogConfirmButton;
	private int selectedProf = -1;
	
	public AddProfesorToSubjectEditDialog(JDialog parent) {
		super(parent, "Odaberi profesora", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(580, 420));
		setResizable(false);
		pack();
		setLocationRelativeTo(parent);
		
		JPanel basePanel = new JPanel();
		BoxLayout box = new BoxLayout(basePanel, BoxLayout.Y_AXIS);
		basePanel.setLayout(box);
		
		basePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		profesoriUSistemu = new AddProfesorToSubjectTable();
		profesoriUSistemu.addMouseListener(new TableMouseListener());
		basePanel.add(Box.createVerticalStrut(10));
		JScrollPane scrollPane = new JScrollPane(profesoriUSistemu);
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
	

	
	
	public int getSelectedProf() {
		return selectedProf;
	}



	public void setSelectedProf(int selectedProf) {
		this.selectedProf = selectedProf;
	}



	public void refreshView() {
		AbstractAddProfesorToSubjectTable model = (AbstractAddProfesorToSubjectTable) profesoriUSistemu.getModel();
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
				SubjectController.getInstance().izmeniProfesora(selectedProf);
				AddProfesorToSubjectController.getInstance().dobaviSveProfesore();
				PredmetEditDialog.textFieldForProfesorNameAndSurname.setText(SubjectController.getInstance().vratiImeIPrezimeProfesora(selectedProf));
				PredmetEditDialog.plusBtn.setEnabled(false);

				ProfessorController.getInstance().dodajProfesoruPredmet(selectedProf);
				
				dispose();
			}		
		}
	}
	
	class TableMouseListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent mouseEvent) {
			if(!profesoriUSistemu.getSelectionModel().isSelectionEmpty()) {
				selectedProf = profesoriUSistemu.convertRowIndexToModel(profesoriUSistemu.getSelectedRow());
				dialogConfirmButton.setEnabled(true);
			} else {
				selectedProf = -1;
				dialogConfirmButton.setEnabled(false);
			}
		}
	}
}
