package com.pedro.webcam.opencv.gui;

import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import com.pedro.webcam.opencv.Detector;

public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;	
	private Detector detector;
	private CameraPanel cameraPanel;
	
	public MainFrame(){
		super("WebCam Face Detection");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		detector = new Detector();
		cameraPanel = new CameraPanel();
		
		setContentPane(cameraPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setVisible(true);
		
	}
	
	public void displayScreen(){
		Mat webcamImage = new Mat();
		VideoCapture videoCapture = new VideoCapture(0);
		
		if(videoCapture.isOpened()){
			while(true){
				videoCapture.read(webcamImage);
				if(!webcamImage.empty()){
					setSize(webcamImage.width() + 50, webcamImage.height() + 70);
					webcamImage = detector.detect(webcamImage);
					cameraPanel.convertMatToImage(webcamImage);
					cameraPanel.repaint();
				}else{
					System.out.println("Error: Not possible to get Image from the webcam");
					break;
				}
			
			}
		}
	}
	
	
}
