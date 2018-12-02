package com.amadeus.RIQBuilder;

import java.util.regex.Pattern;


public class Link implements Comparable<Link>{
	private String name;
	private RIQ localRIQ;
	private RIQ remoteRIQ;
	private String localIP;
	private String remoteIP;
	private int localUDP;
	private int remoteUDP;
	private int type; // 0 for UHF; 1 for PPN
	private String interfaceCES;
	private static final Pattern PATTERN_INT = Pattern.compile("^[13]\\:[13]$");
	private static final Pattern PATTERN_IP = Pattern.compile(
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	private static final Pattern PATTERN_SUBNET = Pattern.compile(
			"^(255)\\.(0|128|192|224|240|248|25[245])\\.(0|128|192|224|240|248|25[245])\\.(0|128|192|224|240|248|25[245])$");
	private String subnetMask;
	private int vLAN;
	private int kg175d;
	private String packetInterface;
	private String gatewayIP;
	private String description;
	
	//Constants
	public static final int UHF = 0;
	public static final int PPN = 1;
	public static final int UHF_PORT = 50000;
	//private static final int PPN_VLAN = 150;
	private static final int IP_TTL = 1;
	private static final String PDV_BUFFER = "Low";
	private static final boolean ADMIN_STATUS = true;
	private static final String CES_TYPE = "Serial";
	private static final int P_BIT = 6;
	private static final int DSCP = 43;
	
	
	public Link() {}
	
	// Used for creating a compareTo link (so we can remove links from a list)
	public Link(String n) {
		name = n;
	}
	
	/**
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
	 */
	public Link(String name, RIQ localRIQ, RIQ remoteRIQ, int type, String localIP, String remoteIP, String interfaceCES, int vLAN, String subnetMask, int kg175d) {
		this.name = name;
		this.localRIQ = localRIQ;
		this.remoteRIQ = localRIQ;
		this.localIP = localIP;
		this.remoteIP = remoteIP;
		this.type = type;
		this.interfaceCES = validateCESInterface(interfaceCES);
		this.subnetMask = subnetMaskValidation(subnetMask);
		this.vLAN = vLAN;
		this.kg175d = kg175d;
	}
	
	/**
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
	public Link(String name, RIQ localRIQ, RIQ remoteRIQ, int type, String localIP, String remoteIP, String interfaceCES, int vLAN, String subnetMask, int kg175d, int udpPort) {
		this.name = name;
		this.localRIQ = localRIQ;
		this.remoteRIQ = localRIQ;
		this.localIP = localIP;
		this.remoteIP = remoteIP;
		this.type = type;
		this.interfaceCES = validateCESInterface(interfaceCES);
		this.subnetMask = subnetMaskValidation(subnetMask);
		this.vLAN = vLAN;
		this.kg175d = kg175d;
		localUDP = remoteUDP = udpPort;
	}
	
	/**
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
	 * @param localPort UDP port for local RIQ
	 * @param remotePort UDP port for remote RIQ
	 */
	public Link(String name, RIQ localRIQ, RIQ remoteRIQ, int type, String localIP, String remoteIP, String interfaceCES, int vLAN, String subnetMask, int kg175d, int localPort, int remotePort) {
		this.name = name;
		this.localRIQ = localRIQ;
		this.remoteRIQ = localRIQ;
		this.localIP = localIP;
		this.remoteIP = remoteIP;
		this.type = type;
		this.interfaceCES = validateCESInterface(interfaceCES);
		this.subnetMask = subnetMaskValidation(subnetMask);
		this.vLAN = vLAN;
		this.kg175d = kg175d;
		localUDP = localPort;
		remoteUDP = remotePort;
	}
	
	/**
	 * validateCESInterface
	 * @param in CES Interface String input
	 * @return returns the input if valid; returns null if invalid
	 */
	public static String validateCESInterface(String in) {
		if(PATTERN_INT.matcher(in).matches()) {return in;}
		return null;
	}
	
	/**
	 * ipValidation
	 * @param in IP Address String input
	 * @return returns the input if a valid IP; returns null if invalid
	 */
	public static String ipValidation(String in) {
		if(PATTERN_IP.matcher(in).matches()) {return in;}
		return null;
	}
	
	/**
	 * subnetMaskValidation
	 * @param in Subnet Mask String input
	 * @return returns input if a valid Subnet Mask; returns null if invalid
	 */
	public static String subnetMaskValidation(String in) {
		if(PATTERN_SUBNET.matcher(in).matches()) {return in;}
		return null;
	}
	
	private void derivation() {
		switch (type) {
		case UHF: 	description = "UHF Shot";
					gatewayIP = "0.0.0.0";
					localUDP = remoteUDP = UHF_PORT;
					switch (interfaceCES) {
					case "1:1": packetInterface = "1:2";
						break;
					case "1:3": packetInterface = "1:4";
						break;
					case "3:1": packetInterface = "3:2";
						break;
					case "3:3": packetInterface = "3:4";
						break;
					default: packetInterface = "Error";
					}
					break;
		case PPN: 	description = "Fiber Shot";
					gatewayIP = ((localRIQ.getKGs())[kg175d]).getPTIP();
					packetInterface = "2:4";
					break;
		default: 	description = "Error";
					localUDP = remoteUDP = UHF_PORT;
		}
	}
	
	public String getName() {return name;}
	public RIQ getLocalRIQ() {return localRIQ;}
	public RIQ getRemoteRIQ() {return remoteRIQ;}
	public int getLocalUDP() {return localUDP;}
	public int getRemoteUDP() {return remoteUDP;}
	public String getLocalIP() {return localIP;}
	public String getRemoteIP() {return remoteIP;}
	public int getType() {return type;}
	public String getCESInterface() {return interfaceCES;}
	public String getSubnetMask() {return subnetMask;}
	public int getVLAN() {return vLAN;}
	public String getPacketInterface() {return packetInterface;}
	public String getGatewayIP() {return gatewayIP;}
	public String getDescription() {return description;}
	public int getSideKG() {return kg175d;}
	
	public void buildSAs() {
		localRIQ.clientSABuilder(this);
		remoteRIQ.clientSABuilder(this);
	}
	
	// Returns NAME,DESCRIPTION,INTERFACECES,LOCALIP,LOCALUDP,SUBNETMASK,VLAN,IP_TTL,PDV_BUFFER,ADMIN_STATUS,CES_TYPE,PACKETINTERFACE,REMOTEIP,REMOTEUDP,GATEWAYIP,P_BIT,DSCP
	public String toString() {
		derivation();
		return name+","+description+","+interfaceCES+","+localIP+","+localUDP+","+subnetMask+","+vLAN+","+IP_TTL+","+
				PDV_BUFFER+","+ADMIN_STATUS+","+CES_TYPE+","+packetInterface+","+remoteIP+","+remoteUDP+","+gatewayIP+","+
				P_BIT+","+DSCP;
	}
	
	public int compareTo(Link other) {
		return toString().compareTo(other.toString());
	}
}
