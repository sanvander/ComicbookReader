package org.ComicReaderSander.Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

import javax.imageio.ImageIO;

public class ComicCBR extends Comic {

	public ComicCBR(int ComicID, String Name, int NumberOfPages, int DisplayedPage, String FilePath) {
		super(ComicID, Name, NumberOfPages, DisplayedPage, FilePath, "CBR");
	}

	public ComicCBR(String Name, int NumberOfPages, int DisplayedPage, String FilePath) {
		super(Name, NumberOfPages, DisplayedPage, FilePath, "CBR");
	}

	public void parseComic() {

	}

	public void extractToDirectory(File file, File destDir) {
		try (Archive archive = new Archive(file)) {
			FileHeader fileHeader;
			while ((fileHeader = archive.nextFileHeader()) != null) {
				File outFile = new File(destDir, fileHeader.getFileNameString().trim());
				// Ensure parent directories exist
				File parentDir = outFile.getParentFile();
				if (parentDir != null && !parentDir.exists()) {
					parentDir.mkdirs();
				}
				try (FileOutputStream fos = new FileOutputStream(outFile)) {
					archive.extractFile(fileHeader, fos);
				}
			}
		} catch (IOException | RarException e) {
			e.printStackTrace();
		}
	}



	public List<BufferedImage> getFrames(Comic comic){
		File directoryToGetFramesFrom = new File(comic.getFilePath());
		System.out.println(directoryToGetFramesFrom);
		File[] files = directoryToGetFramesFrom.listFiles();
		if (files != null && files.length == 1 && files[0].isDirectory()) {
			files = files[0].listFiles();
		}
		for (File file : files) {
			System.out.println(file);
		}
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

	//method that replaces - with space
	public String formatFileName(String fileName) {
		return fileName.replaceAll("-", " ");
	}

}
