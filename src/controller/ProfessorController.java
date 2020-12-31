package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.AbstractProfessorTable;
import model.Profesor;
import view.dialogs.ProfessorEditDialog;
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
		ime = ime.substring(0, 1).toUpperCase() + ime.substring(1);
		prezime = prezime.substring(0, 1).toUpperCase() + prezime.substring(1);
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

	public void izmeniProfesora(String brojStareLicneKarte, String ime, String prezime, LocalDate datumRodjenja, String adresaStanovanja, String kontaktTelefon,
			String emailAdresa, String adresaKancelarije, String brojLicneKarte, String titula, String zvanje) {
		ArrayList<Profesor> professors = (ArrayList<Profesor>) AbstractProfessorTable.getInstance().getProfessors();
		ime = ime.substring(0, 1).toUpperCase() + ime.substring(1);
		prezime = prezime.substring(0, 1).toUpperCase() + prezime.substring(1);
		for(Profesor professor : professors) {
			if(professor.getBrojLicneKarte().equals(brojStareLicneKarte)) {
				professor.setIme(ime);
				professor.setPrezime(prezime);
				professor.setDatumRodjenja(datumRodjenja);
				professor.setAdresaStanovanja(adresaStanovanja);
				professor.setKontaktTelefon(kontaktTelefon);
				professor.setEmailAdresa(emailAdresa);
				professor.setAdresaKancelarije(adresaKancelarije);
				professor.setBrojLicneKarte(brojLicneKarte);
				professor.setTitula(titula);
				professor.setZvanje(zvanje);
			}
		}
		TablePanel.getInstance().refreshView("profesor");
	}
	
	public String getSelectedProfessorID(int rowIndex) {
		Profesor profesor = AbstractProfessorTable.getInstance().getRow(rowIndex);
		return profesor.getBrojLicneKarte();
	}

	public String getSelectedProfessorValueAt(int index) {
		ArrayList<Profesor> professors = (ArrayList<Profesor>) AbstractProfessorTable.getInstance().getProfessors();
		for(Profesor professor : professors) {
			if(professor.getBrojLicneKarte().equals(ProfessorEditDialog.entityID)) {
				switch(index) {
					case 0: return professor.getIme();
					case 1: return professor.getPrezime();
					case 2: return professor.getAdresaStanovanja();
					case 3: return professor.getKontaktTelefon();
					case 4: return professor.getEmailAdresa();
					case 5: return professor.getAdresaKancelarije();
					case 6: return professor.getBrojLicneKarte();
					case 9: return professor.getTitula();
					case 10: return professor.getZvanje();
					default: return null;
				}
			}
		}
		return null;
	}

	public LocalDate getSelectedProfessorDateOfBirth() {
		for(Profesor professor : AbstractProfessorTable.getInstance().getProfessors()) {
			if(professor.getBrojLicneKarte().equals(ProfessorEditDialog.entityID)) {
				return professor.getDatumRodjenja();
			}
		}
		return null;
	}
	
	/**
	* Vraca true ukoliko profesor sa tim brojem licne karte vec postoji, false ako profesor sa tim brojem licne ne postoji ili ako se prosledjeni profesor vec menja
	*/
	public boolean checkIDExistsWhenEdit(String text) {
		ArrayList<Profesor> professors = (ArrayList<Profesor>) AbstractProfessorTable.getInstance().getProfessors();
		for(Profesor professor : professors) {
			if(professor.getBrojLicneKarte().equals(text) && !ProfessorEditDialog.entityID.equals(text)) {
				return true;
			}
		}
		return false;
	}

	public void pronadjiProfesore(String prezime, String ime, String brojLicneKarte) {
		ArrayList<Profesor> searchedProfessors = new ArrayList<Profesor>();
		ArrayList<Profesor> professors = (ArrayList<Profesor>) AbstractProfessorTable.getInstance().getProfessors();
		if(ime == "-1") {
			for(Profesor professor : professors) {
				if(professor.getPrezime().toLowerCase().contains(prezime.toLowerCase())) {
					searchedProfessors.add(professor);
				}
			}
		} else if (brojLicneKarte == "-1") {
			for(Profesor professor : professors) {
				if(professor.getPrezime().toLowerCase().contains(prezime.toLowerCase()) && professor.getIme().toLowerCase().contains(ime.toLowerCase())) {
					searchedProfessors.add(professor);
				}
			}
		} else {
			for(Profesor professor : professors) {
				if(professor.getPrezime().toLowerCase().contains(prezime.toLowerCase()) && professor.getIme().toLowerCase().contains(ime.toLowerCase()) && professor.getBrojLicneKarte().toLowerCase().contains(brojLicneKarte.toLowerCase())) {
					searchedProfessors.add(professor);
				}
			}
		}
		AbstractProfessorTable.getInstance().setSearchedProfessors(searchedProfessors);
		TablePanel.getInstance().refreshView("profesor");
	}
	
	public void vratiDefaultProfesore() {
		AbstractProfessorTable.getInstance().setDefaultProfessors();
		TablePanel.getInstance().refreshView("profesor");
	}
}
