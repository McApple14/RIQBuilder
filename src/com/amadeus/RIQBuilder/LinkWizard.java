package com.amadeus.RIQBuilder;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;

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

	private Combo comboRemoteRIQ;
	private Combo comboLocalRIQ;
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
	private Label lblLocalRiq;
	private Label lblCesConfiguration;
	private Label lblShotType;
	private Label lblKgSelection;
	private Button btnNewButton;
	private Button btnAutoCalc;
	private Button btnCancel;
	private Button btnClear;
	private Button btnUHF;
	private Button btnPPN;
	private Button btnLeft;
	private Button btnRight;
	private Composite composite_1;
	private Composite composite;

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
		
		comboRemoteRIQ = new Combo(this, SWT.NONE);
		comboRemoteRIQ.setBounds(445, 10, 100, 25);
		
		comboLocalRIQ = new Combo(this, SWT.NONE);
		comboLocalRIQ.setBounds(171, 10, 100, 25);
		
		lblName = new Label(this, SWT.NONE);
		lblName.setBounds(30, 120, 100, 20);
		lblName.setText("Name:");
		
		textName = new Text(this, SWT.BORDER);
		textName.setBounds(171, 120, 100, 20);
		
		textDescription = new Text(this, SWT.BORDER);
		textDescription.setBounds(171, 150, 100, 20);
		
		lblDescription = new Label(this, SWT.NONE);
		lblDescription.setText("Description:");
		lblDescription.setBounds(30, 150, 100, 20);
		
		textCESInterface = new Text(this, SWT.BORDER);
		textCESInterface.setBounds(171, 180, 100, 20);
		
		lblCesInterface = new Label(this, SWT.NONE);
		lblCesInterface.setText("CES Interface:");
		lblCesInterface.setBounds(30, 180, 100, 20);
		
		textLocalIP = new Text(this, SWT.BORDER);
		textLocalIP.setBounds(171, 210, 100, 20);
		
		lblLocalIpAddress = new Label(this, SWT.NONE);
		lblLocalIpAddress.setText("Local IP Address:");
		lblLocalIpAddress.setBounds(30, 210, 122, 20);
		
		textLocalUDP = new Text(this, SWT.BORDER);
		textLocalUDP.setBounds(171, 240, 100, 20);
		
		lblLocalUdpPort = new Label(this, SWT.NONE);
		lblLocalUdpPort.setText("Local UDP Port:");
		lblLocalUdpPort.setBounds(30, 240, 122, 20);
		
		textSubnet = new Text(this, SWT.BORDER);
		textSubnet.setBounds(171, 270, 100, 20);
		
		lblSubnetMask = new Label(this, SWT.NONE);
		lblSubnetMask.setText("Subnet Mask:");
		lblSubnetMask.setBounds(30, 270, 100, 20);
		
		textVLAN = new Text(this, SWT.BORDER);
		textVLAN.setBounds(171, 300, 100, 20);
		
		lblVlan = new Label(this, SWT.NONE);
		lblVlan.setText("VLAN:");
		lblVlan.setBounds(30, 300, 100, 20);
		
		lblIpTtl = new Label(this, SWT.NONE);
		lblIpTtl.setText("IP TTL:");
		lblIpTtl.setBounds(30, 330, 100, 20);
		
		textIPTTL = new Text(this, SWT.BORDER);
		textIPTTL.setEditable(false);
		textIPTTL.setBounds(171, 330, 100, 20);
		textIPTTL.setText(Integer.toString(Link.IP_TTL));
		
		textRemotePDV = new Text(this, SWT.BORDER);
		textRemotePDV.setEditable(false);
		textRemotePDV.setBounds(171, 360, 100, 20);
		textRemotePDV.setText(Link.PDV_BUFFER);
		
		lblRemotePdvBuffer = new Label(this, SWT.NONE);
		lblRemotePdvBuffer.setText("Remote PDV Buffer:");
		lblRemotePdvBuffer.setBounds(30, 360, 135, 20);
		
		lblAdminStatus = new Label(this, SWT.NONE);
		lblAdminStatus.setText("Admin Status:");
		lblAdminStatus.setBounds(300, 120, 100, 20);
		
		textCESType = new Text(this, SWT.BORDER);
		textCESType.setEditable(false);
		textCESType.setBounds(445, 150, 100, 20);
		textCESType.setText(Link.CES_TYPE);
		
		lblCesType = new Label(this, SWT.NONE);
		lblCesType.setText("CES Type:");
		lblCesType.setBounds(300, 150, 100, 20);
		
		textPacketInterface = new Text(this, SWT.BORDER);
		textPacketInterface.setBounds(445, 180, 100, 20);
		
		lblPacketInterface = new Label(this, SWT.NONE);
		lblPacketInterface.setText("Packet Interface:");
		lblPacketInterface.setBounds(300, 180, 129, 20);
		
		textRemoteIP = new Text(this, SWT.BORDER);
		textRemoteIP.setBounds(445, 210, 100, 20);
		
		lblRemoteIpAddress = new Label(this, SWT.NONE);
		lblRemoteIpAddress.setText("Remote IP Address:");
		lblRemoteIpAddress.setBounds(300, 210, 129, 20);
		
		textRemoteUDP = new Text(this, SWT.BORDER);
		textRemoteUDP.setBounds(445, 240, 100, 20);
		
		lblRemoteUdpPort = new Label(this, SWT.NONE);
		lblRemoteUdpPort.setText("Remote UDP Port:");
		lblRemoteUdpPort.setBounds(300, 240, 129, 20);
		
		textGatewayIP = new Text(this, SWT.BORDER);
		textGatewayIP.setBounds(445, 270, 100, 20);
		
		lblGatewayIpAddress = new Label(this, SWT.NONE);
		lblGatewayIpAddress.setText("Gateway IP Address:");
		lblGatewayIpAddress.setBounds(300, 270, 139, 20);
		
		textPBit = new Text(this, SWT.BORDER);
		textPBit.setEditable(false);
		textPBit.setBounds(445, 300, 100, 20);
		textPBit.setText(Integer.toString(Link.P_BIT));
	
		lblPbitValue = new Label(this, SWT.NONE);
		lblPbitValue.setText("P-Bit Value:");
		lblPbitValue.setBounds(300, 300, 100, 20);
		
		lblDscp = new Label(this, SWT.NONE);
		lblDscp.setText("DSCP:");
		lblDscp.setBounds(300, 330, 100, 20);
		
		textDSCP = new Text(this, SWT.BORDER);
		textDSCP.setEditable(false);
		textDSCP.setBounds(445, 330, 100, 20);
		textDSCP.setText(Integer.toString(Link.DSCP));
		
		comboAdminStatus = new Combo(this, SWT.NONE);
		comboAdminStatus.setBounds(445, 120, 100, 25);
		comboAdminStatus.add("Down",0);
		comboAdminStatus.add("Up",1);
		
		lblLocalRiq = new Label(this, SWT.NONE);
		lblLocalRiq.setBounds(30, 10, 69, 21);
		lblLocalRiq.setText("Local RIQ:");
		ArrayList<RIQ> riqs = builder.getRIQs();
		for(RIQ riq : riqs) {
			comboLocalRIQ.add(riq.getName());
		}
		if(localRIQ != null) {comboLocalRIQ.setText(localRIQ.getName());}
		
		Label lblRemoteRiq = new Label(this, SWT.NONE);
		lblRemoteRiq.setText("Remote RIQ:");
		lblRemoteRiq.setBounds(300, 10, 100, 21);
		for(RIQ riq : riqs) {
			comboRemoteRIQ.add(riq.getName());
		}
		
		lblCesConfiguration = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblCesConfiguration.setText("CES Configuration");
		lblCesConfiguration.setBounds(10, 105, 664, 9);
		
		lblShotType = new Label(this, SWT.NONE);
		lblShotType.setText("Shot Type:");
		lblShotType.setBounds(30, 55, 69, 21);
		
		lblKgSelection = new Label(this, SWT.NONE);
		lblKgSelection.setText("KG Selection: ");
		lblKgSelection.setBounds(300, 55, 100, 21);
		
		btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// String[] Input 	NAME,DESCRIPTION,INTERFACECES,LOCALIP,LOCALUDP,SUBNETMASK,VLAN,IP_TTL,PDV_BUFFER,ADMIN_STATUS,CES_TYPE,
				// 					PACKETINTERFACE,REMOTEIP,REMOTEUDP,GATEWAYIP,P_BIT,DSCP
				String[] input = new String[] {
					textName.getText(),
					textDescription.getText(),
					Link.validateCESInterface(textCESInterface.getText()),
					Link.ipValidation(textLocalIP.getText()),
					textLocalUDP.getText(),
					Link.subnetMaskValidation(textSubnet.getText()),
					textVLAN.getText(),
					textIPTTL.getText(),
					textRemotePDV.getText(),
					comboAdminStatus.getText(),
					textCESType.getText(),
					Link.validateCESInterface(textPacketInterface.getText()),
					Link.ipValidation(textRemoteIP.getText()),
					textRemoteUDP.getText(),
					Link.ipValidation(textGatewayIP.getText()),
					textPBit.getText(),
					textDSCP.getText()
				};
				for (String s : input) {
					System.out.print(s+", ");
				}
				
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
		btnNewButton.setBounds(574, 120, 100, 50);
		btnNewButton.setText("Add");
		
		btnAutoCalc = new Button(this, SWT.NONE);
		btnAutoCalc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// This'll be fun...
				String[] input = new String[] {
						textName.getText(),										// 0
						textDescription.getText(),								// 1
						Link.validateCESInterface(textCESInterface.getText()),	// 2
						Link.ipValidation(textLocalIP.getText()),				// 3
						textLocalUDP.getText(),									// 4
						Link.subnetMaskValidation(textSubnet.getText()),		// 5
						textVLAN.getText(),										// 6
						textIPTTL.getText(),									// 7
						textRemotePDV.getText(),								// 8
						comboAdminStatus.getText(),								// 9
						textCESType.getText(),									// 10
						Link.validateCESInterface(textPacketInterface.getText()),//11
						Link.ipValidation(textRemoteIP.getText()),				// 12
						textRemoteUDP.getText(),								// 13
						Link.ipValidation(textGatewayIP.getText()),				// 14
						textPBit.getText(),										// 15
						textDSCP.getText()										// 16
					};
				System.out.println(comboLocalRIQ.getText());
				System.out.println(comboRemoteRIQ.getText());
				
				RIQ local = builder.getRIQ(comboLocalRIQ.getText());
				RIQ remote = builder.getRIQ(comboRemoteRIQ.getText());
				
				System.out.println(local);
				System.out.println(remote);
				
				int type = btnUHF.getSelection() ? Link.UHF : (btnPPN.getSelection() ? Link.PPN : null);
				int kg = btnLeft.getSelection() ? 0 : (btnRight.getSelection() ? 0 :null);
				Link test = builder.linkBuilder(
						input[0]=="" ? local.getName()+" -> "+remote.getName() : input[0],
						local,
						remote,
						type,
						input[2]==null ? "1:1" : input[2],
						input[5]==null ? (type==Link.UHF ? "255.255.255.0" : "255.255.0.0") : input[5],
						kg);
				System.out.println(test);
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
		btnAutoCalc.setBounds(574, 10, 100, 50);
		
		btnCancel = new Button(this, SWT.NONE);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				getSelf().dispose();
			}
		});
		btnCancel.setText("Cancel");
		btnCancel.setBounds(574, 240, 100, 50);
		
		btnClear = new Button(this, SWT.NONE);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				textName.setText("");							// 0
				textDescription.setText("");						// 1
				textCESInterface.setText("");					// 2
				textLocalIP.setText("");							// 3
				textLocalUDP.setText("");						// 4
				textSubnet.setText("");							// 5
				textVLAN.setText("");							// 6
				//textIPTTL.setText("");							// 7
				//textRemotePDV.setText("");						// 8
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
		btnClear.setBounds(574, 180, 100, 50);
		
		composite_1 = new Composite(this, SWT.NONE);
		composite_1.setBounds(445, 41, 110, 48);
		
		btnLeft = new Button(composite_1, SWT.RADIO);
		btnLeft.setBounds(0, 0, 110, 21);
		btnLeft.setText("Left");
		btnLeft.setSelection(true);
		
		btnRight = new Button(composite_1, SWT.RADIO);
		btnRight.setBounds(0, 27, 110, 21);
		btnRight.setText("Right");
		
		composite = new Composite(this, SWT.NONE);
		composite.setBounds(171, 41, 110, 48);
		
		btnPPN = new Button(composite, SWT.RADIO);
		btnPPN.setBounds(0, 27, 110, 21);
		btnPPN.setText("Fiber (PPN)");
		
		btnUHF = new Button(composite, SWT.RADIO);
		btnUHF.setBounds(0, 0, 110, 21);
		btnUHF.setText("UHF");
		btnUHF.setSelection(true);
	}

	private Shell getSelf() {return this;}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
