package Invoice;

import Invoice.ReceiptComponent;

public abstract class ReceiptDecorator extends ReceiptComponent{
private ReceiptComponent myReceipt;
	
	public ReceiptDecorator(ReceiptComponent myReceipt){
		this.myReceipt = myReceipt;
	}

	
	
	public void callReceipt(){
		if(myReceipt != null)
			myReceipt.prtReceipt();
	}
}
