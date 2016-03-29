package namingcounter;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * 
 * @author akyao
 *
 */
public class Activator extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "namingcounter";

	private static Activator plugin;
	
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	// TODO ?
	public static Activator getDefault() {
		return plugin;
	}

	// TODO ?
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
