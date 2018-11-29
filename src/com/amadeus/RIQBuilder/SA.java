package com.amadeus.RIQBuilder;

public class SA implements Comparable<SA>{
	private String name;
	private String host;
	private String remotePT;
	private String remoteCT;
	private int cidr;
	
	public SA() {}
	
	public SA(String name, String host, String remotePT, String remoteCT, int cidr) {
		this.name = name;
		this.host = Link.ipValidation(host);
		this.remotePT = Link.ipValidation(remotePT);
		this.remoteCT = Link.ipValidation(remoteCT);
		this.cidr = cidr;
	}
	
	public String getName() {return name;}
	public String getHost() {return host;}
	public String getRemotePT() {return remotePT;}
	public String getRemoteCT() {return remoteCT;}
	public int getCIDR() {return cidr;}
	
	public String toString() {
		return 	"Link Name:\t"+name+"\n"+
				"Host:\t\t"+host+"\\"+"\n"+
				"Remote PT:\t"+remotePT+"\n"+
				"Remote CT:\t"+remoteCT;
	}
	
	public int compareTo(SA other) {
		return toString().compareTo(other.toString());
	}
}
