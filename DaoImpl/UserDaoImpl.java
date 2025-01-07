package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Dao.UserDao;
import Helper.Connect;
import Pojo.TransactionPojo;
import Pojo.TransferPojo;
import Pojo.UserPojo;

public class UserDaoImpl implements UserDao{
	Connection con=Connect.getConnection();
	PreparedStatement ps;

	@Override
	public boolean register(UserPojo userPojo) {
		// TODO Auto-generated method stub
		try {
			String q="insert into bank(name,accNo,pin,A_type)values(?,?,?,?)";
			ps=con.prepareStatement(q);
			ps.setString(1,userPojo.getName());
			ps.setInt(2,userPojo.getAccNo());
			ps.setInt(3,userPojo.getPin());
			ps.setString(4, userPojo.getA_type());
			int x=ps.executeUpdate();
			if(x>0) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean login(int accNo, int pin) {
		try 
		{
			String q="select*from bank where accNo=? and pin=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, accNo);
			ps.setInt(2, pin);
			ResultSet rt=ps.executeQuery();
			if(rt.next())
			{
				return true;		
			}
			else
			{
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean changePin(int accNo,int opin,int npin) {
		try {
			String q="select*from bank where accNo=? and pin=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, accNo);
			ps.setInt(2, opin);
			ResultSet rt=ps.executeQuery();
			if(rt.next())
			{
				String q1="update bank set pin=? where accNo=?";
				ps=con.prepareStatement(q1);
				ps.setInt(1, npin);
				ps.setInt(2, accNo);
				int x=ps.executeUpdate();
				if(x>0)
				{
					return true;		
				}
				else
				{
					return false;
				}	
			}
			else
			{
				return false;
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
			return false;
	}
	@Override
	public boolean c_withdraw(int amt,int accNo) {
		int amt1=c_checkAmtByAccNo(accNo);
		if(amt1>=amt) {
			try {
				String q="update current set amt=? where accNo=?";
				ps=con.prepareStatement(q);
				ps.setInt(1, amt1-amt);
				ps.setInt(2, accNo);
				int x=ps.executeUpdate();
				if(x>0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
	}
	@Override
	public boolean s_withdraw(int amt,int accNo) {
		int amt1=s_checkAmtByAccNo(accNo);
		if(amt1>=amt) {
			try {
				String q="update saving set amt=? where accNo=?";
				ps=con.prepareStatement(q);
				ps.setInt(1, amt1-amt);
				ps.setInt(2, accNo);
				int x=ps.executeUpdate();
				if(x>0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
	}
	@Override
	public boolean c_deposit(int amt, int accNo) {
		int amount=c_checkAmtByAccNo(accNo);
		UserPojo userPojo=getAllDetailsByAccno(accNo);
		try {
			if(accExistsOrNot_CAccTB(accNo))
			{
				System.out.println("updated...");
				String q="update current set amt=? where accNo=?";
				ps=con.prepareStatement(q);
				ps.setInt(1, amount+amt);
				ps.setInt(2, accNo);
				int x=ps.executeUpdate();
				if(x>0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else if(checkAcc_type(accNo).equals("current"))
			{
				String q="insert into current(name,amt,accNo) values(?,?,?)";
				ps=con.prepareStatement(q);
				ps.setString(1, userPojo.getName());
				ps.setInt(2, amt);
				ps.setInt(3, accNo);
				int x=ps.executeUpdate();
				if(x>0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean s_deposit(int amt, int accNo) {
		int amount=s_checkAmtByAccNo(accNo);
		UserPojo userPojo=getAllDetailsByAccno(accNo);
		try {
			if(accExistsOrNot_SAccTB(accNo))
			{
				String q="update saving set amt=? where accNo=?";
				ps=con.prepareStatement(q);
				ps.setInt(1, amount+amt);
				ps.setInt(2, accNo);
				int x=ps.executeUpdate();
				if(x>0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else if(checkAcc_type(accNo).equals("saving"))
			{
				String q="insert into saving(name,amt,accNo) values(?,?,?)";
				ps=con.prepareStatement(q);
				ps.setString(1, userPojo.getName());
				ps.setInt(2, amt);
				ps.setInt(3, accNo);
				int x=ps.executeUpdate();
				if(x>0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean accNoExistsOrNot(int accNo) {
		try {
			String q="select*from bank where accNo=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, accNo);
			ResultSet rt=ps.executeQuery();
			if(rt.next())
			{
				return true;
			}
			else
				return false;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean accExistsOrNot_CAccTB(int accNo) {//current account table
		try {
			String q="select*from current where accNo=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, accNo);
			ResultSet rt=ps.executeQuery();
			if(rt.next())
			{
				return true;
			}
			else
				return false;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	}
	@Override
	public boolean accExistsOrNot_SAccTB(int accNo) {//saving account table
		try {
			String q="select*from saving where accNo=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, accNo);
			ResultSet rt=ps.executeQuery();
			if(rt.next())
			{
				return true;
			}
			else
				return false;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	}
	@Override
	public int c_checkAmtByAccNo(int accNo) {
		int amt=0;
		try {
			String q="select amt from current where accNo=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, accNo);
			ResultSet rt=ps.executeQuery();
			if(rt.next())
			{
				amt=rt.getInt("amt");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return amt;
	}
	@Override
	public int s_checkAmtByAccNo(int accNo) {
		int amt=0;
		try {
			String q="select amt from saving where accNo=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, accNo);
			ResultSet rt=ps.executeQuery();
			if(rt.next())
			{
				amt=rt.getInt("amt");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return amt;
	}
	@Override
	public int checkAmtByPin(int pin) {
		int amt=0;
		try {
			String q="select amt from current where accNo=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, pin);
			ResultSet rt=ps.executeQuery();
			if(rt.next())
			{
				amt=rt.getInt("amt");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return amt;
	}
	@Override
	public int checkAccNoByPin(int pin) {
		int accNo=0;
		try {
			String q="select accNo from bank where pin=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, pin);
			ResultSet rt=ps.executeQuery();
			if(rt.next())
			{
				accNo=rt.getInt("accNo");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return accNo;
	}
	@Override
	public boolean ReceiveraccExists(int accNo) {
		try {
			String q="Select*from transfer where accNo=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, accNo);
			ResultSet rt=ps.executeQuery();
			if(rt.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean c_transferSameBank(int saccNo, String rname, int raccNo, int ramt) {
		int amt=c_checkAmtByAccNo(saccNo);
		int racmt=c_checkAmtByAccNo(raccNo);
		String acc_type=checkAcc_type(saccNo);
		if(amt>=ramt && acc_type.equals("current"))
		{
			try {
				String q="update current set amt=? where accNo=?";
				ps=con.prepareStatement(q);
				ps.setInt(1, amt-ramt);
				ps.setInt(2, saccNo);
				int x=ps.executeUpdate();
				String q1="update current set amt=? where accNo=?";
				PreparedStatement ps1;
				ps1=con.prepareStatement(q1);
				ps1.setInt(1, racmt+ramt);
				ps1.setInt(2, raccNo);
				int x1=ps1.executeUpdate();
				if(x>0 && x1>0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return false;
	}
	@Override
	public boolean c_transferDiffBank(int saccNo, String rname, int raccNo, int ramt) {
		int amt=c_checkAmtByAccNo(saccNo);
		if(amt>=ramt)
		{
			try {
				String q="update current set amt=? where accNo=?";
				ps=con.prepareStatement(q);
				ps.setInt(1, amt-ramt);
				ps.setInt(2, saccNo);
				int x=ps.executeUpdate();
				
				if(x>0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return false;
	}
	@Override
	public boolean s_transferSameBank(int saccNo, String rname, int raccNo, int ramt) {
		int amt=s_checkAmtByAccNo(saccNo);
		int racmt=s_checkAmtByAccNo(raccNo);
		String acc_type=checkAcc_type(saccNo);
		if(amt>=ramt && acc_type.equals("saving"))
		try {
			String q="update saving set amt=? where accNo=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, amt-ramt);
			ps.setInt(2, saccNo);
			int x=ps.executeUpdate();
			String q1="update saving set amt=? where accNo=?";
			PreparedStatement ps1;
			ps1=con.prepareStatement(q1);
			ps1.setInt(1, racmt+ramt);
			ps1.setInt(2, raccNo);
			int x1=ps1.executeUpdate();
			if(x>0 && x1>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean s_transferDiffBank(int saccNo, String rname, int raccNo, int ramt) {
		int amt=s_checkAmtByAccNo(saccNo);
		if(amt>ramt)
		{
			try {
				String q="update saving set amt=? where accNo=?";
				ps=con.prepareStatement(q);
				ps.setInt(1, amt-ramt);
				ps.setInt(2, saccNo);
				int x=ps.executeUpdate();
				
				if(x>0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return false;
	}
	@Override
	public String transactionEntry(TransactionPojo transactionPojo) {
		String ans=null;
		try {
			
			String q="insert into transaction(pin,account_type,transaction_type,receiver_accNo,receiver_name,amt,balance,status)values(?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(q);
			ps.setInt(1, transactionPojo.getPin());
			ps.setString(2, transactionPojo.getA_type());
			ps.setString(3, transactionPojo.getT_type());
			ps.setInt(4, transactionPojo.getRaccNo());
			ps.setString(5, transactionPojo.getRec_name());
			ps.setInt(6, transactionPojo.getAmt());
			ps.setInt(7, transactionPojo.getBalance());
			ps.setString(8, transactionPojo.getStatus());
			int x=ps.executeUpdate();
			if(x>0)
			{
				ans="yes";
			}
			else
			{
				ans="no";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return ans;
	}
	public String checkAccTypeByAccNo(int accNo) {
		String acctype=null;
		try {
			String q="select A_type from bank where accNo=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, accNo);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				acctype=rs.getString("A_type");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return acctype;
	}
	@Override
	public int c_balance(int accNo) {
		int amt=0;
		String atype=checkAcc_type(accNo);
		try {
		if(atype.equals("current")) {
			String q="select amt from current where accNo=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, accNo);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				amt=rs.getInt("amt");
			}
		}
		else {
			String q="select amt from saving where accNo=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, accNo);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				amt=rs.getInt("amt");
			}
		}
		}
		
//		int accNo=checkAccNoByPin(pin);
//		int amt=c_checkAmtByAccNo(accNo);
		 catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return amt;
	}
	@Override
	public int s_balance(int pin) {
		int accNo=checkAccNoByPin(pin);
		int amt=s_checkAmtByAccNo(accNo);
		return amt;
	}
	@Override
	public List<TransactionPojo> showTransaction(String acc_type,int pin) {
		List<TransactionPojo> transaction=new ArrayList<>();
		try
		{
			String q="select * from transaction where account_type=? && pin=?";
			ps=con.prepareStatement(q);
			ps.setString(1, acc_type);
			ps.setInt(2, pin);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				TransactionPojo transactionPojo=new TransactionPojo();
				transactionPojo.setPin(rs.getInt("pin"));
				transactionPojo.setA_type(rs.getString("account_type"));
				transactionPojo.setT_type(rs.getString("transaction_type"));
				transactionPojo.setRec_name(rs.getString("receiver_name"));
				transactionPojo.setRaccNo(rs.getInt("receiver_accNo"));
				transactionPojo.setAmt(rs.getInt("amt"));
				transactionPojo.setBalance(rs.getInt("balance"));
				transactionPojo.setStatus(rs.getString("status"));
				transactionPojo.setDate(rs.getString("date_time"));
				transaction.add(transactionPojo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return transaction;
		
	}
	@Override
	public String checkAcc_type(int saccNo) {
		String ans="";
		try {
			String q="select A_type from bank where accNo=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, saccNo);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ans=rs.getString("A_type");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ans;
	}
	
	
	public UserPojo getAllDetailsByAccno(int accno){
		UserPojo userPojo=new UserPojo();
				
		try {
			String q="select * from bank where accNo=?";
			ps=con.prepareStatement(q);
			ps.setInt(1, accno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				userPojo.setId(rs.getInt("id"));
				userPojo.setAccNo(rs.getInt("accNo"));
				userPojo.setName(rs.getString("name"));
				userPojo.setPin(rs.getInt("pin"));
				userPojo.setA_type(rs.getString("A_type"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return userPojo;
	}
	
}

























