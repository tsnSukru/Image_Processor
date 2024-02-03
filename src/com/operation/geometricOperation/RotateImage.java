package com.operation.geometricOperation;

import java.awt.image.BufferedImage;

import com.operation.IOperation;

public class RotateImage implements IOperation {
	private BufferedImage image;
	private int degrees;

	private BufferedImage convert(BufferedImage image, Integer degrees) {

		BufferedImage secondImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		// arkadaki pikseller silinsin diye direk kopyalamadik
		double angle = Math.toRadians(degrees);
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		double x0 = 0.5 * (image.getWidth() - 1);
		double y0 = 0.5 * (image.getHeight() - 1);
		int xx;
		int yy;
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				double a = x - x0;
				double b = y - y0;
				xx = (int) (+a * cos - b * sin + x0);
				yy = (int) (+a * sin + b * cos + y0);
				if (xx >= 0 && xx < image.getWidth() && yy >= 0 && yy < image.getHeight()) {
					secondImage.setRGB(x, y, image.getRGB(xx, yy));
				}
			}
		}
		return secondImage;
	}

	@Override
	public void setParameters(Object... params) {
		// TODO Auto-generated method stub
		this.image = (BufferedImage) params[0];
		this.degrees = (Integer) params[1];
	}

	@Override
	public BufferedImage apply() {
		// TODO Auto-generated method stub
		return convert(this.image, this.degrees);
	}
}
