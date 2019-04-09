package application;
import java.text.SimpleDateFormat;
import java.util.*;

enum StopType{
	NOSTOPS,
	ONESTOP,
	MANYSTOPS,
}

public class Trip {
	private int ID;
	private String source;
	private String destination;
	private int bookedSeats;
	private Date tripDate;
	//driver object
	private Vehicle vehicle;
	private boolean isInternal;
	private StopType stopType;
	private double price;
	
	Trip(int ID, String source, String destination, int bookedSeats, Date tripDate, boolean isInternal, double price, StopType stopType,
			Vehicle vehicle){
		this.ID = ID;
		this.source = source;
		this.destination = destination;
		this.bookedSeats = bookedSeats;
		this.tripDate = tripDate;
		this.isInternal = isInternal;
		this.price = price;
		this.stopType = stopType;
		this.vehicle = vehicle;
	}
	
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getBookedSeats() {
		return bookedSeats;
	}

	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public Date getTripDate() {
       // SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		return tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	public String getVehicle() {
		return vehicle.getModel();
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getIsInternal() {	
		return isInternal?"Internal":"External";
	}

	public void setInternal(boolean isInternal) {
		this.isInternal = isInternal;
	}

	public StopType getStopType() {
		return stopType;
	}

	public void setStopType(StopType stopType) {
		this.stopType = stopType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getVehicleName() {
		return vehicle.getModel();
	}
	
	
}
