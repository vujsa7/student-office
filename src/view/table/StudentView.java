package view.table;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.StudentController;
import model.Student;

public class StudentView extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Student student;
	private StudentController studentController;
	
	private JPanel pnlcontent;
	private JLabel indeks;
	private JTextField tfindeks;
	private JLabel ime;
	private JTextField tfime;
	private JLabel prezime;
	private JTextField tfprezime;
	private JLabel godinaStudija;
	private JTextField tfgodinaStudija;
	private JLabel status;
	private JTextField tfstatus;
	private JLabel prosek;
	private JTextField tfprosek;
	
	
}
