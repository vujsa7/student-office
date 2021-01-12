package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class AbstractStudentoviPolozeniIspitiTable extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2366828867939472107L;
	
	private static AbstractStudentoviPolozeniIspitiTable instance = null;
	
	public static AbstractStudentoviPolozeniIspitiTable getInstance() {
		if(instance == null)
			instance = new AbstractStudentoviPolozeniIspitiTable();
		
		return instance;
	}
	
	private List<Ocena> oceneStudenta;
	private List<String> kolone;
	
	private AbstractStudentoviPolozeniIspitiTable() {
		
		initIspite();
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("Šifra predmeta");
		this.kolone.add("Naziv predmeta");
		this.kolone.add("ESPB");
		this.kolone.add("Ocena");
		this.kolone.add("Datum");
		
	
	}
	
	public void initIspite() {
		this.oceneStudenta = new ArrayList<Ocena>();
		Student student = AbstractStudentTable.getInstance().getStudenti().get(0);
		Predmet predmet = AbstractSubjectTable.getInstance().getSubjects().get(0);
		this.oceneStudenta.add(new Ocena(student, predmet, 7, LocalDate.of(2018, 6, 26)));
	}
	
	public List<Ocena> getPolozeniIspiti() {
		return oceneStudenta;
	}

	public void setPolozeniIspiti(List<Ocena> polozeniIspiti) {
		this.oceneStudenta = polozeniIspiti;
	}

	public List<String> getKolone() {
		return kolone;
	}

	public void setKolone(List<String> kolone) {
		this.kolone = kolone;
	}

	@Override
	public int getRowCount() {
		return oceneStudenta.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Ocena ocena = this.oceneStudenta.get(rowIndex);
		switch(columnIndex) {
			case 0:
				return ocena.getPredmet().getSifraPredmeta();
			
			case 1:
				return ocena.getPredmet().getNazivPredmeta();
				
			case 2:
				return ocena.getPredmet().getBrojESPB();
			
			case 3:
				return ocena.getOcena();
			
			case 4:
				return String.valueOf(ocena.getDatumPolaganja());
			
			default:
				return null;
		}
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		if (getPolozeniIspiti().isEmpty()) {
	        return Object.class;
	    }
	    return getValueAt(0, column).getClass();
	}
	
	public String getColumName(int index) {
		return this.kolone.get(index);
	}
	
	public void removeRow(int index) {
		this.oceneStudenta.remove(index);
	}
	
	public Ocena getRow(int row) {
		return this.oceneStudenta.get(row);
	}
	
	public float racunajProsek() {
		int brojPredmeta = oceneStudenta.size();
		float zbir = 0;
		
		for(Ocena ocena : oceneStudenta) {
			zbir += ocena.getOcena();
		}
		
		if(brojPredmeta == 0)
			return 0;
		else {
			float rez = zbir/brojPredmeta;
			return rez;
		}
	}
	
	public int racunajESPB() {
		int ukupnoESPB = 0;
		
		for(Ocena ocena : oceneStudenta) {
			ukupnoESPB += ocena.getPredmet().getBrojESPB();
			
		}
		
		return ukupnoESPB;
	}
	
	public void postaviStudentuPolozeneIspite(List<Ocena> ocene) {
		oceneStudenta = ocene;
	}
}
