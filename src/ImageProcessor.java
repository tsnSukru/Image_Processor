import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;

public class ImageProcessor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setBounds(500, 300, 450, 300);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("systemImg//wallpaperflare.com_wallpaper.jpg"));
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		JProgressBar jb = new JProgressBar(0, 2000);
		jb.setBounds(10, 120, 416, 31);
		jb.setValue(0);
		jb.setStringPainted(true);

		frame.add(jb);
		frame.setLayout(null);
		frame.setVisible(true);
		JLabel lblNewLabel1 = new JLabel();
		lblNewLabel1.setBounds(0, 0, 450, 300);
		// lblNewLabel1.setIcon(new ImageIcon("bin//systemImg//187161.jpg"));
		frame.getContentPane().add(lblNewLabel1);

		JLabel lblNewLabel = new JLabel("Yükleniyor...");
		lblNewLabel.setBounds(180, 143, 118, 41);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tosun Software");
		lblNewLabel_1.setFont(new Font("Segoe Script", Font.ITALIC, 41));
		lblNewLabel_1.setBounds(30, 60, 416, 41);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Image Processor (Early Acces)");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(133, 181, 229, 31);
		frame.getContentPane().add(lblNewLabel_1_1);

		int i = 0, j = 0;
		while (i <= 2000) {
			jb.setValue(i);
			i = i + 20;
			if (j == 2) {
				lblNewLabel.setText("Yükleniyor.");
			}
			if (j == 5) {
				lblNewLabel.setText("Yükleniyor..");
			}
			if (j == 8) {
				lblNewLabel.setText("Yükleniyor...");
			}
			j += 1;
			if (j == 11) {
				j = 0;
			}
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if (i == 500) {
				try {
					Thread.sleep(300);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if (i == 1000) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if (i == 1900) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		mainWindow main = new mainWindow();
		main.frmImageProcessor.setVisible(true);
		frame.dispose();
	}

}
