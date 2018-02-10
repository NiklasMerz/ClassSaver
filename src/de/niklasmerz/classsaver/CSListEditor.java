/**
 * 
 */
package de.niklasmerz.classsaver;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Composite;

/**
 * Custom editor for entering strings
 * @author niklas
 * 
 */
public class CSListEditor extends org.eclipse.jface.preference.ListEditor {

	public CSListEditor(String arg0, String arg1, Composite arg2) {
		super(arg0, arg1, arg2);
	}

	@Override
	protected String createList(String[] arg0) {
		StringBuffer imploded = new StringBuffer();
		for(String entry : arg0) {
			imploded.append(entry);
			imploded.append("~");
		}
		imploded.substring(0, imploded.length() - 1);
		
		return imploded.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.ListEditor#getNewInputObject()
	 */
	@Override
	protected String getNewInputObject() {
		InputDialog dlg = new InputDialog(null, "ClassSaver", "Enter path", null, null);
		dlg.open();
		return dlg.getValue();
	}

	@Override
	protected String[] parseString(String arg0) {
		return arg0.split("~");
	}

}
