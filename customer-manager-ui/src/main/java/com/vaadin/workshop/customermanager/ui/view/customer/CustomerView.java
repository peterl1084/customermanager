package com.vaadin.workshop.customermanager.ui.view.customer;

import java.util.Arrays;
import java.util.List;

import com.vaadin.cdi.CDIView;
import com.vaadin.workshop.customermanager.entity.CustomerEntity;
import com.vaadin.workshop.customermanager.service.CustomerService;
import com.vaadin.workshop.customermanager.ui.view.AbstractEntityView;

@CDIView("customers")
public class CustomerView extends
		AbstractEntityView<CustomerEntity, CustomerService> {
	private static final long serialVersionUID = -6085746368038679118L;

	@Override
	protected CustomerEntity buildNewEntity() {
		return new CustomerEntity();
	}

	@Override
	protected Class<CustomerEntity> getEntityType() {
		return CustomerEntity.class;
	}

	@Override
	protected List<String> getVisibleProperties() {
		return Arrays.asList("firstName", "lastName", "birthDate");
	}
}
