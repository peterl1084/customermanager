package com.vaadin.workshop.customermanager.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Customer")
public class CustomerEntity extends AbstractEntity {

	@Id
	@Column(name = "Id", nullable = false, unique = true)
	@TableGenerator(name = "Customer", pkColumnValue = "Customer", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Customer")
	private Long id;

	@Column(name = "Firstname")
	private String firstName;

	@Column(name = "Lastname")
	private String lastName;

	@Column(name = "Birthdate")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	private List<InvoiceEntity> invoices;

	@Override
	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<InvoiceEntity> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<InvoiceEntity> invoices) {
		this.invoices = invoices;
	}
}
