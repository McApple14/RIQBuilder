import java.util.regex.Pattern;

public class Link {
	private String name;
	private RIQ riqLocal;
	private RIQ riqDistant;
	private int localUDP;
	private int distantUDP;
	private int type; // 0 for UHF; 1 for PPN
	private String interfaceCES;
	private static final Pattern PATTERN = Pattern.compile("^[13]\\:[13]$");
	private String subnetMask;
	private int vLAN;
	private String packetInterface;
	private String gatewayIP;
	private String description;
	
	//Constants
	private static final int UHF = 0;
	private static final int PPN = 1;
	private static final int UHF_PORT = 50000;
	//private static final int PPN_VLAN = 150;
	private static final int IP_TTL = 1;
	private static final String PDV_BUFFER = "Low";
	private static final boolean ADMIN_STATUS = true;
	private static final String CES_TYPE = "Serial";
	private static final int P_BIT = 6;
	private static final int DSCP = 43;
	
	
	public Link() {}
	
	public Link(String n, RIQ a, RIQ b, int t) {
		name = n;
		riqLocal = a;
		riqDistant = b;
		type = t;
	}
	
	public Link(String n, RIQ a, RIQ b, int t, String cesInt) {
		name = n;
		riqLocal = a;
		riqDistant = b;
		type = t;
		interfaceCES = validateCESInterface(cesInt);
		subnetMask = "255.255.255.0";
		gatewayIP = "0.0.0.0";
	}
	
	public Link(String n, RIQ a, RIQ b, int t, String cesInt, String subnet) {
		name = n;
		riqLocal = a;
		riqDistant = b;
		type = t;
		interfaceCES = validateCESInterface(cesInt);
		subnetMask = RIQ.ipValidation(subnet); //DANGEROUS!!! You have no way of validating that the valid IP is a SUBNET MASK
		gatewayIP = "0.0.0.0";
	}
	
	public Link(String n, RIQ a, RIQ b, int t, String cesInt, String subnet, String gIP) {
		name = n;
		riqLocal = a;
		riqDistant = b;
		type = t;
		interfaceCES = validateCESInterface(cesInt);
		subnetMask = RIQ.ipValidation(subnet); //DANGEROUS!!! You have no way of validating that the valid IP is a SUBNET MASK
		gatewayIP = RIQ.ipValidation(gIP);
	}
	
	public static String validateCESInterface(String in) {
		if(PATTERN.matcher(in).matches()) {return in;}
		return null;
	}
	
	private void derivation() {
		switch (type) {
		case UHF: 	description = "UHF Shot";
					localUDP = distantUDP = UHF_PORT;
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
					localUDP = riqLocal.getUDPPort();
					distantUDP = riqDistant.getUDPPort();
					packetInterface = "2:4";
					break;
		default: 	description = "Error";
					localUDP = distantUDP = 0;
		}
	}
	
	public String toString() {
		derivation();
		return name+","+description+","+interfaceCES+","+riqLocal.getLocalIP()+","+localUDP+","+subnetMask+","+vLAN+","+IP_TTL+","+PDV_BUFFER+","+ADMIN_STATUS+","+
				CES_TYPE+","+packetInterface+","+riqDistant.getLocalIP()+","+distantUDP+","+gatewayIP+","+P_BIT+","+DSCP;
	}
}
