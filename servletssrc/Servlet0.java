import java.io.*;

import jserver.*;

public class Servlet0 implements Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4655131077907368936L;
	private String version;
	private double servletSpec;

	public Servlet0() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(Config c) {
		version = c.getServerVersion();
		servletSpec = c.servletSpec();
		System.out.println("ServletInit");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean serv(String url) {
		// TODO Auto-generated method stub
		System.out.println("resv: " + url);
		int itemp = url.compareTo("/info ");
		System.out.println("Itemp: " +itemp);
		
		boolean temp = itemp  == 0;
		System.out.println("Returning: " + temp);
		return temp;
		
	}

	@Override
	public void service(InputStream in, OutputStream o, String Path, String[] headers) {
		System.out.println("Servlet Served");
		PrintStream out = new PrintStream(o);
		out.println("Servlet Specifaction Version: " + servletSpec + "<br>Server Version: " + version);
	}

	@Override
	public String GetName() {
		// TODO Auto-generated method stub
		return "Just a servlet";
	}

}
