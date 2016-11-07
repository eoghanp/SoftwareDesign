package Interceptor;

import Vehicle.Vehicle;

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
		return something + " is booked.";
	}

}
