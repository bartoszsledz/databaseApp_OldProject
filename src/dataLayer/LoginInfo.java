package dataLayer;

public class LoginInfo {

	private String username, password;
	
	public LoginInfo(String username, String password){
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
}
