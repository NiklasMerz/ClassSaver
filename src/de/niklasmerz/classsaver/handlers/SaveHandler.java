package de.niklasmerz.classsaver.handlers;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import de.niklasmerz.classsaver.CSLog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SaveHandler extends ClassSaver {
	/**
	 * The constructor.
	 */
	public SaveHandler() {
		super();
		loadSettings();
	}

	/**
	 * Save configured file
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		loadSettings();
		String output = path;

		try {
			output = "Saved: " + path;
			// Read and rewrite completely
			FileWriter out = new FileWriter(path, true);
			out.write(" ");
			out.close();
		} catch (FileNotFoundException e) {
			CSLog.logError(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			CSLog.logError(e);
		}
		CSLog.logInfo(output);
		JOptionPane.showMessageDialog(null, output);
		return null;
	}
}
