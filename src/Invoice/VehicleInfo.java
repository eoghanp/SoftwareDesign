package Invoice;

import Vehicle.Vehicle;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class VehicleInfo extends ReceiptDecorator {
    Vehicle vehicle;

    public VehicleInfo(ReceiptComponent myReceipt, Vehicle vehicle) {
        super(myReceipt);
        this.vehicle = vehicle;
    }
    
    @Override
    public void prtReceipt() {
        // TODO Auto-generated method stub
        super.callReceipt();
        try (FileWriter fw = new FileWriter("printable_Receipt.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(this.vehicle.toString());
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }

}
