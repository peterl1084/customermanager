package com.vaadin.workshop.customermanager.entity;

import org.junit.Assert;
import org.junit.Test;

public class InvoiceCalculatorTests {

	@Test
	public void testInvoiceCalculator_TenItems_HundredCentsEach_TenEurosTotal() {
		Assert.assertEquals(10, InvoiceCalculator.calculateSum(10, 100), 0);
	}

	@Test
	public void testInvoiceCalculator_ZeroItems_HundredCentsEach_ZeroEurosTotal() {
		Assert.assertEquals(0, InvoiceCalculator.calculateSum(0, 100), 0);
	}

	@Test
	public void testInvoiceCalculator_TenItems_ZeroCentsEach_ZeroEurosTotal() {
		Assert.assertEquals(0, InvoiceCalculator.calculateSum(10, 0), 0);
	}

	@Test
	public void testInvoiceCalculator_TenItems_FiftyCentsEach_FiveEurosTotal() {
		Assert.assertEquals(5, InvoiceCalculator.calculateSum(10, 50), 0);
	}

	@Test
	public void testInvoiceCalculator_TenItems_FiftyFiveCentsEach_FiveEurosFiftyTotal() {
		Assert.assertEquals(5.50, InvoiceCalculator.calculateSum(10, 55), 0);
	}
}
