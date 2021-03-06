package editorfindbar.impl;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.texteditor.ITextEditor;

import editorfindbar.api.IFindBarDecorated;

/**
 * This takes care of the case when the active textEdior is an instance of <code>IFindBarDecorated</code> or
 * does adapt to <code>IFindBarDecorated</code>.
 * 
 * @author schitale
 *
 */
public class ShowFindBarHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Object applicationContext = event.getApplicationContext();
		if (applicationContext instanceof IEvaluationContext) {
			IEvaluationContext evaluationContext = (IEvaluationContext) applicationContext;
			Object variable = evaluationContext.getVariable(ISources.ACTIVE_EDITOR_NAME);
			if (variable instanceof ITextEditor) {
				ITextEditor textEditor = (ITextEditor) variable;
				IFindBarDecorated findBarDecorated = null;
				if (textEditor instanceof IFindBarDecorated) {
					findBarDecorated = (IFindBarDecorated) textEditor;
				} else {
					findBarDecorated = (IFindBarDecorated) textEditor.getAdapter(IFindBarDecorated.class);
				}
				if (findBarDecorated != null) {
					findBarDecorated.getFindBarDecorator().showFindBar();
				} else {
					IHandlerService handlerService = (IHandlerService) textEditor.getSite().getService(IHandlerService.class);
					try {
						handlerService.executeCommand(IWorkbenchCommandConstants.EDIT_FIND_AND_REPLACE, null);
					} catch (NotDefinedException e) {
					} catch (NotEnabledException e) {
					} catch (NotHandledException e) {
					}
				}
			}
		}
		return null;
	}
}
