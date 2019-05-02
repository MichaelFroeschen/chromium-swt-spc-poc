package chromiumpoc;

import java.io.IOException;
import java.net.URL;

import javax.inject.Inject;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.chromium.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;

public class View66 extends ViewPart {
	public static final String ID = "chromium-poc.view66";

	@Inject
	IWorkbench workbench;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		Browser browser = new Browser(parent, SWT.NONE);
		browser.addProgressListener(new ProgressListener() {
			@Override
			public void completed(ProgressEvent pEvent) {
				browser.evaluate("longRunning()");
			}

			@Override
			public void changed(ProgressEvent pEvent) {
				// TODO Auto-generated method stub

			}
		});
		Bundle bundle = Platform.getBundle("chromium-poc");
		URL url = FileLocator.find(bundle, Path.fromPortableString("resources/poc66.html"), null);
		try {
			url = FileLocator.toFileURL(url);
			browser.setUrl(url.toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void setFocus() {
	}

}