package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.AbstractProfessorTable;
import model.Profesor;
import view.table.TablePanel;

public class ProfessorController {

	private static ProfessorController instance = null;
	
	public static ProfessorController getInstance() {
		if (instance == null) {
			instance = new ProfessorController();
		}
		return instance;
	}
	
	private ProfessorController() {}
	
	public void dodajProfesora(String ime, String prezime, LocalDate datumRodjenja, String adresaStanovanja, String kontaktTelefon,
			String emailAdresa, String adresaKancelarije, String brojLicneKarte, String titula, String zvanje){
		
		AbstractProfessorTable.getInstance().dodajProfesora(ime, prezime, datumRodjenja, adresaStanovanja, kontaktTelefon, emailAdresa,
				adresaKancelarije, brojLicneKarte, titula, zvanje);
		TablePanel.getInstance().refreshView("profesor");

	}

	public boolean checkIDExists(String text) {
		ArrayList<Profesor> professors = (ArrayList<Profesor>) AbstractProfessorTable.getInstance().getProfessors();
		for(Profesor professor : professors) {
			if(professor.getBrojLicneKarte().equals(text)) {
				return true;
			}
		}
		return false;
	}
}
