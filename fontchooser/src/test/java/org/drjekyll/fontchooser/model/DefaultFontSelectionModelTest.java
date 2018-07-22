package org.drjekyll.fontchooser.model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Font;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by dheid on 4/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultFontSelectionModelTest {

	@InjectMocks
	private DefaultFontSelectionModel defaultFontSelectionModel;

	@Mock
	private Font font;

	@Mock
	private ChangeListener changeListener;

	@Captor
	private ArgumentCaptor<ChangeEvent> changeEventArgumentCaptor;

	@Test
	public void containsSelectedFont() {
		Font actualFont = defaultFontSelectionModel.getSelectedFont();
		assertThat(actualFont, is(font));
	}

	@Test
	public void doesNotChangeSelectedIfNullArgument() {
		defaultFontSelectionModel.setSelectedFont(null);
		Font actualFont = defaultFontSelectionModel.getSelectedFont();
		assertThat(actualFont, is(font));
	}

	@Test
	public void addsChangeListener() {
		defaultFontSelectionModel.addChangeListener(changeListener);
		ChangeListener[] changeListeners = defaultFontSelectionModel.getChangeListeners();
		assertThat(changeListeners, hasItemInArray(changeListener));
	}

	@Test
	public void removesChangeListener() {
		defaultFontSelectionModel.addChangeListener(changeListener);
		defaultFontSelectionModel.removeChangeListener(changeListener);
		ChangeListener[] changeListeners = defaultFontSelectionModel.getChangeListeners();
		assertThat(changeListeners, emptyArray());
	}

	@Test
	public void informsAboutChangedStates() {
		defaultFontSelectionModel.addChangeListener(changeListener);
		Font otherFont = mock(Font.class);

		defaultFontSelectionModel.setSelectedFont(otherFont);
		assertThat(defaultFontSelectionModel.getSelectedFont(), is(otherFont));

		verify(changeListener).stateChanged(changeEventArgumentCaptor.capture());
		ChangeEvent changeEvent = changeEventArgumentCaptor.getValue();
		assertThat(changeEvent.getSource(), is(defaultFontSelectionModel));
	}

}
