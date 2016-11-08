package Vehicle;

import java.io.IOException;

import Data.DBHandler;

/*
 * Context object that will change its behaviour based on its internal state.
 * 
 * Concrete State class
 * Implements a behaviour associated with a state of Context
 */
 

public class HasAvailability implements VehicleState {

	Vehicle vehicle;
	
	public HasAvailability(Vehicle newVehicle) {
		
		vehicle = newVehicle;
		
	}

	@Override
	public void checkAvailability() {
		
		try {
			DBHandler handler = DBHandler.getSingletonInstance();
			if (!(handler.getListOfVehicles().isEmpty())) {
				vehicle.setVehicleState(vehicle.getHasVehicleState());
				System.out.println("Vehicle DB is NOT empty");
			}
				
			else
				vehicle.setVehicleState(vehicle.getNoVehicleState());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}