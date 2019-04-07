package application;

public class Ticket {

	private double priceOfTrip;
	private double amountToPay;
	private String numOfSeats;
	private int tripID;
	private boolean isOneWay;
	
	Ticket(double priceOfTrip, String numOfSeats,int tripID, boolean isOneWay){
		this.numOfSeats = numOfSeats;
		this.tripID = tripID;
		this.isOneWay = isOneWay;
		this.amountToPay = isOneWay? priceOfTrip: priceOfTrip - priceOfTrip*0.01;
	}

	public double getAmountToPay() {
		return amountToPay;
	}

	public String getNumOfSeats() {
		return numOfSeats;
	}

	public int getTripID() {
		return tripID;
	}

	public boolean isOneWay() {
		return isOneWay;
	}

	public double getPriceOfTrip() {
		return priceOfTrip;
	}
}
