package data;

public class Employee {
	private String ssn;
	private String name;
	private String email;
	private int eid;
	private int dept_id;
	private int salary;
	private int m_id;
	private int inTime;
	
	public Employee(String inSsn, String inName, String emailIn, int inEid, int inDept_id, int inSalary, int inMid, int inTimeIn) {
		this.ssn = inSsn;
		this.name = inName;
		this.email = emailIn;
		this.eid = inEid;
		this.dept_id = inDept_id;
		this.salary = inSalary;
		this.m_id = inMid;
		this.inTime = inTimeIn;
	}
	
	public String getSsn() { return this.ssn; }
	public String getName() { return this.name; }
	public String getEmail() { return this.email; }
	public int getEid() { return this.eid; }
	public int getDept() { return this.dept_id; }
	public int getMid() { return this.m_id; }
	public int getinTime() { return this.inTime; }
	public int getSalary() { return this.salary; }

}
