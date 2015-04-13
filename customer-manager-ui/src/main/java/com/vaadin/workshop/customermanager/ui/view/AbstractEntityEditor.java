package com.vaadin.workshop.customermanager.ui.view;

import javax.inject.Inject;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.workshop.customermanager.entity.AbstractEntity;
import com.vaadin.workshop.customermanager.ui.event.EntitySavedEvent;

/**
 * AbstractEntityEditor is base class for all entity editor beans. Extending
 * classes need to define the entity type as well as implement setupFields(E)
 * method.
 *
 * @param <E>
 *            type of entity to edit
 */
public abstract class AbstractEntityEditor<E extends AbstractEntity> extends
		Window {
	private static final long serialVersionUID = -5759096809247845397L;

	private BeanFieldGroup<E> fieldGroup;
	private FormLayout formLayout;

	@Inject
	private javax.enterprise.event.Event<EntitySavedEvent<E>> saveEventSource;

	public AbstractEntityEditor() {
		setWidth(300, Unit.PIXELS);
		setModal(true);
		setResizable(false);
		center();
		setModal(true);
		setClosable(true);

		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setWidth(100, Unit.PERCENTAGE);

		formLayout = new FormLayout();
		formLayout.setWidth(100, Unit.PERCENTAGE);

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setWidth(100, Unit.PERCENTAGE);
		buttonLayout.setSpacing(true);

		Button save = new Button("Save", e -> onSaveClicked());
		save.setClickShortcut(KeyCode.ENTER);
		save.addStyleName(ValoTheme.BUTTON_PRIMARY);

		Button cancel = new Button("Cancel", e -> onCancelClicked());

		buttonLayout.addComponents(cancel, save);
		buttonLayout.setStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
		buttonLayout.setExpandRatio(cancel, 1);
		buttonLayout.setComponentAlignment(cancel, Alignment.BOTTOM_RIGHT);

		layout.addComponents(formLayout, buttonLayout);
		layout.setExpandRatio(formLayout, 1);
		setContent(layout);
		setCloseShortcut(KeyCode.ESCAPE);
	}

	protected void onSaveClicked() {
		try {
			fieldGroup.commit();
			saveEventSource.fire(new EntitySavedEvent<>(fieldGroup
					.getItemDataSource().getBean()));
			close();
		} catch (CommitException e) {
			Notification.show("Error saving data", Type.TRAY_NOTIFICATION);
		}
	}

	protected void onCancelClicked() {
		close();
	}

	public void setItemToEdit(E entity) {
		fieldGroup = new BeanFieldGroup<E>((Class<E>) entity.getClass());
		fieldGroup.setItemDataSource(entity);
		setupFields(entity);
	}

	protected void addTextField(String propertyId, String caption) {
		TextField field = new TextField(caption);
		field.setNullRepresentation("");
		fieldGroup.bind(field, propertyId);
		formLayout.addComponent(field);
	}

	protected void addTextArea(String propertyId, String caption) {
		TextArea field = new TextArea(caption);
		field.setNullRepresentation("");
		fieldGroup.bind(field, propertyId);
		formLayout.addComponent(field);
	}

	protected void addDatePicker(String propertyId, String caption) {
		PopupDateField field = new PopupDateField(caption);
		field.setResolution(Resolution.DAY);
		fieldGroup.bind(field, propertyId);
		formLayout.addComponent(field);
	}

	protected void addNumberField(String propertyId, String caption) {
		TextField field = new TextField(caption);
		field.setNullRepresentation("");
		field.setConverter(StringToIntegerConverter.class);
		fieldGroup.bind(field, propertyId);
		formLayout.addComponent(field);
	}

	protected abstract void setupFields(E entity);
}
