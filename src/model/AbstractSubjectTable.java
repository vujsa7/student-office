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
		subjects.add(new Predmet("E212", "Matematicka analiza", Predmet.TipSemestra.ZIMSKI, 1, null, 9, null, null));
		subjects.add(new Predmet("E213", "Algebra", Predmet.TipSemestra.ZIMSKI, 1, null, 9, null, null));
		subjects.add(new Predmet("E214", "Fizika", Predmet.TipSemestra.LETNJI, 1, null, 9, null, null));	
		subjects.add(new Predmet("E215", "OISISI", Predmet.TipSemestra.ZIMSKI, 3, null, 6, null, null));
		subjectsBackup = subjects;
	}

	@Override
	public int getRowCount() {
		return this.subjects.size();
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
		return this.subjects.get(rowIndex);
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		if (subjects.isEmpty()) {
	        return Object.class;
	    }
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
	
	public void dodajPredmet(String sifra, String naziv, int godina, Predmet.TipSemestra semestar, int espb) {
		this.subjects.add(new Predmet(sifra, naziv, semestar, godina, null, espb, null, null));
	}
}
