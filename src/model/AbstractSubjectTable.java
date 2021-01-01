package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AbstractSubjectTable extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3506556021482677961L;
	
	private static AbstractSubjectTable instance = null;
	
	public static AbstractSubjectTable getInstance() {
		if(instance == null) {
			instance = new AbstractSubjectTable();
		}
		return instance;
	}
	
	private List<Predmet> subjects;
	private List<Predmet> subjectsBackup;
	private List<String> columns;
	
	private AbstractSubjectTable() {
		initialize();
	}
	
	private void initialize() {
		
		initSubjects();
		
		this.columns = new ArrayList<String>();
		this.columns.add("Šifra");
		this.columns.add("Naziv");
		this.columns.add("Broj ESPB");
		this.columns.add("Godina studija");
		this.columns.add("Semestar");
	}


	private void initSubjects() {
		this.setSubjects(new ArrayList<Predmet>());
		getSubjects().add(new Predmet("06-E212", "Matematicka analiza", TipSemestra.ZIMSKI, 1, null, 9, null, null));
		getSubjects().add(new Predmet("06-E213A", "Algebra", TipSemestra.ZIMSKI, 1, null, 9, null, null));
		getSubjects().add(new Predmet("06-E215", "Fizika", TipSemestra.LETNJI, 1, null, 9, null, null));	
		subjectsBackup = subjects;
	}

	@Override
	public int getRowCount() {
		return this.getSubjects().size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		Predmet subject = this.getSubjects().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return subject.getSifraPredmeta();
		case 1:
			return subject.getNazivPredmeta();
		case 2:
			return String.valueOf(subject.getBrojESPB());
		case 3:
			return String.valueOf(subject.getGodinaStudija());
		case 4:
			if(subject.getSemestar() == TipSemestra.LETNJI) {
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
		return this.getSubjects().get(rowIndex);
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
	    return getValueAt(0, column).getClass();
	}

	public List<Predmet> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Predmet> subjects) {
		this.subjects = subjects;
	}
	
	public void removeRow(int row) {
        this.subjects.remove(row);
    }

	public void setDefaultSubjects() {
		subjects = subjectsBackup;
	}

	public void setSearchedSubjects(ArrayList<Predmet> searchedSubjects) {
		subjectsBackup = subjects;
		subjects = searchedSubjects;
	}

}
