package application;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
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
        
        // File inputFile = new File("C:\\Users\\Safynaz\\Desktop\\cars.xml");
         File inputFile = new File("/Users/rowanhisham/eclipse-workspace/BusStation/Vechile.xml");
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
       //     File inputFile = new File("C:\\Users\\Safynaz\\Desktop\\trips2.xml");
            File inputFile = new File("/Users/rowanhisham/eclipse-workspace/BusStation/trips.xml");
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
        try {
           String Password;
           String FirstName;
           String LastName;
           String TripId;
           String Type;
           //File inputFile = new File("C:\\Users\\Safynaz\\Desktop\\Driver.xml");
           File inputFile = new File("/Users/rowanhisham/eclipse-workspace/BusStation/Driver.xml");
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
          // File inputFile = new File("C:\\Users\\Safynaz\\Desktop\\test.xml");
           File inputFile = new File("/Users/rowanhisham/eclipse-workspace/BusStation/test.xml");
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
public Driver AuthenticateLogInDriver(String tempUser, String tempPass) {
		// TODO Auto-generated method stub
        try {
           String Password;
           String FirstName;
           String LastName;
           String TripId;
           String Type;
          // File inputFile = new File("C:\\Users\\Safynaz\\Desktop\\Driver.xml");
           File inputFile = new File("/Users/rowanhisham/eclipse-workspace/BusStation/Driver.xml");
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
                       System.out.println(driver.getFirstName());
                       System.out.println(driver.getLastName());
                       System.out.println(driver.getPassword());
                    return driver;
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
public Manager AuthenticateLogInManger(String tempUser, String tempPass) {
		// TODO Auto-generated method stub
        try {
           String Password;
           String FirstName;
           String LastName;
           String Type;
           //File inputFile = new File("C:\\Users\\Safynaz\\Desktop\\Driver.xml");
           File inputFile = new File("/Users/rowanhisham/eclipse-workspace/BusStation/Driver.xml");
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
 
                    if(Type.compareTo("Manager")==0){
                       System.out.println("Manager");
                       ArrayList<Vehicle> listVech = new ArrayList<Vehicle>();
                       ArrayList<Trip> listTrip = new ArrayList<Trip>();
                       ArrayList<Person> persons = new ArrayList<Person>();
                       listVech = listVechiles();
                       listTrip = listTrips(listVech);
                       persons =  listDrivers(); 
                       Manager manager = new Manager(FirstName, LastName , Password, listTrip, listVech, persons);
                       return manager;
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
public int AuthenticateEmoployee(String tempUser, String tempPass) {
		// TODO Auto-generated method stub
        try {
           String Password;
           String FirstName;
           String LastName;
           String TripId;
           String Type;
          // File inputFile = new File("C:\\Users\\Safynaz\\Desktop\\Driver.xml");
           File inputFile = new File("/Users/rowanhisham/eclipse-workspace/BusStation/Driver.xml");
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
                   if(Type.compareToIgnoreCase("Driver")==0){
                        System.out.println("Driver");
                        return 1;
                   }
                   else if(Type.compareToIgnoreCase("Manager")==0){
                       System.out.println("Manager");
                       return 0;
                   }
              }
             
           }
          }
        } catch (Exception e) {
           e.printStackTrace();
        }
		return -1;
	}

	@Override
public void saveVehicles(ArrayList<Vehicle> listT) {
		// TODO Auto-generated method stub
        try {
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.newDocument();
                       // write the content into xml file
           TransformerFactory transformerFactory = TransformerFactory.newInstance();
           Transformer transformer = transformerFactory.newTransformer();
           DOMSource source = new DOMSource(doc);
           //StreamResult result = new StreamResult(new File("C:\\Users\\Safynaz\\Desktop\\cars2.xml"));
           StreamResult result = new StreamResult(new File("/Users/rowanhisham/eclipse-workspace/BusStation/cars2.xml"));

           Element rootElement = doc.createElement("Vehicle");
           doc.appendChild(rootElement);
           for(Vehicle newV : listT){
            Element automobile = doc.createElement("automobile");
            rootElement.appendChild(automobile);

            // carname element
            Element ID = doc.createElement("ID");
            ID.appendChild(doc.createTextNode(newV.getID()));
            automobile.appendChild(ID);

            Element model = doc.createElement("model");
            model.appendChild(doc.createTextNode(newV.getModel()));
            automobile.appendChild(model);

            Element year = doc.createElement("year");
            year.appendChild(doc.createTextNode(newV.getYear()));
            automobile.appendChild(year);

            Element color = doc.createElement("color");
            color.appendChild(doc.createTextNode(newV.getColor()));
            automobile.appendChild(color);

            Element numseat = doc.createElement("numseat");
            numseat.appendChild(doc.createTextNode(String.valueOf(newV.getNumberOfSeats())));
            automobile.appendChild(numseat);
            
            if(newV.getNumberOfSeats() == 3){
                Attr attr = doc.createAttribute("type");
                attr.setValue("Limo");
                automobile.setAttributeNode(attr);
            
            }
            else if(newV.getNumberOfSeats() == 4){
                Attr attr = doc.createAttribute("type");
                attr.setValue("Car");
                automobile.setAttributeNode(attr);
            
            }
            else if(newV.getNumberOfSeats() == 30){
                Attr attr = doc.createAttribute("type");
                attr.setValue("Bus");
                automobile.setAttributeNode(attr);
            }
            transformer.transform(source, result);
           }  
        } catch (Exception e){           
           e.printStackTrace();
            }	
	}
	@Override
public void saveTrips(ArrayList<Trip> list) {
		// TODO Auto-generated method stub
        try {
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.newDocument();
                       // write the content into xml file
           TransformerFactory transformerFactory = TransformerFactory.newInstance();
           Transformer transformer = transformerFactory.newTransformer();
           DOMSource source = new DOMSource(doc);
           //StreamResult result = new StreamResult(new File("C:\\Users\\Safynaz\\Desktop\\trips3.xml"));
           StreamResult result = new StreamResult(new File("/Users/rowanhisham/eclipse-workspace/BusStation/trips2.xml"));

           Element rootElement = doc.createElement("paths");
           doc.appendChild(rootElement);
           for(Trip newT : list){
            Element trip = doc.createElement("trip");
            rootElement.appendChild(trip);

            // carname element
            Element tripId = doc.createElement("tripId");
            tripId.appendChild(doc.createTextNode(String.valueOf(newT.getID())));
            trip.appendChild(tripId);

            Element source1 = doc.createElement("source");
            source1.appendChild(doc.createTextNode(newT.getSource()));
            trip.appendChild(source1);

            Element destination = doc.createElement("destination");
            destination.appendChild(doc.createTextNode(newT.getDestination()));
            trip.appendChild(destination);

            Element bookedSeats = doc.createElement("bookedSeats");
            bookedSeats.appendChild(doc.createTextNode(String.valueOf(newT.getBookedSeats())));
            trip.appendChild(bookedSeats);

            Element isInternal = doc.createElement("isInternal");
            String isInt = newT.getIsInternal();
            
            if(isInt.compareToIgnoreCase("external")==0){
                boolean isIntBool ;
                isIntBool = false;
                isInternal.appendChild(doc.createTextNode(String.valueOf(isIntBool)));
                 trip.appendChild(isInternal);
            }
                        
            if(isInt.compareToIgnoreCase("internal")==0){
                boolean isIntBool ;
                isIntBool = true;
                isInternal.appendChild(doc.createTextNode(String.valueOf(isIntBool)));
                 trip.appendChild(isInternal);
            }
          
            
            Element date= doc.createElement("date");
            Date myDate = newT.getTripDate();
            String pattern = "dd-MMM-yyyy HH:mm:ss";
            DateFormat df = new SimpleDateFormat(pattern);
            String tripdate = df.format(myDate);
            date.appendChild(doc.createTextNode(tripdate));
            trip.appendChild(date);
            
            String stoptype = newT.getStopType().toString();
            int StopInt;
            if(stoptype.compareToIgnoreCase("ONESTOP")== 0){
                
                StopInt = 1;
                Element stopType = doc.createElement("stopType");
                stopType.appendChild(doc.createTextNode(String.valueOf(StopInt)));    
                trip.appendChild(stopType);
                
            }
            else if(stoptype.compareToIgnoreCase("NOSTOPS")== 0){
                
                StopInt = 0;
                Element stopType = doc.createElement("stopType");
                stopType.appendChild(doc.createTextNode(String.valueOf(StopInt)));    
                trip.appendChild(stopType);
                
            }
            else if(stoptype.compareToIgnoreCase("MANYSTOPS")== 0){
                
                StopInt = 2;
                Element stopType = doc.createElement("stopType");
                stopType.appendChild(doc.createTextNode(String.valueOf(StopInt)));    
                trip.appendChild(stopType);
                
            }

            
            Element vehicle = doc.createElement("vehicle");
            vehicle.appendChild(doc.createTextNode(newT.getVehicleObj().getID()));
            trip.appendChild(vehicle);
            
            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode(String.valueOf(newT.getPrice())));
            trip.appendChild(price);
            
           
            transformer.transform(source, result);
           }  
        } catch (Exception e){           
           e.printStackTrace();
            }

	}

	@Override
public void saveDrivers(ArrayList<Person> list) {
		// TODO Auto-generated method stub
            try {
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.newDocument();
                       // write the content into xml file
           TransformerFactory transformerFactory = TransformerFactory.newInstance();
           Transformer transformer = transformerFactory.newTransformer();
           DOMSource source = new DOMSource(doc);
           //StreamResult result = new StreamResult(new File("C:\\Users\\Safynaz\\Desktop\\Drivers2.xml"));
           StreamResult result = new StreamResult(new File("/Users/rowanhisham/eclipse-workspace/BusStation/Drivers2.xml"));


           Element rootElement = doc.createElement("people");
           doc.appendChild(rootElement);
           for(Person newP : list){
            Element employee = doc.createElement("employee");
            rootElement.appendChild(employee);

            // carname element
            Element firstname = doc.createElement("firstname");
            firstname.appendChild(doc.createTextNode(newP.getFirstName()));
            employee.appendChild(firstname);

            Element lastname = doc.createElement("lastname");
            lastname.appendChild(doc.createTextNode(newP.getLastName()));
            employee.appendChild(lastname);

            Element password = doc.createElement("password");
            password.appendChild(doc.createTextNode(newP.getPassword()));
            employee.appendChild(password);


            if(newP instanceof Manager){
                
                Attr attr = doc.createAttribute("type");
                attr.setValue("Manager");
                employee.setAttributeNode(attr);
                
            }
            else if(newP instanceof Driver){
                
                Attr attr = doc.createAttribute("type");
                attr.setValue("Driver");
                employee.setAttributeNode(attr);
                
                Element tripId = doc.createElement("tripid");
                String IDS[] = ((Driver) newP).getTripIDs();
                
                String Drivertrips = "";
                for(int i = 0 ; i < IDS.length ; i++){
                    
                    Drivertrips = Drivertrips.concat(IDS[i]);
                    if((i != IDS.length -1)){
                      Drivertrips= Drivertrips.concat(",");
                    }
                    else{
                        tripId.appendChild(doc.createTextNode(Drivertrips));
                        employee.appendChild(tripId);
                    }
                }
                

                
           }        
            transformer.transform(source, result);
           }  
        } catch (Exception e){           
           e.printStackTrace();
            }
		
	}

}
