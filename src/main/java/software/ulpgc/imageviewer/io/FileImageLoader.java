package software.ulpgc.imageviewer.io;

import software.ulpgc.imageviewer.model.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.util.Objects.requireNonNull;

public class FileImageLoader implements ImageLoader {
    private final File[] files;

    public FileImageLoader(File folder) {
        this.files = requireNonNull(folder.listFiles());    // TODO check extensions
    }

    @Override
    public Image load() {
        return getImage(0);
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
            public byte[] content() {
                try {
                    return Files.readAllBytes(files[index].toPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
