package CommandDP;

/*
 * Receiver class - Performs the application specific operation
 */

public class CustomerUIUserReceiver implements UserReceiver {

	@Override
	public void openFile() {
		System.out.println("Opening file for Customer");
		
	}

	@Override
	public void writeToFile() {
		System.out.println("Writing file for Customer");
		
	}

	@Override
	public void closeFile() {
		System.out.println("Closing file for Customer");
		
	}

}
