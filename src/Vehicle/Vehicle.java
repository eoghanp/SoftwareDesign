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
	
	public VehicleState hasAvailability;
	public VehicleState noAvailability;
	public VehicleState vehicleState;

	public Vehicle() {
		
		hasAvailability = new HasAvailability(this);
		noAvailability = new NoAvailability(this);
		
		vehicleState = hasAvailability;
		//vehicleState = noAvailability;
		
	}
	
	public void setVehicleState(VehicleState newVehicleState) {
		vehicleState = newVehicleState;
	}
	
	public void insertModel() {
		vehicleState.insertModel();
	}
	
	public void deleteModel() {
		vehicleState.deleteModel();
	}
	
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
