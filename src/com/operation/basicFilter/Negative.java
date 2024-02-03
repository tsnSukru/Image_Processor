package com.operation.basicFilter;

import java.awt.image.BufferedImage;

import com.operation.IOperation;

public class Negative implements IOperation {
	private BufferedImage image;

	private BufferedImage convert(BufferedImage image) {
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				String pixel = Integer.toBinaryString(image.getRGB(i, j));// int degeri binary stringe donusturduk
				String alpha = pixel.substring(0, 8);// ilk 8 alpha degeri
				int red = Integer.parseInt(pixel.substring(8, 16), 2);// 2. sekiz kirmizi degeri
				red = 255 - red;// rengi 255 ten cikarip tersini aldik
				String redString = Integer.toBinaryString(red);
				while (redString.length() < 8) {// eğer 8 bitten azsa sol taraftan 0 ekle
					redString = "0" + redString;
				}
				int green = Integer.parseInt(pixel.substring(16, 24), 2);
				green = 255 - green;
				String greenString = Integer.toBinaryString(green);
				while (greenString.length() < 8) {// eğer 8 bitten azsa sol taraftan 0 ekle
					greenString = "0" + greenString;
				}
				int blue = Integer.parseInt(pixel.substring(24, 32), 2);
				blue = 255 - blue;
				String blueString = Integer.toBinaryString(blue);
				while (blueString.length() < 8) {// eğer 8 bitten azsa sol taraftan 0 ekle
					blueString = "0" + blueString;
				}
				String pixel2 = alpha + redString + greenString + blueString;// ARGB degerleri sırayla string topladik
				image.setRGB(i, j, Integer.parseUnsignedInt(pixel2, 2));// binary string degeri ikilik sistemden int
																		// degere donusturup pikseli yerine koyduk
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
