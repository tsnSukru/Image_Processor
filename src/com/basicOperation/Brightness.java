package com.basicOperation;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Brightness {
	public BufferedImage changeBrightness(BufferedImage image, Object alpha) {
		int R = 0, G = 0, B = 0;
		Color color, color2;
		BufferedImage image2;
		image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		int i = 0, j = 0;
		for (int x = 0; x < image.getWidth(); x++) {
			j = 0;
			for (int y = 0; y < image.getHeight(); y++) {
				color = new Color(image.getRGB(x, y));
				R = color.getRed() + (Integer) alpha;
				G = color.getGreen() + (Integer) alpha;
				B = color.getBlue() + (Integer) alpha;
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
				image2.setRGB(i, j, color2.getRGB());
				j++;
			}
			i++;
		}
		return image2;
	}
}
