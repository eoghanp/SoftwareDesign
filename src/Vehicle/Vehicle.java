package Vehicle;

public class Vehicle
{
	private String model;
	private int seats;
	private String specialFeatures;
	private String storageSpace;

	public Vehicle(String model, int seats, String specialFeatures, String storageSpace) {
		super();
		this.model = model;
		this.seats = seats;
		this.specialFeatures = specialFeatures;
		this.storageSpace = storageSpace;
	}

	
	public String getModel() {
		return model;
	}

	
	public void setModel(String model) {
		this.model = model;
		
	}

	
	public int getSeats() {
		return seats;
	}

	
	public void setSeats(int seats) {
		this.seats = seats;
		
	}

	
	public String getSpecialFeatures() {
		return specialFeatures;
	}

	
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
		
	}

	
	public String getStorageSpace() {
		return storageSpace;
	}

	
	public void setStorageSpace(String storageSpace) {
		this.storageSpace = storageSpace;
		
	}

}
