package org.drjekyll.fontchooser;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import org.junit.jupiter.api.Test;


/**
 * Created by dheid on 4/1/17.
 */
public class FontDialogTest {

    @Test
    public void initializesWithDefaultChooser() {
        if (!GraphicsEnvironment.isHeadless()) {
            FontDialog dialog = new FontDialog();
            Font selectedFont = dialog.getSelectedFont();
            assertThat(selectedFont.getName()).isEqualTo(Font.SANS_SERIF);
            assertThat(selectedFont.getStyle()).isEqualTo(Font.PLAIN);
            assertThat(selectedFont.getSize()).isEqualTo(12);
        }
    }

}
