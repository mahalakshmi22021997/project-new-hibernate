package com.capg.paymentwallet.ui;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.bean.WalletTransaction;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class Client {
	   
	IAccountService service=new AccountServiceImpl();
	CustomerBean customer=new CustomerBean();
	Scanner scanner=new Scanner(System.in);
	
	
	public static void main(String[] args) throws Exception {
		char ch;
		Client client=new  Client();
		while(true)
		{
		System.out.println("========Payment wallet application========");
		System.out.println("1. Create Account ");
		System.out.println("2. Show Balance ");
		System.out.println("3. Deposit ");
		System.out.println("4. Withdraw ");
		System.out.println("5. Fund Transfer");
		System.out.println("6. Print Transactions");
		System.out.println("7. Exit");
		System.out.print("\tChoose an option\t");
		int option =client. scanner.nextInt();
		
		switch (option) {
		case 1:client.create();
               break;
		case 2:client.showbalance();

			break;

		case 3:client.deposit();

			break;
			
			
		case 4:client.withdraw();

			break;
			
	
		case 5:client.fundtransfer();

			break;
			
		
		case 6:client.printTransaction();

			break;
		case 7:System.exit(0);

			break;
			
			
		default:System.out.println("invalid option");
			break;
		}
		
		}

		
	}
	
	
	void create() throws Exception
	{
		
		System.out.print("\tEnter Customer firstname:\t");
		String fname=scanner.next();
		
		System.out.print("\tEnter Customer lastname:\t");
		String lname=scanner.next();
		
		System.out.print("\tEnter  Customer  email id:\t");
		String email=scanner.next();
		
		System.out.print("\tEnter  Customer  phone number:\t");
		String phone=scanner.next();
		
		System.out.print("\tEnter  Customer PAN number:\t");
		String pan=scanner.next();
		
		System.out.print("\tEnter  Customer  address:\t");
		String address=scanner.next();
		
		
		CustomerBean customerBean=new CustomerBean();
		customerBean.setAddress(address);
		customerBean.setEmailId(email);
		customerBean.setPanNum(pan);
		customerBean.setPhoneNo(phone);
		customerBean.setFirstName(fname);
		customerBean.setLastName(lname);
		
		
		System.out.print("\tEnter Date of Opening (DD/MM/YYYY):\t");
		String accDateInput=scanner.next();
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		Date dateOfOpeining=sdf.parse(accDateInput);
		
		
		System.out.print("\tEnter balance to create account:\t");
		double balance=scanner.nextDouble();
		
		AccountBean accountBean=new AccountBean();
		//accountBean.setAccountId(accId);
		accountBean.setBalance(balance);
		accountBean.setInitialDeposit(balance);
		accountBean.setCustomerBean(customerBean);;
		
		

		
		boolean result=service.createAccount(accountBean);
		
		if(result)
		{
			System.out.println("\n\naccount  created successfully...\n\n");
		}
		else
		{
			System.out.println("Please enter valid details..");
		}
	}
	
	
	void showbalance() throws CustomerException, Exception 
	{
		System.out.print("\tEnter Account ID\t");
		int accId=scanner.nextInt();
		
		AccountBean accountBean=service.findAccount(accId);
		
		if(accountBean==null){
			System.out.println("Account Does not exist");
			return ;
		}
		
		double balance=accountBean.getBalance();
				
		
		System.out.println("\n"+accountBean.getCustomerBean().getFirstName()+"\tYour balance is: \t" +balance);
		
			
		
	}
	
	void deposit() throws Exception
	{
		System.out.print("\tEnter Account ID\t");
		int accId=scanner.nextInt();
		
		AccountBean accountBean=service.findAccount(accId);
		if(accountBean==null){
			System.out.println("Account Does not exist");
			return ;
		}
		
		
		System.out.print("\tEnter amount that you want to deposit\t");
		double depositAmt=scanner.nextDouble();
		
		WalletTransaction wt=new WalletTransaction();
		wt.setTransactionType(1);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(depositAmt);
		wt.setBeneficiaryAccountBean(null);
		
		accountBean.addTransation(wt);
		
		
		
		boolean result=service.deposit(accountBean, depositAmt);
		
		
		if(result){
			System.out.println("\tMoney deposited successfully");
			System.out.println("\t Balance is\t"+accountBean.getBalance());
		}else{
			System.out.println("\tNOT Deposited Money into Account ");
		}
			
	}
	
	void withdraw() throws Exception
	{
		System.out.print("\tEnter Account ID\t");
		int accId=scanner.nextInt();
		
		AccountBean accountBean=service.findAccount(accId);
		
		if(accountBean==null){
			System.out.println("\tAccount Does not exist");
			return ;
		}
		
		
		
		System.out.print("\tEnter amount that you want to withdraw\t");
		double withdrawAmt=scanner.nextDouble();
		
		
		
		WalletTransaction wt=new WalletTransaction();
		wt.setTransactionType(2);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(withdrawAmt);
		wt.setBeneficiaryAccountBean(null);
		
		accountBean.addTransation(wt);
		
	
		
		boolean result=service.withdraw(accountBean, withdrawAmt);
		if(result){
			System.out.println("\tWithdaw Money from Account done\t");
			System.out.println("\t Balance is\t"+accountBean.getBalance());
		}else{
			System.out.println("\tWithdaw Money from Account -Failed ");
		}
		
	}
	
	void fundtransfer() throws Exception
	{
		System.out.print("\tEnter Account ID to Transfer Money From\t");
		int srcAccId=scanner.nextInt();
		
		AccountBean accountBean1=service.findAccount(srcAccId);
		
		
		System.out.print("\tEnter Account ID to Transfer Money to\t");
		int targetAccId=scanner.nextInt();
		
		AccountBean accountBean2=service.findAccount(targetAccId);
		
		System.out.print("\tEnter amount that you want to transfer\t");
		double transferAmt=scanner.nextDouble();
		
		WalletTransaction wt=new WalletTransaction();
		wt.setTransactionType(3);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(transferAmt);
		wt.setBeneficiaryAccountBean(accountBean2);
		
		accountBean1.addTransation(wt);
		WalletTransaction wt1=new WalletTransaction();
		wt1.setTransactionType(1);
		wt1.setTransactionDate(new Date());
		wt1.setTransactionAmt(transferAmt);
		wt1.setBeneficiaryAccountBean(accountBean1);
		
		accountBean2.addTransation(wt1);
		
		
		
		boolean result=service.fundTransfer(accountBean1, accountBean2, transferAmt);
		
		if(result){
			System.out.println("\tTransfering Money from Account done\t");
			System.out.println("\t Balance is\t"+accountBean1.getBalance());
		}else{
			System.out.println("\tTransfering Money from Account Failed\t");
		}
		
	}
	
	
	void printTransaction() throws Exception
	{
		System.out.print("\tEnter Account ID (for printing Transaction Details)\t");
		int accId=scanner.nextInt();
		
		AccountBean accountBean=service.findAccount(accId);
		
		List<WalletTransaction>  transactions=accountBean.getAllTransactions();
		
		
		System.out.println(accountBean.getCustomerBean().getFirstName());
		
		System.out.println("------------------------------------------------------------------");
		
		for(WalletTransaction wt:transactions){
			
			String str="";
			if(wt.getTransactionType()==1){
				str=str+"DEPOSIT";
			}
			if(wt.getTransactionType()==2){
				str=str+"WITHDRAW";
			}
			if(wt.getTransactionType()==3){
				str=str+"FUND TRANSFER";
			}
			
			str=str+"\t\t"+wt.getTransactionDate();
			
			str=str+"\t\t"+wt.getTransactionAmt();
			System.out.println(str);
		}
		
		System.out.println("------------------------------------------------------------------");
	
	}
	
	    
	
}
