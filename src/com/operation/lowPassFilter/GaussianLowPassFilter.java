package com.operation.lowPassFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.operation.IOperation;

public class GaussianLowPassFilter implements IOperation {
	private BufferedImage image;

	private BufferedImage convert(BufferedImage image) {
		Color color, color2;
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		int templateSize = 5; // Çekirdek matrisin kose uzunlugu
		int x, y, i, j, sumR, sumG, sumB, avgR, avgG, avgB;
		int[] matris = { 1, 4, 7, 4, 1, 4, 20, 33, 20, 4, 7, 33, 55, 33, 7, 4, 20, 33, 20, 4, 1, 4, 7, 4, 1 };
		int matrisSum = 1 + 4 + 7 + 4 + 1 + 4 + 20 + 33 + 20 + 4 + 7 + 33 + 55 + 33 + 7 + 4 + 20 + 33 + 20 + 4 + 1 + 4
				+ 7 + 4 + 1;
		for (x = (templateSize - 1) / 2; x < image.getWidth() - (templateSize - 1) / 2; x++)
		// Resmi taramaya şablonun yarısı kadar dış kenarlardan içeride başlayacak ve
		// bitirecek
		{
			for (y = (templateSize - 1) / 2; y < image.getHeight() - (templateSize - 1) / 2; y++) {
				sumR = 0;
				sumG = 0;
				sumB = 0;
				// Şablon bölgesi içindeki pikselleri tarıyor.
				int k = 0; // matris içindeki elemanları sırayla okurken kullanılacak.
				for (i = -((templateSize - 1) / 2); i <= (templateSize - 1) / 2; i++) {
					for (j = -((templateSize - 1) / 2); j <= (templateSize - 1) / 2; j++) {
						color = new Color(image.getRGB(x + i, y + j));
						sumR = sumR + color.getRed() * matris[k];
						sumG = sumG + color.getGreen() * matris[k];
						sumB = sumB + color.getBlue() * matris[k];
						k++;
					}
				}
				avgR = sumR / matrisSum;
				avgG = sumG / matrisSum;
				avgB = sumB / matrisSum;
				color2 = new Color(avgR, avgG, avgB);
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
