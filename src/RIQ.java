import java.util.*;
import java.util.regex.*;

public class RIQ {
	private String name;
	private String localIP;
	private int udpPort;
	private ArrayList<String> clients;
	private ArrayList<Link> links;
	private static final Pattern PATTERN = Pattern.compile(
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	
	public RIQ() {
		clients = new ArrayList<String>();
	}
	
	public RIQ(String n, String ip, int port) {
		name = n;
		localIP = ip;
		udpPort = port;
		clients = new ArrayList<String>();
	}
	
	public static String ipValidation(String in) {
		if(PATTERN.matcher(in).matches()) {return in;}
		return null;
	}
	
	public void setName(String in) {name=in;}
	public void setLocalIP(String in) {localIP=ipValidation(in);}
	public void setPort(int in) {udpPort=in;}
	
	public String getName() {return name;}
	public String getLocalIP() {return localIP;}
	public int getUDPPort() {return udpPort;} 
	
	public boolean addClient(String in) {
		String IP = ipValidation(in);
		if(IP != null) {
			clients.add(IP);
			return true;
		}
		return false;
	}
	
	public boolean removeClient(String in) {
		return clients.remove(in);
	}
	
	public ArrayList<String> getClientList() {return clients;}
	
	public boolean addLink(Link link) {
		return links.add(link);
	}
	
	public boolean removeLink(String linkName) {
		return links.remove(new Link(linkName));
	}
	
	public String toString() {
		return name + "\n" + localIP + "\n" + Integer.toString(udpPort);
	}
}
