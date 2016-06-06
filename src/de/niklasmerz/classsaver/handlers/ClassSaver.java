/**
 * 
 */
package de.niklasmerz.classsaver.handlers;

import java.io.InputStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.preference.IPreferenceStore;

import de.niklasmerz.classsaver.Activator;
import de.niklasmerz.classsaver.CSLog;
import de.niklasmerz.classsaver.ClassSaverStrings;

/**
 * @author niklas
 *
 */
public abstract class ClassSaver extends AbstractHandler implements ClassSaverStrings{
	
	
	protected IPreferenceStore preferenceStore;
	protected String path;
	protected String project;
	protected String className;
	protected String workspacepath;
	protected String fileExtension;
	
	/**
	 * TODO Necessary?
	 */
	protected Boolean auto;
	
	/**
	 * Get preferences
	 */
	public ClassSaver(){
		workspacepath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		preferenceStore = Activator.getDefault().getPreferenceStore();
		preferenceStore.setDefault(PATH_KEY, DEFAULT_PATH);
		preferenceStore.setDefault(PROJECT_KEY, DEFAULT_PROJECT);
		preferenceStore.setDefault(CLASS_KEY, DEFAULT_CLASS);
		preferenceStore.setDefault(AUTO_KEY, DEFAULT_AUTO);
		preferenceStore.setDefault(EXTENSION_KEY, DEFAULT_EXTENSION);
		loadSettings();
	}
	
	/**
	 * Load Settings
	 */
	protected void loadSettings() {
		path = preferenceStore.getString(PATH_KEY);
		project = preferenceStore.getString(PROJECT_KEY);
		className = preferenceStore.getString(CLASS_KEY);
		auto = preferenceStore.getBoolean(AUTO_KEY);
		fileExtension = preferenceStore.getString(EXTENSION_KEY);
	}
	
	/**
	 * Save class defined in Settings
	 */
	protected void  saveConfiguredClass() {
		loadSettings();
		String output = project + "/" + path + "/" + className;

		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

		IProject myProject = myWorkspaceRoot.getProject(project);
		if (myProject.exists()){
			try {
				if(!myProject.isOpen()){
					myProject.open(null);
				}
				
				IFolder folder = myProject.getFolder(path);
				   if(folder.exists()) {
				      IFile file = folder.getFile(className);
				      InputStream in = file.getContents();
				      file.appendContents(in, true, false, null);
				   }else{
					   CSLog.logInfo("Folder not found");
				   }
				
			} catch (CoreException e) {
				CSLog.logError(e);
			}
			
		}
		CSLog.logInfo(output);
	}
}
