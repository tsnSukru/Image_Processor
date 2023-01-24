package com.highPassFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SobelFilter {
	public BufferedImage sobelFilter(BufferedImage image) {

		int x = image.getWidth();
		int y = image.getHeight();

		int[][] edgeColors = new int[x][y];
		int maxGradient = -1;
		int a00, a01, a02, a10, a11, a12, a20, a21, a22, sx, sy, s;
		for (int i = 1; i < x - 1; i++) {
			for (int j = 1; j < y - 1; j++) {

				a00 = getGrayScale(image.getRGB(i - 1, j - 1));
				a01 = getGrayScale(image.getRGB(i - 1, j));
				a02 = getGrayScale(image.getRGB(i - 1, j + 1));

				a10 = getGrayScale(image.getRGB(i, j - 1));
				a11 = getGrayScale(image.getRGB(i, j));
				a12 = getGrayScale(image.getRGB(i, j + 1));

				a20 = getGrayScale(image.getRGB(i + 1, j - 1));
				a21 = getGrayScale(image.getRGB(i + 1, j));
				a22 = getGrayScale(image.getRGB(i + 1, j + 1));

				// x düzleminde filtre uygulanisi
				sx = ((-1 * a00) + (0 * a01) + (1 * a02)) + ((-2 * a10) + (0 * a11) + (2 * a12))
						+ ((-1 * a20) + (0 * a21) + (1 * a22));

				// y düzleminde filtre uygulanisi
				sy = ((-1 * a00) + (-2 * a01) + (-1 * a02)) + ((0 * a10) + (0 * a11) + (0 * a12))
						+ ((1 * a20) + (2 * a21) + (1 * a22));

				double sValue = Math.sqrt((sx * sx) + (sy * sy));
				s = (int) sValue;

				if (maxGradient < s) {
					maxGradient = s;
				}

				edgeColors[i][j] = s;
			}
		}

		double scale = 255.0 / maxGradient;
		for (int i = 1; i < x - 1; i++) {
			for (int j = 1; j < y - 1; j++) {
				int edgeColor = edgeColors[i][j];
				edgeColor = (int) (edgeColor * scale);

				edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

				image.setRGB(i, j, edgeColor);
			}
		}
		return image;
	}

	public int getGrayScale(int rgb) {
		Color color = new Color(rgb);
		int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
		return gray;
	}
}
