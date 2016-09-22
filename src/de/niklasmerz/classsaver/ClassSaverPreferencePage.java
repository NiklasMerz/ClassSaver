/**
 *
 */
package de.niklasmerz.classsaver;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Eclipse preference page
 * 
 * @author niklas
 *
 */
public class ClassSaverPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage, ClassSaverStrings {

	private StringFieldEditor pathField;
	private StringFieldEditor projectField;
	private StringFieldEditor classField;

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
	}

	/**
	 * Save the preference to the preference store.
	 */
	public boolean performOk() {
		pathField.store();
		return super.performOk();
	}

	@Override
	protected void createFieldEditors() {
		projectField = new StringFieldEditor(PROJECT_KEY, "Project", getFieldEditorParent());
		pathField = new StringFieldEditor(PATH_KEY, "Path", getFieldEditorParent());
		classField = new StringFieldEditor(CLASS_KEY, "Class File", getFieldEditorParent());

		addField(projectField);
		addField(pathField);
		addField(classField);
	}

}
