package com.basicOperation;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.functional.CopyImage;

public class histogramWindow {
	static BufferedImage image2;
	static String title;

	public histogramWindow(BufferedImage image) {
		// TODO Auto-generated constructor stub
		CopyImage copyImage = new CopyImage();
		image2 = copyImage.copy(image);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setLayout(null);
		frame.setTitle(title);
		JLabel lblNewLabel1 = new JLabel();
		lblNewLabel1.setBounds(0, 0, 450, 300);
		lblNewLabel1.setIcon(new ImageIcon(image2));
		frame.getContentPane().add(lblNewLabel1);
		frame.setVisible(true);
	}

}
