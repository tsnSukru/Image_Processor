package com.operation.highPassFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.operation.IOperation;

public class LaplacianFilter implements IOperation {
	private BufferedImage image;

	private BufferedImage convert(BufferedImage image) {
		Color color1, color2, color3, color4, color5;// diger pixeller 0 la çarpildigi icin eklemedim
		int red, green, blue, avg;
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int i = 1; i < image.getWidth() - 1; i++) {
			for (int j = 1; j < image.getHeight() - 1; j++) {
				color1 = new Color(image.getRGB(i, j - 1));
				color2 = new Color(image.getRGB(i - 1, j));
				color3 = new Color(image.getRGB(i, j));
				color4 = new Color(image.getRGB(i + 1, j));
				color5 = new Color(image.getRGB(i, j + 1));
				red = color1.getRed() + color2.getRed() + (-4) * color3.getRed() + color4.getRed() + color5.getRed();
				green = color1.getGreen() + color2.getGreen() + (-4) * color3.getGreen() + color4.getGreen()
						+ color5.getGreen();
				blue = color1.getBlue() + color2.getBlue() + (-4) * color3.getBlue() + color4.getBlue()
						+ color5.getBlue();
				avg = (red + green + blue) / 3;
				if (avg > 255) {
					avg = 255;
				}
				if (avg < 0) {
					avg = 0;
				}
				color1 = new Color(avg, avg, avg);
				image2.setRGB(i, j, color1.getRGB());
			}
		}

		return image2;
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
