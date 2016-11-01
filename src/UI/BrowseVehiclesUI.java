package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Vehicle.Vehicle;

public class BrowseVehiclesUI extends JPanel implements ActionListener{
	List<Vehicle> vehicles;
	private JTable table;
	private JScrollPane pane;

	public BrowseVehiclesUI() {
		setLayout(null);
		
		vehicles = new ArrayList<Vehicle>();
		for(int i = 0; i < 20; i++){
			this.vehicles.add(new Vehicle("model " + i, i, "a", "a", true));
		}
		populateTable();
		
		JLabel Vehicleslbl = new JLabel("Available Vehicles");
		Vehicleslbl.setBounds(10, 10, 180, 25);
		add(Vehicleslbl);
		
		JButton bookBtn = new JButton("Book Vehicle");
		bookBtn.setBounds(300, 275, 150, 25);
		add(bookBtn);
		bookBtn.addActionListener(this);
		bookBtn.setActionCommand("book");
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
				JOptionPane.showMessageDialog(null, v.getModel() + " booked");
			}
		}
		
	}

}
