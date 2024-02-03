package com.operation.morphologicalFilter;

import java.awt.image.BufferedImage;

import com.operation.IOperation;
import com.operation.utility.CopyImage;

public class ClosingMorph {
	private CopyImage copyImage = new CopyImage();

	public class WhiteClosing implements IOperation {
		private BufferedImage image;

		private BufferedImage convert(BufferedImage image) {
			BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
			image2 = copyImage.copy(image);

			Dilation.WhiteDilation whiteDilation = new Dilation().new WhiteDilation();
			whiteDilation.setParameters(image);
			image2 = whiteDilation.apply();

			Erosion.WhiteErosion whiteErosion = new Erosion().new WhiteErosion();
			whiteErosion.setParameters(copyImage.copy(image2));
			image2 = whiteErosion.apply();

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

	public class BlackClosing implements IOperation {
		private BufferedImage image;

		private BufferedImage convert(BufferedImage image) {
			BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
			image2 = copyImage.copy(image);

			Dilation.BlackDilation blackDilation = new Dilation().new BlackDilation();
			blackDilation.setParameters(image);
			image2 = blackDilation.apply();

			Erosion.BlackErosion blackErosion = new Erosion().new BlackErosion();
			blackErosion.setParameters(copyImage.copy(image2));
			image2 = blackErosion.apply();

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
