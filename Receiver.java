package ConsoleChat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Receiver {
	Socket socket = null;
	
	public Receiver(Socket socket) {
		this.socket = socket;
		receive();
	}
	
	public void receive() {
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				try {
					while(true) {
						InputStream in = socket.getInputStream();
						byte[] buffer = new byte[512];
						int length = in.read(buffer);
						while(length == -1) throw new IOException();
						System.out.println("[메세지 수신 성공] "
							+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName());
						String msg = new String(buffer, 0, length, "UTF-8");
					for(Receiver client : TCP.clients) {
						client.send(msg);
						}
					}
				} catch (Exception e) {
					try {
						System.out.println("[메세지 수신 오류] "
							+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		};
		TCP.threadpool.submit(thread);
	}
	
	public void send(String msg) {
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = msg.getBytes("UTF-8");
					out.write(buffer);
					out.flush();
				} catch (Exception e) {
					try {
						System.out.println("[메세지 수신 오류] "
								+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName());
						TCP.clients.remove(Receiver.this);
						socket.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		};
		TCP.threadpool.submit(thread);
	}
}
