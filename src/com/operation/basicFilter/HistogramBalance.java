package com.operation.basicFilter;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.operation.IOperation;
import com.operation.utility.CopyImage;

public class HistogramBalance implements IOperation {
	private CopyImage copyImage = new CopyImage();
	private BufferedImage image;

	private BufferedImage convert(BufferedImage image) {
		int histRed[] = new int[256];
		int histGreen[] = new int[256];
		int histBlue[] = new int[256];
		int R;
		int G;
		int B;
		BufferedImage renderedImage = copyImage.copy(image);
		int pixelAmount = image.getHeight() * image.getWidth();
		float c = (float) 255 / pixelAmount;

		for (int i = 0; i < image.getWidth(); i++) {// her renk degerine gore histogramları cikardik
			for (int j = 0; j < image.getHeight(); j++) {
				Color color = new Color(image.getRGB(i, j));
				histRed[color.getRed()]++;
				histGreen[color.getGreen()]++;
				histBlue[color.getBlue()]++;
			}
		}
		for (int i = 1; i <= 255; i++) {// forumulu uyguladık
			histRed[i] += histRed[i - 1];
			histGreen[i] += histGreen[i - 1];
			histBlue[i] += histBlue[i - 1];
		}
		for (int i = 0; i < image.getWidth(); i++) {// formulu uyguladık
			for (int j = 0; j < image.getHeight(); j++) {
				Color pixelColor = new Color(image.getRGB(i, j));

				R = (int) (histRed[pixelColor.getRed()] * c);
				G = (int) (histGreen[pixelColor.getGreen()] * c);
				B = (int) (histBlue[pixelColor.getBlue()] * c);
				pixelColor = new Color(R, G, B);
				renderedImage.setRGB(i, j, pixelColor.getRGB());
			}
		}
		return renderedImage;
	}

	@Override
	public void setParameters(Object... params) {
		this.image = (BufferedImage) params[0];
	}

	@Override
	public BufferedImage apply() {
		return convert(this.image);
	}

}
