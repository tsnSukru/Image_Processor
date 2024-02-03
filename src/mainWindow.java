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
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

import com.operation.IOperation;
import com.operation.basicFilter.Brightness;
import com.operation.basicFilter.ContrastSet;
import com.operation.basicFilter.ContrastStretching;
import com.operation.basicFilter.DrawHistogram;
import com.operation.basicFilter.Grayscale;
import com.operation.basicFilter.HistogramBalance;
import com.operation.basicFilter.Negative;
import com.operation.basicFilter.Thresholding;
import com.operation.geometricOperation.ImageReverse;
import com.operation.geometricOperation.MirrorImage;
import com.operation.geometricOperation.RotateImage;
import com.operation.geometricOperation.ShiftPhoto;
import com.operation.geometricOperation.ZoomImage;
import com.operation.highPassFilter.LaplacianFilter;
import com.operation.highPassFilter.PrewittFilter;
import com.operation.highPassFilter.SobelFilter;
import com.operation.lowPassFilter.GaussianLowPassFilter;
import com.operation.lowPassFilter.LowPassFilter;
import com.operation.lowPassFilter.MeanLowPassFilter;
import com.operation.lowPassFilter.MedianLowPassFilter;
import com.operation.morphologicalFilter.ClosingMorph;
import com.operation.morphologicalFilter.Dilation;
import com.operation.morphologicalFilter.Erosion;
import com.operation.morphologicalFilter.OpeningMorph;
import com.operation.perspectiveCorrection.PerspectiveCorrection;
import com.operation.sharpeningFilter.ConvolutionSharpening;
import com.operation.sharpeningFilter.EdgeSharpening;
import com.operation.utility.CopyImage;

public class mainWindow {

	public JFrame frmImageProcessor;

	private JSpinner spinner;
	private JSpinner spinner2;

	private JLabel lblFirstImg;
	private JLabel lblSecondImage;
	private JLabel lblBackground;

	private JComboBox<String> cbBasicOperations;
	private JComboBox<String> cbLowPassFilters;
	private JComboBox<String> cbHighPassFilters;
	private JComboBox<String> cbMorphological;
	private JComboBox<String> cbPerspective;
	private JComboBox<String> cbImageSharpening;
	private JComboBox<String> cbGeometric;

	private JButton btnApply;
	private JButton btnSelectImage;

	private File inputImg;

	private BufferedImage firstImage;

	boolean imageChoose = false;
	private boolean isClearingComboBox = false;

	Map<String, IOperation> operations = new HashMap<>();

	String lastChangeOperation = "";
	CopyImage copyImage = new CopyImage();

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

		populateHashMap();

		frmImageProcessor = createMainFrame(frmImageProcessor);

		createSpinners();

		createComboBoxes();
		createComboBoxEvents();

		createButtons();

		createLabels();
	}

	private void populateHashMap() {

		// basic filter
		operations.put("Gri Tonluya Cevir", new Grayscale());
		operations.put("Negatif", new Negative());
		operations.put("Thresholding", new Thresholding());
		operations.put("Kontrast Ayarlama", new ContrastSet());
		operations.put("Kontrast Germe", new ContrastStretching());
		operations.put("Histogram Dengeleme", new HistogramBalance());
		operations.put("Histogramı Cizdir", new DrawHistogram());
		operations.put("Parlaklık Ayarla", new Brightness());

		// high pass filter
		operations.put("Laplacian", new LaplacianFilter());
		operations.put("Sobel", new SobelFilter());
		operations.put("Prewitt", new PrewittFilter());

		// low pass Filter
		operations.put("Low Pass Filter", new LowPassFilter());
		operations.put("Gaussian Filter", new GaussianLowPassFilter());
		operations.put("Mean", new MeanLowPassFilter());
		operations.put("Median", new MedianLowPassFilter());

		// sharpening filter
		operations.put("Kenar Görüntüsü İle Netleştirme", new EdgeSharpening());
		operations.put("Konvolüsyon Yöntemi", new ConvolutionSharpening());

		// morphological filter
		operations.put("Siyah rengi yay", new Dilation().new BlackDilation());
		operations.put("Beyaz rengi yay", new Dilation().new WhiteDilation());
		operations.put("Beyaz rengi aşındır", new Erosion().new WhiteErosion());
		operations.put("Siyah rengi aşındır", new Erosion().new BlackErosion());
		operations.put("Beyaz Açma", new OpeningMorph().new WhiteOpening());
		operations.put("Siyah Açma", new OpeningMorph().new BlackOpening());
		operations.put("Beyaz Kapama", new ClosingMorph().new WhiteClosing());
		operations.put("Siyah Kapama", new ClosingMorph().new BlackClosing());

		// perspective correction
		operations.put("Perspektif Düzelt", new PerspectiveCorrection());

		// geometric
		operations.put("Döndür", new RotateImage());
		operations.put("Ters Cevir", new ImageReverse());
		operations.put("Aynala", new MirrorImage());
		operations.put("Otele", new ShiftPhoto());
		operations.put("Yakinlastir", new ZoomImage().new Zoom());
		operations.put("Uzaklastir", new ZoomImage().new ZoomOut());
		operations.put("Yakinlastir2", new ZoomImage().new Zoom2());

	}

	private void createButtons() {

		btnSelectImage = new JButton("Resim Seç");
		btnSelectImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readImage();
			}
		});
		btnSelectImage.setBounds(1327, 27, 97, 21);
		frmImageProcessor.getContentPane().add(btnSelectImage);

		btnApply = new JButton("Uygula");
		btnApply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IOperation operation = operations.get(lastChangeOperation);
				if (operation != null) {
					try {
						operation.setParameters(copyImage.copy(firstImage), spinner.getValue(), spinner2.getValue());
						applyChange(firstImage, operation.apply());
					} catch (NullPointerException e2) {
						JOptionPane.showMessageDialog(frmImageProcessor, "Resim seciniz!");
					}
				} else {
					JOptionPane.showMessageDialog(frmImageProcessor, "Resme Uygulanacak Operasyonu Seciniz!");
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
					e2.printStackTrace();
				}
			}
		});
		btnSaveImage.setBounds(1372, 60, 105, 21);
		frmImageProcessor.getContentPane().add(btnSaveImage);
	}

	private void createComboBoxes() {

		cbBasicOperations = new JComboBox<String>();
		populateCbBasicOperations();
		frmImageProcessor.getContentPane().add(cbBasicOperations);

		cbPerspective = new JComboBox<String>();
		cbPerspective.setBounds(1048, 27, 131, 21);
		populateCbPerspective();
		frmImageProcessor.getContentPane().add(cbPerspective);

		cbLowPassFilters = new JComboBox<String>();
		cbLowPassFilters.setBounds(451, 27, 212, 21);
		populateCbLowPassFilters();
		frmImageProcessor.getContentPane().add(cbLowPassFilters);

		cbHighPassFilters = new JComboBox<String>();
		cbHighPassFilters.setBounds(227, 27, 212, 21);
		populateCbHighPassFilters();
		frmImageProcessor.getContentPane().add(cbHighPassFilters);

		cbMorphological = new JComboBox<String>();
		cbMorphological.setBounds(899, 27, 131, 21);
		populateCbMorphological();
		frmImageProcessor.getContentPane().add(cbMorphological);

		cbImageSharpening = new JComboBox<String>();
		cbImageSharpening.setBounds(675, 27, 212, 21);
		populateCbImageSharpening();
		frmImageProcessor.getContentPane().add(cbImageSharpening);

		cbGeometric = new JComboBox<String>();
		cbGeometric.setBounds(1191, 27, 131, 21);
		populateCbGeometric();
		frmImageProcessor.getContentPane().add(cbGeometric);
	}

	private void createComboBoxEvents() {

		cbBasicOperations.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = (String) cbBasicOperations.getSelectedItem();
				clearComboBox(cbBasicOperations, cbBasicOperations.getSelectedIndex());
			}
		});

		cbPerspective.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = (String) cbPerspective.getSelectedItem();
				clearComboBox(cbPerspective, cbPerspective.getSelectedIndex());
			}
		});

		cbLowPassFilters.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = (String) cbLowPassFilters.getSelectedItem();
				clearComboBox(cbLowPassFilters, cbLowPassFilters.getSelectedIndex());
			}
		});

		cbHighPassFilters.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = (String) cbHighPassFilters.getSelectedItem();
				clearComboBox(cbHighPassFilters, cbHighPassFilters.getSelectedIndex());
			}
		});

		cbMorphological.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = (String) cbMorphological.getSelectedItem();
				clearComboBox(cbMorphological, cbMorphological.getSelectedIndex());
			}
		});

		cbImageSharpening.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = (String) cbImageSharpening.getSelectedItem();
				clearComboBox(cbImageSharpening, cbImageSharpening.getSelectedIndex());
			}
		});

		cbGeometric.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				lastChangeOperation = (String) cbGeometric.getSelectedItem();
				clearComboBox(cbGeometric, cbGeometric.getSelectedIndex());
			}
		});
	}

	private void createSpinners() {

		spinner = new JSpinner();
		spinner.setBounds(685, 60, 75, 20);
		frmImageProcessor.getContentPane().add(spinner);

		spinner2 = new JSpinner();
		spinner2.setBounds(812, 60, 75, 20);
		frmImageProcessor.getContentPane().add(spinner2);
	}

	private void createLabels() {

		lblFirstImg = new JLabel("");
		lblFirstImg.setBounds(12, 91, 700, 700);
		frmImageProcessor.getContentPane().add(lblFirstImg);

		lblSecondImage = new JLabel("");
		lblSecondImage.setBounds(828, 91, 700, 700);
		frmImageProcessor.getContentPane().add(lblSecondImage);

		JLabel lblBasicOperation = new JLabel("Basit İslemler");
		lblBasicOperation.setForeground(new Color(255, 255, 255));
		lblBasicOperation.setBounds(12, 12, 208, 16);
		frmImageProcessor.getContentPane().add(lblBasicOperation);

		JLabel lblHighPassFilter = new JLabel("Yuksek Gecis Filterleri");
		lblHighPassFilter.setForeground(new Color(255, 255, 255));
		lblHighPassFilter.setBounds(232, 12, 208, 16);
		frmImageProcessor.getContentPane().add(lblHighPassFilter);

		JLabel lblLowPassFilter = new JLabel("Alcak Gecis Filtreleri");
		lblLowPassFilter.setForeground(new Color(255, 255, 255));
		lblLowPassFilter.setBounds(451, 12, 208, 16);
		frmImageProcessor.getContentPane().add(lblLowPassFilter);

		JLabel lblMorhpological = new JLabel("Morfolojik İslemler");
		lblMorhpological.setForeground(new Color(255, 255, 255));
		lblMorhpological.setBounds(899, 12, 131, 16);
		frmImageProcessor.getContentPane().add(lblMorhpological);

		JLabel lblSharpening = new JLabel("Netlestirme");
		lblSharpening.setForeground(new Color(255, 255, 255));
		lblSharpening.setBounds(677, 12, 208, 16);
		frmImageProcessor.getContentPane().add(lblSharpening);

		JLabel lblPerspectiveCorrection = new JLabel("Perspektif İslemleri");
		lblPerspectiveCorrection.setForeground(new Color(255, 255, 255));
		lblPerspectiveCorrection.setBounds(1048, 12, 131, 16);
		frmImageProcessor.getContentPane().add(lblPerspectiveCorrection);

		JLabel lblGeometricOperation = new JLabel("Geometrik İslemler");
		lblGeometricOperation.setForeground(new Color(255, 255, 255));
		lblGeometricOperation.setBounds(1191, 12, 172, 16);
		frmImageProcessor.getContentPane().add(lblGeometricOperation);

		lblBackground = new JLabel("background");
		lblBackground.setForeground(SystemColor.info);
		lblBackground.setBackground(SystemColor.info);
		lblBackground.setIcon(new ImageIcon("systemImg//wallpaperflare.com_wallpaper.jpg"));
		lblBackground.setBounds(0, 0, 1540, 836);
		frmImageProcessor.getContentPane().add(lblBackground);
	}

	private JFrame createMainFrame(JFrame frmImageProcessor) {

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

		return frmImageProcessor;
	}

	private void populateCbHighPassFilters() {

		cbHighPassFilters.addItem("İslem Sec");
		cbHighPassFilters.addItem("Laplacian");
		cbHighPassFilters.addItem("Sobel");
		cbHighPassFilters.addItem("Prewitt");
	}

	private void populateCbLowPassFilters() {

		cbLowPassFilters.addItem("İslem Sec");
		cbLowPassFilters.addItem("Low Pass Filter");
		cbLowPassFilters.addItem("Gaussian Filter");
		cbLowPassFilters.addItem("Mean");
		cbLowPassFilters.addItem("Median");
	}

	private void populateCbPerspective() {

		cbPerspective.addItem("İslem Sec");
		cbPerspective.addItem("Perspektif Düzelt");
	}

	private void populateCbGeometric() {

		cbGeometric.addItem("İslem Sec");
		cbGeometric.addItem("Döndür");
		cbGeometric.addItem("Ters Cevir");
		cbGeometric.addItem("Aynala");
		cbGeometric.addItem("Otele");
		cbGeometric.addItem("Yakinlastir");
		cbGeometric.addItem("Uzaklastir");
		cbGeometric.addItem("Yakinlastir2");
	}

	private void populateCbImageSharpening() {

		cbImageSharpening.addItem("İslem Sec");
		cbImageSharpening.addItem("Kenar Görüntüsü İle Netleştirme");
		cbImageSharpening.addItem("Konvolüsyon Yöntemi");
	}

	private void populateCbMorphological() {

		cbMorphological.addItem("İslem Sec");
		cbMorphological.addItem("Siyah rengi yay");
		cbMorphological.addItem("Beyaz rengi yay");
		cbMorphological.addItem("Beyaz rengi aşındır");
		cbMorphological.addItem("Siyah rengi aşındır");
		cbMorphological.addItem("Beyaz Açma");
		cbMorphological.addItem("Siyah Açma");
		cbMorphological.addItem("Beyaz Kapama");
		cbMorphological.addItem("Siyah Kapama");
	}

	private void populateCbBasicOperations() {

		cbBasicOperations.addItem("İslem Sec");
		cbBasicOperations.addItem("Gri Tonluya Cevir");
		cbBasicOperations.addItem("Negatif");
		cbBasicOperations.addItem("Thresholding");
		cbBasicOperations.addItem("Kontrast Ayarlama");
		cbBasicOperations.addItem("Kontrast Germe");
		cbBasicOperations.addItem("Histogram Dengeleme");
		cbBasicOperations.addItem("Histogramı Cizdir");
		cbBasicOperations.addItem("Parlaklık Ayarla");
		cbBasicOperations.setBounds(12, 27, 212, 21);
	}

	private void readImage() {

		try {
			JFileChooser chooser = new JFileChooser("img//");
			chooser.showOpenDialog(null);
			File file = chooser.getSelectedFile();
			inputImg = new File(file.getAbsolutePath());
			firstImage = ImageIO.read(inputImg);
			lblFirstImg.setIcon(new ImageIcon(firstImage));
		} catch (NullPointerException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(frmImageProcessor, "Resim Secmedidiniz!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void applyChange(BufferedImage image1, BufferedImage image2) {

		lblFirstImg.setIcon(new ImageIcon(image1));
		lblSecondImage.setIcon(new ImageIcon(image2));
	}

	private void clearComboBox(JComboBox<String> comboBox, int index) {

		// This control is to prevent the program from looping with the itemstatechanged
		// listener.
		if (isClearingComboBox) {
			return;
		}

		isClearingComboBox = true;

		cbBasicOperations.setSelectedIndex(0);
		cbLowPassFilters.setSelectedIndex(0);
		cbHighPassFilters.setSelectedIndex(0);
		cbMorphological.setSelectedIndex(0);
		cbPerspective.setSelectedIndex(0);
		cbImageSharpening.setSelectedIndex(0);
		cbGeometric.setSelectedIndex(0);

		comboBox.setSelectedIndex(index);

		isClearingComboBox = false;
	}
}