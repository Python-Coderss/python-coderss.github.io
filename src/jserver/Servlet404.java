package jserver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import jserver.main.SimpleHTTPServer;

public class Servlet404 implements Servlet {
	/**
	 * 
	 */
	Servlet[] servlets;
	static {
		
	}
	private static final long serialVersionUID = -152137468208586526L;
	String v = "";
	double spec = 0;
	public Servlet404() {
		
			Servlet[] temp;
			try {
				temp = getallinternal();
			} catch (MalformedURLException e) {
				throw new Error("This will not happen");
			}
			
			int len = 0;
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] != null) {
					len++;
				}
			}
			servlets = new Servlet[len];
			int j = 0;
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] != null) {
					servlets[j] = temp[i];
					j++;
				}
			}
			
			
		
	}

	@Override
	public void init(Config c) {
		v = c.getServerVersion();
		spec = c.servletSpec();
		

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	private Servlet[] getallinternal() throws MalformedURLException {
		URLClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[] {
			       new URL(
			           "file:///C:/xampp/tomcat/webapps/java/JServer/servlets"
			       )
			});
		System.out.println("Servlet ClassLoader Made");
		Servlet[] servlets = new Servlet[2];
		System.out.println("Servlet Array Made");
		Class<?> clazz;
		System.out.println("clazz declared");
				for ( int i = 0; i < 2; i++){
					System.out.println("Servlet LOOP ITERATION" + i);
					try {
						clazz = urlClassLoader.loadClass("Servlet"+i);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						System.out.println("Class Notfound");
						//e.printStackTrace();
						servlets[i] = null;
						continue;
					}
					System.out.println("Class Found");
					Class<?>[] ints = clazz.getInterfaces();
					boolean isServlet = false;
					for (int j = 0; j < ints.length; j++) {
						System.out.println("Interface: " + ints[j]);
						if (ints[j] == Servlet.class) isServlet = true;
					}
					if (!isServlet) {
						System.out.println("Invalid Servlet Detected");
						servlets[i] = null;
						continue;
					}
					Servlet s;
					try {
						s = (Servlet) clazz.newInstance();
					} catch (java.lang.Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Invalid Servlet Detected");
						servlets[i] = null;
						continue;
					}
					servlets[i] = s;
					System.out.println(s.GetName());
					System.out.println("Success");
				}
			
			return servlets;
	}
	
	@Override
	public void service(InputStream in, OutputStream o, String path, String[] headers) {
		boolean fail = true;
		
		if (fail) {
		
		
		Servlet s = get(path);
		s.init(DefaultConfig.ServerConfig);
		System.out.println("Init Child");
		s.service(in, o, path, headers);
		System.out.println("Served Child");
		
		
		
		
		
		
		System.out.println("Served");
		
		}
		
	}
	private class Simple404 implements Servlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 767841512264253093L;

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
			return true;
		}

		@Override
		public void service(InputStream in, OutputStream o, String path, String[] headers) {
			System.setOut(SimpleHTTPServer.temp);
			System.setErr(SimpleHTTPServer.temp);
			PrintStream out = new PrintStream(o);
			String resp = "<html><head><title>404</title></head><body><p>You requested \"" 
					+ path +
					"\" but that was unavailable so we presented you with this.<br>Got Header: " ;
			for (int i = 0; i < headers.length; i++) {
				resp += headers[i];
			}
			out.print(resp );
			
			resp = "<br>Servlet Spec "
					+ spec 
					+
					"Server Version" + v + "Request Count: "+ SimpleHTTPServer.sc / 2 +"</body><html>";
			
			
			
			
			out.print(resp );
			System.out.println("Served in child servlet 404");
			out.flush();
			
			out.close();
			
			
		}

		@Override
		public String GetName() {
			// TODO Auto-generated method stub
			return "The Kings Child";
		}
		
	}
	private Servlet get(String url) {
		Servlet s = null;
		Servlet res = null;
		
		
		for (int i = 0; i < servlets.length; i++) {
			
				s = servlets[i];
				
				System.out.println("Name: " + s.GetName() + "\nURL: "+url);
				if (s.serv(url)) return s;
				
			
			
		}
			
		if (res == null) return new Simple404();
		else return res;
	}

	

	@Override
	public boolean serv(String url) {
		// TODO Auto-generated method stub
		System.out.println("Serv called with" + url);
		return true;
	}

	@Override
	public String GetName() {
		// TODO Auto-generated method stub
		return "The King";
	}

}
