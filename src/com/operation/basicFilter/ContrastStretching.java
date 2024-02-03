package com.operation.basicFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.operation.IOperation;
import com.operation.utility.SplitIntoPixel;

public class ContrastStretching implements IOperation {
	private BufferedImage image;

	private BufferedImage convert(BufferedImage image) {

		Grayscale grayscale = new Grayscale();
		grayscale.setParameters(image);
		image = grayscale.apply();// Griye cevirdik

		int[][] pixels = new int[image.getWidth()][image.getHeight()];
		SplitIntoPixel splitPixel = new SplitIntoPixel();
		pixels = splitPixel.split(image);// pixellerine ulastik

		int maxValue = Integer.parseUnsignedInt((Integer.toBinaryString(pixels[0][0]).substring(8, 16)), 2);
		for (int i = 0; i < pixels.length; i++) {// max degeri bulduk
			for (int j = 0; j < pixels[0].length; j++) {
				if (Integer.parseUnsignedInt((Integer.toBinaryString(pixels[i][j]).substring(8, 16)), 2) > maxValue) {
					maxValue = Integer.parseUnsignedInt((Integer.toBinaryString(pixels[i][j]).substring(8, 16)), 2);
				}
			}
		}

		int minValue = Integer.parseUnsignedInt((Integer.toBinaryString(pixels[0][0]).substring(8, 16)), 2);
		for (int i = 0; i < pixels.length; i++) {// min degeri bulduk
			for (int j = 0; j < pixels[0].length; j++) {
				if (Integer.parseUnsignedInt((Integer.toBinaryString(pixels[i][j]).substring(8, 16)), 2) < minValue) {
					minValue = Integer.parseUnsignedInt((Integer.toBinaryString(pixels[i][j]).substring(8, 16)), 2);
				}
			}
		}

		Color color = new Color(0);
		int a = 0;
		for (int i = 0; i < pixels.length; i++) {// tum pikseller icin formul uygulandı
			for (int j = 0; j < pixels[0].length; j++) {
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
