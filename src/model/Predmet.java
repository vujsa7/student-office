package model;

import java.util.List;

public class Predmet {
	
	private String sifraPredmeta;
	private String nazivPredmeta;
	private String semestar;
	private String godinaStudija;
	private List<Student> listaStudentataPol;
	private List<Student> listaStudenataNepol;
	
	
	public Predmet(String sifraPredmeta, String nazivPredmeta, String semestar, String godinaStudija,
			List<Student> listaStudentataPol, List<Student> listaStudenataNepol) {
		super();
		this.sifraPredmeta = sifraPredmeta;
		this.nazivPredmeta = nazivPredmeta;
		this.semestar = semestar;
		this.godinaStudija = godinaStudija;
		this.listaStudentataPol = listaStudentataPol;
		this.listaStudenataNepol = listaStudenataNepol;
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


	public String getSemestar() {
		return semestar;
	}


	public void setSemestar(String semestar) {
		this.semestar = semestar;
	}


	public String getGodinaStudija() {
		return godinaStudija;
	}


	public void setGodinaStudija(String godinaStudija) {
		this.godinaStudija = godinaStudija;
	}



	public List<Student> getListaStudentataPol() {
		return listaStudentataPol;
	}


	public void setListaStudentataPol(List<Student> listaStudentataPol) {
		this.listaStudentataPol = listaStudentataPol;
	}


	public List<Student> getListaStudenataNepol() {
		return listaStudenataNepol;
	}


	public void setListaStudenataNepol(List<Student> listaStudenataNepol) {
		this.listaStudenataNepol = listaStudenataNepol;
	}

	

}