package controller;

import java.util.ArrayList;
import java.util.List;

import model.AbstractAddNepolozeniIspitiTable;
import model.AbstractStudentTable;
import model.AbstractSubjectTable;
import model.Ocena;
import model.Predmet;
import model.Student;
import view.dialogs.AddNepolozenIspitDialog;
import view.dialogs.StudentEditDialog;

public class AddNepolozenIspitController {
	
	private static AddNepolozenIspitController instance = null;
	
	public static AddNepolozenIspitController getInstance() {
		if (instance == null) {
			instance = new AddNepolozenIspitController();
		}
		return instance;
	}
	
	public AddNepolozenIspitController() {}
	
	public void dobaviNepolozeneIspite() {
		Student student = StudentController.getInstance().pronadjiStudentaPrekoIndeksa(StudentEditDialog.stariIndeks);
		List<Predmet> sviPredmeti = AbstractSubjectTable.getInstance().getSubjects();
		List<Predmet> nepolozeniStudentoviPredmeti = student.getNepolozeniIspiti();
		List<Ocena> polozeniStudentoviPredmeti = student.getPolozeniIspiti();		
		
		List<Predmet> predmetiKojiSeNeNalaze = new ArrayList<Predmet>();
		
		for(Predmet predmet : sviPredmeti) {
			if(!nepolozeniStudentoviPredmeti.contains(predmet)) {
				if(!polozeniStudentoviPredmeti.isEmpty()) {
					for(Ocena polozenIspit : polozeniStudentoviPredmeti) {
						int godinaStudenta = AbstractStudentTable.getInstance().vratiGodinuStudenta(student.getTrenutnaGodStudija());
						if(!polozenIspit.getPredmet().getSifraPredmeta().equals(predmet.getSifraPredmeta()) && predmet.getGodinaStudija() <= godinaStudenta) {
							predmetiKojiSeNeNalaze.add(predmet);
						}
					}
				} else {
					int godinaStudenta = AbstractStudentTable.getInstance().vratiGodinuStudenta(student.getTrenutnaGodStudija());
					if(predmet.getGodinaStudija() <= godinaStudenta) {
						predmetiKojiSeNeNalaze.add(predmet);
					}
				}
			}
		}
		
		
		AbstractAddNepolozeniIspitiTable.getInstance().setNepolozeniIspiti(predmetiKojiSeNeNalaze);
		AddNepolozenIspitDialog.getInstance().refreshView();
	}
	
	public String getSifraPredmeta(int selectedSubject) {
		Predmet predmet = AbstractAddNepolozeniIspitiTable.getInstance().getRow(selectedSubject);
		return predmet.getSifraPredmeta();
	}
}
