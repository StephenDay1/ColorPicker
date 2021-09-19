package ColorPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BackgroundWindow extends JFrame implements MouseListener {
    public BackgroundWindow(GraphicsDevice screen) {
        Rectangle screenInfo = screen.getDefaultConfiguration().getBounds();
        setLocation(screenInfo.x, screenInfo.y);
        setSize(screenInfo.width, screenInfo.height);
        setUndecorated(true);
        setOpacity(.2f);
        setVisible(true);
        setAlwaysOnTop(true);

        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        ColorPicker.closeBackgroundWindows();
        if (mouseEvent.getButton() == 1) {
            ColorPicker.getColor();
        } else {
            ColorPicker.closeBackgroundWindows();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
}