import org.drjekyll.fontchooser.FontChooser;
import org.drjekyll.fontchooser.model.FontSelectionModel;

import javax.swing.*;
import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;

public class PanelExample implements Runnable {

    private final JLabel selection = new JLabel("Selected font will be displayed here");

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        ExampleRunner.useLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        ExampleRunner.invoke(new PanelExample());
    }

    @Override
    public void run() {
        selection.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        FontChooser fontChooser = new FontChooser();
        fontChooser.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
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
