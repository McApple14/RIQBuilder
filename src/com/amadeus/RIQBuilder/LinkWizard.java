package com.amadeus.RIQBuilder;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class LinkWizard extends Shell {

	private RIQBuilder builder;
	private RIQ localRIQ;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			LinkWizard shell = new LinkWizard(display, new RIQBuilder());
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
	private Label lblName;
	private Text textName;
	private Text textDescription;
	private Label lblDescription;
	private Text textCESInterface;
	private Label lblCesInterface;
	private Text textLocalIP;
	private Label lblLocalIpAddress;
	private Text textLocalUDP;
	private Label lblLocalUdpPort;
	private Text textSubnet;
	private Label lblSubnetMask;
	private Text textVLAN;
	private Label lblVlan;
	private Label lblIpTtl;
	private Text textIPTTL;
	private Text textRemotePDV;
	private Label lblRemotePdvBuffer;
	private Label lblAdminStatus;
	private Text textCESType;
	private Label lblCesType;
	private Text textPacketInterface;
	private Label lblPacketInterface;
	private Text textRemoteIP;
	private Label lblRemoteIpAddress;
	private Text textRemoteUDP;
	private Label lblRemoteUdpPort;
	private Text textGatewayIP;
	private Label lblGatewayIpAddress;
	private Text textPBit;
	private Label lblPbitValue;
	private Label lblDscp;
	private Text textDSCP;
	private Combo comboAdminStatus;
	private Composite composite_2;
	private Button btnAdd;
	private Button btnCancel;
	private Button btnClear;
	private Button btnAutoCalc;
	private Label lblCesInterface_1;
	private Combo comboCES;
	private Combo comboLocalRIQ;
	private Combo comboRemoteRIQ;
	private Button btnPPN;
	private Button btnUHF;
	private Button btnLeft;
	private Button btnRight;
	private Label lblCesConfiguration;

	/**
	 * Create the shell.
	 * @param display
	 * @wbp.parser.constructor
	 */
	public LinkWizard(Display display, RIQBuilder builder) {
		super(display, SWT.SHELL_TRIM);
		this.builder = builder;
		localRIQ = null;
		createContents();
	}
	
	public LinkWizard(Display display, RIQBuilder builder, RIQ localRIQ) {
		super(display, SWT.SHELL_TRIM);
		this.builder = builder;
		this.localRIQ = localRIQ;
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setSize(700, 450);
		setText("Add Link");
		setLayout(new GridLayout(1, false));
		
		Composite composite_3 = new Composite(this, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite_3.setLayout(new GridLayout(7, false));
		
		Label label = new Label(composite_3, SWT.NONE);
		label.setText("Local RIQ:");
		
		comboLocalRIQ = new Combo(composite_3, SWT.NONE);
		comboLocalRIQ.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = new Label(composite_3, SWT.NONE);
		label_1.setText("Remote RIQ:");
		
		comboRemoteRIQ = new Combo(composite_3, SWT.NONE);
		comboRemoteRIQ.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblCesInterface_1 = new Label(composite_3, SWT.NONE);
		lblCesInterface_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCesInterface_1.setText("CES Interface:");
		
		comboCES = new Combo(composite_3, SWT.NONE);
		comboCES.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCES.add("1:1");
		comboCES.add("1:3");
		comboCES.add("3:1");
		comboCES.add("3:3");
		
		btnAutoCalc = new Button(composite_3, SWT.NONE);
		GridData gd_btnAutoCalc = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 2);
		gd_btnAutoCalc.widthHint = 80;
		gd_btnAutoCalc.heightHint = 40;
		gd_btnAutoCalc.minimumHeight = 100;
		btnAutoCalc.setLayoutData(gd_btnAutoCalc);
		btnAutoCalc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// This'll be fun...
				String[] input = getInput();
				System.out.println(comboLocalRIQ.getText());
				System.out.println(comboRemoteRIQ.getText());
				
				if(comboLocalRIQ.getText() == "" || comboRemoteRIQ.getText() == "" || comboLocalRIQ.getText().compareTo(comboRemoteRIQ.getText()) == 0) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Input Error");
					dialog.setMessage("Local/Remote RIQ input cannot be blank or the same");
					dialog.open();
					return;
				}
				
				RIQ local = builder.getRIQ(comboLocalRIQ.getText());
				RIQ remote = builder.getRIQ(comboRemoteRIQ.getText());
				
				System.out.println(local);
				System.out.println(remote);
				
				int type = btnUHF.getSelection() ? Link.UHF : (btnPPN.getSelection() ? Link.PPN : null);
				int kg = btnLeft.getSelection() ? 0 : (btnRight.getSelection() ? 0 :null);
				Link test = null;
				try {
					if(comboCES.getText()=="") {throw new Exception();}
					test = builder.linkBuilder(
						input[0]=="" ? local.getName()+" -> "+remote.getName() : input[0],
						local,
						remote,
						type,
						comboCES.getText(),//!="" ? comboCES.getText() : (input[2]=="" ? "1:1" : input[2]),
						input[5]=="" ? (type==Link.UHF ? "255.255.255.0" : "255.255.0.0") : input[5],
						kg);
				}
				catch(NoAvailableCESException exceptionCES) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Link Builder Error");
					dialog.setMessage(exceptionCES.getCulperit()+" Has no available CES Interfaces");
					dialog.open();
					return;
				}
				catch(Exception otherException) {
					MessageBox dialog = new MessageBox(getSelf(), SWT.ICON_ERROR | SWT.OK);
					dialog.setText("Link Builder Error");
					dialog.setMessage("Invalid CES Interface");
					dialog.open();
					return;
				}
				
				System.out.println(test);
				if(test == null) {System.out.println("Something went wrong"); return;}
				String[] output = test.toString().split(",");
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
				comboAdminStatus.setText(output[9]);					// 9
				textCESType.setText(output[10]);						// 10
				textPacketInterface.setText(output[11]);				// 11
				textRemoteIP.setText(output[12]);						// 12
				textRemoteUDP.setText(output[13]);						// 13
				textGatewayIP.setText(output[14]);						// 14
				textPBit.setText(output[15]);							// 15
				textDSCP.setText(output[16]);							// 16
			}
		});
		btnAutoCalc.setText("Auto Calc");
		
		Label label_2 = new Label(composite_3, SWT.NONE);
		label_2.setText("Shot Type:");
		
		Composite composite_1 = new Composite(composite_3, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		btnPPN = new Button(composite_1, SWT.RADIO);
		btnPPN.setText("Fiber (PPN)");
		btnPPN.setBounds(0, 27, 110, 21);
		
		btnUHF = new Button(composite_1, SWT.RADIO);
		btnUHF.setText("UHF");
		btnUHF.setSelection(true);
		btnUHF.setBounds(0, 0, 110, 21);
		
		Label label_3 = new Label(composite_3, SWT.NONE);
		label_3.setText("KG Selection: ");
		
		Composite composite = new Composite(composite_3, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		btnLeft = new Button(composite, SWT.RADIO);
		btnLeft.setText("Left");
		btnLeft.setSelection(true);
		btnLeft.setBounds(0, 0, 110, 21);
		
		btnRight = new Button(composite, SWT.RADIO);
		btnRight.setText("Right");
		btnRight.setBounds(0, 27, 110, 21);
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		
		lblCesConfiguration = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblCesConfiguration.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		lblCesConfiguration.setText("CES Configuration");
		
		composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
		GridLayout gl_composite_2 = new GridLayout(5, false);
		gl_composite_2.verticalSpacing = 10;
		gl_composite_2.horizontalSpacing = 10;
		gl_composite_2.marginHeight = 10;
		gl_composite_2.marginWidth = 10;
		composite_2.setLayout(gl_composite_2);
		
		lblName = new Label(composite_2, SWT.NONE);
		lblName.setText("Name:");
		
		textName = new Text(composite_2, SWT.BORDER);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblAdminStatus = new Label(composite_2, SWT.NONE);
		lblAdminStatus.setText("Admin Status:");
		
		comboAdminStatus = new Combo(composite_2, SWT.NONE);
		comboAdminStatus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboAdminStatus.add("Down",0);
		comboAdminStatus.add("Up",1);
		
		btnAdd = new Button(composite_2, SWT.NONE);
		GridData gd_btnAdd = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 2);
		gd_btnAdd.widthHint = 80;
		gd_btnAdd.heightHint = 40;
		btnAdd.setLayoutData(gd_btnAdd);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// String[] Input 	NAME,DESCRIPTION,INTERFACECES,LOCALIP,LOCALUDP,SUBNETMASK,VLAN,IP_TTL,PDV_BUFFER,ADMIN_STATUS,CES_TYPE,
				// 					PACKETINTERFACE,REMOTEIP,REMOTEUDP,GATEWAYIP,P_BIT,DSCP
				String[] input = getInput();
				for (String s : input) {
					System.out.print(s+", ");
				}
				if(!inputValidator(input)) {return;}
				RIQ local = builder.getRIQ(comboLocalRIQ.getText());
				RIQ remote = builder.getRIQ(comboRemoteRIQ.getText());
				int type = btnUHF.getSelection() ? Link.UHF : (btnPPN.getSelection() ? Link.PPN : null);
				int kg = btnLeft.getSelection() ? 0 : (btnRight.getSelection() ? 0 :null);
				
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
				 * @param subnetMask Subnet Mask
				 * @param kg175d Left or Right KG for the Local RIQ (0 for left, 1 for right) (Used for Gateway IP)
				 * @param udpPort UDP port for local AND remote RIQs
				 */
				if(input[4]==input[13]) {
					builder.addLink(new Link(
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
							Integer.parseInt(input[4])
							));
				}
				else {
					builder.addLink(new Link(
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
							Integer.parseInt(input[13])
							));
				}
				getSelf().dispose();
			}
		});
		btnAdd.setText("Add");
		
		lblDescription = new Label(composite_2, SWT.NONE);
		lblDescription.setText("Description:");
		
		textDescription = new Text(composite_2, SWT.BORDER);
		textDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblCesType = new Label(composite_2, SWT.NONE);
		lblCesType.setText("CES Type:");
		
		textCESType = new Text(composite_2, SWT.BORDER);
		textCESType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textCESType.setEditable(false);
		textCESType.setText(Link.CES_TYPE);
		
		lblCesInterface = new Label(composite_2, SWT.NONE);
		lblCesInterface.setText("CES Interface:");
		
		textCESInterface = new Text(composite_2, SWT.BORDER);
		textCESInterface.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblPacketInterface = new Label(composite_2, SWT.NONE);
		lblPacketInterface.setText("Packet Interface:");
		
		textPacketInterface = new Text(composite_2, SWT.BORDER);
		textPacketInterface.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnCancel = new Button(composite_2, SWT.NONE);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getSelf().close();
			}
		});
		GridData gd_btnCancel = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 2);
		gd_btnCancel.heightHint = 40;
		gd_btnCancel.widthHint = 80;
		btnCancel.setLayoutData(gd_btnCancel);
		btnCancel.setText("Cancel");
		
		lblLocalIpAddress = new Label(composite_2, SWT.NONE);
		lblLocalIpAddress.setText("Local IP Address:");
		
		textLocalIP = new Text(composite_2, SWT.BORDER);
		textLocalIP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblRemoteIpAddress = new Label(composite_2, SWT.NONE);
		lblRemoteIpAddress.setText("Remote IP Address:");
		
		textRemoteIP = new Text(composite_2, SWT.BORDER);
		textRemoteIP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblLocalUdpPort = new Label(composite_2, SWT.NONE);
		lblLocalUdpPort.setText("Local UDP Port:");
		
		textLocalUDP = new Text(composite_2, SWT.BORDER);
		textLocalUDP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblRemoteUdpPort = new Label(composite_2, SWT.NONE);
		lblRemoteUdpPort.setText("Remote UDP Port:");
		
		textRemoteUDP = new Text(composite_2, SWT.BORDER);
		textRemoteUDP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnClear = new Button(composite_2, SWT.NONE);
		GridData gd_btnClear = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 2);
		gd_btnClear.widthHint = 80;
		gd_btnClear.heightHint = 40;
		btnClear.setLayoutData(gd_btnClear);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				textName.setText("");							// 0
				textDescription.setText("");					// 1
				textCESInterface.setText("");					// 2
				textLocalIP.setText("");						// 3
				textLocalUDP.setText("");						// 4
				textSubnet.setText("");							// 5
				textVLAN.setText("");							// 6
				//textIPTTL.setText("");						// 7
				//textRemotePDV.setText("");					// 8
				comboAdminStatus.setText("");					// 9
				//textCESType.setText("");						// 10
				textPacketInterface.setText("");				// 11
				textRemoteIP.setText("");						// 12
				textRemoteUDP.setText("");						// 13
				textGatewayIP.setText("");						// 14
				//textPBit.setText("");							// 15
				//textDSCP.setText("");							// 16
			}
		});
		btnClear.setText("Clear");
		
		lblSubnetMask = new Label(composite_2, SWT.NONE);
		lblSubnetMask.setText("Subnet Mask:");
		
		textSubnet = new Text(composite_2, SWT.BORDER);
		textSubnet.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblGatewayIpAddress = new Label(composite_2, SWT.NONE);
		lblGatewayIpAddress.setText("Gateway IP Address:");
		
		textGatewayIP = new Text(composite_2, SWT.BORDER);
		textGatewayIP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblVlan = new Label(composite_2, SWT.NONE);
		lblVlan.setText("VLAN:");
			
		textVLAN = new Text(composite_2, SWT.BORDER);
		textVLAN.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblPbitValue = new Label(composite_2, SWT.NONE);
		lblPbitValue.setText("P-Bit Value:");
		
		textPBit = new Text(composite_2, SWT.BORDER);
		textPBit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textPBit.setEditable(false);
		textPBit.setText(Integer.toString(Link.P_BIT));
		new Label(composite_2, SWT.NONE);
		
		lblIpTtl = new Label(composite_2, SWT.NONE);
		lblIpTtl.setText("IP TTL:");
		
		textIPTTL = new Text(composite_2, SWT.BORDER);
		textIPTTL.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textIPTTL.setEditable(false);
		textIPTTL.setText(Integer.toString(Link.IP_TTL));
		
		lblDscp = new Label(composite_2, SWT.NONE);
		lblDscp.setText("DSCP:");
		
		textDSCP = new Text(composite_2, SWT.BORDER);
		textDSCP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textDSCP.setEditable(false);
		textDSCP.setText(Integer.toString(Link.DSCP));
		new Label(composite_2, SWT.NONE);
		
		lblRemotePdvBuffer = new Label(composite_2, SWT.NONE);
		lblRemotePdvBuffer.setText("Remote PDV Buffer:");
		
		textRemotePDV = new Text(composite_2, SWT.BORDER);
		textRemotePDV.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textRemotePDV.setEditable(false);
		textRemotePDV.setText(Link.PDV_BUFFER);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);		
		
		ArrayList<RIQ> riqs = builder.getRIQs();
		System.out.println(builder);
		for(RIQ riq : riqs) {
			System.out.print(riq.getName());
			comboLocalRIQ.add(riq.getName());
		}
		if(localRIQ != null) {comboLocalRIQ.setText(localRIQ.getName());}
		for(RIQ riq : riqs) {
			System.out.print(riq.getName());
			comboRemoteRIQ.add(riq.getName());
		}
		new Label(composite_2, SWT.NONE);
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
			comboAdminStatus.getText(),								// 9
			textCESType.getText(),									// 10
			Link.validateCESInterface(textPacketInterface.getText()),//11
			textRemoteIP.getText(),									// 12 (Needs IP Validation)
			textRemoteUDP.getText(),								// 13 (Needs Port Validation)
			textGatewayIP.getText(),								// 14 (Needs IP Validation)
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

	private Shell getSelf() {return this;}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
