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
		
		String osName = System.getProperty("os.name");
		System.out.println("Underlying OS is:" + osName);
		if (msg.equals("customerClicked")) {
			System.out.println("Customer");
			return new CustomerUIFileReceiver();
		} else {
			System.out.println("Employee");
			return new EmployeeUIFileReceiver();
		}
		
		
		
		/*
		if ("customerClicked".equals(MainMenuUI.getUserTypesList().get(0).getUser())) {
			JButton b = new JButton("Button 1");
	        b.addActionListener(listener);
	        System.out.println("ReceiverUtil");
			return new CustomerUIFileReceiver();
		} else {
			return new EmployeeUIFileReceiver();
		}
		*/
		
	}
	



}
