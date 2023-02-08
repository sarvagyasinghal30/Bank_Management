package bank_mangement_version_2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This is the implementation class for the Bank_Dao_interface which includes the method implementations
 *  for adding a customer, depositing money and checking the balance of a customer's account.
 */

public class Bank_Dao_Impl implements Bank_Dao_interface {
	// Declaring variables for JDBC connection and statements
	Connection connection;
	PreparedStatement pstm;
	String query;
	Statement stmt;
	ResultSet result;

	// This method is used to add a customer to the bank database
	@Override
	public void addCustomer(Bank atm) {
		// Establish a connection to the database
		connection = DB_Connection.connectDb();
		
		// Define the SQL query for adding a customer
		query = "insert into bank2(userName, balance, mobileNumber, accPin) value(?,?,?,?)";
		try {
			// Prepare the statement using the query
			pstm = connection.prepareStatement(query);
			
			// Set the values of the parameters in the query
			pstm.setString(1, atm.getUserName());
			pstm.setDouble(2, atm.getBalance());
			pstm.setString(3, atm.getMobileNumber());
			pstm.setInt(4, atm.getAccPin());

			// Execute the update and check if the customer was added successfully
			int count = pstm.executeUpdate();
			if (count!=0) {
				System.out.println("User added Successfully");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}



	// This method is used to deposit money into a customer's account.
	@Override
	public void depositMoney(int accNumber, double depositAmount) {
		// Establish a connection to the database
		connection = DB_Connection.connectDb();
		// Update the account balance
		query = "UPDATE bank2 SET balance = balance + ? WHERE accNumber = ?";
		try {
			// Check if the deposit amount is valid
			if (depositAmount >= 0) {
			// Prepare the statement and set the parameters
			pstm = connection.prepareStatement(query);
			pstm.setDouble(1, depositAmount);
			pstm.setInt(2, accNumber);
			// Execute the update query
			pstm.executeUpdate();
			// Retrieve the updated account balance
			query = "SELECT balance FROM bank2 WHERE accNumber = ?";
			pstm = connection.prepareStatement(query);
			pstm.setInt(1, accNumber);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				double balance = rs.getDouble("balance");
				System.out.println("Deposit successful. Current balance: " + balance);
			} 
			else 
			{
				System.out.println("Error: Account not found.");
			}
			}else {
				System.out.println("Invalid entry, please enter valid amount");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//This method is used to check the balance of a customer's account.
	@Override
	public void showbalance(int accNumber) {
		// Establish a connection to the database
		connection = DB_Connection.connectDb();
		try {
			// Check if account exists
			query = "SELECT * FROM bank2 WHERE accNumber = " + accNumber;
			stmt = connection.createStatement();
			result = stmt.executeQuery(query);
			if (!result.next()) {
				System.out.println("Error: Account not found.");
				return;
			}
			// Retrieve and display balance of account
			query = "select * from bank2 where accNumber = "+ accNumber;
			System.out.format("%s\t%s\t%s\t\t%s\t%s\n", "AccNumber", "Name" , "Balance", "Moblie_Number", "Acc_Pin"); 
			stmt = connection.createStatement();
			result = stmt.executeQuery(query);
			while (result.next()) {
				System.out.format("%d\t%s\t%.2f\t%s\t%d\n",
						result.getInt(1),
						result.getString(2),
						result.getDouble(3),
						result.getString(4),
						result.getInt(5));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	
	//This method is used to withdraw money from a customer's account.
	@Override
	public void withdrawMoney(int accNumber, double withdrawAmount) {
		// Establish a connection to the database
		connection = DB_Connection.connectDb();
		try {
			// Check if the withdraw amount is valid
			if (withdrawAmount>=0) {				
			// Select the account with the given account number
			query = "SELECT * FROM bank2 WHERE accNumber = " + accNumber;
			stmt = connection.createStatement();
			result = stmt.executeQuery(query);
			// Check if the account exists
			if (!result.next()) {
				System.out.println("Error: Account not found.");
				return;
			}
			// Check if there is enough balance
			double currentBalance = result.getDouble("balance");
			if (currentBalance < withdrawAmount) {
				System.out.println("Error: Insufficient balance.");
				return;
			}
			// Perform the withdrawal
			double newBalance = (currentBalance - withdrawAmount);
			stmt.executeUpdate("UPDATE bank2 SET balance = " + newBalance + " WHERE accNumber =" + accNumber);
			System.out.println("Withdrawal successful. Your new balance is: " + newBalance);
			}
			else {
				System.out.println("Invalid entry, please enter valid amount");
			}
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	//This method is used to delete a customer from the bank system.
	@Override
	public void deleteCustomer(int accNumber) {
		connection = DB_Connection.connectDb();
		// Establish a connection to the database
		try {
			// Check if account exists
			query = "SELECT * FROM bank2 WHERE accNumber =" + accNumber;
			stmt = connection.createStatement();
			result = stmt.executeQuery(query);
			if (!result.next()) {
				// If account does not exist, print error message and return
				System.out.println("Error: Account not found.");
				return;
				}
			// Deleting customer from the database
			query = "delete from bank2 where accNumber=?";
			pstm = connection.prepareStatement(query);
			pstm.setInt(1, accNumber);
			int count = pstm.executeUpdate();
			if (count!=0) {
				// If customer is successfully deleted, print success message
				System.out.println("The user has been successfully deleted. !!");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
