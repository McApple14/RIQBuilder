package com.amadeus.RIQBuilder;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class GUI {

	private static RIQBuilder builder;
	private Display display;
	
	protected Shell shlRiqbuilder;
	protected Shell childShellRIQWizard;
	protected Shell childShellLinkWizard;
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
		childShellRIQWizard.setText("Add RIQ");
		
		Label lblNewLabel = new Label(childShellRIQWizard, SWT.NONE);
		lblNewLabel.setBounds(25, 30, 55, 20);
		lblNewLabel.setText("Name:");
		
		Label lblLeftKgd = new Label(childShellRIQWizard, SWT.NONE);
		lblLeftKgd.setText("Left KG175D");
		lblLeftKgd.setBounds(25, 84, 101, 20);
		
		Label lblRightKgd = new Label(childShellRIQWizard, SWT.NONE);
		lblRightKgd.setText("Right KG175D");
		lblRightKgd.setBounds(289, 84, 101, 20);
		
		Label label = new Label(childShellRIQWizard, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(259, 84, 2, 265);
		
		Label lblPTIPLeft = new Label(childShellRIQWizard, SWT.NONE);
		lblPTIPLeft.setBounds(25, 130, 80, 20);
		lblPTIPLeft.setText("PT IP: ");
		
		Text textName = new Text(childShellRIQWizard, SWT.BORDER);
		textName.setBounds(86, 30, 101, 21);
		
		Text leftPTIP = new Text(childShellRIQWizard, SWT.BORDER);
		leftPTIP.setBounds(130, 130, 101, 21);
		
		Text leftPTGW = new Text(childShellRIQWizard, SWT.BORDER);
		leftPTGW.setBounds(130, 180, 101, 21);
		
		Label lblPTGWLeft = new Label(childShellRIQWizard, SWT.NONE);
		lblPTGWLeft.setText("PT Gateway: ");
		lblPTGWLeft.setBounds(25, 180, 80, 20);
		
		Text leftCTIP = new Text(childShellRIQWizard, SWT.BORDER);
		leftCTIP.setBounds(130, 230, 101, 21);
		
		Label lblCTIPLeft = new Label(childShellRIQWizard, SWT.NONE);
		lblCTIPLeft.setText("CT IP: ");
		lblCTIPLeft.setBounds(25, 230, 80, 20);
		
		Text leftCTGW = new Text(childShellRIQWizard, SWT.BORDER);
		leftCTGW.setBounds(130, 280, 101, 21);
		
		Label lblCTGWLeft = new Label(childShellRIQWizard, SWT.NONE);
		lblCTGWLeft.setText("CT Gateway: ");
		lblCTGWLeft.setBounds(25, 280, 80, 20);
		
		Label label_1 = new Label(childShellRIQWizard, SWT.NONE);
		label_1.setText("PT IP: ");
		label_1.setBounds(289, 130, 80, 20);
		
		Text rightPTIP = new Text(childShellRIQWizard, SWT.BORDER);
		rightPTIP.setBounds(394, 130, 101, 21);
		
		Label label_2 = new Label(childShellRIQWizard, SWT.NONE);
		label_2.setText("PT Gateway: ");
		label_2.setBounds(289, 180, 80, 20);
		
		Text rightPTGW = new Text(childShellRIQWizard, SWT.BORDER);
		rightPTGW.setBounds(394, 180, 101, 21);
		
		Text rightCTIP = new Text(childShellRIQWizard, SWT.BORDER);
		rightCTIP.setBounds(394, 230, 101, 21);
		
		Label label_3 = new Label(childShellRIQWizard, SWT.NONE);
		label_3.setText("CT IP: ");
		label_3.setBounds(289, 230, 80, 20);
		
		Label label_4 = new Label(childShellRIQWizard, SWT.NONE);
		label_4.setText("CT Gateway: ");
		label_4.setBounds(289, 280, 80, 20);
		
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
				builder.reInitialization(true);				
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
					temp.reInitialization(false);
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
		btnAutoCalc.setBounds(544, 80, 130, 	37);
	}
	
	protected void childShellLinkWizard() {
		childShellLinkWizard = new Shell();
		childShellLinkWizard.setSize(700, 450);
		childShellLinkWizard.setText("Add Link");
		
		Combo comboRemoteRIQ = new Combo(childShellLinkWizard, SWT.NONE);
		comboRemoteRIQ.setBounds(445, 10, 100, 25);
		
		Combo comboLocalRIQ = new Combo(childShellLinkWizard, SWT.NONE);
		comboLocalRIQ.setBounds(171, 10, 100, 25);
		
		Label lblName = new Label(childShellLinkWizard, SWT.NONE);
		lblName.setBounds(30, 120, 100, 20);
		lblName.setText("Name:");
		
		Text textName = new Text(childShellLinkWizard, SWT.BORDER);
		textName.setBounds(171, 120, 100, 20);
		
		Text textDescription = new Text(childShellLinkWizard, SWT.BORDER);
		textDescription.setBounds(171, 150, 100, 20);
		
		Label lblDescription = new Label(childShellLinkWizard, SWT.NONE);
		lblDescription.setText("Description:");
		lblDescription.setBounds(30, 150, 100, 20);
		
		Text textCESInterface = new Text(childShellLinkWizard, SWT.BORDER);
		textCESInterface.setBounds(171, 180, 100, 20);
		
		Label lblCesInterface = new Label(childShellLinkWizard, SWT.NONE);
		lblCesInterface.setText("CES Interface:");
		lblCesInterface.setBounds(30, 180, 100, 20);
		
		Text textLocalIP = new Text(childShellLinkWizard, SWT.BORDER);
		textLocalIP.setBounds(171, 210, 100, 20);
		
		Label lblLocalIpAddress = new Label(childShellLinkWizard, SWT.NONE);
		lblLocalIpAddress.setText("Local IP Address:");
		lblLocalIpAddress.setBounds(30, 210, 122, 20);
		
		Text textLocalUDP = new Text(childShellLinkWizard, SWT.BORDER);
		textLocalUDP.setBounds(171, 240, 100, 20);
		
		Label lblLocalUdpPort = new Label(childShellLinkWizard, SWT.NONE);
		lblLocalUdpPort.setText("Local UDP Port:");
		lblLocalUdpPort.setBounds(30, 240, 122, 20);
		
		Text textSubnet = new Text(childShellLinkWizard, SWT.BORDER);
		textSubnet.setBounds(171, 270, 100, 20);
		
		Label lblSubnetMask = new Label(childShellLinkWizard, SWT.NONE);
		lblSubnetMask.setText("Subnet Mask:");
		lblSubnetMask.setBounds(30, 270, 100, 20);
		
		Text textVLAN = new Text(childShellLinkWizard, SWT.BORDER);
		textVLAN.setBounds(171, 300, 100, 20);
		
		Label lblVlan = new Label(childShellLinkWizard, SWT.NONE);
		lblVlan.setText("VLAN:");
		lblVlan.setBounds(30, 300, 100, 20);
		
		Label lblIpTtl = new Label(childShellLinkWizard, SWT.NONE);
		lblIpTtl.setText("IP TTL:");
		lblIpTtl.setBounds(30, 330, 100, 20);
		
		Text textIPTTL = new Text(childShellLinkWizard, SWT.BORDER);
		textIPTTL.setEditable(false);
		textIPTTL.setBounds(171, 330, 100, 20);
		textIPTTL.setText(Integer.toString(Link.IP_TTL));
		
		Text textRemotePDV = new Text(childShellLinkWizard, SWT.BORDER);
		textRemotePDV.setEditable(false);
		textRemotePDV.setBounds(171, 360, 100, 20);
		textRemotePDV.setText(Link.PDV_BUFFER);
		
		Label lblRemotePdvBuffer = new Label(childShellLinkWizard, SWT.NONE);
		lblRemotePdvBuffer.setText("Remote PDV Buffer:");
		lblRemotePdvBuffer.setBounds(30, 360, 135, 20);
		
		Label lblAdminStatus = new Label(childShellLinkWizard, SWT.NONE);
		lblAdminStatus.setText("Admin Status:");
		lblAdminStatus.setBounds(300, 120, 100, 20);
		
		Text textCESType = new Text(childShellLinkWizard, SWT.BORDER);
		textCESType.setEditable(false);
		textCESType.setBounds(445, 150, 100, 20);
		textCESType.setText(Link.CES_TYPE);
		
		Label lblCesType = new Label(childShellLinkWizard, SWT.NONE);
		lblCesType.setText("CES Type:");
		lblCesType.setBounds(300, 150, 100, 20);
		
		Text textPacketInterface = new Text(childShellLinkWizard, SWT.BORDER);
		textPacketInterface.setBounds(445, 180, 100, 20);
		
		Label lblPacketInterface = new Label(childShellLinkWizard, SWT.NONE);
		lblPacketInterface.setText("Packet Interface:");
		lblPacketInterface.setBounds(300, 180, 129, 20);
		
		Text textRemoteIP = new Text(childShellLinkWizard, SWT.BORDER);
		textRemoteIP.setBounds(445, 210, 100, 20);
		
		Label lblRemoteIpAddress = new Label(childShellLinkWizard, SWT.NONE);
		lblRemoteIpAddress.setText("Remote IP Address:");
		lblRemoteIpAddress.setBounds(300, 210, 129, 20);
		
		Text textRemoteUDP = new Text(childShellLinkWizard, SWT.BORDER);
		textRemoteUDP.setBounds(445, 240, 100, 20);
		
		Label lblRemoteUdpPort = new Label(childShellLinkWizard, SWT.NONE);
		lblRemoteUdpPort.setText("Remote UDP Port:");
		lblRemoteUdpPort.setBounds(300, 240, 129, 20);
		
		Text textGatewayIP = new Text(childShellLinkWizard, SWT.BORDER);
		textGatewayIP.setBounds(445, 270, 100, 20);
		
		Label lblGatewayIpAddress = new Label(childShellLinkWizard, SWT.NONE);
		lblGatewayIpAddress.setText("Gateway IP Address:");
		lblGatewayIpAddress.setBounds(300, 270, 139, 20);
		
		Text textPBit = new Text(childShellLinkWizard, SWT.BORDER);
		textPBit.setEditable(false);
		textPBit.setBounds(445, 300, 100, 20);
		textPBit.setText(Integer.toString(Link.P_BIT));
	
		Label lblPbitValue = new Label(childShellLinkWizard, SWT.NONE);
		lblPbitValue.setText("P-Bit Value:");
		lblPbitValue.setBounds(300, 300, 100, 20);
		
		Label lblDscp = new Label(childShellLinkWizard, SWT.NONE);
		lblDscp.setText("DSCP:");
		lblDscp.setBounds(300, 330, 100, 20);
		
		Text textDSCP = new Text(childShellLinkWizard, SWT.BORDER);
		textDSCP.setEditable(false);
		textDSCP.setBounds(445, 330, 100, 20);
		textDSCP.setText(Integer.toString(Link.DSCP));
		
		Combo comboAdminStatus = new Combo(childShellLinkWizard, SWT.NONE);
		comboAdminStatus.setBounds(445, 120, 100, 25);
		comboAdminStatus.add("Down",0);
		comboAdminStatus.add("Up",1);
		
		Label lblLocalRiq = new Label(childShellLinkWizard, SWT.NONE);
		lblLocalRiq.setBounds(30, 10, 69, 21);
		lblLocalRiq.setText("Local RIQ:");
		ArrayList<RIQ> riqs = builder.getRIQs();
		for(RIQ riq : riqs) {
			comboLocalRIQ.add(riq.getName());
		}
		
		Label lblRemoteRiq = new Label(childShellLinkWizard, SWT.NONE);
		lblRemoteRiq.setText("Remote RIQ:");
		lblRemoteRiq.setBounds(300, 10, 100, 21);
		for(RIQ riq : riqs) {
			comboRemoteRIQ.add(riq.getName());
		}
		
		Label lblCesConfiguration = new Label(childShellLinkWizard, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblCesConfiguration.setText("CES Configuration");
		lblCesConfiguration.setBounds(10, 105, 664, 9);
		
		Label lblShotType = new Label(childShellLinkWizard, SWT.NONE);
		lblShotType.setText("Shot Type:");
		lblShotType.setBounds(30, 55, 69, 21);
		
		Group group = new Group(childShellLinkWizard, SWT.NONE);
		group.setBounds(169, 22, 113, 70);
		
		Button btnUHF = new Button(group, SWT.RADIO);
		btnUHF.setBounds(2, 21, 110, 21);
		btnUHF.setText("UHF");
		btnUHF.setSelection(true);
		
		Button btnPPN = new Button(group, SWT.RADIO);
		btnPPN.setBounds(2, 48, 110, 21);
		btnPPN.setText("Fiber (PPN)");
		
		Label lblKgSelection = new Label(childShellLinkWizard, SWT.NONE);
		lblKgSelection.setText("KG Selection: ");
		lblKgSelection.setBounds(300, 55, 100, 21);
		
		Group group_1 = new Group(childShellLinkWizard, SWT.NONE);
		group_1.setBounds(439, 22, 113, 70);
		
		Button btnLeft = new Button(group_1, SWT.RADIO);
		btnLeft.setBounds(2, 21, 110, 21);
		btnLeft.setText("Left");
		btnLeft.setSelection(true);
		
		Button btnRight = new Button(group_1, SWT.RADIO);
		btnRight.setBounds(2, 48, 110, 21);
		btnRight.setText("Right");
		
		Button btnNewButton = new Button(childShellLinkWizard, SWT.NONE);
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
				childShellLinkWizard.dispose();
			}
		});
		btnNewButton.setBounds(574, 120, 100, 50);
		btnNewButton.setText("Add");
		
		Button btnAutoCalc = new Button(childShellLinkWizard, SWT.NONE);
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
		
		Button btnCancel = new Button(childShellLinkWizard, SWT.NONE);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				childShellLinkWizard.dispose();
			}
		});
		btnCancel.setText("Cancel");
		btnCancel.setBounds(574, 240, 100, 50);
		
		Button btnClear = new Button(childShellLinkWizard, SWT.NONE);
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
	
	protected void childShellLinkWizard(RIQ localRIQ) {
		childShellLinkWizard = new Shell();
		childShellLinkWizard.setSize(700, 450);
		childShellLinkWizard.setText("Add Link");
		
		Combo comboRemoteRIQ = new Combo(childShellLinkWizard, SWT.NONE);
		comboRemoteRIQ.setBounds(445, 10, 100, 25);
		
		Combo comboLocalRIQ = new Combo(childShellLinkWizard, SWT.NONE);
		comboLocalRIQ.setBounds(171, 10, 100, 25);
		
		Label lblName = new Label(childShellLinkWizard, SWT.NONE);
		lblName.setBounds(30, 120, 100, 20);
		lblName.setText("Name:");
		
		Text textName = new Text(childShellLinkWizard, SWT.BORDER);
		textName.setBounds(171, 120, 100, 20);
		
		Text textDescription = new Text(childShellLinkWizard, SWT.BORDER);
		textDescription.setBounds(171, 150, 100, 20);
		
		Label lblDescription = new Label(childShellLinkWizard, SWT.NONE);
		lblDescription.setText("Description:");
		lblDescription.setBounds(30, 150, 100, 20);
		
		Text textCESInterface = new Text(childShellLinkWizard, SWT.BORDER);
		textCESInterface.setBounds(171, 180, 100, 20);
		
		Label lblCesInterface = new Label(childShellLinkWizard, SWT.NONE);
		lblCesInterface.setText("CES Interface:");
		lblCesInterface.setBounds(30, 180, 100, 20);
		
		Text textLocalIP = new Text(childShellLinkWizard, SWT.BORDER);
		textLocalIP.setBounds(171, 210, 100, 20);
		
		Label lblLocalIpAddress = new Label(childShellLinkWizard, SWT.NONE);
		lblLocalIpAddress.setText("Local IP Address:");
		lblLocalIpAddress.setBounds(30, 210, 122, 20);
		
		Text textLocalUDP = new Text(childShellLinkWizard, SWT.BORDER);
		textLocalUDP.setBounds(171, 240, 100, 20);
		
		Label lblLocalUdpPort = new Label(childShellLinkWizard, SWT.NONE);
		lblLocalUdpPort.setText("Local UDP Port:");
		lblLocalUdpPort.setBounds(30, 240, 122, 20);
		
		Text textSubnet = new Text(childShellLinkWizard, SWT.BORDER);
		textSubnet.setBounds(171, 270, 100, 20);
		
		Label lblSubnetMask = new Label(childShellLinkWizard, SWT.NONE);
		lblSubnetMask.setText("Subnet Mask:");
		lblSubnetMask.setBounds(30, 270, 100, 20);
		
		Text textVLAN = new Text(childShellLinkWizard, SWT.BORDER);
		textVLAN.setBounds(171, 300, 100, 20);
		
		Label lblVlan = new Label(childShellLinkWizard, SWT.NONE);
		lblVlan.setText("VLAN:");
		lblVlan.setBounds(30, 300, 100, 20);
		
		Label lblIpTtl = new Label(childShellLinkWizard, SWT.NONE);
		lblIpTtl.setText("IP TTL:");
		lblIpTtl.setBounds(30, 330, 100, 20);
		
		Text textIPTTL = new Text(childShellLinkWizard, SWT.BORDER);
		textIPTTL.setEditable(false);
		textIPTTL.setBounds(171, 330, 100, 20);
		textIPTTL.setText(Integer.toString(Link.IP_TTL));
		
		Text textRemotePDV = new Text(childShellLinkWizard, SWT.BORDER);
		textRemotePDV.setEditable(false);
		textRemotePDV.setBounds(171, 360, 100, 20);
		textRemotePDV.setText(Link.PDV_BUFFER);
		
		Label lblRemotePdvBuffer = new Label(childShellLinkWizard, SWT.NONE);
		lblRemotePdvBuffer.setText("Remote PDV Buffer:");
		lblRemotePdvBuffer.setBounds(30, 360, 135, 20);
		
		Label lblAdminStatus = new Label(childShellLinkWizard, SWT.NONE);
		lblAdminStatus.setText("Admin Status:");
		lblAdminStatus.setBounds(300, 120, 100, 20);
		
		Text textCESType = new Text(childShellLinkWizard, SWT.BORDER);
		textCESType.setEditable(false);
		textCESType.setBounds(445, 150, 100, 20);
		textCESType.setText(Link.CES_TYPE);
		
		Label lblCesType = new Label(childShellLinkWizard, SWT.NONE);
		lblCesType.setText("CES Type:");
		lblCesType.setBounds(300, 150, 100, 20);
		
		Text textPacketInterface = new Text(childShellLinkWizard, SWT.BORDER);
		textPacketInterface.setBounds(445, 180, 100, 20);
		
		Label lblPacketInterface = new Label(childShellLinkWizard, SWT.NONE);
		lblPacketInterface.setText("Packet Interface:");
		lblPacketInterface.setBounds(300, 180, 129, 20);
		
		Text textRemoteIP = new Text(childShellLinkWizard, SWT.BORDER);
		textRemoteIP.setBounds(445, 210, 100, 20);
		
		Label lblRemoteIpAddress = new Label(childShellLinkWizard, SWT.NONE);
		lblRemoteIpAddress.setText("Remote IP Address:");
		lblRemoteIpAddress.setBounds(300, 210, 129, 20);
		
		Text textRemoteUDP = new Text(childShellLinkWizard, SWT.BORDER);
		textRemoteUDP.setBounds(445, 240, 100, 20);
		
		Label lblRemoteUdpPort = new Label(childShellLinkWizard, SWT.NONE);
		lblRemoteUdpPort.setText("Remote UDP Port:");
		lblRemoteUdpPort.setBounds(300, 240, 129, 20);
		
		Text textGatewayIP = new Text(childShellLinkWizard, SWT.BORDER);
		textGatewayIP.setBounds(445, 270, 100, 20);
		
		Label lblGatewayIpAddress = new Label(childShellLinkWizard, SWT.NONE);
		lblGatewayIpAddress.setText("Gateway IP Address:");
		lblGatewayIpAddress.setBounds(300, 270, 139, 20);
		
		Text textPBit = new Text(childShellLinkWizard, SWT.BORDER);
		textPBit.setEditable(false);
		textPBit.setBounds(445, 300, 100, 20);
		textPBit.setText(Integer.toString(Link.P_BIT));
	
		Label lblPbitValue = new Label(childShellLinkWizard, SWT.NONE);
		lblPbitValue.setText("P-Bit Value:");
		lblPbitValue.setBounds(300, 300, 100, 20);
		
		Label lblDscp = new Label(childShellLinkWizard, SWT.NONE);
		lblDscp.setText("DSCP:");
		lblDscp.setBounds(300, 330, 100, 20);
		
		Text textDSCP = new Text(childShellLinkWizard, SWT.BORDER);
		textDSCP.setEditable(false);
		textDSCP.setBounds(445, 330, 100, 20);
		textDSCP.setText(Integer.toString(Link.DSCP));
		
		Combo comboAdminStatus = new Combo(childShellLinkWizard, SWT.NONE);
		comboAdminStatus.setBounds(445, 120, 100, 25);
		comboAdminStatus.add("Down",0);
		comboAdminStatus.add("Up",1);
		
		Label lblLocalRiq = new Label(childShellLinkWizard, SWT.NONE);
		lblLocalRiq.setBounds(30, 10, 69, 21);
		lblLocalRiq.setText("Local RIQ:");
		ArrayList<RIQ> riqs = builder.getRIQs();
		for(RIQ riq : riqs) {
			comboLocalRIQ.add(riq.getName());
		}
		comboLocalRIQ.setText(localRIQ.getName());
		
		Label lblRemoteRiq = new Label(childShellLinkWizard, SWT.NONE);
		lblRemoteRiq.setText("Remote RIQ:");
		lblRemoteRiq.setBounds(300, 10, 100, 21);
		for(RIQ riq : riqs) {
			comboRemoteRIQ.add(riq.getName());
		}
		
		Label lblCesConfiguration = new Label(childShellLinkWizard, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblCesConfiguration.setText("CES Configuration");
		lblCesConfiguration.setBounds(10, 105, 664, 9);
		
		Label lblShotType = new Label(childShellLinkWizard, SWT.NONE);
		lblShotType.setText("Shot Type:");
		lblShotType.setBounds(30, 55, 69, 21);
		
		Group group = new Group(childShellLinkWizard, SWT.NONE);
		group.setBounds(169, 22, 113, 70);
		
		Button btnUHF = new Button(group, SWT.RADIO);
		btnUHF.setBounds(2, 21, 110, 21);
		btnUHF.setText("UHF");
		btnUHF.setSelection(true);
		
		Button btnPPN = new Button(group, SWT.RADIO);
		btnPPN.setBounds(2, 48, 110, 21);
		btnPPN.setText("Fiber (PPN)");
		
		Label lblKgSelection = new Label(childShellLinkWizard, SWT.NONE);
		lblKgSelection.setText("KG Selection: ");
		lblKgSelection.setBounds(300, 55, 100, 21);
		
		Group group_1 = new Group(childShellLinkWizard, SWT.NONE);
		group_1.setBounds(439, 22, 113, 70);
		
		Button btnLeft = new Button(group_1, SWT.RADIO);
		btnLeft.setBounds(2, 21, 110, 21);
		btnLeft.setText("Left");
		btnLeft.setSelection(true);
		
		Button btnRight = new Button(group_1, SWT.RADIO);
		btnRight.setBounds(2, 48, 110, 21);
		btnRight.setText("Right");
		
		Button btnNewButton = new Button(childShellLinkWizard, SWT.NONE);
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
				childShellLinkWizard.dispose();
			}
		});
		btnNewButton.setBounds(574, 120, 100, 50);
		btnNewButton.setText("Add");
		
		Button btnAutoCalc = new Button(childShellLinkWizard, SWT.NONE);
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
		
		Button btnCancel = new Button(childShellLinkWizard, SWT.NONE);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				childShellLinkWizard.dispose();
			}
		});
		btnCancel.setText("Cancel");
		btnCancel.setBounds(574, 240, 100, 50);
		
		Button btnClear = new Button(childShellLinkWizard, SWT.NONE);
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
	
	
	
	public void initTable(Table t) {
		t.removeAll();
		for(RIQ riq : builder.getRIQs()) {
			TableItem tableItem = new TableItem(t, SWT.NONE);
			tableItem.setText(0,riq.getName());
			tableItem.setText(1,Integer.toString(riq.getLinks().size()));
			tableItem.setText(2,Integer.toString(riq.getClientList().size()));
		}
		tableListeners(t);
	}
	
	public void tableListeners(Table t) {
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
						System.out.println("Opening Link Wizard for  "+t.getSelection()[0].getText());
						RIQ local = builder.getRIQ(t.getSelection()[0].getText());
						childShellLinkWizard(local);
						childShellLinkWizard.open();
						childShellLinkWizard.layout();
						while (!childShellLinkWizard.isDisposed()) {
							if (!display.readAndDispatch()) {
								display.sleep();
							}
						}
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
