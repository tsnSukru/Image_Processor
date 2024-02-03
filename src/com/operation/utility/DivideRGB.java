package com.operation.utility;

public class DivideRGB {
	int alpha;
	int red;
	int green;
	int blue;
	private String alphaString;
	private String redString;
	private String greenString;
	private String blueString;

	public void setPixel(int pixel) {
		String pixelString = Integer.toBinaryString(pixel);
		alpha = Integer.parseInt(pixelString.substring(0, 8));
		alphaString = Integer.toBinaryString(alpha);
		while (alphaString.length() < 8) {// eğer 8 bitten azsa sol taraftan 0 ekle
			alphaString = "0" + alphaString;
		}
		red = Integer.parseInt(pixelString.substring(8, 16), 2);
		redString = Integer.toBinaryString(red);
		while (redString.length() < 8) {// eğer 8 bitten azsa sol taraftan 0 ekle
			redString = "0" + redString;
		}
		green = Integer.parseInt(pixelString.substring(16, 24), 2);
		greenString = Integer.toBinaryString(red);
		while (greenString.length() < 8) {// eğer 8 bitten azsa sol taraftan 0 ekle
			greenString = "0" + greenString;
		}
		blue = Integer.parseInt(pixelString.substring(24, 32), 2);
		blueString = Integer.toBinaryString(red);
		while (blueString.length() < 8) {// eğer 8 bitten azsa sol taraftan 0 ekle
			blueString = "0" + blueString;
		}
	}

	public String getAlphaString() {
		return alphaString;
	}

	public String getRedsString() {
		return redString;
	}

	public String getGreenString() {
		return greenString;
	}

	public String getBlueString() {
		return blueString;
	}
}
