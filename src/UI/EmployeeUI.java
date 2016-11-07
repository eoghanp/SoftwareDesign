package UI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	private JTextField seatNumberTxt;
	private JLabel features;
	private JTextField featuresTxt;
	private JLabel carClass;
	private JTextField carClassTxt;
	private JLabel availableLbl;
	private JComboBox<String> available;
	private String availableOpts[] = {"Yes", "No"};
	private JLabel price;
	private JTextField priceTxt;
	
	//Delete Vehicle components
	private JTable vehicleTable;
	private JScrollPane pane;
	
	Caretaker caretaker = new Caretaker();
	Originator originator = new Originator();
	int currentVehicle = -1;
	
	public EmployeeUI()
	{
		setLayout(null);
		createAddVehicleGUIs();
		createDeleteVehicleGUIsAndTable();
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
		seatNumberTxt = new JTextField(20);
		seatNumberTxt.setBounds(140, 80, 200, 25);
		add(seatNumberTxt);
		
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
		carClassTxt = new JTextField(20);
		carClassTxt.setBounds(140, 170, 200, 25);
		add(carClassTxt);
		
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
		createTable();
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

	@Override
	public void actionPerformed(ActionEvent event) 
	{

		Vehicle vehicle = new Vehicle();
		vehicle.insertModel();

		if(currentVehicle <= 0)
			undoDeleteBtn.setEnabled(false);

		//When 'Add Vehicle' button is clicked
		if ("addVehicle" == event.getActionCommand())
		{
			//Check if all fields are not empty
			if ((modelNameTxt.getText().equals("")) || (seatNumberTxt.getText().equals("")) || (featuresTxt.getText().equals("")) || (carClassTxt.getText().equals("")) || (priceTxt.getText().equals("")))
			{
				  JOptionPane.showMessageDialog(null, "You must enter details in all text fields", "Enter all data", 2);
			}
			else //When fields are valid
			{	
				int seatsNumber = Integer.parseInt(seatNumberTxt.getText());
				double price = Double.parseDouble(priceTxt.getText());
				boolean availableBool;
				if(String.valueOf(available.getSelectedItem()) == "No"){
					availableBool = false;
				}
				else availableBool = true;
				
				VehicleBuilder vb = new VehicleBuilder(modelNameTxt.getText(), price).seats(seatsNumber).specialFeatures(featuresTxt.getText()).classification(carClassTxt.getText()).available(availableBool);
				Vehicle aVehicle = vb.createVehicle();
				//Vehicle aVehicle = new Vehicle(modelNameTxt.getText(),seatsNumber,featuresTxt.getText(),carClassTxt.getText(), availableBool ,price);
				//DBHandler handler = DBHandler.getSingletonInstance();
				//handler.saveVehicle(aVehicle);
				//Add vehicle to table
				createTable();
				
				vehicle.insertModel();
					
				JOptionPane.showMessageDialog(null,"Vehicle Added");

					//Vehicle aVehicle = new Vehicle(modelNameTxt.getText(),seatsNumber,featuresTxt.getText(),carClassTxt.getText(), availableBool ,price);
					DBHandler handler = DBHandler.getSingletonInstance();
					handler.saveVehicle(aVehicle);
					//Refreshes table to show new vehicle
					refreshTable();
					JOptionPane.showMessageDialog(null,"Vehicle Added");

						
				clearAddVehicleForm();
					
			}
		}
		//When Delete Vehicle Button is clicked
		else if("deleteVehicle" == event.getActionCommand())
		{
			int row = vehicleTable.getSelectedRow();
			if(row == -1){
				JOptionPane.showMessageDialog(null,"Please select a vehicle to delete", "Select a Row", 2);
			}	
			else
			{
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
					refreshTable();
					JOptionPane.showMessageDialog(null,selectedItem + " vehicle deleted");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//When undo button is clicked
		else if("undoDelete" == event.getActionCommand())
		{
			try {
				String recoveredDeletedVehicle = originator.restoreFromMemento(caretaker.getMemento(currentVehicle));
				//System.out.println("After undo operation: " + recoveredDeletedVehicle);
				currentVehicle--;
				DBHandler handler = DBHandler.getSingletonInstance();
				String recovered = handler.getLastDeletedVehicle(recoveredDeletedVehicle);
				System.out.println("vehicle recovered from file: " + recovered);
				refreshTable();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	private void clearAddVehicleForm(){
		  modelNameTxt.setText("");
		  seatNumberTxt.setText("");
		  featuresTxt.setText("");
		  carClassTxt.setText("");
		  priceTxt.setText("");
	  }
	
	
	public void createTable()
	{
		try {
			String[] columnNames = { "Model", "Seats", "Special Features", "Classification", "Available", "Price"};
			DBHandler db = DBHandler.getSingletonInstance();
			List<Vehicle> listVehicles;
			listVehicles = db.getListOfVehicles();
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
			
			DefaultTableModel model = new DefaultTableModel(cellData, columnNames);
			vehicleTable = new JTable( model );
			vehicleTable.setFocusable(true);
			vehicleTable.setColumnSelectionAllowed(false);
			vehicleTable.setRowSelectionAllowed(true);
		    //vehicleTable = new JTable(cellData, columnNames);
		    System.out.println("The table has " + vehicleTable.getModel().getRowCount() + "rows");
		    
		    //Change default width of table columns
		    TableColumn column = null;
		    for (int i = 0; i < 6; i++) {
		        column = vehicleTable.getColumnModel().getColumn(i);
		        if (i == 1) { //Seats column
		            column.setPreferredWidth(10);
		        } else if (i == 2){ //Special features column
		            column.setPreferredWidth(220);
		        }
		        else if (i == 4){ //Available column
		            column.setPreferredWidth(50);
		        }
		        else if (i == 5){ //Price column
		            column.setPreferredWidth(20);
		        }
		    }
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void refreshTable()
	{
		pane.getViewport().remove(vehicleTable);
		createTable();
		pane.getViewport().add(vehicleTable, null);
	}

}
