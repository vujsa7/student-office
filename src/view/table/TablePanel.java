package view.table;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.ProfessorController;
import controller.StudentController;
import controller.SubjectController;
import model.AbstractProfessorTable;
import model.AbstractStudentTable;
import model.AbstractSubjectTable;


public class TablePanel extends JPanel{
	
	private static TablePanel instance = null;
	
	public static TablePanel getInstance() {
		if (instance == null) {
			instance = new TablePanel();
		}
		return instance;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7261518674510605927L;
	public static final String STUDENT_PANEL = "Studenti";
	public static final String PROFESSOR_PANEL = "Profesori";
	public static final String SUBJECT_PANEL = "Predmeti";
	public static final String[] KEY_TEXTS = {STUDENT_PANEL, PROFESSOR_PANEL, SUBJECT_PANEL};
	private CardLayout cardlayout = new CardLayout();
	private JPanel cards = new JPanel(cardlayout);
	public static String currentlyOpenedTable = STUDENT_PANEL;
	public static int selectedStudentRow = -1;
	public static int selectedProfessorRow = -1;
	public static int selectedSubjectRow = -1;
	
	private JTable studentTable;
	private JTable professorTable;
	private JTable subjectTable;

	public TablePanel() {
		
		studentTable = new StudentTable();
		studentTable.addMouseListener(new MyMouseListener());
		JScrollPane studentScrollPane = new JScrollPane(studentTable);
		studentScrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		professorTable = new ProfessorTable();
		professorTable.addMouseListener(new MyMouseListener());
		JScrollPane professorScrollPane = new JScrollPane(professorTable);
		professorScrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		subjectTable = new SubjectTable();
		subjectTable.addMouseListener(new MyMouseListener());
		JScrollPane subjectScrollPanel = new JScrollPane(subjectTable);
		subjectScrollPanel.setBorder(BorderFactory.createEmptyBorder());
		
		cards.add(studentScrollPane, STUDENT_PANEL);
		cards.add(professorScrollPane, PROFESSOR_PANEL);
		cards.add(subjectScrollPanel, SUBJECT_PANEL);
		setLayout(new BorderLayout());
		add(cards, BorderLayout.CENTER);
	}
	
	public void swapView(String key) {
		   cardlayout.show(cards, key);
		   currentlyOpenedTable = key;
	}

	public void refreshView(String type) {
		if(type == "student") {
			AbstractStudentTable model = (AbstractStudentTable) studentTable.getModel();
			model.fireTableDataChanged();
			validate();
		}
		else if (type == "profesor"){
			AbstractProfessorTable model = (AbstractProfessorTable) professorTable.getModel();
			model.fireTableDataChanged();
			validate();
		} else {
			AbstractSubjectTable model = (AbstractSubjectTable) subjectTable.getModel();
			model.fireTableDataChanged();
			validate();
		}
	}
	
	public String getSelectedEntityID() {
		if(currentlyOpenedTable == PROFESSOR_PANEL) {
			if(selectedProfessorRow != -1) {
				return ProfessorController.getInstance().getSelectedProfessorID(selectedProfessorRow);
			} else {
				return "NO_SELECTION";
			}
		} else if(currentlyOpenedTable == STUDENT_PANEL) {
			if(selectedStudentRow != -1) 
				return StudentController.getInstance().getSelectedStudentIndeks(selectedStudentRow);
			else
				return "NO_SELECTION";
		} else {
			if(selectedSubjectRow != -1) {
				return SubjectController.getInstance().getSelectedSubjectID(selectedSubjectRow);
			} else {
				return "NO_SELECTION";
			}
		}
		
	}
	
	public void setSelectedEntityID(int i) {
		if(currentlyOpenedTable == PROFESSOR_PANEL) {
			selectedProfessorRow = -1;
		} else if(currentlyOpenedTable == STUDENT_PANEL) {
			selectedStudentRow = -1;
		} else {
			selectedSubjectRow = -1;
		}
	}
	
	class MyMouseListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent mouseEvent) {
			if(currentlyOpenedTable == PROFESSOR_PANEL) {
				if(!professorTable.getSelectionModel().isSelectionEmpty()) {
					selectedProfessorRow = professorTable.convertRowIndexToModel(professorTable.getSelectedRow());
				} else {
					selectedProfessorRow = -1;
				}
			} else if (currentlyOpenedTable == STUDENT_PANEL) {
				if(!studentTable.getSelectionModel().isSelectionEmpty()) {
					selectedStudentRow = studentTable.rowAtPoint(mouseEvent.getPoint());
				} else {
					selectedStudentRow = -1;
				}
			} else {
				if(!subjectTable.getSelectionModel().isSelectionEmpty()) {
					selectedSubjectRow = subjectTable.convertRowIndexToModel(subjectTable.getSelectedRow());
				} else {
					selectedSubjectRow = -1;
				}
				
			}
		}
	}

	

}
