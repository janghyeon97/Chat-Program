package ConsoleChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;


public class ClientGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel BorderPanel, Border_EAST, Border_NORTH, GridPanel, btnSendPanel, TextAreaPanel;
	private JTextArea textArea, input;
	private JLabel NicknameLabel;
	private JButton btnSend;
	private Client client;
	
	public String Nickname = null;
	
	public ClientGUI(Client client) {
		// --------------------[Client백그라운드와 GUI 연결]----------------------
		this.client = client;
		
		Nickname = client.userDto.getUserNickname();
		makeClientGUI();
	}
	
	public void makeClientGUI() {
		// --------------------[레이아웃 세팅]---------------------------
		PanelSetting();
		/////////////////////////////////////////////////////////////////////////////////////////////////
		// --------------------[컴포넌트 생성 및 세팅]----------------------
		textArea = new JTextArea();
		input = new JTextArea();
		NicknameLabel = new JLabel(Nickname);
		btnSend = new JButton("전송");
		
		textArea.setEditable(false);
		NicknameLabel.setBorder(new EmptyBorder(0, 0, 0, getWidth() / 2 + 40));
		input.setPreferredSize(new Dimension(320, 80));
		input.setBackground(new Color(0xFFF5FFFA));
		input.setMargin(new Insets(1, 5, 1, 1));
		btnSend.setPreferredSize(new Dimension(60, 30));
		btnSend.setMargin(new Insets(1, 1, 1, 1));
		btnSend.setBackground(new Color(0xFFFFF8DC));
		btnSend.setForeground(Color.BLACK);
		btnSend.setFont(new Font("나눔고딕", Font.PLAIN, 13));

		/////////////////////////////////////////////////////////////////////////////////////////////////
		// --------------------[컴포넌트 추가]---------------------------

		add(BorderPanel);
		BorderPanel.add(textArea);
		Border_NORTH.add(NicknameLabel);
		TextAreaPanel.add(input);
		btnSendPanel.add(btnSend);

		/////////////////////////////////////////////////////////////////////////////////////////////////
		// --------------------[Listener 등록]------------------------

		btnSend.addActionListener(this);

		input.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Ctrl + Enter = 줄 바꿈
					if (e.isShiftDown())
						input.append(System.lineSeparator());
					else { // Enter = 전송
						System.out.println("input :"+input.getText());
						client.send(" "+Nickname+" :"+input.getText());
						input.setText("");
						input.requestFocus();
						e.consume(); // stop event
					}
				}
			}
		});

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				NicknameLabel.setBorder(new EmptyBorder(0, 0, 0, getWidth() - 150));
				input.setPreferredSize(new Dimension(getWidth() - 90, 80));
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				input.requestFocus();
			}
		});

		/////////////////////////////////////////////////////////////////////////////////////////////////
		// --------------------[각종 설정]----------------------------------

		setMinimumSize(new Dimension(410, 500));
		setVisible(true);
		/////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	public void PanelSetting() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(410, 500);
		setTitle("Client");
		
	    // --------------------[프레임 위치 설정하는 코드]----------------------
	    Dimension frameSize = getSize(); 									// 프레임(자바 화면) 크기
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 크기
	    // (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2
	    setLocation((screenSize.width - frameSize.width) /2, (screenSize.height - frameSize.height) /2);
	    
	    /////////////////////////////////////////////////////////////////////////////////////////////////
	    // --------------------[레이아웃 세팅]------------------------------
		BorderPanel = new JPanel();
		Border_EAST = new JPanel();
		Border_NORTH = new JPanel();
		GridPanel = new JPanel();
		btnSendPanel = new JPanel();
		TextAreaPanel = new JPanel();
		
		BorderPanel.setLayout(new BorderLayout());
		Border_EAST.setLayout(new BorderLayout());
		Border_NORTH.setLayout(new FlowLayout(0));
		GridPanel.setLayout(new GridLayout(2, 1));
		
		Border_NORTH.setBackground(Color.WHITE);
		Border_EAST.setBackground(new Color(0xFFF5FFFA));
		GridPanel.setBackground(new Color(0xFFF5FFFA));
		btnSendPanel.setBackground(new Color(0xFFF5FFFA));
		btnSendPanel.setSize(60, 10);
		TextAreaPanel.setBackground(new Color(0xFFF5FFFA));
		
		BorderPanel.add(Border_EAST, BorderLayout.SOUTH);
		BorderPanel.add(Border_NORTH, BorderLayout.NORTH);
		Border_EAST.add(GridPanel, BorderLayout.EAST);
		Border_EAST.add(TextAreaPanel, BorderLayout.CENTER);
		GridPanel.add(btnSendPanel);
	}
	
	// --------------------['전송'버튼 리스너]----------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnSend) {
			System.out.println("input :"+input.getText());
			client.send(Nickname+" :"+input.getText());
			input.setText("");
			input.requestFocus();
		}
	}
	
	// --------------------[TextArea에 메세지 추가]----------------------
	// Client에서 넘어온 메세지를 textarea에 추가.
	public void append(String msg)
	{
		textArea.append(" "+msg+"\n");
		input.setText("");
		input.requestFocus();
	}	

}
