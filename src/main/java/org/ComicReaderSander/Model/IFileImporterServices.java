package org.ComicReaderSander.Model;

import javax.swing.*;
import java.io.File;

public interface IFileImporterServices {
    void importFile();
    File createMapForComic(File file);
    String formatFileName(String name);
    JFileChooser createFileChooser();
    String getFileType(File file);
    void handleNHLcomicFile(File file);
    void handleCBZFile(File file);
    void handleCBRFile(File file);

}
