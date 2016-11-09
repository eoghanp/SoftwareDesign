package Vehicle;

import java.io.IOException;

import Data.DBHandler;

/*
 * Context object that will change its behaviuor based on its internal state.
 * 
 * Concrete State class
 * Implements a behaviour associated with a state of Context
 */

public class NoAvailability implements VehicleState {

	Vehicle vehicle;
	
	
	public NoAvailability(Vehicle newVehicle) {
		
		vehicle = newVehicle;
		
	}

	@Override
	public void checkAvailability() {
		
		vehicle.setVehicleState(vehicle.getNoVehicleState());
		System.out.println("Vehicle DB is empty - Nothing to delete!");
		
	}
}
