package customerUI;

import dataAccessObject.BankDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class CustomerMenus {
	
	private static final Logger logger = LogManager.getLogger(CustomerMenus.class);
	static BankDAO dao = new BankDAO();
	//======================================================================================
	// Customer Main menu
	//======================================================================================
	
	//main menu after login success	
	public static void mainMenu() {
		logger.info("Main Menu Screen Displayed");
		try {
			System.out.println("Logged in as: " + Menus.pojo.getEmail()
							+ "\r\n"
							+ "\r\n"
							+ "Main Menu\r\n"
							+ "================================\r\n"
							+ "1. View Account Balance(s)\r\n"
							+ "2. View Transaction History (Open Accounts Only)\r\n"
							+ "3. Account Management (Open/ Close Accounts)\r\n"
							+ "4. Account Actions (Withdraw/ Deposit/ Transfer)\r\n"
							+ "5. Exit\r\n"
							+ "\r\n"
							+ "Please select an option from the menu above (ex. 1)");
			int selection = Menus.input.nextInt();
			switch(selection) {
			case 1:
				// view account balance
				viewBalanceMenu();
				callBackMenu();
				break;
			case 2:
				// view transaction history
				transactionHistory();
				callBackMenu();
				break;
			case 3:
				// managing your accounts menu
				accountManagementMenu();
				callBackMenu();
				break;
			case 4:
				// withdraw money
				accountActionsMenu();
				callBackMenu();
				break;
			case 5:
				// exit the application
				System.out.println("Exiting application");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				mainMenu();
			}
		} catch(Exception e) {
			logger.debug(e);
			System.out.println("Invalid option. Please try again.");
			mainMenu();
		}
	}
	
	//sub menu after login success	
	public static void accountManagementMenu() {
		logger.info("accountManagementMenu Screen Displayed");
		try {
			System.out.println("Logged in as: " + Menus.pojo.getEmail()
							+ "\r\n"
							+ "\r\n"
							+ "Account Management Menu\r\n"
							+ "================================\r\n"
							+ "1. Create New Account\r\n"
							+ "2. Close Account\r\n"
							+ "3. Main Menu\r\n"
							+ "4. Exit\r\n"
							+ "\r\n"
							+ "Please select an option from the menu above (ex. 1)");
			int selection = Menus.input.nextInt();
			switch(selection) {
			case 1:
				// create new account
				newAccountMenu();
				callBackMenu();
				break;
			case 2:
				// close account
				closeAccountMenu();
				callBackMenu();
				break;
			case 3:
				// exit the application
				mainMenu();
				break;
			case 4:
				// exit the application
				System.out.println("Exiting application");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				accountManagementMenu();
			}
		} catch(Exception e) {
			logger.debug(e);
			System.out.println("Invalid option. Please try again.");
			accountManagementMenu();
		}
	}
	
	//sub menu after login success	
	public static void accountActionsMenu() {
		logger.info("Main Menu Screen Displayed");
		try {
			System.out.println("Logged in as: " + Menus.pojo.getEmail()
							+ "\r\n"
							+ "\r\n"
							+ "Account Actions Menu\r\n"
							+ "================================\r\n"
							+ "1. Deposit Money\r\n"
							+ "2. Withdraw Money\r\n"
							+ "3. Transfer Money Between Accounts\r\n"
							+ "4. (Coming soon) Transfer Money to outside Account\r\n"
							+ "5. (Coming Soon) Accept/Deny Reciept of Money\r\n"
							+ "6. Main Menu\r\n"
							+ "7. Exit\r\n"
							+ "\r\n"
							+ "Please select an option from the menu above (ex. 1)");
			int selection = Menus.input.nextInt();
			switch(selection) {
			case 1:
				// deposit money
				depositMenu();
				callBackMenu();
				break;
			case 2:
				// withdraw money
				withdrawMenu();
				callBackMenu();
				break;
			case 3:
				// transfer money between accounts
				transferMoneyMenu();
				callBackMenu();
				break;
			case 4:
				// transfer to outside account
				externalTransfer();
				callBackMenu();
				break;
			case 5:
				// approve/ deny receipt of money
				
				callBackMenu();
				break;
			case 6:
				// approve/ deny receipt of money
				mainMenu();
				break;
			case 7:
				// exit the application
				System.out.println("Exiting application");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				mainMenu();
			}
		} catch(Exception e) {
			logger.debug(e);
			System.out.println("Invalid option. Please try again.");
			mainMenu();
		}
	}

	public static void callBackMenu() {
		logger.info("callBackMenu Screen Displayed");
		try {
			System.out.println("\r\n"
							+ "1. Return to Main Manu.\r\n"
							+ "2. Exit\r\n"
							+ "\r\n"
							+ "Please select an option from the menu above (ex. 1)");
			int selection = Menus.input.nextInt();
			switch(selection) {
			case 1:
				// launch login menu
				mainMenu();
				break;
			case 2:
				// exit the application
				System.out.println("Exiting application");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				callBackMenu();			
			}
		}catch(Exception e) {
			System.out.println("An unexpected error has occured. Please restart this application.");
			logger.error("unexpected error: ", e);
		}
	}
	
	//======================================================================================
	// Account balance menus (withdraw, deposit, transfer)
	//======================================================================================
	
	//--------------------------------------------------------------------------------------
	// sub menu of mainMenu for depositing money
	public static void depositMenu() throws Exception {
		logger.info("Deposit Screen Displayed");
		dao.printBankAccountsDB();
		System.out.println("Please enter the account number you wish to deposit to.");
		int selection = Menus.input.nextInt();
		Menus.pojo.setBankId(selection);
		Menus.pojo.setRecievingBankId(selection);
		Menus.pojo.setDescription("Deposit");
		System.out.println("Please enter the amount you want to deposit.");
		float deposit = Menus.input.nextFloat();
		if(deposit > 0) {
			Menus.pojo.setDeposit(deposit);
			Menus.manager.deposit();
			Menus.manager.recordDeposit();
			dao.printNewBalanceDB();
		}else {
			System.out.println("Invalid entry.");
			depositMenu();
		}
	}
	
	// sub menu of mainMenu for withdrawing money
	public static void withdrawMenu() throws Exception {
		logger.info("Withdraw Screen Displayed");
		dao.printBankAccountsDB();
		System.out.println("Please enter the account number you wish to withdraw from.");
		int selection = Menus.input.nextInt();
		Menus.pojo.setBankId(selection);
		Menus.pojo.setRecievingBankId(selection);
		Menus.pojo.setDescription("Withdraw");
		System.out.println("Please enter the amount you want to withdraw.");
		float withdraw = Menus.input.nextFloat();
		if(withdraw > 0) {
			Menus.pojo.setWithdraw(withdraw);
			if(Menus.manager.withdraw()) {
				Menus.manager.recordwithdraw();
				dao.printNewBalanceDB();
			}else {
				System.out.println("invalid entry.");
				withdrawMenu();
			}
		}else {
			System.out.println("Invalid entry.");
			withdrawMenu();
		}
		
	}
		
	//--------------------------------------------------------------------------------------
	// sub menu of mainMenu for transferring money between accounts
	public static void transferMoneyMenu(){
		logger.info("Transfer Screen Displayed");
		try {
		withdrawMenu();
		
			if(transferDepositMenu()) {
				logger.info("transfer completed");
			}else {
				Menus.manager.correctTransfer();
				System.out.println("Transfer failed money returned to your account. first");
			}
		}catch(Exception e) {
			try {
				Menus.manager.correctTransfer();
			} catch (Exception e1) {
				logger.debug(e1);
			}
			System.out.println("Transfer failed money returned to your account. second");
			try {
				dao.printBankAccountsDB();
			} catch (Exception e1) {
				logger.debug(e1);
			}
			logger.debug(e);
		}
	}
	
	// this module is only to be used with the transfer menu
	// this is a similar iteration of the deposit menu but instead of asking the user how much they want to deposit 
	// it simply takes the withdraw value that is inputted during the withdraw menu phase
	public static boolean transferDepositMenu() throws Exception {
		logger.info("Deposit Screen Displayed");
		dao.printBankAccountsDB();
		System.out.println("Please enter the account number you wish to deposit to.");
		int selection = Menus.input.nextInt();
		Menus.pojo.setBankId(selection);
		Menus.pojo.setRecievingBankId(selection);
		Menus.pojo.setSendingBankId(selection);
		Menus.pojo.setDescription("Deposit");
		Menus.pojo.setDeposit(Menus.pojo.getWithdraw());
		float deposit = Menus.pojo.getDeposit();
		if(deposit > 0) {
			Menus.pojo.setDeposit(deposit);
			if(Menus.manager.deposit()) {
				Menus.manager.recordDeposit();
				dao.printNewBalanceDB();
				return true;
			}else {
				return false;
			}
		}else {
			System.out.println("Invalid entry.");
			transferDepositMenu();
			return false;
		}
	}
	
	//--------------------------------------------------------------------------------------
	public static void externalTransfer() throws Exception {
		dao.printBankAccountsDB();
		System.out.println("Please enter the account number you wish to send FROM.");
		int bankIDSelection = Menus.input.nextInt();
		Menus.pojo.setBankId(bankIDSelection);
		System.out.println("Please enter the account number you wish to send TO.");
		int recievingBankId = Menus.input.nextInt();
		Menus.pojo.setRecievingBankId(recievingBankId);
		System.out.println("Please enter the email of the account holder you wish to send to.");
		String recievingEmail = Menus.input.next();
		Menus.pojo.setRecievingEmail(recievingEmail);
		Menus.pojo.setDescription("Transfer to:" + recievingEmail + " (" + recievingBankId + ") From:" + bankIDSelection);
		System.out.println("Please enter the amount you want to send.");
		float withdraw = Menus.input.nextFloat();
		if(withdraw > 0) {
			Menus.pojo.setWithdraw(withdraw);
			if(Menus.manager.externalTransfer()) {
				System.out.println("Success! Your request has been posted.\r\n");
			}else {
				System.out.println("Uh-Oh! it looks like something isnt right. Please try again.\r\n"
						+ "NOTE: verify that the information entered is correct.");
				externalTransfer();
			}
			dao.printNewBalanceDB();
		}else {
			System.out.println("Invalid entry.");
			externalTransfer();
		}
	}
	
	public static void approveDenyTransfers() {
		
	}
	
	//--------------------------------------------------------------------------------------
	
	//======================================================================================
	// Account management (create, close)
	//======================================================================================
	
	//--------------------------------------------------------------------------------------
	// sub menu of mainMenu for creating a new account in postgres
	// option to deposit money
	public static void newAccountMenu() throws Exception {
		logger.info("New Account Screen Displayed");
		System.out.println("Would you like to create a new account? (Y/N)\r\n");
		String selection = Menus.input.next();
		switch(selection.toUpperCase()) {
		case("Y"):
			// create a new account
			System.out.println("Account Types\r\n"
					+ "\r\n"
					+ "1. Checking\r\n"
					+ "2. Savings\r\n"
					+ "Select type of account:\r\n");
			int acc = Menus.input.nextInt();
			switch(acc) {
			case 1:
				// set POJO to checking
				Menus.pojo.setAccountType("Checking");
				newAccountDeposit();
				break;
			case 2:
				// set POJO to savings
				Menus.pojo.setAccountType("Savings");
				newAccountDeposit();
				break;
			default:
				System.out.println("Invalid input!");
			}
			Menus.manager.createBankAccount();
			//manager.recordDeposit();
			System.out.println("Congrats! Your new account has been added.");
			break;
		case("N"):
			//just returns them back to the main menu
			mainMenu();
			break;
		default:
			System.out.println("Invalid input. Please try again.");
			newAccountMenu();
		}
	}
	
	// deposit option upon the creation of a new account
	public static void newAccountDeposit() {
		logger.info("New Account deposit Screen Displayed");
		System.out.println("Would you like to deposit money into your new account? (Y/N)\r\n");
		String selection = Menus.input.next();
		switch(selection.toUpperCase()) {
		case("Y"):
			System.out.println("Input amount to deposit.");
			int dep = Menus.input.nextInt();
			Menus.pojo.setDeposit(dep);
			Menus.pojo.setDescription("");
			break;
		case("N"):
			// set POJO to savings
			Menus.pojo.setDeposit(0);
			System.out.println("No worries. You can always deposit money later.");
			break;
		default:
			System.out.println("Invalid input!");
			newAccountDeposit();
		}
	}
	
	//--------------------------------------------------------------------------------------
	// sub menu of mainMenu for closing an account
	public static void closeAccountMenu() throws Exception {
		logger.info("Close Account Screen Displayed");
		dao.printBankAccountsDB();
		System.out.println("Please enter the account number you wish to close. ");
		int selection = Menus.input.nextInt();
		Menus.pojo.setDeleteBankId(selection);
		// this is only used for the validation stage and is over written during the deposit selection stage
		Menus.pojo.setBankId(selection); 
		// check for account permission
		if(Menus.manager.currentBankAccount()) {
			dao.currentBankAccountbalanceDB();
			if(Menus.pojo.getBalance() > 0) {
				Menus.pojo.setWithdraw(Menus.pojo.getBalance());
				if(transferDepositMenu()) {
					Menus.manager.closeBankAccount();
				}
			}else {
				Menus.manager.closeBankAccount();
			}			
		}else {
			System.out.println("Invalid input!");
			transactionHistory();
		}
	}
	
	//======================================================================================
	// Customer reports (balances, transactions)
	//======================================================================================
	
	// sub menu of mainMenu that shows all available account balances
	public static void viewBalanceMenu() throws Exception {
		logger.info("viewBalanceMenu Screen Displayed");
		dao.printBankAccountsDB();
	}
	
	// sub menu of mainMenu that shows transaction menu
	public static void transactionHistory() throws Exception {
		logger.info("Transaction Screen Displayed");
		dao.printBankAccountsDB();
		System.out.println("Please enter the account number you wish to view the transaction history for.");
		int selection = Menus.input.nextInt();
		Menus.pojo.setBankId(selection);
		
		// check for account permission
		if(Menus.manager.currentBankAccount()) {
			dao.printTransactionsDB();
		}else {
			System.out.println("Invalid input!");
			transactionHistory();
		}
	}
		
}
