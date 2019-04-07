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
import javafx.stage.Stage;

public class ManagerMainFormController {

	@FXML
    private JFXButton btn_logOutCustomer, btn_Trips, btn_addtrip, btn_Close;
	
	@FXML
	private AnchorPane pn_Trips, pn_title, pn_addtrip;

    @FXML
    void buttonOnAction(ActionEvent event) throws IOException {
    	if(event.getSource() == btn_logOutCustomer) {
    		Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
    		Scene customerMainFormScene = new Scene(root);
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.setScene(customerMainFormScene);
    		window.show();
    	}else if( event.getSource() == btn_Trips) {
    		pn_Trips.toFront();
    		pn_title.toFront();
    	}else if( event.getSource() == btn_addtrip) {
    		pn_addtrip.toFront();
    		pn_title.toFront();
    	}else if( event.getSource() == btn_Close) {
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.close();
    	}
    }

}