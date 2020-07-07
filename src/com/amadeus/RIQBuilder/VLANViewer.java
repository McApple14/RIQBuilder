package com.amadeus.RIQBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class VLANViewer extends Shell {

	RIQ riq;
	Display display;
	boolean viewOnly;
	private Table table;
	
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
		setSize(815, 400);

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginHeight = 10;
		gridLayout.marginWidth = 10;
		setLayout(gridLayout);
		
		Label lblVlanViewer = new Label(this, SWT.NONE);
		lblVlanViewer.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblVlanViewer.setAlignment(SWT.CENTER);
		lblVlanViewer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		lblVlanViewer.setText("VLAN Viewer");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnVlan = new TableColumn(table, SWT.CENTER);
		tblclmnVlan.setWidth(49);
		tblclmnVlan.setText("VLAN");
		
		TableColumn tblclmnName = new TableColumn(table, SWT.CENTER);
		tblclmnName.setWidth(150);
		tblclmnName.setText("Name");
		
		TableColumn tblclmnDescription = new TableColumn(table, SWT.CENTER);
		tblclmnDescription.setWidth(160);
		tblclmnDescription.setText("Description");
		
		TableColumn tblclmnAdminStatus = new TableColumn(table, SWT.CENTER);
		tblclmnAdminStatus.setWidth(100);
		tblclmnAdminStatus.setText("Admin Status");
		
		TableColumn tblclmnMacLearning = new TableColumn(table, SWT.CENTER);
		tblclmnMacLearning.setWidth(100);
		tblclmnMacLearning.setText("MAC Learning");
		
		TableColumn tblclmnMulticast = new TableColumn(table, SWT.CENTER);
		tblclmnMulticast.setWidth(100);
		tblclmnMulticast.setText("Multicast");
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Button btnAddVlan = new Button(this, SWT.NONE);
		GridData gd_btnAddVlan = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnAddVlan.heightHint = 40;
		gd_btnAddVlan.widthHint = 80;
		btnAddVlan.setLayoutData(gd_btnAddVlan);
		btnAddVlan.setText("Add VLAN");
		
		Button btnBack = new Button(this, SWT.NONE);
		GridData gd_btnBack = new GridData(SWT.FILL, SWT.TOP, false, true, 1, 1);
		gd_btnBack.heightHint = 40;
		gd_btnBack.widthHint = 80;
		btnBack.setLayoutData(gd_btnBack);
		btnBack.setText("Back");
		
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void initVLANTable(Table table) {
		table.removeAll();
		for(VLAN vlan : riq.getVLANs()) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0,Integer.toString(vlan.getVLAN()));
			tableItem.setText(1,vlan.getName());
			tableItem.setText(2,vlan.getDescription());
			tableItem.setText(3,vlan.getAdminStatus() ? "UP" : "DOWN");
			tableItem.setText(4,vlan.getMacLearning() ? "X" : " ");
			switch(vlan.getMulticast()) {
			case 0:
				tableItem.setText(5,"DropUnknown");
				break;
			case 1:
				tableItem.setText(5,"Something");
				break;
			case 2:
				tableItem.setText(5,"SomethingElse");
				break;
			default:
				tableItem.setText(5,"ERROR");
			}
		}
		initVLANTableListeners(table);
	}
	
	public void initVLANTableListeners(Table table) {
		for(Listener listener : table.getListeners(SWT.MouseDoubleClick)) {
			table.removeListener(SWT.MouseDoubleClick, listener);
		}
		
		table.addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event e) {
				try {
					System.out.println("View VLAN "+table.getSelection()[0].getText());
					/*//View VLAN
					Shell linkview = new LinkConfigViewer(display, riq, riq.getVLAN(table.getSelection()[0].getText()), true);
					linkview.open();
					linkview.layout();
					while (!linkview.isDisposed()) {
						if (!display.readAndDispatch()) {
							display.sleep();
						}
					}*/
					initVLANTable(table);
				}
				catch(ArrayIndexOutOfBoundsException exception) {System.out.println("No Selection or Empty Table");}
				initVLANTable(table);
			}
		});
	}

}
