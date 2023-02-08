package bank_management;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * // This is the implementation class for the Bank_Dao_interface which includes the method implementations 
 * for adding a customer, depositing money and checking the balance of a customer's account.
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
		connection = DB_Connection.connectDb();
		// Update the account balance
		query = "UPDATE bank2 SET balance = balance + ? WHERE accNumber = ?";
		try {
			pstm = connection.prepareStatement(query);
			pstm.setDouble(1, depositAmount);
			pstm.setInt(2, accNumber);
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

				System.out.println("Account not found. Please check the account number and try again.");
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
		
		// Retrieve and display balance of account
		query = "select * from bank2 where accNumber = "+ accNumber;
		System.out.format("%s\t%s\t%s\t\t%s\t%s\n", "AccNumber", "Name" , "Balance", "Moblie_Number", "Acc_Pin"); 
		try {
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
			// Select the account with the given account number
			query = "SELECT * FROM bank2 WHERE accNumber =" + accNumber;
			stmt = connection.createStatement();
			result = stmt.executeQuery(query);
			// Check if the account exists
			if (!result.next()) {
				System.out.println("Error: Account not found.");
				return;
			}
			double currentBalance = result.getDouble("balance");
			// Perform the withdrawal
			double newBalance = (currentBalance - withdrawAmount);
			stmt.executeUpdate("UPDATE bank2 SET balance = " + newBalance + " WHERE accNumber = " + accNumber);
			System.out.println("Withdrawal successful. Your new balance is: " + newBalance);
			
		}
			
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	//This method is used to delete a customer from the bank system.
	@Override
	public void deleteCustomer(int accNumber) {
		// Establish a connection to the database
		connection = DB_Connection.connectDb();
		// Deleting customer from the database
		query = "delete from bank2 where accNumber=?";
		try {
			pstm = connection.prepareStatement(query);
			pstm.setInt(1, accNumber);
			int count = pstm.executeUpdate();
			if (count!=0) {
				System.out.println("The user has been successfully deleted. !!");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

	}


}
