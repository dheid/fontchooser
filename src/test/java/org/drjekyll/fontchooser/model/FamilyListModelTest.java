package org.drjekyll.fontchooser.model;

import org.drjekyll.fontchooser.FontFamilies;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.Component;
import java.awt.FontMetrics;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyChar;

@ExtendWith(MockitoExtension.class)
class FamilyListModelTest {

    @Mock
    private Component component;

    @Mock
    private FontMetrics fontMetrics;

    @InjectMocks
    private FamilyListModel familyListModel;

    @Test
    void getsSize() {
        int result = familyListModel.getSize();

        assertThat(result).isEqualTo(FontFamilies.getInstance().size());
    }


    @Test
    void getsSizeOfMonospacedFonts() {

        givenFontMetrics();
        familyListModel.setMonospacedOnly(true);

        int result = familyListModel.getSize();

        assertThat(result).isBetween(1, FontFamilies.getInstance().size());
    }

    @Test
    void getsElementAt() {

        String result = familyListModel.getElementAt(0);

        assertThat(result).isNotBlank();

    }

    @Test
    void getsElementAtMonospaced() {

        givenFontMetrics();
        familyListModel.setMonospacedOnly(true);

        String result = familyListModel.getElementAt(0);

        assertThat(result).isNotBlank();

    }

    @Test
    void findsFirstElement() {

        Optional<String> result = familyListModel.findFirst("sans");

        assertThat(result).isNotEmpty();

    }

    @Test
    void doesNotFindNonExisting() {

        Optional<String> result = familyListModel.findFirst("dsjrh329fidushf");

        assertThat(result).isEmpty();

    }

    @Test
    void setsMonospaced() {
        familyListModel.setMonospacedOnly(true);
    }

    private void givenFontMetrics() {
        given(component.getFontMetrics(any())).willReturn(fontMetrics);
        given(fontMetrics.charWidth(anyChar())).willReturn(5);
    }


}
