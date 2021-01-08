package controller;

import java.util.List;

import model.AbstractStudentTable;
import model.AbstractStudentoviPolozeniIspitiTable;
import model.Ocena;
import view.dialogs.StudentEditDialog;
import view.dialogs.components.studentedit.StudentoviPolozeniIspitiTablePanel;

public class PolozeniStudentiController {
	
	private static PolozeniStudentiController instance = null;
	
	public static PolozeniStudentiController getInstance() {
		if(instance == null) {
			instance = new PolozeniStudentiController();
		}
		return instance;
	}	
	
	private PolozeniStudentiController() {}
	
	public String getSelectedIspit(int selectedIspit) {
		Ocena ispit = AbstractStudentoviPolozeniIspitiTable.getInstance().getRow(selectedIspit);
		return ispit.getPredmet().getSifraPredmeta();
	}
	
	public void postaviPolozenePredmeteStudentu() {
		List<Ocena> ocene = AbstractStudentTable.getInstance().nabaviPolozenePredmeteStudenta(StudentEditDialog.stariIndeks);
		AbstractStudentoviPolozeniIspitiTable.getInstance().postaviStudentuPolozeneIspite(ocene);
		StudentoviPolozeniIspitiTablePanel.getInstance().refreshView();
	}
}
