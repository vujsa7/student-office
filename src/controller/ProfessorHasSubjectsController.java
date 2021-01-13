package controller;

import java.util.List;

import model.AbstractProfessorHasSubjectsTable;
import model.AbstractProfessorTable;
import model.Predmet;
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

	public void nabaviIPostaviPredmeteOdProfesora() {
		List<Predmet> predmeti = AbstractProfessorTable.getInstance().nabaviProfesorovePredmete(ProfessorEditDialog.entityID);
		AbstractProfessorHasSubjectsTable.getInstance().setProfessorSubjects(predmeti);
		ProfessorHasSubjectsTablePanel.getInstance().refreshView();
	}
	
	public String getSelectedPredmet(int index) {
		Predmet predmet = AbstractProfessorHasSubjectsTable.getInstance().getRow(index);
		return predmet.getSifraPredmeta();
	}

}
