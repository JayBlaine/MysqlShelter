package data;

public class Pet {
	private int pId;
	private String pName;
	private String type;
	private String breed;
	private String gender;
	private int age;
	private int weight;
	private int vacRec;
	private int inTime;
	private String shelNum;
	
	public Pet(int idIn, String nameIn, String typeIn, String breedIn, String gendIn, int ageIn, int weightIn, int vacIn, int inTimeIn) {
		this.pId = idIn;
		this.pName = nameIn;
		this.type = typeIn;
		this.breed = breedIn;
		this.gender = gendIn;
		this.age = ageIn;
		this.weight = weightIn;
		this.vacRec = vacIn;
		this.inTime = inTimeIn;
	}
	
	public void setShel(String deptId) { this.shelNum = deptId; }
	public String getShel() { return this.shelNum; }
	public String getGend() { return this.gender; }
	public int getinTime() { return this.inTime; }
	public int getpId() { return this.pId; }
	public int getAge() { return this.age; }
	public int getWeight() { return this.weight; }
	public int getVacRec() { return this.vacRec; }
	public String getpName() { return this.pName; }
	public String getType() { return this.type; }
	public String getBreed() { return this.breed; }

}
