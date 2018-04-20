/**
 * 
 */
package de.niklasmerz.classsaver.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * Save file and class
 * @author Niklas
 */
public class FileAutoSaveHandler extends AutoSaveHandler {

	/**
	 * Construct
	 */
	public FileAutoSaveHandler() {
		super();
	}
	
	/**
	 * Save current editor and trigger autosave afterwards
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		NullProgressMonitor monitor = new NullProgressMonitor();
		
		if(this.activeEditor != null && this.activeEditor.isDirty()) {
			this.activeEditor.doSave(monitor);
		}
		
		super.execute(event);
		return null;
	}

}
