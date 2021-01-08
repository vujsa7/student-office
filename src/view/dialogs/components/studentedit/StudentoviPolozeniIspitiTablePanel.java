package view.dialogs.components.studentedit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.PolozeniStudentiController;
import controller.StudentController;
import controller.SubjectController;
import model.AbstractStudentoviPolozeniIspitiTable;
import view.dialogs.tables.StudentoviPolozeniIspitiTable;
import view.table.TablePanel;

public class StudentoviPolozeniIspitiTablePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9182571236503153102L;
	private static int selectedIspit = -1;
	
	private static StudentoviPolozeniIspitiTablePanel instance = null;
	
	public static StudentoviPolozeniIspitiTablePanel getInstance() {
		if(instance == null)
			instance = new StudentoviPolozeniIspitiTablePanel();
		
		return instance;
	}
	
	private JTable polozeniIspiti;
	public static JLabel prosekLabel;
	public static JLabel espbLabel;
	
	public StudentoviPolozeniIspitiTablePanel() {
		setLayout(new GridLayout(0,1));
		
		JPanel basePanel = new JPanel();
		
		//JPanel ispitiPanel = new JPanel();
		BoxLayout box = new BoxLayout(basePanel, BoxLayout.Y_AXIS);
		basePanel.setLayout(box);
		basePanel.setBorder(new EmptyBorder(40, 40, 40, 40));
		basePanel.setBackground(new Color(249, 249, 249));
		
		JPanel polozeniIspitiButtonPanel = new JPanel();
		polozeniIspitiButtonPanel.setPreferredSize(new Dimension(1000, 36));
		polozeniIspitiButtonPanel.setMaximumSize(new Dimension(1000, 36));
		polozeniIspitiButtonPanel.setOpaque(false);
		BoxLayout buttonHolder = new BoxLayout(polozeniIspitiButtonPanel, BoxLayout.X_AXIS);
		polozeniIspitiButtonPanel.setLayout(buttonHolder);
		
		//polozeniIspitiButtonPanel.add(Box.createHorizontalStrut(25));
		PonistiOcenuButton ponistiButton = new PonistiOcenuButton();
		polozeniIspitiButtonPanel.add(ponistiButton);
		
		polozeniIspiti = new StudentoviPolozeniIspitiTable();
		polozeniIspiti.addMouseListener(new MyMouseListener());
		
		JScrollPane polozeniIspitiScrollPane = new JScrollPane(polozeniIspiti);
		polozeniIspitiScrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		JPanel prosek = new JPanel();
		prosek.setPreferredSize(new Dimension(1000, 30));
		prosek.setMaximumSize(new Dimension(1000, 30));
		prosek.setOpaque(false);
		BoxLayout layoutProsek = new BoxLayout(prosek, BoxLayout.X_AXIS);
		prosek.setLayout(layoutProsek);
		if(!StudentController.getInstance().pronadjiStudentovePolozeneIspite(TablePanel.getInstance().getSelectedEntityID()).isEmpty())
			prosekLabel = new JLabel("Prosečna ocena: " + SubjectController.getInstance().izracunajProsek());
		else
			prosekLabel = new JLabel();
		
		prosek.add(Box.createHorizontalStrut(550));
		prosek.add(prosekLabel);
		
		
		JPanel ukupnoespb = new JPanel();
		ukupnoespb.setPreferredSize(new Dimension(1000, 30));
		ukupnoespb.setMaximumSize(new Dimension(1000, 30));
		ukupnoespb.setOpaque(false);
		BoxLayout layoutESPB = new BoxLayout(ukupnoespb, BoxLayout.X_AXIS);
		ukupnoespb.setLayout(layoutESPB);
		if(!StudentController.getInstance().pronadjiStudentovePolozeneIspite(TablePanel.getInstance().getSelectedEntityID()).isEmpty())
			espbLabel = new JLabel("Ukupno ESPB: " + SubjectController.getInstance().izracunajESPB());
		else
			espbLabel = new JLabel();
		
		ukupnoespb.add(Box.createHorizontalStrut(550));
		ukupnoespb.add(espbLabel);
		
		
		basePanel.add(polozeniIspitiButtonPanel);
		basePanel.add(Box.createVerticalStrut(20));
		basePanel.add(polozeniIspitiScrollPane);
		
		
		basePanel.add(Box.createVerticalStrut(15));
		basePanel.add(prosek);
		basePanel.add(Box.createVerticalStrut(15));
		basePanel.add(ukupnoespb);
		basePanel.add(Box.createVerticalStrut(80));
		
		add(basePanel);
	}
	
	public void refreshView() {
		AbstractStudentoviPolozeniIspitiTable model = (AbstractStudentoviPolozeniIspitiTable) polozeniIspiti.getModel();
		model.fireTableDataChanged();
		validate();
	}
	
	public String getSelectedIspit() {
		if(selectedIspit != -1) {
			return PolozeniStudentiController.getInstance().getSelectedIspit(selectedIspit);
		} else
			return "NO_SELECTION";
	}
	
	public void updateProsekAndESPB(String prosek, String espb) {
		espbLabel.setText(espb);
		prosekLabel.setText(prosek);
	}
	
	
	class MyMouseListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent mouseEvent) {
			if(!polozeniIspiti.getSelectionModel().isSelectionEmpty())
				selectedIspit = polozeniIspiti.convertRowIndexToModel(polozeniIspiti.getSelectedRow());
			else
				selectedIspit = -1;
		}
		
	}
}
