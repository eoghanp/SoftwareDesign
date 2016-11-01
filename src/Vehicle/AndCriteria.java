package Vehicle;

import java.util.List;

public class AndCriteria implements Criteria {

	private Criteria criteria;
	private Criteria otherCriteria;
	
	public AndCriteria(Criteria criteria1, Criteria criteria2){
		this.criteria = criteria1;
		this.otherCriteria = criteria2;
	}
	
	@Override
	public List<Vehicle> meetsCriteria(List<Vehicle> vehicles) {
		List<Vehicle> list1 = criteria.meetsCriteria(vehicles);
		return otherCriteria.meetsCriteria(list1);
	}

}
