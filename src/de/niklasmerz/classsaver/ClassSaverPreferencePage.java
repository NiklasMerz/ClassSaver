/**
 * 
 */
package de.niklasmerz.classsaver;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author niklas
 *
 */
public class ClassSaverPreferencePage extends FieldEditorPreferencePage
implements IWorkbenchPreferencePage, ClassSaverStrings{
	
	private StringFieldEditor pathField;
	private StringFieldEditor projectField;
	private StringFieldEditor classField;
	private BooleanFieldEditor autoField;
	private StringFieldEditor extensionField;

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
	
	/**
	 * Load defaults
	 */
	protected void performDefaults() {
		projectField.loadDefault();
		pathField.loadDefault();
		classField.loadDefault();
		autoField.loadDefault();
		extensionField.loadDefault();
	}
	
	/** 
	 * Save the color preference to the preference store.
	 */
	public boolean performOk() {
		pathField.store();
		projectField.store();
		classField.store();
		
		Activator.removeChangelistener();
		
		autoField.store();
		extensionField.store();
		return super.performOk();
	}

	@Override
	protected void createFieldEditors() {
		projectField = new StringFieldEditor(PROJECT_KEY, "Project", getFieldEditorParent());
		pathField = new StringFieldEditor(PATH_KEY, "Path", getFieldEditorParent());
		classField = new StringFieldEditor(CLASS_KEY, "Class File", getFieldEditorParent());
		autoField = new BooleanFieldEditor(AUTO_KEY, "Save automatically", getFieldEditorParent());
		extensionField = new StringFieldEditor(EXTENSION_KEY, "File extension", getFieldEditorParent());
		
		addField(projectField);
		addField(pathField);
		addField(classField);
		addField(autoField);
		addField(extensionField);
	}

}
