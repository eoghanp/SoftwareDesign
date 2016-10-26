package Transaction;

import java.text.SimpleDateFormat;

import Invoice.Receipt;
import Users.Customer;
import Vehicle.Vehicle;

public class Book implements Booking{
	private Customer customer;
	private Vehicle vehicle;
	private SimpleDateFormat startDate;
	private SimpleDateFormat endDate;
	private Receipt receipt;
	
	public Book() {
	}

	public void bookVehicle(Customer c, Vehicle v, SimpleDateFormat sd, SimpleDateFormat ed){
		this.customer = c;
		this.vehicle = v;
		this.startDate = sd;
		this.endDate = ed;
		updateVehicleAvailability();
	}
	
	public void updateVehicleAvailability(){
		vehicle.setBooked(startDate, endDate);
	}
	
	public void printReceipt(){
		this.receipt = new Receipt(null, null, null, 0, 0);
	}
}
