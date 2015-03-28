package com.ddf.view.base;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ddf.model.controlaccess.DDFViewEnum;

/**
 * Interface for the social main UI
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface DDFUI {

	/**
	 * Enumeration with all templates
	 */
	@SuppressWarnings("javadoc")
	public enum TemplateType {
		NONE,
		PUBLIC,
		MAIN;
	}

	/**
	 * Add a listener
	 * 
	 * @param socialUIListener
	 */
	public void addSocialUIListener(DDFUIListener socialUIListener);

	/**
	 * Clear view
	 */
	public void clearSocialUIListeners();

	/**
	 * Remove a listener
	 * 
	 * @param listener
	 */
	public void removeSocialUIListener(DDFUIListener listener);

	/**
	 * Getter for the social UI listeners
	 * 
	 * @return the list of SocialUIListenrs
	 */
	public List<DDFUIListener> getSocialUIListeners();

	/**
	 * Navigates to a new view
	 * 
	 * @param socialViewEnum
	 */
	public void navigateTo(DDFViewEnum socialViewEnum);

	/**
	 * Navigates to a new view with parameters
	 * 
	 * @param socialViewEnum view for navigate to
	 * @param params Map(name-value) with the parameters to pass to the new view
	 */
	public void navigateTo(DDFViewEnum socialViewEnum,
		Map<String, String> params);

	/**
	 * Getter for the current Request
	 * 
	 * @return the current Request
	 */
	public HttpServletRequest getCurrentRequest();

	/**
	 * Return the value of the viewLeavingCheckEnabled Flag This flag enables or
	 * disables the page leaving check. If the flag is disabled, the "leave"
	 * method is called but the result is ignored, so the navigation is done
	 * despite a false return
	 * 
	 * @return the value of the viewLeavingCheckEnabled Flag
	 */
	public boolean isViewLeavingCheckEnabled();

	/**
	 * This flag enables or disables the page leaving check for the next
	 * navigation try (only for the next one). If the flag is disabled, the
	 * "leave" method is called but the result is ignored, so the navigation is
	 * done despite a false return
	 * 
	 * @param viewLeavingCheckEnabled
	 */
	public void setViewLeavingCheckEnabled(boolean viewLeavingCheckEnabled);

	/**
	 * This method change the current template. If the UI is currently in the
	 * requested template then the method doesn't do anything
	 * 
	 * @param requestedTemplate
	 * @return True if a change was required, this means that the template was
	 *         another than the requested one. False if a change wasn't
	 *         required, that means the requested template was the same as the
	 *         current template
	 */
	boolean setSocialTemplate(TemplateType requestedTemplate);

}
