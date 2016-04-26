package presentationLayer;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DataBaseWindow {

	private static final int WIDTH = 900;
	private static final int HEIGHT = 400;
	
	private static JFrame frame;
	private JPanel contentPane;
	private JTable table;
	private JButton updateButton, logoutButton, addButton, deleteButton, loadButton;
	private JScrollPane scrollPane;
	private JLabel surnameLabel, lpLabel, nameLabel, passwordLabel, usernameLabel, idLabel;
	private JTextField lpField, nameField, surnameField, usernameField, passwordField, idField;

	public DataBaseWindow() {
		initialize();
	}

	private void initialize() {
		createFrame();
		defaultSizeFrame();
		createButtons();
		createTable();
		createLabels();
		createFields();
	}

	private void createFrame() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Data Base Client");
		frame.setLocationRelativeTo(null);

		contentPane = new JPanel();
		frame.setContentPane(contentPane);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 600, 330);
		contentPane.add(scrollPane);
	}
	
	private void defaultSizeFrame() {
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		frame.setBounds(center.x - WIDTH / 2, center.y - HEIGHT / 2, WIDTH, HEIGHT);
	}

	private void createTable() {
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		model.addColumn("Lp");
		model.addColumn("Name");
		model.addColumn("Surname");
		model.addColumn("Username");
		model.addColumn("Password");
		model.addColumn("ID");
		scrollPane.setViewportView(table);
	}

	private void createLabels() {
		Font font = new Font("Courier New", Font.ITALIC, 16);

		lpLabel = new JLabel("Lp:");
		lpLabel.setFont(font);
		lpLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lpLabel.setBounds(620, 20, 93, 25);
		contentPane.add(lpLabel);

		nameLabel = new JLabel("Name:");
		nameLabel.setFont(font);
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(620, 56, 93, 25);
		contentPane.add(nameLabel);

		surnameLabel = new JLabel("Surname:");
		surnameLabel.setFont(font);
		surnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		surnameLabel.setBounds(620, 92, 93, 25);
		contentPane.add(surnameLabel);

		usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(font);
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setBounds(620, 128, 93, 25);
		contentPane.add(usernameLabel);

		passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(font);
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setBounds(620, 164, 93, 25);
		contentPane.add(passwordLabel);

		idLabel = new JLabel("ID:");
		idLabel.setFont(font);
		idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		idLabel.setBounds(620, 200, 93, 25);
		contentPane.add(idLabel);
	}

	private void createFields() {
		lpField = new JTextField();
		lpField.setBounds(723, 20, 152, 25);
		contentPane.add(lpField);
		lpField.setColumns(10);
		lpField.setEditable(false);

		nameField = new JTextField();
		nameField.setBounds(723, 56, 152, 25);
		contentPane.add(nameField);
		nameField.setColumns(10);

		surnameField = new JTextField();
		surnameField.setBounds(723, 92, 152, 25);
		contentPane.add(surnameField);
		surnameField.setColumns(10);

		usernameField = new JTextField();
		usernameField.setBounds(723, 128, 152, 25);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JTextField();
		passwordField.setBounds(723, 164, 152, 25);
		contentPane.add(passwordField);
		passwordField.setColumns(10);

		idField = new JTextField();
		idField.setBounds(723, 200, 152, 25);
		contentPane.add(idField);
		idField.setColumns(10);
	}

	private void createButtons() {
		updateButton = new JButton("Update");
		updateButton.setBounds(785, 238, 90, 25);
		contentPane.add(updateButton);

		logoutButton = new JButton("Logout");
		logoutButton.setBounds(785, 327, 90, 25);
		contentPane.add(logoutButton);

		addButton = new JButton("Add New");
		addButton.setBounds(685, 274, 90, 25);
		contentPane.add(addButton);

		deleteButton = new JButton("Delete");
		deleteButton.setBounds(785, 274, 90, 25);
		contentPane.add(deleteButton);

		loadButton = new JButton("Load");
		loadButton.setBounds(686, 239, 90, 25);
		contentPane.add(loadButton);
	}
	
	public void addLogoutButtonActionListener(ActionListener actionListener) {
		logoutButton.addActionListener(actionListener);
	}
	
	public void addLoadButtonActionListener(ActionListener actionListener) {
		loadButton.addActionListener(actionListener);
	}
	
	public void addUpdateButtonActionListener(ActionListener actionListener) {
		updateButton.addActionListener(actionListener);
	}

	public void addAddButtonActionListener(ActionListener actionListener) {
		addButton.addActionListener(actionListener);
	}
	
	public void addDeleteButtonActionListener(ActionListener actionListener) {
		deleteButton.addActionListener(actionListener);
	}

	public  JTextField getlpField() {
		return lpField;
	}

	public int getLp() {
		return Integer.parseInt(lpField.getText());
	}

	public JTextField getNameField() {
		return nameField;
	}

	public String getName() {
		return nameField.getText();
	}

	public JTextField getSurnameField() {
		return surnameField;
	}

	public String getSurname() {
		return surnameField.getText();
	}

	public JTextField getUsernameField() {
		return usernameField;
	}

	public String getUsername() {
		return usernameField.getText();
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public String getPassword() {
		return passwordField.getText();
	}

	public JTextField getidField() {
		return idField;
	}

	public long getId() {
		return Long.parseLong(idField.getText());
	}

	public static void hideFrame() {
		frame.setVisible(false);
	}

	public void showFrame() {
		frame.setVisible(true);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
}
