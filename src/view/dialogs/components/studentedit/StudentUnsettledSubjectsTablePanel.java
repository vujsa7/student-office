package view.dialogs.components.studentedit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import model.AbstractStudentUnsettledSubjectsTable;
import view.dialogs.tables.StudentUnsettledSubjectsTable;

public class StudentUnsettledSubjectsTablePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7532628157505796755L;

	private static StudentUnsettledSubjectsTablePanel instance = null;
	
	public static StudentUnsettledSubjectsTablePanel getInstance() {
		if (instance == null) {
			instance = new StudentUnsettledSubjectsTablePanel();
		}
		return instance;
	}
	
	private JTable studentUnsettledSubjectsTable;
	private static int selectedSubject = -1;

	
	private StudentUnsettledSubjectsTablePanel(){
		setLayout(new GridLayout(0,1));
		
		JPanel basePanel = new JPanel();
		BoxLayout box = new BoxLayout(basePanel, BoxLayout.Y_AXIS);
		basePanel.setLayout(box);
		basePanel.setBorder(new EmptyBorder(40, 40, 40, 40));
		basePanel.setBackground(new Color(249, 249, 249));
		
		JPanel subjectButtonPanel = new JPanel();
		subjectButtonPanel.setPreferredSize(new Dimension(1000, 36));
		subjectButtonPanel.setMaximumSize(new Dimension(1000, 36));
		subjectButtonPanel.setOpaque(false);
		BoxLayout buttonHolder = new BoxLayout(subjectButtonPanel, BoxLayout.X_AXIS);
		subjectButtonPanel.setLayout(buttonHolder);
		
		AddUnsettledSubjectButton addUnsettledSubjectButton = new AddUnsettledSubjectButton();
		subjectButtonPanel.add(addUnsettledSubjectButton);
		
		subjectButtonPanel.add(Box.createHorizontalStrut(20));
		RemoveUnsettledSubjectButton removeUnsettledSubjectButton = new RemoveUnsettledSubjectButton();
		subjectButtonPanel.add(removeUnsettledSubjectButton);
		
		subjectButtonPanel.add(Box.createHorizontalStrut(20));
		TakeExamButton takeExamButton = new TakeExamButton();
		subjectButtonPanel.add(takeExamButton);
		
		studentUnsettledSubjectsTable = new StudentUnsettledSubjectsTable();
		studentUnsettledSubjectsTable.addMouseListener(new MyMouseListener());
		
		JScrollPane subjectForProfessorScrollPane = new JScrollPane(studentUnsettledSubjectsTable);
		subjectForProfessorScrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		basePanel.add(subjectButtonPanel);
		basePanel.add(Box.createVerticalStrut(20));
		basePanel.add(subjectForProfessorScrollPane);
		add(basePanel);
	}
	
	
	
	public static int getSelectedSubject() {
		return selectedSubject;
	}



	public static void setSelectedSubject(int selectedSubject) {
		StudentUnsettledSubjectsTablePanel.selectedSubject = selectedSubject;
	}



	public void refreshView() {
		AbstractStudentUnsettledSubjectsTable model = (AbstractStudentUnsettledSubjectsTable) studentUnsettledSubjectsTable.getModel();
		model.fireTableDataChanged();
		validate();
	}
	
	
	class MyMouseListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent mouseEvent) {
			if(!studentUnsettledSubjectsTable.getSelectionModel().isSelectionEmpty())
				selectedSubject = studentUnsettledSubjectsTable.convertRowIndexToModel(studentUnsettledSubjectsTable.getSelectedRow());
			else
				selectedSubject = -1;
		}
		
	}
}
