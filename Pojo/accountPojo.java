package Pojo;

public class accountPojo {
	private int accNo;
	private int amt;
	private String name;
	private String date;
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "accountPojo [accNo=" + accNo + ", amt=" + amt + ", name=" + name + ", date=" + date + "]";
	}
	public accountPojo(int accNo, int amt, String name, String date) {
		super();
		this.accNo = accNo;
		this.amt = amt;
		this.name = name;
		this.date = date;
	}
	public accountPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
