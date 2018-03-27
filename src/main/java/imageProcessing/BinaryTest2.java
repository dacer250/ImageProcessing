package imageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BinaryTest2 {

	
	//二值化  种子选手
	public static void main(String[] args) throws IOException {
		
		BufferedImage image = ImageIO.read(new File("E:/img/34bianyuan.jpg"));
		int w = image.getWidth();  
        int h = image.getHeight();
        int rgb= image.getRGB(0, 0);
        int[][] gray=new int[w][h];
        
        for (int x = 0; x < w; x++) {  
            for (int y = 0; y < h; y++) {  
                gray[x][y]=getGray(image.getRGB(x, y));  
            }  
        }  
        
        long startTime = System.currentTimeMillis();
        //int SW = segment(gray, w, h);    //一维最大熵分割算法
        //int SW = segment2(gray, w, h);   
        //int SW = bestThresh(gray, w, h);
        int SW = otsuThresh(gray, w, h);
        System.out.println(SW);
        
        for (int x = 0; x < w; x++) {  
            for (int y = 0; y < h; y++) {  
                if (gray[x][y] <= SW) {  
                    int max = new Color(0, 0, 0).getRGB();  
                    image.setRGB(x, y, max);  
                }else{  
                    int min = new Color(255, 255, 255).getRGB();  
                    image.setRGB(x, y, min);  
                }  
            }             
        }  
          
        long endTime=System.currentTimeMillis();
        float excTime=(float)(endTime-startTime)/1000;
        System.out.println("执行时间："+excTime+"s");
        ImageIO.write(image, "jpg", new File("E:/img/34otsuThresh.jpg"));
        
        
		
		
	}
	
    //调用方法	
	public static int getGray(int rgb){  
        String str=Integer.toHexString(rgb);  
        int r=Integer.parseInt(str.substring(2,4),16);  
        int g=Integer.parseInt(str.substring(4,6),16);  
        int b=Integer.parseInt(str.substring(6,8),16);  
        //or 直接new个color对象  
        Color c=new Color(rgb);  
        r=c.getRed();  
            g=c.getGreen();  
        b=c.getBlue();  
        int top=(r+g+b)/3;  
        return (int)(top);  
    }  
	
	
	
    //一维最大熵分割算法
	public static int segment(int[][] im, int w, int h)  
	{  
	    int i, j, t;  
	    double a1, a2, max, pt;  
	    double[] p = new double[256];  
	    double[] num = new double[256];       
	         
	    /*int[][] im = new int[w][h];*/  
	      
	    /*for(j = 0; j < h; j++)  
	        for(i = 0; i < w; i++)             
	            im[i][j] = pix[i+j*w]&0xff;  */
	      
	    for (i = 0; i < 256; i++)  
	        p[i] = 0;  
	      
	    //统计各灰度级出现的次数  
	    for (j = 0; j < h; j++)  
	        for (i = 0; i < w; i++)              
	            p[im[i][j]]++;  
	      
	    /** 
	     *关于计算各灰度级出现的概率 
	     *1.因为(p[j]/(w*h)) / (pt/(w*h)) = p[j] / pt 
	     *  所以计算p[j] / pt不必计算概率 
	     *2.当p[j]=0时，计算Math.log(p[j] / pt)将出现无穷大.但 
	     *  此时p[j] / pt) * Math.log(p[j] / pt)=0 
	     *  所以在计算a1时,不必计算这一项        
	     */  
	    int hw =  h*w;                 
	    for (i = 0; i < 256; i++)  
	    {  
	        a1 = a2 = 0.0;  
	        pt = 0.0;  
	        for (j = 0; j <= i; j++)  
	            pt += p[j];  
	          
	        for (j = 0; j <= i; j++)  
	          
	            if(p[j]>0)  
	                a1 += (p[j]/pt) * Math.log(pt/p[j]);  
	                      
	        for (j = i+1; j <256; j++)  
	            if(p[j]>0)  
	                a2 += (p[j] /(hw-pt))* Math.log((hw - pt)/p[j]);  
	          
	        num[i] = a1 + a2;  
	    }  
	      
	    max = 0.0; t = 0;  
	    for (i = 0; i < 256; i++)  
	    {  
	        if (max < num[i])  
	        {  
	            max = num[i];  
	            t = i;  
	        }  
	    }          
	    return t;  
	}
	
	
	
	//二维最大熵分割算法, 使用递推算法 
	public static int segment2(int[][] im, int w, int h)  
	{  
	    int i, j, u, v, t;  
	       double a1, a2, max, pa, pb, pa2, pb2, sum;  
	       double[][] p = new double[256][256];  
	       double[][] num = new double[256][256];  
	      
	    /*int[][] im = new int[w][h];*/  
	      
	    /*for(j = 0; j < h; j++)  
	        for(i = 0; i < w; i++)             
	            im[i][j] = pix[i+j*w]&0xff;  */
	              
	       for(i = 0; i < 256; i++)  
	           for(j = 0; j < 256; j++)  
	               p[i][j] = 0;  
	         
	       //统计2维直方图p[i][j]  
	       for(j = 1; j < h-1; j++)  
	       {  
	           for(i = 1; i < w-1; i++)  
	           {  
	            t = (int)((im[i-1][j]+im[i+1][j]+im[i][j-1]  
	              +im[i][j+1]+im[i][j])/5);//4-邻域均值  
	               p[im[i][j]][t]++;  
	           }  
	       }  
	         
	       pa = 0.0; pb = 0.0; max = 0.0; t = 0;  
	       for(i = 49; i < 200; i=i+2)  
	       {     
	           //System.out.println((int)(i*100/199)+" %");  
	           for(j = 0; j < 256; j++)  
	        {  
	            a1 = 0.0; a2 = 0.0;                   
	            pb = 0.0;  
	              
	            //递推算法计算pa  
	            if(j != 0)  
	            {  
	                for(u = 0; u <= i; u++)   
	                    pa += p[u][j];  
	            }  
	            else  
	            {     
	                pa = 0.0;  
	                for( u = 0; u <= i; u++)  
	                    pa += p[u][0];  
	            }  
	                              
	            //递推算法计算pb  
	            if(j != 0)          
	            {  
	                for(u = i+1;u < 256;u++)  
	                    pb -= p[u][j];  
	            }  
	            else  
	            {  
	                pb = 0;  
	                for(u = i+1;u < 256;u++)  
	                    for(v = j+1; v < 256; v++)  
	                        pb += p[u][v];  
	            }  
	             
	            for(u = 0; u <= i; u++)  
	                for(v = 0; v <= j; v++)  
	                    if(p[u][v] > 0)  
	                        a1 += (double)(-p[u][v]/pa)* Math.log(p[u][v]/pa);  
	             
	            for(u = i+1; u < 256; u++)  
	                for(v = j+1; v < 256; v++)  
	                    if(p[u][v] > 0)  
	                        a2 += (double)(-p[u][v]/pb)* Math.log(p[u][v]/pb);    
	             
	            num[i][j] = a1 + a2;                              
	           }  
	       }  
	         
	       max = 0.0; t = 0;  
	       for (i = 0; i < 256; i++)  
	       {  
	        for(j = 0; j < 256; j++)  
	        {  
	               if (max < num[i][j])  
	               {  
	                   max = num[i][j];  
	                   t = i;   
	               }  
	           }  
	       }      
	       return t;  
	}  
	
	
	
	//最佳阈值分割
	public static int bestThresh(int[][] im, int w, int h)  
	{  
	    int i, j, t,  
	        thresh,   
	        newthresh,  
	        gmax, gmin;         //最大,最小灰度值  
	       double a1, a2, max, pt;  
	       double[] p = new double[256];  
	       long[] num = new long[256];  
	  
	    /*int[][] im = new int[w][h];*/  
	      
	    /*for(j = 0; j < h; j++)  
	        for(i = 0; i < w; i++)             
	            im[i][j] = pix[i+j*w]&0xff;*/  
	              
	       for (i = 0; i < 256; i++)  
	           p[i] = 0;  
	         
	       //1.统计各灰度级出现的次数、灰度最大和最小值  
	       gmax = 0;  
	       gmin =255;  
	       for (j = 0; j < h; j++)  
	       {  
	           for (i = 0; i < w; i++)  
	           {  
	            int g = im[i][j];  
	               p[g]++;  
	               if(g > gmax) gmax = g;  
	               if(g < gmin) gmin = g;  
	           }  
	       }  
	         
	       thresh = 0;  
	       newthresh = (gmax+gmin)/2;  
	         
	       int meangray1,meangray2;  
	       long p1, p2, s1, s2;  
	       for(i = 0; (thresh!=newthresh)&&(i<100);i++)  
	       {  
	        thresh = newthresh;  
	        p1 = 0; p2 = 0; s1 = 0; s2 = 0;  
	          
	        //2. 求两个区域的灰度平均值  
	        for(j = gmin; j < thresh;j++)  
	        {  
	            p1 += p[j]*j;  
	            s1 += p[j];               
	        }  
	        meangray1 = (int)(p1/s1);  
	          
	        for(j = thresh+1; j < gmax; j++)  
	        {  
	            p2 += p[j]*j;  
	            s2 += p[j];               
	        }  
	        meangray2 = (int)(p2/s2);  
	        //3. 计算新阈值  
	        newthresh = (meangray1+meangray2)/2;      
	       }  
	       return newthresh;  
	}  
	
	
	
	//Otsu阈值分割
	public static int otsuThresh(int[][] inIm, int iw, int ih)  
	{  
	    ColorModel cm = ColorModel.getRGBdefault();  
	       int wh = iw * ih;  
	       /*int[][] inIm = new int[iw][ih];*/   
	  
	       int i, j, t;  
	       int L = 256;  
	       double[] p = new double[L];  
	                         
	       /*for (j = 0; j < ih; j++)  
	           for (i = 0; i < iw; i++)  
	               inIm[i][j] = pix[i+j*iw]&0xff; */                
	  
	       for (i = 0; i < L; i++)  
	           p[i] = 0;  
	  
	       //计算各灰度出现次数  
	       for (j = 0; j < ih; j++)  
	           for (i = 0; i < iw; i++)  
	               p[inIm[i][j]]++;  
	  
	       //计算各灰度级出现概率  
	       for (int m = 0; m < L; m++)  
	           p[m] = p[m] / wh;  
	  
	       double[] sigma = new double[L];  
	       for (t = 0; t < L; t++)  
	       {  
	           double w0 = 0;  
	           for (int m = 0; m < t+1; m++)  
	               w0 += p[m];  
	           double w1 = 1 - w0;  
	  
	           double u0 = 0;  
	           for (int m = 0; m < t + 1; m++)  
	               u0 += m * p[m] / w0;  
	  
	           double u1 = 0;  
	           for (int m = t; m < L; m++)  
	               u1 += m * p[m] / w1;  
	  
	           sigma[t] = w0*w1*(u0-u1)*(u0-u1);  
	       }  
	       double max = 0.0;  
	       int T = 0;  
	       for (i = 0; i < L-1; i++)  
	       {  
	           if (max < sigma[i])  
	           {  
	               max = sigma[i];  
	               T = i;  
	           }  
	       }          
	       return T;                  
	}  
		
		
}  