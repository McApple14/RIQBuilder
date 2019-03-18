package com.amadeus.RIQBuilder;

import java.io.Serializable;
import java.util.*;

public class RIQ implements Comparable<RIQ>, Serializable {
	/**
	 * Used for Serializable
	 */
	private static final long serialVersionUID = 9142118418101855780L;
	private String name;
	private ArrayList<Client> clients;
	private ArrayList<Link> links;
	private ArrayList<VLAN> vLANs;
	private KG175D kg1;
	private KG175D kg2;
	private boolean isHub;
	private boolean initialized;
	
	
	public RIQ() {
		clients = new ArrayList<Client>();
		links = new ArrayList<Link>();
		setVLANs(new ArrayList<VLAN>());
		initialized = false;
	}
	
	public RIQ(String name) {
		this.name = name;
		clients = new ArrayList<Client>();
		links = new ArrayList<Link>();
		setVLANs(new ArrayList<VLAN>());
		setHub(false);
		initialized = false;
	}
	
	public RIQ(String name, KG175D kg1, KG175D kg2) {
		this.name = name;
		clients = new ArrayList<Client>();
		links = new ArrayList<Link>();
		setVLANs(new ArrayList<VLAN>());
		this.kg1 = kg1;
		this.kg2 = kg2;
		setHub(false);
		initialized = false;
	}
	
	public void setName(String name) {this.name=name;}
	public String getName() {return name;}
	
	public void setKGs(KG175D[] kgs) {kg1=kgs[0];kg2=kgs[1];}
	public KG175D[] getKGs() {return new KG175D[] {kg1, kg2};}
	
	public void initialized(boolean bool) {initialized=bool;}
	public boolean isInitialized() {return initialized;}
	
	public void setHub(boolean isHub) {this.isHub = isHub;}
	public boolean isHub() {return isHub;}
	
	public ArrayList<Link> getLinks() {return links;}
	
	public boolean addClient(String name, String ip) {
		System.out.println(ip);
		ip = Link.ipValidation(ip);
		if(ip != null) {
			clients.add(new Client(name,ip));
			return true;
		}
		return false;
	}
	
	public Client removeClient(String name) {
		for(Client client : clients) {
			if(client.getName().compareTo(name)==0) {clients.remove(client); return client;}
		}
		return null;
	}
	
	public ArrayList<Client> getClientList() {return clients;}
	
	public boolean addLink(Link link) {
		if(link.getType()==Link.PPN) {clientSABuilder(link);}
		vLANs.add(new VLAN(link.getVLAN(),link.getName(),link.getDescription(),true,true,0));
		return links.add(link);
	}
	
	public boolean removeLink(String linkName) {
		for(int i=0;i<links.size();i++) {
			if((links.get(i)).getName().compareTo(linkName) == 0) {
				links.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeLink(Link l) {
		return links.remove(l);
	}
	
	public void clientSABuilder(Link link) {
		ArrayList<Client> devices = (link.getRemoteRIQ()).getClientList();
		if(devices == null || devices.size() == 0) {return;}
		System.out.println("Remote RIQ "+link.getRemoteRIQ().getName()+" has "+link.getRemoteRIQ().getClientList().size()+" Clients.");
		KG175D remoteKG = (link.getRemoteRIQ()).getKGs()[link.getSideKG()];
		System.out.println(remoteKG+" Selected");
		(getKGs())[link.getSideKG()].addSA("RemoteRIQ "+(link.getRemoteRIQ().getName()), link.getRemoteIP(), remoteKG.getPTIP(), remoteKG.getCTIP(), 32);
		System.out.println("Remote RIQ SA Added");
		
		for(Client client : devices) {
			(getKGs())[link.getSideKG()].addSA((link.getRemoteRIQ().getName())+client.getName(), client.getIP(), remoteKG.getPTIP(), remoteKG.getCTIP(), 32);
		}
		
		for(SA sa : (getKGs())[link.getSideKG()].getSAList()) {
			System.out.println(sa);
		}
	}
	
	public String getOpenCES() {
		ArrayList<String> cesInts = new ArrayList<String>(Arrays.asList(new String[] {"1:1", "1:3", "3:1", "3:3"}));
		for(Link link : links) {
			if (cesInts.contains(link.getCESInterface())) {
				cesInts.remove(link.getCESInterface());
			}
		}
		System.out.println("Available CES Interfaces for "+name+": "+cesInts);
		if(cesInts.size() > 0) {return cesInts.get(0);}
		return null;
	}
	
	public ArrayList<String> getAllOpenCES() {
		ArrayList<String> cesInts = new ArrayList<String>(Arrays.asList(new String[] {"1:1", "1:3", "3:1", "3:3"}));
		for(Link link : links) {
			if (cesInts.contains(link.getCESInterface())) {
				cesInts.remove(link.getCESInterface());
			}
		}
		System.out.println("Available CES Interfaces for "+name+": "+cesInts);
		if(cesInts.size() > 0) {return cesInts;}
		return null;
	}
	
	public boolean isCESOpen(String ces) {
		for(Link link : links) {
			if (link.getCESInterface().compareTo(ces)==0) {return false;}
		}
		return true;
	}
	
	public String toString() {
		return name;
	}
	
	public int compareTo(RIQ other) {
		return name.compareTo(other.getName());
	}

	public ArrayList<VLAN> getVLANs() {
		return vLANs;
	}

	public void setVLANs(ArrayList<VLAN> vLANs) {
		this.vLANs = vLANs;
	}
}
