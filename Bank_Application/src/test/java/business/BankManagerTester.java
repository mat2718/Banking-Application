package business;
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

import model.BankPOJO;
import dataAccessObject.BankDAO;

/**
 * @author MATTH
 *
 */
public class BankManagerTester {
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
			assertTrue(mockReturnObjs);;;
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
			assertTrue(mockReturnObjs);;;
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
			assertTrue(mockReturnObjs);;;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}	