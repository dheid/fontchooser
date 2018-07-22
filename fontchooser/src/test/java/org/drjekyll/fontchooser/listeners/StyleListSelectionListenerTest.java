package org.drjekyll.fontchooser.listeners;

import org.drjekyll.fontchooser.FontContainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.event.ListSelectionEvent;
import java.awt.Font;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by dheid on 4/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
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
    public void updatesCurrentFont() throws Exception {

        when(fontContainer.getSelectedStyle()).thenReturn(STYLE);
        when(fontContainer.getSelectedFont()).thenReturn(font);
        when(font.getStyle()).thenReturn(Font.PLAIN);
        when(font.getSize()).thenReturn(SIZE);

        styleListSelectionListener.valueChanged(listSelectionEvent);

        verify(fontContainer).setSelectedFont(fontArgumentCaptor.capture());

        Font actualFont = fontArgumentCaptor.getValue();
        assertThat(actualFont.getName(), is(STYLE));
        assertThat(actualFont.getStyle(), is(Font.PLAIN));
        assertThat(actualFont.getSize(), is(SIZE));

        verify(fontContainer).setPreviewFont(actualFont);
    }
}
