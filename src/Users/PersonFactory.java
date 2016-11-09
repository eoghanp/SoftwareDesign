package Users;


public class PersonFactory {

    Person person;

    public PersonFactory() {
    	
    }

    public Person createPerson(String p) {
    	
        if (p.toLowerCase().equals("customer")) {
        	
            person = new Customer();
            
            return person;
            
        } else if (p.toLowerCase().equals("employee")) {
        	
            person = new Employee(p, p, p, p, p, p, null);
            
            return person;
            
        } else {
            return null;
        }
    } 
}
