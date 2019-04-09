package application;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SampleController {
	private Person person;
	
	@FXML
	private JFXButton btn_signin, btn_signup, btn_employee, btn_signinCustomer, btn_Close, btn_SignInEmp;

	@FXML
	private AnchorPane pn_signup, pn_signin, pn_employeeLogIn;

	@FXML
	void buttonOnAction(ActionEvent event) throws IOException {
		if( event.getSource() == btn_signin) {
			pn_signin.toFront();

		}else if( event.getSource() == btn_signup) {
			pn_signup.toFront();
    		
    	}else if( event.getSource() == btn_employee) {
    		pn_employeeLogIn.toFront();
    	}else if( event.getSource() == btn_signinCustomer) {
    		
    		//create new customer
    		person = new Customer("Rowan", "Hisham", null, null, null);
    		//set a copy of customer in Main
    		Main.getInstance().setCustomer(person);
    		//Load customerForm
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerMainForm.fxml"));
    		Parent root = (AnchorPane)loader.load();
    		CustomerMainFormController customerMainFormController = (CustomerMainFormController) loader.getController();
    		//pass customer to customer form from Main
    		customerMainFormController.setCustomer(Main.getInstance().getCustomer());
    		customerMainFormController.initializeData();
    		customerMainFormController.setTable();
    		Scene customerMainFormScene = new Scene(root);
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.setScene(customerMainFormScene);
    		window.show();
    		
    		
    	}else if( event.getSource() == btn_SignInEmp) {
    		System.out.println("here");
    		Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("driverMainForm.fxml"));
    		Scene managerMainFormScene = new Scene(root);
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.setScene(managerMainFormScene);
    		window.show();
    	}else if( event.getSource() == btn_Close) {
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.close();
    	}
    }
}

