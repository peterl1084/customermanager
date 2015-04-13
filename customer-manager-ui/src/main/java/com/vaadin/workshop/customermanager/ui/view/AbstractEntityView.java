package com.vaadin.workshop.customermanager.ui.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.vaadin.addons.lazyquerycontainer.LazyQueryContainer;
import org.vaadin.addons.lazyquerycontainer.Query;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;
import org.vaadin.addons.lazyquerycontainer.QueryFactory;

import com.vaadin.cdi.ViewScoped;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.workshop.customermanager.entity.AbstractEntity;
import com.vaadin.workshop.customermanager.service.EntityService;
import com.vaadin.workshop.customermanager.ui.EntityQuery;
import com.vaadin.workshop.customermanager.ui.event.EntitySavedEvent;

/**
 * AbstractEntityView is abstract super class for all entity editor views. The
 * purpose of this class is to abstract the concept of displaying entities in a
 * table and being able to open entity type specific editor for entity item.
 *
 * @param <E>
 *            type of the entity
 * @param <ES>
 *            type of the service used to manipulate entities (in backend
 *            server)
 */
@ViewScoped
public abstract class AbstractEntityView<E extends AbstractEntity, ES extends EntityService<E>>
		extends CustomComponent implements View {
	private static final long serialVersionUID = -7330922688670728867L;

	@EJB
	private ES entityService;

	@Inject
	private Instance<AbstractEntityEditor<E>> editorInstantiator;

	private Table table;
	private LazyQueryContainer container;

	@Override
	public final void enter(ViewChangeEvent event) {
		container.refresh();
	}

	public AbstractEntityView() {
		setSizeFull();

		MenuBar menu = new MenuBar();
		menu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		menu.addStyleName(ValoTheme.MENUBAR_SMALL);

		menu.addItem("Add", e -> onAddPressed());
		menu.addItem("Remove", e -> onRemovePressed());

		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setMargin(true);

		table = new Table();
		table.setSizeFull();
		table.setSelectable(true);
		table.addItemClickListener(e -> onItemClick(e));

		layout.addComponents(menu, table);
		layout.setExpandRatio(table, 1);
		layout.setComponentAlignment(menu, Alignment.TOP_RIGHT);

		setCompositionRoot(layout);
	}

	@PostConstruct
	protected void initialize() {
		container = new LazyQueryContainer(new DTOQueryFactory(), "id", 50,
				false);
		for (String propertyId : getVisibleProperties()) {
			container.addContainerProperty(propertyId, String.class, null);
		}

		table.setContainerDataSource(container);
	}

	protected void onAddPressed() {
		openEditorFor(buildNewEntity());
	}

	protected void onRemovePressed() {
		Long itemId = (Long) table.getValue();
		if (itemId == null)
			return;

		E entity = entityService.getEntityById(itemId);
		if (entity == null)
			return;

		entityService.remove(entity);
		container.refresh();
	}

	protected void onItemClick(ItemClickEvent event) {
		if (event.isDoubleClick()) {
			Long itemId = (Long) event.getItemId();
			if (itemId == null)
				return;

			openEditorFor(entityService.getEntityById(itemId));
		}
	}

	protected void openEditorFor(E entity) {
		if (editorInstantiator.isUnsatisfied())
			throw new RuntimeException("Could not find editor for entity type "
					+ getEntityType().getSimpleName());

		if (editorInstantiator.isAmbiguous())
			throw new RuntimeException(
					"More than one editor was found for entity type "
							+ getEntityType().getSimpleName());

		AbstractEntityEditor<E> currentEditor = editorInstantiator.get();
		currentEditor.setItemToEdit(entity);
		UI.getCurrent().addWindow(currentEditor);
	}

	protected void onEntitySaved(
			@Observes(notifyObserver = Reception.IF_EXISTS) EntitySavedEvent<E> event) {
		entityService.store(event.getEntity());
		container.refresh();
	}

	protected abstract Class<E> getEntityType();

	protected abstract List<String> getVisibleProperties();

	protected abstract E buildNewEntity();

	private class DTOQueryFactory implements QueryFactory {
		@Override
		public Query constructQuery(QueryDefinition definition) {
			return new EntityQuery<E>(definition, entityService,
					getEntityType());
		}
	}
}
