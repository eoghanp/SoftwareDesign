package Invoice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CompanyName extends ReceiptDecorator{

	public CompanyName(ReceiptComponent myReceipt) {
		// TODO Auto-generated constructor stub
		super(myReceipt);
	}

	@Override
	public void prtReceipt() {
		// TODO Auto-generated method stub
		super.callReceipt();
		try(FileWriter fw = new FileWriter("printable_Receiot.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("Company Name");
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		
	}

}
