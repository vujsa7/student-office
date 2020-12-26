package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AbstractProfessorTable extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7879422975558547019L;

private static AbstractProfessorTable instance = null;
	
	private List<Profesor> professors;
	private List<String> columns;
	
	private AbstractProfessorTable() {
		initialize();
	}
	
	private void initialize() {
		
		initProfesore();
		
		this.columns = new ArrayList<String>();
		this.columns.add("Ime");
		this.columns.add("Prezime");
		this.columns.add("Titula");
		this.columns.add("Zvanje");
	}
	
	private void initProfesore() {
		this.professors = new ArrayList<Profesor>();
		professors.add(new Profesor("Mika", "Mikic", "19-19-2018", "Dubravska 12", "0617219096", "aleksvuj@yahoo.com", "FTN-park 18", "1293929312", "Inzenjer", "DR", null));
		professors.add(new Profesor("Mika", "Mikic", "19-19-2018", "Dubravska 12", "0617219096", "aleksvuj@yahoo.com", "FTN-park 18", "1293929312", "Inzenjer", "DR", null));
		professors.add(new Profesor("Mika", "Mikic", "19-19-2018", "Dubravska 12", "0617219096", "aleksvuj@yahoo.com", "FTN-park 18", "1293929312", "Inzenjer", "DR", null));
	}
	
	public static AbstractProfessorTable getInstance() {
		if(instance == null) {
			instance = new AbstractProfessorTable();
		}
		return instance;
	}
	
	// ne znam da li ce mi trebati
	public List<Profesor> getProfessors() {
		return professors;
	}

	// ne znam da li ce mi trebati
	public void setProfessors(List<Profesor> profesori) {
		this.professors = profesori;
	}
	
	public int getColumnCount() {
		return 4;
	}
	
	public String getValueAt(int row, int column) {
		Profesor professor = this.professors.get(row);
		switch (column) {
		case 0:
			return professor.getIme();
		case 1:
			return professor.getPrezime();
		case 2:
			return professor.getTitula();
		case 3:
			return professor.getZvanje();
		default:
			return null;
		}
	}
	
	public String getColumnName(int index) {
		return this.columns.get(index);
	}
	
	public Profesor getRow(int rowIndex) {
		return this.professors.get(rowIndex);
	}

	public void dodajProfesora(String ime, String prezime, String datumRodjenja, String adresaStanovanja, String kontaktTelefon, String emailAdresa, String adresaKancelarije, String brojLicneKarte, String titula, String zvanje) {
		professors.add(new Profesor(ime, prezime, datumRodjenja, adresaStanovanja, kontaktTelefon, emailAdresa, adresaKancelarije, brojLicneKarte, titula, zvanje, null));
	}

	@Override
	public int getRowCount() {
		return professors.size();
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
	    return getValueAt(0, column).getClass();
	}

}
