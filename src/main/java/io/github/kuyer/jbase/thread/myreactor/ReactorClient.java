package io.github.kuyer.jbase.thread.myreactor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReactorClient {
	
	private String host;
	private int port;
	
	public ReactorClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void run() throws Exception {
		Socket socket = new Socket(this.host, this.port);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("connect to host: "+this.host+"; port: "+this.port);
		System.out.println("type (\"exit\" to quit)");
		System.out.println("tell what your name is to server ...");
		String line = null;
		while(null != (line=reader.readLine())) {
			line = line.trim().toLowerCase();
			if("".equals(line)) {
				continue;
			}
			out.println(line);
			if("exit".equals(line)) {
				break;
			}
			System.out.println("server: "+in.readLine());
		}
		reader.close();
		out.close();
		in.close();
		socket.close();
	}

	public static void main(String[] args) {
		ReactorClient client = new ReactorClient("127.0.0.1", 9528);
		try {
			client.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
