package Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Interceptor.ContextObject;
import Interceptor.ContextObjectInterface;
import Interceptor.Dispatcher;
import Interceptor.Observer;
import Invoice.CompanyName;
import Invoice.CustomerInfo;
import Invoice.Receipt;
import Invoice.ReceiptComponent;
import Invoice.VehicleInfo;
import Users.Customer;
import Vehicle.Vehicle;

public class Booking implements BookVehicle, Subject{
	private Customer customer;
	private Vehicle vehicle;
	private SimpleDateFormat startDate;
	private SimpleDateFormat endDate;
	private List<Observer> observers;
	

	public Booking(Customer c, Vehicle v, SimpleDateFormat sd, SimpleDateFormat ed){
		this.customer = c;
		this.vehicle = v;
		this.startDate = sd;
		this.endDate = ed;
		observers = new ArrayList<Observer>();
	}
	
	@Override
	public void printReceipt(){
		ReceiptComponent myReceipt = new Receipt(null, null, null, 0, 0);
		myReceipt = new CompanyName(myReceipt);
		myReceipt = new CustomerInfo(myReceipt, customer);
		myReceipt = new VehicleInfo(myReceipt, vehicle);
		myReceipt = new Invoice.DateFooter(myReceipt);
		myReceipt.prtReceipt();
	}

	@Override
	public void bookVehicle() {
		vehicle.setBooked(startDate, endDate);
		notifyObservers();
	}

	@Override
	public void registerObserver(Dispatcher d) 
	{
		observers.add(d);
	}

	@Override
	public void removeObserver(Dispatcher d) 
	{
		observers.remove(d);
	}

	@Override
	public void notifyObservers() 
	{
		ContextObjectInterface coi = new ContextObject(vehicle);
		for(Observer d : observers)
		{
			d.log(coi);
		}
		
	}
}
