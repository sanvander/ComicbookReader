package org.ComicReaderSander.Model;
import javax.swing.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;




public class FileImporter {

    public FileImporter() {
    }

    // TODO: handleCBR
    public static void importFile() {
        // make a filechooser with selfmade function
        JFileChooser fileChooser = createFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileType = getFileType(selectedFile);

            if (fileType.equals("nhlcomic")) {
                handleNHLcomicFile(selectedFile);
            } else if (fileType.equals("cbz")) {
                handleCBZFile(selectedFile);
            }



        } else {
            System.out.println("No files selected");

        }
    }

    /**
     * Creates a directory for a comic based on its name.
     * This method calls the function to format the name of the comic file,
     * and then creates a directory with the formatted name in the
     * `src/main/resources/Comics` directory.
     * Every comic will have his own map because some filetypes consist of multiple files.
     * nhlcomic for example also contains a json file with the metadata of the comic.
     * returns the created directory path so it can be used to add the comic to the map later
     *
     * @param file The comic file
     * @return A `File` object representing the created directory.
     */
    public static File createMapForComic(File file) {
        File comicDir;
        // format name with selfmade function
        String formattedName = formatFileName(file.getName());

        String fileType = getFileType(file);
        if (fileType.equals("nhlcomic")) {
            comicDir = new File(String.format("src/main/resources/Comics/NHLcomic/%s", formattedName));
        } else if (fileType.equals("cbz")) {
            comicDir = new File(String.format("src/main/resources/Comics/CBZ/%s", formattedName));
        } else {
            comicDir = new File(String.format("src/main/resources/Comics/CBR/%s", formattedName));
        }
        boolean directoryCreated = comicDir.mkdir();


        if (directoryCreated) {
            System.out.println("Directory created");
        } else {
            JOptionPane.showMessageDialog(null, "Comic with this title has already been added",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return comicDir;


    }

    /**
     * Removes the file extension from a filename.
     * This method finds the last . in the given filename and removes everything after it,
     * including the . itself. If no . is present, it returns the original filename.
     * This is to be able to create a directory with the name of the comic without the extension.
     *
     * @param name The original filename, including extension.
     * @return The filename without the extension.
     */
    public static String formatFileName(String name) {
        String strippedName;
        // get the index of the last . in the file name
        int dotIndex = name.lastIndexOf(".");
        // remove the extension from the name
        // if for some reason no . is in the filename (shouldn't happen i think) but is possible when debugging it gets skipped
        if (dotIndex != -1) {
            strippedName = name.substring(0, dotIndex);
        } else {
            strippedName = name;
        }
        return strippedName;
    }

    /**
     * Creates and configures a JFileChooser (FileExplorer) with a specific file filter.
     * This method sets up a JFileChooser that only displays files of specified filetypes:
     * .CBZ, .CBR, and .nhlcomic.
     *
     * @return A JFileChooser instance that displays only files of specified filetypes.
     */
    public static JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        // Create a filter to only show files of the specified filetypes
        // After that add the filter to the filechooser
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(null, "CBZ", "CBR", "nhlcomic");
        fileChooser.setFileFilter(imageFilter);
        return fileChooser;
    }

    /**
     * Gets the file extension from a given file.
     * This method extracts the file extension from the provided `File` object.
     * It finds the last dot (.) in the file name and returns the substring
     * following the dot as the file extension.
     *
     * @param file The `File` object from which to extract the file extension.
     * @return A `String` representing the file extension, or an empty string if no extension is found.
     */
    private static String getFileType(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        return fileName.substring(dotIndex + 1);
    }

    public static void handleNHLcomicFile(File file){
        File mapForAddedComic = createMapForComic(file);
        ComicNHLComic comic = new ComicNHLComic(
                formatFileName(file.getName()),
                0, //TODO: create method that returns pages
                0,
                mapForAddedComic.toString());
        comic.extractToDirectory(file, mapForAddedComic);
        System.out.println(comic.getName());
        ComicNHLComicRepo.insertComic(comic);

    }

    public static void handleCBZFile(File file){
        File mapForAddedComic = createMapForComic(file);
        ComicCBZ comic = new ComicCBZ(
                formatFileName(file.getName()),
                0,
                0,
                mapForAddedComic.toString());
        comic.extractToDirectory(file, mapForAddedComic);
        ComicCBZRepo.insertComic(comic);

    }




}
