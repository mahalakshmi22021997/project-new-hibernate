package com.capg.paymentwallet.service;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.dao.AccountDAOImpl;
import com.capg.paymentwallet.dao.IAccountDao;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.exception.CustomerExceptionMessage;

public class AccountServiceImpl implements IAccountService{

	IAccountDao dao=new AccountDAOImpl();
	@Override
	public boolean createAccount(AccountBean accountBean)
			throws Exception {
		boolean result = false;
		
		
		if(validateCustomer(accountBean.getCustomerBean())){
		     result=dao.createAccount(accountBean);
		}	
		
		return result;
		 
	}

	

	@Override
	public boolean deposit(AccountBean accountBean, double depositAmount)
			throws Exception {
		boolean result = false;
		try{
		if(validateBalance(depositAmount)){
		  
		    return dao.deposit(accountBean, depositAmount);
		    
		} 
		}catch(CustomerException e){
			System.out.println(e.getMessage());
		}
		
		return false;
	}

	@Override
	public boolean withdraw(AccountBean accountBean, double withdrawAmount)
			throws Exception {
		boolean result = false;
		try{
		if(validateBalance(withdrawAmount)){
		 
		 return dao.withdraw(accountBean, withdrawAmount);
		   
		} 
		}catch(CustomerException e){
			
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public boolean fundTransfer(AccountBean transferingAccountBean,
			AccountBean beneficiaryAccountBean, double transferAmount) throws Exception {
		
		return dao.fundTransfer(transferingAccountBean, beneficiaryAccountBean, transferAmount);
	}

	



	@Override
	public AccountBean findAccount(int accountId) throws Exception {
		AccountBean bean=dao.findAccount(accountId);
		return bean;
	}
	

	
	
	public boolean validateCustomer(CustomerBean customer) throws CustomerException {
		boolean isValid=true;
		
	if(!( customer.getFirstName().matches("[a-zA-Z]{3,}")))
	{
		//isValid=false;
		throw new CustomerException(CustomerExceptionMessage.ERROR1);
	}
	if(!( customer.getLastName().matches("[a-zA-Z]{3,}")))
	{
		//isValid=false;
		throw new CustomerException(CustomerExceptionMessage.ERROR1);
	}
	if((customer.getEmailId()== null || !(customer.getEmailId().matches("[a-zA-Z][a-zA-z0-9-.]*@[a-zA-Z0-9]+([.][a-zA-Z)]+)+")))){

		//isValid=false;
		throw new CustomerException(CustomerExceptionMessage.ERROR2);
	}

	if((customer.getPanNum()==null) || (!(customer.getPanNum().matches("^[A-Z][A-Z0-9]{9}")))){
		
		//isValid=false;
		throw new CustomerException(CustomerExceptionMessage.ERROR3);
	}
	if(!(customer.getPhoneNo().toString().matches("^[6-9][0-9]{9}"))){
		
		//isValid=false;
		throw new CustomerException(CustomerExceptionMessage.ERROR4);
	}
	
	if((customer.getAddress()==null))
	{
		//isValid=false;
		throw new CustomerException(CustomerExceptionMessage.ERROR5);
	}
	
	
		return isValid;
		
		
	}
	






public boolean validateBalance(double balance) throws CustomerException{
	
	boolean isValid = true; 
	
	if(balance <= 0)
	{
		isValid = false;
		throw new CustomerException(CustomerExceptionMessage.ERROR6);
	}
		
	
	
	
	return isValid;
	
}




}