package Vehicle;

import java.io.IOException;

import Data.DBHandler;

// Context object that will change its behavior based on its internal state

public class HasAvailability implements VehicleState {

	Vehicle vehicle;
	
	public HasAvailability(Vehicle newVehicle) {
		
		vehicle = newVehicle;
		
	}

	// Adds a vehicle into the DB
	@Override
	public void insertModel() {
		
		
		try {
			DBHandler handler = DBHandler.getSingletonInstance();
			if (!(handler.getListOfVehicles().isEmpty())) {
				System.out.println("Inserted a car model into the DB!");
				vehicle.setVehicleState(vehicle.getHasVehicleState());
				System.out.println("Vehicle DB is NOT empty");
			}
				
			else
				System.out.println("Vehicle DB is empty!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// Deletes a vehicle from DB
	@Override
	public void deleteModel() {
		
		System.out.println("Deleted a car model from DB!");
		vehicle.setVehicleState(vehicle.getNoVehicleState());
		
	}

}