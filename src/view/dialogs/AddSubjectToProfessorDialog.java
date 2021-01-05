package view.dialogs;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.ProfessorController;
import controller.SubjectController;
import model.AbstractSubjectNotTeachedTable;
import view.dialogs.components.DialogConfirmButton;
import view.dialogs.components.DialogDeclineButton;
import view.dialogs.tables.SubjectNotTeachedTable;

public class AddSubjectToProfessorDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 163674034029511187L;
	private static AddSubjectToProfessorDialog instance = null;
	
	public static AddSubjectToProfessorDialog getInstance() {	
			instance = new AddSubjectToProfessorDialog(ProfessorEditDialog.getInstance());
		return instance;
	}

	private SubjectNotTeachedTable subjectNotTeachedTable;
	private DialogConfirmButton dialogConfirmButton;
	private int selectedSubjectRow = -1;
	
	private AddSubjectToProfessorDialog(JDialog parent) {
		super(parent, "Dodaj predmet", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(580, 420));
		setResizable(false);
		pack();
		setLocationRelativeTo(parent);
		
		JPanel basePanel = new JPanel();
		BoxLayout box = new BoxLayout(basePanel, BoxLayout.Y_AXIS);
		basePanel.setLayout(box);
		
		basePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(0,1));
		titlePanel.setOpaque(false);
		JLabel title = new JLabel("Predmeti:");
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		layout.setHgap(0);
		title.setLayout(layout);
		titlePanel.add(title);
		basePanel.add(titlePanel);
		
		
		subjectNotTeachedTable = new SubjectNotTeachedTable();
		subjectNotTeachedTable.addMouseListener(new TableMouseListener());
		basePanel.add(Box.createVerticalStrut(10));
		JScrollPane scrollPane = new JScrollPane(subjectNotTeachedTable);
		
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
	
	public void refreshView() {
		AbstractSubjectNotTeachedTable model = (AbstractSubjectNotTeachedTable) subjectNotTeachedTable.getModel();
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
				ProfessorController.getInstance().dodajPredmetProfesoru(selectedSubjectRow);
				SubjectController.getInstance().dodajProfesoraPredmetu(selectedSubjectRow);
				dispose();
			}		
		}
	}
	
	class TableMouseListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent mouseEvent) {
				if(!subjectNotTeachedTable.getSelectionModel().isSelectionEmpty()) {
					selectedSubjectRow = subjectNotTeachedTable.convertRowIndexToModel(subjectNotTeachedTable.getSelectedRow());
					dialogConfirmButton.setEnabled(true);
				} else {
					selectedSubjectRow = -1;
					dialogConfirmButton.setEnabled(false);
				}
		}
	}

}
