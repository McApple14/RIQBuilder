package com.amadeus.RIQBuilder;

import java.util.ArrayList;

public class KG175D {
	private String name;
	private String ptIP;
	private String ptGW;
	private String ctIP;
	private String ctGW;
	private ArrayList<SA> saList;
	
	public KG175D() {}
	
	public KG175D(String n, String pt, String ptg, String ct, String ctg) {
		name = n;
		ptIP = pt;
		ptGW = ptg;
		ctIP = ct;
		ctGW = ctg;
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
}
