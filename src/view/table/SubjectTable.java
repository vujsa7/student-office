package view.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import model.AbstractSubjectTable;

public class SubjectTable extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7811747195366627278L;

	public SubjectTable() {
		setRowSelectionAllowed(true);
		setColumnSelectionAllowed(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setRowHeight(30);
		// Sama JTable komponenta je implementirana postujuci MVC arhitekturu.
		setModel(AbstractSubjectTable.getInstance());
		
		// Centriranje teksta unutar celija
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		setDefaultRenderer(String.class, cellRenderer);
		
		setAutoCreateRowSorter(true);
		
		JTableHeader tableHeader = this.getTableHeader();
		UIManager.put("TableHeader.cellBorder", BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(58, 39, 93)));
		tableHeader.setPreferredSize(new Dimension(170, 25));
	    tableHeader.setBackground(new Color(141, 102,217));
	    tableHeader.setForeground(Color.white);    
	    setGridColor(new Color(196,190,206));
		UIManager.put("Table.focusCellHighlightBorder", BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
		
	}
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (isRowSelected(row)) {
			c.setBackground(new Color(90, 90, 90));
			c.setForeground(Color.white);

		} else {
			c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(232,232,232));
			c.setForeground(Color.black);
		}
		return c;
	}

}
