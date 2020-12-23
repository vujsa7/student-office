package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class TablePanel extends JPanel{

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

	public TablePanel() {
		
		JPanel studentPanel = new JPanel();
		studentPanel.setBackground(Color.black);
		JPanel professorPanel = new JPanel();
		professorPanel.setBackground(Color.yellow);
		JPanel gradePanel = new JPanel();
		gradePanel.setBackground(Color.pink);
		cards.add(studentPanel, STUDENT_PANEL);
		cards.add(professorPanel, PROFESSOR_PANEL);
		cards.add(gradePanel, GRADE_PANEL);
		setLayout(new BorderLayout());
		add(cards, BorderLayout.CENTER);
	}
	
	public void swapView(String key) {
		   cardlayout.show(cards, key);
		}

}
