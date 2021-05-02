package customerUI;

import java.sql.SQLException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.BankManager;
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
		System.out.println(username.length());
		pojo.setEmail(username);
		
		// collect password
		System.out.println("Please enter password:\r\n");
		String pswd = input.next();
		pojo.setPassword(pswd);
		
		// collect first name
		System.out.println("Please enter your first name:\r\n");
		String firstname = input.next();
		pojo.setPassword(firstname);
		
		//collect last name
		System.out.println("Please enter your last name:\r\n");
		String lastname = input.next();
		pojo.setPassword(lastname);
		
		try {
			// add member account and customer details
			manager.accountRegistration();
			manager.addCustomerDetails();
			System.out.println("Congrats! Your all registered.");
		} catch (Exception e) {
			System.out.println("An unexpected error has occured.");
			//e.printStackTrace();
			logger.error("Unexpected error: ", e);
		}
		menuSplashScreen();
		
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
				System.out.println("Login Failed. Please try Again.");
				loginMenu();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
				break;
			case 2:
				// view transaction history
				transactionHistory();
				break;
			case 3:
				// deposit money
				depositMenu();
				break;
			case 4:
				// withdraw money
				withdrawelMenu();
				break;
			case 5:
				// transfer money between accounts
				transferMoneyMenu();
				break;
			case 6:
				// create new account
				newAccountMenu();
				break;
			case 7:
				// close account
				closeAccountMenu();
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
		}
	}
	
	// sub menu of mainMenu for depositing money
	public static void depositMenu() {
		logger.info("Deposit Screen Displayed");
	}
	
	// sub menu of mainMenu for withdrawing money
	public static void withdrawelMenu() {
		logger.info("Withdraw Screen Displayed");
	}
	
	// sub menu of mainMenu for transferring money between accounts
	public static void transferMoneyMenu() {
		logger.info("Transfer Screen Displayed");
	}
	
	// sub menu of mainMenu for creating a new account in postgres
	// option to deposit money
	public static void newAccountMenu() {
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
				pojo.setAccount_type("Checking");
				newAccountDeposit();
				break;
			case 2:
				// set POJO to savings
				pojo.setAccount_type("Savings");
				newAccountDeposit();
				break;
			default:
				System.out.println("Invalid input!");
			}
			
			try {
				manager.createBankAccount();
			}catch(Exception e) {
				System.out.println("An unexpected error has occured.");
				e.printStackTrace();
			}
			System.out.println("Congrats!");
			break;
		case("N"):
			//
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
			break;
		case("N"):
			// set POJO to savings
			System.out.println("No worries. You can always deposit money later.");
			break;
		default:
			System.out.println("Invalid input!");
		}
	}
	
	// sub menu of mainMenu that shows all available accounts to select and show either one or all account balances
	public static void viewBalanceMenu() {
		logger.info("Balance Screen Displayed");
	}
	
	// sub menu of mainMenu for closing an account
	public static void closeAccountMenu() {
		logger.info("Close Account Screen Displayed");
	}
	
	// sub menu of mainMenu that shows transaction menu
	public static void transactionHistory() {
		logger.info("Transaction Screen Displayed");
	}
	
	

}
