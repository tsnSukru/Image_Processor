package com.lowPassFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class MeanLowPassFilter {

	public BufferedImage meanFilter(BufferedImage image, BufferedImage image2) {// aynı resim üzerinde değişmiş
																				// değerlerle
		// çalışmamak için ilk resmi işleyip ikinci
		// resimde yerine koydum

		int R = 0;
		int G = 0;
		int B = 0;
		for (int i = 2; i < image.getWidth() - 2; i++) {
			for (int j = 2; j < image.getHeight() - 2; j++) {
				R = 0;
				G = 0;
				B = 0;

				Color color = new Color(image.getRGB(i - 1, j + 1));
				R += color.getRed();
				G += color.getGreen();
				B += color.getBlue();

				color = new Color(image.getRGB(i, j + 1));
				R += color.getRed();
				G += color.getGreen();
				B += color.getBlue();

				color = new Color(image.getRGB(i + 1, j + 1));
				R += color.getRed();
				G += color.getGreen();
				B += color.getBlue();

				color = new Color(image.getRGB(i - 1, j));
				R += color.getRed();
				G += color.getGreen();
				B += color.getBlue();

				color = new Color(image.getRGB(i, j));
				R += color.getRed();
				G += color.getGreen();
				B += color.getBlue();

				color = new Color(image.getRGB(i + 1, j));
				R += color.getRed();
				G += color.getGreen();
				B += color.getBlue();

				color = new Color(image.getRGB(i - 1, j - 1));
				R += color.getRed();
				G += color.getGreen();
				B += color.getBlue();

				color = new Color(image.getRGB(i, j - 1));
				R += color.getRed();
				G += color.getGreen();
				B += color.getBlue();

				color = new Color(image.getRGB(i + 1, j - 1));
				R += color.getRed();
				G += color.getGreen();
				B += color.getBlue();

				R /= 9;
				G /= 9;
				B /= 9;

				color = new Color(R, G, B);
				image2.setRGB(i, j, color.getRGB());
			}
		}
		return image2;
	}
}
