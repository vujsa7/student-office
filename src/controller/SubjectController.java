package controller;

import java.util.ArrayList;

import model.AbstractSubjectTable;
import model.Predmet;
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

	

}
