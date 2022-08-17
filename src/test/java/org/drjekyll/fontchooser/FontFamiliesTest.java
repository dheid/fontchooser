package org.drjekyll.fontchooser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.awt.Font;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FontFamiliesTest {

    private static final String FAMILY = "family";

    private static final String FONT_NAME = "fontName";

    @Mock
    private Font font;

    @Test
    public void containsOnlyAddedFonts() {

        given(font.getFamily()).willReturn(FAMILY);
        given(font.getName()).willReturn(FONT_NAME);

        FontFamilies fontFamilies = FontFamilies.getInstance();
        fontFamilies.add(font);

        assertThat(fontFamilies.iterator().hasNext()).isTrue();

    }

    @Test
    public void containsAddedFont() {

        given(font.getFamily()).willReturn(FAMILY);
        given(font.getName()).willReturn(FONT_NAME);

        FontFamilies fontFamilies = FontFamilies.getInstance();
        fontFamilies.add(font);

        FontFamily fontFamily = fontFamilies.get(FAMILY);
        assertThat(fontFamily.getName()).isEqualTo(FAMILY);
        for (Font font : fontFamily.getStyles()) {
            assertThat(font).isEqualTo(this.font);
        }

    }

}
