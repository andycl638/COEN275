import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	private ArrayList<BankAccount> bankAccounts;
	private static final String databaseFile = "src/databasefile.csv";
	
	public Bank() {
		this.bankAccounts = new ArrayList<BankAccount>();
	}
	
	@SuppressWarnings("resource")
	public static void login(Bank bank) {
		
		boolean loginSuccess = false;
		boolean accountFound = false;
		int inputBa = 0;
		Scanner reader = null;
		BankAccount currentAccount = new BankAccount();
		
		System.out.print("Enter in Bank Acount Number: ");
		reader = new Scanner(System.in);
		inputBa = reader.nextInt();
		System.out.println("");
		// search arraylist for matching bank account
		// if no match, return control back to banktester
		for (BankAccount ba : bank.bankAccounts){
			
			System.out.println("Searching for bank account...");
			// check if your account is found.
			if (inputBa == ba.getAccountNumber()) {
				System.out.println("Found your account: " +  ba.getAccountNumber());
				System.out.println("");
				currentAccount = ba;
				accountFound = true;
				break;
			}
		}
		
		int chance = 0;
		// once account is found, prompt user for PIN
		// user BankAccount.checkPIN for verification
		// after three attempts to login, return control back to BankTester
		if (accountFound)
		{
			while (chance < 3 && !loginSuccess) {
				System.out.print("Enter in PIN: ");
				reader = new Scanner(System.in);
				int inputPIN = reader.nextInt();
				if (currentAccount.checkPIN(inputPIN)) {
					System.out.println("Correct PIN!\n");
					loginSuccess = true;
				}
				else {
					System.out.println("Wrong pin!");
					chance++;
					System.out.println("This is try " + chance + " out of 3.\n");
				}
			}
		}
		else 
			System.out.println("Did not find your bank account.\n");

		if(loginSuccess)
			menu(currentAccount);
	}
	
	private static void menu(BankAccount ba) {
		int input = 0;
		Scanner reader = null;
		// greet user by their name.
		System.out.println("Hello " + ba.getAccountHolderName() + ", which account would you like to access:");
		
		// navigate the user through available actions.
		// if user has checking account,prompt user to enter which account to access
		if (ba.hasChecking()) {
			System.out.println("   1) Checking");
			System.out.println("   2) Savings");
			
			reader = new Scanner(System.in);
			System.out.print("   Choice: ");
			
			input = reader.nextInt();
			System.out.println("");
		}
		else {
			input = 2;
		}
		
		// ask user to deposit or withdraw
		switch(input) {
		case 1:
			//checking
			System.out.println("Your Savings account has a balance of $" + ba.getCheckingsBalance());
			System.out.println("How would you like to proceed?");
			System.out.println("(Enter in positive number to deposit, negative number to withdraw)");
			System.out.print("Amount: ");
			
			reader = new Scanner(System.in);
			
			double checkingTotal = transactions(ba.getCheckingsBalance(), reader.nextDouble());
			System.out.println("");
			
			ba.setCheckingsBalance(checkingTotal);
			System.out.println("Thank you, your new Checkings balance is $" + ba.getCheckingsBalance());
			System.out.println("Returning to login screen.");
			break;
		case 2:
			//savings
			System.out.println("Your Savings account has a balance of $" + ba.getSavingsBalance());
			System.out.println("How would you like to proceed?");
			System.out.println("(Enter in positive number to deposit, negative number to withdraw)");
			System.out.print("Amount: ");
			
			reader = new Scanner(System.in);
			
			double savingTotal = transactions(ba.getSavingsBalance(), reader.nextDouble());
			System.out.println("");
			
			ba.setSavingsBalance(savingTotal);
			System.out.println("Thank you, your new Savings balance is $" + ba.getSavingsBalance());
			System.out.println("Returning to login screen.\n");
			
			break;
			}
		
	}
	
	private static double transactions(double currentAmount, double inputAmount) {
		double total = 0;
		
		// the inputAmount is added to their current balance
		//total = Math.round(currentAmount + inputAmount);
		total = Double.valueOf(new DecimalFormat("#.##").format(currentAmount+inputAmount));
		return total;
	}
	
	public static Bank initiate() {
		// read the file from databasefile
		File file = new File(databaseFile);
		Bank bank = new Bank();
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
					System.out.println("Could not add bank account: " + ba.toString());
				}
			}
			
			sc.close();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return bank;
		
	}
	
	public static void writeToDB(Bank bank) {
		try {
			File file = new File(databaseFile);
			FileWriter fileWriter = new FileWriter(file);
			
			// read through bankAccounts ArrayList
			for (BankAccount ba : bank.bankAccounts){
				//System.out.println(ba.toString());
				// write each string to the file
				fileWriter.write(ba.toString()+"\n");
			}
		
			fileWriter.flush();
			fileWriter.close();
			System.out.print("Thank you for using our bank services. Bye!\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
