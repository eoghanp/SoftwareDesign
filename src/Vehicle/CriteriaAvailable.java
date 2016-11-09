package Vehicle;

import java.util.ArrayList;
import java.util.List;

public class CriteriaAvailable implements Criteria {


	@Override
	public List<Vehicle> meetsCriteria(List<Vehicle> vehicles) {
		List<Vehicle> newList = new ArrayList<Vehicle>();
		
		for (Vehicle v: vehicles)
			if (v.getAvailable())
				newList.add(v);
		
		return newList;
	}

}
