package model;

import java.io.ObjectOutputStream;
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

	public static ObjectOutputStream os;
	
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
		if (studenti.isEmpty()) {
	        return Object.class;
	    }
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
				emailAdresa, brojIndeksa, godUpisa, trenutnaGodStudija, status, "0", new ArrayList<Ocena>(), new ArrayList<Predmet>()));
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

	public List<Predmet> nabaviNepolozenePredmeteStudenta(String brIndex) {
		List<Predmet> predmeti = new ArrayList<Predmet>();
		for(Student student : studenti) {
			if(student.getBrojIndeksa().contentEquals(brIndex)) {
				return student.getNepolozeniIspiti();
			}
		}
		return predmeti;
	}

	public boolean nekiStudentImaPolozenIspit(String sifraPredmeta) {
		for(Student s : studenti) {
			for(Ocena p : s.getPolozeniIspiti()) {
				if(p.getPredmet().getSifraPredmeta() == sifraPredmeta) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean nekiStudentImaNepolozenIspit(String sifraPredmeta) {
		for(Student s : studenti) {
			for(Predmet p : s.getNepolozeniIspiti()) {
				if(p.getSifraPredmeta() == sifraPredmeta) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int vratiGodinuStudenta(String godina) {
		if(godina == "I(prva)") return 1;
		else if(godina == "II(druga)") return 2;
		else if(godina == "III(treća)") return 3;
		else if(godina == "IV(četvrta)") return 4;
		else if(godina == "V(master studije)") return 5;
		else if(godina == "VI(doktorske studije)") return 6;
		else if(godina == "VII(doktorske studije)") return 7;
		else return 8;
		
		
	}
	
	public List<Ocena> nabaviPolozenePredmeteStudenta(String indeks) {
		List<Ocena> ret = new ArrayList<Ocena>();
		for(Student student : studenti) {
			if(student.getBrojIndeksa().equals(indeks))
				return student.getPolozeniIspiti();
		}
		
		return ret;
	}
	
	public void izbrisiPredmetIzListeNepolozenih(String stariIndeks, int index) {
		for(Student s : studenti) {
			if(s.getBrojIndeksa() == stariIndeks) {
				s.getNepolozeniIspiti().remove(index);
			}
		}
		
	}
	
	public void dodajPredmetUListuPolozenih(String stariIndeks, Predmet p, int ocena, LocalDate localDate) {
		for(Student s : studenti) {
			if(s.getBrojIndeksa() == stariIndeks) {
				Ocena o = new Ocena(s, p, ocena, localDate);
				s.getPolozeniIspiti().add(o);
			}
		}
	}
	
	public void setBackupStudente(List<Student> studenti2) {
		this.defaultStudenti = studenti2;
	}
}
