package com.vaadin.workshop.customermanager.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Invoice")
public class InvoiceEntity extends AbstractEntity implements
		Iterable<InvoiceLineEntity> {

	@Id
	@Column(name = "Id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Invoice")
	@TableGenerator(name = "Invoice", pkColumnValue = "Invoice", allocationSize = 1, initialValue = 1)
	private Long id;

	@Column(name = "Number", nullable = false)
	private long number;

	@Column(name = "Description")
	private String description;

	@Column(name = "DueDate")
	@Temporal(TemporalType.DATE)
	private Date dueDate;

	@OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
	private List<InvoiceLineEntity> lines;

	@ManyToOne
	@JoinColumn(name = "Customer")
	private CustomerEntity customer;

	@Override
	public Long getId() {
		return id;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public List<InvoiceLineEntity> getLines() {
		return lines;
	}

	public void setLines(List<InvoiceLineEntity> lines) {
		this.lines = lines;
	}

	@Override
	public Iterator<InvoiceLineEntity> iterator() {
		return getLines().iterator();
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public float getTotalPrice() {
		int sum = 0;

		for (InvoiceLineEntity line : this) {
			sum += line.getTotalPrice();
		}

		return sum;
	}
}
