/**
 * 
 */
package dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import customerUI.Menus;
import util.DBConnection;

/**
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
		String sql = "INSERT INTO customer_account (last_name, first_name) VALUES (?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, Menus.pojo.getLast_name());
		pstmt.setString(2, Menus.pojo.getFirst_name());
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted customer account: " + inserted);
		return inserted != 0;
	}
		
	//======================================================================================
	// Validating Login credentials
	//======================================================================================
	
	//======================================================================================
	// Account balance changes (withdraw, deposit, transfer)
	//======================================================================================
	
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
	
	//======================================================================================
	// Customer reports (balances, transactions)
	//======================================================================================
}
