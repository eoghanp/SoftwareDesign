package UI;

import java.util.ArrayList;
import java.util.List;

public class Caretaker 
{
	ArrayList<Memento> savedVehicles = new ArrayList<Memento>();
	
	public void addMemento(Memento m)
	{
		savedVehicles.add(m);
	}
	
	public Memento getMemento(int index)
	{
		return savedVehicles.get(index);
	}
}