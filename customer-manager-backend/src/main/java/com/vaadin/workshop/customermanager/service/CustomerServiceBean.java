package com.vaadin.workshop.customermanager.service;

import javax.ejb.Stateless;

import com.vaadin.workshop.customermanager.entity.CustomerEntity;

@Stateless(name = "CustomerService")
public class CustomerServiceBean extends AbstractServiceBean<CustomerEntity>
		implements CustomerService {

	@Override
	protected Class<CustomerEntity> getEntityType() {
		return CustomerEntity.class;
	}
}
