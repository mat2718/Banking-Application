package business;
import static org.junit.Assert.assertFalse;
/**
 * 
 */
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import customerUI.Menus;
import model.BankPOJO;
import dataAccessObject.BankDAO;

/**
 * @author MATTH
 *
 */
public class BankManagerTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	public void accountRegistration() {
		// create mock instance of class im receiving a return value from
		BankDAO daoMock = mock(BankDAO.class);
		boolean mockReturnObjs = true;
		try {
			when(daoMock.addMemberAccountDB()).thenReturn(mockReturnObjs);
			daoMock.addMemberAccountDB();
			assertTrue(mockReturnObjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addCustomerDetails() {
		// create mock instance of class im receiving a return value from
		BankDAO daoMock = mock(BankDAO.class);
		boolean mockReturnObjs = true;
		try {
			when(daoMock.addCustomerDetailsDB()).thenReturn(mockReturnObjs);
			daoMock.addCustomerDetailsDB();
			assertTrue(mockReturnObjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loginValidation() {
		// create mock instance of class im receiving a return value from
		BankDAO daoMock = mock(BankDAO.class);
		boolean mockReturnObjs = true;
		try {
			when(daoMock.loginValidationDB()).thenReturn(mockReturnObjs);
			daoMock.loginValidationDB();
			assertTrue(mockReturnObjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deposit() {
		BankDAO daoMock = mock(BankDAO.class);
		boolean mockReturnObjs = true;
		boolean mockNegativeReturnObj = false;
		
		try{
			when(daoMock.updateBankAccountDB()).thenReturn(mockReturnObjs);
			if(Menus.pojo.getBankId() != Menus.pojo.getDeleteBankId()) {
				daoMock.currentBankAccountbalanceDB();
				Menus.pojo.setNewBalance(Menus.pojo.getDeposit() + Menus.pojo.getBalance());
				daoMock.accountRegistrationDB();
				assertTrue(mockReturnObjs);
			}else {
				System.out.println("ERROR: Cannot transfer to closing account");
				assertFalse(mockNegativeReturnObj);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void createBankAccount() {
		// create mock instance of class im receiving a return value from
		BankDAO daoMock = mock(BankDAO.class);
		boolean mockReturnObjs = true;
		try {
			when(daoMock.createBankAccountDB()).thenReturn(mockReturnObjs);
			daoMock.createBankAccountDB();
			assertTrue(mockReturnObjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void closeBankAccount() {
		// create mock instance of class im receiving a return value from
		BankDAO daoMock = mock(BankDAO.class);
		boolean mockReturnObjs = true;
		try {
			when(daoMock.closeBankAccountDB()).thenReturn(mockReturnObjs);
			daoMock.closeBankAccountDB();
			assertTrue(mockReturnObjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void currentBankAccount() {
		// create mock instance of class im receiving a return value from
		BankDAO daoMock = mock(BankDAO.class);
		boolean mockReturnObjs = true;
		try {
			when(daoMock.currentBankAccountDB()).thenReturn(mockReturnObjs);
			daoMock.currentBankAccountDB();
			assertTrue(mockReturnObjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void recordDeposit() {
		// create mock instance of class im receiving a return value from
		BankDAO daoMock = mock(BankDAO.class);
		boolean mockReturnObjs = true;
		try {
			when(daoMock.recordDepositDB()).thenReturn(mockReturnObjs);
			daoMock.recordDepositDB();
			assertTrue(mockReturnObjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void recordwithdraw() {
		// create mock instance of class im receiving a return value from
		BankDAO daoMock = mock(BankDAO.class);
		boolean mockReturnObjs = true;
		try {
			when(daoMock.recordWithdrawDB()).thenReturn(mockReturnObjs);
			daoMock.recordWithdrawDB();
			assertTrue(mockReturnObjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void recordtransfer() {
		// create mock instance of class im receiving a return value from
		BankDAO daoMock = mock(BankDAO.class);
		boolean mockReturnObjs = true;
		try {
			when(daoMock.recordDepositDB()).thenReturn(mockReturnObjs);
			daoMock.recordDepositDB();
			assertTrue(mockReturnObjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}	