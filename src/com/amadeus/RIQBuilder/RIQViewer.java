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
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

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
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			RIQViewer shell = new RIQViewer(display, new RIQBuilder());
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
		
		Button btnAddRIQ = new Button(this, SWT.NONE);
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
				createContents();
				open();
				layout();
				initRIQTable(riqTable);
			}
		});
		btnAddRIQ.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnAddRIQ.setBounds(482, 10, 192, 37);
		btnAddRIQ.setText("Add RIQ");
		
		scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 10, 369, 341);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		riqTable = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		riqTable.setHeaderVisible(true);
		riqTable.setLinesVisible(true);
		
		tblclmnName = new TableColumn(riqTable, SWT.CENTER);
		tblclmnName.setWidth(100);
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
		
		btnViewLinks = new Button(this, SWT.NONE);
		btnViewLinks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Shell linkview = new LinkViewer(display, builder);
				linkview.open();
				linkview.layout();
				while (!linkview.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
				createContents();
				open();
				layout();
				initRIQTable(riqTable);
			}
		});
		btnViewLinks.setText("View Links");
		btnViewLinks.setBounds(482, 53, 192, 37);
		
		btnImport = new Button(this, SWT.NONE);
		btnImport.setText("Import");
		btnImport.setBounds(482, 96, 192, 37);
		
		btnExport = new Button(this, SWT.NONE);
		btnExport.setText("Export");
		btnExport.setBounds(482, 139, 192, 37);
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
	
	public void initRIQTableListeners(Table t) {
		final Menu menu = new Menu(t);
		t.setMenu(menu);
		menu.addMenuListener(new MenuAdapter() {
			public void menuShown(MenuEvent e) {
				MenuItem[] items = menu.getItems();
				for(int i=0; i < items.length; i++) {
					items[i].dispose();
				}
				// Edit RIQ (Work in Progress)
				MenuItem newItem = new MenuItem(menu, SWT.NONE);
				/*newItem.setText("Edit "+t.getSelection()[0].getText());
				newItem.addListener(SWT.Selection, new Listener() {
					public void handleEvent(Event e) {
						System.out.println("Edit "+t.getSelection()[0].getText());
					}
				});*/
				
				// Add Link to RIQ (Work in Progress)
				newItem.setText("Add link to "+t.getSelection()[0].getText());
				newItem.addListener(SWT.Selection, new Listener() {
					public void handleEvent(Event e) {
						System.out.println("Opening Link Wizard for  "+t.getSelection()[0].getText());
						RIQ local = builder.getRIQ(t.getSelection()[0].getText());
						Shell linkwiz = new LinkWizard(display, builder, local);
						linkwiz.open();
						linkwiz.layout();
						while (!linkwiz.isDisposed()) {
							if (!display.readAndDispatch()) {
								display.sleep();
							}
						}
						createContents();
						open();
						layout();
						initRIQTable(t);
					}
				});
				
				// Remove RIQ (Works)
				newItem = new MenuItem(menu, SWT.NONE);
				newItem.setText("Remove "+t.getSelection()[0].getText());
				newItem.addListener(SWT.Selection, new Listener() {
					public void handleEvent(Event e) {
						System.out.println("Remove "+t.getSelection()[0].getText());
						if(builder.removeRIQ(t.getSelection()[0].getText()) == null) {System.out.println("Failed to Remove");};
						initRIQTable(t);
					}
				});
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
