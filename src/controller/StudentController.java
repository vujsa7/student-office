package controller;

import java.time.LocalDate;
import java.util.List;

import model.AbstractStudentTable;
import model.Student;
import view.dialogs.StudentEditDialog;
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
	
	public void izmeniStudenta(String stariIndeks, String ime, String prezime, LocalDate datumRodjenja, String adresaStanovanja, String kontaktTelefon,
			String emailAdresa, String brojIndeksa, String godUpisa, String trenutnaGodStudija, String status) {
		
		List<Student> listaStudenata = AbstractStudentTable.getInstance().getStudenti();
		for(Student student : listaStudenata) {
			if(student.getBrojIndeksa().equals(stariIndeks)) {
				student.setIme(ime);
				student.setPrezime(prezime);
				student.setDatumRodjenja(datumRodjenja);
				student.setAdresaStanovanja(adresaStanovanja);
				student.setBroj_telefona(kontaktTelefon);
				student.setBroj_indeksa(brojIndeksa);
				student.setEmail(emailAdresa);
				student.setGod_upisa(godUpisa);
				student.setTrenutnaGodStrudija(trenutnaGodStudija);
				student.setStatus(status);
			}
		}
		
		TablePanel.getInstance().refreshView("student");
	}
	
	public String getSelectedStudentIndeks(int row) { 
		Student student = AbstractStudentTable.getInstance().getRow(row);
		return student.getBrojIndeksa();
	}
	
	//provera prilikom dodavanja novog studenta
	public boolean proveriPostojanjeIndeksa(String indeks) {
		List<Student> studenti = AbstractStudentTable.getInstance().getStudenti();
		for(Student student : studenti) {
			if(student.getBrojIndeksa().equals(indeks)) 
				return true;	
		}
		
		return false;
	}
	
	public String getSelectedStudentValue(int index) {
		List<Student> studenti = AbstractStudentTable.getInstance().getStudenti();
		for(Student student : studenti) {
			if(student.getBrojIndeksa().equals(StudentEditDialog.stariIndeks)) {
				switch(index) {
				case 0:
					return student.getIme();
					
				case 1:
					return student.getPrezime();
					
				case 2:
					return student.getAdresaStanovanja();
					
				case 3:
					return student.getBroj_telefona();
					
				case 4:
					return student.getEmail();
					
				case 5:
					return student.getBrojIndeksa();
				
				case 6:
					return student.getGod_upisa();
					
				case 9:
					return student.getTrenutnaGodStudija();
					
				case 10:
					return student.getStatus();
					
				default:
					return null;
				}
			}
		}
		
		return null;
	}
	
	public LocalDate getSelectedStudentDateOfBirth() {
		for(Student student : AbstractStudentTable.getInstance().getStudenti()) {
			if(student.getBrojIndeksa().equals(StudentEditDialog.stariIndeks)) {
				return student.getDatumRodjenja();
			}
		}
		return null;
	}
	
	//provera prilikom izmene studenta
	public boolean postojiLiIndeks(String indeks) {
		List<Student> studenti = AbstractStudentTable.getInstance().getStudenti();
		for(Student student : studenti) {
			//System.out.println("NADJEN BR IDENKSA" + student.getBrojIndeksa());
			//System.out.println("PROSLEDJEN INDEKS" + indeks);
			if(student.getBrojIndeksa().equals(indeks) && !StudentEditDialog.stariIndeks.equals(indeks)) {
				return true;
			}  	
		}		
		
		return false;
	}
}
