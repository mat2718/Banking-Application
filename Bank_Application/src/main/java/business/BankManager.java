/**
 * 
 */
package business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataAccessObject.BankDAO;
import util.ConfigReader;

/**
 * @author MATTH
 *
 */
public class BankManager {
	private static final Logger logger = LogManager.getLogger(BankManager.class);
	
	public boolean accountRegistration() throws Exception {
		logger.debug("Received account creation request: ");
		// delegating call to DAO
		return BankDAO.save();
	}
}
