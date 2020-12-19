package application;
	
import java.util.ArrayList;

import data.Adopter;
import data.Employee;
import data.Pet;
import data.Shelter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	public static int inClock = 0;
	
	public static ArrayList<Employee> empIn = new ArrayList<Employee>();
	public static ArrayList<Shelter> shelIn = new ArrayList<Shelter>();
	public static ArrayList<Pet> petIn = new ArrayList<Pet>();
	public static ArrayList<Adopter> adopIn = new ArrayList<Adopter>();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,550,324);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Shelter Control");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		launch(args);
	}
}