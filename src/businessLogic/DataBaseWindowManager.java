package businessLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import dataLayer.Customer;
import presentationLayer.DataBaseWindow;
import presentationLayer.LoginWindow;

public class DataBaseWindowManager {

	private Connection connection;
	private DefaultTableModel model;
	private PreparedStatement preparedStatement;
	private ArrayList<Customer> customerList;

	private DataBaseWindow dataBaseWindow = new DataBaseWindow();

	public DataBaseWindowManager() {
		addButtonsListeners();
	}

	private ArrayList<Customer> getAllCustomerList() {
		customerList = new ArrayList<Customer>();
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM employeedatabase");
			Customer customer;
			while (rs.next()) {
				customer = new Customer(rs.getInt("Lp"), rs.getString("Name"), rs.getString("Surname"),
						rs.getString("Username"), rs.getString("Password"), rs.getLong("Id"));
				customerList.add(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerList;
	}

	private void showCustomerInTable() {

		ArrayList<Customer> list = getAllCustomerList();
		model = (DefaultTableModel) dataBaseWindow.getTable().getModel();
		Object[] row = new Object[6];
		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getUserLp();
			row[1] = list.get(i).getName();
			row[2] = list.get(i).getSurname();
			row[3] = list.get(i).getUsername();
			row[4] = list.get(i).getPassword();
			row[5] = list.get(i).getUserId();

			model.addRow(row);
		}
	}

	private void loadDataToEdit() {
		try {
			int i = (int) dataBaseWindow.getTable().getSelectedRow();
			TableModel model = (DefaultTableModel) dataBaseWindow.getTable().getModel();
			dataBaseWindow.getlpField().setText(model.getValueAt(i, 0).toString());
			dataBaseWindow.getNameField().setText(model.getValueAt(i, 1).toString());
			dataBaseWindow.getSurnameField().setText(model.getValueAt(i, 2).toString());
			dataBaseWindow.getUsernameField().setText(model.getValueAt(i, 3).toString());
			dataBaseWindow.getPasswordField().setText(model.getValueAt(i, 4).toString());
			dataBaseWindow.getidField().setText(model.getValueAt(i, 5).toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "First select the person to edit!");
		}
	}

	private void updateDataToBase() {
		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE employeedatabase SET Lp= ?, Name= ?, Surname= ?, Username= ?, Password= ?, Id= ? WHERE Lp = ?");

			preparedStatement.setInt(1, dataBaseWindow.getLp());
			preparedStatement.setString(2, dataBaseWindow.getName());
			preparedStatement.setString(3, dataBaseWindow.getSurname());
			preparedStatement.setString(4, dataBaseWindow.getUsername());
			preparedStatement.setString(5, dataBaseWindow.getPassword());
			preparedStatement.setLong(6, dataBaseWindow.getId());
			preparedStatement.setInt(7, dataBaseWindow.getLp());

			preparedStatement.executeUpdate();
			refreshAllCusomersFromTable();
			showCustomerInTable();
			JOptionPane.showMessageDialog(null, "Data upload succesfully!");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Check the data format or press Load!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Data not upload!");
		}
	}

	private void addDataToBase() {
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO employeedatabase (Name, Surname, Username, Password, Id) VALUES (?,?,?,?,?)");
			preparedStatement.setString(1, dataBaseWindow.getName());
			preparedStatement.setString(2, dataBaseWindow.getSurname());
			preparedStatement.setString(3, dataBaseWindow.getUsername());
			preparedStatement.setString(4, dataBaseWindow.getPassword());
			preparedStatement.setLong(5, dataBaseWindow.getId());

			preparedStatement.execute();
			refreshAllCusomersFromTable();
			showCustomerInTable();
			JOptionPane.showMessageDialog(null, "New client has been added correctly");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Data not have been added!");
			e.printStackTrace();
		}
	}

	private void deleteDataFromBase() {
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM employeedatabase WHERE Id=?");
			preparedStatement.setString(1, JOptionPane.showInputDialog(null, "Enter customer id you want to delete:",
					"Enter ID", JOptionPane.PLAIN_MESSAGE));
			preparedStatement.executeUpdate();
			refreshAllCusomersFromTable();
			showCustomerInTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		if (connection != null) {
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Warning!",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				DataBaseWindow.hideFrame();
				LoginWindow.showFrame();
				try {
					connection.close();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Problem closing the connection.");
				}
			}
		}
	}

	public void changeWindow() {
		JOptionPane.showMessageDialog(null, "You are connected!");
		LoginWindow.closeFrame();
		dataBaseWindow.showFrame();
		showCustomerInTable();
	}

	private void refreshAllCusomersFromTable() {
		if (model.getRowCount() > 0) {
			for (int i = model.getRowCount() - 1; i > -1; i--) {
				model.removeRow(i);
			}
		}
	}

	private void addButtonsListeners() {
		updateButtonListener();
		loadButtonListener();
		addButtonListener();
		deleteButtonActionListener();
		logoutButtonActionListener();
	}

	private void updateButtonListener() {
		dataBaseWindow.addUpdateButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateDataToBase();
			}
		});
	}

	private void loadButtonListener() {
		dataBaseWindow.addLoadButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadDataToEdit();
			}
		});
	}

	private void addButtonListener() {
		dataBaseWindow.addAddButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addDataToBase();
			}
		});
	}

	private void deleteButtonActionListener() {
		dataBaseWindow.addDeleteButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteDataFromBase();
			}
		});
	}

	private void logoutButtonActionListener() {
		dataBaseWindow.addLogoutButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeConnection();
			}
		});
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}