import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	private ArrayList<BankAccount> bankAccounts;
	private final String databaseFile = "src/databasefile.csv";
	
	public Bank() {
		this.bankAccounts = new ArrayList<BankAccount>();
	}
	
	public static void login(Bank bank) {
		
		// search arraylist for matching bank account
		// if no match, return control back to banktester
		
		// once account is found, prompt user for PIN
		// user BankAccount.checkPIN for verification
		
		// greet user by their name.
		
		// after three attempts to login, return control back to BankTester
		
		System.out.println("Enter in Bank Acount Number: ");
		Scanner reader = new Scanner(System.in);
		int option = reader.nextInt();
		
		foreach (BankAccount ba : bank.bankAccounts){
			
		}
		
		System.out.println("Enter in PIN");
		Scanner reader2 = new Scanner(System.in);
		int option2 = reader.nextInt();
		
	
		switch(option) {
			case 1:
				Bank.login(bank);
				break;
			case 2:
				Bank.writeToDB();
				break;
		}
		
	}
	
	private void menu(int option) {
		// navigate the user through available actions.
		
		// use switch statement
		// if user has checking account,prompt user to enter which account to access
		// else, access savings account
		
		// ask user to deposit or withdraw
	}
	
	private void transactions(double inputAmount) {
		// positive number is deposit
		// negative number is negative
		
		// the inputAmount is added to their current balance
		
		// return to BankTEster
	}
	
	public void initiate(Bank bank) {
		// read the file from databasefile
		File file = new File(this.databaseFile);
		
		try {
			Scanner sc = new Scanner(file);
			
			while (sc.hasNextLine())
			{
				BankAccount ba = new BankAccount();
				// create the bank account objects
				String line = sc.nextLine();
				String[] values = line.split(",");
				
				// array of length 4 is savings only
				if(values.length == 4) {
					ba = new BankAccount(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Double.parseDouble(values[2]), values[3], false);
					bank.bankAccounts.add(ba);
				}
				else if (values.length == 5) {
					ba = new BankAccount(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3]), values[4], true);
					bank.bankAccounts.add(ba);
				}
				else {
					//dont add account
				}
				// store them in bankAccounts ArrayList
			}
			
			sc.close();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		
		
	}
	
	public static void writeToDB() {
		// read through bankAccounts ArrayList
		// generate a csv for each bank account 
		// write each string to the file
		// only when user selects Save and Close
	}
	
	

}
