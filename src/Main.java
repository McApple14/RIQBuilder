import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// NOTE: THIS CONSOLE IS FOR TESTING PURPOSES ONLY!!!
		Scanner keyboard = new Scanner(System.in);
		RIQ local;
		RIQ remote;
		String input;
		do {
			System.out.println("Please enter the following information for the LOCAL RIQ");
			System.out.print("Name: ");
			String name = keyboard.nextLine();
			String ip = null;
			do {
				System.out.print("IP Address: ");
				ip = RIQ.ipValidation(keyboard.nextLine());
				if(ip==null) {System.out.println("Invalid IP Address");}
			} while (ip == null);
			System.out.print("UDP Port (Only applicable for Fiber Shots; default should be 50000): ");
			int udpPort = keyboard.nextInt();
			keyboard.nextLine(); //May be unnecessary
			String cesIntLocal = null;
			do {
				System.out.print("CES Interface: ");
				cesIntLocal = Link.validateCESInterface(keyboard.nextLine());
				if (cesIntLocal == null) {System.out.println("Invalid interface");}
			} while(cesIntLocal == null);
			local = new RIQ(name,ip,udpPort);
			
			
			System.out.println("\nPlease enter the following information for the REMOTE RIQ");
			System.out.print("Name: ");
			name = keyboard.nextLine();
			ip = null;
			do {
				System.out.print("IP Address: ");
				ip = RIQ.ipValidation(keyboard.nextLine());
				if(ip==null) {System.out.println("Invalid IP Address");}
			} while (ip == null);
			System.out.print("UDP Port (Only applicable for Fiber Shots): ");
			udpPort = keyboard.nextInt();
			keyboard.nextLine(); //May be unnecessary
			String cesIntRemote = null;
			do {
				System.out.print("CES Interface: ");
				cesIntRemote = Link.validateCESInterface(keyboard.nextLine());
				if (cesIntRemote == null) {System.out.println("Invalid interface");}
			} while(cesIntRemote == null);
			remote = new RIQ(name,ip,udpPort);
			System.out.println();
			
			int type;			
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
				ip = RIQ.ipValidation(keyboard.nextLine());
				if(ip==null) {System.out.println("Invalid IP Address");}
			} while (ip == null);
			
			Link A = new Link("L to R", local, remote, type, cesIntLocal, vlan, ip, "0.0.0.0");
			Link B = new Link("R to L", remote, local, type, cesIntRemote, vlan, ip, "0.0.0.0");
			
			System.out.println("\nLink for LOCAL to REMOTE\n\t"+A);
			System.out.println("\nLink for REMOTE to LOCAL\n\t"+B);
			
			System.out.print("Again? (y/n): ");
			input = keyboard.nextLine();
		} while(input == "y");
		keyboard.close();
	}

}
