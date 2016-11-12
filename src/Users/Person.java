package Users;

public class Person {
	
	protected String FirstName;
	protected String LastName;
	protected String email;
	protected String password;
	protected String address; 
	protected String telephone;
	
	public Person() {

    }
	
	public Person(String first, String last, String mail, String pass, String add, String phone){
		FirstName = first;
		LastName = last;
		email = mail;
		password = pass;
		address = add;
		telephone = phone;
		System.out.println("Person created");
	}
	
	public String getName(){
		String name = FirstName + " " + LastName;
		return name;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getTelephone(){
		return telephone;
	}
	
	public String getPassword(){
		return password;
	}

	public void setFirstName(String fname){
		FirstName = fname;
	}

	public void setLastName(String lname){
		LastName = lname;
	}
	
	public void setEmail(String newEmail){
		email = newEmail;
	}
	
	public void setPassword(String newPassword){
		password = newPassword;
	}

	public void setTelephone(String newTelephone){
		telephone = newTelephone;
	}

	public void setAddress(String newAddress){
		address = newAddress;
	}
	
	public String showDetails(){
		String details = FirstName + "\n" +
			   LastName + "\n" +
			   email + "\n" +
			   password + "\n" +
			   address + "\n" +
			   telephone;
		return details;
	}
	
	@Override
    public String toString() {
        return "Person{" + "FirstName=" + FirstName + ", LastName=" + LastName + ", email=" + email + 
        		", password=" + password + ", address=" + address + ", telephone=" + telephone + '}';
    }
}