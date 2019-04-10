package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javafx.fxml.FXMLLoader;

public class Customer extends Person implements CustomerActions {

	private String[] tripIDs;
	private ArrayList<Trip> tripsList = new ArrayList<Trip>();
	private Admin admin = new Admin();
	private ArrayList<Trip> filteredTripList = new ArrayList<Trip>();


	Customer(String firstName, String lastName, String userName, String password, String[] tripIDs) throws IOException{
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUserName(userName);
		this.setPassword(password);
		this.setTripIDs(tripIDs);
	}
	
	
	public String[] getTripIDs() {
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
	public ArrayList<Trip> listTrip(String source,String destination,int numOfSeats){
		// TODO Auto-generated method stub
		filteredTripList.clear();
		
		if(source == "" && destination == "" && numOfSeats == -1) { 
			System.out.println("here");
			return tripsList;}
		
		for(Trip trip: tripsList) {
			int numOfavailableSeats = trip.getVehicleObj().getNumberOfSeats() - trip.getBookedSeats();
			System.out.println(numOfavailableSeats);
			if(trip.getSource().toLowerCase().contains(source.toLowerCase()) && 
					trip.getDestination().toLowerCase().contains(destination.toLowerCase()) && 
					numOfavailableSeats>numOfSeats){
				filteredTripList.add(trip);
			}
		}
		//tripsList.stream().map(Trip::getSource).filter(source::equals).findFirst().isPresent();
		return filteredTripList;
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
