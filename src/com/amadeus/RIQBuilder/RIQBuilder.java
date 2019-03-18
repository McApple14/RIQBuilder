package com.amadeus.RIQBuilder;

import java.util.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RIQBuilder implements Serializable {

	/**
	 * Used for Serializable interface
	 */
	private static final long serialVersionUID = 1237724186392451191L;
	//public String[][] ipScheme_UHF;
	//public String[][] ipScheme_PPN;
	private String[][][] ipScheme;
	private String[][][][] kgScheme; 
	private int[][] vlanScheme;
	private int[][] portScheme;
	private ArrayList<RIQ> riqs;
	private ArrayList<Link> links;
	private String kgGateway;
	private int baseVLAN;
	private String basePPN;
	private String baseUHF;
	private String baseKG;
	
	/* Note:
	 * kgScheme[i][j][SIDE][PT/CT]
	 * i: row i which corresponds to the ipScheme
	 * j: col j which corresponds to the ipScheme
	 * SIDE: The side the KG is on (left=0; right=1)
	 * PT/CT: Which IP (Plain Text / Cipher Text)
	 */
	
	/* Note:
	 * ipScheme[i][j][TYPE]
	 * i: row i which corresponds to the ipScheme
	 * j: col j which corresponds to the ipScheme
	 * TYPE: Type of shot the IP corresponds to (UHF=0; PPN=1)
	 */
	
	public RIQBuilder() {
		riqs = new ArrayList<RIQ>();
		links = new ArrayList<Link>();
		// TODO Auto-generated method stub
	}
	
	public RIQBuilder(boolean debug) {
		riqs = new ArrayList<RIQ>();
		links = new ArrayList<Link>();
		
		if(debug) {
			// Presets for Testing----------------------------
			String uhfBase = "192.168.0.0";
			String ppnBase = "198.127.1.0";
			String kgBase = "10.11.16.0";
			kgGateway = "10.11.16.16";
			int vlanBase = 30;
			
			riqs = new ArrayList<RIQ>();
			
			riqs.add(new RIQ("ICC"));
			riqs.add(new RIQ("Alpha"));
			riqs.add(new RIQ("Bravo"));
			riqs.add(new RIQ("Charlie"));
			riqs.add(new RIQ("Delta"));
			riqs.add(new RIQ("CRG"));
			
			int numRIQs = riqs.size();
			
			for(int i=1; i <= riqs.size(); i++) {
				riqs.get(i-1).addClient("Client1", "169.254."+Integer.toString(i)+".44");
				riqs.get(i-1).addClient("Client2", "169.254."+Integer.toString(i)+".55");
				System.out.println(riqs.get(i-1).getName()+" has "+riqs.get(i-1).getClientList().size()+" Clients.");
			}
			System.out.println("VLAN Base = "+vlanBase+"; numRIQs = "+numRIQs);
			// Presets for Testing----------------------------
			
			initialization(vlanBase, ppnBase, uhfBase, kgBase);
				
			
			//vlanSchemeBuilder(base);
			for(int[] row : vlanScheme) {
				System.out.println(Arrays.toString(row));
			}
			System.out.println();
			
			for(int[] row : portScheme) {
				System.out.println(Arrays.toString(row));
			}
			System.out.println();
			
			//ipSchemeBuilder(Link.PPN,ppnBase);
			for(int i=0; i<numRIQs;i++) {
				System.out.print("[");
				for(int j=0; j<numRIQs;j++) {
					System.out.print(ipScheme[i][j][Link.PPN]);
					if((j+1)!=riqs.size()) {System.out.print(",\t");}
				}
				System.out.println("]");
			}
			System.out.println();
			
			//ipSchemeBuilder(Link.UHF,uhfBase);
			for(int i=0; i<numRIQs;i++) {
				System.out.print("[");
				for(int j=0; j<numRIQs;j++) {
					System.out.print(ipScheme[i][j][Link.UHF]);
					if((j+1)!=riqs.size()) {System.out.print(",\t");}
				}
				System.out.println("]");
			}
			System.out.println();
			
			//kgSchemeBuilder("172.16.32.0");
			System.out.println("KG Scheme LEFT PT");
			for(int i=0; i<riqs.size();i++) {
				System.out.print("[");
				for(int j=0; j<riqs.size();j++) {
					System.out.print(kgScheme[i][j][0][0]);
					if((j+1)!=riqs.size()) {System.out.print(",\t");}
				}
				System.out.println("]");
			}
			System.out.println();
			System.out.println("KG Scheme LEFT CT");
			for(int i=0; i<riqs.size();i++) {
				System.out.print("[");
				for(int j=0; j<riqs.size();j++) {
					System.out.print(kgScheme[i][j][0][1]);
					if((j+1)!=riqs.size()) {System.out.print(",\t");}
				}
				System.out.println("]");
			}
			System.out.println();
			
			System.out.println("KG Scheme RIGHT PT");
			for(int i=0; i<riqs.size();i++) {
				System.out.print("[");
				for(int j=0; j<riqs.size();j++) {
					System.out.print(kgScheme[i][j][1][0]);
					if((j+1)!=riqs.size()) {System.out.print(",\t");}
				}
				System.out.println("]");
			}
			System.out.println();
			System.out.println("KG Scheme RIGHT CT");
			for(int i=0; i<riqs.size();i++) {
				System.out.print("[");
				for(int j=0; j<riqs.size();j++) {
					System.out.print(kgScheme[i][j][1][1]);
					if((j+1)!=riqs.size()) {System.out.print(",\t");}
				}
				System.out.println("]");
			}
			System.out.println();
			
			//linkBuilder(String name, RIQ local, RIQ remote, int type, String ces, String subnet, int kg) throws NoAvailableCESException
			try {
				addLink(linkBuilder("ICC -> Alpha", riqs.get(0), riqs.get(1), Link.PPN, riqs.get(0).getOpenCES(), "255.255.0.0", 0));
				addLink(linkBuilder("Alpha -> ICC", riqs.get(1), riqs.get(0), Link.PPN, riqs.get(1).getOpenCES(), "255.255.0.0", 0));
				
				addLink(linkBuilder("ICC -> Bravo", riqs.get(0), riqs.get(2), Link.PPN, riqs.get(0).getOpenCES(), "255.255.0.0", 0));
				addLink(linkBuilder("Bravo -> ICC", riqs.get(2), riqs.get(0), Link.PPN, riqs.get(2).getOpenCES(), "255.255.0.0", 0));
				
				addLink(linkBuilder("ICC -> Charlie", riqs.get(0), riqs.get(3), Link.PPN, riqs.get(0).getOpenCES(), "255.255.0.0", 0));
				addLink(linkBuilder("Charlie -> ICC", riqs.get(3), riqs.get(0), Link.PPN, riqs.get(3).getOpenCES(), "255.255.0.0", 0));
				
				addLink(linkBuilder("ICC -> Delta", riqs.get(0), riqs.get(4), Link.PPN, riqs.get(0).getOpenCES(), "255.255.0.0", 0));
				addLink(linkBuilder("Delta -> ICC", riqs.get(4), riqs.get(0), Link.PPN, riqs.get(4).getOpenCES(), "255.255.0.0", 0));
			} catch (NoAvailableCESException e) {System.out.print("Something went wrong in RIQBuilder: "); System.out.println(e.getCulperit());}
		}
	}
	
	@SuppressWarnings("unchecked")
	public RIQBuilder (RIQBuilder another) {
		this.ipScheme = another.ipScheme.clone();
		this.kgScheme = another.kgScheme.clone(); 
		this.vlanScheme = another.vlanScheme.clone();
		this.portScheme = another.portScheme.clone();
		this.riqs = (ArrayList<RIQ>) another.riqs.clone();
		this.links = (ArrayList<Link>) another.links.clone();
		this.kgGateway = another.kgGateway;
		this.baseVLAN = another.baseVLAN;
		this.basePPN = another.basePPN;
		this.baseUHF = another.baseUHF;
		this.baseKG = another.baseKG;
	}

	
	public String[][] getIPScheme(int type) throws IllegalArgumentException {
		String[][] output = new String[riqs.size()][riqs.size()];
		
		if(!(type==0 || type==1)) {throw new IllegalArgumentException("Invalid Type (0 for UHF, 1 for Fiber Shot)");}
		
		for(int i=0; i<riqs.size(); i++) {
			for(int j=0; j<riqs.size(); j++) {
				output[i][j] = ipScheme[i][j][type];
			}
		}
		
		return output;
	}
	
	public void vlanSchemeBuilder(int base) throws IllegalArgumentException {
		int numRIQs = riqs.size();
		vlanScheme = new int[numRIQs][numRIQs];
		int count = base;
		for(int i=0;i<numRIQs;i++) {
			for(int j=0;j<i;j++) {
				vlanScheme[i][j] = vlanScheme[j][i] = count;
				count++;
			}
		}
	}
	
	public void ipSchemeBuilder(int type, String baseIP) {
		int count;
		int numRIQs = riqs.size();
		String base;
		if(ipScheme==null) {ipScheme = new String[numRIQs][numRIQs][2];}
		
		switch(type) {
		case Link.UHF:
			count = 0;
			base = 	(baseIP.split("\\."))[0]+"."+
					(baseIP.split("\\."))[1]+".";
			for(int i=0;i<numRIQs;i++) {
				for(int j=0;j<numRIQs;j++) {
					if(i==j) {ipScheme[i][j][Link.UHF]="0.0.0.0";}
					else {
						if(count==0) {ipScheme[i][j][Link.UHF]=(base.concat(Integer.toString(vlanScheme[i][j])).concat(".41"));}
						else {ipScheme[i][j][Link.UHF]=(base.concat(Integer.toString(vlanScheme[i][j])).concat("."+Integer.toString(count)));}
					}
				}
				count++;
			}
			break;
		case Link.PPN:
			count = 0;
			base = 	(baseIP.split("\\."))[0]+"."+
					(baseIP.split("\\."))[1]+"."+
					(baseIP.split("\\."))[2]+".";
			for(int i=0;i<numRIQs;i++) {
				for(int j=0;j<numRIQs;j++) {
					if(count==0) {ipScheme[i][j][Link.PPN]=base.concat("41");}
					else {ipScheme[i][j][Link.PPN]=base.concat(Integer.toString(count));}
				}
				count++;
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid Type (0 for UHF, 1 for Fiber Shot)");
		}
	}
	
	public void kgSchemeBuilder(String baseIP) {
		if(ipScheme == null) {return;}
		int length = riqs.size();
		kgScheme = new String[length][length][2][2];
		
		
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				String[] ip = (ipScheme[i][j][Link.PPN]).split("\\.");
				kgScheme[i][j][0][0] = ip[0]+"."+ip[1]+"."+ip[2]+"."+Integer.toString(Integer.valueOf(ip[3])+100);
				kgScheme[i][j][1][0] = ip[0]+"."+ip[1]+"."+ip[2]+"."+Integer.toString(Integer.valueOf(ip[3])+200);
				if(ipScheme[i][j][Link.PPN]=="0.0.0.0") {kgScheme[i][j][0][0]=kgScheme[i][j][1][0]="0.0.0.0";}
			}				
		}
		
		int count = 1;
		String base = 	(baseIP.split("\\."))[0]+"."+
				(baseIP.split("\\."))[1]+"."+
				(baseIP.split("\\."))[2]+".";
		
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				if(count==16) {count++;}
				kgScheme[i][j][0][1]=base.concat(Integer.toString(count));
				kgScheme[i][j][1][1]=base.concat(Integer.toString(count+riqs.size()));
			}
			count++;
		}
	}
	
	public void portSchemeBuilder() {
		portScheme = new int[riqs.size()][riqs.size()];
		int count = Link.UHF_PORT;
		for(int i=0;i<riqs.size();i++) {
			for(int j=0;j<i;j++) {
				portScheme[i][j] = portScheme[j][i] = count;
				count+=100;
			}
		}
	}
	
	
	public void initialization(int baseVLAN, String basePPN, String baseUHF, String baseKG) {
		System.out.println(riqs);
		
		this.baseVLAN = baseVLAN;
		this.basePPN = basePPN;
		this.baseUHF = baseUHF;
		this.baseKG = baseKG;
		
		vlanSchemeBuilder(baseVLAN);
		ipSchemeBuilder(Link.UHF, baseUHF);
		ipSchemeBuilder(Link.PPN, basePPN);
		kgSchemeBuilder(baseKG);
		portSchemeBuilder();
		
		links = new ArrayList<Link>();
		
		// Setup KGs for every RIQ
		for(RIQ riq : riqs) {
			if(!riq.isInitialized()) {riq.setKGs(new KG175D[] {new KG175D(riq.getName().concat("LeftKG"),kgScheme[riqs.indexOf(riq)][riqs.indexOf(riq)][0][0],getIPScheme(Link.PPN)[riqs.indexOf(riq)][riqs.indexOf(riq)],kgScheme[riqs.indexOf(riq)][riqs.indexOf(riq)][0][1],kgGateway),
					new KG175D(riq.getName().concat("RightKG"),kgScheme[riqs.indexOf(riq)][riqs.indexOf(riq)][1][0],getIPScheme(Link.PPN)[riqs.indexOf(riq)][riqs.indexOf(riq)],kgScheme[riqs.indexOf(riq)][riqs.indexOf(riq)][1][1],kgGateway)});
				riq.initialized(true);
			}
		}
	}
	
	public void initialization(int baseVLAN, String basePPN, String baseUHF, String baseKG, String gateway) {
		kgGateway = gateway;
		riqs = new ArrayList<RIQ>();
		initialization(baseVLAN, basePPN, baseUHF, baseKG);
	}
	
	public void reInitialization(boolean overwrite) {
		System.out.println(riqs);
		
		ipScheme = null;
		
		vlanSchemeBuilder(baseVLAN);
		ipSchemeBuilder(Link.UHF, baseUHF);
		ipSchemeBuilder(Link.PPN, basePPN);
		kgSchemeBuilder(baseKG);
		portSchemeBuilder();
		
		links.clear();
		links = new ArrayList<Link>();
		
		// Setup KGs for every RIQ
		for(RIQ riq : riqs) {
			if(!riq.isInitialized()) {riq.setKGs(new KG175D[] {new KG175D(riq.getName().concat("LeftKG"),kgScheme[riqs.indexOf(riq)][riqs.indexOf(riq)][0][0],getIPScheme(Link.PPN)[riqs.indexOf(riq)][riqs.indexOf(riq)],kgScheme[riqs.indexOf(riq)][riqs.indexOf(riq)][0][1],kgGateway),
					new KG175D(riq.getName().concat("RightKG"),kgScheme[riqs.indexOf(riq)][riqs.indexOf(riq)][1][0],getIPScheme(Link.PPN)[riqs.indexOf(riq)][riqs.indexOf(riq)],kgScheme[riqs.indexOf(riq)][riqs.indexOf(riq)][1][1],kgGateway)});
				riq.initialized(true);
			}
			else if(overwrite) {
				for(int i=0; i<riqs.size(); i++) {
					kgScheme[i][riqs.indexOf(riq)][0][0]= riq.getKGs()[0].getPTIP();
					kgScheme[i][riqs.indexOf(riq)][0][1]= riq.getKGs()[0].getCTIP();
					kgScheme[i][riqs.indexOf(riq)][1][0]= riq.getKGs()[1].getPTIP();
					kgScheme[i][riqs.indexOf(riq)][1][1]= riq.getKGs()[1].getCTIP();
				}
			}
		}
	}
	
	public void addRIQ(RIQ riq) {riqs.add(riq);System.out.println("Added RIQ: "+riq);}
	
	public RIQ getRIQ(String name) {
		for(RIQ riq : riqs) {
			if(riq.getName().compareTo(name)==0) {return riq;}
		}
		return null;
	}
	
	public RIQ removeRIQ(String name) {
		for(RIQ riq : riqs) {
			if(riq.getName().compareTo(name)==0) {
				riqs.remove(riq);
				// Remove all the corresponding links for said RIQ
				for(Link link : riq.getLinks()) {
					links.remove(link);
				}
				for(Link link : links) {
					if(link.getRemoteRIQ().compareTo(riq)==0) {links.remove(link);}
				}
				return riq;
			}
		}
		return null;
	}
	
	public Link removeLink(String name) {
		for(Link link: links) {
			if(link.getName().compareTo(name)==0) {
				links.remove(link);
				getRIQ(link.getLocalRIQ().getName()).removeLink(link);
				//getRIQ(link.getRemoteRIQ().getName()).removeLink(link);
				return link;
			}
		}
		return null;
	}
	
	public ArrayList<RIQ> getRIQs() {return riqs;}
	public ArrayList<Link> getLinks() {return links;}
	
	public Link getLink(String name) {
		for(Link link : links) {
			if(link.getName().compareTo(name)==0) {return link;}
		}
		return null;
	}
	
	public Link linkBuilder(String name, RIQ local, RIQ remote, int type, String ces, String subnet) throws NoAvailableCESException {
		/*
		 * Link Constructor
		 * @param name Name
		 * @param localRIQ Local RIQ
		 * @param localRIQ Remote RIQ
		 * @param type Type of link (0 for UHF, 1 for Fiber Shot)
		 * @param localIP Local IP Address
		 * @param remoteIP Remote IP Address
		 * @param interfaceCES CES Interface
		 * @param vLAN VLAN
		 * @param subnetMask SubnetMask
		 * @param kg175d Left or Right KG for the Local RIQ (0 for left, 1 for right) (Used for Gateway IP)
		 * @param udpPort UDP port for local AND remote RIQs
		 */
		
		int kg = 0;
		int vlan = (type == 0) ? vlanScheme[riqs.indexOf(local)][riqs.indexOf(remote)] : 150;	// CHANGE THIS WHEN DEFAULT PPN VLAN CAN BE SET
		int port = (type == 0) ? portScheme[riqs.indexOf(local)][riqs.indexOf(remote)] : 50000;	// CHANGE THIS WHEN DEFAULT PPN VLAN CAN BE SET 
		
		if(local.getOpenCES() == null) {
			throw new NoAvailableCESException(local.getOpenCES() == null ? local.getName() : remote.getName());
		}
		
		if(!local.isCESOpen(ces)) {
			ces = local.getOpenCES();
		}
		
		String localIP = ipScheme[riqs.indexOf(local)][riqs.indexOf(remote)][type];
		String remoteIP = ipScheme[riqs.indexOf(remote)][riqs.indexOf(local)][type];
		return new Link(name, local, remote, type, localIP, remoteIP, ces, vlan, subnet, kg, port);
	}
	
	public Link linkBuilder(String name, RIQ local, RIQ remote, int type, String ces, String subnet, int kg) throws NoAvailableCESException {
		/*
		 * Link Constructor
		 * @param name Name
		 * @param localRIQ Local RIQ
		 * @param localRIQ Remote RIQ
		 * @param type Type of link (0 for UHF, 1 for Fiber Shot)
		 * @param localIP Local IP Address
		 * @param remoteIP Remote IP Address
		 * @param interfaceCES CES Interface
		 * @param vLAN VLAN
		 * @param subnetMask SubnetMask
		 * @param kg175d Left or Right KG for the Local RIQ (0 for left, 1 for right) (Used for Gateway IP)
		 * @param udpPort UDP port for local AND remote RIQs
		 */
		
		if(local.getOpenCES() == null) {
			throw new NoAvailableCESException(local.getOpenCES() == null ? local.getName() : remote.getName());
		}
		
		if(!local.isCESOpen(ces)) {
			ces = local.getOpenCES();
		}
		
		int vlan = (type == Link.UHF) ? vlanScheme[riqs.indexOf(local)][riqs.indexOf(remote)] : 150;	// CHANGE THIS WHEN DEFAULT PPN VLAN CAN BE SET
		int port = (type == Link.PPN) ? portScheme[riqs.indexOf(local)][riqs.indexOf(remote)] : 50000;	// CHANGE THIS WHEN DEFAULT PPN VLAN CAN BE SET 
		System.out.println(port);
		String localIP = ipScheme[riqs.indexOf(local)][riqs.indexOf(remote)][type];
		String remoteIP = ipScheme[riqs.indexOf(remote)][riqs.indexOf(local)][type];
		return new Link(name, local, remote, type, localIP, remoteIP, ces, vlan, subnet, kg, port);
	}
	
	public boolean addLink (Link link) {
		if (link == null) {return false;}
		RIQ local = riqs.get(riqs.indexOf(link.getLocalRIQ()));
		if(local != null) {local.addLink(link); return links.add(link);}
		return false;
	}
	
	public boolean exportObject(String fileName, RIQBuilder builder) {		
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(builder);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in "+fileName);
			return true;
		} catch (IOException i) {
			i.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean importObject(String fileName) {
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			RIQBuilder another = (RIQBuilder) in.readObject();
			System.out.printf("Serialized data loaded from "+fileName);
			in.close();
			fileIn.close();
			// Set this object as the loaded object
			this.ipScheme = another.ipScheme.clone();
			this.kgScheme = another.kgScheme.clone(); 
			this.vlanScheme = another.vlanScheme.clone();
			this.portScheme = another.portScheme.clone();
			this.riqs = (ArrayList<RIQ>) another.riqs.clone();
			this.links = (ArrayList<Link>) another.links.clone();
			this.kgGateway = another.kgGateway;
			this.baseVLAN = another.baseVLAN;
			this.basePPN = another.basePPN;
			this.baseUHF = another.baseUHF;
			this.baseKG = another.baseKG;
			return true;
		} catch (IOException i) {
			//i.printStackTrace();
			System.out.println("Problem boss");
		} catch (ClassNotFoundException c) {
			System.out.println("RIQBuilder Class not found");
			//c.printStackTrace();
		}
		return false;
	}
}

