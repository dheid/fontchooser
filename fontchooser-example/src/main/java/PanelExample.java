import io.github.dheid.fontchooser.FontChooser;
import io.github.dheid.fontchooser.model.FontSelectionModel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;

public class PanelExample implements Runnable {

    private final JLabel selection = new JLabel("Selected font will be displayed here");

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        ExampleRunner.useLookAndFeel(MetalLookAndFeel.class);
        ExampleRunner.invoke(new PanelExample());
    }

    @Override
    public void run() {
        FontChooser fontChooser = new FontChooser();
        fontChooser.addChangeListener(event -> {
            FontSelectionModel model = (FontSelectionModel) event.getSource();
            selection.setText(model.getSelectedFont().toString());
        });

        JFrame frame = new JFrame("Select Font");
        frame.setSize(600, 400);
        frame.add(fontChooser);
        frame.add(selection, BorderLayout.PAGE_END);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

}
