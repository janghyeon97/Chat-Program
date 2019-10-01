package ConsoleChat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginBG {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String uid = "scott";
	private String upw = "tiger";
	
	public LoginBG() {}
	
	public boolean connection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, upw);
			System.out.println("connect");
			return true; // 데이터베이스 접속 성공
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false; // 데이터베이스 오류
	}
	
	public userDTO getUser(String userID) {
		String SQL = "SELECT userName, userNickname, userGender, userAge FROM MEMBER WHERE userID = ?";
		userDTO userDto = new userDTO();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				userDto.setUserName(rs.getString(1));
				userDto.setUserNickname(rs.getString(2));
				userDto.setUserGender(rs.getString(3));
				userDto.setUserAge(rs.getInt(4));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
			
		return userDto;
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM MEMBER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			System.out.println("아이디 :"+userID);
			System.out.println("비밀번호 :"+userPassword);
			if(rs.next()) { 
				if(rs.getString(1).equals(userPassword)) {
					return 1;  //로그인 성공
				}
				else {
					return 0; //비밀번호 불일치
				}
			}
			
			return -1; //아이디 없음
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -2; //데이터베이스 오류
	}
	
	public int join(userDTO user) {
		String SQL = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserNickname());
			pstmt.setString(5, user.getUserGender());
			pstmt.setInt(6, user.getUserAge());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1; //데이터베이스 오류
	}
}
