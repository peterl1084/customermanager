package com.vaadin.workshop.customermanager.ui.view.invoice;

import java.util.Arrays;
import java.util.List;

import com.vaadin.cdi.CDIView;
import com.vaadin.workshop.customermanager.entity.InvoiceEntity;
import com.vaadin.workshop.customermanager.service.InvoiceService;
import com.vaadin.workshop.customermanager.ui.view.AbstractEntityView;

@CDIView("invoices")
public class InvoiceView extends
		AbstractEntityView<InvoiceEntity, InvoiceService> {
	private static final long serialVersionUID = -9139871138906704888L;

	@Override
	protected InvoiceEntity buildNewEntity() {
		return new InvoiceEntity();
	}

	@Override
	protected Class<InvoiceEntity> getEntityType() {
		return InvoiceEntity.class;
	}

	@Override
	protected List<String> getVisibleProperties() {
		return Arrays.asList("id", "number", "dueDate");
	}
}
