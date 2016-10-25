package Vehicle;

public class SportsCar implements Vehicle {

	private String model;
	private int seats;
	private String specialFeatures;

	
	public SportsCar(String model, int seats, String specialFeatures) {
		this.model = model;
		this.seats = seats;
		this.specialFeatures = specialFeatures;
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

}
