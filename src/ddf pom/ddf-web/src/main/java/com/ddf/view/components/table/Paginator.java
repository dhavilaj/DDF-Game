package com.ddf.view.components.table;

import com.ddf.view.base.ComponentBase;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * This a paged table, with buttons in the button to change the page. Besides,
 * the data loading is lazy, so only one page is loaded at time
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class Paginator extends ComponentBase<PaginatorListener> {
	/* static fields */
	private static final long serialVersionUID = -3964580614701364148L;

	/* instance variables */
	private long totalRows = 0;
	private int rowsPerPage = 10;
	private int totalPages = 0;
	private int pageIndex = 0;

	/**
	 * Constructor
	 * 
	 * @param rowsPerPage
	 */
	public Paginator(int rowsPerPage) {
		// creates main container
		this.rowsPerPage = rowsPerPage;
		HorizontalLayout rootContainer = new HorizontalLayout();

		rootContainer = new HorizontalLayout();
		rootContainer.setStyleName("paginator-wrapper");
		setCompositionRoot(rootContainer);
	}

	/**
	 * Private getter for the paginator container
	 * 
	 * @return
	 */
	private HorizontalLayout getPaginatorContainer() {
		return (HorizontalLayout) getCompositionRoot();
	}

	/* Listener list handling */

	/**
	 * Create paginator, this means putting all page buttons on the buttom
	 */
	private void createPaginator() {
		getPaginatorContainer().removeAllComponents();

		// add first button
		if (pageIndex > 3) {
			getPaginatorContainer().addComponent(createPageButton(1));
		}
		if (pageIndex > 4) {
			getPaginatorContainer().addComponent(new Label("..."));
		}

		// add surrounding buttons
		int lowLimit = Math.max(pageIndex - 2, 1);
		int highLimit = Math.min(pageIndex + 2, totalPages);
		while (highLimit - lowLimit < 4) {
			if (lowLimit > 1) {
				lowLimit--;
			} else if (highLimit < totalPages) {
				highLimit++;
			} else {
				break;
			}
		}

		for (int i = lowLimit; i <= highLimit; i++) {
			Button button = createPageButton(i);
			if (i == pageIndex) {
				button.setStyleName("pagedtable-paginator-current");
				button.setEnabled(false);
			}
			getPaginatorContainer().addComponent(button);
		}

		// add last button
		if ((totalPages - pageIndex) > 3) {
			getPaginatorContainer().addComponent(new Label("..."));
		}
		if ((totalPages - pageIndex) > 2) {
			getPaginatorContainer().addComponent(createPageButton(totalPages));
		}
	}

	/**
	 * @param page
	 * @return
	 */
	@SuppressWarnings("serial")
	private Button createPageButton(final int page) {
		Button button = new Button();
		button.setCaption(page + "");
		button.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				goToPage(page, true);
			}
		});
		return button;
	}

	/**
	 * Set the current page
	 * 
	 * @param pageIndex
	 * @param forceReload If this parameter is false, the page is not reload
	 *            (listener is not called) unless page index has changed. If
	 *            this parameter is true then the page is reload anyway despite
	 *            asked page is the same than current, so the listener will
	 *            always be called
	 */
	public void goToPage(int pageIndex, boolean forceReload) {
		if ((this.pageIndex == pageIndex) && (!forceReload)) {
			return;
		}
		this.pageIndex = pageIndex;

		if (this.pageIndex > totalPages) {
			this.pageIndex = totalPages - 1;
			return;
		}
		if (this.pageIndex < 0) {
			this.pageIndex = 0;
		}
		if ((this.pageIndex == 0) && (totalPages >= 1)) {
			this.pageIndex = 1;
		}

		createPaginator();

		for (PaginatorListener l : getViewListeners()) {
			l.processPageChange(pageIndex, (pageIndex - 1) * rowsPerPage,
				(pageIndex) * rowsPerPage - 1);
		}
	}

	/**
	 * @param totalRows
	 */
	public void setPaginatorInfo(long totalRows) {
		if (totalRows != this.totalRows) {
			this.totalRows = totalRows;
			this.totalPages = (int) Math.ceil((double) totalRows
				/ (double) rowsPerPage);
			createPaginator();
		}
	}

	/**
	 * Set paginator info
	 * 
	 * @param totalRows
	 * @param pageIndex
	 * @param forceReload
	 */
	public void setPaginatorInfo(long totalRows, int pageIndex,
		boolean forceReload) {
		boolean needsCreatePaginator = false;
		if ((totalRows != this.totalRows) || forceReload) {
			this.totalRows = totalRows;
			this.totalPages = (int) Math.ceil((double) totalRows
				/ (double) rowsPerPage);
			needsCreatePaginator = true;
		}
		if ((this.pageIndex != pageIndex) || forceReload) {
			goToPage(pageIndex, true);
		} else if (needsCreatePaginator) {
			createPaginator();
		}

	}

	/**
	 * Getter for the current page index
	 * 
	 * @return Page Index
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * Force page reload calling again listeners to fill page
	 */
	public void reload() {
		setPaginatorInfo(totalRows, pageIndex, true);
	}

}
