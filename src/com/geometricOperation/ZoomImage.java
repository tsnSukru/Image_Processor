package com.geometricOperation;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ZoomImage {
	public BufferedImage Zoom(BufferedImage image) {
		Color color;
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		int zoomCoefficient = 3;
		int x2 = 0, y2 = 0;
		for (int x1 = 0; x1 < image.getWidth(); x1++) {
			for (int y1 = 0; y1 < image.getHeight(); y1++) {
				color = new Color(image.getRGB(x1, y1));
				for (int i = 0; i < zoomCoefficient; i++) {
					for (int j = 0; j < zoomCoefficient; j++) {
						x2 = x1 * zoomCoefficient + i;
						y2 = y1 * zoomCoefficient + j;
						if (x2 > 0 && x2 < image.getWidth() && y2 > 0 && y2 < image.getHeight()) {
							image2.setRGB(x2, y2, color.getRGB());
						}
					}
				}
			}
		}
		return image2;
	}

	public BufferedImage Zoom2(BufferedImage image) {
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Color color = new Color(0);
		int sumR = 0, sumG = 0, sumB = 0, avgR, avgG, avgB;
		for (int i = 0; i < image.getWidth() - 4; i += 2) {
			for (int j = 0; j < image.getHeight() - 4; j += 2) {
				for (int j2 = i; j2 < i + 4; j2++) {
					for (int k = j; k < j + 4; k++) {
						color = new Color(image.getRGB(j2, k));
						sumR += color.getRed();
						sumG += color.getGreen();
						sumB += color.getBlue();
					}
				}
				avgR = sumR / 16;
				avgG = sumG / 16;
				avgB = sumB / 16;
				for (int a = i * 4; a < image2.getWidth(); a++) {
					for (int b = j * 4; b < image2.getHeight(); b++) {
						color = new Color(avgR, avgG, avgB);
						image2.setRGB(a, b, color.getRGB());
					}
				}
				sumR = 0;
				sumG = 0;
				sumB = 0;
			}
		}
		return image2;
	}

	public BufferedImage zoomOut(BufferedImage image) {
		Color color, color2;
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		int R = 0, G = 0, B = 0;
		int x2 = 0, y2 = 0; // Çıkış resminin x ve y si
		int zoomOutCoefficient = 2;
		for (int x1 = 0; x1 < image.getWidth(); x1 = x1 + zoomOutCoefficient) {
			y2 = 0;
			for (int y1 = 0; y1 < image.getHeight(); y1 = y1 + zoomOutCoefficient) {
				// x ve y de ilerlerken atlanan pikselleri oku ve ortalama değerlerini al
				R = 0;
				G = 0;
				B = 0;
				try // resim sinirinin dışına cikilirsa program patlamasin diye try catch
				{
					for (int i = 0; i < zoomOutCoefficient; i++) {
						for (int j = 0; j < zoomOutCoefficient; j++) {
							color = new Color(image.getRGB(x1 + i, y1 + j));
							R = R + color.getRed();
							G = G + color.getGreen();
							B = B + color.getBlue();
						}
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				// Piksellerin ortalaması
				R = R / (zoomOutCoefficient * zoomOutCoefficient);
				G = G / (zoomOutCoefficient * zoomOutCoefficient);
				B = B / (zoomOutCoefficient * zoomOutCoefficient);
				color2 = new Color(R, G, B);
				image2.setRGB(x2, y2, color2.getRGB());
				y2++;
			}
			x2++;
		}
		return image2;
	}
}