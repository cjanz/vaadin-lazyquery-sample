package de.cjanz.vaadin.lqc_demo.frontend.presenter;

import static de.cjanz.vaadin.lqc_demo.backend.domain.QTaskEntity.*;

import java.util.List;

import javax.inject.Inject;

import org.vaadin.addons.lazyquerycontainer.LazyQueryDefinition;
import org.vaadin.addons.lazyquerycontainer.Query;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;
import org.vaadin.addons.lazyquerycontainer.QueryFactory;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.UI;

import de.cjanz.vaadin.lqc_demo.backend.domain.QTaskEntity;
import de.cjanz.vaadin.lqc_demo.backend.domain.TaskEntity;
import de.cjanz.vaadin.lqc_demo.backend.domain.TaskFilter;
import de.cjanz.vaadin.lqc_demo.backend.persistence.TaskRepository;
import de.cjanz.vaadin.lqc_demo.frontend.data.AbstractQBeanQuery;
import de.cjanz.vaadin.lqc_demo.frontend.data.LazyQBeanContainer;
import de.cjanz.vaadin.lqc_demo.frontend.view.TaskFormView;

public class TaskListPresenter {

	private final TaskFilter taskFilter = new TaskFilter();

	private final LazyQBeanContainer container;

	@Inject
	private TaskRepository taskRepository;

	public TaskListPresenter() {
		this.container = new LazyQBeanContainer(new LazyQueryDefinition(false,
				50, taskEntity.id), new TaskQueryFactory());

		this.container.addContainerProperty(taskEntity.id);
		this.container.addContainerProperty(taskEntity.title);
		this.container.addContainerProperty(taskEntity.priority);
		this.container.addContainerProperty(taskEntity.content);
	}

	public Container getTasksContainer() {
		return this.container;
	}

	public Item getFilterItem() {
		return new BeanItem<TaskFilter>(taskFilter);
	}

	public Container getPriorityContainer() {
		BeanItemContainer<Integer> container = new BeanItemContainer<>(
				Integer.class);
		for (int i = 0; i <= 5; i++) {
			container.addBean(i);
		}
		return container;
	}

	public void startSearch() {
		this.container.refresh();
	}

	public void openTask(Object itemId) {
		UI.getCurrent().getNavigator()
				.navigateTo(TaskFormView.VIEW_NAME + "/" + itemId);
	}

	class TaskQueryFactory implements QueryFactory {

		@Override
		public Query constructQuery(QueryDefinition queryDefinition) {
			return new TaskQuery(queryDefinition);
		}

	}

	class TaskQuery extends AbstractQBeanQuery<TaskEntity, QTaskEntity> {

		public TaskQuery(QueryDefinition queryDefinition) {
			super(queryDefinition);
		}

		@Override
		public int size() {
			return (int) taskRepository.countTasks(taskFilter);
		}

		@Override
		public QTaskEntity getMetadata() {
			return QTaskEntity.taskEntity;
		}

		@Override
		public TaskEntity createBean() {
			return new TaskEntity();
		}

		@Override
		public List<TaskEntity> loadRecords(int startIndex, int count) {
			return taskRepository.findTasks(taskFilter,
					Long.valueOf(startIndex), Long.valueOf(count),
					getOrderSpecifiers()).getResults();
		}

	}

}
