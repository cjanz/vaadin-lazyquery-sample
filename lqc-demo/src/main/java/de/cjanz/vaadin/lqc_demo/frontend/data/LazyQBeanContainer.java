package de.cjanz.vaadin.lqc_demo.frontend.data;

import org.vaadin.addons.lazyquerycontainer.LazyQueryContainer;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;
import org.vaadin.addons.lazyquerycontainer.QueryFactory;

import com.mysema.query.types.Path;

public class LazyQBeanContainer extends LazyQueryContainer {

	public LazyQBeanContainer(QueryDefinition queryDefinition,
			QueryFactory queryFactory) {
		super(queryDefinition, queryFactory);
	}

	public void addContainerProperty(Path<?> path) {
		addContainerProperty(path, path.getType(), null, true, true);
	}

	public void addContainerProperty(Path<?> path, boolean sortable) {
		addContainerProperty(path, path.getType(), null, true, sortable);
	}

}
