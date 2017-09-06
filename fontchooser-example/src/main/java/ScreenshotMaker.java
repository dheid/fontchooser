import io.github.dheid.fontchooser.FontChooser;

import javax.imageio.ImageIO;
import javax.swing.CellRendererPane;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ScreenshotMaker implements Runnable {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        ExampleRunner.invoke(new ScreenshotMaker());
    }

    public static void layoutComponent(Component component) {
        synchronized (component.getTreeLock()) {
            component.doLayout();
            if (component instanceof Container) {
                for (Component child : ((Container) component).getComponents()) {
                    layoutComponent(child);
                }
            }
        }
    }

    @Override
    public void run() {

        Dimension size = new Dimension(600, 400);
        FontChooser fontChooser = new FontChooser(new Font("Helvetica", Font.PLAIN, 24));
        fontChooser.setBorder(new EmptyBorder(10, 10, 10, 10));
        fontChooser.setSize(size);
        layoutComponent(fontChooser);

        CellRendererPane rendererPane = new CellRendererPane();
        rendererPane.add(fontChooser);

        BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = image.createGraphics();

        rendererPane.paintComponent(graphics, fontChooser, rendererPane, fontChooser.getBounds());

        try {
            ImageIO.write(image, "png", new File("fontchooser.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
