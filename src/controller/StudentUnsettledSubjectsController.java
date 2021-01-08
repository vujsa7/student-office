package controller;

import java.util.ArrayList;

import model.AbstractStudentUnsettledSubjectsTable;
import model.Predmet;
import view.dialogs.GradeEntryDialog;
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

	private StudentUnsettledSubjectsController() {}
	
	public void postaviNepolozenePredmeteStudentu() {
		ArrayList<Predmet> unsettledSubjects = (ArrayList<Predmet>) StudentController.getInstance().nabaviNepolozenePredmeteStudenta(StudentEditDialog.stariIndeks);
		AbstractStudentUnsettledSubjectsTable.getInstance().setUnsettledSubjects(unsettledSubjects);
		StudentUnsettledSubjectsTablePanel.getInstance().refreshView();
	}
	
	public String getSelectedIspit(int selectedIspit) {
		Predmet ispit = AbstractStudentUnsettledSubjectsTable.getInstance().getRow(selectedIspit);
		return ispit.getSifraPredmeta();
	}
	
	public String getSelectedSubjectValueAt(int index) {
		Predmet p = SubjectController.getInstance().nabaviPredmetSaSifrom(GradeEntryDialog.entityID);
		switch(index) {
			case 0: return p.getSifraPredmeta();
			case 1: return p.getNazivPredmeta();
			default: return null;
		}
	}

}
