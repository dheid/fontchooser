package org.drjekyll.fontchooser.lafs;

import org.drjekyll.fontchooser.FontChooser;

import javax.imageio.ImageIO;
import javax.swing.CellRendererPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenshotMaker {

    private static final Logger log = Logger.getLogger(ScreenshotMaker.class.getName());

    private static final String[] LOOK_AND_FEEL_CLASS_NAMES = {
        UIManager.getSystemLookAndFeelClassName(),
        "com.sun.java.swing.plaf.motif.MotifLookAndFeel",
        "javax.swing.plaf.metal.MetalLookAndFeel",
        "javax.swing.plaf.nimbus.NimbusLookAndFeel",
        "com.github.weisj.darklaf.DarkLaf",
        "com.jtattoo.plaf.graphite.GraphiteLookAndFeel",
        "com.jtattoo.plaf.aero.AeroLookAndFeel",
        "com.jtattoo.plaf.noire.NoireLookAndFeel",
        "com.jtattoo.plaf.texture.TextureLookAndFeel",
        "com.jtattoo.plaf.mint.MintLookAndFeel",
        "com.jtattoo.plaf.acryl.AcrylLookAndFeel",
        "com.jtattoo.plaf.hifi.HiFiLookAndFeel",
        "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel",
        "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel",
        "com.jtattoo.plaf.fast.FastLookAndFeel",
        "com.jtattoo.plaf.luna.LunaLookAndFeel",
        "com.jtattoo.plaf.mcwin.McWinLookAndFeel",
        "com.jtattoo.plaf.smart.SmartLookAndFeel",
        "com.formdev.flatlaf.FlatDarkLaf",
        "com.formdev.flatlaf.FlatDarculaLaf",
        "com.formdev.flatlaf.FlatLightLaf",
        "com.formdev.flatlaf.FlatIntelliJLaf",
    };


    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        System.setProperty("swing.aatext", "true");
        System.setProperty("awt.useSystemAAFontSettings", "lcd");
        Locale.setDefault(new Locale("en"));
        EventQueue.invokeAndWait(ScreenshotMaker::makeScreenshot);
    }

    private static void layoutComponent(Component component) {
        synchronized (component.getTreeLock()) {
            component.doLayout();
            if (component instanceof Container) {
                for (Component child : ((Container) component).getComponents()) {
                    layoutComponent(child);
                }
            }
        }
    }

    private static void makeScreenshot() {

        Dimension size = new Dimension(650, 350);

        for (String lookAndFeelClassName : LOOK_AND_FEEL_CLASS_NAMES) {
            try {
                log.info("Creating screenshot for " + lookAndFeelClassName);
                UIManager.setLookAndFeel(lookAndFeelClassName);
                FontChooser fontChooser = new FontChooser(new Font("DejaVu Sans", Font.PLAIN, 24));
                fontChooser.setBorder(new EmptyBorder(10, 10, 10, 10));
                fontChooser.setSize(size);
                layoutComponent(fontChooser);

                CellRendererPane rendererPane = new CellRendererPane();
                rendererPane.add(fontChooser);

                BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
                Graphics graphics = image.createGraphics();

                rendererPane.paintComponent(graphics, fontChooser, rendererPane, fontChooser.getBounds());

                String[] nameParts = lookAndFeelClassName.split("\\.");
                ImageIO.write(image, "png", new File("screenshots", String.format("fontchooser-%s.png", nameParts[nameParts.length - 1].toLowerCase(Locale.ENGLISH))));
            } catch (Exception e) {
                log.log(Level.WARNING, "Could not create screenshot", e);
            }
        }


    }

}
