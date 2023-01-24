package com.geometricOperation;

import java.awt.image.BufferedImage;

import com.functional.CopyImage;

public class ShiftPhoto {
	CopyImage copyImage = new CopyImage();

	public BufferedImage Shift(BufferedImage image, int x, int y) {
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		if (x < 0 & y < 0) {
			for (int i = -x; i < image.getWidth(); i++) {
				for (int j = -y; j < image.getHeight(); j++) {
					image2.setRGB(i, j, image.getRGB(i, j));
				}
			}
		} else if (x < 0 & y > 0) {
			for (int i = -x; i < image.getWidth(); i++) {
				for (int j = 0; j < image.getHeight() - y; j++) {
					image2.setRGB(i, j, image.getRGB(i, j));
				}
			}
		} else if (x > 0 & y < 0) {
			for (int i = 0; i < image.getWidth() - x; i++) {
				for (int j = -y; j < image.getHeight(); j++) {
					image2.setRGB(i, j, image.getRGB(i, j));
				}
			}
		} else if (x > 0 & y > 0) {
			for (int i = 0; i < image.getWidth() - x; i++) {
				for (int j = 0; j < image.getHeight() - y; j++) {
					image2.setRGB(i, j, image.getRGB(i, j));
				}
			}
		}
		return image2;
	}
}
