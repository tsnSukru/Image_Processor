package com.geometricOperation;

import java.awt.image.BufferedImage;

import com.functional.CopyImage;

public class ImageReverse {
	CopyImage copyImage = new CopyImage();

	public BufferedImage Reverse(BufferedImage image) {
		// TODO Auto-generated method stub
		BufferedImage Image2 = new BufferedImage(image.getWidth(), image.getWidth(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				Image2.setRGB(x, y, image.getRGB(x, image.getHeight() - y - 1));
			}
		}
		return Image2;
	}
}
