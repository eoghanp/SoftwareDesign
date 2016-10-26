package DesignPatterns;

public class EmployeeUIFileReceiver implements FileSystemReceiver {

	@Override
	public void openFile() {
		System.out.println("Opening file for Employee");
		
	}

	@Override
	public void writeToFile() {
		System.out.println("Writing file for Employee");
		
	}

	@Override
	public void closeFile() {
		System.out.println("Closing file for Employee");
		
	}

}
