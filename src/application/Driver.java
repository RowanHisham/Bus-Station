package application;
import java.util.*;  

public class Driver extends Person {

	private String[] tripIDs ;
	private ArrayList<Trip> tripsList = new ArrayList<Trip>();

	Driver(String firstName, String lastName,String password, String[] tripIDs){
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPassword(password);
		this.setTripIDs(tripIDs);
	}

	public String[] getTripIDs() {
		return tripIDs;
	}

	public void setTripIDs(String[] tripIDs) {
		this.tripIDs = tripIDs;
	}
	
	
	public ArrayList<Trip> getTripsList() {
		return tripsList;
	}

	public void setTripsList(ArrayList<Trip> tripsList) {
		this.tripsList = tripsList;
	}

}
