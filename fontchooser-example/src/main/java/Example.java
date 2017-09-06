import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class Example {

    public static void invoke(Runnable runnable) throws InterruptedException, InvocationTargetException {

        // use nimbus look and feel
        String lookAndFeelName = NimbusLookAndFeel.class.getName();
        try {
            UIManager.setLookAndFeel(lookAndFeelName);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            throw new RuntimeException("Could not set look and feel to " + lookAndFeelName, e);
        }

        // better font rendering
        System.setProperty("swing.aatext", "true");
        System.setProperty("awt.useSystemAAFontSettings", "lcd");

        // FontChooserDialog and FontChooser provide different translations
        Locale.setDefault(new Locale("en"));

        EventQueue.invokeAndWait(runnable);

    }

}
