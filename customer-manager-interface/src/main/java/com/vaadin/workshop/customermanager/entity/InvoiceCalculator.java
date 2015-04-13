package com.vaadin.workshop.customermanager.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InvoiceCalculator {

	public static float calculateSum(int amount, int pricePerItemInCents) {
		return BigDecimal.valueOf(amount)
				.multiply(BigDecimal.valueOf(pricePerItemInCents))
				.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
				.floatValue();
	}
}
