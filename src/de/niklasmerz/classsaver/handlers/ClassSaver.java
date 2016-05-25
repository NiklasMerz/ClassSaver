/**
 * 
 */
package de.niklasmerz.classsaver.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;

/**
 * @author niklas
 *
 */
public abstract class ClassSaver extends AbstractHandler{
	static final String PLUGIN_ID = "ClassSaver";
	static final String PATH_KEY = "path";
	static final String SHOWCONFIRM_KEY = "showConfirm";
	
	IEclipsePreferences prefs;
	String path;
	
	public ClassSaver(){
		prefs = InstanceScope.INSTANCE.getNode(PLUGIN_ID);
		loadSettings();
	}
	
	protected void loadSettings() {
		path = prefs.get(PATH_KEY, "webint.nsf/src/com.gi.crm.GI8.java");
	}
}
