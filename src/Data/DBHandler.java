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
		
		
		//BufferedReader reader = new BufferedReader(new FileReader(deleteFile));
		//BufferedWriter delWriter = new BufferedWriter(new FileWriter(deleteVehFile));
		try{
			String currentLine;
			while((currentLine = reader.readLine()) != null)
			{
				//trim the current line
				String trimmedLine = currentLine.trim();
				if(!trimmedLine.startsWith(lineToRemove)){ //if the trimmed line DOES NOT starts with the lineToRemove
					writer.write(currentLine + System.getProperty("line.separator")); //Write the line to tempFile
				}	
				else if(trimmedLine.startsWith(lineToRemove)){//if the trimmed line DOES start with the lineToRemove
					//delWriter.write(currentLine + System.getProperty("line.separator")); //Write the line to deleteVehFile
					saveDeletedVehicle(trimmedLine);
				}
			}
			writer.close();
			reader.close();
			//delWriter.close();
			file.delete(); //Delete old vehicle.txt file
			tempFile.renameTo(file); //Rename temp file to vehicle.txt
			
		}
	   catch (FileNotFoundException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	}
	



	public void saveDeletedVehicle(String deletedVehicle) throws IOException
	{
		//String data = "";
		try{ 		
    		//if file doesn't exists, then create it
    		if(!deleteVehFile.exists()){
    			deleteVehFile.createNewFile();
    		}
    		
    		//true = append file
    		FileWriter fileWriter = new FileWriter(deleteVehFile.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWriter);
    	       // bufferWritter.write(data);
    	        PrintWriter pw = new PrintWriter(bufferWritter);
    	        pw.println(deletedVehicle);
    	        bufferWritter.close();
    	    
	        System.out.println(deletedVehicle +" saved to deleteVehicle.txt");
	        
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
			System.out.println("File Length= "+ length);
			//long lastLineLength = (lastLine.length()) + (lastLine.length() - lastLine.replace(" ", "").length());
			long lastLineLength = lastLine.getBytes().length + 2; //for 2 bytes
			System.out.println("LENGTH OF last line IS : " + lastLineLength);
			raf.setLength(length - lastLineLength);
            System.out.println("File Length="+raf.length());
            raf.close();
			
    	}catch(IOException e){
    		e.printStackTrace();
    	}
		return lastLine;
	}
	
}

