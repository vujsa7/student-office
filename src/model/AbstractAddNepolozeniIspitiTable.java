package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AbstractAddNepolozeniIspitiTable extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8110491129843728992L;
	
	private static AbstractAddNepolozeniIspitiTable instance = null;
	
	public static AbstractAddNepolozeniIspitiTable getInstance() {
		if(instance == null) {
			instance = new AbstractAddNepolozeniIspitiTable();
		}
		return instance;
	}
	
	private List<Predmet> nepolozeniIspiti;
	
	private AbstractAddNepolozeniIspitiTable() {
		nepolozeniIspiti = new ArrayList<Predmet>();
	}
	
	public List<Predmet> getNepolozeniIspiti() {
		return nepolozeniIspiti;
	}


	public void setNepolozeniIspiti(List<Predmet> nepolozeniIspiti) {
		this.nepolozeniIspiti = nepolozeniIspiti;
	}


	@Override
	public int getRowCount() {
		return nepolozeniIspiti.size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return (nepolozeniIspiti.get(rowIndex).getSifraPredmeta() + " - " + nepolozeniIspiti.get(rowIndex).getNazivPredmeta());
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		if (nepolozeniIspiti.isEmpty()) {
	        return Object.class;
	    }
	    return getValueAt(0, column).getClass();
	}
	
	public Predmet getRow(int rowIndex) {
		return this.nepolozeniIspiti.get(rowIndex);
	}
}
