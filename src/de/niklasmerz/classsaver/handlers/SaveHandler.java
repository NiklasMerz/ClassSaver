package de.niklasmerz.classsaver.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

import de.niklasmerz.classsaver.CSLog;

/**
 * Handler for saving
 * 
 * @author niklas
 */
public class SaveHandler extends ClassSaver {
	/**
	 * Constructor loads settings
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
		String output = this.project + "/" + this.path + "/" + this.className;

		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

		IProject myProject = myWorkspaceRoot.getProject(this.project);
		this.saveProject(myProject, false);
		CSLog.logInfo(output);
		return null;
	}
}
