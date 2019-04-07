package application;
import java.util.*;
public interface AdminActions {
	
	public ArrayList<Vehicle> listVechiles();
	public ArrayList<Trip> listTrips();
	public ArrayList<Person> listDrivers();
	
	public Person AuthenticateLogInCustomer();
	public Person AuthenticateLogInEmployee();
	
	public void saveVehicles(ArrayList<Vehicle> list);
	public void saveTrips(ArrayList<Trip> list);
	public void saveDrivers(ArrayList<Person> list);
	
}
