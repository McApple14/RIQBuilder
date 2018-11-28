
public class SA implements Comparable<SA>{
	private String name;
	private String host;
	private String remotePT;
	private String remoteCT;
	private int cidr;
	
	public SA() {}
	
	public SA(String n, String h, String pt, String ct, int c) {
		name = n;
		host = Link.ipValidation(h);
		remotePT = Link.ipValidation(pt);
		remoteCT = Link.ipValidation(ct);
		cidr = c;
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
