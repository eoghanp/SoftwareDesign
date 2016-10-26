package CommandDP;

/*
 * Return appropriate type based on input
 */

public class UserReceiverFP {

	/*private static UserReceiverUtil singletonInstance;
	
	public static UserReceiverUtil getSingletonInstance(){
		if(singletonInstance == null)
			singletonInstance = new UserReceiverUtil();
		return singletonInstance;
	}*/
	
	public static UserReceiver getGUIUser(String msg) {
		if (msg.equals("customerClicked")) {
			System.out.println(msg);
			return new CustomerUIUserReceiver();
		} else {
			System.out.println(msg);
			return new EmployeeUIUserReceiver();
		}	
	}
	
}
