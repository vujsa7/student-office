package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;

import view.RightRootPane;
import view.TabPanel;
import view.TablePanel;

public class MainFrame extends JFrame {

	public MainFrame() throws FontFormatException, IOException {
		
		File font_file = new File("assets/fonts/Montserrat-Regular.ttf");
		Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
		Font sizedFont = font.deriveFont(12f);
		setAppFont(sizedFont);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Studentska služba");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		setSize(toolkit.getScreenSize().width*3/4, toolkit.getScreenSize().height*3/4);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(977, 530));
		Image titleBarLogo = toolkit.getImage("assets/icons/titleBarLogo.png");
		setIconImage(titleBarLogo);
		
		TablePanel tablePanel = new TablePanel();
		
		TabPanel tabPanel = new TabPanel();
		tabPanel.setTablePanel(tablePanel);
		RightRootPane rightRootPane = new RightRootPane(this, tablePanel);
	
		add(rightRootPane, BorderLayout.CENTER);
		add(tabPanel, BorderLayout.WEST);


	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6370020036393900504L;
	
	private void setAppFont(Font myFont) {
	    UIManager.put("CheckBoxMenuItem.acceleratorFont", myFont);
	    UIManager.put("Button.font", myFont);
	    UIManager.put("ToggleButton.font", myFont);
	    UIManager.put("RadioButton.font", myFont);
	    UIManager.put("CheckBox.font", myFont);
	    UIManager.put("ColorChooser.font", myFont);
	    UIManager.put("ComboBox.font", myFont);
	    UIManager.put("Label.font", myFont);
	    UIManager.put("List.font", myFont);
	    UIManager.put("MenuBar.font", myFont);
	    UIManager.put("Menu.acceleratorFont", myFont);
	    UIManager.put("RadioButtonMenuItem.acceleratorFont", myFont);
	    UIManager.put("MenuItem.acceleratorFont", myFont);
	    UIManager.put("MenuItem.font", myFont);
	    UIManager.put("RadioButtonMenuItem.font", myFont);
	    UIManager.put("CheckBoxMenuItem.font", myFont);
	    UIManager.put("OptionPane.buttonFont", myFont);
	    UIManager.put("OptionPane.messageFont", myFont);
	    UIManager.put("Menu.font", myFont);
	    UIManager.put("PopupMenu.font", myFont);
	    UIManager.put("OptionPane.font", myFont);
	    UIManager.put("Panel.font", myFont);
	    UIManager.put("ProgressBar.font", myFont);
	    UIManager.put("ScrollPane.font", myFont);
	    UIManager.put("Viewport.font", myFont);
	    UIManager.put("TabbedPane.font", myFont);
	    UIManager.put("Slider.font", myFont);
	    UIManager.put("Table.font", myFont);
	    UIManager.put("TableHeader.font", myFont);
	    UIManager.put("TextField.font", myFont);
	    UIManager.put("Spinner.font", myFont);
	    UIManager.put("PasswordField.font", myFont);
	    UIManager.put("TextArea.font", myFont);
	    UIManager.put("TextPane.font", myFont);
	    UIManager.put("EditorPane.font", myFont);
	    UIManager.put("TabbedPane.smallFont", myFont);
	    UIManager.put("TitledBorder.font", myFont);
	    UIManager.put("ToolBar.font", myFont);
	    UIManager.put("ToolTip.font", myFont);
	    UIManager.put("Tree.font", myFont);
	    UIManager.put("FormattedTextField.font", myFont);
	    UIManager.put("IconButton.font", myFont);
	    UIManager.put("InternalFrame.optionDialogTitleFont", myFont);
	    UIManager.put("InternalFrame.paletteTitleFont", myFont);
	    UIManager.put("InternalFrame.titleFont", myFont);
	}

	public static void main(String[] args) throws FontFormatException, IOException {
		new MainFrame().setVisible(true);
	}

}