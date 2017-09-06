import apple.laf.AquaLookAndFeel;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import io.github.dheid.fontchooser.FontChooser;

import javax.imageio.ImageIO;
import javax.swing.CellRendererPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class ScreenshotMaker implements Runnable {

    private static final Class<?>[] LOOK_AND_FEELS = {
        MetalLookAndFeel.class,
        NimbusLookAndFeel.class,
        AquaLookAndFeel.class,
        MotifLookAndFeel.class,
    };

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
        for (Class<?> lookAndFeelClass : LOOK_AND_FEELS) {
            ExampleRunner.useLookAndFeel(lookAndFeelClass);
            FontChooser fontChooser = new FontChooser(new Font("Helvetica", Font.PLAIN, 24));
            fontChooser.setBorder(new EmptyBorder(10, 10, 10, 10));
            fontChooser.setSize(size);
            layoutComponent(fontChooser);

            CellRendererPane rendererPane = new CellRendererPane();
            rendererPane.add(fontChooser);

            BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
            Graphics graphics = image.createGraphics();

            rendererPane.paintComponent(graphics, fontChooser, rendererPane, fontChooser.getBounds());

            String pathname = "fontchooser-" + lookAndFeelClass.getSimpleName().toLowerCase(Locale.ENGLISH) + ".png";
            try {
                ImageIO.write(image, "png", new File(pathname));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
