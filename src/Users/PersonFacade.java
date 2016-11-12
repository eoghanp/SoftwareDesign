package Users;

import java.util.ArrayList;

public class PersonFacade {
    PersonFactory personFactory;
    ArrayList<Person> person;
  
    public PersonFacade() {
        personFactory = new PersonFactory();
        person = new ArrayList<Person>();
    }
   
    public Person addPerson(String type){
    	if (type == "customer"){
    		Person p = personFactory.createPerson("customer");
    		person.add(p);
    		return p;
    	}
    	else if (type == "employee"){
    		Person p = personFactory.createPerson("employee");
    		person.add(p);
    		return p;
    	}
    	else
    		return null;
    }	
    
}
