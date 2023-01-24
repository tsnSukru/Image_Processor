package com.morphological;

import java.awt.image.BufferedImage;

import com.functional.CopyImage;

public class OpeningMorph {
	CopyImage copyImage = new CopyImage();

	public BufferedImage whiteOpening(BufferedImage image) {
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		image2 = copyImage.copy(image);

		Erosion erosion = new Erosion();
		image2 = erosion.whiteErosion(image);

		Dilation dilation = new Dilation();
		image2 = dilation.whiteDilation(copyImage.copy(image2));

		return image2;
	}

	public BufferedImage blackOpening(BufferedImage image) {
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		image2 = copyImage.copy(image);

		Erosion erosion = new Erosion();
		image2 = erosion.blackErosion(image);

		Dilation dilation = new Dilation();
		image2 = dilation.blackDilation(copyImage.copy(image2));

		return image2;
	}
}
