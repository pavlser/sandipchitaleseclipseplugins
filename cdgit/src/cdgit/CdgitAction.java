package cdgit;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.texteditor.ITextEditor;

public class CdgitAction implements IObjectActionDelegate, IMenuCreator {
	private File fileObject;

	protected IWorkbenchWindow window;

	public void dispose() {
		if (cdgitMenuInFileMenu != null) {
			cdgitMenuInFileMenu.dispose();
		}
		if (cdgitMenu != null) {
			cdgitMenu.dispose();
		}
	}
	
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.window = targetPart.getSite().getWorkbenchWindow();
		action.setMenuCreator(this);
	}

	public void run(IAction action) {
		// Is this a physical file on the disk ?
		if (fileObject != null) {
			cdgit(fileObject);
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		fileObject = null;
		action.setEnabled(false);
		try {
			IPath location = null;
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				// Is only one item selected?
				if (structuredSelection.size() == 1) {
					Object firstElement = structuredSelection.getFirstElement();
					if (firstElement instanceof IResource) {
						// Is this an IResource ?
						IResource resource = (IResource) firstElement;
						location = resource.getLocation();
					} else if (firstElement instanceof IAdaptable) {
						IAdaptable adaptable = (IAdaptable) firstElement;
						// Is this an IResource adaptable ?
						IResource resource = (IResource) adaptable
								.getAdapter(IResource.class);
						if (resource != null) {
							location = resource.getLocation();
						}
					}
				}
			}
			if (fileObject == null) {
				if (window != null) {
					IWorkbenchPage activePage = window.getActivePage();
					if (activePage != null) {
						IWorkbenchPart activeEditor = activePage.getActivePart();
						if (activeEditor instanceof ITextEditor) {
							ITextEditor abstractTextEditor = (ITextEditor) activeEditor;
							IEditorInput editorInput = abstractTextEditor.getEditorInput();
							if (editorInput instanceof IFileEditorInput) {
								IFileEditorInput fileEditorInput = (IFileEditorInput) editorInput;
								IFile iFile = fileEditorInput.getFile();
								if (iFile != null) {
									location = iFile.getLocation();
								}
							}
						}
					}
				}
			}
			if (location != null) {
				fileObject = location.toFile();
			}
			if (fileObject != null) {
				if (fileObject.isFile()) {
					fileObject = fileObject.getParentFile();
				}
				if (fileObject != null) {
					while (fileObject != null) {
						File dotGit = new File(fileObject, ".git");
						if (dotGit.isDirectory()) {
							return;
						}
						fileObject = fileObject.getParentFile();
					}
				}
			}
		} finally {
			action.setEnabled(fileObject != null);
		}
	}
	
	private Menu cdgitMenuInFileMenu;
	public Menu getMenu(Menu parent) {
		cdgitMenuInFileMenu = new Menu(parent);
		cdgitMenuInFileMenu.addMenuListener(new MenuListener() {
			public void menuHidden(MenuEvent e) {}
			public void menuShown(MenuEvent e) {
				MenuItem[] items = cdgitMenuInFileMenu.getItems();
				for (MenuItem menuItem : items) {
					menuItem.dispose();
				}
				fillMenu(cdgitMenuInFileMenu);
			}			
		});
		return cdgitMenuInFileMenu;
	}
	
	private Menu cdgitMenu;
	public Menu getMenu(Control parent) {
		if (cdgitMenu != null) {
			cdgitMenu.dispose();
		}
		cdgitMenu = new Menu(parent);
		fillMenu(cdgitMenu);
		return cdgitMenu;
		
	}

	private void fillMenu(Menu menu) {
		if (fileObject != null) {
			if (fileObject.isFile()) {
				fileObject = fileObject.getParentFile();
			}
			final File finalGotoFile = fileObject;
			MenuItem gotoParentAction = new MenuItem(menu, SWT.PUSH);
			gotoParentAction.setText("cd to git repo of " + finalGotoFile.getAbsolutePath());
			gotoParentAction.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					cdgit(finalGotoFile);
				}
			});
		}
	}
	
	private static void cdgit(File file) {
		// Get the configured explorer commands for folder and file
		if (file != null && file.exists()) {
			if (file.isFile()) {
				file = file.getParentFile();
			}
			ProcessBuilder processBuilder = new ProcessBuilder();
			if (Platform.OS_MACOSX.equals(Platform.getOS())) {
				processBuilder.command(
						"/usr/bin/osascript"
						,Activator.getDefault().getCdgitScriptPath()
						,file.getAbsolutePath()
				);
			} else if (Platform.OS_LINUX.equals(Platform.getOS())) {
				processBuilder.command(
						"/usr/bin/gnome-terminal"
						,"--working-directory=" + file.getAbsolutePath()
				);
			} else if (Platform.OS_WIN32.equals(Platform.getOS())) {
				processBuilder.command(
						"cmd"
						,"/K"
						,"start"
						,"cd"
						,"/D"
						,file.getAbsolutePath()
				);
			}
			try {
				final Process process = processBuilder.start();
				new Thread(new Runnable() {
					public void run() {
						try {
							process.waitFor();
						} catch (InterruptedException e) {
							// TODO
						}
					}
				});
			} catch (IOException e) {
				// TODO
			}
		}
	}
}
