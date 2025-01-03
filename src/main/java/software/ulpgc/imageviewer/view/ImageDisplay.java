package software.ulpgc.imageviewer.view;

import java.awt.image.BufferedImage;

public interface ImageDisplay {
    int width();

    void paint(int offset, int width, int height, BufferedImage content);
    void clear();

    void on(Shift shift);
    void on(Release release);

    interface Shift {
        Shift Null = _ -> {};
        void offset(int offset);
    }

    interface Release {
        Release Null = _ -> {};
        void offset(int offset);
    }


}
