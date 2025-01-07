package Test;

import java.util.List;
import java.util.Scanner;

import DaoImpl.UserDaoImpl;
import Pojo.TransactionPojo;
import Pojo.TransferPojo;
import Pojo.UserPojo;

public class UserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		Scanner sc=new Scanner(System.in);
		int choice=0;
		while(true)
		{
			System.out.println("===============*****=================");
			System.out.println("Welcome to ATM system");
		System.out.println("1.Generate pin  2.Login");
		choice=sc.nextInt();
		if(choice==1)
		{
			int pin=0;
			System.out.println("Enter your name: ");
			String name=sc.next();
			System.out.println("Enter Account type: \n1.current   2.saving");
			choice=sc.nextInt();
			String A_type;
			if(choice==1)
			{
				A_type="current";
			}
			else {
				A_type="saving";
			}
			System.out.println("Enter Account Number: ");
			int accNo=sc.nextInt();
			System.out.println("Set new Pin: ");
			int old_pin=sc.nextInt();
			System.out.println("Confirm pin: ");
			int new_pin=sc.nextInt();
			UserPojo userPojo=new UserPojo(accNo, A_type, name, new_pin);
			if(old_pin==new_pin)
			{
				pin=new_pin;
				if(userDaoImpl.register(userPojo))
				{
					System.out.println("registered successfully \nPlease login to access the system");
				}
				else {
					System.out.println("something went wrong \nPlease try again");
				}
			}
			else {
				System.out.println("Pin doesnot match");
			}
			
		}
		else
		{
			System.out.println("Enter your accNo: ");
			int accno=sc.nextInt();
			System.out.println("Please enter your PIN: ");
			int pin=sc.nextInt();
			if(userDaoImpl.login(accno, pin))
			{
				boolean exitInnerLoop = false;  
                while (!exitInnerLoop)
                {
                	String T_type=null,status=null,A_type=null,rec_name=null;
                	int amt=0,balance=0;
                	System.out.println("--------------++++--------------");
                	System.out.println("Please select your transaction type: ");
				System.out.println("1.Withdraw \n2.Deposit \n3.Transfer \n4.Transaction History"
						+ "\n5.Balance \n6.Change pin \n7.Quit");
				choice=sc.nextInt();
				switch(choice)
				{
				case 1:
					T_type="Withdraw";
					rec_name="self";
					System.out.println("Please select account type: ");
					System.out.println("1.current account  2.Saving account");
					choice=sc.nextInt();
					if(choice==1)
					{
						A_type="current";
						int accNo1=userDaoImpl.checkAccNoByPin(pin);
						if(userDaoImpl.accNoExistsOrNot(accNo1))
						{
							System.out.println("Enter amount: ");
							amt=sc.nextInt();
							if(userDaoImpl.c_withdraw(amt, accNo1))
							{
								System.out.println(amt+" Rs. withdraw succesfully");
								status="success";
								balance=userDaoImpl.c_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rec_name,accNo1, amt, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
							else 
							{
								System.out.println("check balance first");
								status="Fail";
								balance=userDaoImpl.c_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rec_name,accNo1, amt, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
						}
					}
					else if(choice==2)
					{
						A_type="saving";
						int accNo2=userDaoImpl.checkAccNoByPin(pin);
						if(userDaoImpl.accNoExistsOrNot(accNo2))
						{
							System.out.println("Enter amount: ");
							 amt=sc.nextInt();
							if(userDaoImpl.s_withdraw(amt, accNo2))
							{
								System.out.println(amt+" Rs. withdraw succesfully");
								status="success";
								balance=userDaoImpl.s_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rec_name,accNo2, amt, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
							else 
							{
								System.out.println("check balance first");
								status="Fail";
								balance=userDaoImpl.s_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rec_name,accNo2, amt, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
						}
					}
					else
					{
						System.out.println("Please enter correct account number");
					}
					break;
				   case 2:
					T_type="Deposit";
					rec_name="self";
					System.out.println("1.current account  2.Saving account");
					int choice1=sc.nextInt();
					if(choice1==1)
					{
						A_type="current";
						int accNo=userDaoImpl.checkAccNoByPin(pin);
						if(userDaoImpl.accNoExistsOrNot(accNo))
						{
							System.out.println("Enter amt to deposit: ");
							int amt1=sc.nextInt();
							if(userDaoImpl.c_deposit( amt1, accNo))
							{
								System.out.println("amount deposited successfully");
								status="success";
								balance=userDaoImpl.c_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rec_name,accNo, amt1, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
							else
							{
								System.out.println("Please choose correct account");
								status="Fail";
								balance=userDaoImpl.c_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rec_name,accNo, amt1, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
						}
					}
					else if(choice1==2)
					{
						A_type="saving";
						rec_name="self";
						int accNo=userDaoImpl.checkAccNoByPin(pin);
						if(userDaoImpl.accNoExistsOrNot(accNo))
						{
							System.out.println("Enter amt to deposit: ");
							int amt1=sc.nextInt();
							if(userDaoImpl.s_deposit( amt1, accNo))
							{
								System.out.println("amount deposited successfully");
								status="success";
								balance=userDaoImpl.s_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rec_name,accNo, amt1, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
							else
							{
								System.out.println("Please choose correct account");
								status="Fail";
								balance=userDaoImpl.s_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rec_name,accNo, amt1, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
						}
					}
					else
					{
						System.out.println("please enter corrent account number.");
					}
					break;
				case 3:
					T_type="transfer";
					System.out.println("1.current account  2.Saving account");
					choice=sc.nextInt();
					if(choice==1)
					{
						A_type="current";
						System.out.println("Enter receiver's name: ");
						String rac_name=sc.next();
						System.out.println("Enter receiver's account number: ");
						int recaccNo=sc.nextInt();
						System.out.println("Enter amount to send: ");
						int samt=sc.nextInt();
						int SaccNo=userDaoImpl.checkAccNoByPin(pin);
						System.out.println(SaccNo);
						if(userDaoImpl.accExistsOrNot_CAccTB(recaccNo)|| userDaoImpl.accExistsOrNot_SAccTB(recaccNo))
						{
							if(userDaoImpl.c_transferSameBank(SaccNo, rac_name, recaccNo, samt))
							{
								System.out.println("tranfer done successfully");
								status="success";
								balance=userDaoImpl.c_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rac_name,recaccNo, samt, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
							else
							{
								System.out.println(" Please check your balance first");
								status="fail";
								balance=userDaoImpl.c_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rac_name,recaccNo, samt, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
						}
						else
						{
							if(userDaoImpl.c_transferDiffBank(SaccNo, rec_name, recaccNo, samt))
							{
								System.out.println("tranfer done successfully");
								status="success";
								balance=userDaoImpl.c_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rac_name,recaccNo, samt, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
							else
							{
								System.out.println("Please check your balance first");
								status="fail";
								balance=userDaoImpl.c_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rac_name,recaccNo, samt, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
						}	
					}
					else if(choice==2)
					{
						A_type="saving";
						System.out.println("Enter receiver's name: ");
						rec_name=sc.next();
						System.out.println("Enter receiver's account number: ");
						int recaccNo=sc.nextInt();
						System.out.println("Enter amount to send: ");
						int samt=sc.nextInt();
						int SaccNo=userDaoImpl.checkAccNoByPin(pin);
						if(userDaoImpl.accExistsOrNot_CAccTB(recaccNo) || userDaoImpl.accExistsOrNot_SAccTB(recaccNo))
						{
							if(userDaoImpl.s_transferSameBank(SaccNo, rec_name, recaccNo, samt))
							{
								System.out.println("tranfer done successfully");
								status="success";
								balance=userDaoImpl.s_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rec_name,recaccNo, samt, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
							else
							{
								System.out.println("Please check your  balance first");
								status="fail";
								balance=userDaoImpl.s_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rec_name,recaccNo, samt, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
						}
						else
						{
							if(userDaoImpl.s_transferDiffBank(SaccNo, rec_name, recaccNo, samt))
							{
								System.out.println("tranfer done successfully");
								status="success";
								balance=userDaoImpl.s_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rec_name,recaccNo, samt, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
							else
							{
								System.out.println("Please check your balance first");
								status="fail";
								balance=userDaoImpl.s_balance(pin);
								TransactionPojo transactionPojo=new TransactionPojo(pin,A_type, T_type,rec_name,recaccNo, samt, balance, status);
								userDaoImpl.transactionEntry(transactionPojo);
							}
						}
					}
					
					break;
				case 4:
					System.out.println("1.current  2.saving");
					choice=sc.nextInt();
					if(choice==1)
					{
						
						List<TransactionPojo> l=userDaoImpl.showTransaction("current", pin);
						for(TransactionPojo t:l) {
							System.out.println(t);
							System.out.println("-------------------");
						}
						
					}
					else
					{
						List<TransactionPojo> l=userDaoImpl.showTransaction("saving", pin);
						for(TransactionPojo t:l) {
							System.out.println(t);
							System.out.println("-------------------");
					}
					}
					break;
				case 5:
						System.out.println("1.current 2.saving");
						choice=sc.nextInt();
						if(choice==1)
						{
							balance=userDaoImpl.checkAmtByPin(accno);
							System.out.println("Balance : "+balance);
						}
						else
						{
							balance=userDaoImpl.s_checkAmtByAccNo(accno);
							System.out.println("Balance : "+balance);
						}
					break;
				case 6:
					System.out.println("Enter account number: ");
					int caccNo=sc.nextInt();
					System.out.println("Enter old pin: ");
					int opin=sc.nextInt();
					System.out.println("Enter new pin: ");
					int npin=sc.nextInt();
					if(userDaoImpl.changePin(caccNo,opin,npin))
					{
						System.out.println("Pin change successfully");
					}
					else
					{
						System.out.println("Something went wrong \nPlease try again");
					}
					break;
				case 7:
					System.out.println("*******Thank you for banking with us :-)*******");
                    exitInnerLoop = true;  
                    break;
				}
			}
			}
			else
			{
				System.out.println("Please enter correct username and pin");
			}
		}
	}
}
}