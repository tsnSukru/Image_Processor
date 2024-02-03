package com.operation.geometricOperation;

import java.awt.image.BufferedImage;

import com.operation.IOperation;

public class ShiftPhoto implements IOperation {
	private BufferedImage image;
	private int x;
	private int y;

	private BufferedImage convert(BufferedImage image, int x, int y) {
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		if (x < 0 & y < 0) {
			for (int i = -x; i < image.getWidth(); i++) {
				for (int j = -y; j < image.getHeight(); j++) {
					image2.setRGB(i, j, image.getRGB(i, j));
				}
			}
		} else if (x < 0 & y > 0) {
			for (int i = -x; i < image.getWidth(); i++) {
				for (int j = 0; j < image.getHeight() - y; j++) {
					image2.setRGB(i, j, image.getRGB(i, j));
				}
			}
		} else if (x > 0 & y < 0) {
			for (int i = 0; i < image.getWidth() - x; i++) {
				for (int j = -y; j < image.getHeight(); j++) {
					image2.setRGB(i, j, image.getRGB(i, j));
				}
			}
		} else if (x > 0 & y > 0) {
			for (int i = 0; i < image.getWidth() - x; i++) {
				for (int j = 0; j < image.getHeight() - y; j++) {
					image2.setRGB(i, j, image.getRGB(i, j));
				}
			}
		}
		return image2;
	}

	@Override
	public void setParameters(Object... params) {
		// TODO Auto-generated method stub
		this.image = (BufferedImage) params[0];
		this.x = (Integer) params[1];
		this.y = (Integer) params[2];
	}

	@Override
	public BufferedImage apply() {
		// TODO Auto-generated method stub
		return convert(this.image, this.x, this.y);
	}
}
