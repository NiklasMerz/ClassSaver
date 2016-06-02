/**
 * 
 */
package de.niklasmerz.classsaver.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.preference.IPreferenceStore;

import de.niklasmerz.classsaver.Activator;
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
	}
}
