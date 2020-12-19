package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class RightPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5726614048951072812L;

	public RightPanel() {
		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(box);
		JPanel toolBarPanel = new ToolBarPanel();
		add(toolBarPanel);
		JPanel tablePanel = new JPanel(); // ovde nemamo preffered, zbog toga box layout ce da prosiri toolbarpanel ako toolbarpanel nema maximum size
		add(tablePanel);
		StatusBarPanel statusBarPanel = new StatusBarPanel();
		add(statusBarPanel);
	}
}
