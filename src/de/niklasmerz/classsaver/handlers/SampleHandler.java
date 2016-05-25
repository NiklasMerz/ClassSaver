package de.niklasmerz.classsaver.handlers;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String path = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		path = path + "/test/src/test/test.java";

		try {
			//Read and rewrite completely
			FileWriter out = new FileWriter(path, true);
			out.write("");
			out.close();
		} catch(FileNotFoundException e){
			path = "Not found: " + path;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(window.getShell(), "ClassSaver", path);
		return null;
	}
}
