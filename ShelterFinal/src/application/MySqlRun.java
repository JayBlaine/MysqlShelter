package application;
// MySQLExample.java
// package cs3743;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import data.Adopter;
import data.Employee;
import data.Pet;
import data.Shelter;
//import application.MainController;


public class MySqlRun 
{
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    // error constants
    public static final int ER_DUP_ENTRY = 1062;
    public static final int ER_DUP_ENTRY_WITH_KEY_NAME = 1586;
    ZoneId zid = ZoneId.of("America/Chicago");
    LocalDate date = LocalDate.now(zid);

    public MySqlRun (String user, String password) throws Exception
    {
        try
        {
            // This will load the MySQL driver, each DBMS has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver"); //just added .cj
            this.connect = DriverManager.getConnection
                    ("jdbc:mysql://10.100.1.81:3306/nqg320db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=America/Chicago"
                    , user
                    , password);
        }
        catch (Exception e) 
        {
            throw e;
        } 
    }
    // runProgram does all the work except connecting
    public void runProgram() throws Exception 
    {
        try 
        {
            // Statement allows us to issue SQL queries to the database
            statement = connect.createStatement();
            // Get the sections and save in resultSet
            resultSet = statement.executeQuery("select * from nqg320db.employee");
            printSections("Beginning Employees", resultSet, 1);
            
            // Execute an insert into Section using JDBC statement.execQuery
            
            int empCnt = 0;
        	int shelCnt = 0;
        	int petCnt = 0;
        	int adopCnt = 0;
        	
        	// Instead of just passing a string as is done with
            // statement.executeQuery and statement.executeUpdate,
            // we can use preparedStatement which allows us
            // to provide variable values to replace "?" values.
            
            for(int i = 0; i < Main.inClock; i++) {
            	for(int j = 0; j < Main.empIn.size(); j++) {
	            	if(Main.empIn.get(j).getinTime() == i) {
	            		preparedStatement = connect.prepareStatement
	                            ("insert into nqg320db.employee values "
	                            +"(?, ?, ?, ?, ?, ?, ?)"); //Starts at 1
	            		preparedStatement.setInt(1, Main.empIn.get(empCnt).getEid());
	            		preparedStatement.setString(2, Main.empIn.get(empCnt).getEmail());
	            		preparedStatement.setString(3, Main.empIn.get(empCnt).getSsn());
	            		preparedStatement.setString(4, Main.empIn.get(empCnt).getName());
	            		preparedStatement.setInt(5, Main.empIn.get(empCnt).getDept());
	            		preparedStatement.setInt(6, Main.empIn.get(empCnt).getSalary());
	            		preparedStatement.setInt(7, Main.empIn.get(empCnt).getMid());
	            		//INSERT HERE
	            		empCnt++;
	            	}
            	}
            	for(int j = 0; j < Main.shelIn.size(); j++) {
	            	if(Main.shelIn.get(j).getinTime() == i) {
	            		preparedStatement = connect.prepareStatement
	                            ("insert into nqg320db.shelter values "
	                            +"(?, ?, ?, ?, ?)");
	            		preparedStatement.setInt(1, Main.shelIn.get(shelCnt).getdept());
	            		preparedStatement.setString(2, Main.shelIn.get(shelCnt).getAddress());
	            		preparedStatement.setString(3, Main.shelIn.get(shelCnt).getCity());
	            		preparedStatement.setString(4, Main.shelIn.get(shelCnt).getState());
	            		preparedStatement.setInt(5, Main.shelIn.get(shelCnt).getNumPets());
	            		//INSERT HERE
	            		shelCnt++;
	            	}
            	}
            	for(int j = 0; j < Main.petIn.size(); j++) {
	            	if(Main.petIn.get(j).getinTime() == i) {
	            		preparedStatement = connect.prepareStatement
	                            ("insert into nqg320db.pet values "
	                            +"(?, ?, ?, ?, ?, ?, ?, ?)");
	            		preparedStatement.setInt(1, Main.petIn.get(petCnt).getpId());
	            		preparedStatement.setString(2, Main.petIn.get(petCnt).getpName());
	            		preparedStatement.setString(3, Main.petIn.get(petCnt).getType());
	            		preparedStatement.setString(4, Main.petIn.get(petCnt).getBreed());
	            		preparedStatement.setString(5, Main.petIn.get(petCnt).getGend());
	            		preparedStatement.setInt(6, Main.petIn.get(petCnt).getAge());
	            		preparedStatement.setInt(7, Main.petIn.get(petCnt).getWeight());
	            		preparedStatement.setInt(8, Main.petIn.get(petCnt).getVacRec());
	            		//INSERT HERE
	            		petCnt++;
	            	}
            	}
            	for(int j = 0; j < Main.adopIn.size(); j++) {
	            	if(Main.adopIn.get(j).getinTime() == i) {
	            		preparedStatement = connect.prepareStatement
	                            ("insert into nqg320db.adopter values "
	                            +"(?, ?, ?)");
	            		preparedStatement.setString(1, Main.adopIn.get(adopCnt).getAdd());
	            		preparedStatement.setString(2, Main.adopIn.get(adopCnt).getPhone());
	            		preparedStatement.setString(3, Main.adopIn.get(adopCnt).getName());
	            		//INSERT HERE
	            		adopCnt++;
	            	}
            	}
            	try 
                {
                    // Execute that insert statement
                    preparedStatement.executeUpdate();
                }
                catch (SQLException e)
                {
                    switch (e.getErrorCode())
                    {
                        case ER_DUP_ENTRY:
                        case ER_DUP_ENTRY_WITH_KEY_NAME:
                            System.out.printf("Duplicate key error: %s\n", e.getMessage());
                            break;
                        default:
                            throw e;
                    }
                }
            }
            
            /*
             * 
             * 
             * 
             * 
             * 
             */
            
            petCnt = 0;
            shelCnt = 0;
            empCnt = 0;
            adopCnt = 0;
            for(int i = 0; i < Main.inClock; i++) {
            	//RELATIONSHIP TABLES TODO
            	for(int j = 0; j < Main.empIn.size(); j++) {
            		for(int k = 0; k < Main.empIn.size(); k++) {
            			if(Main.empIn.get(k).getMid() > 0) {
            				preparedStatement = connect.prepareStatement
                                    ("insert into nqg320db.works_under values "
                                    +"(?, ?)");
            				preparedStatement.setInt(1, Main.empIn.get(k).getMid());
            				preparedStatement.setInt(2, Main.empIn.get(k).getEid());
            				try 
                            {
                                // Execute that insert statement
                                preparedStatement.executeUpdate();
                            }
                            catch (SQLException e)
                            {
                                switch (e.getErrorCode())
                                {
                                    case ER_DUP_ENTRY:
                                    case ER_DUP_ENTRY_WITH_KEY_NAME:
                                        System.out.printf("Duplicate key error: %s\n", e.getMessage());
                                        break;
                                    default:
                                        throw e;
                                }
                            }
            			}
            		}
            		for(int k = 0; k < Main.empIn.size(); k++) {
            				preparedStatement = connect.prepareStatement
                                    ("insert into nqg320db.works_at values "
                                    +"(?, ?)");
            				preparedStatement.setInt(1, Main.empIn.get(k).getEid());
            				preparedStatement.setInt(2, Main.empIn.get(k).getDept());
            				try 
                            {
                                // Execute that insert statement
                                preparedStatement.executeUpdate();
                            }
                            catch (SQLException e)
                            {
                                switch (e.getErrorCode())
                                {
                                    case ER_DUP_ENTRY:
                                    case ER_DUP_ENTRY_WITH_KEY_NAME:
                                        System.out.printf("Duplicate key error: %s\n", e.getMessage());
                                        break;
                                    default:
                                        throw e;
                                }
                            }
            			
            		}
            	}
            		
            	for(int j = 0; j < Main.petIn.size(); j++) {
            		if(!Main.petIn.get(j).getShel().isEmpty()) {
            				preparedStatement = connect.prepareStatement
                                    ("insert into nqg320db.houses values "
                                    +"(?, ?)");
            				preparedStatement.setInt(1, Main.petIn.get(j).getpId());
            				preparedStatement.setString(2, Main.petIn.get(j).getShel());
            				//STATEMENT HERE
            				try 
                            {
                                // Execute that insert statement
                                preparedStatement.executeUpdate();
                            }
                            catch (SQLException e)
                            {
                                switch (e.getErrorCode())
                                {
                                    case ER_DUP_ENTRY:
                                    case ER_DUP_ENTRY_WITH_KEY_NAME:
                                        System.out.printf("Duplicate key error: %s\n", e.getMessage());
                                        break;
                                    default:
                                        throw e;
                                }
                            }
            		}
            	}
            	for(int j = 0; j < Main.adopIn.size(); j++) {
            		for(int k = 0; k < Main.adopIn.size(); k++) {
            			if(Main.adopIn.get(k).getAdop() != -1) {
            				preparedStatement = connect.prepareStatement
                                    ("insert into nqg320db.adopts values "
                                    +"(?, ?, ?, ?)");
            				preparedStatement.setString(1, Main.adopIn.get(j).getPhone());
            				preparedStatement.setInt(2, Main.adopIn.get(j).getAdop());
            				preparedStatement.setDate(3, java.sql.Date.valueOf(date));
            				preparedStatement.setString(4, "THIS IS A PLACEHOLDER");
            			}
            			//adopts
            			try 
                        {
                            // Execute that insert statement
                            preparedStatement.executeUpdate();
                        }
                        catch (SQLException e)
                        {
                            switch (e.getErrorCode())
                            {
                                case ER_DUP_ENTRY:
                                case ER_DUP_ENTRY_WITH_KEY_NAME:
                                    System.out.printf("Duplicate key error: %s\n", e.getMessage());
                                    break;
                                default:
                                    throw e;
                            }
                        }
            		}
            		for(int k = 0; k < Main.adopIn.size(); k++) {
            			if(!Main.adopIn.get(k).getShel().isEmpty()) {
            				preparedStatement = connect.prepareStatement
                                    ("insert into nqg320db.visits values "
                                    +"(?, ?, ?)");
            				preparedStatement.setString(1, Main.adopIn.get(k).getPhone());
            				preparedStatement.setString(2, Main.adopIn.get(k).getShel());
            				preparedStatement.setDate(3, java.sql.Date.valueOf(date));
            			}
            			//visits
            			try 
                        {
                            // Execute that insert statement
                            preparedStatement.executeUpdate();
                        }
                        catch (SQLException e)
                        {
                            switch (e.getErrorCode())
                            {
                                case ER_DUP_ENTRY:
                                case ER_DUP_ENTRY_WITH_KEY_NAME:
                                    System.out.printf("Duplicate key error: %s\n", e.getMessage());
                                    break;
                                default:
                                    throw e;
                            }
                        }
            		}
            	}
            }

            statement.executeUpdate("update nqg320db.employee set salary = salary * 1.07 where salary > 0");
            
            resultSet = statement
                    .executeQuery("select avg(salary) from nqg320db.employee");
            System.out.println("Employee average salary");

            resultSet = statement
                    .executeQuery("select e.name from nqg320db.employee e where e.m_id = 0 and e.dept_id = "
                    		+ "(select m.dept_id from nqg320db.employee m where m.dept_id = e.dept_id and m.m_id = 0)");
            System.out.println("Employee all employees working under managers");
            System.out.println("Name");
            while(resultSet.next()) 
            	System.out.println(resultSet.getString(1));
            writeMetaData(resultSet);

            resultSet = statement
                    .executeQuery("select * from nqg320db.pet where gender = 'F' and weight < 30");
            printSections("Pet all females under 30 lbs", resultSet, 3);
            writeMetaData(resultSet);

            resultSet = statement
                    .executeQuery("select * from nqg320db.pet p join nqg320db.houses h on p.pid = h.pid "
                    		+ "where type = 'cat' and address = (select address from shelter where dept_id = 0)");
            printSections("Pet select all cats from hq", resultSet, 3);
            writeMetaData(resultSet);

            resultSet = statement
                    .executeQuery("select * from nqg320db.adopter where phone = ("
                    			+ "select phone from adopts where pid = ("
                    			+ "select pid from pet where pname = 'Binx'))");
            printSections("Adopter select adopter that adopted binx", resultSet, 4);
            writeMetaData(resultSet);
            
            statement.executeUpdate("delete from adopter where name is null");
            
            statement.executeUpdate("update adopter set name = 'ERROR' where address is null");

            resultSet = statement
            		.executeQuery("Select sum(Total_pets) from visits v join shelter s on s.address=v.b_address");
//            System.out.println("Total pets a visitor could look at from all visited shelters.");
//            System.out.println("Total Pets");
//            	System.out.println(resultSet.getInt("sum(Total_pets)"));
//            writeMetaData(resultSet);
            
           resultSet = statement
            		.executeQuery("Select a.name from visits v, adopter a where v.phone = a.phone and v.phone like '210%'");
           System.out.println("List of visitors from san antonio based on area code.");
           System.out.println("Name");
           while(resultSet.next())
        	   System.out.println(resultSet.getString(1));
           writeMetaData(resultSet);
           
           resultSet = statement
        		   .executeQuery("select a.name, count(v.b_address) from adopter a, visits v group by a.name having count(v.b_address > 1) order by count(v.b_address)");
           System.out.println("List of visitors and total visits from all combined.");
           System.out.printf("%-10s %-15s\n", "name", "visit num");
           while(resultSet.next())
        	   System.out.printf("%-10s %-3d\n", resultSet.getString(1), resultSet.getInt(2));
           writeMetaData(resultSet);
           
           resultSet = statement
        		   .executeQuery("select * from employee e, works_under w where e.m_id = 0 and w.m_id != 1 and e.eid = w.eid");
           printSections("List of managers excluding gm", resultSet, 1);
           writeMetaData(resultSet);
           
           resultSet = statement
        		   .executeQuery("select min(e.salary) from employee e, works_under w where e.eid = w.eid and e.m_id = 0");
           System.out.println("lowest manager salary");
           System.out.println("Salary");
           while(resultSet.next())
        	   System.out.println(resultSet.getInt(1));
           writeMetaData(resultSet);
           
//           resultSet = statement
//        		   .executeQuery("select name from works_at w join employee e on w.dept_id=e.dept_id where e.dept_id=0 and e.salary < 30000 and e.eid = rand(e.eid)" );
//           System.out.println("Select random low pay employee from hq for a task");
//           System.out.println("Name");
//           System.out.println(resultSet.getString(1));
//           writeMetaData(resultSet);
           
           resultSet = statement
        		   .executeQuery("select e.* from works_at w join employee e on e.eid = w.eid where e.salary > (select avg(salary) from employee)");
           printSections("All employees with higher than average salaries that work at shelters.", resultSet, 1);
           writeMetaData(resultSet);
           
           resultSet = statement
        		   .executeQuery("select e.* from works_at w, employee e where w.eid = e.eid and w.dept_id = (select dept_id from shelter order by total_pets desc limit 1)");
           printSections("Employees working at shelter with most pets", resultSet, 1);
           writeMetaData(resultSet);
           
           resultSet = statement
        		   .executeQuery("select p.* from pet p, houses h where p.type = 'cat' and p.pid = h.pid and h.address = (select address from shelter where dept_id = 0) order by age desc limit 1");
           printSections("Oldest cat at headquarters", resultSet, 3);
           writeMetaData(resultSet);
           
           resultSet = statement
        		   .executeQuery("select p.* from pet p, houses h where p.pid = h.pid and p.age < 3 and h.address = (select address from shelter where dept_id = 0)");
           printSections("All pets younger than 3 kept at HQ", resultSet, 3);
           writeMetaData(resultSet);
           
           try {
        	   statement.executeUpdate("update houses set address = (select address from shelter where dept_id=0) where address = (select address from shelter order by dept_id asc limit 1)");
           }
           catch (SQLException e)
           {
               switch (e.getErrorCode())
               {
                   case ER_DUP_ENTRY:
                   case ER_DUP_ENTRY_WITH_KEY_NAME:
                       System.out.printf("Duplicate key error: %s\n", e.getMessage());
                       break;
                   default:
                       throw e;
               }
           }
           
           resultSet = statement
        		   .executeQuery("select a.* from adopter a, adopts d where d.phone = a.phone and d.phone like '210%'");
           printSections("Adopters from San Antonio based on 210 phone area code", resultSet, 4);
           writeMetaData(resultSet);
           
           resultSet = statement
        		   .executeQuery("select * from adopter a, adopts d where a.phone = d.phone and date = curdate()");
           printSections("Adopters that adopted a pet on the current day", resultSet, 4);
           writeMetaData(resultSet);
           

        } 
        catch (Exception e) 
        {
            throw e;
        } 
        finally 
        {
            close();
        }

    }
    //1 = employee, 2 = shelter, 3 = pet, 4 = adopter, 5 = works_under, 6 = works_at, 7 = houses, 8 = adopts, 9 = visits
    private void printSections(String title, ResultSet resultSet, int numTable) throws SQLException 
    {
        // The current position in resultSet is before the first row
        System.out.printf("%s\n", title);
        
        switch(numTable) {
        
        case 1: //EMPLOYEE
        	System.out.printf("%-3s %-20s %-9s %-10s %-3s %-3s %-6s\n"
        			, "EID", "Email", "SSN", "Name", "DEP", "MID", "Salary");
        	
        	while(resultSet.next()) {
        		int eid = resultSet.getInt("Eid");
            	String email = resultSet.getString("Email");
            	String ssn = resultSet.getString("ssn");
            	String name = resultSet.getString("name");
            	if(name == null)
            		name = "---";
            	int dept = resultSet.getInt("Dept_id");
            	int mId = resultSet.getInt("M_id");
            	int salary = resultSet.getInt("Salary");
            	System.out.printf("%-3d %-20s %-9s %-10s %-3d %-3d %-6d\n"
            			, eid, email, ssn, name, dept, mId, salary);
        	}
        	System.out.printf("\n");
        	break;
        	
        case 2: //SHELTER
        	System.out.printf("%-4s %-20s %-10s %-2s %-3d\n"
        			, "Dept", "Address", "City", "ST", "Pet");
        	
        	while(resultSet.next()) {
        		int dept = resultSet.getInt("Dept_id");
        		String address = resultSet.getString("Address");
        		String city = resultSet.getString("city");
        		if(city == null)
        			city = "---";
        		String state = resultSet.getString("state");
        		if(state == null)
        			state = "--";
        		int numPets = resultSet.getInt("Total_pets");
        		System.out.printf("%-3d %-20s %-10s %-2s %-3d\n"
        				, dept, address, city, state, numPets);
        	}
        	System.out.printf("\n");
        	break;
        	
        case 3: //PET
        	System.out.printf("%-3s %-10s %-4s %-10s %-1s %-3s %-2s %-6s\n"
        			, "Pid", "pName", "Type", "Breed", "G", "Age", "WT", "VacRec");
        	
        	while(resultSet.next()) {
        		int pId = resultSet.getInt("Pid");
        		String pName = resultSet.getString("Pname");
        		if(pName == null)
        			pName = "---";
        		String type = resultSet.getString("Type");
        		if(type == null)
        			type = "---";
        		String breed = resultSet.getString("Breed");
        		if(breed == null)
        			breed = "---";
        		String gender = resultSet.getString("Gender");
        		if(gender == null)
        			gender = "-";
        		int age = resultSet.getInt("Age");
        		int weight = resultSet.getInt("Weight");
        		int vacRec = resultSet.getInt("vac_rec");
        		System.out.printf("%-3d %-10s %-4s %-10s %-1s %-3d %-2d %-6d\n"
        				, pId, pName, type, breed, gender, age, weight, vacRec);
        	}
        	System.out.printf("\n");
        	break;
        	
        case 4: //ADOPTER
        	System.out.printf("%-15s %-10s %-10s\n"
        			, "Address", "Phone", "Name");
        	
        	while(resultSet.next()) {
        		String address = resultSet.getString("Address");
        		if(address == null)
        			address = "---";
        		String phone = resultSet.getString("phone");
        		String name = resultSet.getString("name");
        		if(name == null)
        			name = "---";
        		System.out.printf("%-15s %-10s %-10s\n"
        				, address, phone, name);
        	}
        	System.out.printf("\n");
        	break;
        	
        case 5: //WORKS_UNDER
        	System.out.printf("%-3s %-3s\n"
        			, "MID", "EID");
        	
        	while(resultSet.next()) {
        		int mid = resultSet.getInt("M_Id");
        		int eid = resultSet.getInt("Eid");
        		System.out.printf("%-3d %-3d\n", mid, eid);
        	}
        	System.out.printf("\n");
        	break;
        
        case 6: //WORKS_AT
        	System.out.printf("%-3s %-4s\n", "EID", "DEPT");
        	
        	while(resultSet.next()) {
        		int eid = resultSet.getInt("eid");
        		int dept = resultSet.getInt("dept_id");
        		System.out.printf("%-3d %-3d\n", eid, dept);
        	}
        	System.out.printf("\n");
        	break;
        	
        case 7: //HOUSES
        	System.out.printf("%-3s %-20s\n", "Pid", "Address");
        	
        	while(resultSet.next()) {
        		int pid = resultSet.getInt("Pid");
        		String address = resultSet.getString("Address");
        		System.out.printf("%-3d %-20s\n", pid, address);
        	}
        	System.out.printf("\n");
        	break;
        	
        case 8: //ADOPTS
        	System.out.printf("%-10s %-3s %-20s %-10s\n", "Phone", "PID", "Certificate", "Date");
        	
        	while(resultSet.next()) {
        		String phone = resultSet.getString("phone");
        		int pid = resultSet.getInt("pid");
        		String cert = resultSet.getString("certificate");
        		Date date = resultSet.getDate("date");
        		System.out.printf("%-10s %-3d %-20s %-tF\n", phone, pid, cert, date);
        	}
        	System.out.printf("\n");
        	break;
        	
        case 9: //VISITS
        	System.out.printf("%-10s %-20s %-10s\n", "Phone", "Address", "Date");
        	
        	while(resultSet.next()) {
        		String phone = resultSet.getString("phone");
        		String add = resultSet.getString("b_address");
        		Date date = resultSet.getDate("visit_rec");
        		System.out.printf("%-10s %-20s %-tF\n", phone, add, date);
        	}
        	System.out.printf("\n");
        	break;
        }
        
    }
    private void writeMetaData(ResultSet resultSet) throws SQLException 
    {
        // We will show some metadata about a result set 
        // First, we will get the table name.  
        // For a join, we would have to look at multiple columns to get 
        // multiple table names.
        System.out.printf("Columns for table %s\n"
            , resultSet.getMetaData().getTableName(1));
        System.out.printf("%-16s  %s\n"
            ,"Column Name", "Data Type");
        // Loop through each column
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++)
        {
            String typeStr = resultSet.getMetaData().getColumnTypeName(i);
            System.out.printf("%-16s  %s\n"
                , resultSet.getMetaData().getColumnName(i)
                , typeStr);
        }
    }

    // You need to close the resultSet
    private void close() 
    {
        try 
        {
            if (resultSet != null) 
                resultSet.close();

            if (statement != null) 
                statement.close();

            if (connect != null) 
                connect.close();
        } 
        catch (Exception e) 
        {

        }
    }

}