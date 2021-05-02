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
	static BankPOJO pojo = new BankPOJO();
	static BankManager manager = new BankManager();
	
	public static void main(String[] args) {
		logger.info("Application Started");
		
		logger.info("Checking connections");
		checkConnections();
		
		// calls the application splash screen
		menuSplashScreen();
			

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
			input.close();
		}
		
		logger.info("Application Stopped");
	}
	
	// this is the start of the application menu
	public static void menuSplashScreen() {
		logger.info("Splash Screen Displayed");
		try {
			System.out.println("Greeting!"
							+ "\r\n"
							+ "Login Menu\r\n"
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
		
		// creating local variables
		String firstname;
		String lastname;
		String username;
		String pswd;
		
		// collect email
		do {
			System.out.println("Please enter your email:\r\n");
			username = input.next();
		}while(username.length() > 0);
		pojo.setEmail(username);
		
		// collect password
		do {
			System.out.println("Please enter password:\r\n");
			pswd = input.next();
		}while(pswd.length() > 0);
		pojo.setPassword(pswd);
		
		// collect first name
		do {
			System.out.println("Please enter your first name:\r\n");
			firstname = input.next();
		}while(firstname.length() > 0);
		pojo.setPassword(firstname);
		
		//collect last name
		do {
			System.out.println("Please enter your last name:\r\n");
			lastname = input.next();
		}while(lastname.length() > 0);
		pojo.setPassword(lastname);
		
		// create account
		
		// load new account menu
		newAccountMenu();
	}
	
	// login menu
	// check postgres via JDBC for valid credentials
	public static void loginMenu() {
		logger.info("Login Screen Displayed");
		String username = "";
		String pswd = "";
		do {
			System.out.println("Please enter email:\r\n");
			username = input.next();
		}while(username.length() > 0);
		
		do {
			System.out.println("Please enter password:\r\n");
			pswd = input.next();
		}while(pswd.length() > 0);
		
		pojo.setEmail(username);
		pojo.setPassword(pswd);
		
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
