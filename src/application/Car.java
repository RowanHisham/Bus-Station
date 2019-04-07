package application;

public class Car extends Vehicle{
	
	Car(String ID, String model, String year, String color){
		this.setID(ID);
		this.setModel(model);
		this.setYear(year);
		this.setColor(color);
		this.setNumberOfSeats(4);
	}
}
