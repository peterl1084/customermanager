package com.vaadin.workshop.customermanager.service;

import javax.ejb.Local;

import com.vaadin.workshop.customermanager.entity.InvoiceEntity;

@Local
public interface InvoiceService extends EntityService<InvoiceEntity> {

}
