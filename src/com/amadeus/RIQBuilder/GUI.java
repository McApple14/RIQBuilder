package com.amadeus.RIQBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class GUI {

	protected Shell shlRiqbuilder;

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlRiqbuilder.open();
		shlRiqbuilder.layout();
		while (!shlRiqbuilder.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlRiqbuilder = new Shell();
		shlRiqbuilder.setSize(700, 400);
		shlRiqbuilder.setText("RIQBuilder");

	}

}
