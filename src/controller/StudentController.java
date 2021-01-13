package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.AbstractStudentTable;
import model.Ocena;
import model.Predmet;
import model.Student;
import view.dialogs.GradeEntryDialog;
import view.dialogs.StudentEditDialog;
import view.dialogs.components.studentedit.StudentUnsettledSubjectsTablePanel;
import view.dialogs.components.studentedit.StudentoviPolozeniIspitiTablePanel;
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
	
	public void obrisiStudenta(String selectedIndex) {
		List<Student> studenti = AbstractStudentTable.getInstance().getStudenti();
		
		if(!studenti.isEmpty()) {
			int row = 0;
			for(Student student : studenti) {
				if(student.getBrojIndeksa().equals(selectedIndex))
					break;
				
				row++;
			}
			
			AbstractStudentTable.getInstance().removeRow(row);
			TablePanel.getInstance().refreshView("student");
		}
	}
	
	public void pronadjiStudente(String prezime, String ime, String brojIndeksa) { //prvo se unosi prezime, pa ime, pa broj indeksa
		List<Student> trazeniStudenti = new ArrayList<Student>();
		List<Student> studenti = AbstractStudentTable.getInstance().getStudenti();
			
		if(ime == "-1") {
			for(Student student : studenti) {
				if(student.getPrezime().toUpperCase().contains(prezime.toUpperCase()))
					trazeniStudenti.add(student);
			}
		} else if(brojIndeksa == "-1") {
			for(Student student : studenti) {
				if(student.getPrezime().toUpperCase().contains(prezime.toUpperCase()) && student.getIme().toUpperCase().contains(ime.toUpperCase()))
					trazeniStudenti.add(student);
			}
		} else {
			for(Student student : studenti) {
				if(student.getPrezime().toUpperCase().contains(prezime.toUpperCase()) && student.getIme().toUpperCase().contains(ime.toUpperCase()) 
						&& student.getBrojIndeksa().toUpperCase().contains(brojIndeksa.toUpperCase()))
					trazeniStudenti.add(student);
			}
		}
		
		AbstractStudentTable.getInstance().setTrazeniStudenti(trazeniStudenti);
		TablePanel.getInstance().refreshView("student");
	}
	
	public void postaviDefaultStudente() {
		AbstractStudentTable.getInstance().setDefaultStudente();
		TablePanel.getInstance().refreshView("student");
	}
	
	public List<Ocena> pronadjiStudentovePolozeneIspite(String selectedIndex) {
		
		List<Student> studenti = AbstractStudentTable.getInstance().getStudenti();
		List<Ocena> ret = new ArrayList<Ocena>();
		
		if(!studenti.isEmpty()) {
			for(Student student : studenti) {
				if(student.getBrojIndeksa().equals(selectedIndex)) {
					return student.getPolozeniIspiti();
				}
			}
		}
		return ret;
	}

	public List<Predmet> nabaviNepolozenePredmeteStudenta(String brIndex) {
		List<Student> studenti = AbstractStudentTable.getInstance().getStudenti();
		List<Predmet> predmeti = new ArrayList<Predmet>();
		
		if(!studenti.isEmpty()) {
			for(Student student : studenti) {
				if(student.getBrojIndeksa().equals(brIndex)) {
					return student.getNepolozeniIspiti();
				}
			}
		} 
		return predmeti;
	}

	public boolean nekiStudentImaPolozenIspit(String sifraPredmeta) {
		return AbstractStudentTable.getInstance().nekiStudentImaPolozenIspit(sifraPredmeta);
	}

	public boolean nekiStudentImaNepolozenIspit(String sifraPredmeta) {
		return AbstractStudentTable.getInstance().nekiStudentImaNepolozenIspit(sifraPredmeta);
	}
	
	public Student pronadjiStudentaPrekoIndeksa(String brojIndeksa) {
		for(Student student : AbstractStudentTable.getInstance().getStudenti()) {
			if(student.getBrojIndeksa().equals(brojIndeksa))
				return student;
		}
		
		return null;
	}
	
	public void dodajPredmetUNepolozene(Predmet predmet) {
		Student student = pronadjiStudentaPrekoIndeksa(StudentEditDialog.stariIndeks);
		student.getNepolozeniIspiti().add(predmet);
		
		StudentUnsettledSubjectsTablePanel.getInstance().refreshView();
	}
	
	public void obrisiNepolozenIspit(String selectedPredmet) {
		String selectedStudent = TablePanel.getInstance().getSelectedEntityID();
		Student student = pronadjiStudentaPrekoIndeksa(selectedStudent);
		Predmet predmet = SubjectController.getInstance().nabaviPredmetSaSifrom(selectedPredmet);
		student.getNepolozeniIspiti().remove(predmet);
		
		StudentUnsettledSubjectsTablePanel.getInstance().refreshView();
	}
	
	public void izbrisiPredmetIzListeNepolozenih() {
		AbstractStudentTable.getInstance().izbrisiPredmetIzListeNepolozenih( StudentEditDialog.stariIndeks, StudentUnsettledSubjectsTablePanel.getSelectedSubject());
		StudentUnsettledSubjectsTablePanel.getInstance().refreshView();
	}

	public void dodajStudentuPredmetUListuPolozenih(int ocena, LocalDate localDate) {
		Predmet p = SubjectController.getInstance().nabaviPredmetSaSifrom(GradeEntryDialog.entityID);
		AbstractStudentTable.getInstance().dodajPredmetUListuPolozenih(StudentEditDialog.stariIndeks, p, ocena, localDate);
		StudentoviPolozeniIspitiTablePanel.getInstance().refreshView();
	}
	
	public void ponistiOcenu(String selectedIndex) {
		Student student = pronadjiStudentaPrekoIndeksa(StudentEditDialog.stariIndeks);
		List<Ocena> polozeniIspiti = student.getPolozeniIspiti();
		Ocena ocena = new Ocena();
		
		for(Ocena polozenIspit : polozeniIspiti) {
			if(polozenIspit.getPredmet().getSifraPredmeta().equals(selectedIndex)) {
				ocena = polozenIspit;
			}
		}
		polozeniIspiti.remove(ocena);	
		
		StudentoviPolozeniIspitiTablePanel.getInstance().refreshView();		
	}
	
}
