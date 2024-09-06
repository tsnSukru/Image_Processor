package com.operation.basicFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.operation.IOperation;

public class ContrastStretching implements IOperation {
	private BufferedImage image;

	private BufferedImage convert(BufferedImage image) {

		Grayscale grayscale = new Grayscale();
		grayscale.setParameters(image);
		image = grayscale.apply();// Griye cevirdik

		int maxValue = new Color(image.getRGB(0, 0)).getRed();
		int minValue = maxValue;

		for (int i = 0; i < image.getWidth(); i++) { // Max ve min değerleri bul
			for (int j = 0; j < image.getHeight(); j++) {
				int redValue = new Color(image.getRGB(i, j)).getRed(); // Kırmızı değeri al
				if (redValue > maxValue) {
					maxValue = redValue;
				}
				if (redValue < minValue) {
					minValue = redValue;
				}
			}
		}

		Color color = new Color(0);
		int a = 0;
		for (int i = 0; i < image.getWidth(); i++) {// tum pikseller icin formul uygulandı
			for (int j = 0; j < image.getHeight(); j++) {
				color = new Color(image.getRGB(i, j));
				a = ((color.getRed() - minValue) * 255) / (maxValue - minValue);
				color = new Color(a, a, a);
				image.setRGB(i, j, color.getRGB());
			}
		}
		return image;
	}

	@Override
	public void setParameters(Object... params) {
		// TODO Auto-generated method stub
		this.image = (BufferedImage) params[0];
	}

	@Override
	public BufferedImage apply() {
		// TODO Auto-generated method stub
		return convert(this.image);
	}
}
