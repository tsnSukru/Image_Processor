package com.functional;

import java.awt.image.BufferedImage;

public class CopyImage {
	public BufferedImage copy(BufferedImage image) {
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				image2.setRGB(i, j, image.getRGB(i, j));
			}
		}
		return image2;
	}

}
