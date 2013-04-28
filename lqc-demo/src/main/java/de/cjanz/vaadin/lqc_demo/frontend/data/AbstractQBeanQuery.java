package de.cjanz.vaadin.lqc_demo.frontend.data;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.lazyquerycontainer.Query;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;

import com.mysema.query.types.EntityPath;
import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Path;
import com.vaadin.data.Item;

public abstract class AbstractQBeanQuery<E, T extends EntityPath<E>> implements
		Query {

	protected QueryDefinition queryDefinition;

	protected AbstractQBeanQuery(QueryDefinition queryDefinition) {
		this.queryDefinition = queryDefinition;
	}

	@Override
	public List<Item> loadItems(int startIndex, int count) {
		List<Item> items = new ArrayList<Item>();

		List<E> results = loadRecords(startIndex, count);
		for (E bean : results) {
			items.add(new QBeanItem<E, T>(getMetadata(), bean));
		}

		return items;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected OrderSpecifier<?>[] getOrderSpecifiers() {
		OrderSpecifier<?>[] orders = new OrderSpecifier[queryDefinition
				.getSortPropertyIds().length];
		for (int i = 0; i < queryDefinition.getSortPropertyIds().length; i++) {
			Path<?> path = (Path<?>) queryDefinition.getSortPropertyIds()[i];
			orders[i] = new OrderSpecifier(
					queryDefinition.getSortPropertyAscendingStates()[i] ? Order.ASC
							: Order.DESC, path);
		}
		return orders;
	}

	@Override
	public void saveItems(List<Item> addedItems, List<Item> modifiedItems,
			List<Item> removedItems) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteAllItems() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Item constructItem() {
		return new QBeanItem<E, T>(getMetadata(), createBean());
	}

	public abstract T getMetadata();

	public abstract E createBean();

	public abstract List<E> loadRecords(int startIndex, int count);

}
