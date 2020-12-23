package model;

public class NepolozenIspit {
	
	private String sifraPredmeta;
	private String nazivPredmeta;
	private String ESPB;
	private String godinaStudija;
	private String semestar;
	
	public NepolozenIspit() {}
	
	public NepolozenIspit(String sifraPredmeta, String nazivPredmeta, String eSPB, String godinaStudija,
			String semestar) {
		super();
		this.sifraPredmeta = sifraPredmeta;
		this.nazivPredmeta = nazivPredmeta;
		ESPB = eSPB;
		this.godinaStudija = godinaStudija;
		this.semestar = semestar;
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

	public String getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(String godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	public String getSemestar() {
		return semestar;
	}

	public void setSemestar(String semestar) {
		this.semestar = semestar;
	}
	
	
	
}
