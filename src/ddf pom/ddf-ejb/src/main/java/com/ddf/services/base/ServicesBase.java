package com.ddf.services.base;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.criteria.CriteriaQuery;
import javax.validation.ConstraintViolation;

import com.ddf.commons.exceptions.services.ValidationException;
import com.ddf.commons.validation.BeanValidator;
import com.ddf.commons.vo.QueryResult;
import com.ddf.commons.vo.criteria.QueryCriteria;
import com.ddf.commons.vo.helpers.GenericVOParser;
import com.ddf.commons.vo.interfaces.DDFEntity;
import com.ddf.commons.vo.interfaces.ValueObject;
import com.ddf.criteria.QueryCriteriaBuilder;
import com.ddf.services.local.PersistenceServicesLocal;

/**
 * This is the superclass for all EJB services
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class ServicesBase {

	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */
	/**
	 * Validates a bean based on bean validation annotations. If some validation
	 * fails then a ValidationException is thrown
	 * 
	 * @param <T>
	 * @param bean
	 * @param beanClass
	 * @param locale
	 * @throws ValidationException when the validation fails
	 */
	protected <T> void validateBean(T bean, Class<T> beanClass) {
		Set<ConstraintViolation<T>> violations = BeanValidator.validateBean(
			bean, Locale.getDefault());
		for (ConstraintViolation<T> violation : violations) {
			throw new ValidationException(violation.getPropertyPath() + "-"
				+ violation.getMessage());
		}
	}

	/**
	 * Validates a bean property based on bean property validation annotations.
	 * If some validation fails then a ValidationException is thrown
	 * 
	 * @param <T>
	 * @param bean
	 * @param propertyName
	 * @param propertyLabel If an error occurs, the label instead property-path
	 *            will be put into the message
	 * @param beanClass
	 * @param locale
	 * @throws ValidationException when the validation fails
	 */
	protected <T> void validateProperty(T bean, String propertyName,
		String propertyLabel, Class<T> beanClass) {
		Set<ConstraintViolation<T>> violations = BeanValidator
			.validateProperty(bean, propertyName, Locale.getDefault());
		for (ConstraintViolation<T> violation : violations) {
			throw new ValidationException((propertyLabel == null ? violation
				.getPropertyPath() : propertyLabel)
				+ "-" + violation.getMessage());
		}
	}

	/**
	 * Validates a bean property based on bean property validation annotations.
	 * If some validation fails then a ValidationException is thrown
	 * 
	 * @param <T>
	 * @param bean
	 * @param propertyName
	 * @param beanClass
	 * @param locale
	 * @throws ValidationException when the validation fails
	 */
	protected <T> void validateProperty(T bean, String propertyName,
		Class<T> beanClass) {
		validateProperty(bean, propertyName, null, beanClass);
	}

	/**
	 * Execute a query given by the criteria bean and convert result into value
	 * objects and encapsulate them into a query result object with pagination
	 * information
	 * 
	 * @param <T> Class of the entity
	 * @param <U> Class of the value object
	 * @param queryCriteria
	 * @param entityClass
	 * @param voClass
	 * @param voParser
	 * @param persistenceServices
	 * @return The built query result object
	 */
	protected <T extends DDFEntity, U extends ValueObject> QueryResult<U> findByCriteria(
		QueryCriteria queryCriteria, Class<T> entityClass, Class<U> voClass,
		GenericVOParser<T, U> voParser, PersistenceServicesLocal persistenceServices) {

		QueryCriteriaBuilder<T> builder = new QueryCriteriaBuilder<>(
			queryCriteria, entityClass);
		CriteriaQuery<T> dataCriteria = builder
			.buildQueryCriteria(persistenceServices.getEntityManager());

		// executes data query
		List<T> dataResult = persistenceServices.executeCriteriaQuery(
			dataCriteria, queryCriteria.getStartIndex(), queryCriteria
				.getEndIndex());

		// builds count query
		CriteriaQuery<Long> countCriteria = builder
			.buildCountQueryCriteria(persistenceServices.getEntityManager());

		// executes count query
		List<Long> countResult = persistenceServices.executeCriteriaQuery(
			countCriteria, 0, 1);

		// create result object
		List<U> result = null;
		result = voParser.toValueObjectList(dataResult, voClass);

		int startIndex = queryCriteria.getStartIndex() == null ? 0
			: queryCriteria.getStartIndex();
		int endIndex = queryCriteria.getEndIndex() == null ? countResult.get(0)
			.intValue() : queryCriteria.getEndIndex();

		// builds the resuling object
		QueryResult<U> q = new QueryResult<U>(startIndex, endIndex, countResult
			.get(0).intValue(), result);
		return q;
	}
	/* Getters & Setters */
}
