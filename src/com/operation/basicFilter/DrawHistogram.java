package com.operation.basicFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.operation.IOperation;

public class DrawHistogram implements IOperation {
	private BufferedImage image;

	private BufferedImage draw(BufferedImage image) {
		// TODO Auto-generated constructor stub
		int R[] = new int[256];
		int G[] = new int[256];
		int B[] = new int[256];

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

		drawHistRed(R);
		drawHistGreen(G);
		drawHistBlue(B);

		return image;
	}

	private BufferedImage drawHistRed(int R[]) {
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
		HistogramWindow h = new HistogramWindow(hist);
		h.title = "Kirmizi rengin histogrami";
		h.main(null);
		return hist;
	}

	private BufferedImage drawHistGreen(int[] G) {
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
		HistogramWindow h2 = new HistogramWindow(hist2);
		h2.title = "Yesil rengin histogrami";
		h2.main(null);
		return hist2;
	}

	private BufferedImage drawHistBlue(int[] B) {
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
		HistogramWindow h3 = new HistogramWindow(hist3);
		h3.title = "Mavi rengin histogrami";
		h3.main(null);
		return hist3;
	}

	@Override
	public void setParameters(Object... params) {
		// TODO Auto-generated method stub
		this.image = (BufferedImage) params[0];
	}

	@Override
	public BufferedImage apply() {
		// TODO Auto-generated method stub
		return draw(this.image);
	}
}
