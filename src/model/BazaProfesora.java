package model;

import java.util.ArrayList;
import java.util.List;


public class BazaProfesora {

	private static BazaProfesora instance = null;
	
	private List<Profesor> professors;
	private List<String> columns;
	
	private BazaProfesora() {
		initialize();
	}
	
	private void initialize() {
		
		initProfesore();
		
		this.columns = new ArrayList<String>();
		this.columns.add("Ime");
		this.columns.add("Prezime");
		this.columns.add("Titula");
		this.columns.add("Zvanje");
	}
	
	private void initProfesore() {
		this.professors = new ArrayList<Profesor>();
		professors.add(new Profesor("Mika", "Mikic", "19-19-2018", "Dubravska 12", "0617219096", "aleksvuj@yahoo.com", "FTN-park 18", "1293929312", "Inzenjer", "DR", null));
		professors.add(new Profesor("Mika", "Mikic", "19-19-2018", "Dubravska 12", "0617219096", "aleksvuj@yahoo.com", "FTN-park 18", "1293929312", "Inzenjer", "DR", null));
		professors.add(new Profesor("Mika", "Mikic", "19-19-2018", "Dubravska 12", "0617219096", "aleksvuj@yahoo.com", "FTN-park 18", "1293929312", "Inzenjer", "DR", null));
	}
	
	public static BazaProfesora getInstance() {
		if(instance == null) {
			instance = new BazaProfesora();
		}
		return instance;
	}
	
	// ne znam da li ce mi trebati
	public List<Profesor> getProfessors() {
		return professors;
	}

	// ne znam da li ce mi trebati
	public void setProfessors(List<Profesor> profesori) {
		this.professors = profesori;
	}
	
	public int getColumnCount() {
		return 4;
	}
	
	public String getValueAt(int row, int column) {
		Profesor professor = this.professors.get(row);
		switch (column) {
		case 0:
			return professor.getIme();
		case 1:
			return professor.getPrezime();
		case 2:
			return professor.getTitula();
		case 3:
			return professor.getZvanje();
		default:
			return null;
		}
	}
	
	public String getColumnName(int index) {
		return this.columns.get(index);
	}
	
	public Profesor getRow(int rowIndex) {
		return this.professors.get(rowIndex);
	}


}
