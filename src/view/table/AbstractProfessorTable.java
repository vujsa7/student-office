package view.table;

import javax.swing.table.AbstractTableModel;

import model.BazaProfesora;

public class AbstractProfessorTable extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7879422975558547019L;

	public AbstractProfessorTable() {
	}

	@Override
	public int getRowCount() {
		return BazaProfesora.getInstance().getProfessors().size();
	}

	@Override
	public int getColumnCount() {
		return BazaProfesora.getInstance().getColumnCount();
	}
	
	// nazivi kolona u zaglavlju
		@Override
	public String getColumnName(int column) {
		return BazaProfesora.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaProfesora.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
	// Za centriranje teksta unutar svake celije
	@Override
	public Class<?> getColumnClass(int column) {
	    return getValueAt(0, column).getClass();
	}

}
