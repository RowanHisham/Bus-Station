package application;
import java.util.*;
public interface AdminActions {
	
	public ArrayList<Vehicle> listVechiles();
	public ArrayList<Trip> listTrips(ArrayList<Vehicle> listVechiles);
	public ArrayList<Person> listDrivers();
	
	public Person AuthenticateLogInCustomer(String username , String password);
	public Person AuthenticateLogInEmployee(String Firstname, String password);
	
	public void saveVehicles(ArrayList<Vehicle> list);
	public void saveTrips(ArrayList<Trip> list);
	public void saveDrivers(ArrayList<Person> list);
	
}
