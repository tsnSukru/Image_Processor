package com.lowPassFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class MedianLowPassFilter {
	int[] R = new int[9];
	int[] G = new int[9];
	int[] B = new int[9];
	BufferedImage renderedImage = null;

	public BufferedImage medianFilter(BufferedImage image, BufferedImage image2) {// aynı resim üzerinde değişmiş
																					// değerlerle
		// çalışmamak için ilk resmi işleyip ikinci
		// resimde yerine koydum
		// TODO Auto-generated constructor stub
		for (int i = 2; i < image.getWidth() - 2; i++) {
			for (int j = 2; j < image.getHeight() - 2; j++) {

				Color color = new Color(image.getRGB(i - 1, j + 1));
				R[0] = color.getRed();
				G[0] = color.getGreen();
				B[0] = color.getBlue();

				color = new Color(image.getRGB(i, j + 1));
				R[1] = color.getRed();
				G[1] = color.getGreen();
				B[1] = color.getBlue();

				color = new Color(image.getRGB(i + 1, j + 1));
				R[2] = color.getRed();
				G[2] = color.getGreen();
				B[2] = color.getBlue();

				color = new Color(image.getRGB(i - 1, j));
				R[3] = color.getRed();
				G[3] = color.getGreen();
				B[3] = color.getBlue();

				color = new Color(image.getRGB(i, j));
				R[4] = color.getRed();
				G[4] = color.getGreen();
				B[4] = color.getBlue();

				color = new Color(image.getRGB(i + 1, j));
				R[5] = color.getRed();
				G[5] = color.getGreen();
				B[5] = color.getBlue();

				color = new Color(image.getRGB(i - 1, j - 1));
				R[6] = color.getRed();
				G[6] = color.getGreen();
				B[6] = color.getBlue();

				color = new Color(image.getRGB(i, j - 1));
				R[7] = color.getRed();
				G[7] = color.getGreen();
				B[7] = color.getBlue();

				color = new Color(image.getRGB(i + 1, j - 1));
				R[8] = color.getRed();
				G[8] = color.getGreen();
				B[8] = color.getBlue();

				Arrays.sort(R);
				Arrays.sort(G);
				Arrays.sort(B);

				color = new Color(R[5], G[5], B[5]);
				image2.setRGB(i, j, color.getRGB());
			}
		}
		return image2;
	}
}
