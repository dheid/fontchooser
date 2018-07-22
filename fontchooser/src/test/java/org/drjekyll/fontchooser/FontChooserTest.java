package org.drjekyll.fontchooser;

import org.drjekyll.fontchooser.model.DefaultFontSelectionModel;
import org.drjekyll.fontchooser.model.FontSelectionModel;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.event.ChangeListener;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;


/**
 * Created by dheid on 4/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class FontChooserTest {

    private FontChooser fontChooser = new FontChooser();

    @Mock
    private Font font;

    @Mock
    private FontSelectionModel model;

    @Mock
    private PropertyChangeListener propertyChangeListener;

    @Mock
    private ChangeListener changeListener;

    @Captor
    private ArgumentCaptor<PropertyChangeEvent> propertyChangeEventArgumentCaptor;

    @Test
    public void initializesWithDefaultFont() throws Exception {
        Font selectedFont = fontChooser.getSelectedFont();

        assertThat(selectedFont.getName(), is(Font.SANS_SERIF));
        assertThat(selectedFont.getStyle(), is(Font.PLAIN));
        assertThat(selectedFont.getSize(), Matchers.is(12));
        assertThat(fontChooser.getSelectedSize(), is(12.0F));
        assertThat(fontChooser.getSelectedFamily(), is(Font.SANS_SERIF));

    }

    @Test
    public void acceptsInitialFont() throws Exception {
        fontChooser = new FontChooser(font);

        Font selectedFont = fontChooser.getSelectedFont();

        assertThat(selectedFont, is(font));
    }

    @Test
    public void notifiesAboutSelectionModelChange() {
        fontChooser = new FontChooser();
        fontChooser.addPropertyChangeListener(propertyChangeListener);

        fontChooser.setSelectionModel(model);

        assertThat(fontChooser.getSelectionModel(), is(model));

        verify(propertyChangeListener).propertyChange(propertyChangeEventArgumentCaptor.capture());

        PropertyChangeEvent event = propertyChangeEventArgumentCaptor.getValue();
        assertThat(event.getPropertyName(), is("selectionModel"));
        assertThat(event.getNewValue(), is(model));
        assertThat(event.getOldValue(), instanceOf(DefaultFontSelectionModel.class));
        assertThat(event.getPropagationId(), is(nullValue()));
        assertThat(event.getSource(), is(fontChooser));

    }

    @Test
    public void addsChangeListenerToModel() {
        fontChooser = new FontChooser();
        fontChooser.setSelectionModel(model);

        fontChooser.addChangeListener(changeListener);

        verify(model).addChangeListener(changeListener);
    }

    @Test
    public void removesChangeListenerFromModel() {
        fontChooser = new FontChooser();
        fontChooser.setSelectionModel(model);

        fontChooser.removeChangeListener(changeListener);

        verify(model).removeChangeListener(changeListener);
    }

}
