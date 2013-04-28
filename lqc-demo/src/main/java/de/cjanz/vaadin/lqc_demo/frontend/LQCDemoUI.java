package de.cjanz.vaadin.lqc_demo.frontend;

import javax.inject.Inject;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * The Application's "main" class
 */
@Theme(Reindeer.THEME_NAME)
@Widgetset("de.cjanz.vaadin.lqc_demo.frontend.AppWidgetSet")
@Title("Lazy Query Container Demo")
@CDIUI
public class LQCDemoUI extends UI {

	@Inject
	private CDIViewProvider cdiViewProvider;

	@Override
	protected void init(VaadinRequest request) {

		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		setContent(layout);
		layout.setSpacing(true);
		layout.setMargin(true);

		Label header = new Label("LazyQueryContainer Demo");
		header.setStyleName(Reindeer.LABEL_H1);
		layout.addComponent(header);

		Panel viewPanel = new Panel();
		viewPanel.setSizeFull();
		layout.addComponent(viewPanel);
		layout.setExpandRatio(viewPanel, 1f);

		Navigator navigator = new Navigator(this, viewPanel);
		navigator.addProvider(cdiViewProvider);
	}

}
