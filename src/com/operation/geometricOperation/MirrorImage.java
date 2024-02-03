package com.operation.geometricOperation;

import java.awt.image.BufferedImage;

import com.operation.IOperation;

public class MirrorImage implements IOperation {
	private BufferedImage image;

	private BufferedImage convert(BufferedImage image) {
		// TODO Auto-generated method stub
		BufferedImage Image2 = new BufferedImage(image.getWidth(), image.getWidth(), BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				Image2.setRGB(x, y, image.getRGB(image.getWidth() - x - 1, y));
			}
		}

		return Image2;
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
