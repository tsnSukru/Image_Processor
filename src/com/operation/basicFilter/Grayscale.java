package com.operation.basicFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.operation.IOperation;

public class Grayscale implements IOperation {
	private BufferedImage image;

	private BufferedImage convert(BufferedImage image) {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				int rgb = image.getRGB(i, j);

				// Alfa kanalını koru
				int alpha = (rgb >> 24) & 0xFF;

				// RGB değerlerini al
				int red = (rgb >> 16) & 0xFF;
				int green = (rgb >> 8) & 0xFF;
				int blue = rgb & 0xFF;

				int average = (red + green + blue) / 3;
				image.setRGB(i, j, new Color(average, average, average).getRGB());
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
