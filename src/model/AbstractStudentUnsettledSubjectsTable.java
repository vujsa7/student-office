package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AbstractStudentUnsettledSubjectsTable extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4847666677430568925L;
	private static AbstractStudentUnsettledSubjectsTable instance = null;
	
	public static AbstractStudentUnsettledSubjectsTable getInstance() {
		if(instance == null) {
			instance = new AbstractStudentUnsettledSubjectsTable();
		}
		return instance;
	}
	
	private AbstractStudentUnsettledSubjectsTable() {
		initialize();
	}
	
	private List<Predmet> unsettledSubjects;
	private List<String> columns;
	
	private void initialize() {
		this.columns = new ArrayList<String>();
		this.columns.add("Šifra predmeta");
		this.columns.add("Naziv predmeta");
		this.columns.add("Broj ESPB");
		this.columns.add("Godina studija");
		this.columns.add("Semestar");
		unsettledSubjects = new ArrayList<Predmet>();
	}

	@Override
	public int getRowCount() {
		return unsettledSubjects.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		Predmet subject = this.unsettledSubjects.get(rowIndex);
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
	
	public Predmet getRow(int rowIndex) {
		return this.unsettledSubjects.get(rowIndex);
	}
	
	public String getColumnName(int index) {
		return columns.get(index);
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		if (unsettledSubjects.isEmpty()) {
	        return Object.class;
	    }
	    return getValueAt(0, column).getClass();
	}
	
	public List<Predmet> getUnsettledSubjects() {
		return unsettledSubjects;
	}

	public void setUnsettledSubjects(List<Predmet> unsettledSubjects) {
		this.unsettledSubjects = unsettledSubjects;
	}

}
