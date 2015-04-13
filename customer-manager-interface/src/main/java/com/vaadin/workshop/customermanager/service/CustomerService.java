package com.vaadin.workshop.customermanager.service;

import javax.ejb.Local;

import com.vaadin.workshop.customermanager.entity.CustomerEntity;

@Local
public interface CustomerService extends EntityService<CustomerEntity> {

}
