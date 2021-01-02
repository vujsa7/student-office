package controller;

import model.AbstractProfessorHasSubjectsTable;
import model.Profesor;
import view.dialogs.ProfessorEditDialog;
import view.dialogs.components.professoredit.ProfessorHasSubjectsTablePanel;

public class ProfessorHasSubjectsController {

	private static ProfessorHasSubjectsController instance = null;
	
	public static ProfessorHasSubjectsController getInstance() {
		if (instance == null) {
			instance = new ProfessorHasSubjectsController();
		}
		return instance;
	}
	
	private ProfessorHasSubjectsController() {}

	public static void nabaviIPostaviPredmeteOdProfesora(String entityID) {
		Profesor profesor = ProfessorController.getInstance().nabaviProfesoraSaLicnomKartom(ProfessorEditDialog.entityID);	
		AbstractProfessorHasSubjectsTable.getInstance().setProfessorSubjects(profesor.getListaPredmeta());
		ProfessorHasSubjectsTablePanel.getInstance().refreshView();
	}

}
