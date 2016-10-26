package DesignPatterns;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import UI.*;

public class FileSystemReceiverUtil implements ActionListener{

	public static FileSystemReceiver getGUIUser() {
		
		String osName = System.getProperty("os.name");
		System.out.println("Underlying OS is:" + osName);
		if (osName.contains("Windows")) {
			System.out.println("Mac OS X");
			return new CustomerUIFileReceiver();
		} else {
			System.out.println("macOS");
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
	

	@Override
	public void actionPerformed(ActionEvent e) {
		e.getActionCommand();
		System.out.println("here"); 
		
	}


}
