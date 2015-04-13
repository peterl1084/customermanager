package com.vaadin.workshop.customermanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "InvoiceLine")
public class InvoiceLineEntity extends AbstractEntity {

	@Id
	@Column(name = "Id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "InvoiceLine")
	@TableGenerator(name = "InvoiceLine", pkColumnValue = "InvoiceLine", allocationSize = 1, initialValue = 1)
	private Long id;

	@Column(name = "Description")
	private String description;

	@Column(name = "Amount", nullable = false)
	private int amount;

	@Column(name = "Price", nullable = false)
	private int priceCents;

	@ManyToOne
	@JoinColumn(name = "Invoice")
	private InvoiceEntity invoice;

	@Override
	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPriceCents() {
		return priceCents;
	}

	public void setPriceCents(int priceCents) {
		this.priceCents = priceCents;
	}

	public InvoiceEntity getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceEntity invoice) {
		this.invoice = invoice;
	}

	public float getTotalPrice() {
		return InvoiceCalculator.calculateSum(getAmount(), getPriceCents());
	}
}
