package controller;

import java.util.ArrayList;

import model.AbstractStudentUnsettledSubjectsTable;
import model.Predmet;
import view.dialogs.StudentEditDialog;
import view.dialogs.components.studentedit.StudentUnsettledSubjectsTablePanel;

public class StudentUnsettledSubjectsController {
	
	private static StudentUnsettledSubjectsController instance = null;
	
	public static StudentUnsettledSubjectsController getInstance(){
		if(instance == null) {
			return new StudentUnsettledSubjectsController();
		}
		return instance;
	}

	public StudentUnsettledSubjectsController() {}
	
	public void postaviNepolozenePredmeteStudentu() {
		ArrayList<Predmet> unsettledSubjects = (ArrayList<Predmet>) StudentController.getInstance().nabaviNepolozenePredmeteStudenta(StudentEditDialog.stariIndeks);
		AbstractStudentUnsettledSubjectsTable.getInstance().setUnsettledSubjects(unsettledSubjects);
		StudentUnsettledSubjectsTablePanel.getInstance().refreshView();
	}

}
