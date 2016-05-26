/**
 * 
 */
package de.niklasmerz.classsaver.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.jface.preference.IPreferenceStore;

import de.niklasmerz.classsaver.Activator;

/**
 * @author niklas
 *
 */
public abstract class ClassSaver extends AbstractHandler{
	static final String PLUGIN_ID = "ClassSaver";
	static final String PATH_KEY = "path";
	static final String SHOWCONFIRM_KEY = "showConfirm";
	static final String DEFAULT_PATH = "/webint.nsf/com/gi/crm/GI8.java";
	
	IPreferenceStore preferenceStore;
	String path;
	
	/**
	 * Get preferences
	 */
	public ClassSaver(){
		preferenceStore = Activator.getDefault().getPreferenceStore();
		preferenceStore.setDefault(PATH_KEY, DEFAULT_PATH);
		loadSettings();
	}
	
	/**
	 * Load Settigns in fields
	 */
	protected void loadSettings() {
		path = preferenceStore.getString(PATH_KEY);
	}
}
