package com.amadeus.RIQBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class LinkConfigViewer extends Shell {

	private RIQBuilder builder;
	private Link link;
	private boolean viewOnly;
	private Text textName;
	private Text textDescription;
	private Text textCESType;
	private Text textCESInterface;
	private Text textPacketInterface;
	private Text textLocalIP;
	private Text textRemoteIP;
	private Text textLocalUDP;
	private Text textRemoteUDP;
	private Text textSubnet;
	private Text textGateway;
	private Text textVLAN;
	private Text textPBit;
	private Text textIPTTL;
	private Text textDSCP;
	private Text textRemotePDV;
	private Combo comboAdmin;
	private Button btnSaveEdit;
	private Button btnRemoveLink;
	private Button btnClose;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			RIQBuilder builder = new RIQBuilder();
			LinkConfigViewer shell = new LinkConfigViewer(display, builder, builder.getLinks().get(0), true);
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
	public LinkConfigViewer(Display display, RIQBuilder builder, Link link, boolean viewOnly) {
		super(display, SWT.SHELL_TRIM);
		this.builder = builder;
		this.link = link;
		this.viewOnly = viewOnly;
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(700, 450);
		
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginHeight = 10;
		gridLayout.marginWidth = 10;
		setLayout(gridLayout);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout gl_composite = new GridLayout(4, false);
		gl_composite.verticalSpacing = 10;
		gl_composite.marginWidth = 10;
		gl_composite.marginHeight = 10;
		gl_composite.horizontalSpacing = 10;
		composite.setLayout(gl_composite);
		
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("Name:");
		
		textName = new Text(composite, SWT.BORDER);
		textName.setEditable(!viewOnly);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("Admin Status:");
		
		comboAdmin = new Combo(composite, SWT.NONE);
		comboAdmin.setEnabled(!viewOnly);
		comboAdmin.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("Description:");
		
		textDescription = new Text(composite, SWT.BORDER);
		textDescription.setEditable(!viewOnly);
		textDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("CES Type:");
		
		textCESType = new Text(composite, SWT.BORDER);
		textCESType.setText("Serial");
		textCESType.setEditable(false);
		textCESType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("CES Interface:");
		
		textCESInterface = new Text(composite, SWT.BORDER);
		textCESInterface.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("Packet Interface:");
		
		textPacketInterface = new Text(composite, SWT.BORDER);
		textPacketInterface.setEditable(!viewOnly);
		textPacketInterface.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_6.setText("Local IP Address:");
		
		textLocalIP = new Text(composite, SWT.BORDER);
		textLocalIP.setEditable(!viewOnly);
		textLocalIP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_7.setText("Remote IP Address:");
		
		textRemoteIP = new Text(composite, SWT.BORDER);
		textRemoteIP.setEditable(!viewOnly);
		textRemoteIP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_8.setText("Local UDP Port:");
		
		textLocalUDP = new Text(composite, SWT.BORDER);
		textLocalUDP.setEditable(!viewOnly);
		textLocalUDP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_9 = new Label(composite, SWT.NONE);
		label_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_9.setText("Remote UDP Port:");
		
		textRemoteUDP = new Text(composite, SWT.BORDER);
		textRemoteUDP.setEditable(!viewOnly);
		textRemoteUDP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_10 = new Label(composite, SWT.NONE);
		label_10.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_10.setText("Subnet Mask:");
		
		textSubnet = new Text(composite, SWT.BORDER);
		textSubnet.setEditable(!viewOnly);
		textSubnet.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_11 = new Label(composite, SWT.NONE);
		label_11.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_11.setText("Gateway IP Address:");
		
		textGateway = new Text(composite, SWT.BORDER);
		textGateway.setEditable(!viewOnly);
		textGateway.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_12 = new Label(composite, SWT.NONE);
		label_12.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_12.setText("VLAN:");
		
		textVLAN = new Text(composite, SWT.BORDER);
		textVLAN.setEditable(!viewOnly);
		textVLAN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_13 = new Label(composite, SWT.NONE);
		label_13.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_13.setText("P-Bit Value:");
		
		textPBit = new Text(composite, SWT.BORDER);
		textPBit.setText("6");
		textPBit.setEditable(false);
		textPBit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_14 = new Label(composite, SWT.NONE);
		label_14.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_14.setText("IP TTL:");
		
		textIPTTL = new Text(composite, SWT.BORDER);
		textIPTTL.setText("1");
		textIPTTL.setEditable(false);
		textIPTTL.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_15 = new Label(composite, SWT.NONE);
		label_15.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_15.setText("DSCP:");
		
		textDSCP = new Text(composite, SWT.BORDER);
		textDSCP.setText("43");
		textDSCP.setEditable(false);
		textDSCP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_16 = new Label(composite, SWT.NONE);
		label_16.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_16.setText("Remote PDV Buffer:");
		
		textRemotePDV = new Text(composite, SWT.BORDER);
		textRemotePDV.setText("Low");
		textRemotePDV.setEditable(false);
		textRemotePDV.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Composite compositeButtons = new Composite(this, SWT.NONE);
		compositeButtons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		GridLayout gl_compositeButtons = new GridLayout(1, false);
		gl_compositeButtons.verticalSpacing = 10;
		gl_compositeButtons.horizontalSpacing = 10;
		gl_compositeButtons.marginHeight = 10;
		gl_compositeButtons.marginWidth = 10;
		compositeButtons.setLayout(gl_compositeButtons);
		
		btnSaveEdit = new Button(compositeButtons, SWT.NONE);
		btnSaveEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if(viewOnly) {
					viewOnly = false;
					setText();
				}
				else
				{
					String[] input = getInput();
					if(!inputValidator(input)) {return;}
					RIQ local = link.getLocalRIQ();
					RIQ remote = link.getRemoteRIQ();
					int type = link.getType();
					int kg = link.getSideKG();
					
					/*
					 * Link Constructor
					 * @param name Name
					 * @param localRIQ Local RIQ
					 * @param localRIQ Remote RIQ
					 * @param type Type of link (0 for UHF, 1 for Fiber Shot)
					 * @param localIP Local IP Address
					 * @param remoteIP Remote IP Address
					 * @param interfaceCES CES Interface
					 * @param vLAN VLAN
					 * @param subnetMask SubnetMask
					 * @param kg175d Left or Right KG for the Local RIQ (0 for left, 1 for right) (Used for Gateway IP)
					 * @param udpPort UDP port for local AND remote RIQs
					 */					
					link.editLink(
							input[0],
							local,
							remote,
							type,
							input[3],
							input[12],
							input[2],
							Integer.parseInt(input[6]),
							input[5],
							kg,
							Integer.parseInt(input[4]),
							Integer.parseInt(input[13]),
							input[9].compareTo("Up")==0 ? true : false
							);
					viewOnly = true;
					setText();
				}
			}
		});
		GridData gd_btnSaveEdit = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnSaveEdit.heightHint = 40;
		gd_btnSaveEdit.widthHint = 80;
		btnSaveEdit.setLayoutData(gd_btnSaveEdit);
		btnSaveEdit.setText(viewOnly ? "Edit" : "Save");
		
		btnRemoveLink = new Button(compositeButtons, SWT.NONE);
		btnRemoveLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				builder.removeLink(link.getName());
				getSelf().close();
			}
		});
		GridData gd_btnRemoveLink = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnRemoveLink.heightHint = 40;
		gd_btnRemoveLink.widthHint = 90;
		btnRemoveLink.setLayoutData(gd_btnRemoveLink);
		btnRemoveLink.setText("Remove Link");
		
		btnClose = new Button(compositeButtons, SWT.NONE);
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getSelf().close();
			}
		});
		GridData gd_btnClose = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnClose.heightHint = 40;
		gd_btnClose.widthHint = 80;
		btnClose.setLayoutData(gd_btnClose);
		btnClose.setText("Close");
		
		setText();
	}
	
	private void setText() {
		if(link == null) {System.out.println("Something went wrong"); return;}
		String[] output = link.toString().split(",");
		// Put the link output to the window
		textName.setText(output[0]);							// 0
		textDescription.setText(output[1]);						// 1
		textCESInterface.setText(output[2]);					// 2
		textLocalIP.setText(output[3]);							// 3
		textLocalUDP.setText(output[4]);						// 4
		textSubnet.setText(output[5]);							// 5
		textVLAN.setText(output[6]);							// 6
		textIPTTL.setText(output[7]);							// 7
		textRemotePDV.setText(output[8]);						// 8
		comboAdmin.setText(output[9]);							// 9
		textCESType.setText(output[10]);						// 10
		textPacketInterface.setText(output[11]);				// 11
		textRemoteIP.setText(output[12]);						// 12
		textRemoteUDP.setText(output[13]);						// 13
		textGateway.setText(output[14]);						// 14
		textPBit.setText(output[15]);							// 15
		textDSCP.setText(output[16]);							// 16
		
		textName.setEditable(!viewOnly);						// 0
		textDescription.setEditable(!viewOnly);					// 1
		textCESInterface.setEditable(!viewOnly);				// 2
		textLocalIP.setEditable(!viewOnly);						// 3
		textLocalUDP.setEditable(!viewOnly);					// 4
		textSubnet.setEditable(!viewOnly);						// 5
		textVLAN.setEditable(!viewOnly);						// 6
		comboAdmin.setEnabled(!viewOnly);						// 9
		textPacketInterface.setEditable(!viewOnly);				// 11
		textRemoteIP.setEditable(!viewOnly);					// 12
		textRemoteUDP.setEditable(!viewOnly);					// 13
		textGateway.setEditable(!viewOnly);						// 14
		btnSaveEdit.setText(viewOnly ? "Edit" : "Save");
	}
	
	private String[] getInput() {
		return new String[] {
			textName.getText(),										// 0
			textDescription.getText(),								// 1
			textCESInterface.getText(),								// 2 (Needs CES Validation)
			textLocalIP.getText(),									// 3 (Needs IP Validation)
			textLocalUDP.getText(),									// 4 (Needs Port validation)
			textSubnet.getText(),									// 5 (Needs SubnetMask Validation)
			textVLAN.getText(),										// 6 (Needs VLAN validation)
			textIPTTL.getText(),									// 7
			textRemotePDV.getText(),								// 8
			comboAdmin.getText(),									// 9
			textCESType.getText(),									// 10
			Link.validateCESInterface(textPacketInterface.getText()),//11
			textRemoteIP.getText(),									// 12 (Needs IP Validation)
			textRemoteUDP.getText(),								// 13 (Needs Port Validation)
			textGateway.getText(),									// 14 (Needs IP Validation)
			textPBit.getText(),										// 15
			textDSCP.getText()										// 16
		};
	}
	
	private boolean inputValidator(String[] input) {
		int in;
		for (int i = 0; i < input.length; i++) {
			switch(i) {
			case 0:
		  //case 1: UNECESSARY; Blank input allowed
			case 7:
			case 8:
			case 9:
			case 10:
			case 15:
			case 16:
				if(input[i] == null | input[i] == "") {
					// Error dialog box
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("Invalid input; cannot have blank or null entries");
					dialog.open();
					return false;
				}
				break;
			case 2:
				if(Link.validateCESInterface(input[i]) == null) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("CES Interface \""+ input[i] +"\" is invalid");
					dialog.open();
					return false;
				}
				break;
			case 3:
			case 12:
			case 14:
				if(Link.ipValidation(input[i]) == null) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("IP Address \""+ input[i] +"\" is an invalid IP");
					dialog.open();
					return false;
				}
				break;
			case 4:
			case 13:
				in = -1;
				try {
					in = Integer.parseInt(input[i]);
				}
				catch (NumberFormatException e) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("UDP Port \""+ input[i] +"\" is not a valid port");
					dialog.open();
					return false;
				}
				if (in > 65535 || in <= 0) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("UDP Port \""+ input[i] +"\" is not a valid port");
					dialog.open();
					return false;
				}
				break;
			case 5:
				if(Link.subnetMaskValidation(input[i]) == null) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("SubnetMask \""+ input[i] +"\" is not a valid SubnetMask");
					dialog.open();
					return false;
				}
				break;
			case 6:
				in = -1;
				try {
					in = Integer.parseInt(input[i]);
				}
				catch (NumberFormatException e) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("VLAN \""+ input[i] +"\" is not a valid VLAN");
					dialog.open();
					return false;
				}
				if (in <= 0) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("VLAN \""+ input[i] +"\" is not a valid VLAN");
					dialog.open();
					return false;
				}
			}
		}
		return true;
	}
	
	private Shell getSelf() {
		return this;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
