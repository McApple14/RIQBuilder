package com.amadeus.RIQBuilder;

public class Client implements Comparable<Client> {
	
	private String name;
	private String ip;
	
	public Client() {
		name = "Client";
		ip = "169.254.0.1";
	}
	
	public Client(String name, String ip) {
		this.name = name;
		this.ip = ip;
	}
	
	public String getName() {return name;}
	
	public void setName(String name) {this.name = name;}
	
	public String getIP() {return ip;}
	
	public void setIp(String ip) {this.ip = ip;}
	
	public int compareTo(Client other) {return (this.name+this.ip).compareTo((other.getName()+other.getIP()));}

}
