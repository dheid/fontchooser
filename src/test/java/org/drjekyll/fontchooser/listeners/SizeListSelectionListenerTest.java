package org.drjekyll.fontchooser.listeners;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Font;
import javax.swing.event.ListSelectionEvent;
import org.drjekyll.fontchooser.FontContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


/**
 * Created by dheid on 4/1/17.
 */
@ExtendWith(MockitoExtension.class)
public class SizeListSelectionListenerTest {

    private static final float SIZE = 1.0f;

    @InjectMocks
    private SizeListSelectionListener sizeListSelectionListener;

    @Mock
    private FontContainer fontContainer;

    @Mock
    private ListSelectionEvent listSelectionEvent;

    @Mock
    private Font font;

    @Mock
    private Font derivedFont;

    @Captor
    private ArgumentCaptor<Font> fontArgumentCaptor;

    @Test
    public void updatesCurrentFont() {

        when(fontContainer.getSelectedSize()).thenReturn(SIZE);
        when(fontContainer.getSelectedFont()).thenReturn(font);
        when(font.deriveFont(SIZE)).thenReturn(derivedFont);

        sizeListSelectionListener.valueChanged(listSelectionEvent);

        verify(font).deriveFont(SIZE);
        verify(fontContainer).setSelectedFont(fontArgumentCaptor.capture());
        verify(fontContainer).setPreviewFont(font);

        Font actualFont = fontArgumentCaptor.getValue();
        assertThat(actualFont).isEqualTo(derivedFont);

    }
}
