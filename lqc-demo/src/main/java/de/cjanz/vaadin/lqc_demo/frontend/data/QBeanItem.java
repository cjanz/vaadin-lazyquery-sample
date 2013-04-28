package de.cjanz.vaadin.lqc_demo.frontend.data;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.mysema.query.types.EntityPath;
import com.mysema.query.types.Path;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.data.util.PropertysetItem;

public class QBeanItem<E, T extends EntityPath<E>> extends PropertysetItem {

	private E item;

	public QBeanItem(T metadata, E item) {
		this.item = item;

		addEntityProperties(metadata, item);
	}

	private void addEntityProperties(T metadata, E item) {
		for (Field field : metadata.getClass().getDeclaredFields()) {
			if (Modifier.isStatic(field.getModifiers())) {
				continue;
			}

			if ("_super".equals(field.getName())) {
				continue;
			}

			if (Path.class.isAssignableFrom(field.getType())) {
				try {
					addItemProperty(field.get(metadata), new MethodProperty<>(
							item, field.getName()));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// ignore
				}
			}
		}
	}

	public E getBean() {
		return item;
	}

}
