package de.cjanz.vaadin.lqc_demo.frontend.presenter;

import javax.inject.Inject;

import com.vaadin.ui.UI;

import de.cjanz.vaadin.lqc_demo.backend.domain.TaskEntity;
import de.cjanz.vaadin.lqc_demo.backend.persistence.TaskRepository;

public class TaskFormPresenter {

	@Inject
	private TaskRepository taskRepository;

	public TaskEntity loadTask(String parameters) {
		return taskRepository.findOne(Long.parseLong(parameters));
	}

	public void saveTask(TaskEntity bean) {
		taskRepository.save(bean);
		gotoHomeView();
	}

	public void cancel() {
		gotoHomeView();
	}

	private void gotoHomeView() {
		UI.getCurrent().getNavigator().navigateTo("");
	}

}
