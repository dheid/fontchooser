package org.drjekyll.fontchooser.panes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.drjekyll.fontchooser.FontFamilies;
import org.drjekyll.fontchooser.FontFamily;
import org.drjekyll.fontchooser.model.FontSelectionModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StylePaneTest {

    private static final String FAMILY = "family";

    @Mock
    private ChangeEvent changeEvent;

    @Mock
    private FontSelectionModel fontSelectionModel;

    @Mock
    private Font font;

    @Mock
    private ListSelectionListener listSelectionListener;

    @Mock
    private Component objectLock;

    @Mock
    private Object aaHint;

    @Mock
    private Object lcdRenderingHint;

    @InjectMocks
    private StylePane stylePane;

    private static FontFamily anyExistingFontFamily() {
        return FontFamilies.getInstance().iterator().next();
    }

    @Test
    public void doesNothingIfFontNotFound() {

        givenSource();
        givenSelectedFont();
        givenFontFamily(FAMILY);

        whenStateChanged();

        thenGetsSource();
        thenGetsSelectedFont();
        thenGetsFamily();

    }

    @Test
    public void doesNotFireSelectionEventOnStateChange() {

        givenSource();
        givenSelectedFont();
        givenFontFamily(anyExistingFontFamily().getName());
        givenListSelectionListener();

        whenStateChanged();

        thenGetsSource();
        thenGetsSelectedFont();
        thenGetsFamily();
        thenDoesNotFireSelectionEvent();

    }

    @Test
    public void updatesSelectedValue() {

        givenSource();
        givenSelectedFont();
        FontFamily family = anyExistingFontFamily();
        givenFontFamily(family.getName());
        givenListSelectionListener();

        whenStateChanged();
        stylePane.setSelectedStyle(family.getStyles().iterator().next());

        thenGetsSource();
        thenGetsSelectedFont();
        thenGetsFamily();
        verify(listSelectionListener).valueChanged(any(ListSelectionEvent.class));


    }

    private void givenListSelectionListener() {
        stylePane.addListSelectionListener(listSelectionListener);
    }

    private void givenFontFamily(String family) {
        given(font.getFamily()).willReturn(family);
    }

    private void givenSelectedFont() {
        given(fontSelectionModel.getSelectedFont()).willReturn(font);
    }

    private void givenSource() {
        given(changeEvent.getSource()).willReturn(fontSelectionModel);
    }

    private void whenStateChanged() {
        stylePane.stateChanged(changeEvent);
    }

    private void thenGetsFamily() {
        verify(font).getFamily();
    }

    private void thenGetsSelectedFont() {
        verify(fontSelectionModel).getSelectedFont();
    }

    private void thenGetsSource() {
        verify(changeEvent).getSource();
    }

    private void thenDoesNotFireSelectionEvent() {
        verifyNoInteractions(listSelectionListener);
    }


}
