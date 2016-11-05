package UI;

public class Memento 
{
	private String vehicle;
	
	public Memento(String vehicleSave){
		vehicle = vehicleSave;
	}
	
	public String getDeletedVehicle()
	{
		return vehicle;
	}
}
