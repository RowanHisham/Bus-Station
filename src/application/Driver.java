package application;
import java.util.*;  

public class Driver extends Person {

	private ArrayList<Integer> tripIDs = new ArrayList<Integer>(5);
	private ArrayList<Trip> tripsList = new ArrayList<Trip>();

	Driver(String firstName, String lastName,String password, ArrayList<Integer> tripIDs){
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPassword(password);
		this.setTripIDs(tripIDs);
	}

	public ArrayList<Integer> getTripIDs() {
		return tripIDs;
	}

	public void setTripIDs(ArrayList<Integer> tripIDs) {
		this.tripIDs = tripIDs;
	}
	
	
	public ArrayList<Trip> getTripsList() {
		return tripsList;
	}

	public void setTripsList(ArrayList<Trip> tripsList) {
		this.tripsList = tripsList;
	}

}
