import java.util.*;
import java.util.regex.*;

public class RIQ {
	private String name;
	private String localIP;
	private int udpPort;
	private ArrayList<String> Clients;	
	private static final Pattern PATTERN = Pattern.compile(
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	
	public RIQ() {
		Clients = new ArrayList<String>();
	}
	
	public RIQ(String n, String ip, int port) {
		name = n;
		localIP = ip;
		udpPort = port;
		Clients = new ArrayList<String>();
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
			Clients.add(IP);
			return true;
		}
		return false;
	}
	
	public boolean removeClient(String in) {
		return Clients.remove(in);
	}
	
	public ArrayList<String> getClientList() {return Clients;}
	
	public String toString() {
		return name + "\n" + localIP + "\n" + Integer.toString(udpPort);
	}
}
