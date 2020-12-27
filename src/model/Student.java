package model;

import java.time.LocalDate;
import java.util.List;

public class Student {
	
	private String ime;
	private String prezime;
	private LocalDate datumRodjenja;
	private String adresaStanovanja;
	private String brojTelefona;
	private String email;
	private String brojIndeksa;
	private String godUpisa;
	private String trenutnaGodStrudija;
	private String status;
	private String prosek;
	private List<PolozenIspit> polozeniIspiti;
	private List<NepolozenIspit> nepolozeniIspiti;
	
	Student() {}
	
	public Student(String ime, String prezime, LocalDate datumRodjenja, String adresaStanovanja, String brojTelefona,
			String email, String brojIndeksa, String godUpisa, String trenutnaGodStrudija, String status, String prosek,
			List<PolozenIspit> polozeniIspiti, List<NepolozenIspit> nepolozeniIspiti) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.adresaStanovanja = adresaStanovanja;
		this.brojTelefona = brojTelefona;
		this.email = email;
		this.brojIndeksa = brojIndeksa;
		this.godUpisa = godUpisa;
		this.trenutnaGodStrudija = trenutnaGodStrudija;
		this.status = status;
		this.prosek = prosek;
		this.polozeniIspiti = polozeniIspiti;
		this.nepolozeniIspiti = nepolozeniIspiti;
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
	public String getBroj_telefona() {
		return brojTelefona;
	}
	public void setBroj_telefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBrojIndeksa() {
		return brojIndeksa;
	}
	public void setBroj_indeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}
	public String getGod_upisa() {
		return godUpisa;
	}
	public void setGod_upisa(String godUpisa) {
		this.godUpisa = godUpisa;
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

	public List<PolozenIspit> getPolozeniIspiti() {
		return polozeniIspiti;
	}

	public void setPolozeniIspiti(List<PolozenIspit> polozeniIspiti) {
		this.polozeniIspiti = polozeniIspiti;
	}

	public List<NepolozenIspit> getNepolozeniIspiti() {
		return nepolozeniIspiti;
	}

	public void setNepolozeniIspiti(List<NepolozenIspit> nepolozeniIspiti) {
		this.nepolozeniIspiti = nepolozeniIspiti;
	}
	
	
}
