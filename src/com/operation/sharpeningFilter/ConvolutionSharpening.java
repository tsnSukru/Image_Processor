package com.operation.sharpeningFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.operation.IOperation;

public class ConvolutionSharpening implements IOperation {
	private BufferedImage image;

	private BufferedImage convert(BufferedImage image) {
		Color color;
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		int templateSize = 3;
		int x, y, i, j, toplamR, toplamG, toplamB;
		int R, G, B;
		int[] Matris = { 0, -2, 0, -2, 11, -2, 0, -2, 0 };
		int matrisSum = 0 + -2 + 0 + -2 + 11 + -2 + 0 + -2 + 0;// sablonun icindeki piksellerin toplamini bulduk
		for (x = (templateSize - 1) / 2; x < image.getWidth() - (templateSize - 1) / 2; x++) {
			for (y = (templateSize - 1) / 2; y < image.getHeight() - (templateSize - 1) / 2; y++) {
				toplamR = 0;
				toplamG = 0;
				toplamB = 0;
				int k = 0;// matris içindeki elemanları sirayla okurken kullanilacak. ,
				for (i = -((templateSize - 1) / 2); i <= (templateSize - 1) / 2; i++) {
					for (j = -((templateSize - 1) / 2); j <= (templateSize - 1) / 2; j++) {
						color = new Color(image.getRGB(x + i, y + j));
						toplamR = toplamR + color.getRed() * Matris[k];// sablonla carptik
						toplamG = toplamG + color.getGreen() * Matris[k];
						toplamB = toplamB + color.getBlue() * Matris[k];
						k++;
					}
				}
				R = toplamR / matrisSum;// sablon toplamini matris toplamina bolduk
				G = toplamG / matrisSum;
				B = toplamB / matrisSum;
				// =========================================
				// Renkler sınırların dışına çıktıysa, sınır değer alınacak.
				if (R > 255) {
					R = 255;
				}
				if (G > 255) {
					G = 255;
				}
				if (B > 255) {
					B = 255;
				}
				if (R < 0) {
					R = 0;
				}
				if (G < 0) {
					G = 0;
				}
				if (B < 0) {
					B = 0;
				}
				Color color2 = new Color(R, G, B);
				image2.setRGB(x, y, color2.getRGB());
			}
		}
		return image2;
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
