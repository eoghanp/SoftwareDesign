package Vehicle;

/* 
 * Allows an object to alter its behaviour when its internal state changes.
 * The object will appear to change its class.
 *
 * State Class
 * 
 * The different ways a user could interact with renting a vehicle.
*/
public interface VehicleState {

	public void checkAvailability();
	
	//public void deleteModel();
	
}
