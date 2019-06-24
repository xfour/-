package com.xfour.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;

/*
 * 处理图片的相关工具类
 */
public class ImageUtil {
	
	//将任意图片格式转化为jpg格式
	public static BufferedImage change2jpg(File file) {
		try {
			Image image =Toolkit.getDefaultToolkit().createImage(file.getAbsolutePath());
			PixelGrabber pg = new PixelGrabber(image,0,0,-1,-1,true);
			pg.grabPixels();//请求图像或图像生成器开始传递像素，并等待传递感兴趣矩形中的所有像素。
            int width = pg.getWidth();
            int	height = pg.getHeight();
            final int[] RGB_MASKS = { 0xFF0000, 0xFF00, 0xFF };
            final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
            DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), pg.getWidth() * pg.getHeight());
            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
            BufferedImage img = new BufferedImage(RGB_OPAQUE, raster, false, null);
            return img;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//用于改变图片分辨率
	public static Image resizeImage(Image srcImage,int width,int height) {
		try {
			BufferedImage bufImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			bufImg.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
			return bufImg;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
