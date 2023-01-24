package com.basicOperation;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.functional.CopyImage;

public class ContrastSet {
	CopyImage copyImage = new CopyImage();

	public BufferedImage Set(BufferedImage image, int contrast) {
		int R = 0, G = 0, B = 0;
		Color color, color2;
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		double c = contrast;
		double f = (259 * (c + 255)) / (255 * (259 - c));
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				color = new Color(image.getRGB(x, y));
				R = color.getRed();
				G = color.getGreen();
				B = color.getBlue();
				R = (int) ((f * (R - 128)) + 128);
				G = (int) ((f * (G - 128)) + 128);
				B = (int) ((f * (B - 128)) + 128);
				if (R > 255) {
					R = 255;
				}
				if (G > 255) {
					G = 255;
				}
				if (B > 255) {
					B = 255;
				}
				if (R < 0) {
					R = 0;
				}
				if (G < 0) {
					G = 0;
				}
				if (B < 0) {
					B = 0;
				}
				color2 = new Color(R, G, B);
				image2.setRGB(x, y, color2.getRGB());
			}
		}
		return image2;
	}
}
