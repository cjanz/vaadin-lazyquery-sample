package de.cjanz.vaadin.lqc_demo.backend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.bval.constraints.NotEmpty;

@Entity
public class TaskEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private Long version;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private String title;

	@Column(nullable = false)
	@Min(value = 0)
	@Max(value = 5)
	private int priority;

	private String content;

	@Column(nullable = false)
	private boolean done;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
