package data;

public class Shelter {
	private int dept_id;
	private String address;
	private String city;
	private String state;
	private int numPets;
	private int inTime;
	
	public Shelter(int dept_id, String address, String city, String state, int numPets, int inTimeIn) {
		this.dept_id = dept_id;
		this.address = address;
		this.city = city;
		this.state = state;
		this.numPets = numPets;
		this.inTime = inTimeIn;
	}
	public int getinTime() { return this.inTime; }
	public int getNumPets() { return this.numPets; }
	public int getdept() { return this.dept_id; }
	public String getAddress() { return this.address; }
	public String getCity() { return this.city; }
	public String getState() { return this.state; }

}
