/**
 * 
 */
package de.niklasmerz.classsaver;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author niklas
 *
 */
public class ClassSaverPreferencePage extends FieldEditorPreferencePage
implements IWorkbenchPreferencePage, ClassSaverStrings{
	
	FileFieldEditor pathField;

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
	
	protected void performDefaults() {
		pathField.loadDefault();
	}
	
	/** 
	 * Save the color preference to the preference store.
	 */
	public boolean performOk() {
		pathField.store();
		return super.performOk();
	}

	@Override
	protected void createFieldEditors() {
		pathField = new FileFieldEditor(PATH_KEY, "Path", getFieldEditorParent());
		addField(pathField);
		
	}

}
