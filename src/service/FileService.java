package service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Student;

import model.AbstractProfessorTable;
import model.AbstractStudentTable;
import model.AbstractSubjectTable;
import model.Predmet;
import model.Profesor;

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
		
		AbstractStudentTable.getInstance().setDefaultStudente();
    	AbstractProfessorTable.getInstance().setDefaultProfessors();
    	AbstractSubjectTable.getInstance().setDefaultSubjects();
    	
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
	
	@SuppressWarnings("unchecked")
	public void getFromExtern() {
		List<Student> studenti = new ArrayList<Student>();
		List<Profesor> profesori = new ArrayList<Profesor>();
		List<Predmet> predmeti = new ArrayList<Predmet>();
		
		File file = new File("assets" + File.separator + "databases" + File.separator + "databases.txt");
		if(file.exists()) {
			try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("assets" + File.separator + "databases" + File.separator + "databases.txt"))){
				studenti.addAll((List<Student>) inputStream.readObject());
				profesori.addAll((List<Profesor>) inputStream.readObject());
				predmeti.addAll((List<Predmet>) inputStream.readObject());
				
				AbstractStudentTable.getInstance().setStudente(studenti);
				AbstractProfessorTable.getInstance().setProfessors(profesori);
				AbstractSubjectTable.getInstance().setSubjects(predmeti);
			} catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}

}
