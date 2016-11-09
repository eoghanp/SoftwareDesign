package Users;

import java.util.ArrayList;

public class PersonFacade {
    PersonFactory personFactory;
    ArrayList<Person> person;
  
    public PersonFacade() {
        personFactory = new PersonFactory();
        person = new ArrayList<Person>();
    }
   
    public void addPerson(){
        person.add(personFactory.createPerson("customer"));
        person.add(personFactory.createPerson("employee"));
    }
    
}
