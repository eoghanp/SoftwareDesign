package Vehicle;

public class FamilyCar extends Vehicle {

	private String model;
	private int seats;
	private String specialFeatures;
	private String storageSpace;

	
	public FamilyCar(String model, int seats, String specialFeatures, String storageSpace) {
		super(storageSpace, seats, storageSpace, storageSpace);
		this.model = model;
		this.seats = seats;
		this.specialFeatures = specialFeatures;
		this.storageSpace = storageSpace;
	}

	@Override
	public String getModel() {
		return model;
	}

	@Override
	public void setModel(String model) {
		this.model = model;
		
	}

	@Override
	public int getSeats() {
		return seats;
	}

	@Override
	public void setSeats(int seats) {
		this.seats = seats;
		
	}

	@Override
	public String getSpecialFeatures() {
		return specialFeatures;
	}

	@Override
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
		
	}

	@Override
	public String getStorageSpace() {
		return storageSpace;
	}

	@Override
	public void setStorageSpace(String storageSpace) {
		this.storageSpace = storageSpace;
		
	}

}
