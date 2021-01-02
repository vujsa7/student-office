package view.dialogs.components.professoredit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import model.AbstractProfessorHasSubjectsTable;
import view.dialogs.tables.ProfessorHasSubjectsTable;

public class ProfessorHasSubjectsTablePanel extends JPanel {
	
	private static ProfessorHasSubjectsTablePanel instance = null;
	
	public static ProfessorHasSubjectsTablePanel getInstance() {
		if (instance == null) {
			instance = new ProfessorHasSubjectsTablePanel();
		}
		return instance;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3085780295579350465L;
	private JTable professorHasSubjectsTable;
	
	public ProfessorHasSubjectsTablePanel() {
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
		
		SubjectAddButton addSubjectButton = new SubjectAddButton();
		subjectButtonPanel.add(addSubjectButton);
		
		subjectButtonPanel.add(Box.createHorizontalStrut(20));
		SubjectRemoveButton removeSubjectButton = new SubjectRemoveButton();
		subjectButtonPanel.add(removeSubjectButton);
		
		professorHasSubjectsTable = new ProfessorHasSubjectsTable();
		
		JScrollPane subjectForProfessorScrollPane = new JScrollPane(professorHasSubjectsTable);
		subjectForProfessorScrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		basePanel.add(subjectButtonPanel);
		basePanel.add(Box.createVerticalStrut(20));
		basePanel.add(subjectForProfessorScrollPane);
		add(basePanel);
	}
	
	public void refreshView() {
		AbstractProfessorHasSubjectsTable model = (AbstractProfessorHasSubjectsTable) professorHasSubjectsTable.getModel();
		model.fireTableDataChanged();
		validate();
	}
	
	
	
	
}
