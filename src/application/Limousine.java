package application;

public class Limousine extends Vehicle{

	Limousine(String ID, String model, String year, String color){
		this.setID(ID);
		this.setModel(model);
		this.setYear(year);
		this.setColor(color);
		this.setNumberOfSeats(3);
	}
}
