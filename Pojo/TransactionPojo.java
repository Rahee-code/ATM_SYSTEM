package Pojo;

public class TransactionPojo {
	private int pin;
	private String A_type;
	private String T_type;
	private String rec_name;
	private int raccNo;
	private int amt;
	private int balance;
	private String status;
	private String date;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getA_type() {
		return A_type;
	}
	public void setA_type(String a_type) {
		A_type = a_type;
	}
	public String getT_type() {
		return T_type;
	}
	public void setT_type(String t_type) {
		T_type = t_type;
	}
	public String getRec_name() {
		return rec_name;
	}
	public void setRec_name(String rec_name) {
		this.rec_name = rec_name;
	}
	public int getRaccNo() {
		return raccNo;
	}
	public void setRaccNo(int raccNo) {
		this.raccNo = raccNo;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TransactionPojo(int pin, String a_type, String t_type, String rec_name, int raccNo, int amt, int balance,
			String status) {
		super();
		this.pin = pin;
		A_type = a_type;
		T_type = t_type;
		this.rec_name = rec_name;
		this.raccNo = raccNo;
		this.amt = amt;
		this.balance = balance;
		this.status = status;
		
	}
	
	
	public TransactionPojo(int pin, String a_type, String t_type, String rec_name, int raccNo, int amt, int balance,
			String status, String date) {
		super();
		this.pin = pin;
		A_type = a_type;
		T_type = t_type;
		this.rec_name = rec_name;
		this.raccNo = raccNo;
		this.amt = amt;
		this.balance = balance;
		this.status = status;
		this.date = date;
	}
	public TransactionPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "pin= " + pin + ",\nAccount type= " + A_type + ",\nTransaction type= " + T_type + ",\nreceiver's name= " + rec_name
				+ ",\nreceiver's accNo= " + raccNo + ",\namount= " + amt + ",\nbalance= " + balance + ",\nstatus= " + status + ",\nDate=  "
				+ date ;
	}
	
	
	
	
	
	
	
	

}
