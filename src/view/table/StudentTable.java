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

import model.AbstractStudentTable;

public class StudentTable extends JTable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5981837537193921070L;
	
	public StudentTable() {
		
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setRowHeight(30);
		
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		setDefaultRenderer(String.class, cellRenderer);
		
		setAutoCreateRowSorter(true);
		
		JTableHeader header = this.getTableHeader();
		UIManager.put("TableHeader.cellBorder", BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(58, 39, 93)));
		header.setBorder(null);
		header.setPreferredSize(new Dimension(170, 25));
	    header.setBackground(new Color(141, 102,217));
	    header.setForeground(Color.white);    
	    setGridColor(new Color(196,190,206));
		UIManager.put("Table.focusCellHighlightBorder", BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
		
		// Sama JTable komponenta je implementirana postujuci MVC arhitekturu.
		this.setModel(AbstractStudentTable.getInstance());
	}

	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		
		Component c = super.prepareRenderer(renderer, row, column);
		
		if(isRowSelected(row)) {
			c.setBackground(new Color(90, 90, 90));
			c.setForeground(Color.WHITE);
		} else {
			if(row % 2 == 0) {
				c.setBackground(Color.WHITE);
				c.setForeground(Color.BLACK);
			} else {
				c.setBackground(new Color(232, 232, 232));
				c.setForeground(Color.BLACK);
			}
		}
		
		return c;
	}
}
