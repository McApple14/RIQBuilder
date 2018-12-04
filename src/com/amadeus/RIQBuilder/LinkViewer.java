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
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 10, 542, 386);
		
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
		
		btnAddLink = new Button(this, SWT.NONE);
		btnAddLink.setBounds(560, 10, 114, 40);
		btnAddLink.setText("Add Link");
		
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
				createContents();
				open();
				layout();
				initLinkTable(table);
			}
		});
		
		btnClose = new Button(this, SWT.NONE);
		btnClose.setText("Close");
		btnClose.setBounds(558, 56, 114, 40);
		
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getSelf().dispose();
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
				MenuItem[] items = menu.getItems();
				for(int i=0; i < items.length; i++) {
					items[i].dispose();
				}
				// Remove Link (Works)
				MenuItem newItem = new MenuItem(menu, SWT.NONE);
				newItem.setText("Remove "+table.getSelection()[0].getText());
				newItem.addListener(SWT.Selection, new Listener() {
					public void handleEvent(Event e) {
						System.out.println("Remove "+table.getSelection()[0].getText());
						if(builder.removeLink(table.getSelection()[0].getText()) == null) {System.out.println("Failed to Remove");};
						initLinkTable(table);
					}
				});
			}
		});
	}
	
	private Shell getSelf() {return this;}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
