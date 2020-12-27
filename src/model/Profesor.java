package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Profesor {
	
	private String ime;
	private String prezime;
	private LocalDate datumRodjenja;
	private String adresaStanovanja;
	private String kontaktTelefon;
	private String emailAdresa;
	private String adresaKancelarije;
	private String brojLicneKarte;
	private String titula;
	private String zvanje;
	private List<Predmet> listaPredmeta;
	
	
	public Profesor(String ime, String prezime, LocalDate localDate, String adresaStanovanja, String kontaktTelefon,
			String emailAdresa, String adresaKancelarije, String brojLicneKarte, String titula, String zvanje,
			List<Predmet> listaPredmeta) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = localDate;
		this.adresaStanovanja = adresaStanovanja;
		this.kontaktTelefon = kontaktTelefon;
		this.emailAdresa = emailAdresa;
		this.adresaKancelarije = adresaKancelarije;
		this.brojLicneKarte = brojLicneKarte;
		this.titula = titula;
		this.zvanje = zvanje;
		this.listaPredmeta = listaPredmeta;
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


	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}


	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}


	public String getAdresaStanovanja() {
		return adresaStanovanja;
	}


	public void setAdresaStanovanja(String adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}


	public String getKontaktTelefon() {
		return kontaktTelefon;
	}


	public void setKontaktTelefon(String kontaktTelefon) {
		this.kontaktTelefon = kontaktTelefon;
	}


	public String getEmailAdresa() {
		return emailAdresa;
	}


	public void setEmailAdresa(String emailAdresa) {
		this.emailAdresa = emailAdresa;
	}


	public String getAdresaKancelarije() {
		return adresaKancelarije;
	}


	public void setAdresaKancelarije(String adresaKancelarije) {
		this.adresaKancelarije = adresaKancelarije;
	}


	public String getBrojLicneKarte() {
		return brojLicneKarte;
	}


	public void setBrojLicneKarte(String brojLicneKarte) {
		this.brojLicneKarte = brojLicneKarte;
	}


	public String getTitula() {
		return titula;
	}


	public void setTitula(String titula) {
		this.titula = titula;
	}


	public String getZvanje() {
		return zvanje;
	}


	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}


	public List<Predmet> getListaPredmeta() {
		return listaPredmeta;
	}


	public void setListaPredmeta(List<Predmet> listaPredmeta) {
		this.listaPredmeta = listaPredmeta;
	}
	
	

	

}