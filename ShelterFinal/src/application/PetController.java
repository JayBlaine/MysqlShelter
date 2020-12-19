package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.Pet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PetController implements Initializable{
	
	@FXML
	private AnchorPane MainPane;
	@FXML
	private Button btnHome;
	@FXML
	private MenuButton menuType;
	@FXML
	private Button btnAdd;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtPid;
	@FXML
	private TextField txtBreed;
	@FXML
	private TextField txtAge;
	@FXML
	private RadioButton radF;
	@FXML
	private RadioButton radM;
	@FXML
	private RadioButton radYesVac;
	@FXML
	private RadioButton radNoVac;
	@FXML
	private Slider slidWeight;
	@FXML
	private Label lblError;
	@FXML
	private TextField txtDept;
	
	int inCheck = 0;
	
	@FXML
	public void addPet(ActionEvent event) throws IOException {
		int pId = 0;
		if(!txtPid.getText().isEmpty()) {
			if(isInteger(txtPid.getText())) {
				pId= Integer.parseInt(txtPid.getText());
				inCheck++;
			}
		}
		String type = null;
		String pName = txtName.getText();
		if(!menuType.getText().equals("Type"))
			type = menuType.getText();
		String breed = null;
		if(!txtBreed.getText().isEmpty())
			breed = txtBreed.getText();
		String gender;
		if(radM.isSelected())
			gender = "M";
		else
			gender = "F";
		int vacRec = 0;
		if(radYesVac.isSelected())
			vacRec = 1;
		else
			vacRec = 0;
		int weight = (int)slidWeight.getValue();
		int age = 0;
		if(!txtAge.getText().isEmpty())
			if(isInteger(txtAge.getText()))
				age = Integer.parseInt(txtAge.getText());
		if(inCheck == 1) {
			Pet tempPet = new Pet(pId, pName, type, breed, gender, age, weight, vacRec, Main.inClock);
			Main.inClock++;
			tempPet.setShel("");
			if(!txtDept.getText().isEmpty())
					tempPet.setShel(txtDept.getText());
			Main.petIn.add(tempPet);
			inCheck = 0;
			lblError.setText(pName + " successfully added!");
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
	public void setDog(ActionEvent event) throws IOException {
		menuType.setText("dog");
	}
	@FXML
	public void setCat(ActionEvent event) throws IOException {
		menuType.setText("cat");
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
