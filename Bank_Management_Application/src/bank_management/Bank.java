package bank_management;
/**
 * This is the model class which store information of customer and getter and setter method 
 */
public class Bank {
	// private fields to store the account information	
	private int accNumber; // Account number
	private String userName; // Account holder's name
	private int accPin;  // Account pin
	private double balance;  // Account balance
	private String mobileNumber;  // Account holder's mobile number
	
	public Bank() {
	}
	//Constructor to initialize the account details
		public Bank(String userName, double balance, String mobileNumber,int accPin) {
			this.userName = userName;
			this.accPin = accPin;
			this.balance = balance;
			this.mobileNumber = mobileNumber;
		}
	
	//Constructor to initialize the account details
	public Bank(int accNumber, String userName, int accPin, double balance, String mobileNumber) {
		this.accNumber = accNumber;
		this.userName = userName;
		this.accPin = accPin;
		this.balance = balance;
		this.mobileNumber = mobileNumber;
	}
	//Getter and Setter for the constructor
	public int getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAccPin() {
		return accPin;
	}
	public void setAccPin(int accPin) {
		this.accPin = accPin;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "ATM [accNumber=" + accNumber + ", userName=" + userName + ", accPin=" + accPin + ", balance=" + balance
				+ ", mobileNumber=" + mobileNumber + "]";
	}
}