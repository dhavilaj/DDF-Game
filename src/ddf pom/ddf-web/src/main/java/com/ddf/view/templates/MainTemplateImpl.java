package com.ddf.view.templates;

import java.util.List;
import java.util.Map;

import com.ddf.view.base.DDFUI.TemplateType;
import com.ddf.view.base.DDFViewBase;
import com.ddf.view.i18n.Messages;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

/**
 * @author David Mantilla
 * @since 1.7
 */
public class MainTemplateImpl extends DDFViewBase<MainTemplateListener>
	implements MainTemplate {

	private static final long serialVersionUID = 1L;

	/* static fields */
	private enum UserMenuItemEnum {
		ADMIN,
		MY_ACCOUNT,
		LOGOUT;
	}

	/* instance variables */
	private HorizontalLayout headerWrapper;
	private CssLayout viewWrapper;
	private MenuItem appsContainer;
	private MenuItem opsContainer;
	private Label emailLabel;
	private MenuItem userItemsContainer;
	@SuppressWarnings("unused")
	private MenuItem myAccountMenuItem;
	private MenuItem adminMenuItem;
	@SuppressWarnings("unused")
	private MenuItem logutMenuItem;
	private MenuBar appMenuBar;
	private MenuBar operationsMenu;

	/* constructors */

	/**
	 * Default constructor
	 */
	public MainTemplateImpl() {
		initComponents();
	}

	/**
	 * Create all components
	 */
	@SuppressWarnings("serial")
	private void initComponents() {
		// Header
		headerWrapper = new HorizontalLayout();
		headerWrapper.setStyleName("header-wrapper");
		this.addComponent(headerWrapper);

		// Logo
		VerticalLayout logoLayout = new VerticalLayout();
		logoLayout.addStyleName("mobiera-logo");
		headerWrapper.addComponent(logoLayout);
		logoLayout.addLayoutClickListener(new LayoutClickListener() {
			@Override
			public void layoutClick(LayoutClickEvent event) {
				for (MainTemplateListener l : getViewListeners()) {
					l.processLogoClick();
				}
			}
		});

		// Applications list
		initApplicationsMenu();

		// operations to supervise
		initOperationsMenu();

		// email label
		emailLabel = new Label();
		emailLabel.setStyleName("email-info");
		headerWrapper.addComponent(emailLabel);

		// user Menu
		initUserMenu();

		viewWrapper = new CssLayout();
		this.addComponent(viewWrapper);
	}

	private void initApplicationsMenu() {
		appMenuBar = new MenuBar();
		appMenuBar.setWidth(100.0f, Unit.PERCENTAGE);
		headerWrapper.addComponent(appMenuBar);

		// adds the top level item
		appsContainer = null;
		appsContainer = appMenuBar.addItem("", null);
		appsContainer.setIcon(new ThemeResource("img/icon-app-32.png"));
		appsContainer.setEnabled(true);
	}

	/**
	 * Initialize operations menu bar
	 */
	private void initOperationsMenu() {
		operationsMenu = new MenuBar();
		operationsMenu.setWidth(100.0f, Unit.PERCENTAGE);
		headerWrapper.addComponent(operationsMenu);

		// adds the top level item
		opsContainer = null;
		opsContainer = operationsMenu.addItem(Messages
			.getString("MainTemplate.Operations"), null);
		opsContainer.setEnabled(true);
	}

	/**
	 * initUserMenu
	 */
	private void initUserMenu() {
		// User menu
		MenuBar userMenu = new MenuBar();
		userMenu.setWidth(100.0f, Unit.PERCENTAGE);
		headerWrapper.addComponent(userMenu);

		// adds the user level item
		userItemsContainer = null;
		userItemsContainer = userMenu.addItem("", null);
		userItemsContainer.setEnabled(true);
		userItemsContainer.setIcon(new ThemeResource("img/icon-user-32.png"));

		// adds my account item
		myAccountMenuItem = userItemsContainer.addItem(Messages
			.getString("MainTemplate.MyAccount"),
			createUserMenuItemCommand(UserMenuItemEnum.MY_ACCOUNT));

		// adds administrator item
		adminMenuItem = userItemsContainer.addItem(Messages
			.getString("MainTemplate.Admin"),
			createUserMenuItemCommand(UserMenuItemEnum.ADMIN));

		// adds logout item
		logutMenuItem = userItemsContainer.addItem(Messages
			.getString("MainTemplate.Logout"),
			createUserMenuItemCommand(UserMenuItemEnum.LOGOUT));
	}

	/* Methods */

	/* Getters & Setters */
	/**
	 * @param userMenuItemEnum
	 * @return
	 */
	@SuppressWarnings("serial")
	private Command createUserMenuItemCommand(
		final UserMenuItemEnum userMenuItemEnum) {
		return new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				switch (userMenuItemEnum) {
				case ADMIN:
					for (MainTemplateListener l : getViewListeners()) {
						l.processAdminMenuItemClick();
					}
					break;
				case LOGOUT:
					for (MainTemplateListener l : getViewListeners()) {
						l.processLogoutMenuItemClick();
					}
					break;
				case MY_ACCOUNT:
					for (MainTemplateListener l : getViewListeners()) {
						l.processMyAccountMenuItemClick();
					}
					break;
				default:
					throw new UnsupportedOperationException("Not yet");
				}
			}
		};
	}

	/**
	 * Creates a command for the applications menu item
	 * 
	 * @param app
	 * @return
	 */
	@SuppressWarnings("serial")
	private Command createAppMenuItemCommand(final App app) {
		return new Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				for (MainTemplateListener l : getViewListeners()) {
					l.processApplicationClick(app);
				}
			}
		};
	}

	/**
	 * Creates a command for the applications menu item
	 * 
	 * @param op
	 * @return
	 */
	@SuppressWarnings("serial")
	private Command createOperationMenuItemCommand(final Operation op) {
		return new Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				for (MainTemplateListener l : getViewListeners()) {
					l.processSuperviseOperationClick(op);
				}
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.base.SocialTemplate#getNavigationSection()
	 */
	@Override
	public ComponentContainer getNavigationSection() {
		return viewWrapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.templates.MainTemplate#setApplicationList(java
	 * .util.List)
	 */
	@Override
	public void setApplicationList(List<App> appModelList) {
		if (appModelList == null) {
			appMenuBar.setVisible(false);
			return;
		}
		if (appsContainer.getChildren() != null) {
			appsContainer.getChildren().clear();
		}
		for (App app : appModelList) {
			appsContainer
				.addItem(app.getLabel(), createAppMenuItemCommand(app));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.templates.MainTemplate#setRecentOperationList
	 * (java.util.List)
	 */
	@SuppressWarnings("serial")
	@Override
	public void setOperationList(List<Operation> opModelList) {
		if (opModelList == null) {
			appMenuBar.setVisible(false);
			return;
		}
		if (opsContainer.getChildren() != null) {
			opsContainer.getChildren().clear();
		}
		for (Operation op : opModelList) {
			opsContainer.addItem(op.getLabel(),
				createOperationMenuItemCommand(op));
		}

		// More item
		opsContainer.addItem(Messages.getString("MainTemplate.More") + "...",
			new Command() {

				@Override
				public void menuSelected(MenuItem selectedItem) {
					for (MainTemplateListener l : getViewListeners()) {
						l.processMoreSupervisedOperationsClick();
					}
				}
			});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.templates.MainTemplate#setAdminMenuItemVisible
	 * (boolean)
	 */
	@Override
	public void setAdminMenuItemVisible(boolean visible) {
		adminMenuItem.setVisible(visible);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.templates.MainTemplate#setAccountEmail(java.lang
	 * .String)
	 */
	@Override
	public void setAccountEmail(String accountEmail) {
		emailLabel.setCaption(accountEmail);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.base.SocialViewBase#enterSocialView(java.util
	 * .Map, com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	protected void enterSocialView(Map<String, String> params,
		ViewChangeEvent event) {
		//
	}

	@Override
	public TemplateType getTemplateType() {
		throw new UnsupportedOperationException(
			"Not supposed to use into another template");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.base.SocialViewBase#initSocialViewl(java.util
	 * .Map, com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	protected void initSocialViewl(Map<String, String> viewParameters2,
		ViewChangeEvent event) {
		//
	}

}
