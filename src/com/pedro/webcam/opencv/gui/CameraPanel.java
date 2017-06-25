package com.pedro.webcam.opencv.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.JPanel;

import org.opencv.core.Mat;

public class CameraPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	public CameraPanel(){}
	
	public boolean convertMatToImage(Mat mat){
		
		int width = mat.width();
		int height = mat.height();
		int channels = mat.channels();
		byte[] sourcePixels = new byte[width * height * channels];
		mat.get(0,0, sourcePixels);
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		final byte[] targetPixels = ( (DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
		
		return true;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(image == null){
			return;
		}
		
		g.drawImage(image, 10, 10, image.getWidth(), image.getHeight(), null);
		
		
	}

}
