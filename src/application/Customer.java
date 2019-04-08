package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javafx.fxml.FXMLLoader;

public class Customer extends Person implements CustomerActions {

	private ArrayList<Integer> tripIDs = new ArrayList<Integer>(5);
	private ArrayList<Trip> tripsList = new ArrayList<Trip>();
	private Admin admin = new Admin();


	Customer(String firstName, String lastName, String userName, String password, String[] tripIDs) throws IOException{
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUserName(userName);
		this.setPassword(password);
		this.setTripIDs(tripIDs);
	}
	
	
	public ArrayList<Integer> getTripIDs() {
		return tripIDs;
	}

	public void setTripIDs(String [] tripIDs) {
		this.tripIDs = tripIDs;
	}

	public ArrayList<Trip> getTripsList() {
		return tripsList;
	}

	public void setTripsList(ArrayList<Trip> tripsList) {
		this.tripsList = tripsList;
	}
	
	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	@Override
	public ArrayList<Trip> listTrip(Map<String, Object> filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkAvailability(Trip selected1, Trip selected2, Integer numOfSeats) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Ticket reserve(Trip selected, Trip selected2, Person customer, Integer numOfSeats) {
		// TODO Auto-generated method stub
		return null;
	}

}
