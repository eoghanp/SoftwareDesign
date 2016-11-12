package UI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Data.DBHandler;
import Vehicle.Vehicle;
import Vehicle.VehicleBuilder;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EmployeeUI extends JPanel implements ActionListener
{
	//Add Vehicle components
	private JButton addVehicleBtn;
	private JButton deleteVehicleBtn;
	private JButton undoDeleteBtn;
	private JLabel modelName;
	private JTextField modelNameTxt;
	private JLabel seatNumber;
	private JComboBox<String> seatNumbersDropDown;
	private String seatNumberOptions[] = {"2","5","7"};
	private JLabel features;
	private JTextField featuresTxt;
	private JLabel carClass;
	private JComboBox<String> carClassDropDown;
	private String carClassOptions[] = {"Family Car","Sports Car","Business"};
	private JLabel availableLbl;
	private JComboBox<String> available;
	private String availableOpts[] = {"Yes", "No"};
	private JLabel price;
	private JTextField priceTxt;
	private JButton collectVehicleBtn;
	
	//Delete Vehicle components
	private JTable vehicleTable;
	private JScrollPane pane;
	
	//Booked Vehicle table
	private JTable bookedVehicleTable;
	private JScrollPane bookedVehPane;
	
	Caretaker caretaker = new Caretaker();
	Originator originator = new Originator();
	int currentVehicle = -1;
	String bookedVehicles = "bookedVehicles";
	String availableVehicles = "availableVehicles";
	
	public EmployeeUI()
	{
		setLayout(null);
		createAddVehicleGUIs();
		createDeleteVehicleGUIsAndTable();
		createBookedVehicleTableAndButton();
	}
	
	public void createAddVehicleGUIs()
	{
		JLabel addVehicleLbl = new JLabel("<HTML><U>Add a Vehicle</U></HTML>");
		addVehicleLbl.setBounds(150, 10, 200, 25);
		add(addVehicleLbl);
		
		//CREATE MODEL NAME LABEL & TEXT FIELD
		modelName = new JLabel("Model:");
		modelName.setBounds(10, 50, 200, 25);
		add(modelName);
		modelNameTxt = new JTextField(20);
		modelNameTxt.setBounds(140, 50, 200, 25);
		add(modelNameTxt);
		
		//CREATE SEAT NUMBER LABEL & TEXT FIELD
		seatNumber = new JLabel("Number of Seats:");
		seatNumber.setBounds(10, 80, 200, 25);
		add(seatNumber);
		seatNumbersDropDown = new JComboBox<String>();
		for (int i = 0; i < 3; i++){
			seatNumbersDropDown.addItem(seatNumberOptions[i]);
		}
		//seatNumberTxt = new JTextField(20);
		seatNumbersDropDown.setBounds(140, 80, 200, 25);
		add(seatNumbersDropDown);
		
		//CREATE SPECIAL FEATURES LABEL & TEXT FIELD
		features = new JLabel("Special Features:");
		features.setBounds(10, 110, 200, 25);
		add(features);
		featuresTxt = new JTextField(20);
		featuresTxt.setBounds(140, 110, 200, 50);
		add(featuresTxt);
		
		//CREATE CAR CLASS LABEL & TEXT FIELD
		carClass = new JLabel("Classification:");
		carClass.setBounds(10, 170, 200, 25);
		add(carClass);
		carClassDropDown = new JComboBox<String>();
		for (int i = 0; i < 3; i++){
			carClassDropDown.addItem(carClassOptions[i]);
		}
		carClassDropDown.setBounds(140, 170, 200, 25);
		add(carClassDropDown);
		
		//CREATE AVAILABLE LABEL, COMBO BOX OPTIONS & COMBO BOX
		availableLbl = new JLabel("Available:");
		availableLbl.setBounds(10, 200, 200, 25);
		add(availableLbl);
		available = new JComboBox<String>();
		for (int i = 0; i < 2; i++){
			available.addItem(availableOpts[i]);
		}
		available.setBounds(140, 200, 200, 25);
		add(available);
		
		//CREATE PRICE LABEL & TEXT FIELD
		price = new JLabel("Price:");
		price.setBounds(10, 230, 200, 25);
		add(price);
		priceTxt = new JTextField(20);
		priceTxt.setBounds(140, 230, 200, 25);
		add(priceTxt);
		
		//CREATE ADD VEHICLE BUTTON & ACTION LISTENER
		addVehicleBtn = new JButton("Add Vehicle");
		addVehicleBtn.setBounds(150, 270, 150, 25);
		add(addVehicleBtn);
		addVehicleBtn.addActionListener(this);
		addVehicleBtn.setActionCommand("addVehicle");
		
	}
	
	public void createDeleteVehicleGUIsAndTable()
	{
		JLabel delVehicleLbl = new JLabel("<HTML><U>Remove a Vehicle</U></HTML>");
		delVehicleLbl.setBounds(670, 10, 200, 25);
		add(delVehicleLbl);
		
		//CREATE VEHICLE TABLE
		createTable(availableVehicles);
		pane = new JScrollPane(vehicleTable);
		pane.setBounds(400, 50, 650, 250);
	    add(pane);
		//CREATE DELETE VEHICLE BUTTON
		deleteVehicleBtn = new JButton("Delete Vehicle");
		deleteVehicleBtn.setBounds(550, 320, 150, 25);
		add(deleteVehicleBtn);
		deleteVehicleBtn.addActionListener(this);
		deleteVehicleBtn.setActionCommand("deleteVehicle");
		//CREATE UNDO DELETE VEHICLE BUTTON
		undoDeleteBtn = new JButton("Undo Delete");
		undoDeleteBtn.setBounds(750, 320, 150, 25);
		add(undoDeleteBtn);
		undoDeleteBtn.addActionListener(this);
		undoDeleteBtn.setActionCommand("undoDelete");
		undoDeleteBtn.setEnabled(false);
	}
	
	public void createBookedVehicleTableAndButton()
	{
		JLabel bookedVehicleLbl = new JLabel("<HTML><U>Booked Vehicles</U></HTML>");
		bookedVehicleLbl.setBounds(670, 370, 200, 25);
		add(bookedVehicleLbl);
		
		//CREATE BOOKED VEHICLES TABLE
		createTable(bookedVehicles);
		bookedVehPane = new JScrollPane(bookedVehicleTable);
		bookedVehPane.setBounds(400, 400, 650, 150);
	    add(bookedVehPane);
		//CREATE COLLECT VEHICLE BUTTON
		collectVehicleBtn = new JButton("Collect Vehicle");
		collectVehicleBtn.setBounds(620, 560, 150, 25);
		add(collectVehicleBtn);
		collectVehicleBtn.addActionListener(this);
		collectVehicleBtn.setActionCommand("collectVehicle");
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		/* Client for State DP */
		// Create a Vehicle object for State DP 
		Vehicle vehicle = new Vehicle();
		// Calls checkAvailability in Vehicle Class, Vehicle class determines (Calls VehicleState) the correct state to execute.
		//vehicle.checkAvailability();

	      // Now create matcher object.
	      //Matcher m = isInteger.matcher(line);

		if(currentVehicle <= 0) //If no vehicles have been deleted yet
			undoDeleteBtn.setEnabled(false);

		//When 'Add Vehicle' button is clicked
		if ("addVehicle" == event.getActionCommand())
		{
			//Check if all fields are not empty
			if ((modelNameTxt.getText().equals("")) || (featuresTxt.getText().equals("")) || (priceTxt.getText().equals("")))
			{
				  JOptionPane.showMessageDialog(null, "You must enter details in all text fields", "Enter all data", 2);
			}
			/*else if(Pattern.matches(String.valueOf(seatNumbersDropDown.getSelectedItem()), "\\d+") == false)
			{
				JOptionPane.showMessageDialog(null, "The price must be a number", "Incorrect data type", 2);
			}*/
			
			else //When fields are valid
			{	
				int seatsNumber = Integer.parseInt(String.valueOf(seatNumbersDropDown.getSelectedItem()));
				String carClass = String.valueOf(carClassDropDown.getSelectedItem());
				double price = Double.parseDouble(priceTxt.getText());
				boolean availableBool;
				if(String.valueOf(available.getSelectedItem()) == "No"){
					availableBool = false;
				}
				else availableBool = true;
				//Create a new vehicle builder
				VehicleBuilder vb = new VehicleBuilder(modelNameTxt.getText(), price).seats(seatsNumber).specialFeatures(
						featuresTxt.getText()).classification(carClass).available(availableBool);
				Vehicle aVehicle = vb.createVehicle();
				
				DBHandler handler = DBHandler.getSingletonInstance();
				handler.saveVehicle(aVehicle);
				
				//Refreshes table to show new vehicle
				refreshAvailableVehiclesTable();
				refreshBookedVehiclesTable();
					
				DBHandler handler2 = DBHandler.getSingletonInstance();
				try {
					if (!(handler2.getListOfAvailableVehicles().isEmpty())) {
						vehicle.setVehicleState(vehicle.getHasVehicleState());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
					
				// State DP
				// Calls checkAvailability in Vehicle Class, Vehicle class determines (Calls VehicleState) the correct state to execute.
				vehicle.checkAvailability();
				
				JOptionPane.showMessageDialog(null,"Vehicle Added");		
				clearAddVehicleForm();
					
			}
		}
		
		//When DELETE Vehicle Button is clicked
		else if("deleteVehicle" == event.getActionCommand())
		{
			// State DP
			// Calls checkAvailability in Vehicle Class, Vehicle class determines (Calls VehicleState) the correct state to execute.
			//vehicle.checkAvailability();
			
			int row = vehicleTable.getSelectedRow();
			//When no row is selected
			if(row == -1) {
				// State DP
				// Calls checkAvailability in Vehicle Class, Vehicle class determines (Calls VehicleState) the correct state to execute.
				vehicle.checkAvailability();
				vehicle.checkAvailability();
				JOptionPane.showMessageDialog(null,"Please select a vehicle to delete", "Select a Row", 2);
				
			}	
			else
			{
			// State DP
			// Calls checkAvailability in Vehicle Class, Vehicle class determines (Calls VehicleState) the correct state to execute.
			//vehicle.checkAvailability();
			try {
				//Gets the value from the Model column of the selected row
				String selectedItem = (vehicleTable.getModel().getValueAt(row, 0).toString());
				originator.set(selectedItem);
				caretaker.addMemento(originator.storeInMemento());
				currentVehicle++;
				undoDeleteBtn.setEnabled(true);

				DBHandler handler = DBHandler.getSingletonInstance();
				((DefaultTableModel) vehicleTable.getModel()).removeRow(row);
				//Deletes vehicle containing selected Item
				handler.deleteVehicle(selectedItem);
				//Refreshes the table to show existing vehicles
				refreshAvailableVehiclesTable();
				
				// State DP
				// Calls checkAvailability in Vehicle Class, Vehicle class determines (Calls VehicleState) the correct state to execute.
				vehicle.checkAvailability();
				JOptionPane.showMessageDialog(null,selectedItem + " vehicle deleted");
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		//When UNDO DELETE button is clicked
		else if("undoDelete" == event.getActionCommand())
		{
			try {
				String recoveredDeletedVehicle = originator.restoreFromMemento(caretaker.getMemento(currentVehicle));
				//System.out.println("After undo operation: " + recoveredDeletedVehicle);
				currentVehicle--;
				DBHandler handler = DBHandler.getSingletonInstance();
				String recovered = handler.getLastDeletedVehicle(recoveredDeletedVehicle);
				//System.out.println("vehicle recovered from file: " + recovered);
				refreshAvailableVehiclesTable();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if("collectVehicle" == event.getActionCommand())  //Return the rented vehicle to the available vehicle table
		{
			int a = bookedVehicleTable.getSelectedRow();
			if (a >= 0){
				//Get the selected model that the employee wants to collect
				String collectedModel = (bookedVehicleTable.getModel().getValueAt(a, 0).toString());
				String specialFeaturesCollectedModel = (bookedVehicleTable.getModel().getValueAt(a, 2).toString());
				DBHandler handler = new DBHandler();
				try {
					handler.changeVehicleAvailability(collectedModel, specialFeaturesCollectedModel, true); //Set vehicle to available
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				refreshAvailableVehiclesTable();
				refreshBookedVehiclesTable();
				JOptionPane.showMessageDialog(null,collectedModel + " has been collected from customer.\n\nIt is available for rent again.", 
						"Vehicle Successfully returned", 1);
			}
			else{
				JOptionPane.showMessageDialog(null,"Please select a vehicle to collect", "Select a Vehicle", 2);
			}
		}
	}
	
	private void clearAddVehicleForm(){
		  modelNameTxt.setText("");
		  featuresTxt.setText("");
		  priceTxt.setText("");
	  }
	
	
	public void createTable(String type) //type can be "bookedVehicles" or "availableVehicles"
	{
		try {
			String[] columnNames = { "Model", "Seats", "Special Features", "Classification", "Available", "Price"};
			DBHandler db = DBHandler.getSingletonInstance();
			List<Vehicle> listVehicles = new ArrayList<Vehicle>();
			
			//if you want to make a table with list of available vehicles
			if(type == availableVehicles) 
			{
				listVehicles = db.getListOfAvailableVehicles();
			}
			
			//if you want to make a table with a list of booked vehicles
			else if(type == bookedVehicles) 
			{
				listVehicles = db.getListOfBookedVehicles();
			}
			else listVehicles.add(new Vehicle(null,0,null,null,false,0)); //just create an empty row.
				
			String cellData[][] = new String[listVehicles.size()][6];
			
			//filling cell data array with information about each vehicle -> one vehicle object per row
			for(int i = 0; i < listVehicles.size(); i++){
				cellData[i][0] = "" + listVehicles.get(i).getModel();
				cellData[i][1] = "" + listVehicles.get(i).getSeats();
				cellData[i][2] = "" + listVehicles.get(i).getSpecialFeatures();
				cellData[i][3] = "" + listVehicles.get(i).getClassification();
				cellData[i][4] = "" + listVehicles.get(i).getAvailable();
				cellData[i][5] = "" + listVehicles.get(i).getPrice();
			
			}
			
			if(type == availableVehicles) //if you want to make a table with list of available vehicles
			{
				DefaultTableModel model = new DefaultTableModel(cellData, columnNames);
				vehicleTable = new JTable( model );
				vehicleTable.setFocusable(true);
				vehicleTable.setColumnSelectionAllowed(false);
				vehicleTable.setRowSelectionAllowed(true);
			}
			else if(type == bookedVehicles)
			{
				DefaultTableModel model = new DefaultTableModel(cellData, columnNames);
				bookedVehicleTable = new JTable( model );
				bookedVehicleTable.setFocusable(true);
				bookedVehicleTable.setColumnSelectionAllowed(false);
				bookedVehicleTable.setRowSelectionAllowed(true);
			}
				
		    
		    //Change default width of table columns
		    TableColumn column = null;
		    for (int i = 0; i < 6; i++) 
		    {
		        column = vehicleTable.getColumnModel().getColumn(i);
		        if (i == 1) 
		        { //Seats column
		            column.setPreferredWidth(10);
		        } else if (i == 2)
		        { //Special features column
		            column.setPreferredWidth(220);
		        }
		        else if (i == 4)
		        { //Available column
		            column.setPreferredWidth(50);
		        }
		        else if (i == 5)
		        { //Price column
		            column.setPreferredWidth(20);
		        }
		    }
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void refreshAvailableVehiclesTable()
	{
		pane.getViewport().remove(vehicleTable);
		createTable(availableVehicles);
		pane.getViewport().add(vehicleTable, null);
	}
	
	public void refreshBookedVehiclesTable()
	{
		bookedVehPane.getViewport().remove(bookedVehicleTable);
		createTable(bookedVehicles);
		bookedVehPane.getViewport().add(bookedVehicleTable, null);
	}

}
