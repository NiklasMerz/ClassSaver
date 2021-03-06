package de.niklasmerz.classsaver.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.niklasmerz.classsaver.CSLog;

/**
 * Handler for saving configured class of current project
 * 
 * @author niklas
 */
public class AutoSaveHandler extends ClassSaver {
	IWorkbenchPage activePage;
	IEditorPart activeEditor;
	
	/**
	 * Constructor loads settings
	 */
	public AutoSaveHandler() {
		super();
		loadSettings();
	}

	/**
	 * Save current file
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		loadSettings();

		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		this.activePage = window.getActivePage();

		this.activeEditor = activePage.getActiveEditor();

		if (activeEditor != null) {
			IEditorInput input = activeEditor.getEditorInput();

			IProject project = (IProject) input.getAdapter(IProject.class);
			if (project == null) {
				IResource resource = (IResource) input
						.getAdapter(IResource.class);
				if (resource != null) {
					project = resource.getProject();
					this.saveProject(project, true);
				}
			}
		}

		CSLog.logInfo("Saved auto");
		return null;
	}
}
