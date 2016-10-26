package DesignPatterns;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import UI.*;

public class FileSystemReceiverUtil {

	private static FileSystemReceiverUtil singletonInstance;
	
	public static FileSystemReceiverUtil getSingletonInstance(){
		if(singletonInstance == null)
			singletonInstance = new FileSystemReceiverUtil();
		return singletonInstance;
	}
	
	public static FileSystemReceiver getGUIUser(String msg) {
		
		if (msg.equals("customerClicked")) {
			System.out.println(msg);
			return new CustomerUIFileReceiver();
		} else {
			System.out.println(msg);
			return new EmployeeUIFileReceiver();
		}
	}
	
	/*public FileSystemReceiver fsUI(ActionEvent e) {
		
		if ("customerClciked".equals(e.getActionCommand())) {
			return new CustomerUIFileReceiver();
		} else {
			return new EmployeeUIFileReceiver();
		}
			
	}*/
}
