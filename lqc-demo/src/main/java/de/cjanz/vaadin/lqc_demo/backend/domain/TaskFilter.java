package de.cjanz.vaadin.lqc_demo.backend.domain;

public class TaskFilter {

	private String title = "";

	private Integer priority;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}
