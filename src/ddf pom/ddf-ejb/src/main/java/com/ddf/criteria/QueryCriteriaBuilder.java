package com.ddf.criteria;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.beanutils.PropertyUtils;

import com.ddf.commons.vo.criteria.FilterOperation;
import com.ddf.commons.vo.criteria.QueryCriteria;
import com.ddf.commons.vo.criteria.QueryFilterField;
import com.ddf.commons.vo.interfaces.DDFEntity;

/**
 * @author David Mantilla
 *
 * @param <U> Class of the entity of the query to build
 * @since 1.7
 */
public class QueryCriteriaBuilder<U extends DDFEntity> {

	private class FilterProperty {
		String fieldName;
		String[] entityFields;
		FilterOperation filterOperation;
	}

	/* static fields */

	/* instance variables */
	private Class<U> entityClass;
	private Object criteriaBean;
	private List<FilterProperty> filterProperties;

	/* constructors */
	/**
	 * Constructor
	 * 
	 * @param <T> Any Object class
	 * @param criteriaBean Bean with QueryFilterField annotations and values to
	 *            filter by
	 * @param entityClass
	 */
	public <T extends QueryCriteria> QueryCriteriaBuilder(T criteriaBean,
		Class<U> entityClass) {
		this.entityClass = entityClass;
		this.criteriaBean = criteriaBean;
		this.filterProperties = loadFilterFieldsFromAnnotations();
	}

	/* Methods */

	/**
	 * Loads annotations in the criteria bean
	 * 
	 * @return the loaded list
	 */
	private List<FilterProperty> loadFilterFieldsFromAnnotations() {
		List<FilterProperty> filterProperties = new ArrayList<>();

		Class<?> currentClass = criteriaBean.getClass();
		while (currentClass != Object.class) {
			for (Field field : currentClass.getDeclaredFields()) {
				if (field.isAnnotationPresent(QueryFilterField.class)) {
					QueryFilterField annotation = field
						.getAnnotation(QueryFilterField.class);

					FilterProperty fp = new FilterProperty();
					fp.fieldName = field.getName();
					fp.entityFields = annotation.field();
					fp.filterOperation = annotation.operation();
					filterProperties.add(fp);
				}

			}
			currentClass = currentClass.getSuperclass();
		}
		return filterProperties;
	}

	/**
	 * Builds the query criteria given the query criteria annotations and values
	 * in there
	 * 
	 * @param em
	 * @return The build query criteria
	 */
	public CriteriaQuery<U> buildQueryCriteria(EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<U> criteria = cb.createQuery(entityClass);
		Root<U> root = criteria.from(entityClass);
		criteria.select(root);
		buildWhere(cb, criteria, root);
		return criteria;
	}

	/**
	 * Builds a count query with the given criteria and values on it
	 * 
	 * @param em
	 * @return the criteria query
	 */
	public CriteriaQuery<Long> buildCountQueryCriteria(EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
		Root<U> root = criteria.from(entityClass);
		criteria.select(cb.count(root));
		buildWhere(cb, criteria, root);
		return criteria;
	}

	/**
	 * Builds the where section in the query
	 * 
	 * @param cb
	 * @param criteria
	 * @param root
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("rawtypes")
	private void buildWhere(CriteriaBuilder cb, CriteriaQuery<?> criteria,
		Root<U> root) {
		// create a list of conditions
		List<Predicate> predicates = new ArrayList<Predicate>();
		for (FilterProperty filterProperty : filterProperties) {

			// gets the value of the property in the crtieria bean
			Object value = null;
			try {
				value = getCriteriaBeanValue(filterProperty.fieldName);
			} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
				throw new RuntimeException(e);
			}

			// if the criteria bean has not value in the field, then no filter
			// is applied
			if (value != null) {

				// depending on the operation type builds the predicate
				switch (filterProperty.filterOperation) {
				case EQUALS:
					predicates.add(buildEqualsExpresssion(cb, root,
						filterProperty, value));
					break;
				case GREATER_THAN:
				case GREATER_OR_EQUALS_THAN:
				case LESS_THAN:
				case LESS_OR_EQUALS_THAN:
					predicates.add(buildNumericExpresssion(cb, root,
						filterProperty, (Comparable) value));
					break;
				case LIKE:
					predicates.add(buildLikeExpresssion(cb, root,
						filterProperty, value, false));
					break;
				case LIKE_LOWER_CASE:
					predicates.add(buildLikeExpresssion(cb, root,
						filterProperty, value.toString().toLowerCase(), true));
					break;
				default:
					throw new UnsupportedOperationException(
						"Operation not supported yet");
				}
			}
		}

		// Makes a total condition with and-joined conditions
		if (!predicates.isEmpty()) {
			criteria.where(cb.and(predicates.toArray(new Predicate[0])));
		}
	}

	/**
	 * Create likes expressions
	 * 
	 * @param cb
	 * @param root
	 * @param filterProperty
	 * @param value
	 * @param lowerCase If is true the like operation is realized with lower of
	 *            the field: LOWER( field ) LIKE '%...%'
	 * @return
	 */
	private Predicate buildLikeExpresssion(CriteriaBuilder cb, Root<U> root,
		FilterProperty filterProperty, Object value, boolean lowerCase) {

		List<Predicate> orPredicates = new ArrayList<Predicate>();
		for (String entityField : filterProperty.entityFields) {
			Path<String> propertyPath = getPropertyPath(root, entityField);
			String str = (String) value;
			if (lowerCase) {
				orPredicates.add(cb.like(cb.lower(propertyPath), "%" + str
					+ "%"));
			} else {
				orPredicates.add(cb.like(propertyPath, "%" + str + "%"));
			}
		}
		return cb.or(orPredicates.toArray(new Predicate[0]));
	}

	/**
	 * Create equals expressions
	 * 
	 * @param cb
	 * @param root
	 * @param filterProperty
	 * @param value
	 * @return
	 */
	private Predicate buildEqualsExpresssion(CriteriaBuilder cb, Root<U> root,
		QueryCriteriaBuilder<U>.FilterProperty filterProperty, Object value) {

		List<Predicate> orPredicates = new ArrayList<Predicate>();
		for (String entityField : filterProperty.entityFields) {
			orPredicates.add(cb
				.equal(getPropertyPath(root, entityField), value));
		}
		return cb.or(orPredicates.toArray(new Predicate[0]));
	}

	/**
	 * Find a path given the fieldName. This is a wrapper for the basic root.get
	 * because this method supports attributes names with "." like
	 * "subtentity.id"
	 * 
	 * @param <Y>
	 * 
	 * @param root
	 * @param entityFieldName
	 * @return
	 */
	private <Y> Path<Y> getPropertyPath(Root<U> root, String entityFieldName) {
		String[] fields = entityFieldName.split("\\.");

		Path<Y> path = root.get(fields[0]);
		for (int i = 1; i < fields.length; i++) {
			path = path.get(fields[i]);
		}
		return path;
	}

	/**
	 * @param cb
	 * @param root
	 * @param filterProperty
	 * @param value
	 * @return The build predicate
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Predicate buildNumericExpresssion(CriteriaBuilder cb, Root<U> root,
		FilterProperty filterProperty, Comparable value) {

		List<Predicate> orPredicates = new ArrayList<Predicate>();

		for (String entityField : filterProperty.entityFields) {
			Path<Comparable> path = getPropertyPath(root, entityField);
			switch (filterProperty.filterOperation) {

			case GREATER_THAN:
				orPredicates.add(cb.greaterThan(path, value));
				break;
			case GREATER_OR_EQUALS_THAN:
				orPredicates.add(cb.greaterThanOrEqualTo(path, value));
				break;
			case LESS_THAN:
				orPredicates.add(cb.lessThan(path, value));
				break;
			case LESS_OR_EQUALS_THAN:
				orPredicates.add(cb.lessThanOrEqualTo(path, value));
				break;
			default:
				throw new UnsupportedOperationException();
			}
		}
		return cb.or(orPredicates.toArray(new Predicate[0]));
	}

	/**
	 * Gets a property from the criteria builder
	 * 
	 * @param fieldName
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private Object getCriteriaBeanValue(String fieldName)
		throws IllegalAccessException, InvocationTargetException,
		NoSuchMethodException {
		return PropertyUtils.getProperty(criteriaBean, fieldName);
	}

	/* Getters & Setters */
}
