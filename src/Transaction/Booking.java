package Transaction;

import java.text.SimpleDateFormat;

import Users.Customer;
import Vehicle.Vehicle;

public interface Booking {
	public void bookVehicle(Customer c, Vehicle v, SimpleDateFormat sd, SimpleDateFormat ed);
	public void updateVehicleAvailability();
	public void printReceipt();
}
