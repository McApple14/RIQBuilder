package com.amadeus.RIQBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class LinkViewer extends Shell {

	private RIQBuilder builder;
	private Display display;
	private Table table;
	private Menu menu;
	private TableColumn tblclmnName;
	private TableColumn tblclmnLocalRiq;
	private TableColumn tblclmnLocalRiqIp;
	private TableColumn tblclmnRemoteRiq;
	private TableColumn tblclmnRemoteRiqIp;
	private Button btnAddLink;
	private Button btnClose;
	private Composite composite;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			LinkViewer shell = new LinkViewer(display, new RIQBuilder());
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
	public LinkViewer(Display display, RIQBuilder builder) {
		super(display, SWT.SHELL_TRIM);
		this.builder = builder;
		this.display = display;
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		this.setSize(700, 450);
		this.setText("Link Viewer");
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginHeight = 10;
		gridLayout.marginWidth = 10;
		setLayout(gridLayout);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		menu = new Menu(table);
		table.setMenu(menu);
		
		tblclmnName = new TableColumn(table, SWT.CENTER);
		tblclmnName.setWidth(140);
		tblclmnName.setText("Name");
		
		tblclmnLocalRiq = new TableColumn(table, SWT.CENTER);
		tblclmnLocalRiq.setWidth(90);
		tblclmnLocalRiq.setText("Local RIQ");
		
		tblclmnLocalRiqIp = new TableColumn(table, SWT.CENTER);
		tblclmnLocalRiqIp.setWidth(110);
		tblclmnLocalRiqIp.setText("Local RIQ IP");
		
		tblclmnRemoteRiq = new TableColumn(table, SWT.CENTER);
		tblclmnRemoteRiq.setWidth(90);
		tblclmnRemoteRiq.setText("Remote RIQ");
		
		tblclmnRemoteRiqIp = new TableColumn(table, SWT.CENTER);
		tblclmnRemoteRiqIp.setWidth(110);
		tblclmnRemoteRiqIp.setText("Remote RIQ IP");
		
		composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.verticalSpacing = 10;
		gl_composite.horizontalSpacing = 10;
		gl_composite.marginHeight = 10;
		gl_composite.marginWidth = 10;
		composite.setLayout(gl_composite);
		
		btnAddLink = new Button(composite, SWT.NONE);
		GridData gd_btnAddLink = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnAddLink.heightHint = 40;
		gd_btnAddLink.widthHint = 70;
		btnAddLink.setLayoutData(gd_btnAddLink);
		btnAddLink.setText("Add Link");
		
		btnClose = new Button(composite, SWT.NONE);
		GridData gd_btnClose = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnClose.heightHint = 40;
		gd_btnClose.widthHint = 70;
		btnClose.setLayoutData(gd_btnClose);
		btnClose.setText("Close");
		
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getSelf().dispose();
			}
		});
		
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
				initLinkTable(table);
			}
		});
		
		initLinkTable(table);
	}
	
	public void initLinkTable(Table table) {
		// TODO Auto-generated method stub
		table.removeAll();
		for(Link link : builder.getLinks()) {
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
		// TODO Auto-generated method stub
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
						// Open link
						MenuItem newItem = new MenuItem(menu, SWT.NONE);
						newItem.setText("View Link");
						newItem.addListener(SWT.Selection, new Listener() {
							public void handleEvent(Event e) {
								System.out.println("View Link "+table.getSelection()[0].getText());
								Shell linkview = new LinkConfigViewer(display, builder, builder.getLink(table.getSelection()[0].getText()), true);
								linkview.open();
								linkview.layout();
								while (!linkview.isDisposed()) {
									if (!display.readAndDispatch()) {
									display.sleep();
									}
								}						
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
