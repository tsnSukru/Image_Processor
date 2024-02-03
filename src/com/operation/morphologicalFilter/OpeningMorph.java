package com.operation.morphologicalFilter;

import java.awt.image.BufferedImage;

import com.operation.IOperation;
import com.operation.utility.CopyImage;

public class OpeningMorph {
	private CopyImage copyImage = new CopyImage();

	public class WhiteOpening implements IOperation {
		private BufferedImage image;

		private BufferedImage convert(BufferedImage image) {
			BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
			image2 = copyImage.copy(image);

			Erosion.WhiteErosion whiteErosion = new Erosion().new WhiteErosion();
			whiteErosion.setParameters(image);
			image2 = whiteErosion.apply();

			Dilation.WhiteDilation whiteDilation = new Dilation().new WhiteDilation();
			whiteDilation.setParameters(copyImage.copy(image2));
			image2 = whiteDilation.apply();

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

	public class BlackOpening implements IOperation {
		private BufferedImage image;

		private BufferedImage convert(BufferedImage image) {
			BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
			image2 = copyImage.copy(image);

			Erosion.BlackErosion blackErosion = new Erosion().new BlackErosion();
			blackErosion.setParameters(image);
			image2 = blackErosion.apply();

			Dilation.BlackDilation blackDilation = new Dilation().new BlackDilation();
			blackDilation.setParameters(copyImage.copy(image2));
			image2 = blackDilation.apply();

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

}
