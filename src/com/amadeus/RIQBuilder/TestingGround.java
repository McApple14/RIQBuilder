package com.amadeus.RIQBuilder;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

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
	private Text textAdminStatus;
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
		shlAddLink.setSize(700, 400);
		shlAddLink.setText("Add Link");
		
		Label lblName = new Label(shlAddLink, SWT.NONE);
		lblName.setBounds(40, 40, 100, 20);
		lblName.setText("Name:");
		
		textName = new Text(shlAddLink, SWT.BORDER);
		textName.setBounds(150, 40, 100, 20);
		
		textDescription = new Text(shlAddLink, SWT.BORDER);
		textDescription.setBounds(150, 70, 100, 20);
		
		Label lblDescription = new Label(shlAddLink, SWT.NONE);
		lblDescription.setText("Description:");
		lblDescription.setBounds(40, 70, 100, 20);
		
		textCESInterface = new Text(shlAddLink, SWT.BORDER);
		textCESInterface.setBounds(150, 100, 100, 20);
		
		Label lblCesInterface = new Label(shlAddLink, SWT.NONE);
		lblCesInterface.setText("CES Interface:");
		lblCesInterface.setBounds(40, 100, 100, 20);
		
		textLocalIP = new Text(shlAddLink, SWT.BORDER);
		textLocalIP.setBounds(150, 130, 100, 20);
		
		Label lblLocalIpAddress = new Label(shlAddLink, SWT.NONE);
		lblLocalIpAddress.setText("Local IP Address:");
		lblLocalIpAddress.setBounds(40, 130, 100, 20);
		
		textLocalUDP = new Text(shlAddLink, SWT.BORDER);
		textLocalUDP.setBounds(150, 160, 100, 20);
		
		Label lblLocalUdpPort = new Label(shlAddLink, SWT.NONE);
		lblLocalUdpPort.setText("Local UDP Port:");
		lblLocalUdpPort.setBounds(40, 160, 100, 20);
		
		textSubnet = new Text(shlAddLink, SWT.BORDER);
		textSubnet.setBounds(150, 190, 100, 20);
		
		Label lblSubnetMask = new Label(shlAddLink, SWT.NONE);
		lblSubnetMask.setText("Subnet Mask:");
		lblSubnetMask.setBounds(40, 190, 100, 20);
		
		textVLAN = new Text(shlAddLink, SWT.BORDER);
		textVLAN.setBounds(150, 220, 100, 20);
		
		Label lblVlan = new Label(shlAddLink, SWT.NONE);
		lblVlan.setText("VLAN:");
		lblVlan.setBounds(40, 220, 100, 20);
		
		Label lblIpTtl = new Label(shlAddLink, SWT.NONE);
		lblIpTtl.setText("IP TTL:");
		lblIpTtl.setBounds(40, 250, 100, 20);
		
		textIPTTL = new Text(shlAddLink, SWT.BORDER);
		textIPTTL.setBounds(150, 250, 100, 20);
		
		textRemotePDV = new Text(shlAddLink, SWT.BORDER);
		textRemotePDV.setBounds(150, 280, 100, 20);
		
		Label lblRemotePdvBuffer = new Label(shlAddLink, SWT.NONE);
		lblRemotePdvBuffer.setText("Remote PDV Buffer:");
		lblRemotePdvBuffer.setBounds(40, 280, 110, 20);
		
		lblAdminStatus = new Label(shlAddLink, SWT.NONE);
		lblAdminStatus.setText("Admin Status:");
		lblAdminStatus.setBounds(285, 40, 100, 20);
		
		textAdminStatus = new Text(shlAddLink, SWT.BORDER);
		textAdminStatus.setBounds(410, 40, 100, 20);
		
		textCESType = new Text(shlAddLink, SWT.BORDER);
		textCESType.setBounds(410, 70, 100, 20);
		
		lblCesType = new Label(shlAddLink, SWT.NONE);
		lblCesType.setText("CES Type:");
		lblCesType.setBounds(285, 70, 100, 20);
		
		textPacketInterface = new Text(shlAddLink, SWT.BORDER);
		textPacketInterface.setBounds(410, 100, 100, 20);
		
		lblPacketInterface = new Label(shlAddLink, SWT.NONE);
		lblPacketInterface.setText("Packet Interface:");
		lblPacketInterface.setBounds(285, 100, 100, 20);
		
		textRemoteIP = new Text(shlAddLink, SWT.BORDER);
		textRemoteIP.setBounds(410, 130, 100, 20);
		
		lblRemoteIpAddress = new Label(shlAddLink, SWT.NONE);
		lblRemoteIpAddress.setText("Remote IP Address:");
		lblRemoteIpAddress.setBounds(285, 130, 100, 20);
		
		textRemoteUDP = new Text(shlAddLink, SWT.BORDER);
		textRemoteUDP.setBounds(410, 160, 100, 20);
		
		lblRemoteUdpPort = new Label(shlAddLink, SWT.NONE);
		lblRemoteUdpPort.setText("Remote UDP Port:");
		lblRemoteUdpPort.setBounds(285, 160, 100, 20);
		
		textGatewayIP = new Text(shlAddLink, SWT.BORDER);
		textGatewayIP.setBounds(410, 190, 100, 20);
		
		lblGatewayIpAddress = new Label(shlAddLink, SWT.NONE);
		lblGatewayIpAddress.setText("Gateway IP Address:");
		lblGatewayIpAddress.setBounds(285, 190, 110, 20);
		
		textPBit = new Text(shlAddLink, SWT.BORDER);
		textPBit.setBounds(410, 220, 100, 20);
		
		lblPbitValue = new Label(shlAddLink, SWT.NONE);
		lblPbitValue.setText("P-Bit Value:");
		lblPbitValue.setBounds(285, 220, 100, 20);
		
		lblDscp = new Label(shlAddLink, SWT.NONE);
		lblDscp.setText("DSCP:");
		lblDscp.setBounds(285, 250, 100, 20);
		
		textDSCP = new Text(shlAddLink, SWT.BORDER);
		textDSCP.setBounds(410, 250, 100, 20);
	}
}
