package com.ddf.view.components.table;

import com.ddf.view.base.DDFViewListener;

/**
 * Paginator listener
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface PaginatorListener extends DDFViewListener{
	
	/* static fields */

	/* Methods */

	/**
	 * Process the page change generated by the paginator. The implementation of
	 * this method should load the page in the table based on the required
	 * indexes
	 * 
	 * @param pageIndex
	 * @param startIndex
	 * @param endIndex
	 */
	public void processPageChange(int pageIndex, int startIndex, int endIndex);

	/* Getters & Setters */
}
