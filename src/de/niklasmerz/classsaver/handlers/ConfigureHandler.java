package de.niklasmerz.classsaver.handlers;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ConfigureHandler extends ClassSaver{

	/**
	 * The constructor.
	 */
	public ConfigureHandler() {
		super();
	}

	/**
	 * Open Config Dialog
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		JTextField pathField = new JTextField(path);
		Object[] message = { "Path in workspace", pathField};

		JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		pane.createDialog(null, "ClassSaver Settings").setVisible(true);

		String fullPath = pathField.getText();
		preferenceStore.setValue(PATH_KEY, fullPath);

		loadSettings();
		return null;
	}
}
