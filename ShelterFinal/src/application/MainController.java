package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable{
	
	@FXML
	private AnchorPane MainPane;
	@FXML
	private Button btnEmp;
	@FXML
	private Button btnAdop;
	@FXML
	private Button btnShel;
	@FXML
	private Button btnPet;
	@FXML
	private Label lblTitle;
	@FXML
	private Button btnRun;
	
	@FXML
	public void runQueries(ActionEvent event) throws Exception {
//		btnEmp.setVisible(false);
//		btnShel.setVisible(false);
//		btnAdop.setVisible(false);
//		btnPet.setVisible(false);
//		btnRun.setVisible(false);
//		lblTitle.setText("Inserting/Running Test Queries...");
		MySqlRun pgm = new MySqlRun("nqg320", "01741320");
		
        pgm.runProgram();
	}

	@FXML
	public void openEmp(ActionEvent event) throws IOException {
		MainPane = FXMLLoader.load(getClass().getResource("Employee.fxml"));
		Scene scene = new Scene(MainPane);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Employee Admin");
	}
	
	@FXML
	public void openAdop(ActionEvent event) throws IOException {
		MainPane = FXMLLoader.load(getClass().getResource("Adopter.fxml"));
		Scene scene = new Scene(MainPane);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Adopter Admin");
	}
	
	@FXML
	public void openPet(ActionEvent event) throws IOException {
		MainPane = FXMLLoader.load(getClass().getResource("Pet.fxml"));
		Scene scene = new Scene(MainPane);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Pet Admin");
	}
	
	@FXML
	public void openShel(ActionEvent event) throws IOException {
		MainPane = FXMLLoader.load(getClass().getResource("Shelter.fxml"));
		Scene scene = new Scene(MainPane);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Shelter Admin");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
