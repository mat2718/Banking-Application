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
	static ManagementMenus mgmt = new ManagementMenus();
	static CustomerMenus customer = new CustomerMenus();
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
			//e.printStackTrace();
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
				switch(pojo.getMemberType()) {
				case 1:
					CustomerMenus.mainMenu();
					break;
				case 2:
					ManagementMenus.mainManagementMenu(); 
					break;
				case 3:
					//admin menu goes here
					break;
				default:
					// this occurs when member type is either wrong or not created
					System.out.println("member type not defined. Please try Again.");
					loginMenu();
				}
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
	
	
	
	
	

}
