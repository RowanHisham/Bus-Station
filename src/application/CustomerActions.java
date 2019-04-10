package application;
import java.util.*;

public interface CustomerActions {
	
	public ArrayList<Trip> listTrip(String source,String destination,int numOfSeats);
	
	public boolean checkAvailability(Trip selected1, Trip selected2, Integer numOfSeats);
	
	public Ticket reserve(Trip selected,Trip selected2,Person customer, Integer numOfSeats);
}
