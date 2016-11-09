package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


import Vehicle.Vehicle;

public class DBHandler 
{
	private static DBHandler singletonInstance;
	private File file =new File("vehicle.txt");
	private File deleteVehFile = new File("deletedVehicles.txt");
	
	//Data instances used for getListOfAvailableVehicles() and getListOfBookedVehicles();
	private String model;
	private int seats;
	private String specialFeatures;
	private String classification;
	private boolean available;
	private double price;
	
	public static DBHandler getSingletonInstance(){
		if(singletonInstance == null)
			singletonInstance = new DBHandler();
		return singletonInstance;
	}
	
	
	public void saveVehicle(Vehicle vehicle)
	{
		String data = "";

		data+=vehicle.getModel()+ "|";
		data+=vehicle.getSeats() + "|";
		data+=vehicle.getSpecialFeatures()+ "|";
		data+=vehicle.getClassification()+ "|";
		data+=vehicle.getAvailable()+ "|";
		data+=vehicle.getPrice();
	
		try{ 		
    		//if file doesn't exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
    			FileWriter fileWriter = new FileWriter(file.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWriter);
    	        //Save the new vehicle to vehicle.txt
    	        PrintWriter pw = new PrintWriter(bufferWritter);
    	        pw.println(data);
    	        bufferWritter.close();
    	    
	        System.out.println(data+" saved");
	        
    	}catch(IOException e){
    		e.printStackTrace();
    	}
		
	}
	
	
	
	public List<Vehicle> getListOfAvailableVehicles() throws IOException
	{
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			FileReader fr = new FileReader("vehicle.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			//Read file line by line
			while ( ( line = br.readLine( ) ) != null ) 
			{
				//Check if current line contains true. This means that this is an available vehicle
	        	if(line.contains("true"))
	        	{
					String[] tokens = line.split("\\|");
					model = tokens[0];
					seats = Integer.parseInt(tokens[1]);
					specialFeatures = tokens[2];
					classification = tokens[3];
					available= Boolean.parseBoolean(tokens[4]);
					price=Double.parseDouble(tokens[5]);
					
					//Create a new vehicle object and add it to the list of available vehicles
					Vehicle aVehicle = new Vehicle(model, seats, specialFeatures, classification, available, price);
					vehicles.add(aVehicle);		
	        	}
			}	
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		return vehicles;
	}
	
	
	//Gets the list of vehicles where available = false
	public List<Vehicle> getListOfBookedVehicles() throws IOException
	{
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			FileReader fr = new FileReader("vehicle.txt");
			BufferedReader br = new BufferedReader(fr);
			//Read file line by line
			String line;
			while ( ( line = br.readLine( ) ) != null ) 
			{
				//Check if current line contains false. This means that this is a booked vehicle
	        	if(line.contains("false"))
	        	{				
					String[] tokens = line.split("\\|");
					model = tokens[0];
					seats = Integer.parseInt(tokens[1]);
					specialFeatures = tokens[2];
					classification = tokens[3];
					available= Boolean.parseBoolean(tokens[4]);
					price=Double.parseDouble(tokens[5]);
					
					Vehicle aVehicle = new Vehicle(model, seats, specialFeatures, classification, available, price);
					vehicles.add(aVehicle);	
	        	}
			}	
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return vehicles;
	}
	
	public void deleteVehicle(String lineToRemove) throws IOException
	{
		File tempFile = new File("tempVehicleFile.txt"); //create a temporary text file for vehicles that are not deleted
		FileReader fr = new FileReader(file);
		BufferedReader reader = new BufferedReader(fr);
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		try{
			String currentLine;
			while((currentLine = reader.readLine()) != null)
			{
				//trim the current line
				String trimmedLine = currentLine.trim();
				if(!trimmedLine.startsWith(lineToRemove))//if the trimmed line DOES NOT starts with the lineToRemove
				{ 
					writer.write(currentLine + System.getProperty("line.separator")); //Write the line to tempFile
				}	
				else if(trimmedLine.startsWith(lineToRemove))//if the trimmed line DOES start with the lineToRemove
				{
					saveDeletedVehicle(trimmedLine);
				}
			}
			writer.close();
			reader.close();
			file.delete(); //Delete old vehicle.txt file
			tempFile.renameTo(file); //Rename temp file to vehicle.txt
		}
	   catch (FileNotFoundException e) {
		   e.printStackTrace();
	   }
	}
	


	//used in deleteVehicle(). Saves the deleted vehicle into deletedVehicles. We need to do this in order to do undo.
	public void saveDeletedVehicle(String deletedVehicle) throws IOException
	{
		try{ 		
    		//if file doesn't exists, then create it
    		if(!deleteVehFile.exists()){
    			deleteVehFile.createNewFile();
    		}
    		
    			//true = append file
    			FileWriter fileWriter = new FileWriter(deleteVehFile.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWriter);
    	        PrintWriter pw = new PrintWriter(bufferWritter);
    	        pw.println(deletedVehicle);
    	        bufferWritter.close();
	        //System.out.println(deletedVehicle +" saved to deleteVehicle.txt");
    	}catch(IOException e){
    		e.printStackTrace();
    	}
		
	}
	
	
	
	public String getLastDeletedVehicle(String deletedVehicle) throws IOException
	{
		String currentLine;
		String lastLine = "";
		BufferedReader reader = new BufferedReader(new FileReader(deleteVehFile));
		try{ 		
			while((currentLine = reader.readLine()) != null)
			{
				lastLine = currentLine;
			}
			//WRITES THE RECOVERED VEHICLE BACK INTO VEHICLE.TXT FILE
			FileWriter fileWritter = new FileWriter(file.getName(),true);
	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	        PrintWriter pw = new PrintWriter(bufferWritter);
	        pw.println(lastLine);
	        bufferWritter.close();
			
	        //get the length of the last line and take it away from the length of the whole file
			RandomAccessFile raf = new RandomAccessFile(deleteVehFile, "rw");
			long length = raf.length(); 
			//gets the length of the last line
			long lastLineLength = lastLine.getBytes().length + 2; //for 2 bytes
			//Sets the file to the full file length - last line lingth -> this deletes the last line in the file
			raf.setLength(length - lastLineLength);
            raf.close();
            reader.close();
			
    	}catch(IOException e){
    		e.printStackTrace();
    	}
		return lastLine;
	}
	
	
	
	//This changes the availability of a selected vehicle to true or false (available or booked)
	public void changeVehicleAvailability(String modelName, String modelSpecialFeatures, boolean available) throws IOException
	{
		try
		{ 		
			String currentLine;
			BufferedReader vehReader = new BufferedReader(new FileReader(file));
			String text = "";
	        String changedLine = "";
	        
	        //Make a new temp file to write the new changes to
	        File tempVehFile = new File("tempVehicleBooked.txt");
	        FileWriter writer = new FileWriter(tempVehFile.getName(),true);
	        BufferedWriter bufferWr = new BufferedWriter(writer);
	          
	        while((currentLine = vehReader.readLine()) != null)
			{
	        	//If the current line matches the model name and special features
	        	if(currentLine.contains(modelName) && currentLine.contains(modelSpecialFeatures))
	        	{
	        		//change availability of the vehicle to be false -> When a vehicle is booked 
	        		if(available == false)
	        		{
	        			changedLine = currentLine.replace("true", "false");
	        		}
	        		else if(available == true) //When a vehicle is unavailable, set it to available (collect)
	        		{
	        			changedLine = currentLine.replace("false", "true");
	        		}
	        		text += changedLine + "\r\n";
	        	}
	        	else text += currentLine + "\r\n";
			}
	        vehReader.close();
	        //write the changes to the temp file
	        bufferWr.write(text);
	        bufferWr.close();
	
	        file.delete(); //Delete old vehicle.txt file
			tempVehFile.renameTo(file); //Rename tempVehFile file to vehicle.txt
			
    	}catch(IOException e){
    		e.printStackTrace();
    	}
		
	}
	
	
	
	
	public boolean getAvailability(Vehicle vehicle) throws IOException {

		boolean available;
		
		try {
			FileReader fr = new FileReader("vehicle.txt");
			BufferedReader br = new BufferedReader(fr);
			//Read file line by line
			String line;
			while ( ( line = br.readLine( ) ) != null ) {
				String[] tokens = line.split("\\|");
				available= Boolean.parseBoolean(tokens[4]);
				
				return vehicleAvailable(available);
				
			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	private boolean vehicleAvailable(boolean available) {
		
		if (available)
			return true;
		else
			return false;
		
	}
	
	public int numberOfSeats(Vehicle vehicle) throws IOException {

		int seats;
		
		try {
			FileReader fr = new FileReader("vehicle.txt");
			BufferedReader br = new BufferedReader(fr);
			//Read file line by line
			String line;
			while ( ( line = br.readLine( ) ) != null ) {
				String[] tokens = line.split("\\|");
				seats = Integer.parseInt(tokens[1]);
				
				if (seats == 2)
					return 2;
				else if (seats == 5)
					return 5;
				else return 7;
				
				//seatsAvailable(seats);
				
			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	/*private int seatsAvailable(int seats) {
		
		if (seats == 2)
			return 2;
		else if (seats == 5)
			return 5;
		else return 7;
		
	}*/
	
	
}

