package com.operation.lowPassFilter;

import java.awt.image.BufferedImage;

import com.operation.IOperation;
import com.operation.utility.DivideRGB;

public class LowPassFilter implements IOperation {
	private DivideRGB divide = new DivideRGB();
	private BufferedImage image;

	private BufferedImage convert(BufferedImage image) {
		for (int i = 1; i < image.getWidth() - 3; i++) {
			for (int j = 1; j < image.getHeight() - 3; j++) {
				divide.setPixel(image.getRGB(i - 1, j - 1));
				int pixel1Red = Integer.parseInt(divide.getRedsString(), 2);
				int pixel1Green = Integer.parseInt(divide.getGreenString(), 2);
				int pixel1Blue = Integer.parseInt(divide.getBlueString(), 2);
				//
				divide.setPixel(image.getRGB(i - 1, j));
				int pixel2Red = Integer.parseInt(divide.getRedsString(), 2);
				int pixel2Green = Integer.parseInt(divide.getGreenString(), 2);
				int pixel2Blue = Integer.parseInt(divide.getBlueString(), 2);
				//
				divide.setPixel(image.getRGB(i - 1, j + 1));
				int pixel3Red = Integer.parseInt(divide.getRedsString(), 2);
				int pixel3Green = Integer.parseInt(divide.getGreenString(), 2);
				int pixel3Blue = Integer.parseInt(divide.getBlueString(), 2);
				//
				divide.setPixel(image.getRGB(i, j - 1));
				int pixel4Red = Integer.parseInt(divide.getRedsString(), 2);
				int pixel4Green = Integer.parseInt(divide.getGreenString(), 2);
				int pixel4Blue = Integer.parseInt(divide.getBlueString(), 2);
				//
				divide.setPixel(image.getRGB(i, j));
				int pixel5Red = Integer.parseInt(divide.getRedsString(), 2);
				int pixel5Green = Integer.parseInt(divide.getGreenString(), 2);
				int pixel5Blue = Integer.parseInt(divide.getBlueString(), 2);
				//
				divide.setPixel(image.getRGB(i, j + 1));
				int pixel6Red = Integer.parseInt(divide.getRedsString(), 2);
				int pixel6Green = Integer.parseInt(divide.getGreenString(), 2);
				int pixel6Blue = Integer.parseInt(divide.getBlueString(), 2);
				//
				divide.setPixel(image.getRGB(i + 1, j - 1));
				int pixel7Red = Integer.parseInt(divide.getRedsString(), 2);
				int pixel7Green = Integer.parseInt(divide.getGreenString(), 2);
				int pixel7Blue = Integer.parseInt(divide.getBlueString(), 2);
				//
				divide.setPixel(image.getRGB(i + 1, j));
				int pixel8Red = Integer.parseInt(divide.getRedsString(), 2);
				int pixel8Green = Integer.parseInt(divide.getGreenString(), 2);
				int pixel8Blue = Integer.parseInt(divide.getBlueString(), 2);
				//
				divide.setPixel(image.getRGB(i + 1, j + 1));
				int pixel9Red = Integer.parseInt(divide.getRedsString(), 2);
				int pixel9Green = Integer.parseInt(divide.getGreenString(), 2);
				int pixel9Blue = Integer.parseInt(divide.getBlueString(), 2);
//
				// int averageAlpha = (pixel1Alpha + pixel2Alpha + pixel3Alpha + pixel4Alpha +
				// pixel5Alpha + pixel6Alpha
				// + pixel7Alpha + pixel8Alpha + pixel9Alpha) / 9;
				// String avgAlpha = Integer.toBinaryString(averageAlpha);
				// while (avgAlpha.length() < 8) {// eğer 8 bitten azsa sol taraftan 0 ekle
				// avgAlpha = "0" + avgAlpha;
				// }
				int averageRed = (pixel1Red + pixel2Red + pixel3Red + pixel4Red + pixel5Red + pixel6Red + pixel7Red
						+ pixel8Red + pixel9Red) / 9;
				String avgRed = Integer.toBinaryString(averageRed);
				while (avgRed.length() < 8) {// eğer 8 bitten azsa sol taraftan 0 ekle
					avgRed = "0" + avgRed;
				}
				int averageGreen = (pixel1Green + pixel2Green + pixel3Green + pixel4Green + pixel5Green + pixel6Green
						+ pixel7Green + pixel8Green + pixel9Green) / 9;
				String avgGreen = Integer.toBinaryString(averageGreen);
				while (avgGreen.length() < 8) {// eğer 8 bitten azsa sol taraftan 0 ekle
					avgGreen = "0" + avgGreen;
				}
				int averageBlue = (pixel1Blue + pixel2Blue + pixel3Blue + pixel4Blue + pixel5Blue + pixel6Blue
						+ pixel7Blue + pixel8Blue + pixel9Blue) / 9;
				String avgBlue = Integer.toBinaryString(averageBlue);
				while (avgBlue.length() < 8) {// eğer 8 bitten azsa sol taraftan 0 ekle
					avgBlue = "0" + avgBlue;
				}
				String pixel = "11111111" + avgRed + avgGreen + avgBlue;
				image.setRGB(i - 1, j - 1, Integer.parseUnsignedInt(pixel, 2));
				image.setRGB(i - 1, j, Integer.parseUnsignedInt(pixel, 2));
				image.setRGB(i - 1, j + 1, Integer.parseUnsignedInt(pixel, 2));
				image.setRGB(i, j - 1, Integer.parseUnsignedInt(pixel, 2));
				image.setRGB(i, j + 1, Integer.parseUnsignedInt(pixel, 2));
				image.setRGB(i + 1, j - 1, Integer.parseUnsignedInt(pixel, 2));
				image.setRGB(i + 1, j, Integer.parseUnsignedInt(pixel, 2));
				image.setRGB(i + 1, j + 1, Integer.parseUnsignedInt(pixel, 2));
				image.setRGB(i, j, Integer.parseUnsignedInt(pixel, 2));
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
