package org.ComicReaderSander.Model;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ComicLibrary implements IComicLibraryServices {

	public ComicLibrary(){

	}

	public List<Comic> getAllComics() {
		List<Comic> allComics = new ArrayList<Comic>();
        allComics.addAll(ComicNHLComicRepo.getAllComics());
		allComics.addAll(ComicCBZRepo.getAllComics());
		allComics.addAll(ComicCBRRepo.getAllComics());
		return allComics;
	}

	public void deleteComic(Comic comic) {
		deleteComicFromDB(comic);
		deleteComicFromDirectory(comic);
	}

	public void deleteComicFromDB(Comic comic) {
		if (comic.getFileType().equals("CBZ")) {
			ComicCBZRepo.deleteComic(comic);
		} else if (comic.getFileType().equals("CBR")) {
			ComicCBRRepo.deleteComic(comic);
		} else if (comic.getFileType().equals("NHLComic")) {
			ComicNHLComicRepo.deleteComic(comic);
		}
	}

	public void deleteComicFromDirectory(Comic comic) {
		Path path = Paths.get(comic.getFilePath());
		try {
			deleteDirectoryRecursively(path);
			System.out.println("Directory " + path + " deleted successfully");
		} catch (IOException e) {
			System.out.println("Error occurred: " + e.getMessage());
		}
	}

	public void deleteAllComics() {
		deleteAllFromDB();
		deleteAllFromDirectory();
	}

	private void deleteAllFromDirectory() {
		Path[] paths = {
				Paths.get("src/main/resources/Comics/CBR"),
				Paths.get("src/main/resources/Comics/CBZ"),
				Paths.get("src/main/resources/Comics/NHLcomic")
		};

		for (Path path : paths) {
			try {
				deleteContentsOnly(path);
				System.out.println("Contents of directory " + path + " deleted successfully");
			} catch (IOException e) {
				System.out.println("Error occurred: " + e.getMessage());
			}
		}
	}

	public static void deleteContentsOnly(Path directory) throws IOException {
		if (Files.isDirectory(directory)) {
			try (DirectoryStream<Path> entries = Files.newDirectoryStream(directory)) {
				for (Path entry : entries) {
					deleteDirectoryRecursively(entry);
				}
			}
		}
	}

	public static void deleteDirectoryRecursively(Path path) throws IOException {
		if (Files.isDirectory(path)) {
			try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
				for (Path entry : entries) {
					deleteDirectoryRecursively(entry);
				}
			}
		}
		Files.delete(path);
	}

	private void deleteAllFromDB(){
		ComicNHLComicRepo.deleteAllComics();
		ComicCBZRepo.deleteAllComics();
		ComicCBRRepo.deleteAllComics();
	}

}
