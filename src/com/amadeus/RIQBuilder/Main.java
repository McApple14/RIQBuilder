package com.amadeus.RIQBuilder;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class Main {

	private static RIQBuilder builder;
	private Display display;
	
	protected Shell shlRIQBuilder;
	protected Shell childShellRIQWizard;
	protected Shell childShellLinkWizard;
	protected Shell childShellLinkViewer;
	
	

	// This will be moved to Main.java when complete
	public static void main(String[] args) {
		
		builder = new RIQBuilder();
		
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		shlRIQBuilder = new RIQViewer(display, builder);
		shlRIQBuilder.open();
		shlRIQBuilder.layout();
		while (!shlRIQBuilder.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
