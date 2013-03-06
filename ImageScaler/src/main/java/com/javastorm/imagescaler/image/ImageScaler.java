package com.javastorm.imagescaler.image;

import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.javastorm.imagescaler.common.OwnerDetails;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * This class is intended for scaling of images 
 * 
 * @author Hemant Kumar
 * @version 1.0 Dated: 01/03/2013
 */
public class ImageScaler extends WindowAdapter implements ActionListener, ItemListener
{
	JFrame frame = new JFrame("ImageScaler......By Hemant Kumar");
	JLabel origWidthHeightLabel = new JLabel("Retain original width and height    ");
	Choice origWidthHeightChoice = new Choice();
	JPanel panelWidth = new JPanel();
	JLabel newWidthLabel = new JLabel("  New Image Width                             ");
	Choice newWidthChoice = new Choice();
	JPanel panelHeight = new JPanel();
	JLabel newHeightLabel = new JLabel("  New Image Height                            ");
	Choice newHeightChoice = new Choice();
	JPanel panelQuality = new JPanel();
	JLabel newQualityLabel = new JLabel("New Image Quality                            ");
	Choice newQualityChoice = new Choice();
	JPanel panelMultipleYN = new JPanel();
	JLabel newMultipleYNLabel = new JLabel("Do Same For Neighbour files also");
	Choice newMultipleYNChoice = new Choice();
	JPanel panelAspectRatioYN = new JPanel();
	JLabel newAspectRatioYNLabel = new JLabel("Maintain Aspect Ratio                       ");
	Choice newAspectRatioYNChoice = new Choice();
	TextField imageName = new TextField(28);
	JButton btmBrowse = new JButton("  Browse..  ");
	JButton btmOk = new JButton("      OK       ");
	Frame frameDialog;
	FileDialog fileDialog;
	JLabel destinationLabel = new JLabel("Scaled images destination folder is ~/ScaledImage");
	JLabel imageTypeLabel = new JLabel("  Include following Image types for this operation :");
	Checkbox jpgCheckBox = new Checkbox("JPEG      ");
	Checkbox pngCheckBox = new Checkbox("PNG       ");
	Checkbox gifCheckBox = new Checkbox("GIF       ");
	Checkbox bmpCheckBox = new Checkbox("BMP       ");

	public void setDefaults() {
		origWidthHeightChoice.select(0);
		origWidthHeightChoice.setEnabled(true);
		newWidthChoice.select("600");
		newWidthChoice.setEnabled(false);
		newHeightChoice.select("400");
		newHeightChoice.setEnabled(false);
		newQualityChoice.select("50");
		newQualityChoice.setEnabled(true);
		newMultipleYNChoice.select(0);
		newMultipleYNChoice.setEnabled(true);
		newAspectRatioYNChoice.select(0);
		newAspectRatioYNChoice.setEnabled(true);
		imageName.setText("");
		jpgCheckBox.setState(true);
		jpgCheckBox.setEnabled(true);
		pngCheckBox.setState(true);
		pngCheckBox.setEnabled(true);
		gifCheckBox.setState(true);
		gifCheckBox.setEnabled(true);
		bmpCheckBox.setState(true);
		bmpCheckBox.setEnabled(true);
		btmBrowse.setEnabled(true);
		btmOk.setEnabled(false);
		frame.setCursor(new Cursor(12));
	}

	@Override
	public void windowClosing(WindowEvent w) {
		System.exit(0);
	}

	private void launchFrame() {
		frame.setBounds(550,140,350,440);
		frame.setCursor(new Cursor(12));
		frame.setIconImage(OwnerDetails.getOwnerLogo());
		frame.setLayout(new FlowLayout());
		frame.setResizable(false);
		frame.addWindowListener(this);
		frame.add(new JLabel("                                                                       "));
		frame.add(origWidthHeightLabel);
		origWidthHeightChoice.add("Yes");
		origWidthHeightChoice.add("No");
		origWidthHeightChoice.addItemListener(this);
		frame.add(origWidthHeightChoice);
		panelWidth.add(newWidthLabel);
		for(int i=50;i<=1600;i=i+50)
			newWidthChoice.add(i+"");
		newWidthChoice.select("600");
		newWidthChoice.setEnabled(false);
		panelWidth.add(newWidthChoice);
		frame.add(panelWidth);
		panelHeight.add(newHeightLabel);
		for(int i=50;i<=1400;i=i+50)
			newHeightChoice.add(i+"");
		newHeightChoice.select("400");
		newHeightChoice.setEnabled(false);
		panelHeight.add(newHeightChoice);
		frame.add(panelHeight);
		panelQuality.add(newQualityLabel);
		for(int i=5;i<=100;i=i+5)
			newQualityChoice.add(i+"");
		newQualityChoice.select("50");
		panelQuality.add(newQualityChoice);
		frame.add(panelQuality);
		panelMultipleYN.add(newMultipleYNLabel);
		newMultipleYNChoice.add("No");
		newMultipleYNChoice.add("Yes");
		panelMultipleYN.add(newMultipleYNChoice);
		frame.add(panelMultipleYN);
		panelAspectRatioYN.add(newAspectRatioYNLabel);
		newAspectRatioYNChoice.add("Yes");
		newAspectRatioYNChoice.add("No");
		panelAspectRatioYN.add(newAspectRatioYNChoice);
		frame.add(panelAspectRatioYN);
		imageName.setEnabled(false);
		frame.add(imageName);
		frame.add(btmBrowse);
		btmBrowse.addActionListener(this);
		frame.add(imageTypeLabel);
		jpgCheckBox.setState(true);
		jpgCheckBox.addItemListener(this);
		frame.add(jpgCheckBox);
		pngCheckBox.setState(true);
		pngCheckBox.addItemListener(this);
		frame.add(pngCheckBox);
		gifCheckBox.setState(true);
		gifCheckBox.addItemListener(this);
		frame.add(gifCheckBox);
		bmpCheckBox.setState(true);
		bmpCheckBox.addItemListener(this);
		frame.add(bmpCheckBox);
		frame.add(btmOk);
		btmOk.addActionListener(this);
		btmOk.setEnabled(false);
		frame.add(destinationLabel);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("  Browse..  ")) {
			frameDialog =new Frame();
			fileDialog=new FileDialog(frameDialog,"Open",FileDialog.LOAD);
			fileDialog.setVisible(true);
			String filename = fileDialog.getFile() != null ? fileDialog.getFile().toUpperCase() : "";
			if(fileDialog.getDirectory() != null && checkExtension(filename)) {
				imageName.setText(fileDialog.getDirectory() + fileDialog.getFile());
				btmOk.setEnabled(true);
			}
			else {
				imageName.setText("");
				btmOk.setEnabled(false);
			}
			frameDialog.dispose();
		}
		else if(e.getActionCommand().equals("      OK       ")) {
			frame.setCursor(new Cursor(3));
			origWidthHeightChoice.setEnabled(false);
			newWidthChoice.setEnabled(false);
			newHeightChoice.setEnabled(false);
			newQualityChoice.setEnabled(false);
			newMultipleYNChoice.setEnabled(false);
			newAspectRatioYNChoice.setEnabled(false);
			btmBrowse.setEnabled(false);
			jpgCheckBox.setEnabled(false);
			pngCheckBox.setEnabled(false);
			gifCheckBox.setEnabled(false);
			bmpCheckBox.setEnabled(false);
			Calendar c = Calendar.getInstance();
			String destFolderName = "~/ScaledImage/" + c.get(Calendar.DATE) + "-" + (c.get(Calendar.MONTH)+1) + "-" +
					(c.get(Calendar.YEAR)+1900) + " [" + c.get(Calendar.HOUR)+ "h" +c.get(Calendar.MINUTE) + "m]";
			(new File(destFolderName)).mkdirs();
			if(newMultipleYNChoice.getSelectedItem().equalsIgnoreCase("Yes")) {
				File file = new File(fileDialog.getDirectory());
				String fileList[] = file.list();
				for(int i=0;i<fileList.length;i++)
				{
					String filename = fileList[i] != null ? fileList[i].toUpperCase() : "";
					if(checkExtension(filename)) {
						scaleImage(fileDialog.getDirectory() + fileList[i], destFolderName + "\\" + fileList[i]);
					}
				}
			}
			else {
				File file = new File(imageName.getText());
				if(file.exists()) {
					String filename = fileDialog.getFile() != null ? fileDialog.getFile().toUpperCase() : "";
					if(checkExtension(filename)) {
						scaleImage(imageName.getText(),destFolderName+"\\"+fileDialog.getFile());
					}
				}
			}
			setDefaults();
		}
	}

	private void scaleImage(String src,String dest) {
		try {
			FileInputStream fis = new FileInputStream(new File(src));
			InputStream is = new BufferedInputStream(fis);
			Image image =(Image)ImageIO.read(is);
			int width,height;
			if(origWidthHeightChoice.getSelectedItem().equalsIgnoreCase("Yes")) {
				width = image.getWidth(null);
				height = image.getHeight(null);
			} else {
				width = Integer.parseInt(newWidthChoice.getSelectedItem());
				height = Integer.parseInt(newHeightChoice.getSelectedItem());
			}
			if(newAspectRatioYNChoice.getSelectedItem().equalsIgnoreCase("Yes")) {
				double ratio = (double)width/(double)height;
				int origWidth = image.getWidth(null);
				int origHeight = image.getHeight(null);
				double origRatio = (double)origWidth/(double)origHeight;
				if(ratio<origRatio) {
					height = (int)((double)width/origRatio);
				} else {
					width = (int)(height * origRatio);
				}
			}
			BufferedImage bufferedImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = bufferedImage.createGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			graphics2D.drawImage(image,0,0,width,height,null);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
			int quality = Integer.parseInt(newQualityChoice.getSelectedItem());
			quality = Math.max(0,Math.min(quality,100));
			param.setQuality((float)quality/100.0f,false);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(bufferedImage);
			ImageIO.write(bufferedImage,"Image_JPG",out);
			FileOutputStream fos = new FileOutputStream(dest);
			fos.write(out.toByteArray());
			fis.close();
			fos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void itemStateChanged(ItemEvent e)
	{
		String filename = imageName.getText() != null ? imageName.getText().toUpperCase() : "";
		if(!filename.equalsIgnoreCase("")) {
			if(checkExtension(filename)) {
				imageName.setText("");
				btmOk.setEnabled(false);
			}
		}
		if(origWidthHeightChoice.getSelectedItem().equalsIgnoreCase("Yes")) {
			newWidthChoice.select("600");
			newWidthChoice.setEnabled(false);
			newHeightChoice.select("400");
			newHeightChoice.setEnabled(false);
		}
		if(origWidthHeightChoice.getSelectedItem().equalsIgnoreCase("No")) {
			newWidthChoice.setEnabled(true);
			newHeightChoice.setEnabled(true);
		}
	}

	private boolean checkExtension(String filename) {
		if((filename.endsWith(".JPG") && jpgCheckBox.getState()) || (filename.endsWith(".JPEG") && jpgCheckBox.getState())
				||(filename.endsWith(".PNG") && pngCheckBox.getState())||(filename.endsWith(".GIF") && gifCheckBox.getState())
				||(filename.endsWith(".BMP") && bmpCheckBox.getState())) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String args[]) throws Exception {
		UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		new ImageScaler().launchFrame();
	}
}