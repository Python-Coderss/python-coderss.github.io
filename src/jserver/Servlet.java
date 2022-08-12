package jserver;

import java.io.*;


public interface Servlet extends Serializable {
	public void init(Config c);
	public void destroy();
	public boolean serv(String url);
	void service(InputStream in, OutputStream o, String Path, String[] headers);
	public static boolean is404(Servlet s) {
		return s instanceof Servlet404;
	}
	public String GetName();
	
}
