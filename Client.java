package ConsoleChat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	private ClientGUI clientGUI;
	private Socket socket = null;
	public userDTO userDto;
	private String IP = "127.0.0.1";
	private int port = 2000;
	private int result = 0;
	
	public Client() {
		startClient();
	}
	
	public void startClient() {
		Thread thread = new Thread() {
			public void run() {
				try {
					socket = new Socket(IP, port);
					result = 2;
					receive();
				} catch (Exception e) {
					System.out.println("[서버 접속 실패]");
					result = 3;
					if(!socket.isClosed()) {
						stopClient();
					}
				}
	
			} 
		};
		thread.start();
	}

	public void stopClient() {
		try {
			if(socket != null || !socket.isClosed()) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void receive() {
		while(true) {
			try {
				InputStream in = socket.getInputStream();
				byte[] buffer = new byte[512];					
				int length = in.read(buffer);
				if(length == -1) throw new IOException();
				String msg = new String(buffer, 0, length, "UTF-8");
				clientGUI.append(msg);
			} catch(Exception e) {
				stopClient();
			}
		}
	}
	
	public void send(String msg) {
		Thread thread = new Thread() {
			public void run() {
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = msg.getBytes("UTF-8");
					out.write(buffer);
					out.flush();
				} catch (Exception e) {
					stopClient();
				}
			}
		};
		thread.start();
	}
	
	public void createClientGUI() {
		clientGUI = new ClientGUI(this);
	}
	
	public void setUserDTO(userDTO dto) {
		System.out.println(dto.getUserName());
		System.out.println(dto.getUserNickname());
		System.out.println(dto.getUserGender());
		System.out.println(dto.getUserAge());
		this.userDto = dto;
		
	}
	
	public int getResult() {
		return result;
	}
}
