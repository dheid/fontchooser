package io.github.dheid.fontchooser.panes;

import io.github.dheid.fontchooser.FontFamilies;
import io.github.dheid.fontchooser.FontFamily;
import io.github.dheid.fontchooser.model.FontSelectionModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
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
        stylePane.setSelectedStyle(family.iterator().next().getName());

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
        verifyZeroInteractions(listSelectionListener);
    }


}
