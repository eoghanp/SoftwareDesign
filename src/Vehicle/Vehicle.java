package Vehicle;

import java.text.SimpleDateFormat;

public class Vehicle
{
	private String model;
	private int seats;
	private String specialFeatures;
	private String classification;
	private boolean available;
	private double price;
	
	
	/* State Context for State DP */
	
	/*
	 * State Context is the class that has a State reference to one of the Concrete implementations of the State.
	 * Context forwards the request to the State object for processing.
	 */
	
	public VehicleState hasAvailability;
	public VehicleState noAvailability;
	public VehicleState vehicleState;

	public Vehicle() {
		
		hasAvailability = new HasAvailability(this);
		noAvailability = new NoAvailability(this);
		
		vehicleState = hasAvailability;
		
	}
	
	public void setVehicleState(VehicleState newVehicleState) {
		vehicleState = newVehicleState;
	}
	
	public void checkAvailability() {
		vehicleState.checkAvailability();
	}
	/*
	public void deleteModel() {
		vehicleState.deleteModel();
	}
	*/
	public VehicleState getHasVehicleState() {
		return hasAvailability;
	}
	
	public VehicleState getNoVehicleState() {
		return noAvailability;
	}

	
	public Vehicle(String model, int seats, String specialFeatures, String classification, boolean available, double price) {
		this.model = model;
		this.seats = seats;
		this.specialFeatures = specialFeatures;
		this.classification = classification;
		this.available = available;
		this.price = price;
	}

	// Unit Test
	public Vehicle(Object object, Object object2, Object object3,
			Object object4, boolean available, Object object5) {
		this.available = available;
	}

	public String getModel() {
		return model;
	}
	
	public int getSeats() {
		return seats;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}
	
	public String getClassification() {
		return classification;
	}
	
	public double getPrice() {
		return price;
	}

	public void setBooked(SimpleDateFormat startDate, SimpleDateFormat endDate) {
		this.available = false;
		
	}

	public boolean getAvailable() {
		return this.available;
	}
}
