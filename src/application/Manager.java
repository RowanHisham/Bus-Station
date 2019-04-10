package application;

import java.util.ArrayList;

public class Manager extends Person {

	private ArrayList<Trip> tripsList = new ArrayList<Trip>();
	private ArrayList<Vehicle> vehiclesList = new ArrayList<Vehicle>();
	private ArrayList<Person> driversList = new ArrayList<Person>();
	private Admin admin = new Admin();

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
}
