package com.expense.tools;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectTextRequest;
import com.amazonaws.services.rekognition.model.DetectTextResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.TextDetection;
import com.amazonaws.util.IOUtils;
import com.expense.DetectTextWithAWS;

public class AwsTools {
	

	public void askText(AmazonRekognition rekognitionClient, String photo1) throws IOException {
		Image source = getImageUtil(photo1);
		DetectTextResult result = callDetectText(source, rekognitionClient);

		try {
			List<TextDetection> textDetections = result.getTextDetections();

			for (TextDetection text : textDetections) {

				System.out.println("Detected: " + text.getDetectedText());
				System.out.println("Confidence: " + text.getConfidence().toString());
				System.out.println("Id : " + text.getId());
				System.out.println("Parent Id: " + text.getParentId());
				System.out.println("Type: " + text.getType());
				System.out.println();
			}
		} catch (AmazonRekognitionException e) {
			e.printStackTrace();
		}
	}

	public DetectTextResult callDetectText(Image sourceImage, AmazonRekognition amazonRekognitionClient) {
		DetectTextRequest request = new DetectTextRequest().withImage(sourceImage);
		DetectTextResult result = null;
		try {
			result = amazonRekognitionClient.detectText(request);

		} catch (AmazonRekognitionException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Image getImageUtil(String key) throws IOException {
		ByteBuffer imageBytes;
		ClassLoader classLoader = new DetectTextWithAWS().getClass().getClassLoader();
		try (InputStream inputStream = classLoader.getResourceAsStream(key)) {
			imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
		}
		return new Image().withBytes(imageBytes);
	}

}
