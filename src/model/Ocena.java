package model;

public class Ocena {
	
	private Student studentKojiJePolozio;
	private Predmet predmet;
	private String ocenaNaIspitu;
	private String datumPolaganja;
	
	
	public Ocena(Student studentKojiJePolozio, Predmet predmet, String ocenaNaIspitu, String datumPolaganja) {
		super();
		this.studentKojiJePolozio = studentKojiJePolozio;
		this.predmet = predmet;
		this.ocenaNaIspitu = ocenaNaIspitu;
		this.datumPolaganja = datumPolaganja;
	}
	
	public Student getStudent() {
		return studentKojiJePolozio;
	}
	public void setStudent(Student studentKojiJePolozio) {
		this.studentKojiJePolozio = studentKojiJePolozio;
	}
	public Predmet getPredmet() {
		return predmet;
	}
	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
	public String getOcenaNaIspitu() {
		return ocenaNaIspitu;
	}
	public void setOcenaNaIspitu(String ocenaNaIspitu) {
		this.ocenaNaIspitu = ocenaNaIspitu;
	}
	public String getDatumPolaganja() {
		return datumPolaganja;
	}
	public void setDatumPolaganja(String datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}
	
	
}
