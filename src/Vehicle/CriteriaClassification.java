package Vehicle;

import java.util.ArrayList;
import java.util.List;

public class CriteriaClassification implements Criteria {
	public String classification;

	public CriteriaClassification(String classification){
		this.classification = classification;
	}
	
	@Override
	public List<Vehicle> meetsCriteria(List<Vehicle> vehicles) {
		List<Vehicle> newList = new ArrayList<Vehicle>();
		
		for (Vehicle v: vehicles)
			if (v.getClassification().equalsIgnoreCase(classification))
				newList.add(v);
		
		return newList;
	}

}