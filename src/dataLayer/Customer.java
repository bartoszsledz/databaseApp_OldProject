package dataLayer;

public class Customer {
	
	private String name;
	private String surname;
	private String username;
	private String password;
	private int userLp;
	private long userId;

	public Customer(int userLp, String name, String surname, String username, String password, long userId){
		this.userLp = userLp;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getUserLp() {
		return userLp;
	}

	public void setUserLp(int userLp) {
		this.userLp = userLp;
	}
}
