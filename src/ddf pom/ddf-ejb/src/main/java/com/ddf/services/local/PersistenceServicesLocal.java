package com.ddf.services.local;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Local interface for PersistanceServices
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
@Local
public interface PersistenceServicesLocal {

	/**
	 * @param <T> Entity Type
	 * @param entity Entity to persist
	 * @return the merged entity
	 * @throws PersistenceException when merging fails
	 */
	public <T> T mergeEntity(T entity) throws PersistenceException;

	/**
	 * @param <T> Entity Type
	 * @param entity Entity to persist
	 * @return the persisted entity
	 * @throws PersistenceException when persisting fails
	 */
	public <T> T persistEntity(T entity) throws PersistenceException;

	/**
	 * @param <T> Entity Type
	 * @param entityClass
	 * @param primaryKey
	 * @return the found entity
	 * @throws PersistenceException
	 */
	public <T> T findEntity(Class<T> entityClass, Object primaryKey)
		throws PersistenceException;

	/**
	 * @param <T> expected Type
	 * @param queryName Name of the named query
	 * @param params array with parameters in the order the query expects. If no
	 *            parameters are needed the value can be null
	 * @param expectedClass expected class
	 * @return query result
	 */
	public <T> List<T> executeNamedQuery(String queryName, Object[] params,
		Class<T> expectedClass);

	/**
	 * @param <T> expected Type
	 * @param queryName Name of the named query
	 * @param params array with parameters in the order the query expects. If no
	 *            parameters are needed the value can be null
	 * @param expectedClass expected class
	 * @param startIndex Start index in the result. Send null if want to get
	 *            from start
	 * @param endIndex End index in the query result. Send null if want all
	 *            records
	 * @return query result
	 */
	public <T> List<T> executeNamedQuery(String queryName, Object[] params,
		Class<T> expectedClass, Integer startIndex, Integer endIndex);

	/**
	 * @param <T> Entity class
	 * @param criteriaQuery
	 * @param startIndex Start index in the result. Send null if want to get
	 *            from start
	 * @param endIndex End index in the query result. Send null if want all
	 *            records
	 * @return The query result
	 */
	public <T> List<T> executeCriteriaQuery(CriteriaQuery<T> criteriaQuery,
		Integer startIndex, Integer endIndex);

	/**
	 * @param sentence Query expression
	 * @param params array with parameters in the order the query expects. If no
	 *            parameters are needed the value can be null
	 * @return query result
	 */
	public List<Object> executeNativeQuery(String sentence, Object[] params);

	/**
	 * @return Entity Manager
	 */
	public EntityManager getEntityManager();
}
