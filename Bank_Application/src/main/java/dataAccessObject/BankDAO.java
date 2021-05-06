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

/**
 * persistence layer
 * 
 * @author MATTH
 *
 */
public class BankDAO {
	// setup class logger
	private static final Logger logger = LogManager.getLogger(BankDAO.class);

	// ======================================================================================
	// Registering an account
	// ======================================================================================

	public boolean accountRegistrationDB() throws Exception {
		if (addMemberAccountDB()) {
			logger.info("Member account added.");
			if (addCustomerDetailsDB()) {
				logger.info("Customer info added.");
			} else {
				// attempt delete for member account
				deleteMemberAccountDB();
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	// adds the email and password to the member account table

	public boolean addMemberAccountDB() throws Exception {
		int inserted = 0;
		logger.debug("Adding member account");
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
		pstmt.setString(1, Menus.pojo.getLastName());
		pstmt.setString(2, Menus.pojo.getFirstName());
		pstmt.setString(3, Menus.pojo.getEmail());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted customer account: " + inserted);
		return inserted != 0;
	}

	// attempts to delete member account
	// created in case second half of customer registration fails
	public boolean deleteMemberAccountDB() throws Exception {
		int deleted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "DELETE FROM member_account WHERE email = ?";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getEmail());
		deleted = pstmt.executeUpdate();
		logger.debug("deleted member account: " + deleted);
		return deleted != 0;
	}

	// ======================================================================================
	// Validating Login credentials & access rights
	// ======================================================================================

	// query an account and return false if nothing is there or the password entered doesnt match the password stored
	public boolean loginValidationDB() throws Exception {
		logger.debug("Comparing login credentials with database");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "SELECT email,password, status, member_type FROM member_account WHERE email = ?";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getEmail());
		ResultSet checked = pstmt.executeQuery();
		checked.next();
		int status = checked.getInt(3);
		Menus.pojo.setMemberType(checked.getInt(3));
		return checked.getString(2).equals(Menus.pojo.getPassword()) && status == 1;
	}

	// validate access to account
	public boolean currentBankAccountDB() throws Exception {
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "SELECT balance FROM bank_account WHERE id = ? AND member_id = ?";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, Menus.pojo.getBankId());
		pstmt.setString(2, Menus.pojo.getEmail());
		ResultSet checked = pstmt.executeQuery();
		checked.next();
		Float result = checked.getFloat(1);
		logger.debug("Completed check for current balance");
		return result != null;
	}

	// ======================================================================================
	// Account balance changes (withdraw, deposit, internal transfer)
	// ======================================================================================

	// pulls current balance
	public void currentBankAccountbalanceDB() throws Exception {
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "SELECT balance FROM bank_account WHERE id = ? AND member_id = ?";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, Menus.pojo.getBankId());
		pstmt.setString(2, Menus.pojo.getEmail());
		ResultSet checked = pstmt.executeQuery();
		checked.next();
		Float result = checked.getFloat(1);
		Menus.pojo.setBalance(result);
		logger.debug("Completed check for current balance. result was: ", result);
	}

	// deposits or withdraws from bank account
	public boolean updateBankAccountDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "UPDATE bank_account SET balance = ? WHERE id = ?";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setFloat(1, Menus.pojo.getNewBalance());
		pstmt.setInt(2, Menus.pojo.getBankId());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}

	// records withdraw in transaction table
	public boolean recordWithdrawDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "INSERT INTO transaction_history (description, amount, bank_account_id) VALUES (?,?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getDescription());
		pstmt.setFloat(2, Menus.pojo.getWithdraw());
		pstmt.setInt(3, Menus.pojo.getBankId());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}

	// records deposits in transaction table
	public boolean recordDepositDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "INSERT INTO transaction_history (description, amount, bank_account_id) VALUES (?,?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getDescription());
		pstmt.setFloat(2, Menus.pojo.getDeposit());
		pstmt.setInt(3, Menus.pojo.getBankId());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}

	// ======================================================================================
	// External Transfers
	// ======================================================================================
	
	// post external transfer
	public boolean recordExternalTransferDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "INSERT INTO money_transfers (description, amount, receiving_account, receiving_user, sending_account, member_id) VALUES (?,?,?,?,?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getDescription());
		pstmt.setFloat(2, Menus.pojo.getWithdraw());
		pstmt.setFloat(3, Menus.pojo.getRecievingBankId());
		pstmt.setString(4, Menus.pojo.getRecievingEmail());
		pstmt.setInt(5, Menus.pojo.getBankId());
		pstmt.setString(6, Menus.pojo.getEmail());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}

	// post external transfer update (approve/deny)
	public boolean updateExternalTransferDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "INSERT INTO money_transfers (description, amount, status, bank_account_id) VALUES (?,?,?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getDescription());
		pstmt.setFloat(2, Menus.pojo.getWithdraw());
		pstmt.setInt(3, Menus.pojo.getBankId());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}

	// add money from transfer page to account receiving 
	public boolean approvedExternalTransferDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "INSERT INTO money_transfers (description, amount, status, bank_account_id) VALUES (?,?,?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getDescription());
		pstmt.setFloat(2, Menus.pojo.getWithdraw());
		pstmt.setInt(3, Menus.pojo.getBankId());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}

	// post external transfer update (approve/deny)
	public boolean deniedExternalTTransferDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "INSERT INTO money_transfers (description, amount, status, bank_account_id) VALUES (?,?,?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getDescription());
		pstmt.setFloat(2, Menus.pojo.getWithdraw());
		pstmt.setInt(3, Menus.pojo.getBankId());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}

	// validate access to account and that the account matched the account holder
	public boolean checkExternalTransferDB() throws Exception {
		logger.debug("Received data to check check for receiving bank account");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "SELECT balance FROM bank_account WHERE id = ? AND member_id = ? AND status = '1'";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, Menus.pojo.getRecievingBankId());
		pstmt.setString(2, Menus.pojo.getRecievingEmail());
		ResultSet checked = pstmt.executeQuery();
		checked.next();
		Float result = checked.getFloat(1);
		logger.debug("Completed account validation");
		return result != null;
	}
		
	// ======================================================================================
	// Account management (create, close)
	// ======================================================================================

	public boolean createBankAccountDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "INSERT INTO bank_account (account_type, balance, member_id) VALUES (?,?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getAccountType());
		pstmt.setFloat(2, Menus.pojo.getDeposit());
		pstmt.setString(3, Menus.pojo.getEmail());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}

	public boolean closeBankAccountDB() throws Exception {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "UPDATE bank_account SET status = ? WHERE id = ?";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, 2);
		pstmt.setInt(2, Menus.pojo.getDeleteBankId());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted member account: " + inserted);
		return inserted != 0;
	}

	// ======================================================================================
	// Customer reports (balances, transactions)
	// ======================================================================================

	// prints balance of specific account after transaction occurs
	public void printNewBalanceDB() throws Exception {
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "SELECT id, account_type, balance FROM bank_account WHERE id = ?";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, Menus.pojo.getBankId());
		ResultSet report = pstmt.executeQuery();
		while (report.next()) {
			String id = report.getString(1);
			String type = report.getString(2);
			String balance = report.getString(3);
			System.out.println("Account: (" + id + ") " + type + " has a balance of $" + balance);
		}
	}

	// prints all bank accounts and their balances
	public void printBankAccountsDB() throws Exception {
		logger.debug("printing open bank accounts");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "SELECT id, account_type, balance FROM bank_account WHERE member_id = ? AND status = '1'";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getEmail());
		ResultSet report = pstmt.executeQuery();

		System.out.println("Account# | Account Type	 | Balance");
		System.out.println("------------------------------------------------------------------------");

		while (report.next()) {
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
		pstmt.setInt(1, Menus.pojo.getBankId());
		ResultSet report = pstmt.executeQuery();

		System.out.println("Account# | Description	 | Date		| Amount");
		System.out.println("------------------------------------------------------------------------");

		while (report.next()) {
			int id = report.getInt(1);
			String description = report.getString(2);
			String date = report.getString(3);
			float amount = report.getFloat(4);
			System.out.println(id + " | " + description + " | " + date + " | " + amount);
		}
	}

}
