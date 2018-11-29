package com.amadeus.RIQBuilder;

import java.util.*;

public class Main {

	public static String[][] ipScheme;
	public static int[][] vlanScheme;
	public static ArrayList<RIQ> riqs;
	public static ArrayList<Link> links;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int numRIQs = 6;
		int base = 30;
		String uhfBase = "192.168.0.0";
		String ppnBase = "138.127.1.0";
		
		System.out.println("VLAN Base = "+base+"; numRIQs = "+numRIQs);
		
		vlanSchemeBuilder(numRIQs,base);
		for(int[] row : vlanScheme) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		
		ipSchemeBuilder(numRIQs,1,ppnBase);
		for(String[] row : ipScheme) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		
		ipSchemeBuilder(numRIQs,0,uhfBase);
		for(String[] row : ipScheme) {
			System.out.println(Arrays.toString(row));
		}
		
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
		ipScheme = new String[numRIQs][numRIQs];
		int count;
		String base;
		
		switch(type) {
		case Link.UHF:
			count = 0;
			base = 	(baseIP.split("\\."))[0]+"."+
					(baseIP.split("\\."))[1]+".";
			for(int i=0;i<numRIQs;i++) {
				for(int j=0;j<numRIQs;j++) {
					if(i==j) {ipScheme[i][j]="0.0.0.0";}
					else {
						if(count==0) {ipScheme[i][j]=(base.concat(Integer.toString(vlanScheme[i][j])).concat(".41"));}
						else {ipScheme[i][j]=(base.concat(Integer.toString(vlanScheme[i][j])).concat("."+Integer.toString(count)));}
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
					if(i==j) {ipScheme[i][j]="0.0.0.0";}
					else {
						if(count==0) {ipScheme[i][j]=base.concat("41");}
						else {ipScheme[i][j]=base.concat(Integer.toString(count));}
					}
				}
				count++;
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid Type (0 for UHF, 1 for Fiber Shot)");
		}
	}
	
	public static void linkBuilder() {
		links = new ArrayList<Link>();
	}

}
