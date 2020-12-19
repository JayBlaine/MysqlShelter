package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EmployeeController implements Initializable{
	
	@FXML
	private AnchorPane MainPane;
	@FXML
	private Button btnHome;
	@FXML
	private Button btnAdd;
	@FXML
	private Label lblError;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtEid;
	@FXML
	private TextField txtDept;
	@FXML
	private TextField txtMan;
	@FXML
	private TextField txtSsn;
	@FXML
	private TextField txtSalary;
	
	int inCheck = 0;
	
	@FXML
	public void addEmp(ActionEvent event) throws IOException {
		String name = null;
		name = txtName.getText();
		String email = null;
		if(!txtEmail.getText().isEmpty()) {
			email = txtEmail.getText();
			inCheck++;
		}
		String ssn = null;
		if(!txtSsn.getText().isEmpty())
			if(txtSsn.getText().length() == 9) {
				ssn = txtSsn.getText(0, 8);
				inCheck++;
			}
		int eId = 0;
		if(!txtEid.getText().isEmpty()) 
			if(isInteger(txtEid.getText())) {
				eId = Integer.parseInt(txtEid.getText());
				inCheck++;
			}
		int mId = 0;
		if(!txtMan.getText().isEmpty())
			if(isInteger(txtMan.getText()))
				mId = Integer.parseInt(txtMan.getText());
		int deptId = 0;
		if(!txtDept.getText().isEmpty())
			if(isInteger(txtDept.getText()))
				deptId = Integer.parseInt(txtDept.getText());
		int salary = 0;
		if(!txtSalary.getText().isEmpty())
			if(isInteger(txtSalary.getText()))
				salary = Integer.parseInt(txtSalary.getText());
		
		if(inCheck == 3) {
			Employee empTemp = new Employee(ssn, name, email, eId, deptId, salary, mId, Main.inClock);
			Main.inClock++;
			Main.empIn.add(empTemp);
			inCheck = 0;
			lblError.setText(name + " successfully added!");
		}
		else {
			inCheck = 0;
			lblError.setText("ERROR: INVALID INPUT");
		}
		
		
		
			
	}
	private static boolean isInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}
	@FXML
	public void goHome(ActionEvent event) throws IOException {
		MainPane = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(MainPane);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Shelter Control");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
