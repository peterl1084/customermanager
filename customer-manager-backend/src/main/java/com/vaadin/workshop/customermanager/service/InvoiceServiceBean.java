package com.vaadin.workshop.customermanager.service;

import javax.ejb.Stateless;

import com.vaadin.workshop.customermanager.entity.InvoiceEntity;

@Stateless(name = "InvoiceService")
public class InvoiceServiceBean extends AbstractServiceBean<InvoiceEntity>
		implements InvoiceService {

	@Override
	protected Class<InvoiceEntity> getEntityType() {
		return InvoiceEntity.class;
	}
}
