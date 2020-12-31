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

}
