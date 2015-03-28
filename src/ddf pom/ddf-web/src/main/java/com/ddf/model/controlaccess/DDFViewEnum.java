package com.ddf.model.controlaccess;

import java.util.Map;

import com.ddf.commons.SystemConstans;
import com.ddf.view.base.DDFView;
import com.ddf.view.base.DDFViewBase;
import com.ddf.view.views.defaultview.DefaultView;
import com.ddf.view.views.forbidden.ForbiddenViewImpl;
import com.ddf.view.views.home.HomeViewImpl;
import com.ddf.view.views.login.LoginViewImpl;
import com.ddf.view.views.password.PassRecoverRequestViewImpl;
import com.ddf.view.views.password.PassRecoveryViewImpl;
import com.ddf.view.views.register.RegisterViewImpl;
import com.ddf.view.views.tests.TestViewImpl;

/**
 * Enumeration to control all the views in the application
 * 
 * @author David Mantilla
 * @since 1.7
 *
 */
@SuppressWarnings({ "javadoc", "rawtypes" })
public enum DDFViewEnum {

	// Public pages
	/**
	 * Page that is shown every time user is not allowed to enter the page is
	 * trying to get into
	 */
	FORBIDDEN(DDFViewNames.FORBIDDEN, ForbiddenViewImpl.class),
	/**
	 * Dummy page to redirect to the right page depending on the login status.
	 * This means login with no-logged user, home with logged-user
	 */
	DEFAULT_VIEW(DDFViewNames.DEFAULT, DefaultView.class),

	// access controlled pages
	/**
	 * Page to log in
	 */
	LOGIN(DDFViewNames.LOGIN, LoginViewImpl.class),
	/**
	 * Page to registering new partner
	 */
	REGISTER(DDFViewNames.REGISTER, RegisterViewImpl.class,
			UseCaseEnum.REGISTER),
	/**
	 * Default page when the user is logged
	 */
	HOME(DDFViewNames.HOME, HomeViewImpl.class, UseCaseEnum.SEE_SOCIAL_HOME),

	/**
	 * Page for user ask the password to be recovery by asking an email
	 */
	PASSWORD_RECOVERY_REQUEST(DDFViewNames.PASSWORD_RECOVERY_REQUEST,
			PassRecoverRequestViewImpl.class),

	/**
	 * Page for user recover password writing a new one
	 */
	PASSWORD_RECOVERY(DDFViewNames.PASSWORD_RECOVERY,
			PassRecoveryViewImpl.class),

	// test
	TEST("test", TestViewImpl.class),

	/**
	 * Page with application list
	 */
	APPS(DDFViewNames.APPS, null);

	/* Static */

	/* Instance variables */
	private final String viewName;
	private final Class<? extends DDFView> viewClass;
	private final UseCaseEnum[] useCases;
	private final boolean alwasyPublic;

	/**
	 * Constructor
	 * 
	 * @param viewName
	 *            Name to identify page
	 * @param viewClass
	 *            Class of the view
	 * @param useCases
	 *            useCases that the page uses. Whit those use-cases, the control
	 *            access will evaluate if the use has or not access to the page.
	 *            At least one use-case access is required to be able to enter
	 *            to the page, otherwise forbidden page is redirected
	 */

	private DDFViewEnum(String viewName,
			Class<? extends DDFViewBase> viewClass, UseCaseEnum... useCases) {
		this.viewName = viewName;
		this.viewClass = viewClass;
		this.useCases = useCases;
		this.alwasyPublic = false;
	}

	/**
	 * Constructor
	 * 
	 * @param viewName
	 *            Name to identify page
	 * @param viewClass
	 *            Class of the view
	 */
	private DDFViewEnum(String viewName,
			Class<? extends DDFViewBase> viewClass) {
		this.viewName = viewName;
		this.viewClass = viewClass;
		this.useCases = new UseCaseEnum[0];
		this.alwasyPublic = true;
	}

	/* Getters & Setters */

	/**
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}

	/**
	 * @return the viewClass
	 */
	public Class<? extends DDFView> getViewClass() {
		return viewClass;
	}

	/**
	 * @return the useCases
	 */
	public UseCaseEnum[] getUseCases() {
		return useCases;
	}

	/**
	 * @return the alwasyPublic
	 */
	public boolean isAlwasyPublic() {
		return alwasyPublic;
	}

	public String getAbsolutePath() {
		return getAbsolutePath(null);
	}

	/**
	 * @param params
	 * @return
	 */
	public String getAbsolutePath(Map<String, String> params) {
		StringBuilder str = new StringBuilder();
		str.append(SystemConstans.SERVER_BASE_URL);
		str.append(SystemConstans.APP_SERVER_CONTEXT);
		str.append("/#!");
		str.append(viewName);

		if (params != null) {
			for (String key : params.keySet()) {
				str.append("/").append(key).append("/").append(params.get(key));
			}
		}
		return str.toString();
	}

	/* Static utils */

	/**
	 * Identify the enumeration of a view given the view object
	 * 
	 * @param socialView
	 * @return
	 * @throws IllegalArgumentException
	 *             when the view is not in the enumeration
	 */
	public static DDFViewEnum identifyView(DDFView socialView) {
		for (DDFViewEnum e : values()) {
			if (e.getViewClass().equals(socialView.getClass())) {
				return e;
			}
		}
		throw new IllegalArgumentException();
	}

	/**
	 * Find a view given its class
	 * 
	 * @param clazz
	 * @return
	 */
	public static DDFViewEnum findByClass(
			Class<? extends DDFViewBase> clazz) {

		for (DDFViewEnum s : values()) {
			if (s.viewClass.equals(clazz)) {
				return s;
			}
		}
		throw new IllegalArgumentException(clazz + " not found in the enum");
	}

}