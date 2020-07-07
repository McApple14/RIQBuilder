package com.amadeus.RIQBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RIQConfigViewer extends Shell {
	
	private Display display;
	private RIQBuilder builder;
	private RIQ riq;
	private Table linkTable;
	private Table clientTable;
	private Button btnAddLink;
	private GridData gd_btnAddLink;
	private TableColumn tblclmnName;
	private TableColumn tblclmnLocalRiq;
	private TableColumn tblclmnLocalIp;
	private TableColumn tblclmnRemoteRiq;
	private TableColumn tblclmnRemoteIp;
	private Button btnAddClient;
	private GridData gd_btnAddClient;
	private Button btnViewSas;
	private GridData gd_btnViewSas;
	private Button btnBack;
	private GridData gd_btnBack;
	private TableColumn tblclmnName_1;
	private TableColumn tblclmnIpAddress;
	private ScrolledComposite scrolledComposite;
	private ScrolledComposite scrolledComposite_1;
	private Label lblLinks;
	private Label lblClients;
	private Button btnViewVLANs;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			RIQConfigViewer shell = new RIQConfigViewer(display);
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
	public RIQConfigViewer(Display display) {
		super(display, SWT.SHELL_TRIM);
		this.display = display;
		builder = new RIQBuilder(true);
		riq = builder.getRIQs().get(0);
		createContents();
	}
	
	/**
	 * Create the shell.
	 * @param display
	 * @wbp.parser.constructor
	 */
	public RIQConfigViewer(Display display, RIQBuilder builder, RIQ riq) {
		super(display, SWT.SHELL_TRIM);
		this.display = display;
		this.builder = builder;
		this.riq = riq;
		
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("RIQ: "+riq.getName());
		setSize(900, 440);
		
		GridLayout gridLayout = new GridLayout(3, false);
		setLayout(gridLayout);
		
		lblLinks = new Label(this, SWT.NONE);
		lblLinks.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblLinks.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblLinks.setAlignment(SWT.CENTER);
		lblLinks.setText("Links");
		
		lblClients = new Label(this, SWT.NONE);
		lblClients.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblClients.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblClients.setAlignment(SWT.CENTER);
		lblClients.setText("Clients");
		
		btnAddLink = new Button(this, SWT.NONE);
		btnAddLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Shell linkwiz = new LinkWizard(display, builder);
				linkwiz.open();
				linkwiz.layout();
				while (!linkwiz.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
				initLinkTable(linkTable);
			}
		});
		gd_btnAddLink = new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1);
		gd_btnAddLink.heightHint = 40;
		gd_btnAddLink.widthHint = 90;
		btnAddLink.setLayoutData(gd_btnAddLink);
		btnAddLink.setText("Add Link");
		
		scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 4));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		linkTable = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		linkTable.setHeaderVisible(true);
		linkTable.setLinesVisible(true);
		
		tblclmnName = new TableColumn(linkTable, SWT.NONE);
		tblclmnName.setWidth(100);
		tblclmnName.setText("Name");
		
		tblclmnLocalRiq = new TableColumn(linkTable, SWT.NONE);
		tblclmnLocalRiq.setWidth(100);
		tblclmnLocalRiq.setText("Local RIQ");
		
		tblclmnLocalIp = new TableColumn(linkTable, SWT.NONE);
		tblclmnLocalIp.setWidth(100);
		tblclmnLocalIp.setText("Local IP");
		
		tblclmnRemoteRiq = new TableColumn(linkTable, SWT.NONE);
		tblclmnRemoteRiq.setWidth(100);
		tblclmnRemoteRiq.setText("Remote RIQ");
		
		tblclmnRemoteIp = new TableColumn(linkTable, SWT.NONE);
		tblclmnRemoteIp.setWidth(100);
		tblclmnRemoteIp.setText("Remote IP");

		scrolledComposite.setContent(linkTable);
		scrolledComposite.setMinSize(linkTable.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		scrolledComposite_1 = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 4));
		scrolledComposite_1.setExpandHorizontal(true);
		scrolledComposite_1.setExpandVertical(true);
		
		clientTable = new Table(scrolledComposite_1, SWT.BORDER | SWT.FULL_SELECTION);
		clientTable.setHeaderVisible(true);
		clientTable.setLinesVisible(true);
		
		tblclmnName_1 = new TableColumn(clientTable, SWT.NONE);
		tblclmnName_1.setWidth(100);
		tblclmnName_1.setText("Name");
		
		tblclmnIpAddress = new TableColumn(clientTable, SWT.NONE);
		tblclmnIpAddress.setWidth(100);
		tblclmnIpAddress.setText("IP Address");
		
		scrolledComposite_1.setContent(clientTable);
		scrolledComposite_1.setMinSize(clientTable.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		btnAddClient = new Button(this, SWT.NONE);
		btnAddClient.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		gd_btnAddClient = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnAddClient.heightHint = 40;
		gd_btnAddClient.widthHint = 90;
		btnAddClient.setLayoutData(gd_btnAddClient);
		btnAddClient.setText("Add Client");
		btnAddClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Shell clientwiz = new LinkWizard(display, builder);
				clientwiz.open();
				clientwiz.layout();
				while (!clientwiz.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
				initClientTable(clientTable);
			}
		});
		
		initLinkTable(linkTable);
		initClientTable(clientTable);		
		
		btnViewVLANs = new Button(this, SWT.NONE);
		GridData gd_btnViewVLANs = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnViewVLANs.heightHint = 40;
		gd_btnViewVLANs.widthHint = 90;
		btnViewVLANs.setLayoutData(gd_btnViewVLANs);
		btnViewVLANs.setText("View VLANs");
		
		btnViewSas = new Button(this, SWT.NONE);
		btnViewSas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				display.dispose();
				Application.open(builder, riq, Application.SAVIEWER);
			}
		});
		gd_btnViewSas = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnViewSas.heightHint = 40;
		gd_btnViewSas.widthHint = 90;
		btnViewSas.setLayoutData(gd_btnViewSas);
		btnViewSas.setText("View SAs");
		
		btnBack = new Button(this, SWT.NONE);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				display.dispose();
				Application.open(builder, Application.RIQVIEWER);
			}
		});
		gd_btnBack = new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1);
		gd_btnBack.heightHint = 40;
		gd_btnBack.widthHint = 90;
		btnBack.setLayoutData(gd_btnBack);
		btnBack.setText("Back");
	}
	
	public void initLinkTable(Table table) {
		// TODO Auto-generated method stub
		table.removeAll();
		for(Link link : riq.getLinks()) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0,link.getName());
			tableItem.setText(1,link.getLocalRIQ().toString());
			tableItem.setText(2,link.getLocalIP());
			tableItem.setText(3,link.getRemoteRIQ().toString());
			tableItem.setText(4,link.getRemoteIP());
		}
		initLinkTableListeners(table);
	}

	public void initLinkTableListeners(Table table) {
		for(Listener listener : table.getListeners(SWT.MouseDoubleClick)) {
			table.removeListener(SWT.MouseDoubleClick, listener);
		}
		table.addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event e) {
				try {
					System.out.println("View Link "+table.getSelection()[0].getText());
					Shell linkview = new LinkConfigViewer(display, builder, builder.getLink(table.getSelection()[0].getText()), true);
					linkview.open();
					linkview.layout();
					while (!linkview.isDisposed()) {
						if (!display.readAndDispatch()) {
							display.sleep();
						}
					}
					initLinkTable(table);
					initClientTable(clientTable);
				}
				catch(ArrayIndexOutOfBoundsException exception) {System.out.println("No Selection or Empty Table");}
				initLinkTable(table);
			}
		});
		
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
					// Open Link
					MenuItem newItem = new MenuItem(menu, SWT.NONE);
					newItem.setText("Open "+table.getSelection()[0].getText());
					newItem.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event e) {
							try {
								System.out.println("View Link "+table.getSelection()[0].getText());
								Shell linkview = new LinkConfigViewer(display, builder, builder.getLink(table.getSelection()[0].getText()), true);
								linkview.open();
								linkview.layout();
								while (!linkview.isDisposed()) {
									if (!display.readAndDispatch()) {
										display.sleep();
									}
								}
								initLinkTable(table);
								initClientTable(clientTable);
							}
							catch(ArrayIndexOutOfBoundsException exception) {System.out.println("No Selection or Empty Table");}
							initLinkTable(table);
						}
					});

					// Remove Link (Works)
					newItem = new MenuItem(menu, SWT.NONE);
					newItem.setText("Remove "+table.getSelection()[0].getText());
					newItem.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event e) {
							System.out.println("Remove "+table.getSelection()[0].getText());
							if(builder.removeLink(table.getSelection()[0].getText()) == null) {System.out.println("Failed to Remove");};
							initLinkTable(table);
						}
					});
				}
				catch(ArrayIndexOutOfBoundsException exception) {System.out.println("No Selection or Empty Table");}
			}
		});
	}
	
	public void initClientTable(Table table) {
		// TODO Auto-generated method stub
		table.removeAll();
		for(Client client : riq.getClientList()) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0,client.getName());
			tableItem.setText(1,client.getIP());
		}
		initClientTableListeners(table);
	}

	public void initClientTableListeners(Table table) {
		// Open Client (Doesn't Work yet)
		for(Listener listener : table.getListeners(SWT.MouseDoubleClick)) {
			table.removeListener(SWT.MouseDoubleClick, listener);
		}
		table.addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event e) {
				try {
					String link = table.getSelection()[0].getText();
					if (builder.getLink(link)==null) {System.out.println("\"Clients\" cannot be opened"); return;}
					System.out.println("View Link "+link);
					Shell linkview = new LinkConfigViewer(display, builder, builder.getLink(link), true);
					linkview.open();
					linkview.layout();
					while (!linkview.isDisposed()) {
						if (!display.readAndDispatch()) {
							display.sleep();
						}
					}
					initLinkTable(table);
					initClientTable(clientTable);
				}
				catch(ArrayIndexOutOfBoundsException exception) {System.out.println("No Selection or Empty Table");}
				initLinkTable(table);
			}
		});
		
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
					// Open Client (Doesn't work yet)
					MenuItem newItem = new MenuItem(menu, SWT.NONE);
					newItem.setText("Open "+table.getSelection()[0].getText());
					newItem.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event e) {
							try {
								String link = table.getSelection()[0].getText();
								if (builder.getLink(link)==null) {System.out.println("\"Clients\" cannot be opened"); return;}
								System.out.println("View Link "+table.getSelection()[0].getText());
								Shell linkview = new LinkConfigViewer(display, builder, builder.getLink(table.getSelection()[0].getText()), true);
								linkview.open();
								linkview.layout();
								while (!linkview.isDisposed()) {
									if (!display.readAndDispatch()) {
										display.sleep();
									}
								}
								initLinkTable(table);
								initClientTable(clientTable);
							}
							catch(ArrayIndexOutOfBoundsException exception) {System.out.println("No Selection or Empty Table");}
							initLinkTable(table);
						}
					});

					// Remove Client (Works)
					newItem = new MenuItem(menu, SWT.NONE);
					newItem.setText("Remove "+table.getSelection()[0].getText());
					newItem.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event e) {
							System.out.println("Remove "+table.getSelection()[0].getText());
							if(riq.removeClient(table.getSelection()[0].getText()) == null) {System.out.println("Failed to Remove");};
							initClientTable(table);
						}
					});
				}
				catch(ArrayIndexOutOfBoundsException exception) {System.out.println("No Selection or Empty Table");}
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
