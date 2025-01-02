package software.ulpgc.imageviewer.app;

import software.ulpgc.imageviewer.control.ImagePresenter;
import software.ulpgc.imageviewer.control.NextImageCommand;
import software.ulpgc.imageviewer.control.PreviousImageCommand;
import software.ulpgc.imageviewer.io.FileImageLoader;
import software.ulpgc.imageviewer.model.Image;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        Image image = new FileImageLoader(new File("imagenes")).load();
        ImagePresenter imagePresenter = new ImagePresenter(mainFrame.getImageDisplay());
        mainFrame.addCommand("next", new NextImageCommand(imagePresenter))
                .addCommand("previous", new PreviousImageCommand(imagePresenter));
        imagePresenter.show(image);
        mainFrame.setVisible(true);
    }
}
