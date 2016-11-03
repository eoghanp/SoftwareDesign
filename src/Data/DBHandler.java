package Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import Vehicle.Vehicle;

public class DBHandler 
{
	private static DBHandler singletonInstance;
	private File file =new File("vehicle.txt");
	
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
    		
    		//true = append file
    		FileWriter fileWritter = new FileWriter(file.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	       // bufferWritter.write(data);
    	        PrintWriter pw = new PrintWriter(bufferWritter);
    	        pw.println(data);
    	        bufferWritter.close();
    	    
	        System.out.println(data+" saved");
	        
    	}catch(IOException e){
    		e.printStackTrace();
    	}
		
	}
	
	
	public List<Vehicle> getListOfVehicles() throws IOException
	{
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			FileReader fr = new FileReader("vehicle.txt");
			BufferedReader br = new BufferedReader(fr);
			//Read file line by line
			String line;
			while ( ( line = br.readLine( ) ) != null ) {
				
				String model;
				int seats;
				String specialFeatures;
				String classification;
				boolean available;
				double price;
				
				String[] tokens = line.split("\\|");
				model = tokens[0];
				seats = Integer.parseInt(tokens[1]);
				specialFeatures = tokens[2];
				classification = tokens[3];
				available= Boolean.parseBoolean(tokens[4]);
				price=Double.parseDouble(tokens[5]);
				
				Vehicle aVehicle = new Vehicle(model, seats, specialFeatures, classification, available, price);
				//Vehicle aVehicle = new Vehicle("modelName", 6, "None", "Family", true, 60.0);
				vehicles.add(aVehicle);			
			}	
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return vehicles;
	}
	
	public void deleteVehicle(String lineToRemove) throws IOException
	{
		File tempFile = new File("tempVehicleFile.txt");
		FileReader fr = new FileReader(file);
		BufferedReader reader = new BufferedReader(fr);
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		try{
			String currentLine;
			while((currentLine = reader.readLine()) != null)
			{
				//trim the current line
				String trimmedLine = currentLine.trim();
				if(!trimmedLine.startsWith(lineToRemove)){ //if the trimmed line DOES NOT starts with the lineToRemove
					writer.write(currentLine + System.getProperty("line.separator")); //Write the line to tempFile
				}	
			}
			writer.close();
			reader.close();
			file.delete(); //Delete old vehicle.txt file
			tempFile.renameTo(file); //Rename temp file to vehicle.txt
			
		}
	   catch (FileNotFoundException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	}
	
}















