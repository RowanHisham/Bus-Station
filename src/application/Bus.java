package application;

public class Bus extends Vehicle {
	
	Bus(String ID, String model, String year, String color){
		this.setID(ID);
		this.setModel(model);
		this.setYear(year);
		this.setColor(color);
		this.setNumberOfSeats(30);
	}
}
