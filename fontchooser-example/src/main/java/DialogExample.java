import io.github.dheid.fontchooser.FontChooserDialog;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.EventQueue;
import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class DialogExample {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        // use nimbus look and feel
        UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());

        // better font rendering
        System.setProperty("swing.aatext", "true");
        System.setProperty("awt.useSystemAAFontSettings", "lcd");

        // FontChooserDialog and FontChooser provide different translations
        Locale.setDefault(new Locale("en"));

        EventQueue.invokeAndWait(() -> {
            FontChooserDialog dialog = new FontChooserDialog((Frame) null, "Font Dialog Example", true);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            if (!dialog.isCancelSelected()) {
                System.out.printf("Selected font is: %s%n", dialog.getSelectedFont());
            }
        });

    }

}
