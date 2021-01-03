package controller;

import java.util.ArrayList;

import model.AbstractSubjectNotTeachedTable;
import model.Predmet;
import model.Profesor;
import view.dialogs.AddSubjectToProfessorDialog;
import view.dialogs.ProfessorEditDialog;

public class SubjectNotTeachedController {

	private static SubjectNotTeachedController instance = null;
	
	public static SubjectNotTeachedController getInstance() {
		if (instance == null) {
			instance = new SubjectNotTeachedController();
		}
		return instance;
	}
	
	private SubjectNotTeachedController() {}
	
	public void postaviPredmeteKojeProfesorNePredaje() {
		Profesor profesor = ProfessorController.getInstance().nabaviProfesoraSaLicnomKartom(ProfessorEditDialog.entityID);
		ArrayList<Predmet> subjectsOfProfessor = (ArrayList<Predmet>) profesor.getListaPredmeta();
		ArrayList<Predmet> allSubjects = (ArrayList<Predmet>) SubjectController.getInstance().nabaviSvePostojecePredmete();
		
		ArrayList<Predmet> notTeachedSubjects = new ArrayList<Predmet>();
		
		for(Predmet subject : allSubjects) {
			boolean shouldAdd = true;
			for(Predmet subjectOfProfessor : subjectsOfProfessor) {
				if(subjectOfProfessor.getSifraPredmeta() == subject.getSifraPredmeta()) {
					shouldAdd = false;
				}
			}
			if(shouldAdd) {
				notTeachedSubjects.add(subject);
			}
		}
		AbstractSubjectNotTeachedTable.getInstance().setSubjectsNotTeachedByProfessor(notTeachedSubjects);
		AddSubjectToProfessorDialog.getInstance().refreshView();
	}

	public String getSelectedSubjectID(int selectedSubjectRow) {
		Predmet predmet = AbstractSubjectNotTeachedTable.getInstance().getRow(selectedSubjectRow);
		return predmet.getSifraPredmeta();
	}

}
