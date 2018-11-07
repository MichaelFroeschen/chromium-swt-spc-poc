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

public class View31 extends ViewPart {
	public static final String ID = "chromium-poc.view31";

	@Inject
	IWorkbench workbench;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		createBrowser(parent);
		createBrowser(parent);
	}

	private void createBrowser(Composite parent) {
		Browser browser = new Browser(parent, SWT.NONE);
		new BrowserFunction(browser, "callFunc") {
			@Override
			public Object function(Object[] arguments) {
				browser.execute("doSomething()");
				if (arguments != null && arguments.length == 1 && "de".equals(arguments[0])) {
					return "{\"Reset\":\"Zur0cksetzen\"}";
				}
				return "{\"Reset\":\"Reset\"}";
			}
		};
		new BrowserFunction(browser, "finishedSomething") {
			@Override
			public Object function(Object[] arguments) {
				System.out.println("finishedSomething BrowserFunc");
				browser.execute("doSomething1()");
				return null;
			}
		};
		Bundle bundle = Platform.getBundle("chromium-poc");
		URL url = FileLocator.find(bundle, Path.fromPortableString("resources/poc.html"), null);
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