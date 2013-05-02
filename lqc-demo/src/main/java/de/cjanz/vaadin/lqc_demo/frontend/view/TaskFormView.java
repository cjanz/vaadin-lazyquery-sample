package de.cjanz.vaadin.lqc_demo.frontend.view;

import static de.cjanz.vaadin.lqc_demo.backend.domain.QTaskEntity.*;

import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.RichTextArea;

import de.cjanz.vaadin.lqc_demo.backend.domain.TaskEntity;
import de.cjanz.vaadin.lqc_demo.frontend.presenter.TaskFormPresenter;
import de.cjanz.vaadin.lqc_demo.frontend.view.field.TextFieldWithNote;

@CDIView(value = TaskFormView.VIEW_NAME, supportsParameters = true)
public class TaskFormView extends CustomComponent implements View {

	public static final String VIEW_NAME = "form";

	private TaskFormPresenter presenter;

	private BeanFieldGroup<TaskEntity> fieldGroup;

	@Inject
	public TaskFormView(final TaskFormPresenter presenter) {
		this.presenter = presenter;

		FormLayout formLayout = new FormLayout();

		fieldGroup = new BeanFieldGroup<>(TaskEntity.class);
		fieldGroup.setBuffered(false);

		createFields(formLayout);
		createButtonPanel(presenter, formLayout);

		setCompositionRoot(formLayout);
	}

	private void createFields(FormLayout formLayout) {
		TextFieldWithNote nameField = new TextFieldWithNote("Title",
				"The descriptive text of the task");
		nameField.setRequired(true);
		formLayout.addComponent(nameField);
		fieldGroup.bind(nameField, taskEntity.title.getMetadata().getName());

		formLayout.addComponent(fieldGroup.buildAndBind("Priority",
				taskEntity.priority.getMetadata().getName()));
		RichTextArea contentField = fieldGroup.buildAndBind("Content",
				taskEntity.content.getMetadata().getName(), RichTextArea.class);
		contentField.setNullRepresentation("");
		contentField.setNullSettingAllowed(true);
		formLayout.addComponent(contentField);

		OptionGroup booleanRadioButtons = new OptionGroup("Done");
		booleanRadioButtons.addStyleName("horizontal");
		booleanRadioButtons.addItem(Boolean.TRUE);
		booleanRadioButtons.setItemCaption(Boolean.TRUE, "Yes");
		booleanRadioButtons.addItem(Boolean.FALSE);
		booleanRadioButtons.setItemCaption(Boolean.FALSE, "No");
		booleanRadioButtons.setValue(Boolean.TRUE);
		formLayout.addComponent(booleanRadioButtons);
		fieldGroup.bind(booleanRadioButtons, taskEntity.done.getMetadata()
				.getName());
	}

	private void createButtonPanel(final TaskFormPresenter presenter,
			FormLayout formLayout) {

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		formLayout.addComponent(buttonLayout);

		Button saveButton = new Button("Save");
		saveButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (fieldGroup.isValid()) {
					presenter
							.saveTask(fieldGroup.getItemDataSource().getBean());
				}
			}
		});
		buttonLayout.addComponent(saveButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				presenter.cancel();
			}
		});
		buttonLayout.addComponent(cancelButton);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (event.getParameters() != null) {
			fieldGroup.setItemDataSource(presenter.loadTask(event
					.getParameters()));
		}
	}

}
