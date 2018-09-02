package com.expense;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.amazonaws.services.rekognition.model.TextDetection;
import com.amazonaws.util.IOUtils;

public class DetectTextWithAWSTest {

	@Test
	public void testExpense3() throws IOException {
		extractText("sampleExpense3.txt");
	}

	@Test
	public void testExpense2() throws IOException {
		extractText("sampleExpense2.txt");
	}

	private void extractText(String file) throws IOException {
		ClassLoader classLoader = new DetectTextWithAWSTest().getClass().getClassLoader();
		try (InputStream inputStream = classLoader.getResourceAsStream(file)) {
			String text = IOUtils.toString(inputStream);
			List<String> items = Arrays.asList(text.split("\\n\\n"));
			List<TextDetection> textDectections = new ArrayList<>();
			String textDetected = "";
			for (String item : items) {
				TextDetection textDetection = new TextDetection();
				List<String> imageDetails = Arrays.asList(item.split("\\n"));
				for (String details : imageDetails) {
					if (details.startsWith("Detected: ")) {
						textDetection.setDetectedText(details.substring("Detected: ".length()));
					} else if (details.startsWith("Confidence: ")) {
						textDetection.setConfidence(new Float(details.substring("Confidence: ".length())));
					} else if (details.startsWith("Id : ")) {
						textDetection.setId(new Integer(details.substring("Id : ".length())));
					} else if (details.startsWith("Parent Id: ")) {
						if (!details.contains("null")) {
							textDetection.setParentId(new Integer(details.substring("Parent Id: ".length())));
						}
					} else if (details.startsWith("Type: ")) {
						textDetection.setType(details.substring("Type: ".length()));
					}

					textDectections.add(textDetection);
				}
				if (textDetection.getType().compareTo("LINE") == 0) {
					textDetected = textDetected.concat(textDetection.getDetectedText() + "\n");
				}
			}
			System.out.println(textDetected);
		}
	}

}
