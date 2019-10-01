package ConsoleChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


public class LoginGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel BorderPanel, FlowPanel, IdPanel, PwPanel;
	private JPanel FlowPanel_Join, IdPanel_Join, PwPanel_Join, NamePanel, NicknamePanel, GenderPanel, AgePanel;
	private JLabel l1, JoinLabel, blank, blank2;
	private JLabel IdLabel, PwLabel, NameLabel, NicknameLabel, GenderLabel, AgeLabel;
	private JButton btnOK, btnJOIN;
	private JTextField userID, userName, userNickname, userGender, userAge;
	private JPasswordField userPassword;
	private Client client;
	private LoginBG loginBG;
	
	public LoginGUI() {
		client = new Client();
		loginBG = new LoginBG();
		loginBG.connection();
		makeInterface(true);
	}	
	
	public void makeInterface(boolean key) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(410, 500);
		setTitle("Login");
		setMinimumSize(new Dimension(410, 500));;
		
		// --------------------[프레임 위치 설정]----------------------
	    // 프레임(자바 화면) 크기
	    Dimension frameSize = getSize();
	    // 모니터 크기
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    // (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2
	    setLocation((screenSize.width - frameSize.width) /2, (screenSize.height - frameSize.height) /2);
	    
	    BorderPanel = new JPanel();
	    add(BorderPanel);
	    
		loginFrame();
		
		setVisible(key);
		/////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	public void loginFrame() {
	    // --------------------[레이아웃 세팅]----------------------
	    
	   
	    FlowPanel = new JPanel();
	    FlowPanel_Join = new JPanel();
	    IdPanel = new JPanel();
	    PwPanel = new JPanel();
	    
	    BorderPanel.setLayout(new BorderLayout());
	    FlowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
	    IdPanel.setLayout(new BorderLayout());
	    PwPanel.setLayout(new BorderLayout());
	    
	    BorderPanel.setBackground(new Color(0xFFF5FFFA));
	    FlowPanel.setBackground(new Color(0xFFF5FFFA));
	    IdPanel.setPreferredSize(new Dimension(180, 30));
		IdPanel.setBorder(new EtchedBorder());
		IdPanel.setBackground(Color.WHITE);
		PwPanel.setPreferredSize(new Dimension(180, 30));
		PwPanel.setBorder(new EtchedBorder());
		PwPanel.setBackground(Color.WHITE);
		
	    // --------------------[컴포넌트 생성 및 세팅]----------------------
		
		btnOK = new JButton("확인");
		l1 = new JLabel("로그인");
		blank = new JLabel("");
		blank2 = new JLabel("");
		JoinLabel = new JLabel("회원가입");
		userID = new JTextField(15);
		userPassword = new JPasswordField(15);
		
	
		l1.setBorder(new EmptyBorder(getHeight()/2 - 80, getWidth()/3, 5, getWidth()/3)); // top left bottom right
		l1.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		blank.setBorder(new EmptyBorder(5, getWidth()/3, 1, getWidth()/3));
		blank2.setBorder(new EmptyBorder(5, getWidth()/3, 1, getWidth()/3));
		userID.setBorder(new EmptyBorder(0, 0, 0, 0));
		userID.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		userPassword.setBorder(new EmptyBorder(0, 0, 0, 0));
		userPassword.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		btnOK.setPreferredSize(new Dimension(180, 30));
		btnOK.setBackground(Color.YELLOW);
		JoinLabel.setForeground(Color.GRAY);
		JoinLabel.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		
		// --------------------[컴포넌트 추가]----------------------
		
		
		BorderPanel.add(FlowPanel, BorderLayout.CENTER);
		FlowPanel.add(l1);
		FlowPanel.add(IdPanel);
		FlowPanel.add(PwPanel);
		FlowPanel.add(blank);
		FlowPanel.add(btnOK);
		FlowPanel.add(blank2);
		FlowPanel.add(JoinLabel);
		IdPanel.add(userID);
		PwPanel.add(userPassword);
		
		// --------------------[Listener 등록]----------------------
		
		btnOK.addActionListener(this);

		userID.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)  		// Ctrl + Enter = 줄 바꿈
					loginCheck();
			}
		});
		
		userPassword.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)  		// Ctrl + Enter = 줄 바꿈
					loginCheck();
			}
		});
		
		JoinLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FlowPanel.setVisible(false);
				BorderPanel.remove(FlowPanel);
				joinFrame();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				JoinLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}

		});
		// --------------------[각종 설정]----------------------
	}
	
	public void joinFrame() {
		FlowPanel_Join = new JPanel();
		IdPanel_Join = new JPanel();
		PwPanel_Join = new JPanel();
		NamePanel = new JPanel();
		NicknamePanel = new JPanel();
		GenderPanel = new JPanel();
		AgePanel = new JPanel();
		
		
		BorderPanel.setBackground(new Color(0xFFF5FFFA));
		FlowPanel_Join.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
	    FlowPanel_Join.setBackground(new Color(0xFFF5FFFA));
	    IdPanel_Join.setLayout(new BorderLayout());
	    PwPanel_Join.setLayout(new BorderLayout());
	    NamePanel.setLayout(new BorderLayout());
	    NicknamePanel.setLayout(new BorderLayout());
	    GenderPanel.setLayout(new BorderLayout());
	    AgePanel.setLayout(new BorderLayout());
		
		IdPanel_Join.setPreferredSize(new Dimension(180, 25));
		IdPanel_Join.setBorder(new EtchedBorder());
		IdPanel_Join.setBackground(Color.WHITE);
		PwPanel_Join.setPreferredSize(new Dimension(180, 25));
		PwPanel_Join.setBorder(new EtchedBorder());
		PwPanel_Join.setBackground(Color.WHITE);
		NamePanel.setPreferredSize(new Dimension(180, 25));
		NamePanel.setBorder(new EtchedBorder());
		NamePanel.setBackground(Color.WHITE);
		NicknamePanel.setPreferredSize(new Dimension(180, 25));
		NicknamePanel.setBorder(new EtchedBorder());
		NicknamePanel.setBackground(Color.WHITE);
		GenderPanel.setPreferredSize(new Dimension(180, 25));
		GenderPanel.setBorder(new EtchedBorder());
		GenderPanel.setBackground(Color.WHITE);
		AgePanel.setPreferredSize(new Dimension(180, 25));
		AgePanel.setBorder(new EtchedBorder());
		AgePanel.setBackground(Color.WHITE);
	
		
		blank = new JLabel("");
		IdLabel = new JLabel("아이디");
		PwLabel = new JLabel("비밀번호");
		NameLabel = new JLabel("실명");
		NicknameLabel = new JLabel("닉네임");
		GenderLabel = new JLabel("성별");
		AgeLabel = new JLabel("나이");
		btnJOIN = new JButton("회원가입");
		userID = new JTextField(15);
		userPassword = new JPasswordField(15);
		userName = new JTextField(15);
		userNickname = new JTextField(15);
		userGender = new JTextField(15);
		userAge = new JTextField(15);
		
		
		IdLabel.setBorder(new EmptyBorder(50, getWidth()/3, 0, getWidth()/3)); 
		IdLabel.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		PwLabel.setBorder(new EmptyBorder(5, getWidth()/3, 0, getWidth()/3)); 
		PwLabel.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		NameLabel.setBorder(new EmptyBorder(5, getWidth()/3, 0, getWidth()/3)); 
		NameLabel.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		NicknameLabel.setBorder(new EmptyBorder(5, getWidth()/3, 0, getWidth()/3)); 
		NicknameLabel.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		GenderLabel.setBorder(new EmptyBorder(5, getWidth()/3, 0, getWidth()/3)); 
		GenderLabel.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		AgeLabel.setBorder(new EmptyBorder(5, getWidth()/3, 0, getWidth()/3)); 
		AgeLabel.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		blank.setBorder(new EmptyBorder(50, getWidth()/3, 1, getWidth()/3));
		btnJOIN.setPreferredSize(new Dimension(180, 30));
		btnJOIN.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		btnJOIN.setBackground(new Color(0xFFFF99));
		userID.setBorder(new EmptyBorder(0, 0, 0, 0));
		userID.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		userID.setMargin(new Insets(0, 0, 1, 0));
		userPassword.setBorder(new EmptyBorder(0, 0, 0, 0));
		userPassword.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		userName.setBorder(new EmptyBorder(0, 0, 0, 0));
		userName.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		userNickname.setBorder(new EmptyBorder(0, 0, 0, 0));
		userNickname.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		userGender.setBorder(new EmptyBorder(0, 0, 0, 0));
		userGender.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		userAge.setBorder(new EmptyBorder(0, 0, 0, 0));
		userAge.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		
		
		FlowPanel_Join.add(IdLabel);
		FlowPanel_Join.add(IdPanel_Join);
		FlowPanel_Join.add(blank);
		FlowPanel_Join.add(PwLabel);
		FlowPanel_Join.add(PwPanel_Join);
		FlowPanel_Join.add(NameLabel);
		FlowPanel_Join.add(NamePanel);
		FlowPanel_Join.add(NicknameLabel);
		FlowPanel_Join.add(NicknamePanel);
		FlowPanel_Join.add(GenderLabel);
		FlowPanel_Join.add(GenderPanel);
		FlowPanel_Join.add(AgeLabel);
		FlowPanel_Join.add(AgePanel);	
		FlowPanel_Join.add(blank);
		FlowPanel_Join.add(btnJOIN);	
		
		IdPanel_Join.add(userID);
		PwPanel_Join.add(userPassword);
		NamePanel.add(userName);
		NicknamePanel.add(userNickname);
		GenderPanel.add(userGender);
		AgePanel.add(userAge);
		
		BorderPanel.add(FlowPanel_Join, BorderLayout.CENTER);
		
		btnJOIN.addActionListener(this);
	}
	
	
	
	public void loginCheck() {
		while(true) {
			System.out.print("r");
			if(client.getResult() > 1)
				break;
		}
		
		if (client.getResult() == 2) {
			if(loginBG.login(userID.getText(), new String(userPassword.getPassword())) == 1) {
				client.setUserDTO(loginBG.getUser(userID.getText()));
				client.createClientGUI();
				setVisible(false);
			} 
			else {
				l1.setText("아이디 또는 비밀번호를 다시 확인해 주세요.");
				l1.setForeground(Color.RED);
			}
		}
		else 
		{
			l1.setText("서버 접속에 실패했습니다.");
			l1.setForeground(Color.RED);
			btnOK.setEnabled(false);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOK) {
			loginCheck();
		}
		else if(e.getSource() == btnJOIN) {
			userDTO user = new userDTO();
			
			user.setUserID(userID.getText());
			user.setUserPassword(new String(userPassword.getPassword()));
			user.setUserName(userName.getText());
			user.setUserNickname(userNickname.getText());
			user.setUserGender(userGender.getText());
			user.setUserAge(Integer.parseInt(userAge.getText()));
			
			loginBG.join(user);
			
			FlowPanel_Join.setVisible(false);
			BorderPanel.remove(FlowPanel_Join);
			loginFrame();
		}
	}
	
	public static void main(String[] args) {
		new LoginGUI();
	}
}
