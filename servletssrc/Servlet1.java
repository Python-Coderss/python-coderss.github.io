import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import jserver.Config;
import jserver.Servlet;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class Servlet1 implements Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4613939274415821232L;
	private PyObject obj;
	private PythonInterpreter i;
	public Servlet1() {
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
		return url.startsWith("/python");
	}

	@Override
	public void service(InputStream in, OutputStream o, String path, String[] headers) {
		i = new PythonInterpreter();
		i.exec("x = ''\n");
		while (true) {
		if (path.equals("/python ")) {
			i.exec("x = x + \"<html><head><title>Python Test</title></head><body><h1>Root of pyservlets</h1></body></html>\"");
			break;
		} else {
			String npath = path.trim();
			npath = npath.substring(7);
			String fp = "C:/xampp/tomcat/webapps/java/JServer/pyservlets" + npath;
			FileInputStream r = null;
			try {
				r = new FileInputStream(fp);
			} catch (FileNotFoundException e) {
				i.exec("x = x + \"<html><head><title>404 "+npath+"</title></head><body><h1>404 "+npath+"</h1></body></html>\"");
				break;
			}
			i.execfile(in);
			try {
				r.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		}
		obj = i.get("x");
		String outs = (String) obj.__tojava__(String.class);
		PrintStream out = new PrintStream(o);
		out.println(outs);
		

	}

	@Override
	public String GetName() {
		// TODO Auto-generated method stub
		return "Python Sript Manager";
	}

}
