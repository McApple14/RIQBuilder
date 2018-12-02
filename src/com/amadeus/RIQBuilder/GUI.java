package com.amadeus.RIQBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class GUI {

	private static RIQBuilder builder;
	private Display display;
	
	protected Shell shlRiqbuilder;
	protected Shell childShellRIQWizard;
	private Table riqTable;
	private ScrolledComposite scrolledComposite;
	private TableColumn tblclmnName;
	private TableColumn tblclmnLinks;
	private TableColumn tblclmnClients;
	
	

	// This will be moved to Main.java when complete
	public static void main(String[] args) {
		
		builder = new RIQBuilder();
		
		try {
			GUI window = new GUI();
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
		shlRiqbuilder.setSize(701, 400);
		shlRiqbuilder.setText("RIQBuilder");
		
		Button btnAddRIQ = new Button(shlRiqbuilder, SWT.NONE);
		btnAddRIQ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				childShellRIQWizard();
				childShellRIQWizard.open();
				childShellRIQWizard.layout();
				while (!childShellRIQWizard.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
				initTable(riqTable);
			}
		});
		btnAddRIQ.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnAddRIQ.setBounds(482, 10, 192, 37);
		btnAddRIQ.setText("Add RIQ");
		
		scrolledComposite = new ScrolledComposite(shlRiqbuilder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
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
		
		initTable(riqTable);
		
		scrolledComposite.setContent(riqTable);
		scrolledComposite.setMinSize(riqTable.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
	
	/**
	 * Create contents of the Add RIQ window.
	 */
	protected void childShellRIQWizard() {
		childShellRIQWizard = new Shell();
		childShellRIQWizard.setSize(700, 400);
		childShellRIQWizard.setText("SWT Application");
		
		Label lblNewLabel = new Label(childShellRIQWizard, SWT.NONE);
		lblNewLabel.setBounds(25, 30, 55, 15);
		lblNewLabel.setText("Name:");
		
		Label lblLeftKgd = new Label(childShellRIQWizard, SWT.NONE);
		lblLeftKgd.setText("Left KG175D");
		lblLeftKgd.setBounds(25, 84, 80, 15);
		
		Label lblRightKgd = new Label(childShellRIQWizard, SWT.NONE);
		lblRightKgd.setText("Right KG175D");
		lblRightKgd.setBounds(289, 84, 80, 15);
		
		Label label = new Label(childShellRIQWizard, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(259, 84, 2, 265);
		
		Label lblPTIPLeft = new Label(childShellRIQWizard, SWT.NONE);
		lblPTIPLeft.setBounds(25, 130, 80, 15);
		lblPTIPLeft.setText("PT IP: ");
		
		Text textName = new Text(childShellRIQWizard, SWT.BORDER);
		textName.setBounds(86, 30, 101, 21);
		
		Text leftPTIP = new Text(childShellRIQWizard, SWT.BORDER);
		leftPTIP.setBounds(130, 130, 101, 21);
		
		Text leftPTGW = new Text(childShellRIQWizard, SWT.BORDER);
		leftPTGW.setBounds(130, 180, 101, 21);
		
		Label lblPTGWLeft = new Label(childShellRIQWizard, SWT.NONE);
		lblPTGWLeft.setText("PT Gateway: ");
		lblPTGWLeft.setBounds(25, 180, 80, 15);
		
		Text leftCTIP = new Text(childShellRIQWizard, SWT.BORDER);
		leftCTIP.setBounds(130, 230, 101, 21);
		
		Label lblCTIPLeft = new Label(childShellRIQWizard, SWT.NONE);
		lblCTIPLeft.setText("CT IP: ");
		lblCTIPLeft.setBounds(25, 230, 80, 15);
		
		Text leftCTGW = new Text(childShellRIQWizard, SWT.BORDER);
		leftCTGW.setBounds(130, 280, 101, 21);
		
		Label lblCTGWLeft = new Label(childShellRIQWizard, SWT.NONE);
		lblCTGWLeft.setText("CT Gateway: ");
		lblCTGWLeft.setBounds(25, 280, 80, 15);
		
		Label label_1 = new Label(childShellRIQWizard, SWT.NONE);
		label_1.setText("PT IP: ");
		label_1.setBounds(289, 130, 80, 15);
		
		Text rightPTIP = new Text(childShellRIQWizard, SWT.BORDER);
		rightPTIP.setBounds(394, 130, 101, 21);
		
		Label label_2 = new Label(childShellRIQWizard, SWT.NONE);
		label_2.setText("PT Gateway: ");
		label_2.setBounds(289, 180, 80, 15);
		
		Text rightPTGW = new Text(childShellRIQWizard, SWT.BORDER);
		rightPTGW.setBounds(394, 180, 101, 21);
		
		Text rightCTIP = new Text(childShellRIQWizard, SWT.BORDER);
		rightCTIP.setBounds(394, 230, 101, 21);
		
		Label label_3 = new Label(childShellRIQWizard, SWT.NONE);
		label_3.setText("CT IP: ");
		label_3.setBounds(289, 230, 80, 15);
		
		Label label_4 = new Label(childShellRIQWizard, SWT.NONE);
		label_4.setText("CT Gateway: ");
		label_4.setBounds(289, 280, 80, 15);
		
		Text rightCTGW = new Text(childShellRIQWizard, SWT.BORDER);
		rightCTGW.setBounds(394, 280, 101, 21);
		
		Button btnAdd = new Button(childShellRIQWizard, SWT.NONE);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				System.out.println("RIQS: "+builder.getRIQs());
				String[] input = new String[] {
					textName.getText(), 	//Name
					leftPTIP.getText(), 	//Left PTIP
					leftPTGW.getText(),		//Left PTGW
					leftCTIP.getText(),		//Left CTIP
					leftCTGW.getText(),		//Left CTGW
					rightPTIP.getText(),	//Right PTIP
					rightPTGW.getText(),	//Right PTGW
					rightCTIP.getText(),	//Right CTIP
					rightCTGW.getText()		//Right CTGW
				};
				for(String in : input) {
					if (in=="" || in==null) {
						// Error dialog box
						MessageBox dialog = new MessageBox(childShellRIQWizard, SWT.ICON_ERROR | SWT.OK);
						dialog.setText("Input Error");
						dialog.setMessage("Invalid input; cannot have blank entries");
						dialog.open();
						return;
					}
				}
				builder.addRIQ(new RIQ(input[0],
						new KG175D(input[0].concat("LeftKG"),input[1],input[2],input[3],input[4]),
						new KG175D(input[0].concat("RightKG"),input[5],input[6],input[7],input[8])));
				System.out.println("RIQS: "+builder.getRIQs());
				childShellRIQWizard.dispose();
			}
		});
		btnAdd.setText("Add");
		btnAdd.setBounds(544, 30, 130, 37);
		
		Button btnCancel = new Button(childShellRIQWizard, SWT.NONE);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				childShellRIQWizard.dispose();
			}
		});
		btnCancel.setText("Cancel");
		btnCancel.setBounds(544, 130, 130, 37);
		
		Button btnAutoCalc = new Button(childShellRIQWizard, SWT.NONE);
		btnAutoCalc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				String name = textName.getText();
				if(name == "") {
					// Error dialog box
					MessageBox dialog = new MessageBox(childShellRIQWizard, SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("Name required for Auto Calc");
					dialog.open();
				}
				else {
					System.out.println("Builder has "+builder.getRIQs().size()+" RIQS");
					RIQBuilder temp = new RIQBuilder(builder);
					System.out.println("TEMP has "+temp.getRIQs().size()+" RIQS");
					temp.addRIQ(new RIQ(name));
					System.out.println("TEMP has "+temp.getRIQs().size()+" RIQS");
					temp.reInitialization();
					System.out.println("TEMP has "+temp.getRIQs().size()+" RIQS");
					
					RIQ riq = temp.getRIQ(name);
					leftPTIP.setText(riq.getKGs()[0].getPTIP());
					leftPTGW.setText(riq.getKGs()[0].getPTGW());
					leftCTIP.setText(riq.getKGs()[0].getCTIP());
					leftCTGW.setText(riq.getKGs()[0].getCTGW());
					rightPTIP.setText(riq.getKGs()[1].getPTIP());
					rightPTGW.setText(riq.getKGs()[1].getPTGW());
					rightCTIP.setText(riq.getKGs()[1].getCTIP());
					rightCTGW.setText(riq.getKGs()[1].getCTGW());
				}
			}
		});
		btnAutoCalc.setText("Auto Calc");
		btnAutoCalc.setBounds(544, 80, 130, 37);
	}
	
	
	
	public static void initTable(Table t) {
		t.removeAll();
		for(RIQ riq : builder.getRIQs()) {
			TableItem tableItem = new TableItem(t, SWT.NONE);
			tableItem.setText(0,riq.getName());
			tableItem.setText(1,Integer.toString(riq.getLinks().size()));
			tableItem.setText(2,Integer.toString(riq.getClientList().size()));
		}
		tableListeners(t);
	}
	
	public static void tableListeners(Table t) {
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
						System.out.println("Remove "+t.getSelection()[0].getText());
						if(builder.removeRIQ(t.getSelection()[0].getText()) == null) {System.out.println("Failed to Remove");};
						initTable(t);
					}
				});
				
				// Remove RIQ (Works)
				newItem = new MenuItem(menu, SWT.NONE);
				newItem.setText("Remove "+t.getSelection()[0].getText());
				newItem.addListener(SWT.Selection, new Listener() {
					public void handleEvent(Event e) {
						System.out.println("Remove "+t.getSelection()[0].getText());
						if(builder.removeRIQ(t.getSelection()[0].getText()) == null) {System.out.println("Failed to Remove");};
						initTable(t);
					}
				});
			}
		});
	}
}
