package org.ComicReaderSander.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ComicCBZ extends Comic {

	public ComicCBZ(int ComicID, String Name, int NumberOfPages, int DisplayedPage, File FilePath) {
		super(ComicID, Name, NumberOfPages, DisplayedPage, FilePath, "CBZ");
	}

	public void parseComic() {

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

}
