package Color.Picker;

import java.awt.*;

public class ColorPicker {
    static GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();

    // List of Screens connected (For example, a second monitor connected via HDMI)
    static GraphicsDevice[] graphicsDevices = graphicsEnvironment.getScreenDevices();

    static GUITab guiTab = new GUITab();
    static BackgroundWindow[] backgroundWindows;

    public static void main(String[] args) {}

    public static void getColor() {
        Color color = new Color(255,255,255);
        try {
            Robot robot = new Robot(graphicsDevices[0]);
            color = robot.getPixelColor((int) getMouseX(), (int) getMouseY());
        } catch (AWTException e) {
            e.printStackTrace();
        }
        guiTab.setColor(color);
    }

    public static void initializeBackgroundWindows() {
        backgroundWindows = new BackgroundWindow[graphicsDevices.length];
        for (int i = 0; i < backgroundWindows.length; i++) {
            backgroundWindows[i] = new BackgroundWindow(graphicsDevices[i]);
        }
    }

    public static void closeBackgroundWindows() {
        for (BackgroundWindow backgroundWindow : backgroundWindows) {
            backgroundWindow.dispose();
        }
    }

    private static double getMouseX() {
        return MouseInfo.getPointerInfo().getLocation().getX();
    }
    private static double getMouseY() {
        return MouseInfo.getPointerInfo().getLocation().getY();
    }
}