package model;

public class Student {
	
	private String ime;
	private String prezime;
	private String datumRodjenja;
	private String adresaStanovanja;
	private String broj_telefona;
	private String email;
	private String broj_indeksa;
	private String god_upisa;
	private String trenutnaGodStrudija;
	private String status;
	private String prosek;
	
	public Student(String ime, String prezime, String datumRodjenja, String adresaStanovanja, String broj_telefona,
			String email, String broj_indeksa, String god_upisa, String trenutnaGodStrudija, String status,
			String prosek) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.adresaStanovanja = adresaStanovanja;
		this.broj_telefona = broj_telefona;
		this.email = email;
		this.broj_indeksa = broj_indeksa;
		this.god_upisa = god_upisa;
		this.trenutnaGodStrudija = trenutnaGodStrudija;
		this.status = status;
		this.prosek = prosek;
	}
	public String getProsek() {
		return prosek;
	}
	public void setProsek(String prosek) {
		this.prosek = prosek;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getAdresaStanovanja() {
		return adresaStanovanja;
	}
	public void setAdresaStanovanja(String adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}
	public String getBroj_telefona() {
		return broj_telefona;
	}
	public void setBroj_telefona(String broj_telefona) {
		this.broj_telefona = broj_telefona;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBroj_indeksa() {
		return broj_indeksa;
	}
	public void setBroj_indeksa(String broj_indeksa) {
		this.broj_indeksa = broj_indeksa;
	}
	public String getGod_upisa() {
		return god_upisa;
	}
	public void setGod_upisa(String god_upisa) {
		this.god_upisa = god_upisa;
	}
	public String getTrenutnaGodStrudija() {
		return trenutnaGodStrudija;
	}
	public void setTrenutnaGodStrudija(String trenutnaGodStrudija) {
		this.trenutnaGodStrudija = trenutnaGodStrudija;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
