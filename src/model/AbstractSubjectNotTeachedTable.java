package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;


public class AbstractSubjectNotTeachedTable extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8102611786516362281L;
	
	private static AbstractSubjectNotTeachedTable instance = null;
	
	public static AbstractSubjectNotTeachedTable getInstance() {
		if(instance == null) {
			instance = new AbstractSubjectNotTeachedTable();
		}
		return instance;
	}
	
	private AbstractSubjectNotTeachedTable() {}
	
	private List<Predmet> subjectsNotTeachedByProfessor;

	@Override
	public int getRowCount() {
		return subjectsNotTeachedByProfessor.size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}
	
	public Predmet getRow(int rowIndex) {
		return this.subjectsNotTeachedByProfessor.get(rowIndex);
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		return subjectsNotTeachedByProfessor.get(rowIndex).getNazivPredmeta();
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		if (subjectsNotTeachedByProfessor.isEmpty()) {
	        return Object.class;
	    }
	    return getValueAt(0, column).getClass();
	}
	
	public void setSubjectsNotTeachedByProfessor(List<Predmet> subjects) {
		subjectsNotTeachedByProfessor = subjects;
	}
	
}
