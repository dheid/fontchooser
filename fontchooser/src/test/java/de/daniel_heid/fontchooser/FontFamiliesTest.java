package de.daniel_heid.fontchooser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.Font;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class FontFamiliesTest {

    private static final String FAMILY = "family";
    private static final String FONT_NAME = "fontName";

    @Mock
    private Font font;

    @Test
    public void containsOnlyAddedFonts() throws Exception {

        given(font.getFamily()).willReturn(FAMILY);
        given(font.getName()).willReturn(FONT_NAME);

        FontFamilies fontFamilies = FontFamilies.getInstance();
        fontFamilies.add(font);

        assertThat(fontFamilies.iterator().hasNext(), is(true));

    }

    @Test
    public void containsAddedFont() throws Exception {

        given(font.getFamily()).willReturn(FAMILY);
        given(font.getName()).willReturn(FONT_NAME);

        FontFamilies fontFamilies = FontFamilies.getInstance();
        fontFamilies.add(font);

        FontFamily fontFamily = fontFamilies.get(FAMILY);
        assertThat(fontFamily.getName(), is(FAMILY));
        for (Font font : fontFamily) {
            assertThat(font, is(this.font));
        }

    }

}
