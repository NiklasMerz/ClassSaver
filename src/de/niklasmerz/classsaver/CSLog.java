package de.niklasmerz.classsaver;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Custom log class
 * 
 * @author niklas
 */
public class CSLog {

	public static void logInfo(String message) {
		log(IStatus.INFO, IStatus.OK, message, null);
	}

	public static void logError(Throwable exception) {
		logError("Unexpected Exception", exception);
	}

	public static void logError(String message, Throwable exception) {
		log(IStatus.ERROR, IStatus.OK, message, exception);
	}

	public static void log(int severity, int code, String message, Throwable exception) {
		System.out.println(message);
		log(createStatus(severity, code, message, exception));
	}

	public static IStatus createStatus(int severity, int code, String message, Throwable exception) {
		return new Status(severity, Activator.PLUGIN_ID, code, message, exception);
	}

	public static void log(IStatus status) {
		Activator.getDefault().getLog().log(status);
	}
}
