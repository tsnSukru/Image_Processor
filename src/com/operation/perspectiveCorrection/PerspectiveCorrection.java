package com.operation.perspectiveCorrection;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.operation.IOperation;
import com.operation.utility.CopyImage;

public class PerspectiveCorrection implements IOperation {
	private BufferedImage image;
	private CopyImage copyImage = new CopyImage();

	private BufferedImage convert(BufferedImage image) {
		BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		double x1 = 47.0;
		double y1 = 155.0;
		double x2 = 500.0;
		double y2 = 101.0;
		double x3 = 46.0;
		double y3 = 291.0;
		double x4 = 440.0;
		double y4 = 330.0;
		double X1 = 0.0;
		double Y1 = 50.0;
		double X2 = 472.0;
		double Y2 = 0.0;
		double X3 = 0.0;
		double Y3 = 470.0;
		double X4 = 472.0;
		double Y4 = 355.0;
		double[][] GirisMatrisi = new double[8][8];
//matrisi girdiğimiz değerlerle elle doldurduk
		GirisMatrisi[0][0] = x1;
		GirisMatrisi[0][1] = y1;
		GirisMatrisi[0][2] = 1;
		GirisMatrisi[0][3] = 0;
		GirisMatrisi[0][4] = 0;
		GirisMatrisi[0][5] = 0;
		GirisMatrisi[0][6] = -x1 * X1;
		GirisMatrisi[0][7] = -y1 * X1;

		GirisMatrisi[1][0] = 0;
		GirisMatrisi[1][1] = 0;
		GirisMatrisi[1][2] = 0;
		GirisMatrisi[1][3] = x1;
		GirisMatrisi[1][4] = y1;
		GirisMatrisi[1][5] = 1;
		GirisMatrisi[1][6] = -x1 * Y1;
		GirisMatrisi[1][7] = -y1 * Y1;

		GirisMatrisi[2][0] = x2;
		GirisMatrisi[2][1] = y2;
		GirisMatrisi[2][2] = 1;
		GirisMatrisi[2][3] = 0;
		GirisMatrisi[2][4] = 0;
		GirisMatrisi[2][5] = 0;
		GirisMatrisi[2][6] = -x2 * X2;
		GirisMatrisi[2][7] = -y2 * X2;

		GirisMatrisi[3][0] = 0;
		GirisMatrisi[3][1] = 0;
		GirisMatrisi[3][2] = 0;
		GirisMatrisi[3][3] = x2;
		GirisMatrisi[3][4] = y2;
		GirisMatrisi[3][5] = 1;
		GirisMatrisi[3][6] = -x2 * Y2;
		GirisMatrisi[3][7] = -y2 * Y2;

		GirisMatrisi[4][0] = x3;
		GirisMatrisi[4][1] = y3;
		GirisMatrisi[4][2] = 1;
		GirisMatrisi[4][3] = 0;
		GirisMatrisi[4][4] = 0;
		GirisMatrisi[4][5] = 0;
		GirisMatrisi[4][6] = -x3 * X3;
		GirisMatrisi[4][7] = -y3 * X3;

		GirisMatrisi[5][0] = 0;
		GirisMatrisi[5][1] = 0;
		GirisMatrisi[5][2] = 0;
		GirisMatrisi[5][3] = x3;
		GirisMatrisi[5][4] = y3;
		GirisMatrisi[5][5] = 1;
		GirisMatrisi[5][6] = -x3 * Y3;
		GirisMatrisi[5][7] = -y3 * Y3;

		GirisMatrisi[6][0] = x4;
		GirisMatrisi[6][1] = y4;
		GirisMatrisi[6][2] = 1;
		GirisMatrisi[6][3] = 0;
		GirisMatrisi[6][4] = 0;
		GirisMatrisi[6][5] = 0;
		GirisMatrisi[6][6] = -x4 * X4;
		GirisMatrisi[6][7] = -y4 * X4;

		GirisMatrisi[7][0] = 0;
		GirisMatrisi[7][1] = 0;
		GirisMatrisi[7][2] = 0;
		GirisMatrisi[7][3] = x4;
		GirisMatrisi[7][4] = y4;
		GirisMatrisi[7][5] = 1;
		GirisMatrisi[7][6] = -x4 * Y4;
		GirisMatrisi[7][7] = -y4 * Y4;

		// matrisin tersini aldık
		double[][] matrisBTersi = MatrisTersiniAl(GirisMatrisi);

		// tersini aldığımız matrisin çarpacağımız değeri oluşturuyoruz
		double a00 = 0, a01 = 0, a02 = 0, a10 = 0, a11 = 0, a12 = 0, a20 = 0, a21 = 0, a22 = 0;

		a00 = matrisBTersi[0][0] * X1 + matrisBTersi[0][1] * Y1 + matrisBTersi[0][2] * X2 + matrisBTersi[0][3] * Y2
				+ matrisBTersi[0][4] * X3 + matrisBTersi[0][5] * Y3 + matrisBTersi[0][6] * X4 + matrisBTersi[0][7] * Y4;

		a01 = matrisBTersi[1][0] * X1 + matrisBTersi[1][1] * Y1 + matrisBTersi[1][2] * X2 + matrisBTersi[1][3] * Y2
				+ matrisBTersi[1][4] * X3 + matrisBTersi[1][5] * Y3 + matrisBTersi[1][6] * X4 + matrisBTersi[1][7] * Y4;

		a02 = matrisBTersi[2][0] * X1 + matrisBTersi[2][1] * Y1 + matrisBTersi[2][2] * X2 + matrisBTersi[2][3] * Y2
				+ matrisBTersi[2][4] * X3 + matrisBTersi[2][5] * Y3 + matrisBTersi[2][6] * X4 + matrisBTersi[2][7] * Y4;

		a10 = matrisBTersi[3][0] * X1 + matrisBTersi[3][1] * Y1 + matrisBTersi[3][2] * X2 + matrisBTersi[3][3] * Y2
				+ matrisBTersi[3][4] * X3 + matrisBTersi[3][5] * Y3 + matrisBTersi[3][6] * X4 + matrisBTersi[3][7] * Y4;

		a11 = matrisBTersi[4][0] * X1 + matrisBTersi[4][1] * Y1 + matrisBTersi[4][2] * X2 + matrisBTersi[4][3] * Y2
				+ matrisBTersi[4][4] * X3 + matrisBTersi[4][5] * Y3 + matrisBTersi[4][6] * X4 + matrisBTersi[4][7] * Y4;

		a12 = matrisBTersi[5][0] * X1 + matrisBTersi[5][1] * Y1 + matrisBTersi[5][2] * X2 + matrisBTersi[5][3] * Y2
				+ matrisBTersi[5][4] * X3 + matrisBTersi[5][5] * Y3 + matrisBTersi[5][6] * X4 + matrisBTersi[5][7] * Y4;

		a20 = matrisBTersi[6][0] * X1 + matrisBTersi[6][1] * Y1 + matrisBTersi[6][2] * X2 + matrisBTersi[6][3] * Y2
				+ matrisBTersi[6][4] * X3 + matrisBTersi[6][5] * Y3 + matrisBTersi[6][6] * X4 + matrisBTersi[6][7] * Y4;

		a21 = matrisBTersi[7][0] * X1 + matrisBTersi[7][1] * Y1 + matrisBTersi[7][2] * X2 + matrisBTersi[7][3] * Y2
				+ matrisBTersi[7][4] * X3 + matrisBTersi[7][5] * Y3 + matrisBTersi[7][6] * X4 + matrisBTersi[7][7] * Y4;

		a22 = 1;

		image2 = PerspektifDuzelt(a00, a01, a02, a10, a11, a12, a20, a21, a22, copyImage.copy(image));
		return image2;
	}

	private BufferedImage PerspektifDuzelt(double a00, double a01, double a02, double a10, double a11, double a12,
			double a20, double a21, double a22, BufferedImage image) {
		BufferedImage GirisResmi, CikisResmi;
		Color OkunanRenk = new Color(0);
		GirisResmi = copyImage.copy(image);
		int ResimGenisligi = GirisResmi.getWidth();
		int ResimYuksekligi = GirisResmi.getHeight();
		CikisResmi = new BufferedImage(ResimGenisligi, ResimYuksekligi, BufferedImage.TYPE_INT_ARGB);
		double X, Y, z;// X,Y,z değerlerini bul yerlerine yerleştir
		for (int x = 0; x < ResimGenisligi; x++) {
			for (int y = 0; y < ResimYuksekligi; y++) {
				OkunanRenk = new Color(GirisResmi.getRGB(x, y));
				z = a20 * x + a21 * y + 1;
				X = (a00 * x + a01 * y + a02) / z;
				Y = (a10 * x + a11 * y + a12) / z;
				if (X > 0 && X < ResimGenisligi && Y > 0 && Y < ResimYuksekligi) {// resim sinirlardan tasmiyosa resimde
																					// yerine koy
					CikisResmi.setRGB((int) X, (int) Y, OkunanRenk.getRGB());
				}
			}
		}
		return CikisResmi;
	}

	private double[][] MatrisTersiniAl(double[][] GirisMatrisi) {
		int MatrisBoyutu = 8;
		double[][] CikisMatrisi = new double[MatrisBoyutu][MatrisBoyutu];
		for (int i = 0; i < MatrisBoyutu; i++) {
			for (int j = 0; j < MatrisBoyutu; j++) {
				if (i == j) {
					CikisMatrisi[i][j] = 1;
				} else {
					CikisMatrisi[i][j] = 0;
				}
			}
		}
		double d, k;
		for (int i = 0; i < MatrisBoyutu; i++) {
			d = GirisMatrisi[i][i];
			for (int j = 0; j < MatrisBoyutu; j++) {
				if (d == 0) {
					d = 0.0001;
				}
				GirisMatrisi[i][j] = GirisMatrisi[i][j] / d;
				CikisMatrisi[i][j] = CikisMatrisi[i][j] / d;
			}
			for (int x = 0; x < MatrisBoyutu; x++) {
				if (x != i) {
					k = GirisMatrisi[x][i];
					for (int j = 0; j < MatrisBoyutu; j++) {
						GirisMatrisi[x][j] = GirisMatrisi[x][j] - GirisMatrisi[i][j] * k;
						CikisMatrisi[x][j] = CikisMatrisi[x][j] - CikisMatrisi[i][j] * k;
					}
				}
			}
		}
		return CikisMatrisi;
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
