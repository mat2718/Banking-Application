/**
 * 
 */
package dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import customerUI.Menus;
import util.DBConnection;

/** persistence layer
 * @author MATTH
 *
 */
public class BankDAO {
	//setup class logger
	private static final Logger logger = LogManager.getLogger(BankDAO.class);
	
	//======================================================================================
	// Registering an account
	//======================================================================================
	
	// adds the email and password to the member account table
	public boolean addMemberAccountDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "INSERT INTO member_account (email, password) VALUES (?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getEmail());
		pstmt.setString(2, Menus.pojo.getPassword());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}
	
	// adds the customers details from the registration process
	public boolean addCustomerDetailsDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "INSERT INTO customer_information (last_name, first_name, member_id) VALUES (?,?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getLast_name());
		pstmt.setString(2, Menus.pojo.getFirst_name());
		pstmt.setString(3, Menus.pojo.getEmail());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted customer account: " + inserted);
		return inserted != 0;
	}
		
	//======================================================================================
	// Validating Login credentials
	//======================================================================================
	
	
	public boolean loginValidationDB() throws Exception {
		logger.debug("comparing login credentials with database");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "SELECT email,password FROM member_account WHERE email = ?";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getEmail());
		ResultSet checked = pstmt.executeQuery();
		checked.next();
		logger.debug("Member password is: ", checked.getString(2)," Password input was: ", Menus.pojo.getPassword());
		return checked.getString(2).equals(Menus.pojo.getPassword());
	}
	
	//======================================================================================
	// Account balance changes (withdraw, deposit, transfer)
	//======================================================================================
	
	// pulls current balance
	public void currentBankAccountbalanceDB() throws Exception {
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "SELECT balance FROM bank_account WHERE id = ? AND member_id = ?";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, Menus.pojo.getBank_id());
		pstmt.setString(2, Menus.pojo.getEmail());
		ResultSet checked = pstmt.executeQuery();
		checked.next();
		Float result = checked.getFloat(1);
		Menus.pojo.setBalance(result);
		logger.debug("Completed check for current balance");
	}
	
	public boolean updateBankAccountDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "UPDATE bank_account SELECT balance = ? WHERE id = ?";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setFloat(1, Menus.pojo.getNew_balance());
		pstmt.setInt(2, Menus.pojo.getBank_id());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}
	
	public void printNewBalanceDB() throws Exception {
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "SELECT id, account_type, balance FROM bank_account WHERE id = ";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, Menus.pojo.getBank_id());
		ResultSet report = pstmt.executeQuery();
		System.out.println("Account# | Account Type	 | Balance");
		System.out.println("------------------------------------------------------------------------");
		while(report.next()) {
			String id = report.getString(1);
			String type = report.getString(2);
			String balance = report.getString(3);
			System.out.println(id + " | " + type + " | " + balance);
		}		
	}
	
	
	//======================================================================================
	// Account management (create, close)
	//======================================================================================
	
	
	public boolean createBankAccountDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "INSERT INTO bank_account (account_type, balance, email) VALUES (?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getEmail());
		pstmt.setString(2, Menus.pojo.getPassword());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}
	
	public boolean recordWithdrawDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "INSERT INTO transaction_history (description, amount, bank_account_id) VALUES (?,?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getDescription());
		pstmt.setFloat(1, Menus.pojo.getWithdraw());
		pstmt.setInt(3, Menus.pojo.getBank_id());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}
	
	public boolean recordDepositDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "INSERT INTO transaction_history (description, amount, bank_account_id) VALUES (?,?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getDescription());
		pstmt.setFloat(1, Menus.pojo.getDeposit());
		pstmt.setInt(3, Menus.pojo.getBank_id());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}
	
	public void printBankAccountsDB() throws Exception {
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "SELECT id, account_type, balance FROM bank_account WHERE member_id = ? AND status = '1'";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getEmail());
		ResultSet report = pstmt.executeQuery();
		
		System.out.println("Account# | Account Type	 | Balance");
		System.out.println("------------------------------------------------------------------------");

		while(report.next()) {
			String id = report.getString(1);
			String type = report.getString(2);
			String balance = report.getString(3);
			System.out.println(id + " | " + type + " | " + balance);
		}

	}
	
	// prints transaction history of selected open account after access is validated
	public void printTransactionsDB() throws Exception {
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "SELECT bank_account_id, description, action_date, amount FROM transaction_history WHERE bank_account_id = ?";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, Menus.pojo.getBank_id());
		ResultSet report = pstmt.executeQuery();
		
		System.out.println("Account# | Description	 | Date		| Amount");
		System.out.println("------------------------------------------------------------------------");

		while(report.next()) {
			int id = report.getInt(1);
			String description = report.getString(2);
			String date = report.getString(3);
			float amount = report.getFloat(4);
			System.out.println(id + " | " + description + " | " + date + " | " + amount);
		}
	}
	
	// validate access to account
	public boolean currentBankAccountDB() throws Exception {
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "SELECT balance FROM bank_account WHERE id = ? AND member_id = ?";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, Menus.pojo.getBank_id());
		pstmt.setString(1, Menus.pojo.getEmail());
		ResultSet checked = pstmt.executeQuery();
		checked.next();
		Float result = checked.getFloat(1);
		logger.debug("Completed check for current balance");
		return result > 0;
	}
	
}
