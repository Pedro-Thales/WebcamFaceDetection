package com.pedro.webcam.opencv;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Detector {

	private CascadeClassifier cascadeClassifier;
	private MatOfRect detectedFaces;
	private Mat colorImage;
	private Mat greyImage;

	public Detector() {
		detectedFaces = new MatOfRect();
		colorImage = new Mat();
		greyImage = new Mat();
		cascadeClassifier = new CascadeClassifier("cascade_frontalface_alt.xml");
	}

	public Mat detect(Mat frame) {

		frame.copyTo(colorImage);
		frame.copyTo(greyImage);

		// converts to color for grey
		Imgproc.cvtColor(colorImage, greyImage, Imgproc.COLOR_BGR2GRAY);
		Imgproc.equalizeHist(greyImage, greyImage);

		//cascadeClassifier.detectMultiScale(greyImage, detectedFaces, 1.05, 4, 10, new Size(20,20), new Size(500,500));
		cascadeClassifier.detectMultiScale(greyImage, detectedFaces);
		showFaces();
		
		return colorImage;

	}

	private void showFaces() {
		for (Rect rect : detectedFaces.toArray()) {
			Imgproc.rectangle(colorImage, new Point(rect.x, rect.y),
					new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(250, 100, 100), 2);
		}
	}

}
