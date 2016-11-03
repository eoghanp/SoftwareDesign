package Vehicle;

import java.text.SimpleDateFormat;

public class Vehicle
{
	private final String model;
	private final int seats;
	private final String specialFeatures;
	private final String classification;
	private boolean available;
	private double price;

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
