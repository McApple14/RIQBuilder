package com.amadeus.RIQBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class InitWizard extends Shell {

	private RIQBuilder builder;
	String baseUHF;
	String basePPN;
	String baseKG;
	String gateway;
	int baseVLAN;
	private Text textUHF;
	private Text textPPN;
	private Text textKG;
	private Text textGateway;
	private Text textVLAN;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			InitWizard shell = new InitWizard(display, new RIQBuilder(true));
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
	public InitWizard(Display display, RIQBuilder builder) {
		super(display, SWT.SHELL_TRIM);
		//this.display = display;
		this.builder = builder;
		createContents();
		/*
		 * 	String uhfBase = "192.168.0.0";
			String ppnBase = "198.127.1.0";
			String kgBase = "10.11.16.0";
			kgGateway = "10.11.16.16";
			int vlanBase = 30;
		 */
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(450, 300);

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginHeight = 10;
		gridLayout.marginWidth = 10;
		setLayout(gridLayout);
		
		Composite composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.verticalSpacing = 10;
		gl_composite.horizontalSpacing = 10;
		gl_composite.marginHeight = 10;
		gl_composite.marginWidth = 10;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
		
		Label lblUhfBaseIp = new Label(composite, SWT.NONE);
		lblUhfBaseIp.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUhfBaseIp.setText("UHF Base IP Address");
		
		textUHF = new Text(composite, SWT.BORDER);
		textUHF.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPpnBaseIp = new Label(composite, SWT.NONE);
		lblPpnBaseIp.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPpnBaseIp.setText("PPN Base IP Address");
		
		textPPN = new Text(composite, SWT.BORDER);
		textPPN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblKgdBaseIp = new Label(composite, SWT.NONE);
		lblKgdBaseIp.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblKgdBaseIp.setText("KG175D Base CT IP Address");
		
		textKG = new Text(composite, SWT.BORDER);
		textKG.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("KG175D CT Gateway");
		
		textGateway = new Text(composite, SWT.BORDER);
		textGateway.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblBaseVlan = new Label(composite, SWT.NONE);
		lblBaseVlan.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBaseVlan.setText("Base VLAN");
		
		textVLAN = new Text(composite, SWT.BORDER);
		textVLAN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnInit = new Button(this, SWT.NONE);
		btnInit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				try {
					builder = new RIQBuilder();
					baseUHF = Link.ipValidation(textUHF.getText()); if(baseUHF==null) {throw new IllegalArgumentException("Invalid Base UHF IP Address");}
					basePPN = Link.ipValidation(textPPN.getText()); if(basePPN==null) {throw new IllegalArgumentException("Invalid Base PPN IP Address");} 
					baseKG = Link.ipValidation(textKG.getText()); if(baseKG==null) {throw new IllegalArgumentException("Invalid Base PPN IP Address");}
					gateway = Link.ipValidation(textGateway.getText()); if(gateway==null) {throw new IllegalArgumentException("Invalid Gateway IP Address");}
					baseVLAN = Integer.parseInt(textVLAN.getText());
				} catch (NumberFormatException exception) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("Invalid VLAN");
					dialog.open();
					return;
				} catch (IllegalArgumentException exception) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage(exception.getMessage());
					dialog.open();
					return;
				}
				builder.initialization(baseVLAN, basePPN, baseUHF, baseKG, gateway);
				getSelf().dispose();
			}
		});
		GridData gd_btnInit = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnInit.heightHint = 40;
		gd_btnInit.widthHint = 80;
		btnInit.setLayoutData(gd_btnInit);
		btnInit.setText("Initialize");
		
		Button btnClose = new Button(this, SWT.NONE);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getSelf().dispose();
			}
		});
		GridData gd_btnClose = new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1);
		gd_btnClose.heightHint = 40;
		gd_btnClose.widthHint = 80;
		btnClose.setLayoutData(gd_btnClose);
		btnClose.setText("Close");
	}
	
	private Shell getSelf() {return this;}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
