package customerUI;

import dataAccessObject.BankDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class CustomerMenus {
	
	private static final Logger logger = LogManager.getLogger(CustomerMenus.class);
	//======================================================================================
	// Customer Main menu
	//======================================================================================
	
	//main menu after login success	
	public static void mainMenu() {
		logger.info("Main Menu Screen Displayed");
		try {
			System.out.println("Logged in as: " + Menus.pojo.getEmail()
							+ "\r\n"
							+ "Main Menu\r\n"
							+ "================================\r\n"
							+ "1. View Account Balance(s)\r\n"
							+ "2. View Transaction History (Open Accounts Only)\r\n"
							+ "3. Deposit Money\r\n"
							+ "4. Withdraw Money\r\n"
							+ "5. Transfer Money Between Accounts\r\n"
							+ "6. Transfer Money to outside Account\r\n"
							+ "7. Accept/Deny Reciept of Money\r\n"
							+ "8. Create New Account\r\n"
							+ "9. Close Account\r\n"
							+ "10. Exit\r\n"
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
				// deposit money
				depositMenu();
				callBackMenu();
				break;
			case 4:
				// withdraw money
				withdrawMenu();
				callBackMenu();
				break;
			case 5:
				// transfer money between accounts
				transferMoneyMenu();
				callBackMenu();
				break;
			case 6:
				// transfer to outside account
				
				callBackMenu();
				break;
			case 7:
				// approve/ deny receipt of money
				
				callBackMenu();
				break;
			case 8:
				// create new account
				newAccountMenu();
				callBackMenu();
				break;
			case 9:
				// close account
				closeAccountMenu();
				callBackMenu();
				break;
			case 10:
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
		BankDAO dao = new BankDAO();
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
		BankDAO dao = new BankDAO();
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
	public static void transferMoneyMenu() throws Exception {
		logger.info("Transfer Screen Displayed");
		withdrawMenu();
		transferDepositMenu();
	}
	
	// this module is only to be used with the transfer menu
	// this is a similar iteration of the deposit menu but instead of asking the user how much they want to deposit 
	// it simply takes the withdraw value that is inputted during the withdraw menu phase
	public static boolean transferDepositMenu() throws Exception {
		logger.info("Deposit Screen Displayed");
		BankDAO dao = new BankDAO();
		dao.printBankAccountsDB();
		System.out.println("Please enter the account number you wish to deposit to.");
		int selection = Menus.input.nextInt();
		Menus.pojo.setBankId(selection);
		Menus.pojo.setRecievingBankId(selection);
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
	
	public static void externalTransfer() throws Exception {
		BankDAO dao = new BankDAO();
		dao.printBankAccountsDB();
		System.out.println("Please enter the account number you wish to send to.");
		int bankIDSelection = Menus.input.nextInt();
		Menus.pojo.setBankId(bankIDSelection);
		Menus.pojo.setRecievingBankId(bankIDSelection);
		System.out.println("Please enter the email of the account holder you wish to send to.");
		String emailSelection = Menus.input.next();
		Menus.pojo.setRecievingEmail(emailSelection);
		Menus.pojo.setDescription("Deposit");
		System.out.println("Please enter the amount you want to send.");
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
		BankDAO dao = new BankDAO();
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
		BankDAO dao = new BankDAO();
		dao.printBankAccountsDB();
	}
	
	// sub menu of mainMenu that shows transaction menu
	public static void transactionHistory() throws Exception {
		logger.info("Transaction Screen Displayed");
		BankDAO dao = new BankDAO();
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
