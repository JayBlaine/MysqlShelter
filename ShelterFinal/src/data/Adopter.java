package data;

public class Adopter {
	private int inTime;
	private String address;
	private String phone;
	private String name;
	private String shelNum;
	private int adopId;
	
	public Adopter(String inAdd, String inPhone, String inName, int inTimeIn) {
		this.address = inAdd;
		this.phone = inPhone;
		this.name = inName;
		this.inTime = inTimeIn;
	}
	
	public void setShel(String deptId) { this.shelNum = deptId; }
	public String getShel() { return this.shelNum; }
	public void setAdopt(int pId) { this.adopId = pId; }
	public int getAdop() { return this.adopId; }
	public int getinTime() { return this.inTime; }
	public String getAdd() { return this.address; }
	public String getPhone() { return this.phone; }
	public String getName() { return this.name; }

}
