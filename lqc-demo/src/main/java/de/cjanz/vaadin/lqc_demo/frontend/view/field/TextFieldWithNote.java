package de.cjanz.vaadin.lqc_demo.frontend.view.field;

import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

/**
 * A {@link CustomField} that contains a {@link TextField} with a {@link Label}
 * on the right side.
 * 
 * <pre>
 *            ---------------------
 * Caption    |                   |  Note
 *            ---------------------
 * </pre>
 */
public class TextFieldWithNote extends CustomField<String> {

	private final TextField textField = new TextField();

	private final Label note = new Label();

	public TextFieldWithNote() {
		this("", "");
	}

	public TextFieldWithNote(String caption, String noteText) {
		setCaption(caption);
		setSizeUndefined();

		getNote().setValue(noteText);
		getNote().addStyleName("note");
	}

	@Override
	public Class<? extends String> getType() {
		return String.class;
	}

	@Override
	protected Component initContent() {
		this.textField.setImmediate(true);
		this.textField
				.addValueChangeListener(new Property.ValueChangeListener() {

					@Override
					public void valueChange(
							com.vaadin.data.Property.ValueChangeEvent event) {
						setValue((String) event.getProperty().getValue());
					}
				});

		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		layout.addComponent(textField);
		layout.addComponent(note);
		layout.setComponentAlignment(note, Alignment.MIDDLE_LEFT);

		return layout;
	}

	@Override
	protected void setInternalValue(String newValue) {
		super.setInternalValue(newValue);
		this.textField.setValue(newValue);
	}

	public TextField getTextField() {
		return textField;
	}

	public Label getNote() {
		return note;
	}

}
