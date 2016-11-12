package Invoice;

import Users.Customer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomerInfo extends ReceiptDecorator {

    Customer customer;

    public CustomerInfo(ReceiptComponent myReceipt, Customer customer) {
        // TODO Auto-generated constructor stub
        super(myReceipt);
        this.customer = customer;
    }

    @Override
    public void prtReceipt() {
        // TODO Auto-generated method stub
        super.callReceipt();
        try (FileWriter fw = new FileWriter("printable_Receipt.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(this.customer.toString());
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
