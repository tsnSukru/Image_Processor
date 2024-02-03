package com.operation.morphologicalFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.operation.IOperation;
import com.operation.utility.CopyImage;

public class Dilation {
	private CopyImage copyImage = new CopyImage();

	public class WhiteDilation implements IOperation {
		private BufferedImage image;

		private BufferedImage convert(BufferedImage image) {
			BufferedImage secondImage = copyImage.copy(image);
			Color color = new Color(0);
			Color color2 = new Color(0);
			Color color3 = new Color(0);
			Color color4 = new Color(0);
			Color color6 = new Color(0);
			Color color7 = new Color(0);
			Color color8 = new Color(0);
			Color color9 = new Color(0);
			for (int i = 3; i < image.getHeight() - 3; i++) {
				for (int j = 3; j < image.getWidth() - 3; j++) {
					color = new Color(image.getRGB(j - 1, i - 1));
					color2 = new Color(image.getRGB(j, i - 1));
					color3 = new Color(image.getRGB(j + 1, i - 1));
					color4 = new Color(image.getRGB(j - 1, i));
					color6 = new Color(image.getRGB(j + 1, i));
					color7 = new Color(image.getRGB(j - 1, i + 1));
					color8 = new Color(image.getRGB(j, i + 1));
					color9 = new Color(image.getRGB(j + 1, i + 1));
					if (color.getRed() == 255 || color2.getRed() == 255 || color3.getRed() == 255
							|| color4.getRed() == 255 || color6.getRed() == 255 || color7.getRed() == 255
							|| color8.getRed() == 255 || color9.getRed() == 255) {
						color = new Color(255, 255, 255);
						secondImage.setRGB(j, i, color.getRGB());
					}
				}
			}
			return secondImage;
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

	public class BlackDilation implements IOperation {
		private BufferedImage image;

		private BufferedImage convert(BufferedImage image) {
			BufferedImage secondImage = copyImage.copy(image);
			Color color = new Color(0);
			Color color2 = new Color(0);
			Color color3 = new Color(0);
			Color color4 = new Color(0);
			Color color6 = new Color(0);
			Color color7 = new Color(0);
			Color color8 = new Color(0);
			Color color9 = new Color(0);
			for (int i = 3; i < image.getHeight() - 3; i++) {
				for (int j = 3; j < image.getWidth() - 3; j++) {
					color = new Color(image.getRGB(j - 1, i - 1));
					color2 = new Color(image.getRGB(j, i - 1));
					color3 = new Color(image.getRGB(j + 1, i - 1));
					color4 = new Color(image.getRGB(j - 1, i));
					color6 = new Color(image.getRGB(j + 1, i));
					color7 = new Color(image.getRGB(j - 1, i + 1));
					color8 = new Color(image.getRGB(j, i + 1));
					color9 = new Color(image.getRGB(j + 1, i + 1));
					if (color.getRed() == 0 || color2.getRed() == 0 || color3.getRed() == 0 || color4.getRed() == 0
							|| color6.getRed() == 0 || color7.getRed() == 0 || color8.getRed() == 0
							|| color9.getRed() == 0) {
						color = new Color(0, 0, 0);
						secondImage.setRGB(j, i, color.getRGB());
					}
				}
			}
			return secondImage;
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

}
