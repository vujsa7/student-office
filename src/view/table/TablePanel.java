package view.table;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
	public static final String GRADE_PANEL = "Predmeti";
	public static final String[] KEY_TEXTS = {STUDENT_PANEL, PROFESSOR_PANEL, GRADE_PANEL};
	private CardLayout cardlayout = new CardLayout();
	private JPanel cards = new JPanel(cardlayout);
	
	private JTable studentTable;
	private JTable professorTable;
	private JTable subjectTable;

	public TablePanel() {
		
		studentTable = new StudentTable();
		JScrollPane studentScrollPane = new JScrollPane(studentTable);
		studentScrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		professorTable = new ProfessorTable();
		JScrollPane professorScrollPane = new JScrollPane(professorTable);
		professorScrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		subjectTable = new SubjectTable();
		JScrollPane subjectScrollPanel = new JScrollPane(subjectTable);
		subjectScrollPanel.setBorder(BorderFactory.createEmptyBorder());
		
		cards.add(studentScrollPane, STUDENT_PANEL);
		cards.add(professorScrollPane, PROFESSOR_PANEL);
		cards.add(subjectScrollPanel, GRADE_PANEL);
		setLayout(new BorderLayout());
		add(cards, BorderLayout.CENTER);
	}
	
	public void swapView(String key) {
		   cardlayout.show(cards, key);
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

}
