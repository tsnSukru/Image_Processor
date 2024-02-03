package com.operation.utility;

import java.awt.image.BufferedImage;

public class SplitIntoPixel {
	int[][] pixels = null;

	public int[][] split(BufferedImage img) {
		pixels = new int[img.getWidth()][img.getHeight()];
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				pixels[i][j] = img.getRGB(i, j);
			}
		}
		return pixels;
	}
}
