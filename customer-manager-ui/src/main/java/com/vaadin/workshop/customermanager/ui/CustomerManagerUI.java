package com.vaadin.workshop.customermanager.ui;

import javax.inject.Inject;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@CDIUI("")
@Theme("valo")
public class CustomerManagerUI extends UI {
	private static final long serialVersionUID = 5777549750221495843L;

	private Navigator navigator;

	@Inject
	private CDIViewProvider viewProvider;

	private MenuBar mainMenu;
	private Panel viewArea;

	private class MenuCommand implements MenuBar.Command {
		private static final long serialVersionUID = 4509769006693547853L;

		private final String viewName;

		public MenuCommand(String viewName) {
			this.viewName = viewName;
		}

		@Override
		public void menuSelected(MenuItem selectedItem) {
			navigator.navigateTo(viewName);
		}
	};

	public CustomerManagerUI() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setMargin(true);
		layout.setSpacing(true);

		mainMenu = new MenuBar();
		viewArea = new Panel();

		layout.addComponents(mainMenu, viewArea);
		layout.setExpandRatio(viewArea, 1);
		navigator = new Navigator(this, viewArea);

		mainMenu.addItem("customers", new MenuCommand("customers"));
		mainMenu.addItem("invoices", new MenuCommand("invoices"));

		setContent(layout);
	}

	@Override
	protected void init(VaadinRequest request) {
		navigator.addProvider(viewProvider);
		navigator.navigateTo("customers");
	}
}
