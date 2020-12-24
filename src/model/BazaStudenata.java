package model;

import java.util.ArrayList;
import java.util.List;

public class BazaStudenata {
	
	private static BazaStudenata instance = null;

	public static BazaStudenata getInstance() {
		if (instance == null) {
			instance = new BazaStudenata();
		}
		return instance;
	}
	
	private List<Student> studenti;
	private List<String> kolone;
	
	private BazaStudenata() {
		
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
		studenti.add(new Student("Pera", "Peric", "01.01.1999.", "Bulevar oslobodjenja 2", "060111222", "peraperic@gmail.com",
				"RA1/2019", "2019.", "druga", "Budzet", "9.2", null, null));
		studenti.add(new Student("Pera", "Peric", "01.01.1999.", "Bulevar oslobodjenja 2", "060111222", "peraperic@gmail.com",
				"RA1/2019", "2019.", "druga", "Budzet", "9.2", null, null));
		studenti.add(new Student("Pera", "Peric", "01.01.1999.", "Bulevar oslobodjenja 2", "060111222", "peraperic@gmail.com",
				"RA1/2019", "2019.", "druga", "Budzet", "9.2", null, null));
		
	}

	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}

	public List<String> getKolone() {
		return kolone;
	}

	public void setKolone(List<String> kolone) {
		this.kolone = kolone;
	}
	
	public int getColumnCount() {
		return 6;
	}
	
	public String getValueAt(int row, int column) {
		Student student = this.studenti.get(row);
		switch (column) {
		case 0:
			return student.getBrojIndeksa();
		case 1:
			return student.getIme();
		case 2:
			return student.getPrezime();
		case 3:
			return student.getTrenutnaGodStrudija();
		case 4:
			return student.getStatus();
		case 5:
			return student.getProsek();
		default:
			return null;
		}
	}
	
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	public Student getRow(int rowIndex) {
		return this.studenti.get(rowIndex);
	}
}
