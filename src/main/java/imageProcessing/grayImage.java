package imageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class grayImage {

	//普通方法图片灰度化技术
    public static void main(String[] args) throws IOException {
		
    	
    	 File file = new File("E:/img/38.png");
    	    BufferedImage image = ImageIO.read(file);

    	    int width = image.getWidth();  
    	    int height = image.getHeight();  

    	    BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY); 
    	    for(int i= 0 ; i < width ; i++){  
    	        for(int j = 0 ; j < height; j++){  
    	        int rgb = image.getRGB(i, j);  
    	        grayImage.setRGB(i, j, rgb);  
    	        }  
    	    }  

    	    File newFile = new File("E:/img/38putong.png");  
    	    ImageIO.write(grayImage, "png", newFile);  
    	
    	
	}


}
