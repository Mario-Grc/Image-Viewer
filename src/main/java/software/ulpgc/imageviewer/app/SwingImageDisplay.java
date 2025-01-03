package software.ulpgc.imageviewer.app;

import software.ulpgc.imageviewer.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private final List<Paint> paints = new ArrayList<>();
    private Shift shift = Shift.Null;
    private Release release = Release.Null;
    private int initialOffset;

    public SwingImageDisplay() {
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                initialOffset = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                release.offset(e.getX() - initialOffset);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                shift.offset(e.getX() - initialOffset);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        };
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for(Paint paint : paints) drawImage(g, paint);
    }

    private void drawImage(Graphics g, Paint paint) {
        Dimension scaledSize = fitToSize(paint.content);

        int x = (getWidth() - scaledSize.width) / 2;
        int y = (getHeight() - scaledSize.height) / 2;

        g.drawImage(paint.content(), x + paint.shiftOffset, y, scaledSize.width, scaledSize.height, null);
    }

    private Dimension fitToSize(BufferedImage image) {
        int originalWidth = image.getWidth(null);
        int originalHeight = image.getHeight(null);

        double scale = calculateScale(originalWidth, originalHeight);

        int scaledWidth = (int) (scale * originalWidth);
        int scaledHeight = (int) (scale * originalHeight);

        return new Dimension(scaledWidth, scaledHeight);
    }

    private double calculateScale(int originalWidth, int originalHeight) {
        double panelWidth = getWidth();
        double panelHeight = getHeight();
        return Math.min(panelWidth / originalWidth, panelHeight / originalHeight);
    }

    @Override
    public int width() {
        return getWidth();
    }

    @Override
    public void paint(int offset, int width, int height, BufferedImage content) {
        paints.add(new Paint(width, height, offset, content));
        repaint();
    }

    @Override
    public void clear() {
        paints.clear();
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift != null ? shift : Shift.Null;
    }

    @Override
    public void on(Release release) {
        this.release = release != null ? release : Release.Null;
    }

    private record Paint(int width, int height, int shiftOffset, BufferedImage content) {}
}
