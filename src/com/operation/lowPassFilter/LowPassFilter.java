package com.operation.lowPassFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.operation.IOperation;
import com.operation.utility.DivideRGB;

public class LowPassFilter implements IOperation {
	private DivideRGB divide = new DivideRGB();
	private BufferedImage image;

	private BufferedImage convert(BufferedImage image) {
		for (int i = 1; i < image.getWidth() - 3; i++) {
			for (int j = 1; j < image.getHeight() - 3; j++) {

				int pixel1Red = new Color(image.getRGB(i - 1, j - 1)).getRed();
				int pixel1Green = new Color(image.getRGB(i - 1, j - 1)).getGreen();
				int pixel1Blue = new Color(image.getRGB(i - 1, j - 1)).getBlue();
				//
				int pixel2Red = new Color(image.getRGB(i - 1, j)).getRed();
				int pixel2Green = new Color(image.getRGB(i - 1, j)).getGreen();
				int pixel2Blue = new Color(image.getRGB(i - 1, j)).getBlue();
				//
				int pixel3Red = new Color(image.getRGB(i - 1, j + 1)).getRed();
				int pixel3Green = new Color(image.getRGB(i - 1, j + 1)).getGreen();
				int pixel3Blue = new Color(image.getRGB(i - 1, j + 1)).getBlue();
				//
				int pixel4Red = new Color(image.getRGB(i, j - 1)).getRed();
				int pixel4Green = new Color(image.getRGB(i, j - 1)).getGreen();
				int pixel4Blue = new Color(image.getRGB(i, j - 1)).getBlue();
				//
				int pixel5Red = new Color(image.getRGB(i, j)).getRed();
				int pixel5Green = new Color(image.getRGB(i, j)).getGreen();
				int pixel5Blue = new Color(image.getRGB(i, j)).getBlue();
				//
				int pixel6Red = new Color(image.getRGB(i, j + 1)).getRed();
				int pixel6Green = new Color(image.getRGB(i, j + 1)).getGreen();
				int pixel6Blue = new Color(image.getRGB(i, j + 1)).getBlue();
				//
				int pixel7Red = new Color(image.getRGB(i + 1, j - 1)).getRed();
				int pixel7Green = new Color(image.getRGB(i + 1, j - 1)).getGreen();
				int pixel7Blue = new Color(image.getRGB(i + 1, j - 1)).getBlue();
				//
				int pixel8Red = new Color(image.getRGB(i + 1, j)).getRed();
				int pixel8Green = new Color(image.getRGB(i + 1, j)).getGreen();
				int pixel8Blue = new Color(image.getRGB(i + 1, j)).getBlue();
				//
				int pixel9Red = new Color(image.getRGB(i + 1, j + 1)).getRed();
				int pixel9Green = new Color(image.getRGB(i + 1, j + 1)).getGreen();
				int pixel9Blue = new Color(image.getRGB(i + 1, j + 1)).getBlue();

				int averageRed = (pixel1Red + pixel2Red + pixel3Red + pixel4Red + pixel5Red + pixel6Red + pixel7Red
						+ pixel8Red + pixel9Red) / 9;

				int averageGreen = (pixel1Green + pixel2Green + pixel3Green + pixel4Green + pixel5Green + pixel6Green
						+ pixel7Green + pixel8Green + pixel9Green) / 9;

				int averageBlue = (pixel1Blue + pixel2Blue + pixel3Blue + pixel4Blue + pixel5Blue + pixel6Blue
						+ pixel7Blue + pixel8Blue + pixel9Blue) / 9;

				Color color = new Color(averageRed, averageGreen, averageBlue);
				image.setRGB(i - 1, j - 1, color.getRGB());
				image.setRGB(i - 1, j, color.getRGB());
				image.setRGB(i - 1, j + 1, color.getRGB());
				image.setRGB(i, j - 1, color.getRGB());
				image.setRGB(i, j + 1, color.getRGB());
				image.setRGB(i + 1, j - 1, color.getRGB());
				image.setRGB(i + 1, j, color.getRGB());
				image.setRGB(i + 1, j + 1, color.getRGB());
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
