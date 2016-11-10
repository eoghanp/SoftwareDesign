package Interceptor;

import Vehicle.Vehicle;

/*
 * 7. Contains info to be logged
 * 
 * Allows services to obtain state informations from the "Concrete Framework".
 * Allows services to control certain behaviours in the "Concrete Framework".
 * 
 * Define Context Objects to allow a concrete interceptor to introspect and control certain aspects of the framework.
 * Context Objects provide methods to access and modify the framework's internal state.
 * They can be passed to concrete interceptors when they are dispatched by the framework.
 */

public class ContextObject implements ContextObjectInterface{

	private Vehicle aVehicle;
	
	public ContextObject(Vehicle aVehicle) {
		super();
		this.aVehicle = aVehicle;
	}

	@Override
	public String getInfo() 
	{
		String something = aVehicle.getModel();
		return "INTERCEPTOR LOG:  " + something + " is booked.";
	}

}
