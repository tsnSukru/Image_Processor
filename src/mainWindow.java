import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

import com.basicOperation.Brightness;
import com.basicOperation.ContrastSet;
import com.basicOperation.ContrastStretching;
import com.basicOperation.Grayscale;
import com.basicOperation.Histogram;
import com.basicOperation.HistogramBalance;
import com.basicOperation.Negative;
import com.basicOperation.Thresholding;
import com.functional.CopyImage;
import com.geometricOperation.ImageReverse;
import com.geometricOperation.MirrorImage;
import com.geometricOperation.RotateImage;
import com.geometricOperation.ShiftPhoto;
import com.geometricOperation.ZoomImage;
import com.highPassFilter.LaplacianFilter;
import com.highPassFilter.PrewittFilter;
import com.highPassFilter.SobelFilter;
import com.lowPassFilter.GaussianLowPassFilter;
import com.lowPassFilter.LowPassFilter;
import com.lowPassFilter.MeanLowPassFilter;
import com.lowPassFilter.MedianLowPassFilter;
import com.morphological.ClosingMorph;
import com.morphological.Dilation;
import com.morphological.Erosion;
import com.morphological.OpeningMorph;
import com.perspectiveCorrection.PerspectiveCorrection;
import com.sharpening.ConvolutionSharpening;
import com.sharpening.EdgeSharpening;

public class mainWindow {

	public JFrame frmImageProcessor;
	private JSpinner spinner;
	private JSpinner spinner2;
	private JLabel lblFirstImg;
	private JLabel lblSecondImage;
	private JComboBox cbBasicOperations;
	private JComboBox cbLowPassFilters;
	private JComboBox cbHighPassFilters;
	private JComboBox cbMorphological;
	public JComboBox cbPerspective;
	private JButton btnApply;
	private File inputImg;
	private BufferedImage firstImage;
	private BufferedImage secondImage;
	private BufferedImage thirdImage;
	boolean imageChoose = false;
	String lastChangeOperation = "basicOperation";
	private JComboBox cbImageSharpening;
	CopyImage copyImage = new CopyImage();
	private JLabel lblPerspektifIslemleri_1;
	private JLabel lblBackground;

	/**
	 * , Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					mainWindow window = new mainWindow();
					window.frmImageProcessor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmImageProcessor = new JFrame();
		frmImageProcessor
				.setIconImage(Toolkit.getDefaultToolkit().getImage("systemImg//wallpaperflare.com_wallpaper.jpg"));
		frmImageProcessor.getContentPane().setBackground(new Color(255, 248, 220));
		frmImageProcessor.setForeground(new Color(255, 255, 255));
		frmImageProcessor.setTitle(
				"Image Processor(Early Acces)                                                                  -----Tosun Software-----");
		frmImageProcessor.setBackground(new Color(0, 0, 0));
		frmImageProcessor.setBounds(-10, 0, 1900, 840);
		frmImageProcessor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmImageProcessor.getContentPane().setLayout(null);

		lblFirstImg = new JLabel();
		lblFirstImg.setBounds(12, 91, 700, 700);
		frmImageProcessor.getContentPane().add(lblFirstImg);

		lblSecondImage = new JLabel("");
		lblSecondImage.setBounds(828, 91, 700, 700);
		frmImageProcessor.getContentPane().add(lblSecondImage);

		spinner = new JSpinner();
		spinner.setBounds(685, 60, 75, 20);
		frmImageProcessor.getContentPane().add(spinner);

		spinner2 = new JSpinner();
		spinner2.setBounds(812, 60, 75, 20);
		frmImageProcessor.getContentPane().add(spinner2);

		cbBasicOperations = new JComboBox();
		cbBasicOperations.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = "basicOperation";
			}
		});
		cbBasicOperations.addItem("Gri Tonluya Cevir");
		cbBasicOperations.addItem("Negatif");
		cbBasicOperations.addItem("Thresholding");
		cbBasicOperations.addItem("Kontrast Ayarlama");
		cbBasicOperations.addItem("Kontrast Germe");
		cbBasicOperations.addItem("Histogram Dengeleme");
		cbBasicOperations.addItem("Histogramı Cizdir");
		cbBasicOperations.addItem("Parlaklık Ayarla");
		cbBasicOperations.setBounds(12, 27, 212, 21);
		frmImageProcessor.getContentPane().add(cbBasicOperations);

		cbLowPassFilters = new JComboBox();
		cbLowPassFilters.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = "lowPassFilter";
			}
		});
		cbLowPassFilters.setBounds(451, 27, 212, 21);
		frmImageProcessor.getContentPane().add(cbLowPassFilters);
		cbLowPassFilters.addItem("Low Pass Filter");
		cbLowPassFilters.addItem("Gaussian Filter");
		cbLowPassFilters.addItem("Mean");
		cbLowPassFilters.addItem("Median");

		cbHighPassFilters = new JComboBox();
		cbHighPassFilters.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = "highPassFilter";
			}
		});
		cbHighPassFilters.setBounds(227, 27, 212, 21);
		frmImageProcessor.getContentPane().add(cbHighPassFilters);
		cbHighPassFilters.addItem("Laplacian");
		cbHighPassFilters.addItem("Sobel");
		cbHighPassFilters.addItem("Prewitt");

		JButton btnSelectImage = new JButton("Resim Seç");
		btnSelectImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readImage();
			}
		});
		btnSelectImage.setBounds(1327, 27, 97, 21);
		frmImageProcessor.getContentPane().add(btnSelectImage);

		cbMorphological = new JComboBox();
		cbMorphological.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = "morphological";
			}
		});
		cbMorphological.setBounds(899, 27, 131, 21);
		cbMorphological.addItem("Siyah rengi yay");
		cbMorphological.addItem("Beyaz rengi yay");
		cbMorphological.addItem("Beyaz rengi aşındır");
		cbMorphological.addItem("Siyah rengi aşındır");
		cbMorphological.addItem("Beyaz Açma");
		cbMorphological.addItem("Siyah Açma");
		cbMorphological.addItem("Beyaz Kapama");
		cbMorphological.addItem("Siyah Kapama");
		frmImageProcessor.getContentPane().add(cbMorphological);

		cbImageSharpening = new JComboBox();
		cbImageSharpening.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = "sharpening";
			}
		});
		cbImageSharpening.setBounds(675, 27, 212, 21);
		cbImageSharpening.addItem("Kenar Görüntüsü İle Netleştirme");
		cbImageSharpening.addItem("Konvolüsyon Yöntemi");
		frmImageProcessor.getContentPane().add(cbImageSharpening);

		JComboBox cbGeometric = new JComboBox();
		cbGeometric.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = "geometric";
			}
		});
		cbGeometric.setBounds(1191, 27, 131, 21);
		cbGeometric.addItem("Döndür");
		cbGeometric.addItem("Ters Cevir");
		cbGeometric.addItem("Aynala");
		cbGeometric.addItem("Otele");
		cbGeometric.addItem("Yakinlastir");
		cbGeometric.addItem("Uzaklastir");
		cbGeometric.addItem("Yakinlastir2");
		frmImageProcessor.getContentPane().add(cbGeometric);

		btnApply = new JButton("Uygula");
		btnApply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (lastChangeOperation == "basicOperation") {
						if (cbBasicOperations.getSelectedIndex() == 0) {
							Grayscale g = new Grayscale();
							applyChange(copyImage.copy(firstImage), g.convert(copyImage.copy(firstImage)));
						} else if (cbBasicOperations.getSelectedIndex() == 1) {
							Negative negative = new Negative();
							applyChange(copyImage.copy(firstImage), negative.convert(copyImage.copy(firstImage)));
						} else if (cbBasicOperations.getSelectedIndex() == 2) {
							Thresholding th = new Thresholding();
							applyChange(copyImage.copy(firstImage),
									th.convert(copyImage.copy(firstImage), spinner.getValue()));
						} else if (cbBasicOperations.getSelectedIndex() == 3) {
							ContrastSet contrastSet = new ContrastSet();
							applyChange(firstImage,
									contrastSet.Set(copyImage.copy(firstImage), (Integer) spinner.getValue()));
						} else if (cbBasicOperations.getSelectedIndex() == 4) {
							ContrastStretching cStretching = new ContrastStretching();
							Grayscale grayscale = new Grayscale();
							applyChange(grayscale.convert(firstImage), cStretching.convert(copyImage.copy(firstImage)));
						} else if (cbBasicOperations.getSelectedIndex() == 5) {
							HistogramBalance hBalance = new HistogramBalance();
							applyChange(copyImage.copy(firstImage),
									hBalance.histogramBalance(copyImage.copy(firstImage)));
						} else if (cbBasicOperations.getSelectedIndex() == 6) {
							Histogram hist = new Histogram(copyImage.copy(firstImage));
							lblFirstImg.setIcon(new ImageIcon(firstImage));
							hist.drawHistRed();
							hist.drawHistGreen();
							hist.drawHistBlue();
						} else if (cbBasicOperations.getSelectedIndex() == 7) {
							Brightness brightness = new Brightness();
							applyChange(firstImage,
									brightness.changeBrightness(copyImage.copy(firstImage), spinner.getValue()));
						}
					} else if (lastChangeOperation == "lowPassFilter") {
						if (cbLowPassFilters.getSelectedIndex() == 0) {
							LowPassFilter lwpf = new LowPassFilter();
							Grayscale grey = new Grayscale();
							applyChange(grey.convert(firstImage), lwpf.convert(copyImage.copy(firstImage)));
						} else if (cbLowPassFilters.getSelectedIndex() == 1) {
							GaussianLowPassFilter gaussianLowPassFilter = new GaussianLowPassFilter();
							applyChange(copyImage.copy(firstImage),
									gaussianLowPassFilter.gaussFilter(copyImage.copy(firstImage)));
						} else if (cbLowPassFilters.getSelectedIndex() == 2) {
							MeanLowPassFilter meanLowPassFilter = new MeanLowPassFilter();
							applyChange(firstImage, meanLowPassFilter.meanFilter(copyImage.copy(firstImage),
									copyImage.copy(firstImage)));
						} else if (cbLowPassFilters.getSelectedIndex() == 3) {
							MedianLowPassFilter medianLowPassFilter = new MedianLowPassFilter();
							applyChange(copyImage.copy(firstImage), medianLowPassFilter
									.medianFilter(copyImage.copy(firstImage), copyImage.copy(firstImage)));
						}
					} else if (lastChangeOperation == "highPassFilter") {
						if (cbHighPassFilters.getSelectedIndex() == 0) {
							LaplacianFilter lFilter = new LaplacianFilter();
							applyChange(firstImage, lFilter.convert(copyImage.copy(firstImage)));
						} else if (cbHighPassFilters.getSelectedIndex() == 1) {
							SobelFilter sFilter = new SobelFilter();
							applyChange(copyImage.copy(firstImage), sFilter.sobelFilter(copyImage.copy(firstImage)));
						} else if (cbHighPassFilters.getSelectedIndex() == 2) {
							PrewittFilter pFilter = new PrewittFilter();
							applyChange(copyImage.copy(firstImage), pFilter.prewittFilter(copyImage.copy(firstImage)));
						}
					} else if (lastChangeOperation == "morphological") {
						Thresholding thresholding = new Thresholding();
						if (cbMorphological.getSelectedIndex() == 0) {
							Dilation dl = new Dilation();
							applyChange(thresholding.convert(copyImage.copy(firstImage), spinner.getValue()),
									dl.blackDilation(
											thresholding.convert(copyImage.copy(firstImage), spinner.getValue())));
						} else if (cbMorphological.getSelectedIndex() == 1) {
							Dilation dl = new Dilation();
							applyChange(thresholding.convert(copyImage.copy(firstImage), spinner.getValue()),
									dl.whiteDilation(
											thresholding.convert(copyImage.copy(firstImage), spinner.getValue())));
						} else if (cbMorphological.getSelectedIndex() == 2) {
							Erosion erosion = new Erosion();
							applyChange(thresholding.convert(copyImage.copy(firstImage), spinner.getValue()),
									erosion.whiteErosion(
											thresholding.convert(copyImage.copy(firstImage), spinner.getValue())));
						} else if (cbMorphological.getSelectedIndex() == 3) {
							Erosion erosion = new Erosion();
							applyChange(thresholding.convert(copyImage.copy(firstImage), spinner.getValue()),
									erosion.blackErosion(
											thresholding.convert(copyImage.copy(firstImage), spinner.getValue())));
						} else if (cbMorphological.getSelectedIndex() == 4) {
							OpeningMorph openingMorph = new OpeningMorph();
							applyChange(thresholding.convert(copyImage.copy(firstImage), spinner.getValue()),
									openingMorph.blackOpening(
											thresholding.convert(copyImage.copy(firstImage), spinner.getValue())));
						} else if (cbMorphological.getSelectedIndex() == 5) {
							OpeningMorph openingMorph = new OpeningMorph();
							applyChange(thresholding.convert(copyImage.copy(firstImage), spinner.getValue()),
									openingMorph.whiteOpening(
											thresholding.convert(copyImage.copy(firstImage), spinner.getValue())));
						} else if (cbMorphological.getSelectedIndex() == 6) {
							ClosingMorph closingMorph = new ClosingMorph();
							applyChange(thresholding.convert(copyImage.copy(firstImage), spinner.getValue()),
									closingMorph.whiteClosing(
											thresholding.convert(copyImage.copy(firstImage), spinner.getValue())));
						} else if (cbMorphological.getSelectedIndex() == 7) {
							ClosingMorph closingMorph = new ClosingMorph();
							applyChange(thresholding.convert(copyImage.copy(firstImage), spinner.getValue()),
									closingMorph.blackClosing(
											thresholding.convert(copyImage.copy(firstImage), spinner.getValue())));
						}
					} else if (lastChangeOperation == "sharpening") {
						if (cbImageSharpening.getSelectedIndex() == 0) {
							EdgeSharpening edgeSharpening = new EdgeSharpening();
							applyChange(firstImage, edgeSharpening.Sharp(copyImage.copy(firstImage)));
						} else if (cbImageSharpening.getSelectedIndex() == 1) {
							ConvolutionSharpening convolutionSharpening = new ConvolutionSharpening();
							applyChange(firstImage, convolutionSharpening.Sharp(copyImage.copy(firstImage)));
						}
					} else if (lastChangeOperation == "perspective") {
						if (cbPerspective.getSelectedIndex() == 0) {
							PerspectiveCorrection perspectiveCorrection = new PerspectiveCorrection();
							applyChange(firstImage, perspectiveCorrection.Correct(copyImage.copy(firstImage)));
						} else if (cbPerspective.getSelectedIndex() == 1) {
							PerspectiveCorrection perspectiveCorrection = new PerspectiveCorrection();
							applyChange(firstImage, perspectiveCorrection.Correct(copyImage.copy(firstImage)));
						}
					} else if (lastChangeOperation == "geometric") {
						if (cbGeometric.getSelectedIndex() == 0) {
							RotateImage rImage = new RotateImage();
							applyChange(firstImage,
									rImage.rotate(copyImage.copy(firstImage), (Integer) spinner.getValue()));
						} else if (cbGeometric.getSelectedIndex() == 1) {
							ImageReverse imageReverse = new ImageReverse();
							applyChange(firstImage, imageReverse.Reverse(copyImage.copy(firstImage)));
						} else if (cbGeometric.getSelectedIndex() == 2) {
							MirrorImage mirrorImage = new MirrorImage();
							applyChange(firstImage, mirrorImage.Mirror(copyImage.copy(firstImage)));
						} else if (cbGeometric.getSelectedIndex() == 3) {
							ShiftPhoto shiftPhoto = new ShiftPhoto();
							applyChange(firstImage, shiftPhoto.Shift(copyImage.copy(firstImage),
									(Integer) spinner.getValue(), (Integer) spinner2.getValue()));
						} else if (cbGeometric.getSelectedIndex() == 4) {
							ZoomImage zoomImage = new ZoomImage();
							applyChange(firstImage, zoomImage.Zoom(copyImage.copy(firstImage)));
						} else if (cbGeometric.getSelectedIndex() == 5) {
							ZoomImage zoomImage = new ZoomImage();
							applyChange(firstImage, zoomImage.zoomOut(copyImage.copy(firstImage)));
						} else if (cbGeometric.getSelectedIndex() == 6) {
							ZoomImage zoomImage = new ZoomImage();
							applyChange(firstImage, zoomImage.Zoom2(copyImage.copy(firstImage)));
						}
					}
				} catch (NullPointerException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(frmImageProcessor, "Resim seciniz");
				}
			}
		});
		btnApply.setBounds(1431, 27, 97, 21);
		frmImageProcessor.getContentPane().add(btnApply);

		JButton btnSaveImage = new JButton("Kaydet");
		btnSaveImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedImage img = new BufferedImage(lblSecondImage.getWidth(), lblSecondImage.getHeight(),
							BufferedImage.TYPE_INT_ARGB);
					Graphics2D g2d = img.createGraphics();
					lblSecondImage.printAll(g2d);
					g2d.dispose();
					File outputFile = new File("img//newFile.jpg");
					ImageIO.write(img, "png", outputFile);
					JOptionPane.showMessageDialog(frmImageProcessor, "Resim kaydedildi!");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnSaveImage.setBounds(1372, 60, 105, 21);
		frmImageProcessor.getContentPane().add(btnSaveImage);

		cbPerspective = new JComboBox();
		cbPerspective.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = "perspective";
			}
		});
		cbPerspective.setBounds(1048, 27, 131, 21);
		frmImageProcessor.getContentPane().add(cbPerspective);
		cbPerspective.addItem("Perspektif Düzelt");
		cbPerspective.addItem("Perspektif Düzelt");

		JLabel lblNewLabel = new JLabel("Basit İslemler");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(12, 12, 208, 16);
		frmImageProcessor.getContentPane().add(lblNewLabel);

		JLabel lblYuksekGecisFilterleri = new JLabel("Yuksek Gecis Filterleri");
		lblYuksekGecisFilterleri.setForeground(new Color(255, 255, 255));
		lblYuksekGecisFilterleri.setBounds(232, 12, 208, 16);
		frmImageProcessor.getContentPane().add(lblYuksekGecisFilterleri);

		JLabel lblAlcakGecisFiltreleri = new JLabel("Alcak Gecis Filtreleri");
		lblAlcakGecisFiltreleri.setForeground(new Color(255, 255, 255));
		lblAlcakGecisFiltreleri.setBounds(451, 12, 208, 16);
		frmImageProcessor.getContentPane().add(lblAlcakGecisFiltreleri);

		JLabel lblMorhpological = new JLabel("Morfolojik İslemler");
		lblMorhpological.setForeground(new Color(255, 255, 255));
		lblMorhpological.setBounds(899, 12, 131, 16);
		frmImageProcessor.getContentPane().add(lblMorhpological);

		JLabel lblNetlestirme = new JLabel("Netlestirme");
		lblNetlestirme.setForeground(new Color(255, 255, 255));
		lblNetlestirme.setBounds(677, 12, 208, 16);
		frmImageProcessor.getContentPane().add(lblNetlestirme);

		JLabel lblPerspektifIslemleri = new JLabel("Perspektif İslemleri");
		lblPerspektifIslemleri.setForeground(new Color(255, 255, 255));
		lblPerspektifIslemleri.setBounds(1048, 12, 131, 16);
		frmImageProcessor.getContentPane().add(lblPerspektifIslemleri);

		lblPerspektifIslemleri_1 = new JLabel("Perspektif İslemleri");
		lblPerspektifIslemleri_1.setForeground(new Color(255, 255, 255));
		lblPerspektifIslemleri_1.setBounds(1191, 12, 172, 16);
		frmImageProcessor.getContentPane().add(lblPerspektifIslemleri_1);

		lblBackground = new JLabel("New label");
		lblBackground.setForeground(SystemColor.info);
		lblBackground.setBackground(SystemColor.info);
		lblBackground.setIcon(new ImageIcon("systemImg//wallpaperflare.com_wallpaper.jpg"));
		lblBackground.setBounds(0, 0, 1540, 836);
		frmImageProcessor.getContentPane().add(lblBackground);

	}

	private void readImage() {
		try {
			JFileChooser chooser = new JFileChooser("img//");
			chooser.showOpenDialog(null);
			File file = chooser.getSelectedFile();
			inputImg = new File(file.getAbsolutePath());
			firstImage = ImageIO.read(inputImg);
			secondImage = ImageIO.read(inputImg);
			thirdImage = ImageIO.read(inputImg);
			lblFirstImg.setIcon(new ImageIcon(firstImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void applyChange(BufferedImage image1, BufferedImage image2) {
		lblFirstImg.setIcon(new ImageIcon(image1));
		lblSecondImage.setIcon(new ImageIcon(image2));
	}
}