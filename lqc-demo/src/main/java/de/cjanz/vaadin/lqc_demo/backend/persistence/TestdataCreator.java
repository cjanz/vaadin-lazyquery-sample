package de.cjanz.vaadin.lqc_demo.backend.persistence;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import de.cjanz.vaadin.lqc_demo.backend.domain.TaskEntity;

@Startup
@Singleton
public class TestdataCreator {

	@Inject
	private TaskRepository repository;

	@PostConstruct
	public void createData() {
		for (int i = 0; i < 10000; i++) {
			TaskEntity task = new TaskEntity();
			task.setTitle("Task: " + i);
			task.setPriority((int) Math.round(Math.random() * 5));
			repository.save(task);
		}
	}

}
