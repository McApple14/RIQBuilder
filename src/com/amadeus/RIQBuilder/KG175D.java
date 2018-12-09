package com.amadeus.RIQBuilder;

import java.io.Serializable;
import java.util.ArrayList;

public class KG175D implements Serializable {
	/**
	 * Used for Serializable
	 */
	private static final long serialVersionUID = -8298668530076076618L;
	private String name;
	private String ptIP;
	private String ptGW;
	private String ctIP;
	private String ctGW;
	private ArrayList<SA> saList;
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	public KG175D() {saList = new ArrayList<SA>();}
	
	public KG175D(String name, String ptIP, String ptGW, String ctIP, String ctGW) {
		this.name = name;
		this.ptIP = ptIP;
		this.ptGW = ptGW;
		this.ctIP = ctIP;
		this.ctGW = ctGW;
		saList = new ArrayList<SA>();
	}
	
	public String getName() {return name;}
	public String getPTIP() {return ptIP;}
	public String getCTIP() {return ctIP;}
	public String getPTGW() {return ptGW;}
	public String getCTGW() {return ctGW;}
	public ArrayList<SA> getSAList() {return saList;}
	
	public boolean addSA(String n, String h, String pt, String ct, int c) {
		return saList.add(new SA(n,h,pt,ct,c));
	}
	
	public boolean removeSA(SA other) {
		return saList.remove(other);
	}
	
	public boolean removeSA(String n) {
		for(int i=0; i<saList.size(); i++) {
			if((saList.get(i)).getName().compareTo(n) == 0) {
				saList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return name;
	}
}
