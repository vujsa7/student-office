package controller;

import model.AbstractStudentoviPolozeniIspitiTable;
import model.PolozenIspit;

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
		PolozenIspit ispit = AbstractStudentoviPolozeniIspitiTable.getInstance().getRow(selectedIspit);
		return ispit.getSifraPredmeta();
	}
}
