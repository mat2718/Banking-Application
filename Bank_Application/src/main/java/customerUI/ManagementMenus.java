package customerUI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManagementMenus {
	
	private static final Logger logger = LogManager.getLogger(ManagementMenus.class);
	//======================================================================================
	// Customer Main menu
	//======================================================================================
	
	//main menu after login success	
	public static void mainManagementMenu() {
		logger.info("Management Main Menu Screen Displayed");
		try {
			System.out.println("Logged in as: " + Menus.pojo.getEmail()
							+ "\r\n"
							+ "Main Menu\r\n"
							+ "================================\r\n"
							+ "1. View Account Balance(s)\r\n"
							+ "2. View All Transactions (Open Accounts Only)\r\n"
							+ "3. Deposit Money\r\n"
							+ "4. Withdraw Money\r\n"
							+ "5. Transfer Money Between Accounts\r\n"
							+ "6. Create New Account\r\n"
							+ "7. Close Account\r\n"
							+ "8. Exit\r\n"
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
	
}
