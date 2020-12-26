package view.table;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.AbstractProfessorTable;


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
	
	private JTable professorTable;

	public TablePanel() {
		
		StudentTable studentTable = new StudentTable();
		JScrollPane studentScrollPane = new JScrollPane(studentTable);
		studentScrollPane.setBorder(BorderFactory.createEmptyBorder());
		professorTable = new ProfessorTable();
		JScrollPane professorScrollPane = new JScrollPane(professorTable);
		professorScrollPane.setBorder(BorderFactory.createEmptyBorder());
		JPanel gradePanel = new JPanel();
		gradePanel.setBackground(Color.pink);
		gradePanel.setLayout(new GridLayout(0,1));
		cards.add(studentScrollPane, STUDENT_PANEL);
		cards.add(professorScrollPane, PROFESSOR_PANEL);
		cards.add(gradePanel, GRADE_PANEL);
		setLayout(new BorderLayout());
		add(cards, BorderLayout.CENTER);
	}
	
	public void swapView(String key) {
		   cardlayout.show(cards, key);
	}

	public void refreshView() {
		AbstractProfessorTable model = (AbstractProfessorTable) professorTable.getModel();
		model.fireTableDataChanged();
		validate();
	}

}
