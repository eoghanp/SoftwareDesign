package Invoice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;




public class Receipt extends ReceiptComponent {

	private String vehicleType;
	private String vehicleRentalDate;
	private String vehicleReturnDate;
	private int rentalLength;
	private double cost;
	
	
	public Receipt(String vehicleType, String vehicleRentalDate, String vehicleReturnDate,
			int rentalLength, double cost) {
		super();
		this.vehicleType = vehicleType;
		this.vehicleRentalDate = vehicleRentalDate;
		this.vehicleReturnDate = vehicleReturnDate;
		this.rentalLength = rentalLength;
		this.cost = cost;
	}

	
	
	
	public String getVehicleRentalDate() {
		return vehicleRentalDate;
	}
	public void setVehicleRentalDate(String vehicleRentalDate) {
		this.vehicleRentalDate = vehicleRentalDate;
	}
	public String getVehicleReturnDate() {
		return vehicleReturnDate;
	}
	public void setVehicleReturnDate(String vehicleReturnDate) {
		this.vehicleReturnDate = vehicleReturnDate;
	}
	public int getRentalLength() {
		return rentalLength;
	}
	public void setRentalLength(int rentalLength) {
		this.rentalLength = rentalLength;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@Override
	public void prtReceipt() {
		// TODO Auto-generated method stub
		try(FileWriter fw = new FileWriter("printable_Receipt.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("Car Rental Receipt");
			    out.println();
			
			    out.println("Vehicle Type: " + this.vehicleType);
			    out.println("Vehicle Rental Date: " + this.vehicleRentalDate);
			    out.println("Vehicle Return Date: " + this.vehicleReturnDate);
			    out.println("Length of Rental: " + this.rentalLength);
			    out.println("Total Cost of Rental: " + this.cost);
			 
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
	}
}
