package view.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.StudentController;
import controller.StudentUnsettledSubjectsController;
import controller.SubjectController;
import view.dialogs.components.CustomComboBox;
import view.dialogs.components.CustomTextField;
import view.dialogs.components.DateComboBox;
import view.dialogs.components.DialogConfirmButton;
import view.dialogs.components.DialogDeclineButton;
import view.dialogs.components.studentedit.StudentUnsettledSubjectsTablePanel;
import view.dialogs.components.studentedit.StudentoviPolozeniIspitiTablePanel;
public class GradeEntryDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6873911037058700491L;
	
	private static GradeEntryDialog instance = null;
	
	public static GradeEntryDialog getInstance() {	
		if(instance == null)
			instance = new GradeEntryDialog(StudentEditDialog.getInstance());
		return instance;
	}

	private DialogConfirmButton dialogConfirmButton;
	public static final String[] fieldText = {"Šifra", "Naziv", "Ocena", "Datum"};
	
	@SuppressWarnings("serial")
	public ArrayList<String> gradeList = new ArrayList<String>() {{
			add("6");
			add("7");
			add("8");
			add("9");
			add("10");
	}};
	
	public String[] textFieldName = {"0", "1"};
	
	@SuppressWarnings("serial")
	public ArrayList<Integer> days = new ArrayList<Integer>() {{
		for(int i = 1; i <= 31; i++)
			add(i);
	}};
	@SuppressWarnings("serial")
	public ArrayList<Integer> months = new ArrayList<Integer>() {{
		for(int i = 1; i <= 12; i++)
			add(i);
	}};
	@SuppressWarnings("serial")
	public ArrayList<Integer> years = new ArrayList<Integer>() {{
		for(int i = 1960; i <= 2021; i++)
			add(i);
	}};
	
	public static CustomComboBox customComboBox;
	public static ArrayList<DateComboBox> dateComboBoxes = new ArrayList<DateComboBox>();
	public static ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();

	public static String entityID;

	private GradeEntryDialog(JDialog parent) {
		super(parent, "Unos ocene", true);		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(419, 375));
		setResizable(false);
		pack();
		setLocationRelativeTo(parent);
		
		dialogConfirmButton = new DialogConfirmButton();
		dialogConfirmButton.setEnabled(true);
		dialogConfirmButton.addMouseListener(new MyMouseListener());
		
		JPanel basePanel = new JPanel();
		BoxLayout box = new BoxLayout(basePanel, BoxLayout.Y_AXIS);
		basePanel.setLayout(box);
		basePanel.setBackground(new Color(249,249,249));
		basePanel.add(Box.createVerticalStrut(18));
		basePanel.setPreferredSize(new Dimension(419,315));
		
		
		int textFieldCounter = 0;
		for(int i = 1; i <= 4; i++) {
			JPanel holderPanel = new JPanel();
			holderPanel.setPreferredSize(new Dimension(419, 36));
			holderPanel.setOpaque(false);
			
			
			BoxLayout boxHolder = new BoxLayout(holderPanel, BoxLayout.X_AXIS);
			holderPanel.setLayout(boxHolder);
						
			FieldName fieldName = new FieldName(fieldText[i-1]);
			
			holderPanel.add(fieldName);
			holderPanel.add(Box.createHorizontalStrut(39));
			
			
			if(i == 1 || i == 2) {
				JPanel textPanel = new JPanel();
				textPanel.setPreferredSize(new Dimension(214, 36));
				textPanel.setMinimumSize(new Dimension(214, 36));
				textPanel.setMaximumSize(new Dimension(214, 36));
				textPanel.setLayout(new GridLayout(0,1));
				textPanel.setOpaque(false);
				JTextField textField = new JTextField();
				
				textFieldList.add(textField);
				
				CustomTextField customTextField = new CustomTextField(textField, textFieldName[textFieldCounter++]);
				textPanel.add(customTextField);
				holderPanel.add(textPanel);
			} else if (i == 3){
				customComboBox = new CustomComboBox(gradeList);
				holderPanel.add(customComboBox);
			} else {
				DateComboBox yearsComboBox = new DateComboBox(years, new Dimension(80, 36), "years");
				dateComboBoxes.add(yearsComboBox);
				holderPanel.add(yearsComboBox);
				holderPanel.add(Box.createHorizontalStrut(8));
				DateComboBox monthsComboBox = new DateComboBox(months, new Dimension(58, 36), "months");
				dateComboBoxes.add(monthsComboBox);
				holderPanel.add(monthsComboBox);
				holderPanel.add(Box.createHorizontalStrut(8));
				DateComboBox daysComboBox = new DateComboBox(days, new Dimension(58, 36), "days");
				dateComboBoxes.add(daysComboBox);
				holderPanel.add(daysComboBox);
				
				daysComboBox.setDateComboBoxes(dateComboBoxes);
				monthsComboBox.setDateComboBoxes(dateComboBoxes);
				yearsComboBox.setDateComboBoxes(dateComboBoxes);
			}
			
			holderPanel.add(Box.createHorizontalStrut(43));
			
			basePanel.add(holderPanel);
			if(i == 2) {
				basePanel.add(Box.createVerticalStrut(45));
			} else {
				basePanel.add(Box.createVerticalStrut(18));
			}
			
		}
		
		ButtonHolderPanel buttonHolderPanel = new ButtonHolderPanel(dialogConfirmButton, this);
		
		basePanel.add(buttonHolderPanel);
		basePanel.add(Box.createVerticalStrut(16));
		
		add(basePanel);
	}
	
	public void setProperFields() {
		int textFieldCounter = 0;
		for(JTextField t : textFieldList) {
			t.setEditable(true);
			t.setText(StudentUnsettledSubjectsController.getInstance().getSelectedSubjectValueAt(textFieldCounter++));
			t.setEditable(false);
		}
	}
	
	private class FieldName extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -613397988654719482L;

		public FieldName(String fieldText) {
			
			FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
			layout.setHgap(0);
			setLayout(layout);
			setPreferredSize(new Dimension(123, 36));
			setMinimumSize(new Dimension(123, 36));
			setOpaque(false);
			JPanel fieldNamePanel = new JPanel();
			
			FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER);
			layout1.setHgap(0);
			fieldNamePanel.setLayout(layout1);
			fieldNamePanel.setOpaque(false);
			JLabel labelFieldName = new JLabel(fieldText , SwingConstants.RIGHT);
			labelFieldName.setVerticalAlignment(JLabel.CENTER);
			labelFieldName.setBorder(new EmptyBorder(3,0,0,0));
			fieldNamePanel.add(labelFieldName);
			add(fieldNamePanel);
		}
	}
	
	private class ButtonHolderPanel extends JPanel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 451422552727284984L;

		public ButtonHolderPanel(DialogConfirmButton dialogConfirmButton, JDialog dialog) {
			setPreferredSize(new Dimension(419, 36));
			setOpaque(false);
			BoxLayout buttonHolder = new BoxLayout(this, BoxLayout.X_AXIS);
			setLayout(buttonHolder);
			DialogDeclineButton dialogDeclineButton = new DialogDeclineButton();
			dialogDeclineButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dialog.dispose();
				}
			});
			
			add(Box.createHorizontalStrut(162));
			add(dialogDeclineButton);
			add(Box.createHorizontalStrut(30));
			add(dialogConfirmButton);
			add(Box.createHorizontalStrut(43));
		}
	}
	
	private class MyMouseListener extends MouseAdapter{
		public void mouseEntered(MouseEvent mouseEvent) {
			JButton thisButton = (JButton) mouseEvent.getComponent();
			thisButton.setIcon(dialogConfirmButton.hoveredConfirmIcon);
			new Thread(
					new Runnable() {
						public void run() {
							for (float i = 0.75f; i <= 1f; i += 0.02f){
								dialogConfirmButton.setAlpha(i);
								try {
									Thread.sleep(10);
								} catch (Exception e) {
									
								}
							}
						}
					}).start();
			

		}
		public void mouseExited(MouseEvent mouseEvent) {
			JButton thisButton = (JButton) mouseEvent.getComponent();
			new Thread(
					new Runnable() {
						public void run() {
							for (float i = 1f; i >= 0.8f; i -= 0.07f){
								dialogConfirmButton.setAlpha(i);
								try {
									Thread.sleep(10);
								} catch (Exception e) {
									
								}
							}
							thisButton.setIcon(dialogConfirmButton.confirmIcon);
							dialogConfirmButton.setAlpha(1f);
						}
					}).start();
		}
		public void mousePressed(MouseEvent mouseEvent) {
			
			StudentController.getInstance().izbrisiPredmetIzListeNepolozenih();
			
			int ocena = Integer.parseInt(customComboBox.getField());

			String date = DateComboBox.dateString;
			LocalDate localDate;
			if(date.contentEquals("yyyy-MM-dd")) {
				localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			} else if(date.contentEquals("yyyy-M-dd")){
				localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-M-dd"));
			} else if(date.contentEquals("yyyy-MM-d")){
				localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-d"));
			} else {
				localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d"));
			}
			StudentUnsettledSubjectsTablePanel.setSelectedSubject(-1);
			StudentController.getInstance().dodajStudentuPredmetUListuPolozenih(ocena, localDate);
			String prosek = SubjectController.getInstance().izracunajProsek();
			String espb = SubjectController.getInstance().izracunajESPB();
			StudentoviPolozeniIspitiTablePanel.getInstance().updateProsekAndESPB(prosek , espb);
			dispose();
				
		}
	}

}
