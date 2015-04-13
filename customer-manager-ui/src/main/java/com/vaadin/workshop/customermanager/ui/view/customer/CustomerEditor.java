package com.vaadin.workshop.customermanager.ui.view.customer;

import com.vaadin.workshop.customermanager.entity.CustomerEntity;
import com.vaadin.workshop.customermanager.ui.view.AbstractEntityEditor;

public class CustomerEditor extends AbstractEntityEditor<CustomerEntity> {
	private static final long serialVersionUID = 3176042555244906710L;

	@Override
	protected void setupFields(CustomerEntity entity) {
		addTextField("firstName", "First name");
		addTextField("lastName", "Last name");
		addDatePicker("birthDate", "Birth date");
	}
}
