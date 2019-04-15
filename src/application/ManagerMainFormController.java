package application;
import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManagerMainFormController {
	private Manager manager;
	private Admin admin = new Admin();
	

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@FXML
    private JFXButton btn_logOutCustomer, btn_Trips, btn_addtrip, btn_Close, btn_ApplyFilter, btn_savenewtrip;
	
	@FXML
	private AnchorPane pn_Trips, pn_title, pn_addtrip;
	
	@FXML
	private Label lblUserName;
	
	@FXML
	private JFXTextField txt_Source,txt_Destination,txt_NumOfSeats2;
	
	@FXML
	private JFXRadioButton RB_Internal, RB_External, RB_NonStop, RB_OneStop, RB_ManyStops;
	
	@FXML
	private DatePicker date;
	
	@FXML
	private ComboBox<String> driverBox; 
	
	@FXML
	private ComboBox<String> vehicleBox; 
	
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
    	}else if( event.getSource() == btn_Trips) {
    		pn_Trips.toFront();
    		pn_title.toFront();
    	}else if( event.getSource() == btn_addtrip) {
    		pn_addtrip.toFront();
    		pn_title.toFront();
    	}else if( event.getSource() == btn_Close) {
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.close();
    	}else if(event.getSource() == btn_ApplyFilter){
    		String source = txt_Source.getText();
    		String destination = txt_Destination.getText();
    		String num = txt_NumOfSeats2.getText();

    		System.out.println("heree" + num);
    		if(num.equals("")) num = "-1";
    		int numOfSeats = Integer.parseInt(num);

    		this.setTable(manager.listTrip(source,destination, numOfSeats));
    	}else if( event.getSource() == btn_savenewtrip) {
    		
    	}
    }
    
    public void initializeData() throws IOException {
    	System.out.println(manager.getFirstName());
    	lblUserName.setText(manager.getFirstName() + " " + manager.getLastName());
    	manager.setTripsList(admin.listTrips(admin.listVechiles()));
    	setTable(manager.getTripsList());
    	for(Person x : manager.getDriversList()) {
    		driverBox.getItems().add(x.getFirstName());
    	}
    	
    	for(Vehicle x : manager.getVehiclesList()) {
    		vehicleBox.getItems().add(x.getID());
    	}
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

    	for(Trip trip : tripList)
    		tblBookedTrips.getItems().add(trip);
    			
    	
    
//    	Person person = tblBookedTrips.getSelectionModel().getSelectedItem();
//		System.out.println(person.getFirstName());
    	
    }

}