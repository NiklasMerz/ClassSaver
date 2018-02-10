/**
 *
 */
package de.niklasmerz.classsaver.handlers;

import java.io.InputStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;

import de.niklasmerz.classsaver.Activator;
import de.niklasmerz.classsaver.CSLog;
import de.niklasmerz.classsaver.ClassSaverStrings;

/**
 * Handler superclass
 * 
 * @author niklas
 * 
 */
public abstract class ClassSaver extends AbstractHandler implements
		ClassSaverStrings {

	protected IPreferenceStore preferenceStore;
	protected String path;
	protected String project;
	protected String className;
	protected String workspacepath;
	protected String[] paths;

	/**
	 * Get preferences
	 */
	public ClassSaver() {
		workspacepath = ResourcesPlugin.getWorkspace().getRoot().getLocation()
				.toString();
		preferenceStore = Activator.getDefault().getPreferenceStore();
		preferenceStore.setDefault(PATH_KEY, DEFAULT_PATH);
		preferenceStore.setDefault(PROJECT_KEY, DEFAULT_PROJECT);
		preferenceStore.setDefault(CLASS_KEY, DEFAULT_CLASS);
		loadSettings();
	}

	/**
	 * Load Settings
	 */
	protected void loadSettings() {
		path = preferenceStore.getString(PATH_KEY);
		project = preferenceStore.getString(PROJECT_KEY);
		className = preferenceStore.getString(CLASS_KEY);
		paths = preferenceStore.getString(PATHSELECTION_KEY).split("~");

	}

	/**
	 * Save given eclipse project
	 * 
	 * @param project
	 */
	protected void saveProject(IProject project, boolean automode) {
		try {
			if (project.exists()) {
				if (!project.isOpen()) {
					project.open(null);

				}

				if (automode) {
					this.saveFolder(this.path, project);
				} else {
					for (String path : this.paths) {
						this.saveFolder(path, project);
					}
				}
			}
		} catch (CoreException e) {
			CSLog.logError("Project not found", e);
		}
	}

	/**
	 * Get class from folder and append nothing to save file
	 * 
	 * @param folderPath
	 * @param project
	 */
	private void saveFolder(String folderPath, IProject project) {
		IFolder folder = project.getFolder(folderPath);
		try {
			if (folder.exists()) {
				IFile file = folder.getFile(this.className);
				InputStream in = file.getContents();
				file.appendContents(in, true, false, null);
				
				String[] buttons = {"OK"};
				MessageDialog dlg = new MessageDialog(null, "ClassSaver", null,
						"Saved class" + folderPath + className,
						MessageDialog.INFORMATION, buttons, 0);
				dlg.open();
			}
		} catch (CoreException e) {
			CSLog.logError(e);
		}
	}
}
