package Vehicle;

public class VehicleBuilder {
	private String model;
	private int seats;
	private String specialFeatures;
	private String classification;
	private boolean available;
	private double price;
	
	public VehicleBuilder(String model, double price) {
		this.model = model;
		this.price = price;
	}

	public VehicleBuilder model(String model){
		this.model = model;
		return this;
	}
	
	public VehicleBuilder seats(int seats){
		this.seats = seats;
		return this;
	}
	
	public VehicleBuilder specialFeatures(String specialFeatures){
		this.specialFeatures = specialFeatures;
		return this;
	}
	
	public VehicleBuilder classification(String classification){
		this.classification = classification;
		return this;
	}
	
	public VehicleBuilder available(boolean available){
		this.available = available;
		return this;
	}
	
	public VehicleBuilder price(double price){
		this.price = price;
		return this;
	}
	
	public Vehicle createVehicle(){
		return new Vehicle(model, seats, specialFeatures, classification, available, price);
	}
}
