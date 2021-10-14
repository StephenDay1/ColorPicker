package Color.Picker;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class GUITab extends JFrame {
    private Color color = new Color(255,255,255);
    private enum ColorMode {
        RGB,
        Hex
    }
    private ColorMode colorMode = ColorMode.RGB;

    private final JPanel colorPanel = new JPanel();
    private final JPanel dataPanel = new JPanel();

    private final JLabel rgbLabel = new JLabel(String.format(
            "(%d, %d, %d)",
            color.getRed(),
            color.getGreen(),
            color.getBlue()
    ));

    private final JButton startButton = new JButton("Pick Color");
    private final JButton copyButton = new JButton("Copy Color");
    private final JButton colorModeButton = new JButton("RGB");

    private final BasicAction initializeBackgroundWindowsAction = new BasicAction(() -> {
        ColorPicker.initializeBackgroundWindows();
    });
    private BasicAction copyRGB = new BasicAction(() -> {
        StringSelection selection = colorMode.equals(ColorMode.RGB)?
            new StringSelection(
                String.format(
                    "%d, %d, %d",
                    color.getRed(),
                    color.getGreen(),
                    color.getBlue()
                )):
            new StringSelection(
                String.format(
                    "#%s%s%s",
                    ColorPicker.toHex(color.getRed()),
                    ColorPicker.toHex(color.getGreen()),
                    ColorPicker.toHex(color.getBlue())
                )
            );
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    });

    private BasicAction toggleColorMode = new BasicAction(() -> {
        if (this.colorMode.equals(ColorMode.RGB)) {
            this.colorMode = ColorMode.Hex;
            this.colorModeButton.setText("HEX");
        } else {
            this.colorMode = ColorMode.RGB;
            this.colorModeButton.setText("RGB");
        }
        setColor(this.color);
    });

    public GUITab() {
        // Initializing Window
        super("Color Picker");
        this.frameInit();
        this.setVisible(true);
        this.setSize(300,200);
        this.setLocation(-6,0); // (0,0 doesn't actually put it in the corner)
        this.setResizable(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setLayout(new GridLayout(2,1));

        // colorPanel
        this.colorPanel.setBackground(color);
        this.colorPanel.setBorder(BorderFactory.createLineBorder(new Color(0), 2, true));
        this.colorPanel.setFocusable(false);
//        this.colorPanel.setSize(300, 20);

        // dataPanel
        this.dataPanel.setLayout(new GridLayout(3,1));
        this.dataPanel.setBackground(new Color(255,255,255));
//        this.dataPanel.setFocusable(false);
//        this.dataPanel.setSize(300, 100);

        // rgbLabel
        this.rgbLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.rgbLabel.setFocusable(false);
//        this.rgbLabel.setSize(250,10);

        // startButton
        this.startButton.setBackground(new Color(220,220,220));
        this.startButton.addActionListener(initializeBackgroundWindowsAction);
        this.startButton.setFocusable(false);
//        this.startButton.setSize(250,10);

        // copyButton
        this.copyButton.setBackground(new Color(220,220,220));
        this.copyButton.addActionListener(copyRGB);
        this.copyButton.setFocusable(false);
//        this.copyButton.setSize(250,10);

        // colorModeButton
        this.colorModeButton.setBackground(new Color(220,220,220));
        this.colorModeButton.addActionListener(toggleColorMode);
        this.colorModeButton.setFocusable(false);

        this.dataPanel.add(rgbLabel);
        this.dataPanel.add(startButton);
        this.dataPanel.add(copyButton);
        this.dataPanel.add(colorModeButton);

//        this.add(this.colorPanel);
        this.add(this.dataPanel);
        this.add(this.colorPanel);
    }

    public void setColor(Color color) {
        this.color = color;
        this.colorPanel.setBackground(this.color);
        this.rgbLabel.setText(colorMode.equals(ColorMode.RGB)?
            String.format(
                "(%d, %d, %d)",
                color.getRed(),
                color.getGreen(),
                color.getBlue()
            ):
            String.format(
                "#%s%s%s",
                ColorPicker.toHex(color.getRed()),
                ColorPicker.toHex(color.getGreen()),
                ColorPicker.toHex(color.getBlue())
            )
        );
    }
}