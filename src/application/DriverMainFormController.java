package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DriverMainFormController {
	private Driver driver;   
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		System.out.println("here");
		this.driver = driver;
	}

	private Admin admin = new Admin();

	@FXML
    private JFXButton btn_logOutCustomer, btn_Trips, btn_Dashboard, btn_AccountSettings, btn_Close;
	
	@FXML
	private AnchorPane pn_Dashboard;
	
	@FXML
	private Label lblUserName;
	
	@FXML
	TableView<Trip> tblBookedTrips = new TableView<Trip>();

    @FXML
    void buttonOnAction(ActionEvent event) throws IOException {
    	if(event.getSource() == btn_logOutCustomer) {
    		Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
    		Scene customerMainFormScene = new Scene(root);
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.setScene(customerMainFormScene);
    		window.show();
    	}else if( event.getSource() == btn_Close) {
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.close();
    	}
    }
    
    public void initializeData() throws IOException {
    	System.out.println(driver.getFirstName());
    	lblUserName.setText(driver.getFirstName() + " " + driver.getLastName());
    	driver.setTripsList(admin.listTrips(admin.listVechiles()));
    	setTable(driver.getTripsList());
    }
    
    public void setTable(ArrayList<Trip> tripList) throws IOException {
    	//System.out.println("here");

//    	tbl_PrevTrips.getItems().removeAll(tripList);

    	
    	tblBookedTrips.getItems().clear();
    	tblBookedTrips.getColumns().clear();
    	tblBookedTrips.refresh();
    	
    	TableColumn<Trip, String> source;
    	TableColumn<Trip, String> destination;
    	TableColumn<Trip, String> date;
    	TableColumn<Trip, String> price;
    	TableColumn<Trip, String> inExternal;
    	TableColumn<Trip, String> stopType;
    	TableColumn<Trip, String> vehicle;
    	
    	
    	source = new TableColumn<>("Source");
    	destination = new TableColumn<>("Destination");
    	date = new TableColumn<>("Date");
    	price = new TableColumn<>("Price");
    	inExternal = new TableColumn<>("Type");
    	stopType = new TableColumn<>("Stop Type");
    	vehicle = new TableColumn<>("Vehicle");
    	
    	
    	source.setCellValueFactory(new PropertyValueFactory<Trip, String>("source"));
    	destination.setCellValueFactory(new PropertyValueFactory<Trip, String>("destination"));
    	date.setCellValueFactory(new PropertyValueFactory<Trip, String>("tripDate")); //change this
    	price.setCellValueFactory(new PropertyValueFactory<Trip, String>("price"));
    	inExternal.setCellValueFactory(new PropertyValueFactory<Trip, String>("isInternal")); //change this
    	stopType.setCellValueFactory(new PropertyValueFactory<Trip, String>("stopType"));
    	vehicle.setCellValueFactory(new PropertyValueFactory<Trip, String>("vehicle")); //change this


    //Trip trip = new Trip(0, "hello", "world", 0, null, false, 0, null, new Car("1","car","1","1"));


    	
    	tblBookedTrips.getColumns().add(source);
    	tblBookedTrips.getColumns().add(destination);
    	tblBookedTrips.getColumns().add(date);
    	tblBookedTrips.getColumns().add(price);
    	tblBookedTrips.getColumns().add(inExternal);
    	tblBookedTrips.getColumns().add(stopType);
    	tblBookedTrips.getColumns().add(vehicle);
    	
    
    	
    	//Add to correct table
    	//tblBookedTrips.getItems().add(trip);
    	///tbl_PrevTrips.getItems().add(trip);
		System.out.println("updating table");

    	for(Trip trip : tripList) {
    		for(String n : driver.getTripIDs()) {
    			if( Integer.parseInt(n) ==   trip.getID()) {
    					tblBookedTrips.getItems().add(trip);
    			}
    		}
    	}
    
//    	Person person = tblBookedTrips.getSelectionModel().getSelectedItem();
//		System.out.println(person.getFirstName());
    	
    }
}



