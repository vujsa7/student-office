package controller;

import java.awt.FontFormatException;
import java.io.IOException;

import view.dialogs.StudentDialog;

public class StudentController {
	
private static StudentController instance = null;
	
	public static StudentController getInstance() {
		if (instance == null) {
			instance = new StudentController();
		}
		return instance;
	}
	
	private StudentController() {}
	
	public void dodajStudenta() throws FontFormatException, IOException {
		
	}
}
