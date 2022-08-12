package jserver.main;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import jserver.client.WebBrowser;
public class ClientMain {
    public static void main (String args []) {
        WebBrowser browser = new WebBrowser();
        browser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        browser.setSize(500,  300);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:/xampp/tomcat/webapps/java/JServer/assets/browser.png");  
	    browser.setIconImage(icon); 
        browser.setVisible(true);
    }
}
