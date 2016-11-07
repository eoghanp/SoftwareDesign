package Vehicle;

import java.io.IOException;

import Data.DBHandler;

//Context object that will change its behavior based on its internal state

public class NoAvailability implements VehicleState {

	Vehicle vehicle;
	
	public NoAvailability(Vehicle newVehicle) {
		
		vehicle = newVehicle;
		
	}

	@Override
	public void insertModel() {
		
		try {
			DBHandler handler = DBHandler.getSingletonInstance();
			if (handler.getListOfVehicles().isEmpty()) 
				vehicle.setVehicleState(vehicle.getNoVehicleState());
				System.out.println("Vehicle DB is empty");
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("No vehicles entered to insert");
		
		
	}

	@Override
	public void deleteModel() {
		
		System.out.println("No vehicles available to delete");
		
	}

}
