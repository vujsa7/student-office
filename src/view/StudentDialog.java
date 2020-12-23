package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.MainFrame;

public class StudentDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StudentDialog() throws FontFormatException, IOException{
		
		
		setPreferredSize(new Dimension(600, 450));
		setResizable(false);
		pack();
		setLocationRelativeTo(MainFrame.getInstance());
		
		setVisible(true);
		
		
		MyFocusListener focusListener = new MyFocusListener();
		JPanel panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxLayout);
		
		Dimension dim=new Dimension(200,20);
		
		JPanel panIme = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblIme = new JLabel("Ime*: ");
		lblIme.setPreferredSize(dim);
		
		JTextField txtIme=new JTextField();
        txtIme.setPreferredSize(dim);
        txtIme.setBackground(Color.GRAY);
        txtIme.setName("txtIme");
        txtIme.addFocusListener(focusListener);
        panIme.add(lblIme);
        panIme.add(txtIme);
		
        
        JPanel panPrezime = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblPrezime = new JLabel("Prezime*: ");
		lblPrezime.setPreferredSize(dim);
		
		JTextField txtPrezime=new JTextField();
        txtPrezime.setPreferredSize(dim);
        txtPrezime.setBackground(Color.GRAY);
        txtPrezime.setName("txtPrezime");
        txtPrezime.addFocusListener(focusListener);
        panPrezime.add(lblPrezime);
        panPrezime.add(txtPrezime);
        
        
        JPanel panRodj = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblRodj = new JLabel("Datum rodjenja*: ");
		lblRodj.setPreferredSize(dim);
		
		JTextField txtRodj=new JTextField();
        txtRodj.setPreferredSize(dim);
        txtRodj.setBackground(Color.GRAY);
        txtRodj.setName("txtRodj");
        txtRodj.addFocusListener(focusListener);
        panRodj.add(lblRodj);
        panRodj.add(txtRodj);
        
        
        JPanel panAdr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblAdr = new JLabel("Adresa stanovanja*: ");
		lblAdr.setPreferredSize(dim);
		
		JTextField txtAdr=new JTextField();
        txtAdr.setPreferredSize(dim);
        txtAdr.setBackground(Color.GRAY);
        txtAdr.setName("txtAdr");
        txtAdr.addFocusListener(focusListener);
        panAdr.add(lblAdr);
        panAdr.add(txtAdr);
        
        
        JPanel panBr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblBr = new JLabel("Broj telefona*: ");
		lblBr.setPreferredSize(dim);
		
		JTextField txtBr=new JTextField();
        txtBr.setPreferredSize(dim);
        txtBr.setBackground(Color.GRAY);
        txtBr.setName("txtBr");
        txtBr.addFocusListener(focusListener);
        panBr.add(lblBr);
        panBr.add(txtBr);
        
        
        JPanel panMail = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMail = new JLabel("E-mail adresa*: ");
		lblMail.setPreferredSize(dim);
		
		JTextField txtMail=new JTextField();
        txtMail.setPreferredSize(dim);
        txtMail.setBackground(Color.GRAY);
        txtMail.setName("txtMail");
        txtMail.addFocusListener(focusListener);
        panMail.add(lblMail);
        panMail.add(txtMail);
        
        
        JPanel panIdx = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblIdx = new JLabel("Broj indeksa*: ");
		lblIdx.setPreferredSize(dim);
		
		JTextField txtIdx=new JTextField();
        txtIdx.setPreferredSize(dim);
        txtIdx.setBackground(Color.GRAY);
        txtIdx.setName("txtIdx");
        txtIdx.addFocusListener(focusListener);
        panIdx.add(lblIdx);
        panIdx.add(txtIdx);
        
        
        JPanel panUpis = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblUpis = new JLabel("Godina upisa*: ");
		lblUpis.setPreferredSize(dim);
		
		JTextField txtUpis=new JTextField();
        txtUpis.setPreferredSize(dim);
        txtUpis.setBackground(Color.GRAY);
        txtUpis.setName("txtUpis");
        txtUpis.addFocusListener(focusListener);
        panUpis.add(lblUpis);
        panUpis.add(txtUpis);
        
        
        JPanel panStud = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblStud = new JLabel("Trenutna godina studija*: ");
		lblStud.setPreferredSize(dim);
		
		String[] godinaStudija = { "I(prva)", "II(druga)", "III(treća)", "IV(četvrta)", };
		JComboBox<String> combo = new JComboBox<String>(godinaStudija);
        combo.setPreferredSize(dim);
        panStud.add(lblStud);
        panStud.add(combo);
        
        
        JPanel panFin = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblFin = new JLabel("Način finansiranja*: ");
		lblFin.setPreferredSize(dim);
		
		String[] statusStudenta = { "Budžet", "Samofinansiranje", };
		JComboBox<String> comboBox = new JComboBox<String>(statusStudenta);
		comboBox.setPreferredSize(dim);
        panFin.add(lblFin);
        panFin.add(comboBox);
        
        
        panel.add(panIme);
        panel.add(panPrezime);
        panel.add(panRodj);
        panel.add(panAdr);
        panel.add(panBr);
        panel.add(panMail);
        panel.add(panIdx);
        panel.add(panUpis);
        panel.add(panStud);
        panel.add(panFin);
        panel.add(Box.createVerticalStrut(50));
        
        add(panel, BorderLayout.CENTER);
        
        JPanel panBottom=new JPanel();
		BoxLayout box=new BoxLayout(panBottom, BoxLayout.X_AXIS);
		panBottom.setLayout(box);
		
		JButton btnPotvrdi=new JButton("Potvrdi");
		btnPotvrdi.setPreferredSize(new Dimension(100,25));
		
		JButton btnOdustani=new JButton("Odustani");
		btnOdustani.setPreferredSize(new Dimension(100,25));
		btnOdustani.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		panBottom.add(Box.createHorizontalStrut(75));
		panBottom.add(btnPotvrdi);
		panBottom.add(Box.createGlue());
		panBottom.add(Box.createHorizontalStrut(10));
		panBottom.add(btnOdustani);
		panBottom.add(Box.createHorizontalStrut(10));
		
		
		add(panBottom,BorderLayout.SOUTH);
		
		
	}
}
