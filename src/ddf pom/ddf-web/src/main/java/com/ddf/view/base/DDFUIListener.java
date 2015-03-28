package com.ddf.view.base;


/**
 * Interface for the UISocial listener
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
@SuppressWarnings("rawtypes")
public interface DDFUIListener {

	/**
	 * Process the view change event before the change happens
	 * 
	 * @param socialView
	 * @param socialView2
	 * @return true if the change is allowed or false if the change is wanted to
	 *         be avoided
	 */
	public boolean processBeforeViewChange(DDFView socialView,
			DDFView socialView2);

	/**
	 * Process the view change event after the change happens
	 * 
	 * @param socialView
	 * @param socialView2
	 */
	public void processAfterViewChange(DDFView socialView,
			DDFView socialView2);

}
