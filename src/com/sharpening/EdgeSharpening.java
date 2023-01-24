package com.sharpening;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.functional.CopyImage;
import com.lowPassFilter.MeanLowPassFilter;

public class EdgeSharpening {
	CopyImage copyImage = new CopyImage();

	public BufferedImage Sharp(BufferedImage image) {

		// GaussianLowPassFilter gaussianFilter = new GaussianLowPassFilter();
		MeanLowPassFilter meanLowPassFilter = new MeanLowPassFilter();

		BufferedImage BulanikResim = new BufferedImage(image.getWidth(), image.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		// BulanikResim = gaussianFilter.gaussFilter(copyImage.copy(image));
		BulanikResim = meanLowPassFilter.meanFilter(copyImage.copy(image), copyImage.copy(image));

		BufferedImage edgeView = Substraction(copyImage.copy(image), copyImage.copy(BulanikResim));
		BufferedImage NetlesmisResim = Unite(copyImage.copy(image), copyImage.copy(edgeView));
		return NetlesmisResim;
	}

	// birbirinden cikarma kismi
	private BufferedImage Substraction(BufferedImage image, BufferedImage BulanikResim) {
		Color color, color2, lastColor;
		BufferedImage image2;
		image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		int R, G, B;
		double Olcekleme = 2; // Keskin kenaları daha iyi görmek için değerini artırıyoruz.
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				color = new Color(image.getRGB(x, y));
				color2 = new Color(BulanikResim.getRGB(x, y));
				Double d = new Double(Olcekleme * Math.abs(color.getRed() - color2.getRed()));
				R = d.intValue();
				d = new Double(Olcekleme * Math.abs(color.getGreen() - color2.getGreen()));
				G = d.intValue();
				d = new Double(Olcekleme * Math.abs(color.getBlue() - color2.getBlue()));
				B = d.intValue();
				// Renkler sınırların dışına çıktıysa, sınır değer alınacak
				if (R > 255) {
					R = 255;
				}
				if (G > 255) {
					G = 255;
				}
				if (B > 255) {
					B = 255;
				}
				if (R < 0) {
					R = 0;
				}
				if (G < 0) {
					G = 0;
				}
				if (B < 0) {
					B = 0;
				}
				lastColor = new Color(R, G, B);
				image2.setRGB(x, y, lastColor.getRGB());
			}
		}
		return image2;
	}

	private BufferedImage Unite(BufferedImage image, BufferedImage edgeView) {// birlestirme kismi
		Color color, color2, lastColor;
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		int R, G, B;
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				color = new Color(image.getRGB(x, y));
				color2 = new Color(edgeView.getRGB(x, y));
				R = color.getRed() + color2.getRed();
				G = color.getGreen() + color2.getGreen();
				B = color.getBlue() + color2.getBlue();
				// Renkler sınırların dışına çıktıysa, sınır değer alınacak
				if (R > 255) {
					R = 255;
				}
				if (G > 255) {
					G = 255;
				}
				if (B > 255) {
					B = 255;
				}
				if (R < 0) {
					R = 0;
				}
				if (G < 0) {
					G = 0;
				}
				if (B < 0) {
					B = 0;
				}
				lastColor = new Color(R, G, B);
				image2.setRGB(x, y, lastColor.getRGB());
			}
		}
		return image2;
	}
}
