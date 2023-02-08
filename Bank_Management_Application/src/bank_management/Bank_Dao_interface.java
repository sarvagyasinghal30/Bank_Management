package bank_management;
/**
 *  This code defines an interface for bank management application
 *  The interface provides methods for adding a customer, depositing money, 
 *  checking balance, withdrawing money, and deleting a customer.
 *   All methods are declared as public and void type.
 */

public interface Bank_Dao_interface {
	
	/**
	This method is used to add a new customer to the bank system.
	@param atm an instance of the Bank class representing the new customer's account
	*/
	public void addCustomer(Bank atm);

	/**
	This method is used to deposit money into a customer's account.
	@param accNumber the account number of the customer's account
	@param depositAmount the amount of money to be deposited
	*/
	public void depositMoney(int accNumber, double depositAmount);

	/**
	This method is used to check the balance of a customer's account.
	@param accNumber the account number of the customer's account
	*/
	public void showbalance(int accNumber);

	/**
	This method is used to withdraw money from a customer's account.
	@param accNumber the account number of the customer's account
	@param withdrawAmount the amount of money to be withdrawn
	*/
	public void withdrawMoney(int accNumber, double withdrawAmount);

	/**
	This method is used to delete a customer from the bank system.
	@param accNumber the account number of the customer's account
	*/
	public void deleteCustomer(int accNumber);

}
