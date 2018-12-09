package com.amadeus.RIQBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class SAViewer extends Shell {

	private Display display;
	private RIQBuilder builder;
	private RIQ riq;
	private Table table;
	private Button btnLeftKgd;
	private Button btnRightKgd;
	private Button btnAddSa;
	private Button btnAddClient;
	private Button btnClear;
	private Button btnClose;
	private Composite composite;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			RIQBuilder builder = new RIQBuilder(true);
			SAViewer shell = new SAViewer(display, builder, builder.getRIQs().get(0));
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
	public SAViewer(Display display, RIQBuilder builder, RIQ riq) {
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
		setText("SA Viewer");
		setSize(700, 400);
		
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginHeight = 10;
		gridLayout.marginWidth = 10;
		setLayout(gridLayout);
		
		composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		btnLeftKgd = new Button(composite, SWT.RADIO);
		btnLeftKgd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				initSATable(table);
			}
		});
		btnLeftKgd.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		btnLeftKgd.setText("Left KG175D");
		btnLeftKgd.setSelection(true);
		
		btnRightKgd = new Button(composite, SWT.RADIO);
		btnRightKgd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				initSATable(table);
			}
		});
		btnRightKgd.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		btnRightKgd.setText("Right KG175D");
		new Label(this, SWT.NONE);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 4));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(100);
		tblclmnName.setText("Name");
		
		TableColumn tblclmnHostAddresscidr = new TableColumn(table, SWT.NONE);
		tblclmnHostAddresscidr.setWidth(120);
		tblclmnHostAddresscidr.setText("Host Address/CIDR");
		
		TableColumn tblclmnRemoteEcuPt = new TableColumn(table, SWT.WRAP);
		tblclmnRemoteEcuPt.setWidth(149);
		tblclmnRemoteEcuPt.setText("Remote ECU PT Address");
		
		TableColumn tblclmnRemoteEcuCt = new TableColumn(table, SWT.WRAP);
		tblclmnRemoteEcuCt.setWidth(151);
		tblclmnRemoteEcuCt.setText("Remote ECU CT Address");
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		btnAddSa = new Button(this, SWT.NONE);
		btnAddSa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Shell saWizard = new SAWizard(display, riq.getKGs()[getKGSelection()]);
				saWizard.open();
				saWizard.layout();
				while (!saWizard.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
			}
		});
		GridData gd_btnAddSa = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnAddSa.heightHint = 40;
		gd_btnAddSa.widthHint = 80;
		btnAddSa.setLayoutData(gd_btnAddSa);
		btnAddSa.setText("Add SA");
		
		btnAddClient = new Button(this, SWT.NONE);
		GridData gd_btnAddClient = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnAddClient.heightHint = 40;
		gd_btnAddClient.widthHint = 80;
		btnAddClient.setLayoutData(gd_btnAddClient);
		btnAddClient.setText("Add Client");
		
		btnClear = new Button(this, SWT.NONE);
		GridData gd_btnClear = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnClear.heightHint = 40;
		gd_btnClear.widthHint = 80;
		btnClear.setLayoutData(gd_btnClear);
		btnClear.setText("Clear");
		
		initSATable(table);
		
		btnClose = new Button(this, SWT.NONE);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				display.dispose();
				Application.open(builder, Application.RIQCONFIGVIEWER);
			}
		});
		GridData gd_btnClose = new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1);
		gd_btnClose.heightHint = 40;
		gd_btnClose.widthHint = 80;
		btnClose.setLayoutData(gd_btnClose);
		btnClose.setText("Back");
	}
	
	private int getKGSelection() {
		return btnLeftKgd.getSelection() ? 0 : 1;
	}
	
	private void initSATable(Table table) {
		table.removeAll();
		int selection = btnLeftKgd.getSelection() ? 0 : 1;
		for(SA sa : riq.getKGs()[selection].getSAList()) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0,sa.getName());
			tableItem.setText(1,sa.getHost());
			tableItem.setText(2,sa.getRemotePT());
			tableItem.setText(3,sa.getRemoteCT());
		}
		initSATableListeners(table);
	}
	
	private void initSATableListeners(Table table) {
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
