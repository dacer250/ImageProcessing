package imageProcessing;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Expand {

	
	public static void main(String[] args) throws IOException {
		
		BufferedImage image = ImageIO.read(new File("E:/img/segment.jpg"));
		
		//BufferedImage image1 = getPicEdge(image);
        ImageIO.write(image, "jpg", new File("E:/img/segment(bianyuan).jpg"));
        
	}
	
	
	
	//图像边缘化
	public static BufferedImage getPicEdge(BufferedImage originalPic) {  
        int imageWidth = originalPic.getWidth();  
        int imageHeight = originalPic.getHeight();  
  
        BufferedImage newPic = new BufferedImage(imageWidth, imageHeight,  
                BufferedImage.TYPE_3BYTE_BGR);  
  
        float[] elements = { 0.0f, -1.0f, 0.0f, -1.0f, 4.0f, -1.0f, 0.0f,  
                -1.0f, 0.0f };  
  
        // AffineTransform at = new AffineTransform();  
        Kernel kernel = new Kernel(3, 3, elements);  
        ConvolveOp cop = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);  
        cop.filter(originalPic, newPic);  
        return newPic;  
    }  
	
	
	
	
	
}
