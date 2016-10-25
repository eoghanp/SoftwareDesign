package Vehicle;

public class FamilyCar implements Vehicle {

	private String model;
	private int seats;
	private String specialFeatures;

	
	public FamilyCar(String model, int seats, String specialFeatures) {
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
