package com.operation.basicFilter;

import java.awt.image.BufferedImage;

import com.operation.IOperation;

public class Thresholding implements IOperation {
	private BufferedImage image;
	private Object border;

	private BufferedImage convert(BufferedImage image, int border) {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				int rgb = image.getRGB(i, j);

				// Alfa kanalını koru
				int alpha = (rgb >> 24) & 0xFF;

				// RGB değerlerini al
				int red = (rgb >> 16) & 0xFF;
				int green = (rgb >> 8) & 0xFF;
				int blue = rgb & 0xFF;

				// Ortalama hesapla ve eşik uygula
				int average = (red + green + blue) / 3;
				average = (average < border) ? 0 : 255;

				// Yeni RGB değeri oluştur
				int newRgb = (alpha << 24) | (average << 16) | (average << 8) | average;

				image.setRGB(i, j, newRgb);
			}
		}
		return image;
	}

	@Override
	public void setParameters(Object... params) {
		// TODO Auto-generated method stub
		this.image = (BufferedImage) params[0];
		this.border = params[1];
	}

	@Override
	public BufferedImage apply() {
		// TODO Auto-generated method stub
		return convert(this.image, (int) this.border);
	}
}
