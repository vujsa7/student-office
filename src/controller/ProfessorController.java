package controller;

import java.time.LocalDate;

import model.AbstractProfessorTable;
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
		TablePanel.getInstance().refreshView();

	}
}
