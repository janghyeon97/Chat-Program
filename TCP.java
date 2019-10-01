package ConsoleChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TCP {
	static final int SERVER_PORT = 2000;	
	public static ExecutorService threadpool;
	public static Vector<Receiver> clients = new Vector<Receiver>();
	ServerSocket serverSocket = null;
	
	public TCP() {}
	
	public void startServer() {
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("127.0.0.1", SERVER_PORT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Socket socket = serverSocket.accept();
						clients.add(new Receiver(socket));
						System.out.println("[클라이언트 접속] "+socket.getRemoteSocketAddress()+": "
								+Thread.currentThread().getName());
					} catch (Exception e) {
						if(!serverSocket.isClosed()) stopServer();
						break;
					}
				}				
			}			
		};
		threadpool = Executors.newCachedThreadPool();
		threadpool.submit(thread);
	}
	
	public void stopServer() {
		try {
			Iterator<Receiver> iterator = clients.iterator();
			while(iterator.hasNext()) {
				Receiver client = iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if(serverSocket != null && !serverSocket.isClosed()) serverSocket.close();
			if(threadpool != null && !threadpool.isShutdown()) threadpool.shutdown();		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TCP server = new TCP();
		server.startServer();
	}
}
