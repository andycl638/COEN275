import java.util.Scanner;

public class BankTester {
	
	public static void main(String[] args) {
		
		int option = 0;
		Bank bank = new Bank();
		bank.initiate(bank);
		
		while (option != 2) {
			// prompt user login or save and quit
			System.out.println("Options:");
			System.out.println("   1) Login");
			System.out.println("   2) Save and Quit");
			Scanner reader = new Scanner(System.in);
			System.out.println("   Choice: ");
			
			option = reader.nextInt();
			
			switch(option) {
				case 1:
					Bank.login(bank);
					break;
				case 2:
					Bank.writeToDB();
					break;
			}
			
			reader.close();
		}
	}
}

