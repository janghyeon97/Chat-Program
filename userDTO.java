package ConsoleChat;

public class userDTO {
	private String userID = null;
	private String userPassword = null;
	private String userName;
	private String userNickname;
	private String userGender;
	private int userAge;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
}
