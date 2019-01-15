package com.amadeus.RIQBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class VLANViewer extends Shell {

	RIQ riq;
	Display display;
	boolean viewOnly;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			RIQBuilder builder = new RIQBuilder(true);
			VLANViewer shell = new VLANViewer(display, builder.getRIQs().get(0), true);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public VLANViewer(Display display, RIQ riq, boolean viewOnly) {
		super(display, SWT.SHELL_TRIM);
		this.riq = riq;
		this.display = display;
		this.viewOnly = viewOnly;
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("VLAN Viewer");
		setSize(450, 300);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
