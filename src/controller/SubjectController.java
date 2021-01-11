package controller;

import java.util.ArrayList;
import java.util.List;

import model.AbstractStudentTable;
import model.AbstractStudentoviPolozeniIspitiTable;
import model.AbstractSubjectTable;
import model.Ocena;
import model.Predmet;
import model.Profesor;
import model.Student;
import view.dialogs.PredmetEditDialog;
import view.dialogs.ProfessorEditDialog;
import view.dialogs.components.studentedit.StudentoviPolozeniIspitiTablePanel;
import view.table.TablePanel;


public class SubjectController {
	
	private static SubjectController instance = null;
	
	public static SubjectController getInstance() {
		if (instance == null) {
			instance = new SubjectController();
		}
		return instance;
	}
	
	private SubjectController() {}
	
	public String getSelectedSubjectID(int rowIndex) {
		Predmet predmet = AbstractSubjectTable.getInstance().getRow(rowIndex);
		return predmet.getSifraPredmeta();
	}

	public void obrisiPredmet(String selectedEntityID) {
		ArrayList<Predmet> predmeti = (ArrayList<Predmet>) AbstractSubjectTable.getInstance().getSubjects();
		if(!predmeti.isEmpty()) {
			int i = 0;
			for(Predmet predmet : predmeti) {
				if(predmet.getSifraPredmeta() == selectedEntityID) {
					break;
				}
				i++;
			}
			AbstractSubjectTable.getInstance().removeRow(i);
			TablePanel.getInstance().refreshView("predmet");
		}
		
	}
	
	public void pronadjiPredmete(String ime1, String ime2, String ime3) {
		ArrayList<Predmet> searchedSubjects = new ArrayList<Predmet>();
		ArrayList<Predmet> subjects = (ArrayList<Predmet>) AbstractSubjectTable.getInstance().getSubjects();
		if(ime2 == "-1") {
			for(Predmet subject : subjects) {
				if(subject.getNazivPredmeta().toLowerCase().contains(ime1.toLowerCase())) {
					searchedSubjects.add(subject);
				}
			}
		} else if(ime3 == "-1"){
			for (Predmet subject : subjects) {
				String[] parts = subject.getNazivPredmeta().toLowerCase().split(" ");
				if(parts.length == 2) {
					if(parts[0].contains(ime1.toLowerCase()) && parts[1].contains(ime2.toLowerCase())) {
						searchedSubjects.add(subject);
					}
				}
			}
		} else {
			for(Predmet subject : subjects) {
				String[] parts = subject.getNazivPredmeta().toLowerCase().split(" ");
				if(parts.length == 3) {
					if(parts[0].contains(ime1.toLowerCase()) && parts[1].contains(ime2.toLowerCase()) && parts[2].contains(ime3.toLowerCase())) {
						searchedSubjects.add(subject);
					}
				}
			}
		}
		AbstractSubjectTable.getInstance().setSearchedSubjects(searchedSubjects);
		TablePanel.getInstance().refreshView("predmet");
	}

	public void vratiDefaultPredmete() {
		AbstractSubjectTable.getInstance().setDefaultSubjects();
		TablePanel.getInstance().refreshView("predmet");
	}

	public void dodajPredmet(String sifra, String naziv, int godina, Predmet.TipSemestra semestar, int espb){
		
		AbstractSubjectTable.getInstance().dodajPredmet(sifra, naziv, godina, semestar, espb);
		TablePanel.getInstance().refreshView("predmet");
	} 
	
	public void izmeniPredmet(String staraSifra, String sifra, String naziv, int godina, Predmet.TipSemestra semestar, int espb, Profesor profesor){
		
		List<Predmet> listaPredmeta = AbstractSubjectTable.getInstance().getSubjects();
		for(Predmet predmet : listaPredmeta) {
			if(predmet.getSifraPredmeta().equals(staraSifra)) {
				predmet.setSifraPredmeta(sifra);
				predmet.setNazivPredmeta(naziv);
				predmet.setGodinaStudija(godina);
				predmet.setSemestar(semestar);
				predmet.setBrojESPB(espb);
				predmet.setPredmetniProfesor(profesor);
			}
		}
		
		TablePanel.getInstance().refreshView("predmet");
	} 
    
    public boolean proveriPostojanjeSifre(String sifra) {
		List<Predmet> predmeti = AbstractSubjectTable.getInstance().getSubjects();
		for(Predmet predmet : predmeti) {
			if(predmet.getSifraPredmeta().equals(sifra))
				return true;
		}
		
		return false;
	}
    
    public String getSelectedPredmetValue(int index) {
		List<Predmet> predmeti = AbstractSubjectTable.getInstance().getSubjects();
		for(Predmet predmet : predmeti) {
			if(predmet.getSifraPredmeta().equals(PredmetEditDialog.staraSifra)) {
				switch(index) {
				case 0:
					return predmet.getSifraPredmeta();
					
				case 1:
					return predmet.getNazivPredmeta();
					
				case 2:
					if(predmet.getSemestar() == Predmet.TipSemestra.LETNJI)
						return "LETNJI";
					else
						return "ZIMSKI";
					
				case 3:
					return String.valueOf(predmet.getGodinaStudija());
					
				case 4:
					return String.valueOf(predmet.getBrojESPB());
					
				case 5:
					if(predmet.getPredmetniProfesor() != null) 
						return (predmet.getPredmetniProfesor().getIme() + " " + predmet.getPredmetniProfesor().getPrezime());
						
					return "";
					
					
				default:
					return null;
				}
			}
		}
		
		return null;
	}
    
   
    public boolean postojiLiSifra(String sifra) {
		List<Predmet> predmeti = AbstractSubjectTable.getInstance().getSubjects();
		for(Predmet predmet : predmeti) {
			if(predmet.getSifraPredmeta().equals(sifra) && !PredmetEditDialog.staraSifra.equals(sifra)) {
				return true;
			}  	
		}		
		
		return false;
	}
    
    public List<Predmet> nabaviSvePostojecePredmete() {
		return AbstractSubjectTable.getInstance().getSubjects();
	}

	public Predmet nabaviPredmetSaSifrom(String subjectID) {
		ArrayList<Predmet> list = (ArrayList<Predmet>) AbstractSubjectTable.getInstance().getSubjects();
		for(Predmet predmet : list) {
			if(predmet.getSifraPredmeta() == subjectID) {
				return predmet;
			}
		}
		return null;
	}
	
	public void ponistiOcenu(String selectedIndex) {
		String selektovanStudent = TablePanel.getInstance().getSelectedEntityID();
		List<Ocena> polozeniIspiti = StudentController.getInstance().pronadjiStudentovePolozeneIspite(selektovanStudent);
		List<Student> studenti = AbstractStudentTable.getInstance().getStudenti();
		
		
		if(!studenti.isEmpty()) {
			for(Student student : studenti) {
				if(student.getBrojIndeksa().equals(selektovanStudent)) {
					if(!polozeniIspiti.isEmpty()) {
						int row = 0;
						for(Ocena polozenIspit : polozeniIspiti) {
							if(polozenIspit.getPredmet().getSifraPredmeta().equals(selectedIndex))
								break;
							
							row++;
							
						}
					
						AbstractStudentoviPolozeniIspitiTable.getInstance().removeRow(row);
						StudentoviPolozeniIspitiTablePanel.getInstance().refreshView();
					}
				}
			}
		}
		
		
	}

	public void dodajProfesoraPredmetu(int selectedSubjectRow) {
		String subjectID = SubjectNotTeachedController.getInstance().getSelectedSubjectID(selectedSubjectRow);
		Predmet subject = SubjectController.getInstance().nabaviPredmetSaSifrom(subjectID);
		Profesor professor = ProfessorController.getInstance().nabaviProfesoraSaLicnomKartom(ProfessorEditDialog.entityID);
		subject.setPredmetniProfesor(professor);
	}
	
	public void izmeniProfesora(int selectedProf) {
		String profesorLicnaKarta = AddProfesorToSubjectController.getInstance().getProfesor(selectedProf);
		Profesor profesor = ProfessorController.getInstance().nabaviProfesoraSaLicnomKartom(profesorLicnaKarta);
		List<Predmet> predmeti = AbstractSubjectTable.getInstance().getSubjects();
		
		for(Predmet predmet : predmeti) {
			
			if(predmet.getSifraPredmeta().equals(PredmetEditDialog.staraSifra)) {
				predmet.setPredmetniProfesor(profesor);
				System.out.println(predmet.getPredmetniProfesor().getIme() + "DODAT PREDMETU" + predmet.getNazivPredmeta());
				break;
			}	
		}
		
		StudentoviPolozeniIspitiTablePanel.getInstance().refreshView();
	}
	
	public String vratiImeIPrezimeProfesora(int selectedProf) {
		String profesorLicnaKarta = AddProfesorToSubjectController.getInstance().getProfesor(selectedProf);
		Profesor profesor = ProfessorController.getInstance().nabaviProfesoraSaLicnomKartom(profesorLicnaKarta);
		
		return (profesor.getIme() + " " + profesor.getPrezime());
	}

	
	public String izracunajProsek() {
		
		String prosek = String.valueOf(AbstractStudentoviPolozeniIspitiTable.getInstance().racunajProsek());
		
		return prosek;
	}
	
	public String izracunajESPB() {
		String espb = String.valueOf(AbstractStudentoviPolozeniIspitiTable.getInstance().racunajESPB());
		
		return espb;
	}
	
	public void obrisiProfesoraSaPredmeta(String selectedSubject) {
		Predmet predmet = nabaviPredmetSaSifrom(selectedSubject);
		predmet.setPredmetniProfesor(null);
	}
	
	 public String nabaviSifruPredmetnogProfesora() {
	    	Predmet predmet = SubjectController.getInstance().nabaviPredmetSaSifrom(PredmetEditDialog.staraSifra);
	    	if(predmet.getPredmetniProfesor() != null) 
				return predmet.getPredmetniProfesor().getBrojLicneKarte();
	    	else 
	    		return "ERROR";
	 }
	    

}
