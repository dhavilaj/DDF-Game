package com.ddf.commons.vo;

import com.ddf.commons.vo.interfaces.ValueObject;

/**
 * Value object for sending and receiving Files
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
public class FileVO implements ValueObject {
	/* static fields */

	/* instance variables */
	private Long id;
	private String originalName;
	private java.io.File content;
	private String mimeType;
	private String code;
	private String url;

	/* constructors */

	/**
	 * Constructor
	 */
	public FileVO() {
	}

	/**
	 * Constructor a new instances with the same values than the passed value
	 * object
	 * 
	 * @param fileVO
	 */
	public FileVO(FileVO fileVO) {
		this.code = fileVO.code;
		this.content = fileVO.content;
		this.id = fileVO.id;
		this.mimeType = fileVO.mimeType;
		this.originalName = fileVO.originalName;
		this.url = fileVO.url;
	}

	/* Methods */

	/**
	 * Constructor
	 * 
	 * @param originalName
	 * @param mimeType
	 * @param code
	 * @param url
	 */
	public FileVO(String originalName, String mimeType,
		String code, String url) {
		super();
		this.originalName = originalName;
		this.mimeType = mimeType;
		this.code = code;
		this.url = url;
	}

	/* Getters & Setters */
	/**
	 * Getter for id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for id
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for originalName
	 * 
	 * @return the originalName
	 */
	public String getOriginalName() {
		return originalName;
	}

	/**
	 * Setter for originalName
	 * 
	 * @param originalName the originalName to set
	 */
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	/**
	 * Getter for content
	 * 
	 * @return the content
	 */
	public java.io.File getContent() {
		return content;
	}

	/**
	 * Setter for content
	 * 
	 * @param content the content to set
	 */
	public void setContent(java.io.File content) {
		this.content = content;
	}

	/**
	 * Getter for mimeType
	 * 
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * Setter for mimeType
	 * 
	 * @param mimeType the mimeType to set
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * Getter for code
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter for code
	 * 
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter for url
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Setter for url
	 * 
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
