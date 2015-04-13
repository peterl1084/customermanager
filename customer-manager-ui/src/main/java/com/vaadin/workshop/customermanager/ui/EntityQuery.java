package com.vaadin.workshop.customermanager.ui;

import java.util.Collections;
import java.util.List;

import org.vaadin.addons.lazyquerycontainer.AbstractBeanQuery;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;

import com.vaadin.workshop.customermanager.entity.AbstractEntity;
import com.vaadin.workshop.customermanager.service.EntityService;

public class EntityQuery<E extends AbstractEntity> extends AbstractBeanQuery<E> {
	private static final long serialVersionUID = -6862588368714995626L;

	private final EntityService<E> service;
	private Class<E> entityType;

	public EntityQuery(QueryDefinition definition, EntityService<E> service,
			Class<E> entityType) {
		super(definition, Collections.emptyMap(), definition
				.getSortPropertyIds(), definition
				.getSortPropertyAscendingStates());

		this.service = service;
		this.entityType = entityType;
	}

	@Override
	protected void saveBeans(List<E> added, List<E> modified, List<E> removed) {
		throw new UnsupportedOperationException(
				"Storing is not supported through container");
	}

	@Override
	protected List<E> loadBeans(int start, int count) {
		return service.getByIndex(start, count);
	}

	@Override
	public int size() {
		return service.getNumberOfObjects();
	}

	@Override
	protected E constructBean() {
		try {
			return entityType.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
