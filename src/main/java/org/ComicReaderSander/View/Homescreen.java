package org.ComicReaderSander.View;

import org.ComicReaderSander.Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homescreen extends JPanel {
    private Controller controller;

    public Homescreen() {
        this.controller = new Controller();

        setBackground(new Color(33, 33, 33, 255));
        setLayout(new FlowLayout(FlowLayout.LEFT));

        //Code for the import button
        JButton importButton = addImportButton();
        add(importButton);

        //Code for the rectangles which in the future will be comics, for now just a placeholder
        for (int i = 0; i < 50; i++) {
            JPanel rectangle = new JPanel();
            rectangle.setPreferredSize(new Dimension(100, 150));
            rectangle.setBackground(Color.GRAY);
            rectangle.setLayout(new BorderLayout());

            JLabel nameLabel = new JLabel("Comic " + (i + 1), SwingConstants.CENTER);
            rectangle.add(nameLabel, BorderLayout.SOUTH);

            add(rectangle);
        }
    }

    public JButton addImportButton() {
        JButton importButton = new JButton("Import");
        importButton.setPreferredSize(new Dimension(100, 150));
        importButton.addActionListener(e -> controller.handleImportComic());
        return importButton;
    }


}
