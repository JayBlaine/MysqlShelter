package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.Shelter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShelterController implements Initializable {
	
	@FXML
	private AnchorPane MainPane;
	@FXML
	private Button btnHome;
	@FXML
	private Button btnAdd;
	@FXML
	private TextField txtDept;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtPets;
	@FXML
	private MenuButton menuState;
	@FXML
	private MenuItem TX;
	@FXML
	private MenuItem NY;
	@FXML
	private MenuItem CA;
	@FXML
	private Label lblError;
	
	int inCheck = 0; //+1 for each good check, if < 2, no insert (for not nulls address and dept)
	
	@FXML
	public void addShelter(ActionEvent event) throws IOException {
		int dept = 0;
		if(txtDept.getText().length() > 0) {
			if(isInteger(txtDept.getText())) {
				dept = Integer.parseInt(txtDept.getText());
				inCheck++;
			}
		}
		String address = txtAddress.getText();
		if(!address.isEmpty())
			inCheck++;
		
		String city = txtCity.getText();
		String state = menuState.getText();
		if(state.length() > 2)
			state = null;
		
		int numPets = 0;
		if(txtPets.getText().length() > 0) 
			numPets = Integer.parseInt(txtPets.getText());
			
		if(inCheck == 2) {
			Shelter shelTemp = new Shelter(dept, address, city, state, numPets, Main.inClock);
			Main.inClock++;
			Main.shelIn.add(shelTemp);
			//INSERT
			lblError.setText(address + " succesfully added!");
			inCheck = 0;
		}
		else {
			lblError.setText("INVALID ENTRY");
			inCheck = 0;
		}
		
		System.out.println("ADD TO DB TODO");
		
		//application.MySqlRun.statement.excecuteUpdate();
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
	public void setTX(ActionEvent event) throws IOException {
		menuState.setText(TX.getText());
	}
	@FXML
	public void setNY(ActionEvent event) throws IOException {
		menuState.setText(NY.getText());
	}
	@FXML
	public void setCA(ActionEvent event) throws IOException {
		menuState.setText(CA.getText());
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
