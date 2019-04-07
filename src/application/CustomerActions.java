package application;
import java.util.*;

public interface CustomerActions {
	
	public ArrayList<Trip> listTrip(Map<String, Object> filter);
	
	public boolean checkAvailability(Trip selected1, Trip selected2, Integer numOfSeats);
	
	public Ticket reserve(Trip selected,Trip selected2,Person customer, Integer numOfSeats);
}
