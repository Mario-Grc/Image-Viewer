package software.ulpgc.imageviewer.app;

import software.ulpgc.imageviewer.control.Command;
import software.ulpgc.imageviewer.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final SwingImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    public MainFrame() {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(imageDisplay = newImageDisplay());
        this.add(navigationPanel(), BorderLayout.SOUTH);
        this.commands = new HashMap<>();
    }

    private Component navigationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        panel.add(customButton("previous"));
        panel.add(customButton("next"));
        return panel;
    }

    private Component customButton(String name) {
        JButton button = new JButton(name);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(name).execute();
            }
        });
        return button;
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }

    private SwingImageDisplay newImageDisplay() {
        return new SwingImageDisplay(new SwingImageDeserializer());
    }

    public MainFrame addCommand(String name, Command command) {
        commands.put(name, command);
        return this;
    }
}
