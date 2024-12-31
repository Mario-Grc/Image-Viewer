package software.ulpgc.imageviewer.model;

import java.awt.image.BufferedImage;

public interface Image {
    String name();
    Image next();
    Image previous();
    BufferedImage content();
}
