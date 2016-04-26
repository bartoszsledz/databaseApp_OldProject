package presentationLayer;

import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow {

	private static final int WIDTH = 400;
	private static final int HEIGHT = 280;

	private static JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel usernameLabel, passwordLabel;
	private JButton loginButton, exitButton;

	public LoginWindow() {
		initialize();
	}

	private void initialize() {
		frameSettings();
		defaultSizeFrame();
		createUsernameSettings();
		createPasswordSettings();
		createButtonsSettings();
		showFrame();
	}

	private void frameSettings() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Data Base Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
	}

	private void defaultSizeFrame() {
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		frame.setBounds(center.x - WIDTH / 2, center.y - HEIGHT / 2, WIDTH, HEIGHT);
	}

	private void createUsernameSettings() {
		usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(40, 60, 70, 25);
		frame.getContentPane().add(usernameLabel);

		usernameField = new JTextField();
		usernameField.setBounds(120, 60, 200, 25);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
	}

	private void createPasswordSettings() {
		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(40, 120, 70, 25);
		frame.getContentPane().add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(120, 120, 200, 25);
		frame.getContentPane().add(passwordField);
	}

	private void createButtonsSettings() {
		loginButton = new JButton("Login");
		loginButton.setBounds(230, 175, 90, 25);
		frame.getContentPane().add(loginButton);

		exitButton = new JButton("Exit");
		exitButton.setBounds(120, 175, 90, 25);
		frame.getContentPane().add(exitButton);
	}
	
	public void addLoginButtonActionListener(ActionListener actionListener) {
		loginButton.addActionListener(actionListener);
	}
	
	public void addCloseButtonActionListener(ActionListener actionListener) {
		exitButton.addActionListener(actionListener);
	}
	
	public static void closeFrame() {
		frame.dispose();
	}

	public static void showFrame() {
		frame.setVisible(true);
	}
	
	public JTextField getUsernameField() {
		return usernameField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public String getUsername() {
		return usernameField.getText();
	}

	public String getPassword() {
		return String.valueOf(passwordField.getPassword());
	}
}
