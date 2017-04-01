package com.connectina.swing;

import java.awt.Font;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by dheid on 4/1/17.
 */
public class FontChooserDialogTest {

    private FontChooserDialog dialog = new FontChooserDialog();

    @Test
    public void initializesWithDefaultChooser() throws Exception {
        Font selectedFont = dialog.getSelectedFont();
        assertThat(selectedFont.getName(), is(Font.SANS_SERIF));
        assertThat(selectedFont.getStyle(), is(Font.PLAIN));
        assertThat(selectedFont.getSize(), is(FontChooser.DEFAULT_FONT_SIZE));
    }

}
