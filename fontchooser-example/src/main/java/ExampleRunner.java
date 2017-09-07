import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class ExampleRunner {

    public static void invoke(Runnable runnable) throws InterruptedException, InvocationTargetException {
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

    public static void useLookAndFeel(Class<?> lookAndFeelClass) {
        String lookAndFeelName = lookAndFeelClass.getName();
        useLookAndFeel(lookAndFeelName);
    }

    public static void useLookAndFeel(String lookAndFeelName) {
        try {
            UIManager.setLookAndFeel(lookAndFeelName);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            throw new RuntimeException("Could not set look and feel to " + lookAndFeelName, e);
        }
    }

}
