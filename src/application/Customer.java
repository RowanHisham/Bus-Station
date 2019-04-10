package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
			System.out.println("num of seats " + numOfSeats);
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
		int numOfAvailableSeats = selected1.getVehicleObj().getNumberOfSeats() - selected1.getBookedSeats();
		return  numOfAvailableSeats >= numOfSeats;
	}

	@Override
	public Ticket reserve(Trip selected, Trip selected2, Customer customer, Integer numOfSeats, boolean isOneWay) {
		// TODO Auto-generated method stub
		final int N = customer.getTripIDs().length;
		String[] newTripIds = Arrays.copyOf(customer.getTripIDs(), N + 1);
		newTripIds[N] = Integer.toString(selected.getID());
		customer.setTripIDs(newTripIds);
		Ticket ticket = new Ticket(selected.getPrice(),numOfSeats,isOneWay);
		return ticket;
	}
}
