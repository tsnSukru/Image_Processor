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

		int globalMax = 0;
		for (int i = 0; i < 256; i++) {
			if (R[i] > globalMax)
				globalMax = R[i];
			if (G[i] > globalMax)
				globalMax = G[i];
			if (B[i] > globalMax)
				globalMax = B[i];
		}

		drawHistRed(R, globalMax);
		drawHistGreen(G, globalMax);
		drawHistBlue(B, globalMax);

//		drawHistRed(R);
//		drawHistGreen(G);
//		drawHistBlue(B);

		return image;
	}

	private BufferedImage drawHistRed(int R[], int globalMax) {
		BufferedImage hist = new BufferedImage(256, 500, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < hist.getWidth(); i++) {
			for (int j = 0; j < hist.getHeight(); j++) {
				Color color = new Color(255, 0, 0);
				hist.setRGB(i, j, color.getRGB());
			}
		}

		for (int i = 0; i <= 255; i++) {
			int scaledValue = (R[i] * hist.getHeight() - 1) / globalMax;
			for (int j = 0; j <= scaledValue; j++) {
				hist.setRGB(i, hist.getHeight() - j - 1, Integer.parseUnsignedInt("1111111111110000000000000000", 2));
			}
		}
		HistogramWindow h = new HistogramWindow(hist);
		h.title = "Kirmizi rengin histogrami";
		h.main(null);
		return hist;
	}

	private BufferedImage drawHistGreen(int G[], int globalMax) {
		BufferedImage hist = new BufferedImage(256, 500, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < hist.getWidth(); i++) {
			for (int j = 0; j < hist.getHeight(); j++) {
				Color color = new Color(0, 255, 0);
				hist.setRGB(i, j, color.getRGB());
			}
		}

		for (int i = 0; i <= 255; i++) {
			int scaledValue = (G[i] * hist.getHeight() - 1) / globalMax;
			System.out.println("scaled Value: " + scaledValue);
			for (int j = 0; j <= scaledValue; j++) {
				hist.setRGB(i, hist.getHeight() - j - 1, Integer.parseUnsignedInt("0000000011111111111100000000", 2));
			}
		}
		HistogramWindow h2 = new HistogramWindow(hist);
		h2.title = "Yesil rengin histogrami";
		h2.main(null);
		return hist;
	}

	private BufferedImage drawHistBlue(int B[], int globalMax) {
		BufferedImage hist = new BufferedImage(256, 500, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < hist.getWidth(); i++) {
			for (int j = 0; j < hist.getHeight(); j++) {
				Color color = new Color(0, 0, 255);
				hist.setRGB(i, j, color.getRGB());
			}
		}

		for (int i = 0; i <= 255; i++) {
			int scaledValue = (B[i] * hist.getHeight() - 1) / globalMax;
			for (int j = 0; j <= scaledValue; j++) {
				hist.setRGB(i, hist.getHeight() - j - 1, Integer.parseUnsignedInt("0000000000000000111111111111", 2));
			}
		}
		HistogramWindow h3 = new HistogramWindow(hist);
		h3.title = "Mavi rengin histogrami";
		h3.main(null);
		return hist;
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
		BufferedImage hist = new BufferedImage(256, 500, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < hist.getWidth(); i++) {
			for (int j = 0; j < hist.getHeight(); j++) {
				Color color = new Color(0, 255, 0);
				hist.setRGB(i, j, color.getRGB());
			}
		}
		for (int i = 0; i <= 255; i++) {
			for (int j = 0; j <= G[i]; j++) {
				hist.setRGB(i, 500 - j - 1, Integer.parseUnsignedInt("0000000011111111111100000000", 2));
			}
		}
		HistogramWindow h2 = new HistogramWindow(hist);
		h2.title = "Yesil rengin histogrami";
		h2.main(null);
		return hist;
	}

	private BufferedImage drawHistBlue(int[] B) {
		BufferedImage hist = new BufferedImage(256, 500, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < hist.getWidth(); i++) {
			for (int j = 0; j < hist.getHeight(); j++) {
				Color color = new Color(0, 0, 255);
				hist.setRGB(i, j, color.getRGB());
			}
		}
		for (int i = 0; i <= 255; i++) {
			for (int j = 0; j <= B[i]; j++) {
				hist.setRGB(i, 500 - j - 1, Integer.parseUnsignedInt("0000000000000000111111111111", 2));
			}
		}
		HistogramWindow h3 = new HistogramWindow(hist);
		h3.title = "Mavi rengin histogrami";
		h3.main(null);
		return hist;
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
