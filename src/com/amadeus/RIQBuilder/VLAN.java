package com.amadeus.RIQBuilder;

import java.io.Serializable;

public class VLAN implements Comparable<VLAN>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9030447401469184884L;
	private String name;
	private String description;
	private boolean adminStatus;
	private boolean macLearning;
	private int vLAN;
	private int multicast;
	private Tuple[] interfaces;
	
	public VLAN() {}
	
	public VLAN(int vLAN, String name, String description, boolean adminStatus, boolean macLearning, int multicast) {
		this.name = name;
		this.description = description;
		this.adminStatus = adminStatus;
		this.macLearning = macLearning;
		this.vLAN = vLAN;
		this.multicast = multicast;
		this.setInterfaces(new Tuple[14]);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(boolean adminStatus) {
		this.adminStatus = adminStatus;
	}

	public boolean getMacLearning() {
		return macLearning;
	}

	public void setMacLearning(boolean macLearning) {
		this.macLearning = macLearning;
	}

	public int getVLAN() {
		return vLAN;
	}

	public void setVLAN(int vLAN) {
		this.vLAN = vLAN;
	}

	public int getMulticast() {
		return multicast;
	}

	public void setMulticast(int multicast) {
		this.multicast = multicast;
	}
	
	public Tuple[] getInterfaces() {
		return interfaces;
	}
	
	public static int interfaceToInt(String i) {
		switch(i) {
		case "2:1":
			return 0;
		case "2:2":
			return 1;
		case "2:3":
			return 2;
		case "2:4":
			return 3;
		case "2:5":
			return 4;
		case "2:6":
			return 5;
		case "1:1":
			return 6;
		case "1:2":
			return 7;
		case "1:3":
			return 8;
		case "1:4":
			return 9;
		case "3:1":
			return 10;
		case "3:2":
			return 11;
		case "3:3":
			return 12;
		case "3:4":
			return 13;
		default:
			System.out.println("Bad Input");
			return -1;
		}
	}
	
	public Tuple getInterface(String i) {
		if(interfaceToInt(i)==-1) {return null;}
		return interfaces[interfaceToInt(i)];
	}

	public void setInterfaces(Tuple[] interfaces) {
		this.interfaces = interfaces;
	}
	
	public void setInterface(String i, Tuple t) {
		if(interfaceToInt(i)==-1) {return;}
		interfaces[interfaceToInt(i)] = t;
	}
	
	public String toString() {
		return 	"VLAN:\t\t"+vLAN+"\n"+
				"Name:\t\t"+name+"\n"+
				"Description:\t"+description+"\\"+
				"Admin Status:\t"+adminStatus+"\n"+
				"MAC Learning:\t"+macLearning+"\n"+
				"Mutlicast:\t"+multicast;
	}
	
	public int compareTo(VLAN other) {
		return toString().compareTo(other.toString());
	}
}
