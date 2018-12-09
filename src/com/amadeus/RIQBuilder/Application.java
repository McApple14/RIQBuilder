package com.amadeus.RIQBuilder;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class Application {

	private static RIQBuilder builder;
	private static Display display;
	
	public static final int RIQVIEWER = 0;
	public static final int LINKVIEWER = 1;
	public static final int RIQCONFIGVIEWER = 2;
	public static final int SAVIEWER = 3;
	//public static final int IMPORT = 4;
	//public static final int EXPORT = 5;
	
	protected static Shell shlRIQBuilder;
	

	// This will be moved to Main.java when complete
	public static void main(String[] args) {
		
		//builder = new RIQBuilder();
		builder = new RIQBuilder(true);
		
		try {
			Application window = new Application();
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
	
	public static void open(RIQBuilder builder, int window) {
		display = Display.getDefault();		
		switch(window) {
		case 0:
			shlRIQBuilder = new RIQViewer(display, builder);
			break;
		case 1:
			shlRIQBuilder = new LinkViewer(display, builder);
			break;
		}
		shlRIQBuilder.open();
		shlRIQBuilder.layout();
		while (!shlRIQBuilder.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public static void open(RIQBuilder builder, Object o, int window) {
		display = Display.getDefault();		
		switch(window) {
		case RIQVIEWER:
			shlRIQBuilder = new RIQViewer(display, builder);
			break;
		case LINKVIEWER:
			shlRIQBuilder = new LinkViewer(display, builder);
			break;
		case RIQCONFIGVIEWER:
			shlRIQBuilder = new RIQConfigViewer(display, builder, (RIQ) o);
			break;
		case SAVIEWER:
			shlRIQBuilder = new SAViewer(display, builder, (RIQ) o);
			break;
		default:
			display.dispose();
		}
		shlRIQBuilder.open();
		shlRIQBuilder.layout();
		while (!shlRIQBuilder.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
