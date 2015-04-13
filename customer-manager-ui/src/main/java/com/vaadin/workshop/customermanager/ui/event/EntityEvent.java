package com.vaadin.workshop.customermanager.ui.event;

import com.vaadin.workshop.customermanager.entity.AbstractEntity;

public abstract class EntityEvent<E extends AbstractEntity> {
	private final E entity;

	public EntityEvent(E entity) {
		this.entity = entity;
	}

	public E getEntity() {
		return entity;
	}
}
