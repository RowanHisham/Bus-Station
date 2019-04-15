package application;
import java.util.*;
public interface AdminActions {
	
	public ArrayList<Vehicle> listVechiles();
	public ArrayList<Trip> listTrips(ArrayList<Vehicle> listVechiles);
	public ArrayList<Person> listDrivers();
	
	public Person AuthenticateLogInCustomer(String username , String password);
        public void ModifyCustomer(Customer x);
	public Driver AuthenticateLogInDriver(String tempUser, String tempPass);
        public Driver ModifyDriver(Driver x);
        public Manager AuthenticateLogInManger(String tempUser, String tempPass);
        public int AuthenticateEmoployee(String tempUser, String tempPass);
	
	public void saveVehicles(ArrayList<Vehicle> list);
	public void saveTrips(ArrayList<Trip> list);
	public void saveDrivers(ArrayList<Person> list);
	
}
