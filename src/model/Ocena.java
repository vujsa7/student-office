package model;

import java.time.LocalDate;

public class PolozenIspit {
	
	private String sifraPredmeta;
	private String nazivPredmeta;
	private String ESPB;
	private String ocenaIzPredmeta;
	private LocalDate datumPolaganja;
	
	public PolozenIspit() {}
	
	public PolozenIspit(String sifraPredmeta, String nazivPredmeta, String ESPB, String ocenaIzPredmeta,
			LocalDate datumPolaganja) {
		super();
		this.sifraPredmeta = sifraPredmeta;
		this.nazivPredmeta = nazivPredmeta;
		this.ESPB = ESPB;
		this.ocenaIzPredmeta = ocenaIzPredmeta;
		this.datumPolaganja = datumPolaganja;
	}



	public String getSifraPredmeta() {
		return sifraPredmeta;
	}
	public void setSifraPredmeta(String sifraPredmeta) {
		this.sifraPredmeta = sifraPredmeta;
	}
	public String getNazivPredmeta() {
		return nazivPredmeta;
	}
	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}
	public String getESPB() {
		return ESPB;
	}
	public void setESPB(String ESPB) {
		this.ESPB = ESPB;
	}
	public String getOcenaIzPredmeta() {
		return ocenaIzPredmeta;
	}
	public void setOcenaIzPredmeta(String ocenaIzPredmeta) {
		this.ocenaIzPredmeta = ocenaIzPredmeta;
	}
	public LocalDate getDatumPolaganja() {
		return datumPolaganja;
	}
	public void setDatumPolaganja(LocalDate datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}
	
	

}
