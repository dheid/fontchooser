import io.github.dheid.fontchooser.FontChooserDialog;

import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;

public class DialogExample implements Runnable {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        ExampleRunner.useLookAndFeel(NimbusLookAndFeel.class);
        ExampleRunner.invoke(new DialogExample());
    }

    @Override
    public void run() {
        FontChooserDialog dialog = new FontChooserDialog((Frame) null, "Select Font", true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        if (!dialog.isCancelSelected()) {
            System.out.printf("Selected font is: %s%n", dialog.getSelectedFont());
        }
    }

}
