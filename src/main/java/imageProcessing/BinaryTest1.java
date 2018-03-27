package imageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BinaryTest1 {

	
	public static void main(String[] args) throws IOException {
		
		BufferedImage image = ImageIO.read(new File("E:/img/38gray.jpg"));  
        int w = image.getWidth();      //获取图片宽度
        int h = image.getHeight();     //获取图片高度
        float[] rgb = new float[3];  
        double[][] zuobiao = new double[w][h];  //初始化坐标
        int R = 0;  
        float red = 0;  
        float green = 0;  
        float blue = 0;  
        BufferedImage bi= new BufferedImage(w, h,  
                BufferedImage.TYPE_BYTE_BINARY);;  	
         
        //遍历这个图像        
        for (int x = 0; x < w; x++) {  
            for (int y = 0; y < h; y++) {  
                int pixel = image.getRGB(x, y);   
                rgb[0] = (pixel & 0xff0000) >> 16;  
                rgb[1] = (pixel & 0xff00) >> 8;  
                rgb[2] = (pixel & 0xff);  
                red += rgb[0];  
                green += rgb[1];  
                blue += rgb[2];  
                R = (x+1) *(y+1);  
                float avg = (rgb[0]+rgb[1]+rgb[2])/3;  //取平均值作为新像素，太low了嘛
                zuobiao[x][y] = avg;      
                  
            }  
        }  
        
        
        //阈值，自己调的话还可以，现在想算出来
        double SW = 145;  
        for (int x = 0; x < w; x++) {  
            for (int y = 0; y < h; y++) {  
                if (zuobiao[x][y] <= SW) {  
                    int max = new Color(0, 0, 0).getRGB();  
                    bi.setRGB(x, y, max);  
                }else{  
                    int min = new Color(255, 255, 255).getRGB();  
                    bi.setRGB(x, y, min);  
                }  
            }             
        }  
          
        ImageIO.write(bi, "jpg", new File("E:/img/38binary1.jpg"));  	
		
		
		
		
		
	}
	
	
}
