package software.ulpgc.imageviewer.control;

import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.view.ImageDisplay;

public class ImagePresenter {
    private final ImageDisplay display;
    private Image image;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
        this.display.on((ImageDisplay.Shift) this::shift);
        this.display.on((ImageDisplay.Release) this::released);
    }

    public Image getImage() {
        return image;
    }

    private void shift(int offset) {
        updateDisplay(image, offset);
        if (offset > 0)
            paintNextImage(offset, image);
        else
            paintPreviousImage(offset, image);
    }

    private void paintPreviousImage(int offset, Image image) {
        display.paint(offset + display.width(), image.next().content().getHeight(), image.next().content().getWidth(), image.next().content());
    }

    private void paintNextImage(int offset, Image image) {
        display.paint(offset - display.width(), image.previous().content().getHeight(), image.previous().content().getWidth(), image.previous().content());
    }


    private void released(int offset) {
        if (Math.abs(offset) >= display.width() / 2) {
            image = offset > 0 ? image.previous() : image.next();
        }
        updateDisplay(image, 0);
    }

    private void updateDisplay(Image image, int offset) {
        display.clear();
        display.paint(offset, image.content().getWidth(), image.content().getHeight(), image.content());
    }

    public void show(Image image) {
        this.image = image;
        updateDisplay(image, 0);
    }
}
