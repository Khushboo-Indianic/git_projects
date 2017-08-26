package com.example.VaadinSpringBoot;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;

@SpringViewDisplay
@UIScope
public class MainView extends MainViewDesign implements ViewDisplay {

private final Map<Class<? extends View>, Button> navigationButtons = new HashMap<>();
	
	@Autowired
	public NavigationManager navigationManager;

	@PostConstruct
	public void init() {
		attachNavigation(storefront, StoreFrontView.class);
		attachNavigation(dashboard, DashboardView.class);
		attachNavigation(users, UserView.class);
		attachNavigation(products, ProductView.class);
	}
	
	/**
	 * Attach target view with navigation buttons
	 * */
	private void attachNavigation(Button navigationButton, Class<? extends View> targetView) {
		navigationButtons.put(targetView, navigationButton);
		navigationButton.addClickListener(e -> navigationManager.navigateTo(targetView));		
	}
	
	/**
	 * Show selected view
	 * */
	@Override
	public void showView(View view) {
		content.removeAllComponents();
		content.addComponent(view.getViewComponent());

		navigationButtons.forEach((viewClass, button) -> button.setStyleName("selected", viewClass == view.getClass()));
		Button menuItem = navigationButtons.get(view.getClass());
		String viewName = "";
		if (menuItem != null) {
			viewName = menuItem.getCaption();
		}
		activeViewName.setValue(viewName);		
	}

}
