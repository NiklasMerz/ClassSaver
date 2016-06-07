package de.niklasmerz.classsaver.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import de.niklasmerz.classsaver.CSLog;

public class DeltaVisitor extends ClassSaver implements IResourceDeltaVisitor {

	// TODO Autofind project and GI8Class

	public DeltaVisitor() {
		super();
	}

	public boolean visit(IResourceDelta delta) {
		IResource res = delta.getResource();
		System.out.println("Delta in File: " + res.getFileExtension());

		if (fileExtension.equals("java")) {
			CSLog.logInfo("Loop Detected");
			return true;
		}

		if (fileExtension.equals(res.getFileExtension())) {

			Job job = new Job(PLUGIN_ID + ": Saving class") {
				protected IStatus run(IProgressMonitor monitor) {
					System.out.println("run job");
					//TODO run job right
					saveConfiguredClass();
					return Status.OK_STATUS;
				}
			};
			job.setPriority(Job.SHORT);
			job.schedule(); // start as soon as possible
		}

		return true;
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Ignore
		// TODO Better structure
		return null;
	}
}
