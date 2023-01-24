package com.geometricOperation;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.functional.CopyImage;

public class deneme {
	CopyImage copyImage = new CopyImage();

	public BufferedImage zoom(BufferedImage image) {
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Color color = new Color(0);
		int sumR = 0, sumG = 0, sumB = 0, avgR, avgG, avgB;
		for (int i = 0; i < image.getWidth() - 4; i += 2) {
			for (int j = 0; j < image.getHeight() - 4; j += 2) {
				for (int j2 = i; j2 < i + 4; j2++) {
					for (int k = j; k < j + 4; k++) {
						color = new Color(image.getRGB(j2, k));
						sumR += color.getRed();
						sumG += color.getGreen();
						sumB += color.getBlue();
					}
				}
				avgR = sumR / 16;
				avgG = sumG / 16;
				avgB = sumB / 16;
				for (int a = i * 4; a < image2.getWidth(); a++) {
					for (int b = j * 4; b < image2.getHeight(); b++) {
						color = new Color(avgR, avgG, avgB);
						image2.setRGB(a, b, color.getRGB());
					}
				}
				sumR = 0;
				sumG = 0;
				sumB = 0;
			}
		}
		return image2;
	}
}
