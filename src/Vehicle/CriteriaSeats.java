package Vehicle;

import java.util.ArrayList;
import java.util.List;

public class CriteriaSeats implements Criteria {
	public int seats;

	public CriteriaSeats(int seats){
		this.seats = seats;
	}
	
	@Override
	public List<Vehicle> meetsCriteria(List<Vehicle> vehicles) {
		List<Vehicle> newList = new ArrayList<Vehicle>();
		
		for (Vehicle v: vehicles)
			if (v.getSeats() >= seats)
				newList.add(v);
		
		return newList;
	}

}
