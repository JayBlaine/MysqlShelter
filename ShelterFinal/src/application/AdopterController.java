package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.Adopter;
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

public class AdopterController implements Initializable {
	
	@FXML
	private AnchorPane MainPane;
	@FXML
	private Button btnHome;
	@FXML
	private Button btnAdd;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtPhone1;
	@FXML
	private TextField txtPhone2;
	@FXML
	private TextField txtPhone3;
	@FXML
	private TextField txtName;
	@FXML
	private Label lblError;
	@FXML
	private TextField txtShelNum;
	@FXML
	private TextField txtAdopId;
	
	int inCheck = 0;
	@FXML
	public void addAdopter(ActionEvent event) throws IOException {
		String address = null;
		String phone1 = null;
		String phone2 = null;
		String phone3 = null;
		if(!txtAddress.getText().isEmpty()) {
			address = txtAddress.getText();
			inCheck++;
		}
		if(!txtPhone1.getText().isEmpty()) {
			if(isInteger(txtPhone1.getText())) {
				phone1 = txtPhone1.getText(0, 3);
				inCheck++;
			}
		}
		if(!txtPhone2.getText().isEmpty()) {
			if(isInteger(txtPhone2.getText())) {
				phone2 = txtPhone2.getText(0, 3);
				inCheck++;
			}
		}
		if(!txtPhone3.getText().isEmpty()) {
			if(isInteger(txtPhone3.getText())) {
				phone3 = txtPhone3.getText(0, 4);
				inCheck++;
			}
		}
		String name = txtName.getText();
		
		if(inCheck == 4) {
			String phone = (phone1.concat(phone2)).concat(phone3);
			Adopter adoptTemp = new Adopter(address, phone, name, Main.inClock);
			adoptTemp.setAdopt(-1);
			adoptTemp.setShel("");
			if(!txtShelNum.getText().isEmpty())
					adoptTemp.setShel(txtShelNum.getText());
			if(!txtAdopId.getText().isEmpty())
				if(isInteger(txtAdopId.getText()))
					adoptTemp.setAdopt(Integer.parseInt(txtAdopId.getText()));
			
			Main.inClock++;
			Main.adopIn.add(adoptTemp);
			//INSERT
			lblError.setText(name + " succesfully added");
			inCheck = 0;
		}
		else {
			lblError.setText("ERROR: INVALID ENTRY");
			inCheck = 0;
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
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
