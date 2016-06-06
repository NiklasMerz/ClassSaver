package de.niklasmerz.classsaver.handlers;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;

public class DeltaVisitor implements IResourceDeltaVisitor {
	
	//TODO detect event type and file extension
	//TODO Autofind project and GI8Class
	//TODO Run command
	
	public boolean visit(IResourceDelta delta) {
		IResource res = delta.getResource();
		switch (delta.getKind()) {
		case IResourceDelta.ADDED:
			System.out.print("Resource ");
			System.out.print(res.getFullPath());
			System.out.println(" was added.");
			break;
		case IResourceDelta.REMOVED:
			System.out.print("Resource ");
			System.out.print(res.getFullPath());
			System.out.println(" was removed.");
			break;
		case IResourceDelta.CHANGED:
			System.out.print("Resource ");
			System.out.print(res.getFullPath());
			System.out.println(" has changed.");
			break;
		}
		return true; // visit the children
	}
}
