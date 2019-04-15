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

public class BookingController {

	@FXML
    private JFXButton btn_Confirm, btn_Cancel;
	
	@FXML
	private AnchorPane pn_Dashboard;

    @FXML
    void buttonOnAction(ActionEvent event) throws IOException {
    	if(event.getSource() == btn_Confirm) {
    		
    		
    	}else if( event.getSource() == btn_Cancel) {
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.close();
    	}
    }

}
