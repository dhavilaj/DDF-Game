package com.ddf.commons.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * This value object represents the result of query service
 * 
 * @author David Mantilla
 *
 * @param <T> The bean type
 * @since 1.7
 */
public class QueryResult<T> {

	/* static fields */

	/* instance variables */
	private int startIndex;
	private int endIndex;
	private int totalCount;
	private List<T> result;

	/* constructors */

	/**
	 * Constructor
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @param totalCount
	 * @param result
	 */
	public QueryResult(int startIndex, int endIndex, int totalCount,
			List<T> result) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.totalCount = totalCount;
		this.result = result;
	}

	/**
	 * Constructor
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @param totalCount
	 */
	public QueryResult(int startIndex, int endIndex, int totalCount) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.totalCount = totalCount;
		this.result = new ArrayList<T>();
	}

	/* Methods */

	/* Getters & Setters */

	/**
	 * Getter for startIndex
	 * 
	 * @return the startIndex
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * Setter for startIndex
	 * 
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * Getter for totalCount
	 * 
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * Setter for totalCount
	 * 
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * Getter for result
	 * 
	 * @return the result
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * Setter for result
	 * 
	 * @param result the result to set
	 */
	public void setResult(List<T> result) {
		this.result = result;
	}

	/**
	 * Getter for endIndex
	 * 
	 * @return the endIndex
	 */
	public int getEndIndex() {
		return endIndex;
	}

	/**
	 * Setter for endIndex
	 * 
	 * @param endIndex the endIndex to set
	 */
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
