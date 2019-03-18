package com.amadeus.RIQBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class ClientWizard extends Shell {

	private RIQ localRIQ;
	private Button btnAddClient;
	private Button btnClose;
	private Text txtName;
	private Text txtIpAddress;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			RIQBuilder bldr = new RIQBuilder(true);
			ClientWizard shell = new ClientWizard(display, bldr.getRIQs().get(0));
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
	 * @wbp.parser.constructor
	 */
	public ClientWizard(Display display, RIQ localRIQ) {
		super(display, SWT.SHELL_TRIM);
		this.localRIQ = localRIQ;
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setSize(500, 161);
		setText("Add Client");
		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginWidth = 10;
		setLayout(gridLayout);
		
		Label lblName = new Label(this, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText("Name:");
		
		txtName = new Text(this, SWT.BORDER);
		txtName.setText("Name");
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnAddClient = new Button(this, SWT.NONE);
		GridData gd_btnAddClient = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_btnAddClient.heightHint = 40;
		gd_btnAddClient.widthHint = 80;
		btnAddClient.setLayoutData(gd_btnAddClient);
		btnAddClient.setText("Add Client");
		btnAddClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				String name = txtName.getText();
				String ip = txtIpAddress.getText();
				
				if(name == "") {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("Name cannot be blank");
					dialog.open();
				}
				
				if(Link.ipValidation(ip) == null) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("Invalid IP Address");
					dialog.open();
				}
				
				if(!(localRIQ.addClient(name, ip))) {
					System.out.println("Client "+name+" : "+ip+" could not be added to RIQ "+localRIQ);
				}
				else {
					System.out.println("Client "+name+" : "+ip+" has been added to RIQ "+localRIQ);
				}
			}
		});
		
		Label lblIpAddress = new Label(this, SWT.NONE);
		lblIpAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblIpAddress.setText("IP Address:");
		
		txtIpAddress = new Text(this, SWT.BORDER);
		txtIpAddress.setText("IP Address");
		txtIpAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnClose = new Button(this, SWT.NONE);
		GridData gd_btnClose = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_btnClose.heightHint = 40;
		gd_btnClose.widthHint = 80;
		btnClose.setLayoutData(gd_btnClose);
		btnClose.setText("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				dispose();
			}
		});
	}
	
	private Shell getSelf() {
		return this;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
