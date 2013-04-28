package de.cjanz.vaadin.lqc_demo.backend.persistence;

import static de.cjanz.vaadin.lqc_demo.backend.domain.QTaskEntity.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.common.base.Strings;
import com.mysema.query.SearchResults;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.OrderSpecifier;

import de.cjanz.vaadin.lqc_demo.backend.domain.TaskEntity;
import de.cjanz.vaadin.lqc_demo.backend.domain.TaskFilter;

@Stateless
public class TaskRepository {

	@PersistenceContext
	private EntityManager em;

	public TaskEntity findOne(Long id) {
		return em.find(TaskEntity.class, id);
	}

	public TaskEntity save(TaskEntity entity) {
		if (entity.getId() == null) {
			em.persist(entity);
			return entity;
		} else {
			return em.merge(entity);
		}
	}

	public long countTasks(TaskFilter taskFilter) {
		return createQuery(taskFilter).count();
	}

	public SearchResults<TaskEntity> findTasks(TaskFilter taskFilter,
			Long startRow, Long recordCount, OrderSpecifier<?>... sortColumns) {
		return createQuery(taskFilter).orderBy(sortColumns).offset(startRow)
				.limit(recordCount).listResults(taskEntity);
	}

	private JPAQuery createQuery(TaskFilter taskFilter) {
		JPAQuery query = select().from(taskEntity);

		if (taskFilter != null) {
			if (!Strings.isNullOrEmpty(taskFilter.getTitle())) {
				query = query.where(taskEntity.title
						.containsIgnoreCase(taskFilter.getTitle()));
			}
			if (taskFilter.getPriority() != null) {
				query = query.where(taskEntity.priority.eq(taskFilter
						.getPriority()));
			}
		}
		return query;
	}

	protected JPAQuery select() {
		return new JPAQuery(em);
	}

}
