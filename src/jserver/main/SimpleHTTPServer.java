package jserver.main;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import console.TextAreaOutputStream;
import jserver.ImageProvider;
import jserver.REF;
import jserver.Server;
import jserver.Servlet404;
import jserver.img.providers.GenericImageProvider;
public class SimpleHTTPServer {
	private static String pathpp;
    public static int sc = 0;
    static Component c;
    static int sc2 = 0;
    static ImageProvider provider;
    static Servlet404 servlet404 = new Servlet404();
    public static PrintStream temp;
    public static REF<PrintStream> reftotemp;
	public static void main(String args[] ) throws Throwable {
			provider = new GenericImageProvider();
		    c = new Container();
		    JFrame frame = new JFrame("JServer Servlet And Python Server");
		    frame.setLayout(new FlowLayout());
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    String text = "";
		    
		    JTextArea textArea = new JTextArea(text, 50, 150);
		    textArea.setPreferredSize(new Dimension(100, 100));
		    JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		    temp = new PrintStream(new TextAreaOutputStream(textArea, 25));
		    textArea.setLineWrap(true);
		    frame.add(scrollPane);
		    Image icon = Toolkit.getDefaultToolkit().getImage("C:/xampp/tomcat/webapps/java/JServer/assets/server.png");  
		    frame.setIconImage(icon);  
		    frame.pack();
		    frame.setVisible(true);
		System.setOut(temp);
		System.setErr(temp);
		reftotemp = new REF<PrintStream>(temp);
		System.setOut(reftotemp.thisobj.thisobj.obj);
		System.setErr(reftotemp.thisobj.obj);
		
		
		
		
		
		
		
		
		
		
        ServerSocket server = new ServerSocket(9000);
        System.out.println("Listening for connection on port 9000 ....");
        Server s = Server.NewServer();
        //All this stuff with arrays is for performance only
        RequestHandler handler = new RequestHandler(server, s);
        Thread t = new Thread(handler);
        Thread client = new Thread(() -> SimpleWebBrowser.main(args));
        client.start();
        t.start();
        String str = "";
        Scanner scan = new Scanner(System.in);
		while (!str.equalsIgnoreCase("yes")) {
			if (scan.hasNext("[A-Za-z][A-Za-z][A-Za-z][A-Za-z]*")) str = scan.next();
		}
		handler.run = false;
		t.join();
		Thread.sleep(1000);
		scan.close();
        server.close();
    }
    
	static private class RequestHandler implements Runnable {
    	Server s;
		ServerSocket server;
		boolean run = true;
		RequestHandler(ServerSocket ss, Server s) {
    		server = ss;
    		this.s = s;
    	}

		@Override
		public void run() {
			while (run) {
			
			try {
				serv(server, s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
					
			}
			
		}
		
		
    }
	
    private static void serv(ServerSocket server, Server s) throws IOException {
    	
        Socket clientSocket = server.accept();
        InputStream in = clientSocket.getInputStream();
		InputStreamReader isr 
      =  new InputStreamReader(in );
        BufferedReader reader = new BufferedReader(isr);
        String line = reader.readLine();
        String path = null;
        boolean start = true;
        
        System.setOut(temp);
		System.setErr(temp);
        ArrayList<String> headers = new ArrayList<>();
        headers.add(line);
        boolean re = true;
		while (re ) {
        	if (start) {
        		int pathlen = line.length() - 12;
        		char[] arr = new char[pathlen];
        		line.getChars(4, pathlen + 4, arr, 0);
        		path = new String(arr);
        		start = false;
        		System.out.println("Path: " + path);
        		pathpp = path;
        	}
            System.out.println(line);
            line = reader.readLine();
            if (line.equals("")) {
            	break;
            }
            headers.add(line);
           if (line == null) break;
           re = !(line == null);
           if (re) {
        	   re = !line.isEmpty();
           }
        }
        String[] ar = new String[headers.size()];
        headers.toArray(ar);
        OutputStream out = clientSocket.getOutputStream();
        System.out.println(pathpp);
        System.out.println("Start");
        if (pathpp.equals("/favicon.ico "))
        {
        	PrintStream pout = new PrintStream(out);
            
        	pout.print("HTTP/1.1 200 OK\n");
            pout.print("Content-Type: image/png; charset=utf-8\n");
            pout.print("Date: Tue, 25 Oct 2016 08:17:59 GMT\n");
            pout.print("\n");
        	try {
        		int width = 250;
                int height = 250;
         
                // Constructs a BufferedImage of one of the predefined image types.
                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
         
                // Create a graphics which can be used to draw into the buffered image
                Graphics2D g2d = bufferedImage.createGraphics();
         
                
        		
        		
        		
                Image img;
                img = provider.getImage(width, height);
				g2d.drawImage(img, 0, 0, null);
         
                // Disposes of this graphics context and releases any system resources that it is using. 
                g2d.dispose();
         
                // Save as PNG
                
                ImageIO.write(bufferedImage, "png", pout);
                
        	} catch (Throwable t) {
        		t.printStackTrace(System.out);
        		StackTraceElement[] stack = t.getCause().getStackTrace();
        		Throwable e = t.getCause();
				System.out.println(e);
				for (StackTraceElement el : stack) {
					System.out.println(el);
				}
				
        	}
            
            
            
        } else {
		s.serv(in, out , pathpp, servlet404, ar);}
		in.close();
		out.close();
		clientSocket.close();
		sc2 = sc2 + 1;
		sc = sc2 / 2;
		
    }
}