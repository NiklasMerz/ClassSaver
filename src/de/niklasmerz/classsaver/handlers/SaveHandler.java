package de.niklasmerz.classsaver.handlers;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

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
	 * Savae configured file
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
			output = "Not found: " + path;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(output);
		return null;
	}
}
