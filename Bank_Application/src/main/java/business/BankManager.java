/**
 * 
 */
package business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import customerUI.Menus;
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
	
	// validate insert into customer table
	public boolean loginValidation() throws Exception {
		logger.debug("Received customer details update request: ");
		// delegating call to DAO
		return dao.loginValidationDB();
	}
			
	//======================================================================================
	// Account balance changes (withdraw, deposit, transfer)
	//======================================================================================
	
	// validate update of deposit
	public boolean deposit() throws Exception {
		logger.debug("Received customer details update request: ");
		// delegating call to DAO
		dao.currentBankAccountDB();
		Menus.pojo.setNew_balance(Menus.pojo.getDeposit() + Menus.pojo.getBalance());
		return dao.updateBankAccountDB();
	}	
	
	// validate update of withdraw
	public boolean withdraw() throws Exception {
		logger.debug("Received customer details update request: ");
		// delegating call to DAO
		dao.currentBankAccountDB();
		Menus.pojo.setNew_balance(Menus.pojo.getBalance() - Menus.pojo.getWithdraw());
		if(Menus.pojo.getNew_balance() < 0) {
			return false;
		}else {
			return dao.updateBankAccountDB();
		}
	}
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

	//validate transaction recorded
	public boolean recordDeposit() throws Exception {
		logger.debug("Received bank account creation request");
		// delegating call to DAO
		return dao.recordDepositDB();
	}	
	
	// validate transaction recorded
	public boolean recordwithdraw() throws Exception {
		logger.debug("Received bank account creation request");
		// delegating call to DAO
		return dao.recordDepositDB();
	}

	// validate transaction recorded
	public boolean recordtransfer() throws Exception {
		logger.debug("Received bank account creation request");
		// delegating call to DAO
		return dao.recordDepositDB();
	}	
		
}
