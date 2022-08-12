package jserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Server {
	
	
	
	private Server() {
		
		
		
	}
	public static Server NewServer() {
		Server self = new Server();
		
		return self.init();
	}
	private Server init() {
		
		return this;
		
	}
	public void serv(InputStream in, OutputStream out, String pa, Servlet servlet404, String[] arr) {
		ConnectionHandler g = new ConnectionHandler(in, out, pa, arr);
		g.run(servlet404);
	}
	class ConnectionHandler {
		InputStream in;
		OutputStream out;
		String path;
		String[] headers;
		public ConnectionHandler(InputStream in, OutputStream out, String pa, String[] arr) {
			System.out.println("New ConHan");
			this.out = out;
			this.in = in;
			path = pa;
			headers = arr;
		}
		public void run(Servlet servlet404) {
			ServletInvoker serv = null;
			try {
				serv = new ServletInvoker(in, out, headers);
			} catch (IOException e) {
				
				e.printStackTrace();
				return;
			}
			System.out.println("Run Servlet S");
			if (servlet404.serv(path))
			serv.serv(servlet404, path);
			
			
		}
		
	}
}
