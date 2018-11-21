package com.capg.paymentwallet.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class WalletTest {
	
	private static IAccountService service = null;

	@Before
	public  void createInstance() {
		service = new AccountServiceImpl();
	}

	@Test
	public void test() throws Exception {
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("rani"); 
		customer.setLastName("gollakoti");
        customer.setPhoneNo("8465023240");
		customer.setAddress("chennai");
		customer.setEmailId("rani@gmail.com");
		customer.setPanNum("HDH909HJH9");
	    AccountBean bean = new AccountBean();
	    bean.setBalance(2000.00);
	    bean.setDateOfOpening(new Date());
	    bean.setCustomerBean(customer);
		boolean result1=service.createAccount(bean);
		assertTrue(result1);
	
	}
	
	@Test(expected = CustomerException.class)
	public void testFirstNameForLength() throws Exception {
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("r"); 
		customer.setLastName("gollakoti");
        customer.setPhoneNo("8465023240");
		customer.setAddress("chennai");
		customer.setEmailId("rani@gmail.com");
		customer.setPanNum("HDH909HJH9");
	    AccountBean bean = new AccountBean();
	    bean.setBalance(2000.00);
	    bean.setDateOfOpening(new Date());
	    bean.setCustomerBean(customer);
		boolean result1=service.createAccount(bean);
		assertFalse(result1);
	
	}
	
	@Test(expected = CustomerException.class)
	public void testFirstNameForNumbers() throws Exception {
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("1234"); 
		customer.setLastName("gollakoti");
        customer.setPhoneNo("8465023240");
		customer.setAddress("chennai");
		customer.setEmailId("rani@gmail.com");
		customer.setPanNum("HDH909HJH9");
	    AccountBean bean = new AccountBean();
	    bean.setBalance(2000.00);
	    bean.setDateOfOpening(new Date());
	    bean.setCustomerBean(customer);
		boolean result1=service.createAccount(bean);
		assertFalse(result1);
	
	}

	@Test(expected = CustomerException.class)
	public void testLastNameForLength() throws Exception {
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("rani"); 
		customer.setLastName("go");
        customer.setPhoneNo("8465023240");
		customer.setAddress("chennai");
		customer.setEmailId("rani@gmail.com");
		customer.setPanNum("HDH909HJH9");
	    AccountBean bean = new AccountBean();
	    bean.setBalance(2000.00);
	    bean.setDateOfOpening(new Date());
	    bean.setCustomerBean(customer);
		boolean result1=service.createAccount(bean);
		assertFalse(result1);
	}
	

	@Test(expected = CustomerException.class)
	public void testLastNameForNumbers() throws Exception {
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("rani"); 
		customer.setLastName("12345");
        customer.setPhoneNo("8465023240");
		customer.setAddress("chennai");
		customer.setEmailId("rani@gmail.com");
		customer.setPanNum("HDH909HJH9");
	    AccountBean bean = new AccountBean();
	    bean.setBalance(2000.00);
	    bean.setDateOfOpening(new Date());
	    bean.setCustomerBean(customer);
		boolean result1=service.createAccount(bean);
		assertFalse(result1);
	
	}	
	
    @Test(expected = CustomerException.class)
    public void testForPhoneNumberLength() throws Exception{
    	CustomerBean customer = new CustomerBean();
		customer.setFirstName("rani"); 
		customer.setLastName("gollakoti");
        customer.setPhoneNo("846502");
		customer.setAddress("chennai");
		customer.setEmailId("rani@gmail.com");
		customer.setPanNum("HDH909HJH9");
    	AccountBean bean = new AccountBean();
	    bean.setBalance(2000.00);
	    bean.setDateOfOpening(new Date());
	    bean.setCustomerBean(customer);
         boolean result = service.createAccount(bean);
         assertFalse(result);
    }

    @Test(expected = CustomerException.class)
    public void testPhoneNumbersForAlphabets() throws Exception{
    	CustomerBean customer = new CustomerBean();
		customer.setFirstName("rani"); 
		customer.setLastName("gollakoti");
        customer.setPhoneNo("846502asda");
		customer.setAddress("chennai");
		customer.setEmailId("rani@gmail.com");
		customer.setPanNum("HDH909HJH9");
    	AccountBean bean = new AccountBean();
	    bean.setBalance(2000.00);
	    bean.setDateOfOpening(new Date());
	    bean.setCustomerBean(customer);
         boolean result = service.createAccount(bean);
         assertFalse(result);
    }

    @Test(expected = CustomerException.class)
    public void testForAddress() throws Exception{
    	CustomerBean customer = new CustomerBean();
		customer.setFirstName("rani"); 
		customer.setLastName("gollakoti");
        customer.setPhoneNo("6281660697");
		customer.setAddress(null);
		customer.setEmailId("rani@gmail.com");
		customer.setPanNum("HDH909HJH9");
    	AccountBean bean = new AccountBean();
	    bean.setBalance(2000.00);
	    bean.setDateOfOpening(new Date());
	    bean.setCustomerBean(customer);
         boolean result = service.createAccount(bean);
         assertFalse(result);
    }

    @Test(expected = CustomerException.class)
    public void testForEmail() throws Exception{
    	CustomerBean customer = new CustomerBean();
		customer.setFirstName("rani"); 
		customer.setLastName("gollakoti");
        customer.setPhoneNo("6281660697");
		customer.setAddress(null);
		customer.setEmailId("ranigmail.com");
		customer.setPanNum("HDH909HJH9");
    	AccountBean bean = new AccountBean();
	    bean.setBalance(2000.00);
	    bean.setDateOfOpening(new Date());
	    bean.setCustomerBean(customer);
         boolean result = service.createAccount(bean);
         assertFalse(result);
    }
    
    @Test(expected = CustomerException.class)
    public void testForPanLength() throws Exception{
    	CustomerBean customer = new CustomerBean();
		customer.setFirstName("rani"); 
		customer.setLastName("gollakoti");
        customer.setPhoneNo("6281660697");
		customer.setAddress("chennai");
		customer.setEmailId("rani@gmail.com");
		customer.setPanNum("HDH909");
    	AccountBean bean = new AccountBean();
	    bean.setBalance(2000.00);
	    bean.setDateOfOpening(new Date());
	    bean.setCustomerBean(customer);
         boolean result = service.createAccount(bean);
         assertFalse(result);
    }
    
    
    @Test
    public void testForDeposit() throws Exception{
   
    	AccountBean bean = new AccountBean();
    	bean.setAccountId(1);
    	
        boolean result = service.deposit(bean,200.00);
        assertTrue(result);
    }
    
    @Test
    public void testForWithDraw() throws Exception{
   
    	AccountBean bean = new AccountBean();
    	bean.setAccountId(1);
    	
        boolean result = service.deposit(bean,200.00);
        assertTrue(result);
    }
    
    @Test
    public void testForFundTransfer() throws Exception{
   
    	AccountBean bean = new AccountBean();
    	bean.setAccountId(1);
    	AccountBean bean1 = new AccountBean();
    	bean1.setAccountId(1);
    	
        boolean result = service.fundTransfer(bean, bean1, 200.00);
        assertTrue(result);
    }

    
   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
