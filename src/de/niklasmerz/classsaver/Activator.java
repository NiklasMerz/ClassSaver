package de.niklasmerz.classsaver;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.niklasmerz.classsaver.handlers.DeltaVisitor;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin implements ClassSaverStrings, IStartup
{

	// The plug-in ID
	public static final String PLUGIN_ID = "ClassSaver"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private boolean auto;

	private static IResourceChangeListener listener = new IResourceChangeListener() {
		public void resourceChanged(IResourceChangeEvent event) {

			// TODO only pre build events
			//if (event.getType() == IResourceChangeEvent.POST_CHANGE){
				IResourceDelta delta = event.getDelta();
				try {
					delta.accept(new DeltaVisitor());
				} catch (CoreException e) {
					e.printStackTrace();
				}
			//}

			//CSLog.logInfo("Something changed!");
		}
	};

	/**
	 * The constructor
	 */
	public Activator() {
		auto = Platform.getPreferencesService().getBoolean(
				PLUGIN_PACKAGE, AUTO_KEY,
			    DEFAULT_AUTO, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		// TODO check prefs
		if(auto){
			System.out.println("Started listener");
			
			addChangelistener();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 *
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	/**
	 * Initializes a preference store with default preference values for this
	 * plug-in.
	 */
	protected void initializeDefaultPreferences(IPreferenceStore store) {
		store.setDefault(PATH_KEY, DEFAULT_PATH);
		store.setDefault(PROJECT_KEY, DEFAULT_PROJECT);
		store.setDefault(CLASS_KEY, DEFAULT_CLASS);
		store.setDefault(AUTO_KEY, DEFAULT_AUTO);
	}

	@Override
	public void earlyStartup() {
		//Ignore
	}
	
	/**
	 * Remove listener
	 */
	public static void removeChangelistener(){
		System.out.println("remove listener");
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(listener);
	}
	
	/**
	 * Add listener
	 */
	public static void addChangelistener(){
		System.out.println("add listener");
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener);
	}
}
