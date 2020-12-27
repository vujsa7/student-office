package controller;

import java.time.LocalDate;

import model.AbstractStudentTable;
import view.table.TablePanel;

public class StudentController {
	
private static StudentController instance = null;
	
	public static StudentController getInstance() {
		if (instance == null) {
			instance = new StudentController();
		}
		return instance;
	}
	
	private StudentController() {}
	
	public void dodajStudenta(String ime, String prezime, LocalDate datumRodjenja, String adresaStanovanja, String kontaktTelefon,
			String emailAdresa, String brojIndeksa, String godUpisa, String trenutnaGodStudija, String status){
		
		AbstractStudentTable.getInstance().dodajStudenta(ime, prezime, datumRodjenja, adresaStanovanja, kontaktTelefon, emailAdresa,
				brojIndeksa, godUpisa, trenutnaGodStudija, status);
		TablePanel.getInstance().refreshView("student");

	}
}
