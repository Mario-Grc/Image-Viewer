package software.ulpgc.imageviewer.app;

import software.ulpgc.imageviewer.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ImageDisplay imageDisplay;

    public MainFrame() {
        this.setTitle("ImageViewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(newImageDisplay());
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }

    private SwingImageDisplay newImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay(new SwingImageDeserializer());
        this.imageDisplay = display;
        return display;
    }
}
