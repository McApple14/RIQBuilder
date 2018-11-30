package com.amadeus.RIQBuilder;

import java.util.*;

public class Main {

	public static String[][] ipScheme_UHF;
	public static String[][] ipScheme_PPN;
	public static String[][][][] kgScheme; 
	public static int[][] vlanScheme;
	public static int[][] portScheme;
	public static ArrayList<RIQ> riqs;
	public static ArrayList<Link> links;
	public static String kgGateway;
	
	/* Note:
	 * kgScheme[i][j][SIDE][PT/CT]
	 * i: row i which corresponds to the ipScheme
	 * j: col j which corresponds to the ipScheme
	 * SIDE: The side the KG is on (left=0; right=1)
	 * PT/CT: Which IP (Plain Text / Cipher Text)
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int numRIQs = 6;
		int base = 30;
		String uhfBase = "192.168.0.0";
		String ppnBase = "138.127.1.0";
		ArrayList<RIQ> riqs = new ArrayList<RIQ>();
		
		riqs.add(new RIQ("ICC"));
		riqs.add(new RIQ("Alpha"));
		riqs.add(new RIQ("Bravo"));
		riqs.add(new RIQ("Charlie"));
		riqs.add(new RIQ("Delta"));
		riqs.add(new RIQ("CRG"));
		
		//System.out.println("VLAN Base = "+base+"; numRIQs = "+numRIQs);
		
		vlanSchemeBuilder(numRIQs,base);
		for(int[] row : vlanScheme) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		
		ipSchemeBuilder(numRIQs,1,ppnBase);
		for(String[] row : ipScheme_PPN) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		
		ipSchemeBuilder(numRIQs,0,uhfBase);
		for(String[] row : ipScheme_UHF) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		
		kgSchemeBuilder("172.16.16.0");
		for(int i=0; i<riqs.size();i++) {
			System.out.print("[");
			for(int j=0; j<riqs.size();j++) {
				System.out.print(kgScheme[i][j][0][0]+",\t");
			}
			System.out.println("]");
		}
		System.out.println();
		for(int i=0; i<riqs.size();i++) {
			System.out.print("[");
			for(int j=0; j<riqs.size();j++) {
				System.out.print(kgScheme[i][j][0][1]+",\t");
			}
			System.out.println("]");
		}
		System.out.println();
		/*for(String[][][] row : kgScheme) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		for(String[][][] row : kgScheme) {
			System.out.println(Arrays.toString(row));
		}*/
		System.out.println();

		/*// NOTE: THIS CONSOLE IS FOR TESTING PURPOSES ONLY!!!
		Scanner keyboard = new Scanner(System.in);
		RIQ local;
		RIQ remote;
		String input;
		do {
			int type;
			String localip;
			String remoteip;
			String subnet;
			do {
				System.out.print("Type of shot (0 for UHF; 1 for Fiber): ");
				type = keyboard.nextInt();
				keyboard.nextLine(); //May be unnecessary
				if ((type != 0) && (type != 1)) {System.out.println("Invalid Type"); type = -1;}
			} while (type == -1);
			
			System.out.print("VLAN of link: ");
			int vlan = keyboard.nextInt();
			keyboard.nextLine();
			do {
				System.out.print("Subnet Mask for link: ");
				subnet = Link.ipValidation(keyboard.nextLine());
				if(subnet==null) {System.out.println("Invalid IP Address");}
			} while (subnet == null);
			
			System.out.println("Please enter the following information for the LOCAL RIQ");
			System.out.print("Name: ");
			String name = keyboard.nextLine();
			do {
				System.out.print("IP Address: ");
				localip = Link.ipValidation(keyboard.nextLine());
				if(localip==null) {System.out.println("Invalid IP Address");}
			} while (localip == null);
			System.out.print("UDP Port (Only applicable for Fiber Shots; default should be 50000): ");
			int localudpPort = keyboard.nextInt();
			keyboard.nextLine(); //May be unnecessary
			String cesIntLocal = null;
			do {
				System.out.print("CES Interface: ");
				cesIntLocal = Link.validateCESInterface(keyboard.nextLine());
				if (cesIntLocal == null) {System.out.println("Invalid interface");}
			} while(cesIntLocal == null);
			local = new RIQ(name);
			
			
			System.out.println("\nPlease enter the following information for the REMOTE RIQ");
			System.out.print("Name: ");
			name = keyboard.nextLine();
			remoteip = null;
			do {
				System.out.print("IP Address: ");
				remoteip = Link.ipValidation(keyboard.nextLine());
				if(remoteip==null) {System.out.println("Invalid IP Address");}
			} while (remoteip == null);
			System.out.print("UDP Port (Only applicable for Fiber Shots): ");
			int remoteudpPort = keyboard.nextInt();
			keyboard.nextLine(); //May be unnecessary
			String cesIntRemote = null;
			do {
				System.out.print("CES Interface: ");
				cesIntRemote = Link.validateCESInterface(keyboard.nextLine());
				if (cesIntRemote == null) {System.out.println("Invalid interface");}
			} while(cesIntRemote == null);
			remote = new RIQ(name);
			System.out.println();
			
			Link A = new Link("L to R", local, remote, type, localip, remoteip, cesIntLocal, vlan, subnet, 0);
			Link B = new Link("R to L", remote, local, type, remoteip, localip, cesIntLocal, vlan, subnet, 0);
			
			System.out.println("\nLink for LOCAL to REMOTE\n\t"+A);
			System.out.println("\nLink for REMOTE to LOCAL\n\t"+B);
			
			System.out.print("Again? (y/n): ");
			input = keyboard.nextLine();
		} while(input == "y");
		keyboard.close();
		*/
	}
	
	public static void vlanSchemeBuilder(int numRIQs, int base) throws IllegalArgumentException {
		vlanScheme = new int[numRIQs][numRIQs];
		int count = base;
		for(int i=0;i<numRIQs;i++) {
			for(int j=0;j<i;j++) {
				vlanScheme[i][j] = vlanScheme[j][i] = count;
				count++;
			}
		}
	}
	
	public static void ipSchemeBuilder(int numRIQs, int type, String baseIP) {
		int count;
		String base;
		
		switch(type) {
		case Link.UHF:
			ipScheme_UHF = new String[numRIQs][numRIQs];
			count = 0;
			base = 	(baseIP.split("\\."))[0]+"."+
					(baseIP.split("\\."))[1]+".";
			for(int i=0;i<numRIQs;i++) {
				for(int j=0;j<numRIQs;j++) {
					if(i==j) {ipScheme_UHF[i][j]="0.0.0.0";}
					else {
						if(count==0) {ipScheme_UHF[i][j]=(base.concat(Integer.toString(vlanScheme[i][j])).concat(".41"));}
						else {ipScheme_UHF[i][j]=(base.concat(Integer.toString(vlanScheme[i][j])).concat("."+Integer.toString(count)));}
					}
				}
				count++;
			}
			break;
		case Link.PPN:
			ipScheme_PPN = new String[numRIQs][numRIQs];
			count = 0;
			base = 	(baseIP.split("\\."))[0]+"."+
					(baseIP.split("\\."))[1]+"."+
					(baseIP.split("\\."))[2]+".";
			for(int i=0;i<numRIQs;i++) {
				for(int j=0;j<numRIQs;j++) {
					if(i==j) {ipScheme_PPN[i][j]="0.0.0.0";}
					else {
						if(count==0) {ipScheme_PPN[i][j]=base.concat("41");}
						else {ipScheme_PPN[i][j]=base.concat(Integer.toString(count));}
					}
				}
				count++;
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid Type (0 for UHF, 1 for Fiber Shot)");
		}
	}
	
	public static void kgSchemeBuilder(String baseIP) {
		if(ipScheme_PPN == null) {return;}
		int length = ipScheme_PPN[0].length;
		kgScheme = new String[length][length][2][2];
		
		
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				String[] ip = (ipScheme_PPN[i][j]).split("\\.");
				kgScheme[i][j][0][0] = ip[0]+"."+ip[1]+"."+ip[2]+"."+Integer.toString(Integer.valueOf(ip[3])+100);
				kgScheme[i][j][1][0] = ip[0]+"."+ip[1]+"."+ip[2]+"."+"2"+Integer.toString(Integer.valueOf(ip[3])+100);
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
				kgScheme[i][j][1][1]=base.concat(Integer.toString(count+ipScheme_PPN[0].length));
			}
			count++;
		}
	}
	
	public static void portSchemeBuilder() {
		portScheme = new int[riqs.size()][riqs.size()];
		int count = Link.UHF_PORT;
		for(int i=0;i<riqs.size();i++) {
			for(int j=0;j<i;j++) {
				portScheme[i][j] = portScheme[j][i] = count;
				count+=100;
			}
		}
	}
	
	//public KG175D(String name, String ptIP, String ptGW, String ctIP, String ctGW)
	public static void initialization() {
		links = new ArrayList<Link>();
		
		for(RIQ riq : riqs) {
			riq.setKGs(new KG175D[] {new KG175D(riq.getName().concat("LeftKG"),kgScheme[riqs.indexOf(riq)][riqs.indexOf(riq)][0][0],"0.0.0.0",kgScheme[riqs.indexOf(riq)][riqs.indexOf(riq)][0][1],kgGateway),
					new KG175D(riq.getName().concat("RightKG"),kgScheme[riqs.indexOf(riq)][riqs.indexOf(riq)][1][0],"0.0.0.0",kgScheme[riqs.indexOf(riq)][riqs.indexOf(riq)][1][1],kgGateway)});
		}
	}

}