package model;

public class PolozenIspit {
	
	private String sifraPredmeta;
	private String nazivPredmeta;
	private String ESPB;
	private String ocenaIzPredmeta;
	private String datumPolaganja;
	
	public PolozenIspit() {}
	
	public PolozenIspit(String sifraPredmeta, String nazivPredmeta, String eSPB, String ocenaIzPredmeta,
			String datumPolaganja) {
		super();
		this.sifraPredmeta = sifraPredmeta;
		this.nazivPredmeta = nazivPredmeta;
		ESPB = eSPB;
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
	public void setESPB(String eSPB) {
		ESPB = eSPB;
	}
	public String getOcenaIzPredmeta() {
		return ocenaIzPredmeta;
	}
	public void setOcenaIzPredmeta(String ocenaIzPredmeta) {
		this.ocenaIzPredmeta = ocenaIzPredmeta;
	}
	public String getDatumPolaganja() {
		return datumPolaganja;
	}
	public void setDatumPolaganja(String datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}
	
	

}
