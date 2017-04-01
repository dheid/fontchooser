import javax.swing.WindowConstants;
import java.awt.EventQueue;
import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;

import com.connectina.swing.FontChooserDialog;


public class DialogExample {

    public static void main(String... args) throws InvocationTargetException, InterruptedException {

        System.setProperty("swing.aatext", "true");
        System.setProperty("awt.useSystemAAFontSettings", "lcd");

        EventQueue.invokeAndWait(() -> {
            FontChooserDialog dialog = new FontChooserDialog((Frame)null, "Font Dialog Example", true);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            if (!dialog.isCancelSelected()) {
                System.out.println("Selected font is: " + dialog.getSelectedFont());
            }
        });
    }

}
