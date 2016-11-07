package UI;

public class Originator 
{
	private String vehicle;
	
	public void set(String newVehicle)
	{
		System.out.println("From Originator: Current Version of article" + newVehicle + "\n");
		
		vehicle = newVehicle;
	}
	
	//create a new memento with a new article
	public Memento storeInMemento(){
		System.out.println("From Originator: Saving to memento");
		
		return new Memento(vehicle);
	}
	
	public String restoreFromMemento(Memento memento){
		vehicle = memento.getDeletedVehicle();
		
		System.out.println("From Originator: Previous Vehicle Saved in Memento\n" + vehicle + "\n");
		
		return vehicle;
	}
}
