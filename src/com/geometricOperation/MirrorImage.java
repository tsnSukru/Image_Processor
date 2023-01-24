package com.geometricOperation;

import java.awt.image.BufferedImage;

import com.functional.CopyImage;

public class MirrorImage {
	CopyImage copyImage = new CopyImage();

	public BufferedImage Mirror(BufferedImage image) {
		// TODO Auto-generated method stub
		BufferedImage Image2 = new BufferedImage(image.getWidth(), image.getWidth(), BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				Image2.setRGB(x, y, image.getRGB(image.getWidth() - x - 1, y));
			}
		}

		return Image2;
	}
}
