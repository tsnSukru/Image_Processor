package com.basicOperation;

import java.awt.image.BufferedImage;

public class Thresholding {
	public BufferedImage convert(BufferedImage image, Object border) {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				String pixel = Integer.toBinaryString(image.getRGB(i, j));
				String alpha = pixel.substring(0, 8);
				int red = Integer.parseInt(pixel.substring(8, 16), 2);
				int green = Integer.parseInt(pixel.substring(16, 24), 2);
				int blue = Integer.parseInt(pixel.substring(24, 32), 2);
				int average = (red + green + blue) / 3;// rengi siyah beyaza cevirdik
				if (average < (int) border) {
					average = 0;
				} else {
					average = 255;
				}
				String avg = Integer.toBinaryString(average);
				while (avg.length() < 8) {// eğer 8 bitten azsa sol taraftan 0 ekle
					avg = "0" + avg;
				}
				String pixel2 = alpha + avg + avg + avg;
				image.setRGB(i, j, Integer.parseUnsignedInt(pixel2, 2));
			}
		}
		return image;
	}
}
