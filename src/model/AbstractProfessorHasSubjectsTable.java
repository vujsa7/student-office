package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AbstractProfessorHasSubjectsTable extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9188875851162848487L;
	
	private static AbstractProfessorHasSubjectsTable instance = null;
	
	public static AbstractProfessorHasSubjectsTable getInstance() {
		if(instance == null) {
			instance = new AbstractProfessorHasSubjectsTable();
		}
		return instance;
	}
	
	private AbstractProfessorHasSubjectsTable() {
		initialize();
	}
	
	private List<Predmet> professorSubjects;
	private List<String> columns;

	
	private void initialize() {
		this.columns = new ArrayList<String>();
		this.columns.add("Sifra");
		this.columns.add("Naziv");
		this.columns.add("Godina studija");
		this.columns.add("Semestar");
		this.professorSubjects = new ArrayList<Predmet>();
	}
	
	
	@Override
	public int getRowCount() {
		return professorSubjects.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		Predmet subject = this.getProfessorSubjects().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return subject.getSifraPredmeta();
		case 1:
			return subject.getNazivPredmeta();
		case 2:
			return String.valueOf(subject.getGodinaStudija());
		case 3:
			if(subject.getSemestar() == Predmet.TipSemestra.LETNJI) {
				return "Letnji";
			} else {
				return "Zimski";
			}
			
		default:
			return null;
		}
	}
	

	public String getColumnName(int index) {
		return this.columns.get(index);
	}
	
	public Predmet getRow(int rowIndex) {
		return this.getProfessorSubjects().get(rowIndex);
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		if (getProfessorSubjects().isEmpty()) {
	        return Object.class;
	    }
	    return getValueAt(0, column).getClass();
	}

	public List<Predmet> getProfessorSubjects() {
		return professorSubjects;
	}

	public void setProfessorSubjects(List<Predmet> professorSubjects) {
		this.professorSubjects = professorSubjects;
	}

}
