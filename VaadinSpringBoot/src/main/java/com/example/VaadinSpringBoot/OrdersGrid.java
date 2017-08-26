package com.example.VaadinSpringBoot;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;

@SpringComponent
public class OrdersGrid extends Grid<Order> {

	public OrdersGrid() {
		addStyleName("orders-grid");
		setSizeFull();
		removeHeaderRow(0);
	}
	
	
}
