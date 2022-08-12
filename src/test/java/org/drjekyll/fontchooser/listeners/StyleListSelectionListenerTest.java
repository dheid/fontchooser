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
public class StyleListSelectionListenerTest {

    private static final String STYLE = "style";
    private static final int SIZE = 12;

    @InjectMocks
    private StyleListSelectionListener styleListSelectionListener;

    @Mock
    private FontContainer fontContainer;

    @Mock
    private ListSelectionEvent listSelectionEvent;

    @Mock
    private Font font;

    @Captor
    private ArgumentCaptor<Font> fontArgumentCaptor;

    @Test
    public void updatesCurrentFont() {

        when(fontContainer.getSelectedStyle()).thenReturn(STYLE);
        when(fontContainer.getSelectedFont()).thenReturn(font);
        when(font.getStyle()).thenReturn(Font.PLAIN);
        when(font.getSize()).thenReturn(SIZE);

        styleListSelectionListener.valueChanged(listSelectionEvent);

        verify(fontContainer).setSelectedFont(fontArgumentCaptor.capture());

        Font actualFont = fontArgumentCaptor.getValue();
        assertThat(actualFont.getName()).isEqualTo(STYLE);
        assertThat(actualFont.getStyle()).isEqualTo(Font.PLAIN);
        assertThat(actualFont.getSize()).isEqualTo(SIZE);

        verify(fontContainer).setPreviewFont(actualFont);
    }
}
