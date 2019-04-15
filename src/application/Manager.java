package application;

import java.util.ArrayList;

public class Manager extends Person {

	private ArrayList<Trip> tripsList = new ArrayList<Trip>();
	private ArrayList<Vehicle> vehiclesList = new ArrayList<Vehicle>();
	private ArrayList<Person> driversList = new ArrayList<Person>();
	private Admin admin = new Admin();
	private ArrayList<Trip> filteredTripList = new ArrayList<Trip>();


	Manager(String firstName, String lastName,String password,ArrayList<Trip> tripsList,ArrayList<Vehicle> vehiclesList,
			ArrayList<Person> driversList ){
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPassword(password);
		setDriversList(driversList);
		setTripsList(tripsList);
		setVehiclesList(vehiclesList);
	}

	public ArrayList<Trip> getTripsList() {
		return tripsList;
	}

	public void setTripsList(ArrayList<Trip> tripsList) {
		this.tripsList = tripsList;
	}

	public ArrayList<Vehicle> getVehiclesList() {
		return vehiclesList;
	}

	public void setVehiclesList(ArrayList<Vehicle> vehiclesList) {
		this.vehiclesList = vehiclesList;
	}

	public ArrayList<Person> getDriversList() {
		return driversList;
	}

	public void setDriversList(ArrayList<Person> driversList) {
		this.driversList = driversList;
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
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
}
