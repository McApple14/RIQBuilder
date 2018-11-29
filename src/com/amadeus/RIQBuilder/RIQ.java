package com.amadeus.RIQBuilder;

import java.util.*;

public class RIQ {
	private String name;
	private ArrayList<String> clients;
	private ArrayList<Link> links;
	private KG175D KG1;
	private KG175D KG2;
	private boolean isHub;
	
	
	public RIQ() {
		clients = new ArrayList<String>();
		links = new ArrayList<Link>();
	}
	
	public RIQ(String n) {
		name = n;
		clients = new ArrayList<String>();
		links = new ArrayList<Link>();
		isHub = false;
	}
	
	public RIQ(String n, KG175D k1, KG175D k2) {
		name = n;
		clients = new ArrayList<String>();
		links = new ArrayList<Link>();
		KG1 = k1;
		KG2 = k2;
		isHub = false;
	}
	
	public void setName(String in) {name=in;}
	
	public String getName() {return name;}
	
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
	
	public KG175D[] getKGs() {
		return new KG175D[] {KG1, KG2};
		
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
	
	public String toString() {
		return name;
	}
}
