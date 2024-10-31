package org.ComicReaderSander.Model;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

public class ComicNHLComic extends Comic {
	private int currentFrameIndex;
	private JLabel gifLabel;
	private List<BufferedImage> frames = new ArrayList<>();

	public ComicNHLComic(int ComicID, String Name, int NumberOfPages, int DisplayedPage, String FilePath) {
		super(ComicID, Name, NumberOfPages, DisplayedPage, FilePath, "NHLComic");
	}
	public ComicNHLComic(String Name, int NumberOfPages, int DisplayedPage, String FilePath) {
		super(Name, NumberOfPages, DisplayedPage, FilePath, "NHLComic");
	}


	public void parseComic() {
		try {
			List<BufferedImage> frames2 = extractFrames(getGifPath(this.getFilePath()));
			Collections.reverse(frames2);
			for (int i = 0; i < frames2.size(); i++) {
				File outFile = new File(this.getFilePath(), "frame_" + i + ".jpg");
				try (FileOutputStream fos = new FileOutputStream(outFile)) {
					ImageIO.write(frames2.get(i), "jpg", fos);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void extractToDirectory(File file, File destDir) {
		try (FileInputStream fileInputStream = new FileInputStream(file);
			 ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);) {

			ZipEntry zipEntry = null;
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				File outFile = new File(destDir, zipEntry.getName());
				try (FileOutputStream fos = new FileOutputStream(outFile)) {
					byte[] buffer = new byte[1024];
					int length;
					while ((length = zipInputStream.read(buffer)) > 0) {
						fos.write(buffer, 0, length);
					}

				}

				zipInputStream.closeEntry();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<BufferedImage> extractFrames(File gifFile) throws IOException {
		ImageInputStream stream = ImageIO.createImageInputStream(gifFile);
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("gif");

		if (!readers.hasNext()) {
			throw new IOException("No GIF readers found");
		}

		ImageReader reader = readers.next();
		reader.setInput(stream);

		int numFrames = reader.getNumImages(true);
		for (int i = 0; i < numFrames; i++) {
			frames.add(reader.read(i));
		}


		stream.close();
		return frames;

	}

	private Image scaleImage(BufferedImage image, int width, int height) {
		return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	private static File getGifPath(String directoryPath){
		File directoryToExtractTo = new File(directoryPath);
		File[] files = directoryToExtractTo.listFiles();
		for (File file : files) {
			if (file.getName().endsWith(".gif")) {
				return file;
			}
		}
		return null;
	}

	public List<BufferedImage> getFrames(Comic comic){
		File directoryToGetFramesFrom = new File(comic.getFilePath());
		File[] files = directoryToGetFramesFrom.listFiles();
		List<BufferedImage> frames = new ArrayList<>();
		for (File file : files) {
			if (file.getName().endsWith(".jpg")) {
				try {
					frames.add(ImageIO.read(file));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return frames;
	}



}
