package Vehicle;

import java.util.List;

public class OrCriteria implements Criteria {

	private Criteria criteria;
	private Criteria otherCriteria;
	
	public OrCriteria(Criteria criteria1, Criteria criteria2) {
		this.criteria = criteria1;
		this.otherCriteria = criteria2;
	}

	@Override
	public List<Vehicle> meetsCriteria(List<Vehicle> vehicles) {
		List<Vehicle> list1 = criteria.meetsCriteria(vehicles);
		List<Vehicle> list2 = otherCriteria.meetsCriteria(vehicles);
		
		for (Vehicle v : list2)
			if (!list1.contains(v))
				list1.add(v);
		
		return list1;
	}

}
