package de.niklasmerz.classsaver.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;

import de.niklasmerz.classsaver.CSLog;

public class DeltaVisitor extends ClassSaver implements IResourceDeltaVisitor {
	
	//TODO Autofind project and GI8Class
	
	public DeltaVisitor(){
		super();
	}
	
	public boolean visit(IResourceDelta delta) {
		IResource res = delta.getResource();
		
		if(fileExtension.equals("java")){
			CSLog.logInfo("Loop Detected");
			return true;
		}
		
		if(fileExtension.equals(res.getFileExtension())){
			saveConfiguredClass();
		}
		
		return true;
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Ignore
		//TODO Better structure
		return null;
	}
}
