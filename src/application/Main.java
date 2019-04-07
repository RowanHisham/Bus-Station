package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private Person customer;
	
	private static Main instance;
	
	// static method to get instance of Main
	public static Main getInstance() {
	        return instance;
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		instance = this;
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			Scene scene = new Scene(root,981,559);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.setFill(Color.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) throws IOException {
		this.customer = customer;
	}

	public static void main(String[] args) {
		
		launch(args);
	}
	
	
}
