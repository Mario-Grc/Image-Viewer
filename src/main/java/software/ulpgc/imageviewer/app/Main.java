package software.ulpgc.imageviewer.app;

import software.ulpgc.imageviewer.io.FileImageLoader;
import software.ulpgc.imageviewer.model.Image;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Image image = new FileImageLoader(new File("G:/Otros ordenadores/Mi portátil/Año_3/1ºSemestre/IS2/imagenes")).load();
        frame.getImageDisplay().show(image);
        frame.setVisible(true);
    }

}
