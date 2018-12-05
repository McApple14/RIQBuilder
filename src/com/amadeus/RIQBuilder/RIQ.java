package com.amadeus.RIQBuilder;

import java.util.*;

public class RIQ implements Comparable<RIQ> {
	private String name;
	private ArrayList<String> clients;
	private ArrayList<Link> links;
	private KG175D kg1;
	private KG175D kg2;
	private boolean isHub;
	private boolean initialized;
	
	
	public RIQ() {
		clients = new ArrayList<String>();
		links = new ArrayList<Link>();
		initialized = false;
	}
	
	public RIQ(String name) {
		this.name = name;
		clients = new ArrayList<String>();
		links = new ArrayList<Link>();
		setHub(false);
		initialized = false;
	}
	
	public RIQ(String name, KG175D kg1, KG175D kg2) {
		this.name = name;
		clients = new ArrayList<String>();
		links = new ArrayList<Link>();
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
	
	public boolean addClient(String in) {
		String IP = Link.ipValidation(in);
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
		ArrayList<String> devices = (link.getRemoteRIQ()).getClientList();
		KG175D remoteKG = (link.getRemoteRIQ()).getKGs()[link.getSideKG()];
		(getKGs())[link.getSideKG()].addSA("RemoteRIQ "+(link.getRemoteRIQ().getName()), link.getRemoteIP(), remoteKG.getPTIP(), remoteKG.getCTIP(), 32);
		
		int count = 1;
		for(String ip : devices) {
			(getKGs())[link.getSideKG()].addSA((link.getRemoteRIQ().getName())+" Client "+count++, ip, remoteKG.getPTIP(), remoteKG.getCTIP(), 32);
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
}
