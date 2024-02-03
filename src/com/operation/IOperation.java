package com.operation;

import java.awt.image.BufferedImage;

public interface IOperation {
	void setParameters(Object... params);

	public BufferedImage apply();
}
