package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.table.DefaultTableModel;

import Vehicle.AndCriteria;
import Vehicle.Criteria;
import Vehicle.CriteriaAvailable;
import Vehicle.CriteriaClassification;
import Vehicle.CriteriaSeats;
import Vehicle.Vehicle;

public class BrowseVehiclesUI extends JPanel implements ActionListener{
	List<Vehicle> vehicles;
	private JTable table;
	private JScrollPane pane;
	private JComboBox<String> classificationBox;
	private JComboBox<String> seatsBox;

	public BrowseVehiclesUI() {
		setLayout(null);
		
		vehicles = new ArrayList<Vehicle>();
		for(int i = 0; i < 20; i++){
			this.vehicles.add(new Vehicle("model " + i, i, "a", "a", true));
		}
		
		Criteria criteriaAvailable = new CriteriaAvailable();
		vehicles = criteriaAvailable.meetsCriteria(vehicles);
		populateTable();
		
		JLabel Vehicleslbl = new JLabel("Available Vehicles");
		Vehicleslbl.setBounds(10, 10, 180, 25);
		add(Vehicleslbl);
		
		JButton bookBtn = new JButton("Book Vehicle");
		bookBtn.setBounds(300, 275, 150, 25);
		add(bookBtn);
		bookBtn.addActionListener(this);
		bookBtn.setActionCommand("book");
		
		classificationBox = new JComboBox<String>();
		classificationBox.addItem("class1");
		classificationBox.addItem("class2");
		classificationBox.setBounds(300, 50, 150, 25);
		add(classificationBox);
		
		seatsBox = new JComboBox<String>();
		seatsBox.addItem("5 seats");
		seatsBox.addItem("7 seats");
		seatsBox.setBounds(300, 100, 150, 25);
		add(seatsBox);
		
		JButton filterBtn = new JButton("Filter Search");
		filterBtn.setBounds(300, 150, 150, 25);
		add(filterBtn);
		filterBtn.addActionListener(this);
		filterBtn.setActionCommand("filter");
	}

	private void populateTable() {
		
		String[][] cellData = new String[vehicles.size()][1];;
		for(int i = 0; i < vehicles.size(); i++){
			cellData[i][0] = "" + vehicles.get(i).getModel();
		}
			
	    String[] columnNames = { "Model"};

	    DefaultTableModel model = new DefaultTableModel(cellData, columnNames);
	    
	    table = new JTable(model);
	    pane = new JScrollPane(table);
		pane.setBounds(10, 50, 250, 250);
	    add(pane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("book" == e.getActionCommand()){
			int a = table.getSelectedRow();
			if (a >= 0){
				Vehicle v = vehicles.get(a);
				v.setBooked(null, null);
				((DefaultTableModel)table.getModel()).removeRow(a);
				JOptionPane.showMessageDialog(null, v.getModel() + " booked");
			}
		}
		else if ("filter" == e.getActionCommand()){
			System.out.print("DEBUG: " + classificationBox.getSelectedIndex());
			
			String classification;
			if (classificationBox.getSelectedIndex() == 0)
				classification = "class1";
			else
				classification = "class2";
			
			int seats;
			if (seatsBox.getSelectedIndex() == 0)
				seats = 4;
			else
				seats = 6;
			
			Criteria criteria2 = new AndCriteria(new CriteriaClassification(classification), new CriteriaSeats(seats));
			vehicles = criteria2.meetsCriteria(vehicles);
			populateTable();
		}
		
	}

}
