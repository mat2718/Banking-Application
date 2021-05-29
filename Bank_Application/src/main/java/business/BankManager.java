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
	
	// call upon account registration in DB
	public boolean accountRegistration() throws Exception {
		logger.debug("Received account creation request: ");
		
		return dao.accountRegistrationDB();
	}
			
	//======================================================================================
	// Validating Login credentials
	//======================================================================================
	
	// validate insert into customer table
	public boolean loginValidation(String password) throws Exception {
		logger.debug("Received login credentials sending to DB to validate request.");
		BankDAO dao = new BankDAO();
		// delegating call to DAO
		return dao.loginValidationDB(password);
	}
			
	//======================================================================================
	// Account balance changes (withdraw, deposit, transfer)
	//======================================================================================
	
	// validate update of deposit
	public boolean deposit() throws Exception {
		logger.debug("Received customer details update request: ");
		// delegating call to DAO
		if(Menus.pojo.getBankId() != Menus.pojo.getDeleteBankId()) {
			if(dao.currentBankAccountDB()) {
				dao.currentBankAccountbalanceDB();
				Menus.pojo.setNewBalance(Menus.pojo.getDeposit() + Menus.pojo.getBalance());
				return dao.updateBankAccountDB();
			}else {
				return false;
			}
		}else {
			System.out.println("ERROR: Cannot transfer to closing account");
			return false;
		}
	}	
	
	
	//
	public boolean externalTransfer() throws Exception{
		if(checkExternalTransfer()) {
			if(dao.recordExternalTransferDB()) {
				withdraw();
				dao.recordWithdrawDB();
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	// validate receiving account and member id match
	public boolean checkExternalTransfer() throws Exception{
		logger.debug("Sending request to check external account");
		
		return dao.checkExternalTransferDB();
	}
	
	
	public boolean correctTransfer() throws Exception {
		 Menus.pojo.setBankId(Menus.pojo.getRecievingBankId());
		 dao.currentBankAccountbalanceDB();
		 Menus.pojo.setNewBalance(Menus.pojo.getWithdraw() +  + Menus.pojo.getBalance());
		 return dao.updateBankAccountDB();
	 }
	
	// validate update of withdraw
	public boolean withdraw() throws Exception {
		logger.debug("Received customer details update request: ");
		// delegating call to DAO
		dao.currentBankAccountbalanceDB();
		Menus.pojo.setNewBalance(Menus.pojo.getBalance() - Menus.pojo.getWithdraw());
		if(Menus.pojo.getNewBalance() < 0) {
			logger.debug("Proposed new balance was less than zero. exiting transaction.");
			return false;
		}else {
			logger.debug("Proposed value above zero. Executing withdraw");
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
	
	// validate closure of account
	// NOTE: this only sets a status to 2
	// this is designed this way in case of a partial transaction failure or a accidental closure. 
	// however only a manager would be able to reinstate the account
	public boolean closeBankAccount() throws Exception {
		logger.debug("Received bank account closure request");
		// delegating call to DAO
		return dao.closeBankAccountDB();
	}
		
	//======================================================================================
	// Customer reports (balances, transactions)
	//======================================================================================

	//validate access to account transaction recorded
	public boolean currentBankAccount() throws Exception {
		logger.debug("validating access to current account");
		// delegating call to DAO
		return dao.currentBankAccountDB();
	}
		
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
		return dao.recordWithdrawDB();
	}

	// validate transaction recorded
	public boolean recordtransfer() throws Exception {
		logger.debug("Received bank account creation request");
		// delegating call to DAO
		return dao.recordDepositDB();
	}	
		
}
