import com.connectina.swing.FontChooserDialog;

import javax.swing.JDialog;
import java.awt.EventQueue;
import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;

public class DialogExample {

    public static void main(String... args) throws InvocationTargetException, InterruptedException {
        EventQueue.invokeAndWait(() -> {
            FontChooserDialog dialog = new FontChooserDialog((Frame) null, "Font Dialog Example", true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            if (!dialog.isCancelSelected()) {
                System.out.println("Selected font is: " + dialog.getSelectedFont());
            }
        });
    }

}
