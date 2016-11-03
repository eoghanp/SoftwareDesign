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

import Users.*;


public class MainMenuUI extends JPanel implements ActionListener {

	private static JFrame frame;
	
	public static void main(String[] args) throws InterruptedException {
		frame = new JFrame("Main Menu");
		frame.setSize(500, 300);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.getContentPane().add(new MainMenuUI());
		frame.setVisible(true);
		
	}

	private JButton customerBtn;
	private JButton employeeBtn;
	

	public MainMenuUI() {
		setLayout(null);

		customerBtn = new JButton("Customer");
		customerBtn.setBounds(100, 75, 180, 25);
		add(customerBtn);
		customerBtn.addActionListener(this);
		customerBtn.setActionCommand("customerClicked");
		
		employeeBtn = new JButton("Employee");
		employeeBtn.setBounds(100, 105, 180, 25);
		add(employeeBtn);
		employeeBtn.addActionListener(this);
		employeeBtn.setActionCommand("employeeClicked");
	}

	@Override
	public void actionPerformed(ActionEvent evt){
		
		/*
		 * Creates the ConcreteCommans and sets their receivers.
		 * And Registers command with invoker
		 */
		
		// Creating the receiver object
		UserReceiver ur = UserReceiverFP.getGUIUser(evt.getActionCommand());
						
		// Creating command and associating with receiver
		OpenFileCommand openFileCommand = new OpenFileCommand(ur);
						
		// Creating invoker and associating with Command
		UserInvoker aUser = new UserInvoker(openFileCommand);
					
		// Perform action on Invoker object;
		aUser.execute();
						
		WriteToFileCommand writeFileCommand = new WriteToFileCommand(ur);
		aUser = new UserInvoker(writeFileCommand);
		aUser.execute();
						
		CloseFileCommand closeFileCommand = new CloseFileCommand(ur);
		aUser = new UserInvoker(closeFileCommand);
		aUser.execute();

		if(evt.getActionCommand().equals("customerClicked"))
		{
			JFrame frame1 = new JFrame("BrowseVehicles");
			frame1.setSize(600, 600);
			frame1.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			
			frame1.getContentPane().add(new BrowseVehiclesUI());
			frame1.setVisible(true);
			frame.setVisible(false);
		}
		else if(evt.getActionCommand().equals("employeeClicked"))
		{
			JFrame frame1 = new JFrame("Employee UI");
			frame1.setSize(1150, 500);
			frame1.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			
				frame1.getContentPane().add(new EmployeeUI());
				frame1.setVisible(true);
				frame.setVisible(false);
			
		}
			
		

		
	}

}