package model;

import java.time.LocalDate;
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
	private List<Profesor> professorsBackup;
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
		professors.add(new Profesor("Mile", "Mitić", LocalDate.of(1973, 10, 13), "Novi Sad, Stevana Musića 18", "+381 618289304", "mile.mitic@gmail.com", "FTN-park 18", "129392932", "Prof. dr.", "Redovni profesor", new ArrayList<Predmet>()));
		professors.add(new Profesor("Rade", "Milenković", LocalDate.of(1963, 7, 11), "Novi Sad, Stevana Musića 10", "+381 6284853501", "rade.milenkovic@yahoo.com", "FTN-park 1", "129392921", "Dr.", "Naučni-saradnik", new ArrayList<Predmet>()));
		professors.add(new Profesor("Mika", "Mikic", LocalDate.of(1969, 1, 29), "Novi Sad, Radnička 8", "+381 617444036", "mikamikic@gmail.rs", "FTN-park 33", "129392944", "Prof. dr", "Vanredni profesor", new ArrayList<Predmet>()));
		professorsBackup = professors;
	}
	
	public static AbstractProfessorTable getInstance() {
		if(instance == null) {
			instance = new AbstractProfessorTable();
		}
		return instance;
	}
	
	public List<Profesor> getProfessors() {
		return professors;
	}

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

	public void dodajProfesora(String ime, String prezime, LocalDate datumRodjenja, String adresaStanovanja, String kontaktTelefon, String emailAdresa, String adresaKancelarije, String brojLicneKarte, String titula, String zvanje) {
		professors.add(new Profesor(ime, prezime, datumRodjenja, adresaStanovanja, kontaktTelefon, emailAdresa, adresaKancelarije, brojLicneKarte, titula, zvanje, null));
	}

	@Override
	public int getRowCount() {
		return professors.size();
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		if (professors.isEmpty()) {
	        return Object.class;
	    }
	    return getValueAt(0, column).getClass();
	}

	public void setSearchedProfessors(ArrayList<Profesor> searchedProfessors) {
		professorsBackup = professors;
		professors = searchedProfessors;
	}
	
	public void setDefaultProfessors() {
		professors = professorsBackup;
	}
	
	public void removeRow(int row) {
		this.professors.remove(row);
	}

	public boolean nekiProfesorImaPredmet(String sifraPredmeta) {
		for(Profesor p : professors) {
			for(Predmet predmet : p.getListaPredmeta()) {
				if(predmet.getSifraPredmeta() == sifraPredmeta) {
					return true;
				}
			}
		}
		return false;
	}

}
