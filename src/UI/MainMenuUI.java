package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DesignPatterns.CloseFileCommand;
import DesignPatterns.FileInvoker;
import DesignPatterns.FileSystemReceiver;
import DesignPatterns.FileSystemReceiverUtil;
import DesignPatterns.OpenFileCommand;
import DesignPatterns.WriteToFileCommand;
import Users.*;

//import Data.DBHandler;

public class MainMenuUI extends JPanel  {

	private static ArrayList<UserTypes> userTypesList = new ArrayList<UserTypes>();
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Main Menu");
		frame.setSize(500, 300);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.getContentPane().add(new MainMenuUI());
		frame.setVisible(true);
		
		
		// Creating the receiver object
		FileSystemReceiver fs = FileSystemReceiverUtil.getGUIUser();
				
		// Creating command and associating with receiver
		OpenFileCommand openFileCommand = new OpenFileCommand(fs);
				
		// Creating invoker and associating with Command
		FileInvoker aFile = new FileInvoker(openFileCommand);
				
		// Perform action on Invoker object;
		aFile.execute();
				
		WriteToFileCommand writeFileCommand = new WriteToFileCommand(fs);
		aFile = new FileInvoker(writeFileCommand);
		aFile.execute();
				
		CloseFileCommand closeFileCommand = new CloseFileCommand(fs);
		aFile = new FileInvoker(closeFileCommand);
		aFile.execute();
		
	}

	private JButton customerBtn;
	private JButton employeeBtn;
	private JTextField userNameTxt;
	private JTextField IDTxt;
	private JPasswordField passwordPwd;
	private JButton logInBtn;
	private JButton registerBtn;
	private JButton trackBtn;

	public MainMenuUI() {
		setLayout(null);

		customerBtn = new JButton("Customer");
		customerBtn.setBounds(100, 75, 180, 25);
		add(customerBtn);
		customerBtn.addActionListener(FileSystemReceiverUtil.getSingletonInstance());
		customerBtn.setActionCommand("customerClicked");
		
		employeeBtn = new JButton("Employee");
		employeeBtn.setBounds(100, 105, 180, 25);
		add(employeeBtn);
		employeeBtn.addActionListener(FileSystemReceiverUtil.getSingletonInstance());
		employeeBtn.setActionCommand("employeeClicked");
	}

	/*@Override
	public void actionPerformed(ActionEvent evt) {
		JFrame frame = new JFrame("MainActivity");
		frame.setSize(600, 400);
		
		String evtAction = "";
		evtAction = evt.getActionCommand();
		UserTypes addUser = new UserTypes(evtAction);
		getUserTypesList().add(addUser);
		if ("customerClicked".equals(evt.getActionCommand())) {
			System.out.println("Customer Clicked");
			
			System.out.println(getUserTypesList().get(0).getUser());
			userTypesList.clear();
		} else {
			System.out.println("Employee Clicked");

			System.out.println(getUserTypesList().get(0).getUser());
			userTypesList.clear();
		}
		
	}

	public static ArrayList<UserTypes> getUserTypesList() {
		return userTypesList;
	}

	public static void setUserTypesList(ArrayList<UserTypes> userTypesList) {
		MainMenuUI.userTypesList = userTypesList;
	}*/
}