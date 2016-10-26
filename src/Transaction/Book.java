package Transaction;

import java.text.SimpleDateFormat;

import Invoice.CompanyName;
import Invoice.CustomerInfo;
import Invoice.Receipt;
import Invoice.ReceiptComponent;
import Invoice.VehicleInfo;
import Users.Customer;
import Vehicle.Vehicle;

public class Book implements Booking{
	private Customer customer;
	private Vehicle vehicle;
	private SimpleDateFormat startDate;
	private SimpleDateFormat endDate;
	
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
		ReceiptComponent myReceipt = new Receipt(null, null, null, 0, 0);
		myReceipt = new CompanyName(myReceipt);
		myReceipt = new CustomerInfo(myReceipt);
		myReceipt = new VehicleInfo(myReceipt);
		myReceipt = new Invoice.DateFooter(myReceipt);
		myReceipt.prtReceipt();
	}
}
