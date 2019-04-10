package application;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.security.auth.callback.Callback;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.*;


public class CustomerMainFormController {
	private Customer customer;   
	private Admin admin = new Admin();

	@FXML
    private JFXButton btn_logOutCustomer, btn_Trips, btn_Dashboard, btn_AccountSettings, btn_Close, btn_CancelTrip, btn_Delete, 
    btn_ApplyFilter, btn_bookTicket, btn_Cancel,btn_Confirm, btn_NumOfSeats;
	
	@FXML
	private AnchorPane pn_Trips, pn_Dashboard, pn_title, pn_AccountSettings, pn_booking;
	
	@FXML
	private Label lblUserName, lbl_price;
	
	@FXML
	private JFXTextField txt_Source,txt_Destination,txt_NumOfSeats,txt_NumOfSeats2;
	
	@FXML
	TableView<Trip> tblBookedTrips = new TableView<Trip>();
	
	@FXML
	TableView<Trip> tbl_PrevTrips = new TableView<Trip>();
	
	@FXML
	TableView<Trip> tbl_Trips = new TableView<Trip>();
	
	@FXML
	private JFXCheckBox btn_isRoundTrip;

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
    	}else if(event.getSource() == btn_ApplyFilter) {
    		String source = txt_Source.getText();
    		String destination = txt_Destination.getText();
    		String num = txt_NumOfSeats2.getText();

    		System.out.println("heree" + num);
    		if(num.equals("")) num = "-1";
    		int numOfSeats = Integer.parseInt(num);

    		this.setTripsTable(customer.listTrip(source,destination, numOfSeats));
    	}else if( event.getSource() == btn_bookTicket) {
//    		System.out.println("here");
//    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Booking.fxml"));
//            Parent parent = fxmlLoader.load();
//       
//            Scene scene = new Scene(parent, 512, 254);
//            Stage stage = new Stage();
//            stage.initStyle(StageStyle.TRANSPARENT);
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setScene(scene);
//            stage.showAndWait();
    		pn_booking.setVisible(true);
    		pn_booking.toFront();
    	}else if ( event.getSource() == btn_Cancel) {
    		pn_booking.setVisible(false);
    	}else if ( event.getSource() == btn_Confirm) {
    		Trip trip = tbl_Trips.getSelectionModel().getSelectedItem();
    		if(customer.checkAvailability(trip, null, Integer.parseInt(txt_NumOfSeats.getText()))){
       			System.out.println(!btn_isRoundTrip.isSelected());
    			Ticket ticket = customer.reserve(trip, null, customer, Integer.parseInt(txt_NumOfSeats.getText()), !btn_isRoundTrip.isSelected());
    			
    			Alert alert = new Alert(AlertType.CONFIRMATION, "price of booked trip(s) = ".concat(Double.toString(ticket.getPriceOfTrip())), ButtonType.CLOSE);
        		alert.showAndWait();
        		
    			pn_booking.setVisible(false);
    			txt_NumOfSeats.setText("");
    			this.setTable(customer.getTripsList());
    		}
    			
    	}else if( event.getSource() == btn_Dashboard) { 
    		pn_Dashboard.toFront();
    		pn_title.toFront();
    	}else if( event.getSource() == btn_AccountSettings) {
    		pn_AccountSettings.toFront();
    		pn_title.toFront();
    	}else if( event.getSource() == btn_Close) {
    		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
    		window.close();
    	}else if( event.getSource() == btn_CancelTrip) {
    		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to cancel your reservation ?", ButtonType.YES, ButtonType.NO);
    		alert.showAndWait();

    		if (alert.getResult() == ButtonType.YES) {
    		    //do stuff
    	    	Trip trip = tblBookedTrips.getSelectionModel().getSelectedItem();
    	    	//search for selected trip and delete its ID
    			if(trip != null) {
    				int ID = trip.getID();
    				for(int i=0; i< customer.getTripIDs().length; i++) {
    	    	    	System.out.println(Arrays.toString(customer.getTripIDs()));
    					if(ID == Integer.parseInt(customer.getTripIDs()[i])){
    						List<String> list = new ArrayList<String>(Arrays.asList(customer.getTripIDs()));
    						list.remove(customer.getTripIDs()[i]);
    						customer.setTripIDs(list.toArray(new String[0]));
    						//increment number of seats
    						int c=0;
    						for(Trip x : customer.getTripsList()) {
    							if( x.getID() == ID) {
    								customer.getTripsList().get(c).setBookedSeats( customer.getTripsList().get(c).getBookedSeats() + 1);
    								System.out.println(customer.getTripsList().get(c).getBookedSeats());
    							}
    						}
    						
    						this.setTable(customer.getTripsList());
    						return;
    					}
    				}
    			}
    		}
    	}else if(event.getSource() == btn_Delete) {
    		Trip trip = tbl_PrevTrips.getSelectionModel().getSelectedItem();
    		//search for selected trip and delete its ID
    		if(trip != null) {
    			int ID = trip.getID();
    			for(int i=0; i< customer.getTripIDs().length; i++) {
    				if(ID == Integer.parseInt(customer.getTripIDs()[i])){
    					List<String> list = new ArrayList<String>(Arrays.asList(customer.getTripIDs()));
    					list.remove(customer.getTripIDs()[i]);
    					customer.setTripIDs(list.toArray(new String[0]));
    					//increment number of seats
    					int c=0;
    					for(Trip x : customer.getTripsList()) {
    						if( x.getID() == ID) {
    							customer.getTripsList().get(c).setBookedSeats( customer.getTripsList().get(c).getBookedSeats() + 1);
    							System.out.println(customer.getTripsList().get(c).getBookedSeats());
    						}
    					}

    					this.setTable(customer.getTripsList());
    					return;
    				}
    			}
    		}
    	}
    }
    
    
    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		System.out.println(this.customer.getFirstName());
	}
    
    public void initializeData() {
    	lblUserName.setText(customer.getFirstName() + " " + customer.getLastName());
    	customer.setTripsList(admin.listTrips(admin.listVechiles()));
    }
    
    public void setTripsTable(ArrayList<Trip> tripList) {
//    	customer.setTripsList(admin.listTrips());
    	
    	tbl_Trips.getItems().clear();
    	tbl_Trips.getColumns().clear();
    	tbl_Trips.refresh();
    	
    	TableColumn<Trip, String> source3;
    	TableColumn<Trip, String> destination3;
    	TableColumn<Trip, String> date3;
    	TableColumn<Trip, String> price3;
    	TableColumn<Trip, String> inExternal3;
    	TableColumn<Trip, String> stopType3;
    	TableColumn<Trip, String> vehicle3;
    	
    	source3 = new TableColumn<>("Source");
    	destination3 = new TableColumn<>("Destination");
    	date3 = new TableColumn<>("Date");
    	price3 = new TableColumn<>("Price");
    	inExternal3 = new TableColumn<>("Type");
    	stopType3 = new TableColumn<>("Stop Type");
    	vehicle3 = new TableColumn<>("Vehicle");
    	
    	source3.setCellValueFactory(new PropertyValueFactory<Trip, String>("source"));
    	destination3.setCellValueFactory(new PropertyValueFactory<Trip, String>("destination"));
    	date3.setCellValueFactory(new PropertyValueFactory<Trip, String>("tripDate")); //change this
    	price3.setCellValueFactory(new PropertyValueFactory<Trip, String>("price"));
    	inExternal3.setCellValueFactory(new PropertyValueFactory<Trip, String>("isInternal")); //change this
    	stopType3.setCellValueFactory(new PropertyValueFactory<Trip, String>("stopType"));
    	vehicle3.setCellValueFactory(new PropertyValueFactory<Trip, String>("vehicle")); //change this
    	
    	//Trip trip = new Trip(0, "hello", "world", 0, null, false, 0, null, new Car("1","car","1","1"));
    	
    	tbl_Trips.getColumns().add(source3);
    	tbl_Trips.getColumns().add(destination3);
    	tbl_Trips.getColumns().add(date3);
    	tbl_Trips.getColumns().add(price3);
    	tbl_Trips.getColumns().add(inExternal3);
    	tbl_Trips.getColumns().add(stopType3);
    	tbl_Trips.getColumns().add(vehicle3);
    	
    	//Add all trips
    	//tbl_Trips.getItems().add(trip);
    	for(Trip trip : tripList) {
    		tbl_Trips.getItems().add(trip);
    	}
    	//System.out.println(listTrips.get(0).getSource());

    }
        
    public void setTable(ArrayList<Trip> tripList) throws IOException {
    	//System.out.println("here");

//    	tbl_PrevTrips.getItems().removeAll(tripList);

    	tbl_PrevTrips.getItems().clear();
    	tbl_PrevTrips.getColumns().clear();
    	tbl_PrevTrips.refresh();
    	
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
    	
    	TableColumn<Trip, String> source2;
    	TableColumn<Trip, String> destination2;
    	TableColumn<Trip, String> date2;
    	TableColumn<Trip, String> price2;
    	TableColumn<Trip, String> inExternal2;
    	TableColumn<Trip, String> stopType2;
    	TableColumn<Trip, String> vehicle2;
    	
    	
    	
    	
    	source = new TableColumn<>("Source");
    	destination = new TableColumn<>("Destination");
    	date = new TableColumn<>("Date");
    	price = new TableColumn<>("Price");
    	inExternal = new TableColumn<>("Type");
    	stopType = new TableColumn<>("Stop Type");
    	vehicle = new TableColumn<>("Vehicle");
    	
    	source2 = new TableColumn<>("Source");
    	destination2 = new TableColumn<>("Destination");
    	date2 = new TableColumn<>("Date");
    	price2 = new TableColumn<>("Price");
    	inExternal2 = new TableColumn<>("Type");
    	stopType2 = new TableColumn<>("Stop Type");
    	vehicle2 = new TableColumn<>("Vehicle");
    	
    	
    	
    	
    	source.setCellValueFactory(new PropertyValueFactory<Trip, String>("source"));
    	destination.setCellValueFactory(new PropertyValueFactory<Trip, String>("destination"));
    	date.setCellValueFactory(new PropertyValueFactory<Trip, String>("tripDate")); //change this
    	price.setCellValueFactory(new PropertyValueFactory<Trip, String>("price"));
    	inExternal.setCellValueFactory(new PropertyValueFactory<Trip, String>("isInternal")); //change this
    	stopType.setCellValueFactory(new PropertyValueFactory<Trip, String>("stopType"));
    	vehicle.setCellValueFactory(new PropertyValueFactory<Trip, String>("vehicle")); //change this


    	source2.setCellValueFactory(new PropertyValueFactory<Trip, String>("source"));
    	destination2.setCellValueFactory(new PropertyValueFactory<Trip, String>("destination"));
    	date2.setCellValueFactory(new PropertyValueFactory<Trip, String>("tripDate"));
    	price2.setCellValueFactory(new PropertyValueFactory<Trip, String>("price"));
    	inExternal2.setCellValueFactory(new PropertyValueFactory<Trip, String>("isInternal")); //changethis
    	stopType2.setCellValueFactory(new PropertyValueFactory<Trip, String>("stopType"));
    	vehicle2.setCellValueFactory(new PropertyValueFactory<Trip, String>("vehicle"));
    	  	

    //Trip trip = new Trip(0, "hello", "world", 0, null, false, 0, null, new Car("1","car","1","1"));


    	
    	tblBookedTrips.getColumns().add(source);
    	tblBookedTrips.getColumns().add(destination);
    	tblBookedTrips.getColumns().add(date);
    	tblBookedTrips.getColumns().add(price);
    	tblBookedTrips.getColumns().add(inExternal);
    	tblBookedTrips.getColumns().add(stopType);
    	tblBookedTrips.getColumns().add(vehicle);
    	
    	
    	tbl_PrevTrips.getColumns().add(source2);
    	tbl_PrevTrips.getColumns().add(destination2);
    	tbl_PrevTrips.getColumns().add(date2);
    	tbl_PrevTrips.getColumns().add(price2);
    	tbl_PrevTrips.getColumns().add(inExternal2);
    	tbl_PrevTrips.getColumns().add(stopType2);
    	tbl_PrevTrips.getColumns().add(vehicle2);
    	
    
    	
    	//Add to correct table
    	//tblBookedTrips.getItems().add(trip);
    	///tbl_PrevTrips.getItems().add(trip);
		System.out.println("updating table");

    	for(Trip trip : tripList) {
    		for(String n : customer.getTripIDs()) {
    			if( Integer.parseInt(n) ==   trip.getID()) {
    				Date currentDate = new Date();
    				if( currentDate.before(trip.getTripDate())){
    					tblBookedTrips.getItems().add(trip);
    				}else {
    					tbl_PrevTrips.getItems().add(trip);
    				}
    			}
    		}
    	}
    

    	
//    	Person person = tblBookedTrips.getSelectionModel().getSelectedItem();
//		System.out.println(person.getFirstName());
    	
    }
}
