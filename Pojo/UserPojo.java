package Pojo;

public class UserPojo {
	
	private int id;
	private int accNo;
	private String a_type;
	private String name;
	private int pin;

	public UserPojo( int accNo, String a_type, String name, int pin) {
		super();
		this.accNo = accNo;
		this.a_type = a_type;
		this.name = name;
		this.pin = pin;
	}
	public UserPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public String getA_type() {
		return a_type;
	}
	public void setA_type(String a_type) {
		this.a_type = a_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	@Override
	public String toString() {
		return "UserPojo [id=" + id + ", accNo=" + accNo + ", A_type=" + a_type + ", name=" + name + ", pin=" + pin
				+ "]";
	}
	
	
	
	
}