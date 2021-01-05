package controller;

import java.util.List;

import model.AbstractAddProfesorToSubjectTable;
import model.AbstractProfessorTable;
import model.Profesor;
import view.dialogs.AddProfesorToSubjectEditDialog;

public class AddProfesorToSubjectController {
	
	private static AddProfesorToSubjectController instance = null;
	
	public static AddProfesorToSubjectController getInstance() {
		if (instance == null) {
			instance = new AddProfesorToSubjectController();
		}
		return instance;
	}
	
	public AddProfesorToSubjectController() {}
	
	public void dobaviSveProfesore() {
		List<Profesor> profesori = AbstractProfessorTable.getInstance().getProfessors();
		AbstractAddProfesorToSubjectTable.getInstance().setProfesori(profesori);
		AddProfesorToSubjectEditDialog.getInstance().refreshView();
	}
	
	public String getProfesor(int selectedRow) {
		Profesor profesor = AbstractAddProfesorToSubjectTable.getInstance().getRow(selectedRow);
		return profesor.getBrojLicneKarte();
	}
}
