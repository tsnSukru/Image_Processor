package com.basicOperation;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Histogram {
	int R[] = new int[256];
	int G[] = new int[256];
	int B[] = new int[256];

	public Histogram(BufferedImage image) {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < image.getWidth(); i++) {// her piksel değerinden toplam kaç tane bulduk
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				R[color.getRed()]++;
				G[color.getGreen()]++;
				B[color.getBlue()]++;
			}
		}
		for (int j = 0; j < 256; j++) {// resim içine sığdırmak için değerleri belli bir oranla azaltıyoruz
			R[j] = R[j] / 10;
			G[j] = G[j] / 10;
			B[j] = B[j] / 10;
		}
	}

	public BufferedImage drawHistRed() {
		BufferedImage hist = new BufferedImage(256, 500, BufferedImage.TYPE_INT_ARGB);
		// 256 genislikte 500 yukseklikte bos resim olusturduk
		for (int i = 0; i < hist.getWidth(); i++) {
			for (int j = 0; j < hist.getHeight(); j++) {
				Color color = new Color(255, 0, 0);
				hist.setRGB(i, j, color.getRGB());
			}
		}
		for (int i = 0; i <= 255; i++) {// her piksel degeri icin bir ust yukseklikteki pikseli boyadik
			for (int j = 0; j <= R[i]; j++) {
				hist.setRGB(i, 500 - j - 1, Integer.parseUnsignedInt("1111111111110000000000000000", 2));
			}
		}
		histogramWindow h = new histogramWindow(hist);
		h.title = "Kirmizi rengin histogrami";
		h.main(null);
		return hist;
	}

	public BufferedImage drawHistGreen() {
		BufferedImage hist2 = new BufferedImage(256, 500, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < hist2.getWidth(); i++) {
			for (int j = 0; j < hist2.getHeight(); j++) {
				Color color = new Color(0, 255, 0);
				hist2.setRGB(i, j, color.getRGB());
			}
		}
		for (int i = 0; i <= 255; i++) {
			for (int j = 0; j <= G[i]; j++) {
				hist2.setRGB(i, 500 - j - 1, Integer.parseUnsignedInt("0000000011111111111100000000", 2));
			}
		}
		histogramWindow h2 = new histogramWindow(hist2);
		h2.title = "Yesil rengin histogrami";
		h2.main(null);
		return hist2;
	}

	public BufferedImage drawHistBlue() {
		BufferedImage hist3 = new BufferedImage(256, 500, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < hist3.getWidth(); i++) {
			for (int j = 0; j < hist3.getHeight(); j++) {
				Color color = new Color(0, 0, 255);
				hist3.setRGB(i, j, color.getRGB());
			}
		}
		for (int i = 0; i <= 255; i++) {
			for (int j = 0; j <= B[i]; j++) {
				hist3.setRGB(i, 500 - j - 1, Integer.parseUnsignedInt("0000000000000000111111111111", 2));
			}
		}
		histogramWindow h3 = new histogramWindow(hist3);
		h3.title = "Mavi rengin histogrami";
		h3.main(null);
		return hist3;
	}
}
