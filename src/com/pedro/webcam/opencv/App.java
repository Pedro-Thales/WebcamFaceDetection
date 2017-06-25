package com.pedro.webcam.opencv;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.pedro.webcam.opencv.gui.MainFrame;


public class App {

	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}	
		
		MainFrame main = new MainFrame();
		main.displayScreen();

	}

}
