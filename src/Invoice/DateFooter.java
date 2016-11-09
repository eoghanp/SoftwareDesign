package Invoice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFooter extends ReceiptDecorator{

	public DateFooter(ReceiptComponent myReceipt) {
		// TODO Auto-generated constructor stub
		super(myReceipt);
	}

	@Override
	public void prtReceipt() {
		// TODO Auto-genercated method stub
		super.callReceipt();
		try(FileWriter fw = new FileWriter("printable_Receipt.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				out.println(dateFormat.format(cal.getTime()));
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		
	}

}
