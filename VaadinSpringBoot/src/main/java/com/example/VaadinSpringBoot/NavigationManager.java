package com.example.VaadinSpringBoot;

import org.springframework.stereotype.Component;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.internal.Conventions;
import com.vaadin.spring.navigator.SpringNavigator;

@Component
@UIScope
public class NavigationManager extends SpringNavigator {
	
	public void navigateToDefaultView() {
		navigateTo(DashboardView.class);
	}
	
	public void navigateTo(Class<? extends View> targetView) {
		String viewId = getViewId(targetView);
		navigateTo(viewId);
	}
	
	public void navigateTo(Class<? extends View> targetView, Object parameter) {
		String viewId = getViewId(targetView);
		navigateTo(viewId + "/" + parameter.toString());
	}
	
	/**
	 * View class must be annoted with @SpringView	 * 
	 * */
	public String getViewId(Class<? extends View> viewClass) {
		SpringView springView = viewClass.getAnnotation(SpringView.class);
		if (springView == null) {
			throw new IllegalArgumentException("The target class must be a @SpringView");
		}
		return Conventions.deriveMappingForView(viewClass, springView);
	}


}
