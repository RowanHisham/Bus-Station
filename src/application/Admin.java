package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
		return null;
	}

	@Override
	public ArrayList<Trip> listTrips() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Person> listDrivers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person AuthenticateLogInCustomer(String tempUser , String tempPass) {
		// TODO Auto-generated method stub
         Customer customer1;
         
        try {
           String UserName ;
           String Password;
           String FirstName;
           String LastName;
           String TripId;
           File inputFile = new File("C:\\Users\\Safynaz\\Documents\\NetBeansProjects\\xml\\src\\xml\\test.xml");
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
	public Person AuthenticateLogInEmployee() {
		// TODO Auto-generated method stub
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
