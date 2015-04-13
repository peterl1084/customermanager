package com.vaadin.workshop.customermanager.entity;

import java.util.UUID;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {
	private String uuid;

	public AbstractEntity() {
		uuid = UUID.randomUUID().toString();
	}

	public String getUuid() {
		return uuid;
	}

	public abstract Long getId();

	public boolean isPersisted() {
		return getId() != null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj instanceof AbstractEntity) {
			((AbstractEntity) obj).getUuid().equals(this.uuid);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return uuid.hashCode();
	}
}
