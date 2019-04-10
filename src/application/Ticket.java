package application;

public class Ticket {

	private double priceOfTrip;
	private double amountToPay;
	private Integer numOfSeats;
	private int tripID;
	private boolean isOneWay;
	
	Ticket(double priceOfTrip, Integer numOfSeats, boolean isOneWay){
		this.priceOfTrip = priceOfTrip;
		this.numOfSeats = numOfSeats;
		this.isOneWay = isOneWay;
		this.amountToPay = isOneWay? priceOfTrip: priceOfTrip - priceOfTrip*0.01;

	}

	public double getAmountToPay() {
		return amountToPay;
	}

	public Integer getNumOfSeats() {
		return numOfSeats;
	}

	public int getTripID() {
		return tripID;
	}

	public boolean isOneWay() {
		return isOneWay;
	}

	public double getPriceOfTrip() {
		System.out.println(isOneWay);
		if(this.isOneWay) {
			return (double)this.priceOfTrip*this.numOfSeats;
		}else {
			return this.priceOfTrip*this.numOfSeats - (this.priceOfTrip*this.numOfSeats)*0.2;
		}
	}
}
