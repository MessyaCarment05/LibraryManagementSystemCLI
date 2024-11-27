package librarycli;

public abstract class User {
	private String userID;
	private String name;
	private String email;
	private String phoneNumber;
	public User(String userID, String name, String email, String phoneNumber) {
		super();
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return "UserID = " + userID +"\n"+ "Name = " + name + "\n"+ "Email = " + email + "\n"+ "Phone Number = " + phoneNumber + "\n";
	}
	abstract public void showMenu();
}
