import java.util.Scanner;

public class BankTester {
	
	public static void main(String[] args) {
		
		int option = 0;
		Bank bank = new Bank();
		Scanner reader = null;
		bank = Bank.initiate();
		
		while (option != 2) {
			// prompt user login or save and quit
			System.out.println("Options:");
			System.out.println("   1) Login");
			System.out.println("   2) Save and Quit");
			reader = new Scanner(System.in);
			System.out.print("   Choice: ");
			
			option = reader.nextInt();
			System.out.println("");
			
			// Main menu
			switch(option) {
				case 1:
					Bank.login(bank);
					break;
				case 2:
					Bank.writeToDB(bank);
					break;
			}	
		}
		reader.close();
	}
}

