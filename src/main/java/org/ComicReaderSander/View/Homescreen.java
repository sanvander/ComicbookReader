package org.ComicReaderSander.View;

import org.ComicReaderSander.Controller.Controller;
import org.ComicReaderSander.Model.Comic;
import org.ComicReaderSander.Model.ComicLibrary;

import javax.swing.*;
import java.awt.*;


public class Homescreen extends JPanel {
    private Controller controller;

    public Homescreen(Controller controller) {
        ComicLibrary comicLibrary = new ComicLibrary();
        this.controller = controller;

        setBackground(new Color(33, 33, 33, 255));
        setLayout(new FlowLayout(FlowLayout.LEFT));

        //Code for the import button
        add(addImportButton());

        //Code for the list of comics
        add(addComicList());

        //Code for the delete all Button
        add(addDeleteAllButton());

        //Code for the rectangles which in the future will be comics, for now just a placeholder
        /*for (int i = 0; i < 50; i++) {
            JPanel rectangle = new JPanel();
            rectangle.setPreferredSize(new Dimension(100, 150));
            rectangle.setBackground(Color.GRAY);
            rectangle.setLayout(new BorderLayout());

            JLabel nameLabel = new JLabel("Comic " + (i + 1), SwingConstants.CENTER);
            rectangle.add(nameLabel, BorderLayout.SOUTH);

            add(rectangle);
        }*/
    }

    public JButton addImportButton() {
        JButton importButton = new JButton("Import");
        importButton.setPreferredSize(new Dimension(100, 150));
        importButton.addActionListener(e -> controller.handleImportComic());
        return importButton;
    }

    public JButton comicButton(Comic comic){
        JButton button = new JButton(comic.getName());
        button.setPreferredSize(new Dimension(1000, 30));
        button.addActionListener(e -> controller.handleOpenComic(comic));
        return button;
    }

    public JScrollPane addComicList() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(1300, 700));
        panel.setBackground(new Color(33, 33, 33, 255));

       for (Comic comic : controller.handleGetAllComics()) {
            JButton button = comicButton(comic);
            panel.add(button);
            panel.add(addDeleteComicButton(comic));
       }

        JScrollPane scrollPane = new JScrollPane(panel);

        return scrollPane;

    }

    public JButton addDeleteAllButton() {
        JButton deleteAllButton = new JButton("Delete All");
        deleteAllButton.setPreferredSize(new Dimension(100, 30));
        deleteAllButton.setBackground(Color.RED);
        deleteAllButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete all comics?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (response == JOptionPane.YES_OPTION) {
                controller.handleDeleteAllComics();
            }
        });
        return deleteAllButton;
    }

    public JButton addDeleteComicButton(Comic comic) {
        JButton deleteComicButton = new JButton("Delete");
        deleteComicButton.setPreferredSize(new Dimension(100, 30));
        deleteComicButton.setBackground(Color.RED);
        deleteComicButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this comic?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (response == JOptionPane.YES_OPTION) {
                controller.handleDeleteComic(comic);
            }
        });
        return deleteComicButton;
    }


}
