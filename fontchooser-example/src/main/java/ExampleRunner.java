import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class ExampleRunner {

    public static void invoke(Runnable runnable) throws InterruptedException, InvocationTargetException {
        useMetalLookAndFeel();
        enhanceFontRendering();
        useDifferentTranslation();
        EventQueue.invokeAndWait(runnable);
    }

    private static void useDifferentTranslation() {
        Locale.setDefault(new Locale("en"));
    }

    private static void enhanceFontRendering() {
        System.setProperty("swing.aatext", "true");
        System.setProperty("awt.useSystemAAFontSettings", "lcd");
    }

    private static void useMetalLookAndFeel() {
        String lookAndFeelName = MetalLookAndFeel.class.getName();
        try {
            UIManager.setLookAndFeel(lookAndFeelName);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            throw new RuntimeException("Could not set look and feel to " + lookAndFeelName, e);
        }
    }

}
