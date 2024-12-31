package software.ulpgc.imageviewer.io;

import software.ulpgc.imageviewer.model.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class FileImageLoader implements ImageLoader {
    private final File[] files;
    private final Map<File, BufferedImage> cache;
    private final Set<String> validExtensions =Set.of("jpg", "jpeg", "png", "gif");

    public FileImageLoader(File folder) {
        this.files = requireNonNull(folder.listFiles(isImageFile()));
        this.cache = new HashMap<>();
    }

    @Override
    public Image load() {
        return getImage(0);
    }

    private FilenameFilter isImageFile(){
        return (dir, name) -> validExtensions.contains(extensionOf(name));
    }

    private static String extensionOf(String name) {
        return name.substring(name.lastIndexOf(".") + 1).toLowerCase();
    }

    private Image getImage(int index) {
        return new Image() {
            @Override
            public String name() {
                return files[index].getName();
            }

            @Override
            public Image next() {
                int nextIndex = (index + 1) % files.length;
                return getImage(nextIndex);
            }

            @Override
            public Image previous() {
                int prevIndex = (index - 1 + files.length) % files.length;
                return getImage(prevIndex);
            }

            @Override
            public BufferedImage content() {
                return loadContent(files[index]);
            }
        };
    }

    private BufferedImage loadContent(File file) {
        return cache.computeIfAbsent(file, this::readFileContent);
    }

    private BufferedImage readFileContent(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
