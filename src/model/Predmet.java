package model;

import java.util.List;

enum TipSemestra{LETNJI, ZIMSKI};

public class Predmet {
	
	private String sifraPredmeta;
	private String nazivPredmeta;
	private TipSemestra semestar;
	private int godinaStudija; // Od 1-10
	private Profesor predmetniProfesor;
	private int brojESPB;
	private List<Student> listaStudentataPol;
	private List<Student> listaStudenataNepol;
	
	
	public Predmet(String sifraPredmeta, String nazivPredmeta, TipSemestra semestar, int godinaStudija,
			Profesor predmetniProfesor, int brojESPB, List<Student> listaStudentataPol, List<Student> listaStudenataNepol) {
		super();
		this.sifraPredmeta = sifraPredmeta;
		this.nazivPredmeta = nazivPredmeta;
		this.semestar = semestar;
		this.godinaStudija = godinaStudija;
		this.predmetniProfesor = predmetniProfesor;
		this.brojESPB = brojESPB;
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


	public TipSemestra getSemestar() {
		return semestar;
	}


	public void setSemestar(TipSemestra semestar) {
		this.semestar = semestar;
	}


	public int getGodinaStudija() {
		return godinaStudija;
	}


	public void setGodinaStudija(int godinaStudija) {
		this.godinaStudija = godinaStudija;
	}


	public Profesor getPredmetniProfesor() {
		return predmetniProfesor;
	}


	public void setPredmetniProfesor(Profesor predmetniProfesor) {
		this.predmetniProfesor = predmetniProfesor;
	}
	
	public int getBrojESPB() {
		return brojESPB;
	}


	public void setBrojESPB(int brojESPB) {
		this.brojESPB = brojESPB;
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
