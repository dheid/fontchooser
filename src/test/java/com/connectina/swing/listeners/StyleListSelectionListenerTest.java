package com.connectina.swing.listeners;

import javax.swing.event.ListSelectionEvent;
import java.awt.Font;

import com.connectina.swing.FontContainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by dheid on 4/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class StyleListSelectionListenerTest {

    private static final int STYLE = 1;

    @InjectMocks
    private StyleListSelectionListener styleListSelectionListener;

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
    public void updatesCurrentFont() throws Exception {

        when(fontContainer.getSelectedStyle()).thenReturn(STYLE);
        when(fontContainer.getSelectedFont()).thenReturn(font);
        when(font.deriveFont(STYLE)).thenReturn(derivedFont);

        styleListSelectionListener.valueChanged(listSelectionEvent);

        verify(font).deriveFont(STYLE);
        verify(fontContainer).setSelectedFont(fontArgumentCaptor.capture());
        verify(fontContainer).setPreviewFont(font);

        Font actualFont = fontArgumentCaptor.getValue();
        assertThat(actualFont, is(derivedFont));

    }
}
