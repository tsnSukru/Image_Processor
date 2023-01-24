package com.morphological;

import java.awt.image.BufferedImage;

import com.functional.CopyImage;

public class ClosingMorph {
	CopyImage copyImage = new CopyImage();

	public BufferedImage whiteClosing(BufferedImage image) {
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		image2 = copyImage.copy(image);

		Dilation dilation = new Dilation();
		image2 = dilation.whiteDilation(image);

		Erosion erosion = new Erosion();
		image2 = erosion.whiteErosion(copyImage.copy(image2));

		return image2;
	}

	public BufferedImage blackClosing(BufferedImage image) {
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		image2 = copyImage.copy(image);

		Dilation dilation = new Dilation();
		image2 = dilation.blackDilation(image);

		Erosion erosion = new Erosion();
		image2 = erosion.blackErosion(copyImage.copy(image2));

		return image2;
	}
}
