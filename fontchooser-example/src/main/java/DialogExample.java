import io.github.dheid.fontchooser.FontChooserDialog;

import javax.swing.WindowConstants;
import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;

public class DialogExample extends Example implements Runnable {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        invoke(new DialogExample());
    }

    @Override
    public void run() {
        FontChooserDialog dialog = new FontChooserDialog((Frame) null, "Font Dialog Example", true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        if (!dialog.isCancelSelected()) {
            System.out.printf("Selected font is: %s%n", dialog.getSelectedFont());
        }
    }

}
