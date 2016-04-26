package businessLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;

import dataLayer.LoginInfo;
import presentationLayer.LoginWindow;

public class LoginWindowManager {

	private final String DB_URL = "jdbc:mysql://localhost:3306/database";
	private Connection connection;

	private LoginWindow loginWindow;
	private DataBaseWindowManager dataBaseWindowManager = new DataBaseWindowManager();

	public LoginWindowManager(LoginWindow loginWindow) {
		this.loginWindow = loginWindow;
		addButtonsListeners();
	}

	private void dataBaseConnection() {
		LoginInfo loginInfo = new LoginInfo(loginWindow.getUsername(), loginWindow.getPassword());

		if (loginWindow.getUsername().isEmpty() || loginWindow.getPassword().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Enter username and  password!");
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(DB_URL, loginInfo.getUsername(), loginInfo.getPassword());
				dataBaseWindowManager.setConnection(connection);
				if (!connection.isClosed()) {
					dataBaseWindowManager.changeWindow();
				}
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Not found data base");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Failed to connect to the database!");
			}
		}
		// return connection;
	}

	private void addButtonsListeners() {
		loginButtonListener();
		closeButtonListener();
	}

	private void loginButtonListener() {
		loginWindow.addLoginButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dataBaseConnection();
			}
		});
	}

	private void closeButtonListener() {
		loginWindow.addCloseButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
	}
}
