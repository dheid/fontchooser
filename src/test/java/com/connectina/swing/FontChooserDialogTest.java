package com.connectina.swing;

import org.junit.Test;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by dheid on 4/1/17.
 */
public class FontChooserDialogTest {

    @Test
    public void initializesWithDefaultChooser() throws Exception {
        if (!GraphicsEnvironment.isHeadless()) {
            FontChooserDialog dialog = new FontChooserDialog();
            Font selectedFont = dialog.getSelectedFont();
            assertThat(selectedFont.getName(), is(Font.SANS_SERIF));
            assertThat(selectedFont.getStyle(), is(Font.PLAIN));
            assertThat(selectedFont.getSize(), is(FontChooser.DEFAULT_FONT_SIZE));
        }
    }

}
