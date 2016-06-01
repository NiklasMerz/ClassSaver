package de.niklasmerz.classsaver.handlers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import de.niklasmerz.classsaver.CSLog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SaveHandler extends ClassSaver {
	/**
	 * The constructor.
	 */
	public SaveHandler() {
		super();
		loadSettings();
	}

	/**
	 * Save configured file
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		loadSettings();
		String output = "TODO PATH";

		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

		IProject myProject = myWorkspaceRoot.getProject("webint.nsf");
		if (myProject.exists()){
			try {
				if(!myProject.isOpen()){
					myProject.open(null);
				}
				
				IFolder folder = myProject.getFolder("src");
				   if(folder.exists()) {
				      IFile file = folder.getFile("GI8.java");
				      String inputString = "\n";
				      InputStream in = new ByteArrayInputStream(inputString.getBytes());
				      file.appendContents(in, true, false, null);
				   }else{
					   CSLog.logInfo("Folder not found");
				   }
				
			} catch (CoreException e) {
				CSLog.logError(e);
			}
			
		}
		CSLog.logInfo(output);
		// JOptionPane.showMessageDialog(null, output);
		return null;
	}
}
