package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIManager;

import service.FileService;
import view.menubar.MenuBar;
import view.statusbar.StatusBarPanel;
import view.tab.TabPanel;
import view.table.TablePanel;
import view.toolbar.ToolBarPanel;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6370020036393900504L;
	private static MainFrame instance = null;
	
	public static MainFrame getInstance() throws FontFormatException, IOException {	
		if(instance == null) {
			instance = new MainFrame(); 
		}
		return instance;
	}
	
	public MainFrame() throws FontFormatException, IOException {
		
		FileService.getInstance().getFromExtern();
		
		File font_file = new File("assets"+ File.separator +"fonts"+ File.separator +"Montserrat-Regular.ttf");
		Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
		Font sizedFont = font.deriveFont(16f);
		Font comboBoxFont = font.deriveFont(14f);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(sizedFont);
		setAppFont(sizedFont, comboBoxFont);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Studentska služba");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		setSize(toolkit.getScreenSize().width*3/4, toolkit.getScreenSize().height*3/4);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(977, 530));
		Image titleBarLogo = toolkit.getImage("assets"+ File.separator +"icons"+ File.separator +"title_bar_logo.png");
		Image resizedTitleBarLogo = titleBarLogo.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
		setIconImage(resizedTitleBarLogo);
		
		TablePanel tablePanel = new TablePanel();
		
		TabPanel tabPanel = new TabPanel();
		tabPanel.setTablePanel(tablePanel);
		JRootPane rightRootPane = getRightRootPane(this, tablePanel);
	
		add(rightRootPane, BorderLayout.CENTER);
		add(tabPanel, BorderLayout.WEST);
		addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	FileService.getInstance().saveToExtern();
		    	dispose();
		        System.exit(0);
		    }
		});
	}
	
	private void setAppFont(Font myFont, Font comboBoxFont) {
	    UIManager.put("CheckBoxMenuItem.acceleratorFont", myFont);
	    UIManager.put("Button.font", myFont);
	    UIManager.put("ToggleButton.font", myFont);
	    UIManager.put("RadioButton.font", myFont);
	    UIManager.put("CheckBox.font", myFont);
	    UIManager.put("ColorChooser.font", myFont);
	    UIManager.put("ComboBox.font", comboBoxFont);
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
	
	private JRootPane getRightRootPane(JFrame parent, TablePanel tablePanel) {
		JRootPane jRootPane = new JRootPane();
		JPanel rightPanel = new JPanel();
		BoxLayout box = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
		rightPanel.setLayout(box);
		ToolBarPanel toolBarPanel = new ToolBarPanel();
		rightPanel.add(toolBarPanel);
		rightPanel.add(tablePanel);
		StatusBarPanel statusBarPanel = new StatusBarPanel();
		rightPanel.add(statusBarPanel);
		MenuBar menuBar = new MenuBar(parent);
		jRootPane.setJMenuBar(menuBar);
		jRootPane.getContentPane().add(rightPanel);
		return jRootPane;
	}

	public static void main(String[] args) {
		try {
			new MainFrame().setVisible(true);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}