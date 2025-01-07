package Dao;

import java.util.List;

import Pojo.TransactionPojo;
import Pojo.TransferPojo;
import Pojo.UserPojo;

public interface UserDao {
	public boolean register(UserPojo userPojo);
	public boolean login(int  accNo,int pin);
	public boolean changePin(int accNo,int opin,int npin);
	public boolean c_deposit(int amt,int accNo);
	public boolean s_deposit(int amt,int accNo);
	public boolean c_withdraw(int amt,int accNo);
	public boolean s_withdraw(int amt,int accNo);
	public boolean accNoExistsOrNot(int accNo);
	public boolean accExistsOrNot_CAccTB(int accNo);
	public boolean accExistsOrNot_SAccTB(int accNo);
	public boolean ReceiveraccExists(int accNo);
	public int c_checkAmtByAccNo(int accNo);
	public int s_checkAmtByAccNo(int accNo);
	public int checkAmtByPin(int pin);
	public int checkAccNoByPin(int pin);
	public String checkAcc_type(int saccNo);
	public boolean c_transferSameBank(int saccNo,String rname,int raccNo,int ramt);
	public boolean s_transferSameBank(int saccNo,String rname,int raccNo,int ramt);
	public boolean c_transferDiffBank(int saccNo,String rname,int raccNo,int ramt);
	public boolean s_transferDiffBank(int saccNo,String rname,int raccNo,int ramt);
	public String transactionEntry(TransactionPojo transactionPojo);
	public int c_balance(int pin);
	public int s_balance(int pin);
	public List<TransactionPojo> showTransaction(String acc_type,int pin);
	

}