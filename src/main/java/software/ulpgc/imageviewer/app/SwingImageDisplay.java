package software.ulpgc.imageviewer.app;

import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image image;

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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        drawImage(g);
    }

    private void drawImage(Graphics g) {
        Dimension scaledSize = fitToSize(image);

        int x = (this.getWidth() - scaledSize.width) / 2;
        int y = (this.getHeight() - scaledSize.height) / 2;

        g.drawImage(image.content(), x, y, scaledSize.width, scaledSize.height, null);
    }

    private Dimension fitToSize(Image image) {
        int originalWidth = image.content().getWidth(null);
        int originalHeight = image.content().getHeight(null);

        double scale = calculateScale(originalWidth, originalHeight);

        int scaledWidth = (int) (scale * originalWidth);
        int scaledHeight = (int) (scale * originalHeight);

        return new Dimension(scaledWidth, scaledHeight);
    }

    private double calculateScale(int originalWidth, int originalHeight) {
        double panelWidth = this.getWidth();
        double panelHeight = this.getHeight();
        return Math.min(panelWidth / originalWidth, panelHeight / originalHeight);
    }

}
