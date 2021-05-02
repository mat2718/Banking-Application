/**
 * 
 */
package business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dataAccessObject.BankDAO;


/**
 * @author MATTH
 *
 */
public class BankManager {
	private static final Logger logger = LogManager.getLogger(BankManager.class);
	BankDAO dao = new BankDAO();
	
	//======================================================================================
	// Registering an account
	//======================================================================================
		
	// validate insert into account table
	public boolean accountRegistration() throws Exception {
		logger.debug("Received account creation request: ");
		// delegating call to DAO
		return dao.addMemberAccountDB();
	}
	
	// validate insert into customer table
	public boolean addCustomerDetails() throws Exception {
		logger.debug("Received customer details update request: ");
		// delegating call to DAO
		return dao.addCustomerDetailsDB();
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
	
	// validate creation of new account
		public boolean createBankAccount() throws Exception {
			logger.debug("Received bank account creation request");
			// delegating call to DAO
			return dao.createBankAccountDB();
		}
		
	//======================================================================================
	// Customer reports (balances, transactions)
	//======================================================================================
}
