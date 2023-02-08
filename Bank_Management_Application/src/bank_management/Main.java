package bank_management;
import java.util.Scanner;
/**
 * This class is the main class for the bank management system.
 * It provides an interactive menu for the user to perform various operations 
 such as adding a customer, depositing money, withdrawing money, checking balance, and deleting a customer.
 */

public class Main {

	public static void main(String[] args) {
		//Object creations
		Bank_Dao_interface dao = new Bank_Dao_Impl();
		int accNumber, count= 0;
		String name;
		int accPin;
		double balance;
		String mobileNumber;
		String specialCharactersString = "!@#$%^&*()+_-[]{}|;:'<>?/,.123456789";
		// creating an object for scanner class
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Bank application");
		System.out.println("Enter Choice: ");
		int choice;
		// printing the menu for the customer
		do {
			System.out.println("1. Add customer");
			System.out.println("2. Deposit money");
			System.out.println("3. Withdraw money");
			System.out.println("4. Check balance");
			System.out.println("5. Delete customer");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			choice = input.nextInt();

			switch (choice) {
			case 1: 
				// Create a new Bank object and set its properties
				
				System.out.println("Enter Name : ");
				name = input.next();
				System.out.println("Enter the balance : ");
				balance = input.nextDouble();
				System.out.println("Enter Mobile Number : ");
				mobileNumber = input.next();
				System.out.println("Enter Account Pin : ");
				accPin = input.nextInt();
				

				// Checking the mobile number 
				if (mobileNumber.length()!=10) {
					System.out.println("Invalid Mobile Number");
					break;
				}
				
				// checking the name field
				for (int i=0; i < name.length() ; i++)
		        {
		            char ch = name.charAt(i);
		            if(specialCharactersString.contains(Character.toString(ch))) {
		                count = count + 1 ;
		                break;
		            }   
		        }  
				if (count!=0) {
					System.out.println("Invalid Customer name, Please try again");
					break;
				}

				//creating object for Bank class
				Bank atm = new Bank(name, balance, mobileNumber, accPin);

				// Add the customer to the database
				dao.addCustomer(atm);
				break;
			case 2:
				// Ask user for account number and deposit amount
				System.out.print("Enter account number: ");
				accNumber = input.nextInt();
				System.out.print("Enter deposit amount: ");
				double depositAmount = input.nextDouble();
				// Call the depositMoney method to deposit money into the account
				dao.depositMoney(accNumber, depositAmount);
				break;
			case 3:
				// Ask user for account number and withdraw amount
				System.out.print("Enter account number: ");
				accNumber = input.nextInt();
				System.out.print("Enter withdraw amount: ");
				double withdrawAmount = input.nextDouble();
				// Call the withdrawMoney method to withdraw money into the account
				dao.withdrawMoney(accNumber, withdrawAmount);
				break;
			case 4:
				// Ask user for account number.
				System.out.println("Enter Account Number to check the balance :");
				accNumber= input.nextInt();
				// Call the showbalance method to show balance.
				dao.showbalance(accNumber);
				break;
			case 5:
				//Ask user for account number
				System.out.println("Enter Account Number to delete the customer :");
				accNumber = input.nextInt();
				// Call the deleteCustomer method to delete account.
				dao.deleteCustomer(accNumber);
				break;
			case 6: 
				System.out.println("Thank you for using our service!");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;

			}
		} while (choice != 6);

		input.close();
	}

}