package com.vaadin.workshop.customermanager.service;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import com.vaadin.workshop.customermanager.entity.AbstractEntity;

@Local
public interface EntityService<E extends AbstractEntity> {

	List<E> getAll();

	void store(E entity);

	void store(Collection<E> entities);

	void remove(E entity);

	void remove(Collection<E> entities);

	List<E> getByIndex(int start, int count);

	int getNumberOfObjects();

	E getEntityById(long itemId);
}
