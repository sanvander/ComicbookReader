package org.ComicReaderSander.Model;
import java.io.*;
import java.util.zip.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ComicGIF extends Comic {

	public void parseComic() {
		String filePath = "src/main/resources/Comics/pepper&carrot_1.nhlcomic";

		try (FileInputStream fis = new FileInputStream(filePath)) {
			int byteData;
			int x = 0;
			while (x < 100) {
				x++;
				byteData = fis.read();
				System.out.printf("%02X ", byteData);
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void extractZip() {
		String zipFilePath = "src/main/resources/Comics/pepper&carrot_1.nhlcomic";
		String destDir = "src/main/resources/Comics";
		try (FileInputStream fis = new FileInputStream(zipFilePath);
			 ZipInputStream zis = new ZipInputStream(fis)) {

			ZipEntry zipEntry;
			while ((zipEntry = zis.getNextEntry()) != null) {
				File outFile = new File(destDir + zipEntry.getName());
				new File(outFile.getParent()).mkdirs(); // Zorg dat de output directory bestaat

				try (FileOutputStream fos = new FileOutputStream(outFile)) {
					byte[] buffer = new byte[1024];
					int length;
					while ((length = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, length);
					}
				}

				zis.closeEntry();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
