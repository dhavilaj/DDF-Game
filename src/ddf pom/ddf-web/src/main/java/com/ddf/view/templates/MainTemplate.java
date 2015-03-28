package com.ddf.view.templates;

import java.util.List;

import com.ddf.view.base.DDFTemplate;
import com.ddf.view.base.DDFView;

/**
 * Interface for the main template
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface MainTemplate extends DDFTemplate,
	DDFView<MainTemplateListener> {

	/* static fields */

	/**
	 * This class represents an application from the view point
	 */
	public class App {
		private Object id;
		private String label;

		/**
		 * Defautlt Constructor
		 */
		public App() {
		}

		/**
		 * Constructor
		 * 
		 * @param id
		 * @param label
		 */
		public App(Object id, String label) {
			super();
			this.id = id;
			this.label = label;
		}

		/**
		 * Getter for id
		 * 
		 * @return the id
		 */
		public Object getId() {
			return id;
		}

		/**
		 * Setter for id
		 * 
		 * @param id the id to set
		 */
		public void setId(Object id) {
			this.id = id;
		}

		/**
		 * Getter for label
		 * 
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}

		/**
		 * Setter for label
		 * 
		 * @param label the label to set
		 */
		public void setLabel(String label) {
			this.label = label;
		}

	}

	/**
	 * This class represents an operation from the view point
	 */
	public class Operation {
		private Object id;
		private String label;

		/**
		 * Default constructor
		 */
		public Operation() {
		}

		/**
		 * Constructor
		 * 
		 * @param id
		 * @param label
		 */
		public Operation(Object id, String label) {
			super();
			this.id = id;
			this.label = label;
		}

		/**
		 * Getter for id
		 * 
		 * @return the id
		 */
		public Object getId() {
			return id;
		}

		/**
		 * Setter for id
		 * 
		 * @param id the id to set
		 */
		public void setId(Object id) {
			this.id = id;
		}

		/**
		 * Getter for label
		 * 
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}

		/**
		 * Setter for label
		 * 
		 * @param label the label to set
		 */
		public void setLabel(String label) {
			this.label = label;
		}

	}

	/* Methods */

	/**
	 * Set application list
	 * 
	 * @param appModelList
	 */
	public void setApplicationList(List<App> appModelList);

	/**
	 * Set recent operation list
	 * 
	 * @param opModelList
	 */
	public void setOperationList(List<Operation> opModelList);

	/**
	 * Set administrator menuItem visible or not
	 * 
	 * @param visible
	 */
	public void setAdminMenuItemVisible(boolean visible);

	/**
	 * Set account email in the label
	 * 
	 * @param accountEmail
	 */
	public void setAccountEmail(String accountEmail);

	/* Getters & Setters */
}
