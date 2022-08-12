package jserver.img.providers;

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.util.Random;

import jserver.*;
public class GenericImageProvider implements ImageProvider {

	private Component c;
	private Random ran;
	public GenericImageProvider() {
		c = new Container();
		ran = new Random();
	}
	@Override
	public Image getImage(int w, int h) {
		Image img;
		int rval = ran.nextInt(1);
		if (rval==0) {
		int i = 0;
		int pixels[] = new int[w * h];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x<w; x++) {
				int r = (x^y)&0xff;
				int g = (x*2^y*2)&0xff;
				int b = (x*4^y*4)&0xff;
				pixels[i++] = (255 << 24) | (r << 16) | (g << 8) | b;
			}
			
		}
		
		img = c.createImage(new MemoryImageSource(w, h, pixels, 0, w));
		return img;
		} else if (rval == 1) {
				int i = 0;
				
				int pixels[] = new int[w * h];
				
				int a = -2147483648;
				for (int y = 0; y < h; y++) {
				
					for (int x = 0; x<w; x++) {
						int r;
						int g;
						int b;
						
					
						r = ((x^y) + a)&0xff;
						g = ((x*2^y*2) + a)&0xff;
						b = ((x*4^y*4) + a)&0xff;
						a++;
					
						pixels[i++] = (255 << 24) | (r << 16) | (g << 8) | b;
					}
					img = c.createImage(new MemoryImageSource(w, h, pixels, 0, w));
					return img;
			}
		}
		int i = 0;
		int pixels[] = new int[w * h];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x<w; x++) {
				int r = (x^y)&0xff;
				int g = (x*2^y*2)&0xff;
				int b = (x*4^y*4)&0xff;
				pixels[i++] = (255 << 24) | (r << 16) | (g << 8) | b;
			}
			
		}
		
		img = c.createImage(new MemoryImageSource(w, h, pixels, 0, w));
		return img;
	}
	
}
