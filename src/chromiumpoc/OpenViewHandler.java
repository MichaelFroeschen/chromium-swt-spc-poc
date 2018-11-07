package chromiumpoc;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

public class OpenViewHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent pEvent) throws ExecutionException {
		String viewId = pEvent.getParameter("viewId");
		try {
			HandlerUtil.getActiveWorkbenchWindow(pEvent).getActivePage().showView(viewId);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		return null;
	}

}
