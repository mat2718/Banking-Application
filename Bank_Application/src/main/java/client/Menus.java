package client;

import java.sql.SQLException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.BankManager;
import dao.BankDAO;
import model.BankPOJO;
import util.ConfigReader;
import util.DBConnection;


/**
 * Presentation Layer
 * 
 * This class contains the main method for the application as well as all menus and input validation
 * for this application
 * 
 * @author MATTH
 *
 */
public class Menus {
	
	private static final Logger logger = LogManager.getLogger(Menus.class);
	
	static Scanner input = new Scanner(System.in);
	public static BankPOJO pojo = new BankPOJO();
	static BankManager manager = new BankManager();
	
	public static void main(String[] args) {
		logger.info("Application Started");
		logger.info("Checking connections");
		checkConnections();
		menuSplashScreen();
		input.close();
		logger.info("Application Stopped");	
	}
	
	//======================================================================================
	// Start up (check connection/ splash screen
	//======================================================================================
	public static void checkConnections() {
		try {
			logger.info("Getting instance of ConfigReader");
			ConfigReader.getInstance();
			logger.info("ConfigReader instance established");
			logger.info("Getting instance of DBConnection");
			DBConnection.getInstance();
			logger.info("DBConnection instance established");
		} catch (Exception e) {
			if (e instanceof SQLException) {
				System.out.println("ERROR: " + e.getLocalizedMessage());
				System.exit(0);
			}
			logger.error("Unexpected Error", e);
		} finally {
			//input.close();
		}
		
		//logger.info("Application Stopped");
	}
	
	// this is the start of the application menu
	public static void menuSplashScreen() {
		logger.info("Splash Screen Displayed");
		try {
			System.out.println("Login Menu\r\n"
							+ "================================\r\n"
							+ "1. Login\r\n"
							+ "2. Register for Account\r\n"
							+ "3. Exit\r\n"
							+ "\r\n"
							+ "Please select an option from the menu above (ex. 1)");
			int selection = input.nextInt();
			switch(selection) {
			case 1:
				// launch login menu
				loginMenu();
				break;
			case 2:
				// launch registration menu
				registrationMenu();
				break;
			case 3:
				// exit the application
				System.out.println("Exiting application");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				menuSplashScreen();			
			}
		}catch(Exception e) {
			System.out.println("An unexpected error has occured. Please restart this application.");
			logger.error("unexpected error: ", e);
		}
	}
	
	//======================================================================================
	// options from splash screen
	//======================================================================================
		
	/** registration menu
	 * pushes to login menu after registration completed
	 * if registration not complete pushes back to splash screen
	 * 
	 * includes bank account creation and starting balance
	 */
	public static void registrationMenu() {
		logger.info("Registration Screen Displayed");
				
		// collect email
		System.out.println("Please enter your email:\r\n");
		String username = input.next();
		pojo.setEmail(username);
		pojo.setDeleteMemberId(username);
		// collect password
		System.out.println("Please enter password:\r\n");
		String pswd = input.next();
		
		pojo.setPassword(pswd);
		
		// collect first name
		System.out.println("Please enter your first name:\r\n");
		String firstname = input.next();
		pojo.setFirstName(firstname);
		
		//collect last name
		System.out.println("Please enter your last name:\r\n");
		String lastname = input.next();
		pojo.setLastName(lastname);
		
		try {
			// add member account and customer details
			manager.accountRegistration();
			//manager.addCustomerDetails();
			System.out.println("Congrats! Your all registered.");
		} catch (Exception e) {
			System.out.println("An unexpected error has occured.");
			logger.error("Unexpected error: ", e);
			System.out.println("Registration failed. Please try Again.");
		}finally {
			menuSplashScreen();
		}
		
		
	}
	
	// login menu
	// check postgres via JDBC for valid credentials
	public static void loginMenu() {
		logger.info("Login Screen Displayed");
		String username;
		String pswd;
		System.out.println("Please enter your email:\r\n");
		username = input.next();
		System.out.println("Please enter your password:\r\n");
		pswd = input.next();
		pojo.setEmail(username);
		pojo.setPassword(pswd);
		
		try {
			if(manager.loginValidation()) {
				mainMenu();
			}else {
				// this will run when someone mistypes the password
				System.out.println("Login failed. Please try Again.");
				loginMenu();
			}
		} catch (Exception e) {
			// this will usually run if someone mistypes the email
			logger.error("Unexpected error: ", e);
			System.out.println("Login failed. Please try Again.");
		}finally {
			menuSplashScreen();
		}
	}
	
	//======================================================================================
	// Customer Main menu
	//======================================================================================
	
	//main menu after login success	
	public static void mainMenu() {
		logger.info("Main Menu Screen Displayed");
		try {
			System.out.println("Logged in as: " + pojo.getEmail()
							+ "\r\n"
							+ "Main Menu\r\n"
							+ "================================\r\n"
							+ "1. View Account Balance(s)\r\n"
							+ "2. View Transaction History (Open Accounts Only)\r\n"
							+ "3. Deposit Money\r\n"
							+ "4. Withdraw Money\r\n"
							+ "5. Transfer Money Between Accounts\r\n"
							+ "6. Create New Account\r\n"
							+ "7. Close Account\r\n"
							+ "8. Exit\r\n"
							+ "\r\n"
							+ "Please select an option from the menu above (ex. 1)");
			int selection = input.nextInt();
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
				// create new account
				newAccountMenu();
				callBackMenu();
				break;
			case 7:
				// close account
				closeAccountMenu();
				callBackMenu();
				break;
			case 8:
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
			int selection = input.nextInt();
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
		int selection = input.nextInt();
		pojo.setBankId(selection);
		pojo.setRecievingBankId(selection);
		pojo.setDescription("Deposit");
		System.out.println("Please enter the amount you want to deposit.");
		float deposit = input.nextFloat();
		if(deposit > 0) {
			pojo.setDeposit(deposit);
			manager.deposit();
			manager.recordDeposit();
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
		int selection = input.nextInt();
		pojo.setBankId(selection);
		pojo.setRecievingBankId(selection);
		pojo.setDescription("Withdraw");
		System.out.println("Please enter the amount you want to withdraw.");
		float withdraw = input.nextFloat();
		if(withdraw > 0) {
			pojo.setWithdraw(withdraw);
			if(manager.withdraw()) {
				manager.recordwithdraw();
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
		int selection = input.nextInt();
		pojo.setBankId(selection);
		pojo.setRecievingBankId(selection);
		pojo.setDescription("Deposit");
		pojo.setDeposit(pojo.getWithdraw());
		float deposit = pojo.getDeposit();
		if(deposit > 0) {
			pojo.setDeposit(deposit);
			if(manager.deposit()) {
				manager.recordDeposit();
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
	
	//======================================================================================
	// Account management (create, close)
	//======================================================================================
	
	//--------------------------------------------------------------------------------------
	// sub menu of mainMenu for creating a new account in postgres
	// option to deposit money
	public static void newAccountMenu() throws Exception {
		logger.info("New Account Screen Displayed");
		System.out.println("Would you like to create a new account? (Y/N)\r\n");
		String selection = input.next();
		switch(selection.toUpperCase()) {
		case("Y"):
			// create a new account
			System.out.println("Account Types\r\n"
					+ "\r\n"
					+ "1. Checking\r\n"
					+ "2. Savings\r\n"
					+ "Select type of account:\r\n");
			int acc = input.nextInt();
			switch(acc) {
			case 1:
				// set POJO to checking
				pojo.setAccountType("Checking");
				newAccountDeposit();
				break;
			case 2:
				// set POJO to savings
				pojo.setAccountType("Savings");
				newAccountDeposit();
				break;
			default:
				System.out.println("Invalid input!");
			}
			manager.createBankAccount();
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
		String selection = input.next();
		switch(selection.toUpperCase()) {
		case("Y"):
			System.out.println("Input amount to deposit.");
			int dep = input.nextInt();
			pojo.setDeposit(dep);
			pojo.setDescription("");
			break;
		case("N"):
			// set POJO to savings
			pojo.setDeposit(0);
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
		int selection = input.nextInt();
		pojo.setDeleteBankId(selection);
		// this is only used for the validation stage and is over written during the deposit selection stage
		pojo.setBankId(selection); 
		// check for account permission
		if(manager.currentBankAccount()) {
			dao.currentBankAccountbalanceDB();
			if(pojo.getBalance() > 0) {
				pojo.setWithdraw(pojo.getBalance());
				if(transferDepositMenu()) {
					manager.closeBankAccount();
				}
			}else {
				manager.closeBankAccount();
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
		int selection = input.nextInt();
		pojo.setBankId(selection);
		
		// check for account permission
		if(manager.currentBankAccount()) {
			dao.printTransactionsDB();
		}else {
			System.out.println("Invalid input!");
			transactionHistory();
		}
	}

}
