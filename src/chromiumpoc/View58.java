package chromiumpoc;

import java.io.IOException;
import java.net.URL;

import javax.inject.Inject;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.chromium.Browser;
import org.eclipse.swt.chromium.BrowserFunction;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;

public class View58 extends ViewPart {
	public static final String ID = "chromium-poc.view58";

	@Inject
	IWorkbench workbench;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		Browser browser = new Browser(parent, SWT.NONE);
		new BrowserFunction(browser, "call") {
			@Override
			public Object function(Object[] arguments) {
				return 0;
			};
		};
		new BrowserFunction(browser, "callInt") {
			@Override
			public Object function(Object[] arguments) {
				return new int[] { 0 };
			}
		};
		new BrowserFunction(browser, "callEmpty") {
			@Override
			public Object function(Object[] arguments) {
				return new int[] {};
			}
		};
		new BrowserFunction(browser, "callBoolean") {
			@Override
			public Object function(Object[] arguments) {
				return new boolean[] { true };
			}
		};
		new BrowserFunction(browser, "callString") {
			@Override
			public Object function(Object[] arguments) {
				return new String[] { "a,b", "a", "a=b" };
			}
		};
		new BrowserFunction(browser, "callObject") {
			@Override
			public Object function(Object[] arguments) {
				return "{\"key\"=\"val\", \"key1\"=\"val1\"}";
			}
		};
		Bundle bundle = Platform.getBundle("chromium-poc");
		URL url = FileLocator.find(bundle, Path.fromPortableString("resources/poc58.html"), null);
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