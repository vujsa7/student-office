package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AbstractAddProfesorToSubjectTable extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4280463088848543179L;
	private static AbstractAddProfesorToSubjectTable instance = null;
	
	public static AbstractAddProfesorToSubjectTable getInstance() {
		if(instance == null) {
			instance = new AbstractAddProfesorToSubjectTable();
		}
		return instance;
	}
	
	private List<Profesor> profesori;
	
	private AbstractAddProfesorToSubjectTable() {}

	@Override
	public int getRowCount() {
		return profesori.size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return (profesori.get(rowIndex).getIme() + " " + profesori.get(rowIndex).getPrezime());
	}
	
	public Profesor getRow(int rowIndex) {
		return this.profesori.get(rowIndex);
	}
	
	public Class<?> getColumnClass(int column) {
		if (profesori.isEmpty()) {
	        return Object.class;
	    }
	    return getValueAt(0, column).getClass();
	}
	
	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}
	
	
}
