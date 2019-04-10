package application;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Admin implements AdminActions {
	
	@Override
    public ArrayList<Vehicle> listVechiles() {
		// TODO Auto-generated method stub
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
                
        try {
         String ID;
	 String model;
	 String year;
	 String color;
         String Type;
	 //int numseats;
        
         File inputFile = new File("C:\\Users\\Safynaz\\Desktop\\Vechile.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("automobile");
         System.out.println("----------------------------");
         
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                
               Element eElement = (Element) nNode;
                 Type=  eElement.getAttribute("type");
                 ID = eElement.getElementsByTagName("ID").item(0).getTextContent();
                 model = eElement.getElementsByTagName("model").item(0).getTextContent();
                 year= eElement.getElementsByTagName("year").item(0).getTextContent();
                 color= eElement.getElementsByTagName("color").item(0).getTextContent();
                 //numseats = Integer.parseInt( eElement.getElementsByTagName("numseat").item(0).getTextContent());
                 if(Type.compareTo("Bus")==0){
                    Bus newV = new Bus(ID, model,year,color);
                    vehicles.add(newV);
                 }
                 else if(Type.compareTo("Car")==0){
                     Car newV = new Car(ID,model,year,color);
                     vehicles.add(newV);
                 }
                 else if(Type.compareTo("Limousine")==0){
                     Limousine newV = new Limousine(ID,model,year,color);
                     vehicles.add(newV);
                 }
                 
            }
         }
         
         return vehicles;
      } catch (Exception e) {
         e.printStackTrace();
      }
		return null;
	}

	@Override
    public ArrayList<Trip> listTrips(ArrayList<Vehicle> listVehicle) {
		// TODO Auto-generated method stub
        Trip trip1;
        //Car newCar = new Car("100","BMW","1990","Black");
        ArrayList<Trip> trips = new ArrayList<Trip>();
        try {
            int ID;
            String source;
            String destination;
            int bookedSeats;
            Date tripDate;
            boolean isInternal;
            StopType temp = null;
            double price;
            String Vehicle;
            int stop;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            File inputFile = new File("C:\\Users\\Safynaz\\Desktop\\trips.xml");
   //       File inputFile = new File("/Users/rowanhisham/Downloads/trips.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("trip");
            System.out.println("----------------------------");

            for (int i = 0; i < nList.getLength(); i++) {
               Node nNode = nList.item(i);
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;
                    ID = Integer.parseInt(eElement.getElementsByTagName("tripId").item(0).getTextContent());
                    source= eElement.getElementsByTagName("source").item(0).getTextContent();
                    destination= eElement.getElementsByTagName("destination").item(0).getTextContent();
                    bookedSeats = Integer.parseInt( eElement.getElementsByTagName("bookedSeats").item(0).getTextContent());
                    tripDate = formatter.parse(eElement.getElementsByTagName("date").item(0).getTextContent());
                    isInternal= Boolean.parseBoolean (eElement.getElementsByTagName("isInternal").item(0).getTextContent());
                    stop = Integer.parseInt( eElement.getElementsByTagName("stopType").item(0).getTextContent());
                    Vehicle = eElement.getElementsByTagName("vehicle").item(0).getTextContent();
                    price = Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent());
                    switch(stop){
                        case 0 : temp = StopType.NOSTOPS;break;
                        case 1 : temp= StopType.ONESTOP;break;
                        case 2: temp= StopType.MANYSTOPS;break;
                    }           //trip1 = new Trip(ID,source,destination,bookedSeats,tripDate,isInternal,price,temp,newCar);
                                 //trips.add(trip1);
                         for(Vehicle vehicle : listVehicle){
                           if(Vehicle.compareTo(vehicle.getID())==0){
                              int numOfSeats = vehicle.getNumberOfSeats();
                              if(numOfSeats == 4){
                               Car newCar = new Car(vehicle.getID(),vehicle.getModel(),vehicle.getYear(),vehicle.getColor());
                                trip1 = new Trip(ID,source,destination,bookedSeats,tripDate,isInternal,price,temp,newCar);
                                trips.add(trip1);
                              }
                              else if(numOfSeats == 30){
                             Bus newCar = new Bus(vehicle.getID(),vehicle.getModel(),vehicle.getYear(),vehicle.getColor());
                            trip1 = new Trip(ID,source,destination,bookedSeats,tripDate,isInternal,price,temp,newCar);
                             trips.add(trip1);
                              }
                              else if(numOfSeats ==3){
                             Limousine newCar = new Limousine(vehicle.getID(),vehicle.getModel(),vehicle.getYear(),vehicle.getColor());
                                trip1 = new Trip(ID,source,destination,bookedSeats,tripDate,isInternal,price,temp,newCar);
                                 trips.add(trip1);
                              }
                              break;
                           }
}

                    
 
             }
            }
    
            return trips;
      } catch (Exception e) {
         e.printStackTrace();
      }
		return null;
	}

	@Override
    public ArrayList<Person> listDrivers() {
		// TODO Auto-generated method stub
        ArrayList<Person> persons = new ArrayList<Person>();
        Driver Driver1; 
        try {
           String Password;
           String FirstName;
           String LastName;
           String TripId;
           String Type;
           File inputFile = new File("C:\\Users\\Safynaz\\Desktop\\Driver.xml");
           //File inputFile = new File("/Users/rowanhisham/Downloads/test.xml");
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.parse(inputFile);
           doc.getDocumentElement().normalize();
           System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
           NodeList nList = doc.getElementsByTagName("employee");
           System.out.println("----------------------------");
           
           for (int temp = 0; temp < nList.getLength(); temp++) {
              Node nNode = nList.item(temp);
              if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;
                  Type = eElement.getAttribute("type");
                  if(Type.compareTo("Driver")==0){
                    FirstName =  eElement.getElementsByTagName("firstname").item(0).getTextContent();
                    LastName = eElement.getElementsByTagName("lastname").item(0).getTextContent();
                    Password =  eElement.getElementsByTagName("password").item(0).getTextContent();
                    TripId = eElement.getElementsByTagName("tripid").item(0).getTextContent();
                    String IDs[] = TripId.split(",");

                        Driver driver = new Driver(FirstName, LastName,Password,IDs);  
                        persons.add(driver);
                  }
              }
           }
           return persons;
        } catch (Exception e) {
           e.printStackTrace();
        }
		return null;
	}

	@Override
    public Customer AuthenticateLogInCustomer(String tempUser , String tempPass) {
		// TODO Auto-generated method stub
         Customer customer1;
         
        try {
           String UserName ;
           String Password;
           String FirstName;
           String LastName;
           String TripId;
           File inputFile = new File("C:\\Users\\Safynaz\\Desktop\\test.xml");
          // File inputFile = new File("/Users/rowanhisham/Downloads/test.xml");
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.parse(inputFile);
           doc.getDocumentElement().normalize();
           System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
           NodeList nList = doc.getElementsByTagName("person");
           System.out.println("----------------------------");
           
           for (int temp = 0; temp < nList.getLength(); temp++) {
              Node nNode = nList.item(temp);
              if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;
                   FirstName =  eElement.getElementsByTagName("firstname").item(0).getTextContent();
                   LastName = eElement.getElementsByTagName("lastname").item(0).getTextContent();
                   UserName= eElement.getElementsByTagName("username").item(0).getTextContent();
                   Password =  eElement.getElementsByTagName("password").item(0).getTextContent();
                   TripId = eElement.getElementsByTagName("tripid").item(0).getTextContent();
                   String IDs[] = TripId.split(",");

               if(UserName.compareToIgnoreCase(tempUser) == 0 && Password.compareTo(tempPass) == 0){
                       customer1 = new Customer(FirstName , LastName , UserName , Password , IDs);                        
                       return customer1;
                   }
              }
           }
        } catch (Exception e) {
           e.printStackTrace();
        }
      
		return null;
	}

	@Override
    public Person AuthenticateLogInEmployee(String tempUser, String tempPass) {
		// TODO Auto-generated method stub
        try {
           String Password;
           String FirstName;
           String LastName;
           String TripId;
           String Type;
           File inputFile = new File("C:\\Users\\Safynaz\\Documents\\NetBeansProjects\\xml\\src\\xml\\Driver.xml");
           //File inputFile = new File("/Users/rowanhisham/Downloads/test.xml");
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.parse(inputFile);
           doc.getDocumentElement().normalize();
           System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
           NodeList nList = doc.getElementsByTagName("employee");
           System.out.println("----------------------------");
           
           for (int temp = 0; temp < nList.getLength(); temp++) {
              Node nNode = nList.item(temp);
              if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;
                   FirstName =  eElement.getElementsByTagName("firstname").item(0).getTextContent();
                   LastName = eElement.getElementsByTagName("lastname").item(0).getTextContent();
                   Password =  eElement.getElementsByTagName("password").item(0).getTextContent();

               if(FirstName.compareToIgnoreCase(tempUser) == 0 && Password.compareTo(tempPass) == 0){
                   Type = eElement.getAttribute("type");
                   if(Type.compareTo("Driver")==0){
                    TripId = eElement.getElementsByTagName("tripid").item(0).getTextContent();
                    String IDs[] = TripId.split(",");
                    Driver driver = new Driver(FirstName , LastName , Password , IDs);                        
                    return driver;
                   }
                   else if(Type.compareTo("Manager")==0){
                       
                       //Manager manager = new Manager(FirstName, LastName , Password);
                       //return manager;
                   }
              }
             
           }
          }
        } catch (Exception e) {
           e.printStackTrace();
        }
		return null;
	}

	@Override
	public void saveVehicles(ArrayList<Vehicle> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveTrips(ArrayList<Trip> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveDrivers(ArrayList<Person> list) {
		// TODO Auto-generated method stub
		
	}

}
