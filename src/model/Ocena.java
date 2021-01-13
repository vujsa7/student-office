package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Ocena implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7800729509841854473L;
	private Student student;
	private Predmet predmet;
	private int ocena;
	private LocalDate datumPolaganja;
	
	public Ocena() {}

	public Ocena(Student student, Predmet predmet, int ocena, LocalDate datumPolaganja) {
		super();
		this.student = student;
		this.predmet = predmet;
		this.ocena = ocena;
		this.datumPolaganja = datumPolaganja;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public LocalDate getDatumPolaganja() {
		return datumPolaganja;
	}

	public void setDatumPolaganja(LocalDate datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}
	
}
