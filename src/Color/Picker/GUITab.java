package Color.Picker;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class GUITab extends JFrame {
    private Color color = new Color(255,255,255);

    private final JPanel colorPanel = new JPanel();
    private final JPanel dataPanel = new JPanel();

    private final JLabel rgbLabel = new JLabel(String.format(
            "RGB: (%d, %d, %d)",
            color.getRed(),
            color.getGreen(),
            color.getBlue()
    ));

    private final JButton startButton = new JButton("Pick Color");
    private final JButton copyButton = new JButton("Copy Color");

    private BasicAction initializeBackgroundWindowsAction = new BasicAction(() -> {
        ColorPicker.initializeBackgroundWindows();
    });
    private BasicAction copyRGB = new BasicAction(() -> {
        StringSelection selection = new StringSelection(
                String.format(
                        "(%d, %d, %d)",
                        color.getRed(),
                        color.getGreen(),
                        color.getBlue()
                )
        );
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    });

    public GUITab() {
        // Initializing Window
        super("Color Picker");
        this.frameInit();
        this.setVisible(true);
        this.setSize(250,200);
        this.setLocation(-6,0); // (0,0 doesn't actually put it in the corner)
        this.setResizable(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);

        // colorPanel
        this.colorPanel.setLayout(new FlowLayout());
        this.colorPanel.setBackground(color);

        // dataPanel
        this.dataPanel.setLayout(new FlowLayout());
        this.dataPanel.setBackground(new Color(255,255,255));
        this.dataPanel.setSize(250, 40);

        // rgbLabel
        this.rgbLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // startButton
        this.startButton.setBackground(new Color(220,220,220));
        this.startButton.addActionListener(initializeBackgroundWindowsAction);

        // copyButton
        this.copyButton.setBackground(new Color(220,220,220));
        this.copyButton.addActionListener(copyRGB);

        this.dataPanel.add(rgbLabel);
        this.dataPanel.add(startButton);
        this.dataPanel.add(copyButton);

        this.add(this.dataPanel);
        this.add(this.colorPanel);
    }

    public void setColor(Color color) {
        this.color = color;
        this.colorPanel.setBackground(this.color);
        this.rgbLabel.setText(String.format(
                "RGB: (%d, %d, %d)",
                color.getRed(),
                color.getGreen(),
                color.getBlue()
        ));
    }
}