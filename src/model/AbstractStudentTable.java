package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AbstractStudentTable extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7920025647227272600L;
	
	private static AbstractStudentTable instance = null;

	public static AbstractStudentTable getInstance() {
		if (instance == null) {
			instance = new AbstractStudentTable();
		}
		return instance;
	}
	
	private List<Student> studenti;
	private List<String> kolone;
	private List<Student> defaultStudenti;
	
	private AbstractStudentTable() {
		
		initStudente();
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("Indeks");
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Godina studija");
		this.kolone.add("Status");
		this.kolone.add("Prosek");
	}
	
	private void initStudente() {
		this.studenti = new ArrayList<Student>();
		studenti.add(new Student("Pera", "Peric", LocalDate.of(1973, 10, 13), "Bulevar oslobodjenja 2", "060111222", "peraperic@gmail.com",
				"RA2/2020", "2019", "I(prva)", "Budzet", "9.2", new ArrayList<PolozenIspit>(), new ArrayList<Predmet>()));
		studenti.add(new Student("Mika", "Mikic", LocalDate.of(1973, 10, 14), "Bulevar oslobodjenja 2", "060111222", "peraperic@gmail.com",
				"RA1/2019", "2019", "II(druga)", "Budzet", "8.9", new ArrayList<PolozenIspit>(), new ArrayList<Predmet>()));
		studenti.add(new Student("Ivan", "Ivanovic", LocalDate.of(1973, 10, 15), "Bulevar oslobodjenja 2", "060111222", "peraperic@gmail.com",
				"RA3/2018", "2019", "III(treca)", "Samofinansiranje", "9.1", new ArrayList<PolozenIspit>(), new ArrayList<Predmet>() ));
		
		defaultStudenti = studenti;
	}
	
	public List<Student> getStudenti() {
		return studenti;
	}
	
	public void setStudente(List<Student> studenti) {
		this.studenti = studenti;
	}
	
	public List<String> getKolone() {
		return kolone;
	}
	
	public void setKolone(List<String> kolone) {
		this.kolone = kolone;
	}
	
	@Override
	public int getRowCount() {
		return studenti.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}
	
	@Override
	public String getColumnName(int column) {
		return this.kolone.get(column);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Student student = this.studenti.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return student.getBrojIndeksa();
		case 1:
			return student.getIme();
		case 2:
			return student.getPrezime();
		case 3:
			return student.getTrenutnaGodStudija();
		case 4:
			return student.getStatus();
		case 5:
			return student.getProsek();
		default:
			return null;
		}
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
	    return getValueAt(0, column).getClass();
	}
	
	public Student getRow(int rowIndex) {
		return this.studenti.get(rowIndex);
	}
	
	public void removeRow(int rowIndex) {
		this.studenti.remove(rowIndex);
	}

	public void dodajStudenta(String ime, String prezime, LocalDate datumRodjenja, String adresaStanovanja,
			String kontaktTelefon, String emailAdresa, String brojIndeksa, String godUpisa, String trenutnaGodStudija,
			String status) {
		
		this.studenti.add(new Student(ime, prezime, datumRodjenja, adresaStanovanja, kontaktTelefon,
				emailAdresa, brojIndeksa, godUpisa, trenutnaGodStudija, status, null, new ArrayList<PolozenIspit>(), new ArrayList<Predmet>()));
	}

	public List<Student> getDefaultStudenti() {
		return defaultStudenti;
	}

	public void setTrazeniStudenti(List<Student> trazeniStudenti) {
		this.defaultStudenti = studenti;
		this.studenti = trazeniStudenti;
	}
	
	public void setDefaultStudente() {
		this.studenti = defaultStudenti;
	}
}
