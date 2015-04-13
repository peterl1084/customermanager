package com.vaadin.workshop.customermanager.ui.event;

import com.vaadin.workshop.customermanager.entity.AbstractEntity;

public class EntitySavedEvent<E extends AbstractEntity> extends EntityEvent<E> {

	public EntitySavedEvent(E entity) {
		super(entity);
	}
}
