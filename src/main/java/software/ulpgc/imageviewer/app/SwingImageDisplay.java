package software.ulpgc.imageviewer.app;

import software.ulpgc.imageviewer.io.ImageDeserializer;
import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private final ImageDeserializer deserializer;
    private Image image;

    public SwingImageDisplay(ImageDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    @Override
    public void show(Image image) {
        this.image = image;
        this.repaint();
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        drawImage(g);
    }

    private void drawImage(Graphics g) {
        java.awt.Image image = deserialze();
        Dimension scaledSize = fitToSize(image);
        // TODO refactor this method
        int x = (this.getWidth() - scaledSize.width) / 2;
        int y = (this.getHeight() - scaledSize.height) / 2;
        g.drawImage(image, x, y, scaledSize.width, scaledSize.height, null);
    }

    private Dimension fitToSize(java.awt.Image image) {
        int originalWidth = image.getWidth(null);
        int originalHeight = image.getHeight(null);

        double panelWidth = this.getWidth();
        double panelHeight = this.getHeight();

        double scale = Math.min(panelWidth / originalWidth, panelHeight / originalHeight);

        int scaledWidth = (int) (scale * originalWidth);
        int scaledHeight = (int) (scale * originalHeight);

        return new Dimension(scaledWidth, scaledHeight);
    }

    private java.awt.Image deserialze() {
        return (java.awt.Image) deserializer.deserialize(image.content());
    }
}
