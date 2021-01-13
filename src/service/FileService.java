package service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.AbstractProfessorTable;
import model.AbstractStudentTable;
import model.AbstractSubjectTable;

public class FileService {

	private static FileService instance = null;
	
	public static FileService getInstance() {
		if (instance == null) {
			instance = new FileService();
		}
		return instance;
	}
	
	private FileService(){}
	
	private ObjectOutputStream os;
	
	public void saveToExtern(){
		try {
			os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("assets"+ File.separator +"databases" + File.separator + "databases.txt")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			os.writeObject(AbstractStudentTable.getInstance().getStudenti());
			os.writeObject(AbstractProfessorTable.getInstance().getProfessors());
			os.writeObject(AbstractSubjectTable.getInstance().getSubjects());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
