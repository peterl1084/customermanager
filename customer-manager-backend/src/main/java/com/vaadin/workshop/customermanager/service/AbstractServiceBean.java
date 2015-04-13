package com.vaadin.workshop.customermanager.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.vaadin.workshop.customermanager.entity.AbstractEntity;

public abstract class AbstractServiceBean<E extends AbstractEntity> implements
		EntityService<E> {

	private static final String PERSISTENCE_UNIT = "customermanager";

	@PersistenceContext(name = PERSISTENCE_UNIT)
	protected EntityManager entityManager;

	@Override
	public List<E> getAll() {
		return entityManager.createQuery("SELECT e FROM " + entity() + " e ",
				getEntityType()).getResultList();
	}

	@Override
	public void store(E entity) {
		if (!entity.isPersisted()) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
	}

	@Override
	public void store(Collection<E> entities) {
		for (E entity : entities) {
			store(entity);
		}
	}

	@Override
	public void remove(E entity) {
		if (entity == null)
			return;

		entity = entityManager.getReference(getEntityType(), entity.getId());
		entityManager.remove(entity);
	}

	@Override
	public void remove(Collection<E> entities) {
		for (E entity : entities) {
			remove(entity);
		}
	}

	@Override
	public List<E> getByIndex(int start, int count) {
		return entityManager
				.createQuery("SELECT e FROM " + entity() + " e",
						getEntityType()).setFirstResult(start)
				.setMaxResults(count).getResultList();
	}

	@Override
	public int getNumberOfObjects() {
		return entityManager
				.createQuery("SELECT COUNT(e) FROM " + entity() + " e",
						Long.class).getSingleResult().intValue();
	}

	@Override
	public E getEntityById(long itemId) {
		return entityManager.find(getEntityType(), itemId);
	}

	protected String entity() {
		return getEntityType().getSimpleName();
	}

	protected abstract Class<E> getEntityType();
}
