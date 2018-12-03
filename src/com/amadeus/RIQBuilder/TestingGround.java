package com.amadeus.RIQBuilder;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;

public class TestingGround {
	
	private static RIQBuilder builder;
	
	protected Shell shlAddLink;
	private Text textName;
	private Text textDescription;
	private Text textCESInterface;
	private Text textLocalIP;
	private Text textLocalUDP;
	private Text textSubnet;
	private Text textVLAN;
	private Text textIPTTL;
	private Text textRemotePDV;
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
	private Label lblShotType;
	private Button btnPPN;
	private Label lblKgSelection;
	private Button btnLeft;
	private Button btnRight;
	private Group group;
	private Group group_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		builder = new RIQBuilder();
		try {
			TestingGround window = new TestingGround();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlAddLink.open();
		shlAddLink.layout();
		while (!shlAddLink.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAddLink = new Shell();
		shlAddLink.setSize(700, 450);
		shlAddLink.setText("Add Link");
		
		Combo comboRemoteRIQ = new Combo(shlAddLink, SWT.NONE);
		comboRemoteRIQ.setBounds(445, 10, 100, 25);
		
		Combo comboLocalRIQ = new Combo(shlAddLink, SWT.NONE);
		comboLocalRIQ.setBounds(171, 10, 100, 25);
		
		Label lblName = new Label(shlAddLink, SWT.NONE);
		lblName.setBounds(30, 120, 100, 20);
		lblName.setText("Name:");
		
		Text textName = new Text(shlAddLink, SWT.BORDER);
		textName.setBounds(171, 120, 100, 20);
		
		Text textDescription = new Text(shlAddLink, SWT.BORDER);
		textDescription.setBounds(171, 150, 100, 20);
		
		Label lblDescription = new Label(shlAddLink, SWT.NONE);
		lblDescription.setText("Description:");
		lblDescription.setBounds(30, 150, 100, 20);
		
		Text textCESInterface = new Text(shlAddLink, SWT.BORDER);
		textCESInterface.setBounds(171, 180, 100, 20);
		
		Label lblCesInterface = new Label(shlAddLink, SWT.NONE);
		lblCesInterface.setText("CES Interface:");
		lblCesInterface.setBounds(30, 180, 100, 20);
		
		Text textLocalIP = new Text(shlAddLink, SWT.BORDER);
		textLocalIP.setBounds(171, 210, 100, 20);
		
		Label lblLocalIpAddress = new Label(shlAddLink, SWT.NONE);
		lblLocalIpAddress.setText("Local IP Address:");
		lblLocalIpAddress.setBounds(30, 210, 122, 20);
		
		Text textLocalUDP = new Text(shlAddLink, SWT.BORDER);
		textLocalUDP.setBounds(171, 240, 100, 20);
		
		Label lblLocalUdpPort = new Label(shlAddLink, SWT.NONE);
		lblLocalUdpPort.setText("Local UDP Port:");
		lblLocalUdpPort.setBounds(30, 240, 122, 20);
		
		Text textSubnet = new Text(shlAddLink, SWT.BORDER);
		textSubnet.setBounds(171, 270, 100, 20);
		
		Label lblSubnetMask = new Label(shlAddLink, SWT.NONE);
		lblSubnetMask.setText("Subnet Mask:");
		lblSubnetMask.setBounds(30, 270, 100, 20);
		
		Text textVLAN = new Text(shlAddLink, SWT.BORDER);
		textVLAN.setBounds(171, 300, 100, 20);
		
		Label lblVlan = new Label(shlAddLink, SWT.NONE);
		lblVlan.setText("VLAN:");
		lblVlan.setBounds(30, 300, 100, 20);
		
		Label lblIpTtl = new Label(shlAddLink, SWT.NONE);
		lblIpTtl.setText("IP TTL:");
		lblIpTtl.setBounds(30, 330, 100, 20);
		
		Text textIPTTL = new Text(shlAddLink, SWT.BORDER);
		textIPTTL.setEditable(false);
		textIPTTL.setBounds(171, 330, 100, 20);
		textIPTTL.setText(Integer.toString(Link.IP_TTL));
		
		Text textRemotePDV = new Text(shlAddLink, SWT.BORDER);
		textRemotePDV.setEditable(false);
		textRemotePDV.setBounds(171, 360, 100, 20);
		textRemotePDV.setText(Link.PDV_BUFFER);
		
		Label lblRemotePdvBuffer = new Label(shlAddLink, SWT.NONE);
		lblRemotePdvBuffer.setText("Remote PDV Buffer:");
		lblRemotePdvBuffer.setBounds(30, 360, 135, 20);
		
		Label lblAdminStatus = new Label(shlAddLink, SWT.NONE);
		lblAdminStatus.setText("Admin Status:");
		lblAdminStatus.setBounds(300, 120, 100, 20);
		
		Text textCESType = new Text(shlAddLink, SWT.BORDER);
		textCESType.setEditable(false);
		textCESType.setBounds(445, 150, 100, 20);
		textCESType.setText(Link.CES_TYPE);
		
		Label lblCesType = new Label(shlAddLink, SWT.NONE);
		lblCesType.setText("CES Type:");
		lblCesType.setBounds(300, 150, 100, 20);
		
		Text textPacketInterface = new Text(shlAddLink, SWT.BORDER);
		textPacketInterface.setBounds(445, 180, 100, 20);
		
		Label lblPacketInterface = new Label(shlAddLink, SWT.NONE);
		lblPacketInterface.setText("Packet Interface:");
		lblPacketInterface.setBounds(300, 180, 129, 20);
		
		Text textRemoteIP = new Text(shlAddLink, SWT.BORDER);
		textRemoteIP.setBounds(445, 210, 100, 20);
		
		Label lblRemoteIpAddress = new Label(shlAddLink, SWT.NONE);
		lblRemoteIpAddress.setText("Remote IP Address:");
		lblRemoteIpAddress.setBounds(300, 210, 129, 20);
		
		Text textRemoteUDP = new Text(shlAddLink, SWT.BORDER);
		textRemoteUDP.setBounds(445, 240, 100, 20);
		
		Label lblRemoteUdpPort = new Label(shlAddLink, SWT.NONE);
		lblRemoteUdpPort.setText("Remote UDP Port:");
		lblRemoteUdpPort.setBounds(300, 240, 129, 20);
		
		Text textGatewayIP = new Text(shlAddLink, SWT.BORDER);
		textGatewayIP.setBounds(445, 270, 100, 20);
		
		Label lblGatewayIpAddress = new Label(shlAddLink, SWT.NONE);
		lblGatewayIpAddress.setText("Gateway IP Address:");
		lblGatewayIpAddress.setBounds(300, 270, 139, 20);
		
		Text textPBit = new Text(shlAddLink, SWT.BORDER);
		textPBit.setEditable(false);
		textPBit.setBounds(445, 300, 100, 20);
		textPBit.setText(Integer.toString(Link.P_BIT));
	
		Label lblPbitValue = new Label(shlAddLink, SWT.NONE);
		lblPbitValue.setText("P-Bit Value:");
		lblPbitValue.setBounds(300, 300, 100, 20);
		
		Label lblDscp = new Label(shlAddLink, SWT.NONE);
		lblDscp.setText("DSCP:");
		lblDscp.setBounds(300, 330, 100, 20);
		
		Text textDSCP = new Text(shlAddLink, SWT.BORDER);
		textDSCP.setEditable(false);
		textDSCP.setBounds(445, 330, 100, 20);
		textDSCP.setText(Integer.toString(Link.DSCP));
		
		Combo comboAdminStatus = new Combo(shlAddLink, SWT.NONE);
		comboAdminStatus.setBounds(445, 120, 100, 25);
		comboAdminStatus.add("Down",0);
		comboAdminStatus.add("Up",1);
		
		Label lblLocalRiq = new Label(shlAddLink, SWT.NONE);
		lblLocalRiq.setBounds(30, 10, 69, 21);
		lblLocalRiq.setText("Local RIQ:");
		ArrayList<RIQ> riqs = builder.getRIQs();
		for(RIQ riq : riqs) {
			comboLocalRIQ.add(riq.getName());
		}
		
		Label lblRemoteRiq = new Label(shlAddLink, SWT.NONE);
		lblRemoteRiq.setText("Remote RIQ:");
		lblRemoteRiq.setBounds(300, 10, 100, 21);
		for(RIQ riq : riqs) {
			comboRemoteRIQ.add(riq.getName());
		}
		
		Label lblCesConfiguration = new Label(shlAddLink, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblCesConfiguration.setText("CES Configuration");
		lblCesConfiguration.setBounds(10, 105, 664, 9);
		
		Label lblShotType = new Label(shlAddLink, SWT.NONE);
		lblShotType.setText("Shot Type:");
		lblShotType.setBounds(30, 55, 69, 21);
		
		Group group = new Group(shlAddLink, SWT.NONE);
		group.setBounds(169, 22, 113, 70);
		
		Button btnUHF = new Button(group, SWT.RADIO);
		btnUHF.setBounds(2, 21, 110, 21);
		btnUHF.setText("UHF");
		btnUHF.setSelection(true);
		
		Button btnPPN = new Button(group, SWT.RADIO);
		btnPPN.setBounds(2, 48, 110, 21);
		btnPPN.setText("Fiber (PPN)");
		
		Label lblKgSelection = new Label(shlAddLink, SWT.NONE);
		lblKgSelection.setText("KG Selection: ");
		lblKgSelection.setBounds(300, 55, 100, 21);
		
		Group group_1 = new Group(shlAddLink, SWT.NONE);
		group_1.setBounds(439, 22, 113, 70);
		
		Button btnLeft = new Button(group_1, SWT.RADIO);
		btnLeft.setBounds(2, 21, 110, 21);
		btnLeft.setText("Left");
		btnLeft.setSelection(true);
		
		Button btnRight = new Button(group_1, SWT.RADIO);
		btnRight.setBounds(2, 48, 110, 21);
		btnRight.setText("Right");
		
		Button btnNewButton = new Button(shlAddLink, SWT.NONE);
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
				shlAddLink.dispose();
			}
		});
		btnNewButton.setBounds(574, 120, 100, 50);
		btnNewButton.setText("Add");
		
		Button btnAutoCalc = new Button(shlAddLink, SWT.NONE);
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
		
		Button btnCancel = new Button(shlAddLink, SWT.NONE);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shlAddLink.dispose();
			}
		});
		btnCancel.setText("Cancel");
		btnCancel.setBounds(574, 240, 100, 50);
		
		Button btnClear = new Button(shlAddLink, SWT.NONE);
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
	}
}
