package Invoice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class VehicleInfo extends ReceiptDecorator{

	public VehicleInfo(ReceiptComponent myReceipt) {
		// TODO Auto-generated constructor stub
		super(myReceipt);
	}

	@Override
	public void prtReceipt() {
		// TODO Auto-generated method stub
		super.callReceipt();
		try(FileWriter fw = new FileWriter("printable_Receipt.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("Vehicle Type:");
			    out.println("Engine size:");
			    out.println("Year:");
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		
	}

}