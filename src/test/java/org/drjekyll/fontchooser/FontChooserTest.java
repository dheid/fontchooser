package org.drjekyll.fontchooser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.event.ChangeListener;
import org.drjekyll.fontchooser.model.DefaultFontSelectionModel;
import org.drjekyll.fontchooser.model.FontSelectionModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


/**
 * Created by dheid on 4/1/17.
 */
@ExtendWith(MockitoExtension.class)
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
    public void initializesWithDefaultFont() {
        Font selectedFont = fontChooser.getSelectedFont();

        assertThat(selectedFont.getName()).isEqualTo(Font.SANS_SERIF);
        assertThat(selectedFont.getStyle()).isEqualTo(Font.PLAIN);
        assertThat(selectedFont.getSize()).isEqualTo(12);
        assertThat(fontChooser.getSelectedSize()).isEqualTo(12.0F);
        assertThat(fontChooser.getSelectedFamily()).isEqualTo(Font.SANS_SERIF);

    }

    @Test
    public void acceptsInitialFont() {
        fontChooser = new FontChooser(font);

        Font selectedFont = fontChooser.getSelectedFont();

        assertThat(selectedFont).isEqualTo(font);
    }

    @Test
    public void notifiesAboutSelectionModelChange() {
        fontChooser = new FontChooser();
        fontChooser.addPropertyChangeListener(propertyChangeListener);

        fontChooser.setSelectionModel(model);

        assertThat(fontChooser.getSelectionModel()).isEqualTo(model);

        verify(propertyChangeListener).propertyChange(propertyChangeEventArgumentCaptor.capture());

        PropertyChangeEvent event = propertyChangeEventArgumentCaptor.getValue();
        assertThat(event.getPropertyName()).isEqualTo("selectionModel");
        assertThat(event.getNewValue()).isEqualTo(model);
        assertThat(event.getOldValue()).isInstanceOf(DefaultFontSelectionModel.class);
        assertThat(event.getPropagationId()).isNull();
        assertThat(event.getSource()).isEqualTo(fontChooser);

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
