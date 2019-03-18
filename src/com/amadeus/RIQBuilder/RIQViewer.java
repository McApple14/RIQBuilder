package com.amadeus.RIQBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class RIQViewer extends Shell {

	private Display display;
	private RIQBuilder builder;
	private ScrolledComposite scrolledComposite;
	private Table riqTable;
	private TableColumn tblclmnName;
	private TableColumn tblclmnLinks;
	private TableColumn tblclmnClients;
	private Button btnViewLinks;
	private Button btnImport;
	private Button btnExport;
	private Composite composite;
	private GridLayout gridLayout;
	private Button btnAddRIQ;
	private Button btnClearAndInit;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			RIQViewer shell = new RIQViewer(display, new RIQBuilder(true));
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
	public RIQViewer(Display display, RIQBuilder builder) {
		super(display, SWT.SHELL_TRIM);
		this.display = display;
		this.builder = builder;
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		this.setSize(701, 400);
		this.setText("RIQBuilder");
		gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginHeight = 10;
		gridLayout.marginWidth = 10;
		setLayout(gridLayout);
		
		scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		riqTable = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		riqTable.setHeaderVisible(true);
		riqTable.setLinesVisible(true);
		
		tblclmnName = new TableColumn(riqTable, SWT.CENTER);
		tblclmnName.setWidth(149);
		tblclmnName.setText("Name");
		
		tblclmnLinks = new TableColumn(riqTable, SWT.CENTER);
		tblclmnLinks.setWidth(100);
		tblclmnLinks.setText("# Links");
		
		tblclmnClients = new TableColumn(riqTable, SWT.CENTER);
		tblclmnClients.setWidth(100);
		tblclmnClients.setText("# Clients");
		
		initRIQTable(riqTable);
		
		scrolledComposite.setContent(riqTable);
		scrolledComposite.setMinSize(riqTable.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.verticalSpacing = 10;
		gl_composite.horizontalSpacing = 10;
		gl_composite.marginHeight = 10;
		gl_composite.marginWidth = 10;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, true, 1, 1));
		
		btnAddRIQ = new Button(composite, SWT.NONE);
		GridData gd_btnAddRIQ = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnAddRIQ.heightHint = 40;
		gd_btnAddRIQ.widthHint = 80;
		btnAddRIQ.setLayoutData(gd_btnAddRIQ);
		btnAddRIQ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Shell riqwiz = new RIQWizard(display, builder);
				riqwiz.open();
				riqwiz.layout();
				while (!riqwiz.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
				initRIQTable(riqTable);
			}
		});
		btnAddRIQ.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnAddRIQ.setText("Add RIQ");
		
		btnViewLinks = new Button(composite, SWT.NONE);
		GridData gd_btnViewLinks = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnViewLinks.heightHint = 40;
		gd_btnViewLinks.widthHint = 80;
		btnViewLinks.setLayoutData(gd_btnViewLinks);
		btnViewLinks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				/*Shell linkview = new LinkViewer(display, builder);
				linkview.open();
				linkview.layout();
				while (!linkview.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
				initRIQTable(riqTable);*/
				display.dispose();
				Application.open(builder, Application.LINKVIEWER);
			}
		});
		btnViewLinks.setText("View Links");
		
		btnClearAndInit = new Button(composite, SWT.WRAP);
		btnClearAndInit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// builder = new RIQBuilder();
				Shell initWiz = new InitWizard(display, builder);
				initWiz.open();
				initWiz.layout();
				while (!initWiz.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
				initRIQTable(riqTable);
			}
		});
		GridData gd_btnClearAndInit = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnClearAndInit.heightHint = 40;
		gd_btnClearAndInit.widthHint = 80;
		btnClearAndInit.setLayoutData(gd_btnClearAndInit);
		btnClearAndInit.setText("Clear and Reinitialize");
		
		btnImport = new Button(composite, SWT.NONE);
		GridData gd_btnImport = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnImport.heightHint = 40;
		gd_btnImport.widthHint = 80;
		btnImport.setLayoutData(gd_btnImport);
		btnImport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				FileDialog dialog = new FileDialog(getSelf(), SWT.OPEN);
				dialog.setFilterExtensions(new String [] {"*.rb","*.*"});
				dialog.setFilterPath("c:\\temp");
				String fileName = dialog.open();
				builder.importObject(fileName);
				if(fileName==null || fileName=="") {System.out.println("No FileName");return;}
				initRIQTable(riqTable);
			}
		});
		btnImport.setText("Import");
		
		btnExport = new Button(composite, SWT.NONE);
		GridData gd_btnExport = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnExport.heightHint = 40;
		gd_btnExport.widthHint = 80;
		btnExport.setLayoutData(gd_btnExport);
		btnExport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				FileDialog dialog = new FileDialog(getSelf(), SWT.SAVE);
				dialog.setFilterExtensions(new String [] {"*.rb","*.*"});
				dialog.setFilterPath("c:\\temp");
				String fileName = dialog.open();
				if(fileName==null || fileName=="") {System.out.println("No FileName");return;}
				builder.exportObject(fileName, builder);
				initRIQTable(riqTable);
			}
		});
		btnExport.setText("Export");
		
		initRIQTable(riqTable);
	}
	
	public void initRIQTable(Table t) {
		t.removeAll();
		for(RIQ riq : builder.getRIQs()) {
			TableItem tableItem = new TableItem(t, SWT.NONE);
			tableItem.setText(0,riq.getName());
			tableItem.setText(1,Integer.toString(riq.getLinks().size()));
			tableItem.setText(2,Integer.toString(riq.getClientList().size()));
		}
		initRIQTableListeners(t);
	}
	
	public void initRIQTableListeners(Table table) {
		final Menu menu = new Menu(table);
		table.setMenu(menu);
		menu.addMenuListener(new MenuAdapter() {
			public void menuShown(MenuEvent e) {
				try {
					System.out.println(table.getSelection()[0] + " Selected");
					MenuItem[] items = menu.getItems();
					for(int i=0; i < items.length; i++) {
						items[i].dispose();
					}
					// Open RIQ
					MenuItem newItem = new MenuItem(menu, SWT.NONE);
					newItem.setText("Open "+table.getSelection()[0].getText());
					newItem.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event e) {
							display.close();
							Application.open(builder, builder.getRIQ(table.getSelection()[0].getText()), Application.RIQCONFIGVIEWER);
						}
					});
					// Add Link to RIQ (Work in Progress)
					newItem = new MenuItem(menu, SWT.NONE);
					newItem.setText("Add link to "+riqTable.getSelection()[0].getText());
					newItem.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event e) {
							System.out.println("Opening Link Wizard for  "+riqTable.getSelection()[0].getText());
							RIQ local = builder.getRIQ(riqTable.getSelection()[0].getText());
							Shell linkwiz = new LinkWizard(display, builder, local);
							linkwiz.open();
							linkwiz.layout();
							while (!linkwiz.isDisposed()) {
								if (!display.readAndDispatch()) {
									display.sleep();
								}
							}
						initRIQTable(riqTable);
						}
					});
				
					// Remove RIQ (Works)
					newItem = new MenuItem(menu, SWT.NONE);
					newItem.setText("Remove "+riqTable.getSelection()[0].getText());
					newItem.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event e) {
							System.out.println("Remove "+riqTable.getSelection()[0].getText());
							if(builder.removeRIQ(riqTable.getSelection()[0].getText()) == null) {System.out.println("Failed to Remove");};
							initRIQTable(riqTable);
						}
					});
				}
				catch(ArrayIndexOutOfBoundsException exception) {System.out.println("No Selection or Empty Table");}
			}
		});
		for(Listener listener : table.getListeners(SWT.MouseDoubleClick)) {
			table.removeListener(SWT.MouseDoubleClick, listener);
		}
		table.addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event e) {
				try {
					RIQ riq = builder.getRIQ(table.getSelection()[0].getText());
					display.dispose();
					Application.open(builder, riq, Application.RIQCONFIGVIEWER);
				}
				catch(ArrayIndexOutOfBoundsException exception) {System.out.println("No Selection or Empty Table");}
			}
		});		
	}
	
	private Shell getSelf() {return this;}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
