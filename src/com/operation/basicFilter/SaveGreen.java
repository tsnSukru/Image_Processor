package com.operation.basicFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.operation.IOperation;
import com.operation.utility.CopyImage;

public class SaveGreen implements IOperation {
	CopyImage copyImage = new CopyImage();
	BufferedImage image;
	int greenBorder;
	int tolerance;

	public BufferedImage convert(BufferedImage image, int greenBorder, int tolerance) {
		BufferedImage renderedImage = copyImage.copy(image);
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {

				Color color = new Color(image.getRGB(x, y));
				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();

				if (!(green >= red + tolerance && green >= blue + tolerance)) {
					renderedImage.setRGB(x, y, Color.BLACK.getRGB());
				}
				if (green < greenBorder) {
					renderedImage.setRGB(x, y, Color.BLACK.getRGB());
				}
				if (red > green) {
					renderedImage.setRGB(x, y, Color.BLACK.getRGB());
				}
//				if (blue > green) {
//					renderedImage.setRGB(x, y, Color.BLACK.getRGB());
//				}
			}
		}
		return renderedImage;
	}

	@Override
	public void setParameters(Object... params) {
		// TODO Auto-generated method stub
		this.image = (BufferedImage) params[0];
		this.greenBorder = (int) params[1];
		this.tolerance = (int) params[2];

	}

	@Override
	public BufferedImage apply() {
		// TODO Auto-generated method stub
		return convert(image, greenBorder, tolerance);
	}

}
