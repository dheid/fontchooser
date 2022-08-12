import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.MetalLookAndFeel;
import org.drjekyll.fontchooser.FontDialog;

public class ShowDialogExample implements Runnable {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        ExampleRunner.useLookAndFeel(MetalLookAndFeel.class);
        ExampleRunner.invoke(new ShowDialogExample());
    }

    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 300);

        JTextArea textArea = new JTextArea("This text will be formatted according to the selected font. " +
            "Double click to change font.");
        textArea.setLineWrap(true);

        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    FontDialog.showDialog(textArea);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.add(scrollPane);

        frame.setVisible(true);

    }
}
