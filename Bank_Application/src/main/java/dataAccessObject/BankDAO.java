/**
 * 
 */
package dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.BankPOJO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.DBConnection;

/**
 * @author MATTH
 *
 */
public class BankDAO {
	//setup class logger
	private static final Logger logger = LogManager.getLogger(BankDAO.class);
	
	public boolean accountRegistrationDB() {
		int inserted = 0;
		logger.debug("Received data to save");
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "INSERT INTO member_account (email, password) VALUES (?,?)";
		logger.debug(sql);
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, BankPOJO);
		pstmt.setDate(2, );
		inserted = pstmt.executeUpdate();
		logger.debug("Inserted todo: " + inserted);
		return inserted != 0;
		
	}
}
