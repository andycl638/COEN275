
public class BankAccount {
	
	int accountNumber;
	int pinNumber;
	double savingsBalance;
	double checkingsBalance;
	String accountHolderName;
	boolean hasChecking;
	
	public BankAccount()
	{
		//empty constructor
	}
	
	// without checking
	public BankAccount(int accountNumber, int pinNumber, double savingsBalance, String accountHolderName, boolean hasChecking)
	{
		this.accountNumber = accountNumber;
		this.pinNumber = pinNumber;
		this.savingsBalance = savingsBalance;
		this.accountHolderName = accountHolderName;
		this.hasChecking = hasChecking;
	}
	
	// with checking
	public BankAccount(int accountNumber, int pinNumber, double savingsBalance, double checkingsBalance, String accountHolderName, boolean hasChecking)
	{
		this.accountNumber = accountNumber;
		this.pinNumber = pinNumber;
		this.savingsBalance = savingsBalance;
		this.checkingsBalance = checkingsBalance;
		this.accountHolderName = accountHolderName;
		this.hasChecking = hasChecking;
	}
	// get the account number
	public int getAccountNumber() {
		return this.accountNumber;
	}
	
	// get the account holder name
	public String getAccountHolderName() {
		return this.accountHolderName;
	}
	
	//get the checking balance if it has checking account
	public double getCheckingsBalance()
	{
		if (this.hasChecking)
		{
			return this.checkingsBalance;
		}
		else
		{
			throw new UnsupportedOperationException();
		}
	}
	
	// get the savings balance
	public double getSavingsBalance()
	{
		return this.savingsBalance;
	}
	
	// set the checking balance if it has a checking account
	public void setCheckingsBalance(double checkingsBalance)
	{
		if (this.hasChecking)
		{
			this.checkingsBalance = checkingsBalance;
		}
		else
		{
			throw new UnsupportedOperationException();
		}
	}
	
	// set the savings balance
	public void setSavingsBalance(double savingsBalance)
	{
		this.savingsBalance = savingsBalance;
	}
	
	// set if account has checking or not
	public void setHasChecking(boolean hasChecking)
	{
		this.hasChecking = hasChecking;
	}
	
	// determine if account has checking
	public boolean hasChecking()
	{
		return this.hasChecking;
	}
	
	// check if PIN is  correct
	public boolean checkPIN(int pinNumber)
	{
		if (this.pinNumber == pinNumber)
		{
			return true;
		}
		return false;
	}
	
	public String toString()
	{
		// example: 1,1111,1.11,
		String account = this.accountNumber+ "," + this.pinNumber + "," + this.savingsBalance;
		
		if (this.hasChecking)
		{
			// add checking balance. example: 1,1111,1.11,2.22
			account = account + "," + this.checkingsBalance ;
		}
		
		//add account holder name example 1,1111,1.11,Abe or 1,1111,1.11,2.22Abe
		account = account + "," + this.accountHolderName;
		
		return account;
	}
}
