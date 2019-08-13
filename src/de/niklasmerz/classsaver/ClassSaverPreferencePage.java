/**
 *
 */
package de.niklasmerz.classsaver;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.preference.ListEditor;
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

	private StringFieldEditor classField;
	private ListEditor pathSelection;

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	/**
	 * Load defaults
	 */
	protected void performDefaults() {
		classField.loadDefault();
		pathSelection.loadDefault();
	}

	/**
	 * Save the preference to the preference store.
	 */
	public boolean performOk() {
		return super.performOk();
	}

	@Override
	protected void createFieldEditors() {
		classField = new StringFieldEditor(CLASS_KEY, "Class file", getFieldEditorParent());
		pathSelection = new CSListEditor(PATHSELECTION_KEY, "Paths to look for class", getFieldEditorParent());

		addField(classField);
		addField(pathSelection);
	}

}
