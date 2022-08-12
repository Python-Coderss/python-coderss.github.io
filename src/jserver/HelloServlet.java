package jserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class HelloServlet implements Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6846550070849364817L;

	public HelloServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(Config c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean serv(String url) {
		// TODO Auto-generated method stub
		return url.contains("/hello");
	}

	@Override
	public void service(InputStream in, OutputStream o, String Path, String[] headers) {
		PrintStream out = new PrintStream(o);
		out.println("Hello");

	}

	@Override
	public String GetName() {
		// TODO Auto-generated method stub
		return "Hello? Are you watching";
	}

	

}
